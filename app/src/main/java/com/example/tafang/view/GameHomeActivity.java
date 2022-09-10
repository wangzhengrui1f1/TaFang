package com.example.tafang.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.tafang.R;
import com.example.tafang.other.Data;


public class GameHomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        setContentView(R.layout.activity_main);
        joinGuanqia(3);
        TextView textView= (TextView) findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joinGuanqia(1);
            }
        });
        TextView textView2= (TextView) findViewById(R.id.textView2);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joinGuanqia(2);
            }
        });
        TextView textView3= (TextView) findViewById(R.id.textView3);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joinGuanqia(3);
            }
        });
    }



    private void joinGuanqia(int guanqia) {
        Data.setGuanqia(1);
        Intent intent=new Intent(GameHomeActivity.this,MainActivity.class);
        startActivity(intent);
    }


}
