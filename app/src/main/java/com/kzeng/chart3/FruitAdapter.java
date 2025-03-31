package com.kzeng.chart3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kzeng.R;

import java.util.List;

public class FruitAdapter extends ArrayAdapter<Fruit> {
    // 声明一个私有整型变量，用于存储布局资源ID
    private int resourceId;

    // 构造方法
    // @param context 上下文对象，通常是Activity实例
    // @param textViewResourceId 列表项的布局资源ID
    // @param objects 要显示的数据数组（Fruit对象数组）
    public FruitAdapter(@NonNull Context context, int textViewResourceId, List<Fruit> objects) {
        // 调用父类(ArrayAdapter)的构造方法
        super(context, textViewResourceId, objects);
        // 将传入的布局资源ID保存到成员变量中
        resourceId = textViewResourceId;
    }
    //重写getView方法，用于创建和绑定列表项的视图
    // @param position 当前项在列表中的位置(索引)
    // @param convertView 可重用的旧视图(用于视图回收)
    // @param parent 父视图(ListView或GridView)
    // @return 返回处理好的列表项视图
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        Fruit fruit = getItem(position);
        if (convertView == null) {
            // 只有convertView为空时才新建视图
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.fruitImage = view.findViewById(R.id.fruit_image);
            viewHolder.textView = view.findViewById(R.id.fruit_name);
            view.setTag(viewHolder);

        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }


        assert fruit != null;
        viewHolder.fruitImage.setImageResource(fruit.getImageId());
        viewHolder.textView.setText(fruit.getName());

        return view;
    }

    static class ViewHolder {
        ImageView fruitImage;
        TextView textView;
    }
}
