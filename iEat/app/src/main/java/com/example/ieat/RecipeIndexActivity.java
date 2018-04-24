package com.example.ieat;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;

import Base.BaseActivity;

/**
 * Created by fanmiaomiao on 2018/3/29.
 */

public class RecipeIndexActivity extends BaseActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_info);
        imageView = (ImageView) findViewById(R.id.recipe_info_background);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RecipeIndexActivity.this, RecipeActivity.class);
                startActivity(intent);
            }
        });
    }

}
