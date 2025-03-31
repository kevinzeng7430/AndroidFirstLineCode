package com.kzeng.DownloadTest;

import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadTask  {
    public static final int TYPE_SUCCESS = 0;
    public static final int TYPE_FAILED = 1;
    public static final int TYPE_PAUSED = 2;
    public static final int TYPE_CANCELED = 3;

    private static final int MSG_START_DOWNLOAD = 1;
    private static final int MSG_UPDATE_PROGRESS = 2;
    private static final int MSG_DOWNLOAD_FINISHED = 3;

    private final DownloadListener listener;
    private boolean isCanceled = false;
    private boolean isPaused = false;
    private int lastProgress;

    private final HandlerThread downloadThread;
    private Handler downloadHandler;
    private final Handler mainHandler;

    public DownloadTask(DownloadListener downloadListener){
        this.listener = downloadListener;

        //创建后台线程用于下载
        downloadThread = new HandlerThread("DownloadThread");
        downloadThread.start();

        //主线程用于更新UI
        mainHandler = new Handler(android.os.Looper.getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == MSG_START_DOWNLOAD) {
                    String downloadUrl = (String) msg.obj;
                    preformDownload(downloadUrl);
                }
            }
        };
    }

    public void execute(String downloadUrl){
        Message message = Message.obtain();
        message.what = MSG_START_DOWNLOAD;
        message.obj = downloadUrl;
        mainHandler.sendMessage(message);
    }

    public void preformDownload(String downloadUrl) {
        InputStream is = null;
        RandomAccessFile savedFile = null;
        File file = null;
        try {
            long downloadedLength = 0;
            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
            String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            file = new File(directory + fileName);
            if (file.exists()) {
                downloadedLength = file.length();
            }

            long contentLength = getContentLength(downloadUrl);
            if (contentLength == 0) {
                notifyDownloadFinished(TYPE_FAILED);
                return;
            } else if (contentLength == downloadedLength) {
                notifyDownloadFinished(TYPE_SUCCESS);
                return;
            }

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .addHeader("RANGE", "bytes=" + downloadedLength + "-")
                    .url(downloadUrl)
                    .build();

            Response response = client.newCall(request).execute();

            assert response.body() != null;
            is = response.body().byteStream();
            savedFile = new RandomAccessFile(file, "rw");
            savedFile.seek(downloadedLength);
            byte[] b = new byte[1024];
            int total = 0;
            int len;

            while ((len = is.read(b)) != -1) {
                if (isCanceled) {
                    notifyDownloadFinished(TYPE_CANCELED);
                    return;
                } else if (isPaused) {
                    notifyDownloadFinished(TYPE_PAUSED);
                    return;
                } else {
                    total += len;
                    savedFile.write(b, 0, len);
                    int progress = (int) ((total + downloadedLength) * 100 / contentLength);
                    if (progress > lastProgress) {
                        notifyProgress(progress);
                        lastProgress = progress;
                    }
                }
            }
            response.body().close();
            notifyDownloadFinished(TYPE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            notifyDownloadFinished(TYPE_FAILED);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (savedFile != null) {
                    savedFile.close();
                }
                if (isCanceled && file != null) {
                    file.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void notifyProgress(final int progress) {
        mainHandler.post(() -> listener.onProgress(progress));
    }

    private void notifyDownloadFinished(final int type) {
        mainHandler.post(() -> {
            switch (type) {
                case TYPE_SUCCESS:
                    listener.onSuccess();
                    break;
                case TYPE_FAILED:
                    listener.onFailed();
                    break;
                case TYPE_PAUSED:
                    listener.onPaused();
                    break;
                case TYPE_CANCELED:
                    listener.onCanceled();
                    break;
            }
        });
    }

    public void pauseDownload() {
        isPaused = true;
    }

    public void cancelDownload() {
        isCanceled = true;
    }

    private long getContentLength(String downloadUrl) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(downloadUrl)
                .build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            assert response.body() != null;
            long contentLength = response.body().contentLength();
            response.close();
            return contentLength;
        }
        return 0;
    }

    public void destroy() {
        if (downloadThread != null) {
            downloadThread.quit();
        }
    }
}