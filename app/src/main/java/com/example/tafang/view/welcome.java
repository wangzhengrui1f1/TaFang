package com.example.tafang.view;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tafang.R;
import com.example.tafang.dataModel.Buff;
import com.example.tafang.model.CreateFD;

import java.util.Random;

import static com.example.tafang.model.Data.mids;


public class welcome extends AppCompatActivity {
    LinearLayout bottoms, welcomeroot;
    Button welcome;
    TextView tit1, tit2;
    int xW=50,xH=50;
    int is1=0;
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            viewGame.invalidate();

            Log.e("sssaa",String.valueOf(is1));
        }
    };
    public Thread downThread;
    View viewGame;
    MediaPlayer mediaPlayer;
    int sf=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO 设置状态栏透明
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            int ui = decorView.getSystemUiVisibility();
            ui |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体的颜色为黑色
            decorView.setSystemUiVisibility(ui);
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        }
        setContentView(R.layout.activity_pdf);
        init();
        //初始化 buff
        initBuff();
       // mids();
        startGame();

        initView();
        tit1.setVisibility(View.VISIBLE);
        tit2.setVisibility(View.VISIBLE);
        Animation animation = new AlphaAnimation(0.2f, 1.0f);
        animation.setDuration(1800);
        tit1.setAnimation(animation);
        tit2.setAnimation(animation);
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(2700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Animation animation2 = new AlphaAnimation(1.0f, 0.5f);
                animation2.setDuration(2300);
                tit1.setAnimation(animation2);
                tit2.setAnimation(animation2);

            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //handler.sendEmptyMessage(50);

            }
        }.start();

