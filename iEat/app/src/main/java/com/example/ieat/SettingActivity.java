package com.example.ieat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import Base.BaseActivity;

/**
 * Created by fanmiaomiao on 2018/3/29.
 */

public class SettingActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ImageView imageView = (ImageView) findViewById(R.id.setting_bg_img);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this,RecipeIndexActivity.class);
                startActivity(intent);
            }
        });
    }
}
