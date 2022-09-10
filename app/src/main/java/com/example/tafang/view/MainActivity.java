package com.example.tafang.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tafang.R;
import com.example.tafang.mySurfaceView.Mysurfaceview;
import com.example.tafang.model.CreateFD;
import com.example.tafang.model.Data;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
    public Mysurfaceview mysurfaceview;
    public Thread downThread;
    private SurfaceView surfaceView = null;
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 60) {
                Toast.makeText(MainActivity.this, "时间到，已帮您自动选择.", Toast.LENGTH_SHORT).show();
            }
            if (msg.what == 50) {
                tipsUpdate();
            }
            if (msg.what == 10) {
                shuxing();
                Toast.makeText(MainActivity.this, "查看属性面板.", Toast.LENGTH_SHORT).show();
            }
        }
    };
    int select = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        setContentView(R.layout.activity_main2);

        // 获得surfaceView
        this.surfaceView = (SurfaceView) findViewById(R.id.surface_view);
        CreateFD.chushihua();
        // 获得屏幕的高度
        DisplayMetrics metric = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(metric);
        int height = metric.heightPixels;

        // 重置SurfaceView的高度
        ViewGroup.LayoutParams params = this.surfaceView.getLayoutParams();
        params.height = 2 * height;
        params.width = 4 * height;
        mysurfaceview = new Mysurfaceview(this);
        setContentView(mysurfaceview);
        tipsUpdate();
        //游戏状态监测
        jiance();
    }

    //todo 开始游戏
    public void jiance() {
        if (downThread == null) {
            downThread = new Thread() {
                @Override
                public void run() {
                    super.run();
                    while (true) {
                        try {
                            //休眠500ms
                            sleep(900);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (CreateFD.yibojieshu == 1) {
                            handler.sendEmptyMessage(50);
                        }
                        if (CreateFD.shuxingflag == 1) {
                            handler.sendEmptyMessage(10);
                            CreateFD.shuxingflag = 0;
                        }
                    }
                }
            };
            downThread.start();
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void tipsUpdate() {
        CreateFD.zidongxuanze = 0;
        LayoutInflater inflater = LayoutInflater.from(getApplication());
        View view = inflater.inflate(R.layout.activity_jieshu, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.TransparentDialog);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);  //此处可以设置dialog显示的位置
        //window.setWindowAnimations(R.style.SelectUpload2);  //添加动画
        //随机buuf
        suijiBuff();
        final TextView t1 = (TextView) view.findViewById(R.id.t1);
        final TextView t2 = (TextView) view.findViewById(R.id.t2);
        final TextView t3 = (TextView) view.findViewById(R.id.t3);
        t2.setTextColor(Color.WHITE);
        t3.setTextColor(Color.WHITE);
        t1.setTextColor(Color.WHITE);
        t1.setText(CreateFD.buffList2.get(0).getInfo());
        t2.setText(CreateFD.buffList2.get(1).getInfo());
        t3.setText(CreateFD.buffList2.get(2).getInfo());
        final LinearLayout buff1 = (LinearLayout) view.findViewById(R.id.buff1);
        final LinearLayout buff2 = (LinearLayout) view.findViewById(R.id.buff2);
        final LinearLayout buff3 = (LinearLayout) view.findViewById(R.id.buff3);

        final ImageView ima1 = (ImageView) view.findViewById(R.id.ima1);
        final ImageView ima2 = (ImageView) view.findViewById(R.id.ima2);
        final ImageView ima3 = (ImageView) view.findViewById(R.id.ima3);
        ima1.setImageResource(CreateFD.buffList2.get(0).getPic());
        ima2.setImageResource(CreateFD.buffList2.get(1).getPic());
        ima3.setImageResource(CreateFD.buffList2.get(2).getPic());
        final Button buffok = (Button) view.findViewById(R.id.buffok);
        final Button shuaxin = (Button) view.findViewById(R.id.shuaxin);
        buff1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t2.setTextColor(Color.WHITE);
                t3.setTextColor(Color.WHITE);
                t1.setTextColor(Color.RED);
                select = 0;
            }
        });
        buff2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t1.setTextColor(Color.WHITE);
                t3.setTextColor(Color.WHITE);
                t2.setTextColor(Color.RED);
                select = 1;
            }
        });
        buff3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t1.setTextColor(Color.WHITE);
                t2.setTextColor(Color.WHITE);
                t3.setTextColor(Color.RED);
                select = 2;
            }
        });
        buffok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (select == -1) {
                    Toast.makeText(MainActivity.this, "请选择buff", Toast.LENGTH_SHORT).show();
                } else {
                    buffadd();
                    dialog.dismiss();
                    CreateFD.zidongxuanze = 20;
                }

            }
        });
        if (CreateFD.boshu >= 2) {
            shuaxin.setVisibility(View.VISIBLE);
        }
        shuaxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Data.money - 2000 >= 0) {
                    Data.money -= 2000;
                    dialog.dismiss();
                    CreateFD.zidongxuanze = 20;
                    tipsUpdate();
                } else {
                    Toast.makeText(MainActivity.this, "金币不足...", Toast.LENGTH_SHORT).show();
                }

            }
        });
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                if (CreateFD.zidongxuanze == 0) {
                    select = 1;
                    buffadd();
                    dialog.dismiss();
                    handler.sendEmptyMessage(60);
                }

            }

        }, 15000);
        if (CreateFD.gameAll == 1) {
            dialog.show();
        }


        CreateFD.yibojieshu = 0;
    }

    //属性
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void shuxing() {
        LayoutInflater inflater = LayoutInflater.from(getApplication());
        View view = inflater.inflate(R.layout.activity_shuxing, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.TransparentDialog);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);  //此处可以设置dialog显示的位置
        final TextView t1g1 = (TextView) view.findViewById(R.id.t1g1);
        final TextView t1g2 = (TextView) view.findViewById(R.id.t1g2);
        final TextView t1g3 = (TextView) view.findViewById(R.id.t1g3);
        final TextView t2g1 = (TextView) view.findViewById(R.id.t2g1);
        final TextView t2g2 = (TextView) view.findViewById(R.id.t2g2);
        final TextView t2g3 = (TextView) view.findViewById(R.id.t2g3);
        final TextView t3g1 = (TextView) view.findViewById(R.id.t3g1);
        final TextView t3g2 = (TextView) view.findViewById(R.id.t3g2);
        final TextView t3g3 = (TextView) view.findViewById(R.id.t3g3);
        t1g1.setText("一级攻击力:" + CreateFD.ta1gj1);
        t1g2.setText("二级攻击力:" + CreateFD.ta1gj2);
        t1g3.setText("三级攻击力:" + CreateFD.ta1gj3);
        t2g1.setText("一级攻击力:" + CreateFD.ta2gj1);
        t2g2.setText("二级攻击力:" + CreateFD.ta2gj2);
        t2g3.setText("三级攻击力:" + CreateFD.ta2gj3);
        t3g1.setText("一级攻击力:" + CreateFD.ta3gj1);
        t3g2.setText("二级攻击力:" + CreateFD.ta3gj2);
        t3g3.setText("三级攻击力:" + CreateFD.ta3gj3);
        final TextView t1z1 = (TextView) view.findViewById(R.id.t1z1);
        final TextView t2z1 = (TextView) view.findViewById(R.id.t2z1);
        final TextView t3z1 = (TextView) view.findViewById(R.id.t3z1);
        t1z1.setText("造价:" + "250");
        t2z1.setText("造价:" + "500");
        t3z1.setText("造价:" + "1200");
        final Button buffok = (Button) view.findViewById(R.id.buffok);
        buffok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateFD.shuxingflag = 0;
                dialog.dismiss();
            }
        });

        dialog.show();


    }

    private void buffadd() {
        if (CreateFD.buffList2.get(select).getBid() == 1) {
            Data.money += 2000;
        } else if (CreateFD.buffList2.get(select).getBid() == 2) {
            CreateFD.ta2gj1++;
            CreateFD.ta2gj2++;
            CreateFD.ta2gj3++;
        } else if (CreateFD.buffList2.get(select).getBid() == 3) {
            CreateFD.ta3gj1++;
            CreateFD.ta3gj2++;
            CreateFD.ta3gj3++;
        } else if (CreateFD.buffList2.get(select).getBid() == 4) {
            Data.money = (int) (Data.money + (int) Data.money * 0.1);
        } else if (CreateFD.buffList2.get(select).getBid() == 5) {
            Data.money = (int) (Data.money + (int) Data.money * 0.15);
        } else if (CreateFD.buffList2.get(select).getBid() == 6) {
            Data.money = (int) (Data.money + (int) Data.money * 0.20);
        } else if (CreateFD.buffList2.get(select).getBid() == 7) {
            CreateFD.ta1gj1++;
            CreateFD.ta1gj2++;
            CreateFD.ta1gj3++;
        } else if (CreateFD.buffList2.get(select).getBid() == 8) {
            CreateFD.ta1gj1++;
            CreateFD.ta1gj2++;
            CreateFD.ta1gj3++;
            CreateFD.ta2gj1++;
            CreateFD.ta2gj2++;
            CreateFD.ta2gj3++;
            CreateFD.ta3gj1++;
            CreateFD.ta3gj2++;
            CreateFD.ta3gj3++;
        } else if (CreateFD.buffList2.get(select).getBid() == 9) {
            CreateFD.ta1gj1++;
            CreateFD.ta1gj2++;
            CreateFD.ta1gj3++;
            CreateFD.ta2gj1++;
            CreateFD.ta2gj2++;
            CreateFD.ta2gj3++;
            CreateFD.ta3gj1++;
            CreateFD.ta3gj2++;
            CreateFD.ta3gj3++;
            Data.money -= 2000;
            CreateFD.ta1gj1++;
            CreateFD.ta1gj2++;
            CreateFD.ta1gj3++;
            CreateFD.ta2gj1++;
            CreateFD.ta2gj2++;
            CreateFD.ta2gj3++;
            CreateFD.ta3gj1++;
            CreateFD.ta3gj2++;
            CreateFD.ta3gj3++;
        } else if (CreateFD.buffList2.get(select).getBid() == 10) {
            Data.money += 300;
        } else if (CreateFD.buffList2.get(select).getBid() == 11) {
            Data.money += 500;
        } else if (CreateFD.buffList2.get(select).getBid() >= 12 && CreateFD.buffList2.get(select).getBid() <= 31) {
            Data.money += 200;
        } else if (CreateFD.buffList2.get(select).getBid() == 32) {
            CreateFD.ta1gj1++;
            CreateFD.ta1gj2++;
            CreateFD.ta1gj3++;
            CreateFD.ta2gj1++;
            CreateFD.ta2gj2++;
            CreateFD.ta2gj3++;
            CreateFD.ta3gj1++;
            CreateFD.ta3gj2++;
            CreateFD.ta3gj3++;
            Data.money -= 4000;
            CreateFD.ta1gj1++;
            CreateFD.ta1gj2++;
            CreateFD.ta1gj3++;
            CreateFD.ta2gj1++;
            CreateFD.ta2gj2++;
            CreateFD.ta2gj3++;
            CreateFD.ta3gj1++;
            CreateFD.ta3gj2++;
            CreateFD.ta3gj3++;
        }
        select = -1;
    }

    private void suijiBuff() {
        CreateFD.buffList2.clear();
        for (int i = 0; i < 3; i++) {
            Random random = new Random();
            int s = random.nextInt(CreateFD.buffList.size());
            CreateFD.buffList2.add(CreateFD.buffList.get(s));
        }


    }

    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("确定要退出吗?");
        builder.setTitle("提示");
        builder.setPositiveButton("确认",
                new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mysurfaceview.setselectgame();
                        dialog.dismiss();
                    }
                });
        builder.setNegativeButton("取消",
                new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            dialog();
            return false;
        }
        return false;
    }

}