//声明MediaPlayer

        //将声音资源文件设置给MediaPlayer对象
        mediaPlayer=MediaPlayer.create(welcome.this, R.raw.mids);
        //运行MediaPlayer


        new Thread() {
            @Override
            public void run() {
                super.run();
                mediaPlayer.start();
                //Thread.sleep(5000);
                //handler.sendEmptyMessage(50);

            }
        }.start();

    }

    private void initBuff() {
        Buff b1=new Buff();
        b1.setBid(1);
        b1.setPic(R.drawable.jinbi);
        b1.setInfo("大富翁:金币+2000");
        CreateFD.buffList.add(b1);

        Buff b2=new Buff();
        b2.setBid(2);
        b2.setPic(R.drawable.ta222);
        b2.setInfo("强化攻击:小黄龟攻击永久增加1点");
        CreateFD.buffList.add(b2);

        Buff b3=new Buff();
        b3.setBid(3);
        b3.setPic(R.drawable.ta333);
        b3.setInfo("强化攻击:美人鱼攻击永久增加1点");
        CreateFD.buffList.add(b3);

        Buff b4=new Buff();
        b4.setBid(4);
        b4.setPic(R.drawable.jinbi);
        b4.setInfo("初级理财高手:当前金币增加10%");
        CreateFD.buffList.add(b4);

        Buff b5=new Buff();
        b5.setBid(5);
        b5.setPic(R.drawable.jinbibuff3);
        b5.setInfo("中级理财高手:当前金币增加15%");
        CreateFD.buffList.add(b5);

        Buff b6=new Buff();
        b6.setBid(6);
        b6.setPic(R.drawable.jinbibuff3);
        b6.setInfo("大师级理财高手:当前金币增加20%");
        CreateFD.buffList.add(b6);

        Buff b7=new Buff();
        b7.setBid(7);
        b7.setPic(R.drawable.ta111);
        b7.setInfo("强化攻击:小红龟攻击永久增加1点");
        CreateFD.buffList.add(b7);

        Buff b8=new Buff();
        b8.setBid(8);
        b8.setPic(R.drawable.gongjibuff);
        b8.setInfo("你让别人怎么玩？:全体攻击永久增加1点");
        CreateFD.buffList.add(b8);

        Buff b9=new Buff();
        b9.setBid(9);
        b9.setPic(R.drawable.gongjibuff);
        b9.setInfo("超级加倍？:全体攻击永久增加2点,但是你要付出2000金币的代价,但是可以欠钱哦");
        CreateFD.buffList.add(b9);

        Buff b10=new Buff();
        b10.setBid(10);
        b10.setPic(R.drawable.jinbi);
        b10.setInfo("小有成就:金币+300");
        CreateFD.buffList.add(b10);

        Buff b11=new Buff();
        b11.setBid(11);
        b11.setPic(R.drawable.jinbi);
        b11.setInfo("我觉得还行:金币+500");
        CreateFD.buffList.add(b11);

        //12-31
        Buff b12=new Buff();
        for(int is=0;is<20;is++){
            b12.setBid(12+is);
            b12.setPic(R.drawable.jinbi);
            b12.setInfo("挣钱哪有那么容易:金币+200");
            CreateFD.buffList.add(b12);
        }
        Buff b32=new Buff();
        b32.setBid(32);
        b32.setPic(R.drawable.gongjibuff);
        b32.setInfo("付出点小代价：全体攻击永久增加2点,但是你要付出4000金币的代价,但是可以欠钱哦");
        CreateFD.buffList.add(b32);


        Log.e("zhengchang","2");
        CreateFD.buffList.add(b1);
    }

    //todo 开始游戏
    public void startGame() {
        if (downThread == null) {
            downThread = new Thread() {
                @Override
                public void run() {
                    super.run();
                    while (true) {
                        try {
                            //休眠500ms
                            sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Random random=new Random();
                        int s=random.nextInt(200)+80;
                        is1+=s;
                        handler.sendEmptyMessage(50);
                    }
                }
            };
            downThread.start();
        }
    }

    private void init() {
        welcomeroot= (LinearLayout) findViewById(R.id.welcomeroot);
        tit1= (TextView) findViewById(R.id.tit1);
        tit2= (TextView) findViewById(R.id.tit2);
        welcomeroot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sf==1){
                    Intent intent=new Intent(welcome.this,SelectHome.class);
                    startActivity(intent);
                    finish();
                }

               // finish();
            }
        });
    }

    //todo Activity创建或者从被覆盖、后台重新回到前台时被调用
    @Override
    protected void onResume() {
        super.onResume();


    }
    private void initView() {
        LinearLayout layoutGame=(LinearLayout) findViewById(R.id.welcomeroot);
    //实例化游戏区域
        viewGame = new View(this){
        //方块绘制：重写游戏区域绘制
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            int screenWidth = getWindowManager().getDefaultDisplay().getWidth(); // 屏幕宽（像素，如：480px）
            int screenHeight = getWindowManager().getDefaultDisplay().getHeight(); // 屏幕高（像素，如：800p）
            Paint jdtPaint=new Paint();
            jdtPaint.setColor(Color.WHITE);
            jdtPaint.setStrokeWidth(50);
            Paint paint=new Paint();
            paint.setColor(Color.WHITE);
            Paint tPaint=new Paint();
            tPaint.setTextSize(35);
            tPaint.setColor(Color.WHITE);
            //建造进度条
            if(8*xW+(int)(is1)<(screenWidth-screenWidth/7)){
                canvas.drawText("加载中... "+String.valueOf((8*xW+(int)(is1)))+"",8*xW,1*xH,tPaint);
                canvas.drawRect(screenWidth/7,1*xH+xH/2-xH/5,8*xW+(int)(is1),1*xH*8/9+xH/2-xH/5,jdtPaint);
            }else {
                sf=1;
                canvas.drawText("任意点击进入游戏...  100%",8*xW,1*xH,tPaint);
                canvas.drawRect(screenWidth/7,1*xH+xH/2-xH/5,(screenWidth-screenWidth/7),1*xH*8/9+xH/2-xH/5,jdtPaint);
            }
        }
    };
        int screenWidth = getWindowManager().getDefaultDisplay().getWidth(); // 屏幕宽（像素，如：480px）
        int screenHeight = getWindowManager().getDefaultDisplay().getHeight(); // 屏幕高（像素，如：800p）
        //设置游戏区域大小
        viewGame.setLayoutParams(new FrameLayout.LayoutParams(screenWidth,screenHeight/8));
    //设置背景颜色
        viewGame.setBackgroundColor(0x10000000);
    // viewGame.setBackgroundColor(getResources().getColor(R.color.colorAccent));
    //添加到父容器
        layoutGame.addView(viewGame);
}

}