package com.example.tafang.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tafang.R;
import com.example.tafang.model.Data;

public class SelectHome extends Activity {
    TextView lantu,jiangli,shop,paihang,shangdian,shezhi,zhengtu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        setContentView(R.layout.select_activity);


        init();
        Onclick();
    }

    private void Onclick() {
        lantu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SelectHome.this, "暂未开放", Toast.LENGTH_SHORT).show();
            }
        });
        jiangli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SelectHome.this, "暂未开放", Toast.LENGTH_SHORT).show();
            }
        });
        paihang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SelectHome.this, "暂未开放", Toast.LENGTH_SHORT).show();
            }
        });
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SelectHome.this, "暂未开放", Toast.LENGTH_SHORT).show();
            }
        });
        shezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {Toast.makeText(SelectHome.this, "暂未开放", Toast.LENGTH_SHORT).show();
            }
        });
        zhengtu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joinGuanqia(3);
            }
        });
    }

    private void init() {
        lantu=(TextView)findViewById(R.id.lantu);
        jiangli=(TextView)findViewById(R.id.jiangli);
        shop=(TextView)findViewById(R.id.shop);
        paihang=(TextView)findViewById(R.id.paihang);
        shezhi=(TextView)findViewById(R.id.shezhi);
        zhengtu=(TextView)findViewById(R.id.zhengtu);
    }

    private void joinGuanqia(int guanqia) {
        Data.setGuanqia(3);
        Intent intent=new Intent(SelectHome.this,MainActivity.class);
        startActivity(intent);
    }
}
