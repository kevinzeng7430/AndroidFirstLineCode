package com.kzeng.chart4Fragment;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.kzeng.R;

public class BasicChartFourActivity extends AppCompatActivity implements LeftFragment.OnButtonClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_chart_four);
        //replaceFragment(new RightFragment());
    }

    @Override
    public void onButtonClicked() {
        replaceFragment(new AnotherRightFragment());
    }
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.right_layout, fragment);
        transaction.addToBackStack(null); // Add the transaction to the back stack
        transaction.commit();
    }


}