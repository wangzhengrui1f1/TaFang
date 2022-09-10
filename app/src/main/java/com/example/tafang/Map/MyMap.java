package com.example.tafang.Map;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.Log;

import com.example.tafang.Model.CreateFD;
import com.example.tafang.Model.Data;
import com.example.tafang.Model.Gwcreat;
import com.example.tafang.Model.Hero;
import com.example.tafang.MySurfaceView.Mysurfaceview;
import com.example.tafang.R;


public class MyMap {
    private Gwcreat guaiwucreat;//创造怪物的类
    private Hero hero;
    private int[][] map;
    private boolean pause = true;//游戏暂停吗？
    private Mysurfaceview mysurfaceview;//控制上一个类的方法
    private Context context;
    //region 游戏数据自己设定
    private final int JL1 = 250;//新建塔的花费为：JL代表建立
    private final int SJ1_11 = 200;//升级塔的花费为：
    private final int SJ11_111 = 300;//升级塔的花费为：
    private final int M1_0 = 200;//卖塔挣钱
    private final int M11_0 = 400;//卖塔挣钱
    private final int M111_0 = 600;//卖塔挣钱

    private final int JL2 = 500;//新建塔的花费为：
    private final int SJ2_22 = 350;//升级塔的花费为：
    private final int SJ22_222 = 450;//升级塔的花费为：
    private final int M2_0 = 350;//卖塔挣钱
    private final int M22_0 = 600;//卖塔挣钱
    private final int M222_0 = 900;//卖塔挣钱

    private final int JL3 = 1200;//新建塔的花费为：
    private final int SJ3_33 = 600;//升级塔的花费为：
    private final int SJ33_333 = 900;//升级塔的花费为：
    private final int M3_0 = 800;//卖塔挣钱
    private final int M33_0 = 1300;//卖塔挣钱
    private final int M333_0 = 1950;//卖塔挣钱

    private int backX = 1;//后退图标和暂停图标的具体位置
    private int backY = 0;
    private int pauseX = 15;
    private int pauseY = 11;
    //飞碟的x和y 状态 0123 sxzy
    int fdx = 3, fdy = 3;
    int fdzt = -1;
    private Bitmap leidian;
    int leidiantime = 0;
    //canvas.drawBitmap(startpic,null,new Rect(xW*15,xH*10+xH/2,xW*17,xH*12+xH/2),paint);
    private int moneytextX = 3;//定义money坐标
    private int moneytextY = 1;
    private int tishitextX = 8;//定义提示信息坐标
    private int tishitextY = 1;
    private final int tishinumzong = 600;//定义提示坚持的时间 ：tishinum * 50 （ms）
    int px1 = 1, py1 = 1;
    //最高级大图存在时长 50=1s  0否 1是
    private int meirenyutime = 0;
    private int mrytime = 0;
    //endregion
    private int tishinum = -1;//当前的次数 用于实时记录
    private int tishimoney;//暂时需要的钱 显示   钱不够了，还需要tishimoney钱
    //飞碟存在标志零时
    private int fdt = 0, ffx, ffy;
    private Canvas canvas;
    //画笔
    private Paint paint;
    //攻击圆画笔
    private Paint gjPaint;
    Resources resources;
    private Bitmap m1, m2, m3, m4, m5, m6, m7, m8, m0, ta1, ta11, ta111, ta2, ta22, ta222, ta3, ta33, ta333, ta1c, ta2c, ta3c, update, delete, pausepic, startpic, back;//暂停开始后退图标
    //buff
    private Bitmap jinbita, jinbibuff, gongjibuff;
    //技能大图
    private Bitmap meirenyuda, paodanmei;
    //建造飞碟画笔
    private Paint createFd;
    private Bitmap feidie;
    //炮弹图1 flag 1 2控制炮弹 3控制生成x
    private Bitmap paos1, baozha1;
    int paox, paoflag1 = 0, paoflag2 = 0, paoflag3;
    //菜单边框
    private Bitmap biankuang1, mubiao, shezhippic, caidanpic, jinbipic, zhadanpic;
    private int ScreenH, ScreenW;//屏幕高和宽
    private int num;//num为第几幅地图
    private final int W = 32;//定义宽分为16份
    private final int H = 13;//定义高分为10份
    private int xW, xH;//小图的宽和高
    private int onX, onY;//当前指针的的坐标，刚刚按过的位置
    private boolean showtamenu = false, showupdate = false;//是否绘制塔选项？升级选项？
    //炮弹进度条
    private Bitmap jdt;
    private Bitmap jdt2;
    private Bitmap jsgj[] = new Bitmap[10];
    private int jszhen = 0;
    //    private int[] gj=new int[]{R.drawable.js1gj10,R.drawable.js1gj10,R.drawable.js1gj11,R.drawable.js1gj11,
//            R.drawable.js1gj12,R.drawable.js1gj12,R.drawable.js1gj13,R.drawable.js1gj13,R.drawable.js1gj14,R.drawable.js1gj14};
    private int[] gj = new int[]{R.drawable.a10, R.drawable.a10, R.drawable.a11, R.drawable.a11,
            R.drawable.a12, R.drawable.a12, R.drawable.a13, R.drawable.a13, R.drawable.a14, R.drawable.a14};
    //声音

    public MyMap(Canvas canvas, Paint paint, Mysurfaceview mysurfaceview, Resources resources, int num, int[][] map)//num为第几幅地图
    {
        this.canvas = canvas;
        this.paint = paint;
        this.num = num;
        this.ScreenH = canvas.getHeight();
        this.ScreenW = canvas.getWidth();
        this.resources = resources;
        this.mysurfaceview = mysurfaceview;
        this.map = map;
        initializie();
        if (num == 1) {
            guaiwucreat = new Gwcreat(mysurfaceview, this.context, map, num, resources, canvas, paint,
                    5, 4, 0, xW, xH);
            guaiwucreat.setmap(map);///////删除试试
            hero = new Hero(map, resources, canvas, paint, xW, xH, xW * 2, xH * 4);
        } else if (num == 2) {
            guaiwucreat = new Gwcreat(mysurfaceview, this.context, map, num, resources, canvas, paint,
                    10, 9, 0, xW, xH);
            guaiwucreat.setmap(map);///
            hero = new Hero(map, resources, canvas, paint, xW, xH, xW, xH * 5);
        } else {
            guaiwucreat = new Gwcreat(mysurfaceview, this.context, map, num, resources, canvas, paint, 2000,
                    10, 0, xW, xH);
            guaiwucreat.setmap(map);///
            //(int[][] map, Resources resources, Canvas canvas, Paint paint,int xW, int xH)
            hero = new Hero(map, resources, canvas, paint, xW, xH, xW * 10, xH * 5);
        }

    }

    private void initializie()//初始化
    {
        for (int s = 0; s < gj.length; s++) {
            jsgj[s] = BitmapFactory.decodeResource(resources, gj[s]);
        }
        xW = ScreenW / W;
        xH = ScreenH / H;
        m0 = BitmapFactory.decodeResource(resources, R.drawable.ms0);
        m1 = BitmapFactory.decodeResource(resources, R.drawable.ms1);
        m2 = BitmapFactory.decodeResource(resources, R.drawable.ms2);
        m3 = BitmapFactory.decodeResource(resources, R.drawable.ms3);
        m4 = BitmapFactory.decodeResource(resources, R.drawable.ms4);
        m5 = BitmapFactory.decodeResource(resources, R.drawable.ms5);
        m6 = BitmapFactory.decodeResource(resources, R.drawable.ms6);
        m7 = BitmapFactory.decodeResource(resources, R.drawable.ms7);
        m8 = BitmapFactory.decodeResource(resources, R.drawable.ms8);
        ta1 = BitmapFactory.decodeResource(resources, R.drawable.ta1);
        ta11 = BitmapFactory.decodeResource(resources, R.drawable.ta11);
        ta111 = BitmapFactory.decodeResource(resources, R.drawable.ta111);
        ta2 = BitmapFactory.decodeResource(resources, R.drawable.ta2);
        ta22 = BitmapFactory.decodeResource(resources, R.drawable.ta22);
        ta222 = BitmapFactory.decodeResource(resources, R.drawable.ta222);
        ta3 = BitmapFactory.decodeResource(resources, R.drawable.ta3);
        ta33 = BitmapFactory.decodeResource(resources, R.drawable.ta33);
        ta333 = BitmapFactory.decodeResource(resources, R.drawable.ta333);
        update = BitmapFactory.decodeResource(resources, R.drawable.update);
        delete = BitmapFactory.decodeResource(resources, R.drawable.delete);
        ta1c = BitmapFactory.decodeResource(resources, R.drawable.ta1c);
        ta2c = BitmapFactory.decodeResource(resources, R.drawable.ta2c);
        ta3c = BitmapFactory.decodeResource(resources, R.drawable.ta3c);
        pausepic = BitmapFactory.decodeResource(resources, R.drawable.kaishis);
        startpic = BitmapFactory.decodeResource(resources, R.drawable.zantings);
        // back=BitmapFactory.decodeResource(resources, R.drawable.back);
        biankuang1 = BitmapFactory.decodeResource(resources, R.drawable.biank1);
        mubiao = BitmapFactory.decodeResource(resources, R.drawable.mubiao);
        shezhippic = BitmapFactory.decodeResource(resources, R.drawable.shezhis);
        caidanpic = BitmapFactory.decodeResource(resources, R.drawable.caidans);
        jinbipic = BitmapFactory.decodeResource(resources, R.drawable.jinbi);
        zhadanpic = BitmapFactory.decodeResource(resources, R.drawable.zhadan);
        jinbita = BitmapFactory.decodeResource(resources, R.drawable.jsa1);
        jinbibuff = BitmapFactory.decodeResource(resources, R.drawable.jinbibuff3);
        gongjibuff = BitmapFactory.decodeResource(resources, R.drawable.gongjibuff);
        meirenyuda = BitmapFactory.decodeResource(resources, R.drawable.meirenyuda);
        feidie = BitmapFactory.decodeResource(resources, R.drawable.feidie);
        leidian = BitmapFactory.decodeResource(resources, R.drawable.leidian1);
        paodanmei = BitmapFactory.decodeResource(resources, R.drawable.pao1);
        paos1 = BitmapFactory.decodeResource(resources, R.drawable.paos1);
        baozha1 = BitmapFactory.decodeResource(resources, R.drawable.baozha1);
        jdt = BitmapFactory.decodeResource(resources, R.drawable.special1);
        jdt2 = BitmapFactory.decodeResource(resources, R.drawable.special2);

        // guaiwucreat=new Gwcreat(resources,canvas,paint,10,4,0,xW,xH);//guaiwucreat.setmap(map1);///
    }

    public void drawmap() {

        //region 绘制地图
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
//                    if(map3[i][j]<0){
//                        canvas.drawBitmap(m0,null,new Rect(j*xW,i*xH,j*xW+xW,i*xH+xH),paint);
//                    }
//                    if(map3[i][j]==0) {canvas.drawBitmap(m0,null,new Rect(j*xW,i*xH,j*xW+xW,i*xH+xH),paint);}else
//                    if(map3[i][j]==1) {canvas.drawBitmap(m1,null,new Rect(j*xW,i*xH,j*xW+xW,i*xH+xH),paint);}else
//                    if(map3[i][j]==2) {canvas.drawBitmap(m2,null,new Rect(j*xW,i*xH,j*xW+xW,i*xH+xH),paint);}else
//                    if(map3[i][j]==3) {canvas.drawBitmap(m3,null,new Rect(j*xW,i*xH,j*xW+xW,i*xH+xH),paint);}else
//                    if(map3[i][j]==4) {canvas.drawBitmap(m4,null,new Rect(j*xW,i*xH,j*xW+xW,i*xH+xH),paint);}else
//                    if(map3[i][j]==5) {canvas.drawBitmap(m5,null,new Rect(j*xW,i*xH,j*xW+xW,i*xH+xH),paint);}else
//                    if(map3[i][j]==6) {canvas.drawBitmap(m6,null,new Rect(j*xW,i*xH,j*xW+xW,i*xH+xH),paint);}else
//                    if(map3[i][j]==7) {canvas.drawBitmap(m7,null,new Rect(j*xW,i*xH,j*xW+xW,i*xH+xH),paint);}else
//                    if(map3[i][j]==8) {canvas.drawBitmap(m8,null,new Rect(j*xW,i*xH,j*xW+xW,i*xH+xH),paint);}else
                if (map[i][j] == -1) {
//                        if(jszhen>=gj.length){
//                            jszhen=0;
//                            ptgj=1;
//                        }
//                        canvas.drawBitmap(jsgj[jszhen],null,
//                                new Rect(j * xW-xW/2, i * xH-xH/2, j * xW + xW+xW/2, i * xH + xH+xH/2),paint);
//                        jszhen++;
                    canvas.drawBitmap(ta1, null, new Rect(j * xW, i * xH, j * xW + xW, i * xH + xH), paint);
                } else if (map[i][j] == -2) {
                    canvas.drawBitmap(ta2, null, new Rect(j * xW, i * xH, j * xW + xW, i * xH + xH), paint);
                } else if (map[i][j] == -3) {
                    canvas.drawBitmap(ta3, null, new Rect(j * xW, i * xH, j * xW + xW, i * xH + xH), paint);
                } else if (map[i][j] == -11) {
                    canvas.drawBitmap(ta11, null, new Rect(j * xW, i * xH, j * xW + xW, i * xH + xH), paint);
                } else if (map[i][j] == -22) {
                    canvas.drawBitmap(ta22, null, new Rect(j * xW, i * xH, j * xW + xW, i * xH + xH), paint);
                } else if (map[i][j] == -33) {
                    canvas.drawBitmap(ta33, null, new Rect(j * xW, i * xH, j * xW + xW, i * xH + xH), paint);
                } else if (map[i][j] == -111) {
                    canvas.drawBitmap(ta111, null, new Rect(j * xW, i * xH, j * xW + xW, i * xH + xH), paint);
                } else if (map[i][j] == -222) {
                    canvas.drawBitmap(ta222, null, new Rect(j * xW, i * xH, j * xW + xW, i * xH + xH), paint);
                } else if (map[i][j] == -333) {
                    canvas.drawBitmap(ta333, null, new Rect(j * xW, i * xH, j * xW + xW, i * xH + xH), paint);
                }
            }
        }


        if (showtamenu)//如果有塔菜单
        {
            // canvas.drawLine((onX)*xW,(onX+1)*xW,(onX+1)*2*xW,(onX)*xW,paint);
            if (onX < W - 6) {
                //造塔的位置
                canvas.drawBitmap(mubiao, null, new Rect((onX) * xW, onY * xH, (onX) * xW + xW,
                        onY * xH + xH), paint);
                //todo 造塔的菜单边缘
                canvas.drawBitmap(biankuang1, null, new Rect((onX + 2) * xW - xW / 2,
                        (onY - 1) * xH - xH / 2, (onX + 6) * xW - xW / 2, (onY + 1) * xH + xH), paint);
                paint.setColor(Color.WHITE);
                canvas.drawCircle(onX * xW + xW / 2, onY * xH + xH / 2, xW * 4, paint);
                //选择
                canvas.drawBitmap(ta1c, null, new Rect((onX + 2) * xW, onY * xH, (onX + 2) * xW + xW, onY * xH + xH), paint);
                canvas.drawBitmap(ta2c, null, new Rect((onX + 3) * xW, onY * xH, (onX + 3) * xW + xW, onY * xH + xH), paint);
                canvas.drawBitmap(ta3c, null, new Rect((onX + 4) * xW, onY * xH, (onX + 4) * xW + xW, onY * xH + xH), paint);
//                canvas.drawBitmap(jinbita,null,new Rect((onX+2)*xW,(onY+1)*xH,(onX+2)*xW+xW,(onY+1)*xH+xH),paint);
            } else {
                //造塔的位置
                canvas.drawBitmap(mubiao, null, new Rect((onX) * xW, onY * xH, (onX) * xW + xW,
                        onY * xH + xH), paint);
                //todo 造塔的菜单边缘
                canvas.drawBitmap(biankuang1, null, new Rect((onX - 4) * xW - xW / 2,
                        (onY - 1) * xH - xH / 2, (onX) * xW - xW / 2, (onY + 1) * xH + xH), paint);
                paint.setColor(Color.WHITE);
                canvas.drawCircle(onX * xW + xW / 2, onY * xH + xH / 2, xW * 4, paint);
                //选择
                canvas.drawBitmap(ta1c, null, new Rect((onX - 4) * xW, onY * xH, (onX - 4) * xW + xW, onY * xH + xH), paint);
                canvas.drawBitmap(ta2c, null, new Rect((onX - 3) * xW, onY * xH, (onX - 3) * xW + xW, onY * xH + xH), paint);
                canvas.drawBitmap(ta3c, null, new Rect((onX - 2) * xW, onY * xH, (onX - 2) * xW + xW, onY * xH + xH), paint);
            }

            //根据onX和onY
            // drawtamenew必须
        }
        if (showupdate)//有升级菜单
        {
            canvas.drawBitmap(update, null, new Rect((onX) * xW, (onY - 1) * xH, (onX) * xW + xW, (onY - 1) * xH + xH), paint);
            canvas.drawBitmap(delete, null, new Rect((onX) * xW, (onY + 1) * xH, (onX) * xW + xW, (onY + 1) * xH + xH), paint);
            //paint.setColor(Color.WHITE);
            paint.setColor(resources.getColor(R.color.ffaacc));
            //攻击圆
            canvas.drawCircle(onX * xW + xW / 2, onY * xH + xH / 2, xW * 4, paint);
            paint.setColor(Color.WHITE);
        }


        //下面绘制暂停开始和返回菜单

        //canvas.drawBitmap(back,null,new Rect(backX*xW,backY*xH,backX*xW+xW,backY*xH+xH),paint);

        if (pause) {
            //开始暂停图
            // canvas.drawText("暂停",(pauseX)*xW+100,(pauseY)*xH,paint);
            canvas.drawBitmap(pausepic, null, new Rect(xW * 15, xH * 10 + xH / 2, xW * 17, xH * 12 + xH / 2), paint);
            //canvas.drawBitmap(pausepic,null,new Rect((pauseX)*xW,(pauseY)*xH,(pauseX+3)*xW,pauseY*xH+xH),paint);
            guaiwucreat.Pause();
        } else {

            canvas.drawBitmap(startpic, null, new Rect(xW * 15, xH * 10 + xH / 2, xW * 17, xH * 12 + xH / 2), paint);
            //canvas.drawBitmap(startpic,null,new Rect((pauseX)*xW,(pauseY)*xH,(pauseX+2)*xW,(pauseY+2)*xH),paint);
            guaiwucreat.Resume();
            hero.drawgw(canvas, paint, hero.getNow_hx(), hero.getNow_hy());
        }

        Paint linePaint = new Paint();
        linePaint.setColor(Color.WHITE);
        linePaint.setStrokeWidth(1 + xW / 15);
//        //属性菜单使用完毕 停止暂停
//        if(shuxingflag==0){
//            isPause();
//        }
        //下方菜单
        canvas.drawLine(0, xH * 12, xW * 12, xH * 12, linePaint);
        canvas.drawLine(xW * 20, xH * 12, xW * 32, xH * 12, linePaint);
        canvas.drawBitmap(caidanpic, null, new Rect(xW * 13 - xW / 3, xH * 11 - xH / 3, xW * 14 + xW / 3, xH * 12 + xH / 3), paint);
        canvas.drawBitmap(shezhippic, null, new Rect(xW * 18 - xW / 3, xH * 11 - xH / 3, xW * 19 + xW / 3, xH * 12 + xH / 3), paint);
        //角色移动
//        canvas.drawBitmap(caidanpic,null,new Rect(xW*5-xW/3,xH*8-xH/3,
//                xW*6+xW/3,xH*9+xH/3),paint);
//        canvas.drawBitmap(caidanpic,null,new Rect(xW*5-xW/3,xH*10-xH/3,
//                xW*6+xW/3,xH*11+xH/3),paint);
//        canvas.drawBitmap(caidanpic,null,new Rect(xW*3-xW/3,xH*9-xH/3,
//                xW*4+xW/3,xH*10+xH/3),paint);
//        canvas.drawBitmap(caidanpic,null,new Rect(xW*7-xW/3,xH*9-xH/3,
//                xW*8+xW/3,xH*10+xH/3),paint);
//        canvas.drawBitmap(caidanpic,null,new Rect(xW*28-xW/3,xH*8-xH/3,
//                xW*30+xW/3,xH*10+xH/3),paint);

        linePaint.setTextSize(50);
        Paint buffPaint = new Paint();
        Paint zhadanPaint = new Paint();
        zhadanPaint.setAlpha(CreateFD.quanpignpaodan);
        Paint jdtPaint = new Paint();
        jdtPaint.setColor(Color.WHITE);
        jdtPaint.setStrokeWidth(xH);
        paint.setColor(Color.WHITE);
        Paint tPaint = new Paint();
        tPaint.setTextSize(35);
        tPaint.setColor(Color.WHITE);
        //建造进度条
        if (CreateFD.isjz == 0) {
            CreateFD.jzjdt = 0;
            canvas.drawText("可建造...", 8 * xW, 1 * xH, tPaint);
            canvas.drawRect(8 * xW, 1 * xH - xH / 5 + xH / 2, 8 * xW + (int) (210), 1 * xH * 8 / 9 + xH / 2 - xH / 5, paint);
        } else {
            CreateFD.jzjdt += 1;
            canvas.drawText("建造冷却中...", 8 * xW, 1 * xH, tPaint);
            canvas.drawRect(8 * xW, 1 * xH + xH / 2 - xH / 5, 8 * xW + (int) (CreateFD.jzjdt), 1 * xH * 8 / 9 + xH / 2 - xH / 5, jdtPaint);
            canvas.drawRect(8 * xW, 1 * xH + xH / 2 - xH / 5, 8 * xW + (int) (210), 1 * xH * 8 / 9 + xH / 2 - xH / 5, paint);
        }
        //全屏炮弹
        //开始蓄能 qflag 0 0
        if (CreateFD.qflag == 0) {
            CreateFD.quanpignpaodan++;
            canvas.drawRect(12 * xW, 1 * xH - xH / 5 + xH / 2, 12 * xW + (int) (210), 1 * xH * 8 / 9 + xH / 2 - xH / 5, paint);
            canvas.drawText("拉普达蓄能中...", 12 * xW, 1 * xH, tPaint);
            canvas.drawRect(12 * xW, 1 * xH + xH / 2 - xH / 5, 12 * xW + (int) (CreateFD.quanpignpaodan / 2), 1 * xH * 8 / 9 + xH / 2 - xH / 5, jdtPaint);
            if (CreateFD.quanpignpaodan >= CreateFD.lapudacd) {
                CreateFD.qflag = 1;
            }
        }

        if (CreateFD.qflag == 1) {
            CreateFD.quanpignpaodan -= 2;
            if (CreateFD.quanpignpaodan <= 0) {
                CreateFD.quanpignpaodan = 0;
                CreateFD.qflag = 0;
            }
            canvas.drawRect(12 * xW, 1 * xH - xH / 5 + xH / 2, 12 * xW + (int) (210), 1 * xH * 8 / 9 + xH / 2 - xH / 5, paint);
            canvas.drawText("拉普达攻击...", 12 * xW, 1 * xH, tPaint);
            canvas.drawRect(12 * xW, 1 * xH + xH / 2 - xH / 5, 12 * xW + (int) (CreateFD.quanpignpaodan / 2), 1 * xH * 8 / 9 + xH / 2 - xH / 5, jdtPaint);
        }
        //英雄 动作代码 保留
        if (CreateFD.jsfx == 1) {
            if (CreateFD.jsxingzoutime > 0) {
                hero.dratop(canvas, paint, hero.getNow_hx(), hero.getNow_hy() - 5);
                hero.setNow_hy(hero.getNow_hy() - 5);
                CreateFD.jsxingzoutime--;
            } else {
                CreateFD.jsfx = 0;
            }
        } else if (CreateFD.jsfx == 2) {
            if (CreateFD.jsxingzoutime > 0) {
                hero.drabottom(canvas, paint, hero.getNow_hx(), hero.getNow_hy() + 5);
                hero.setNow_hy(hero.getNow_hy() + 5);
                CreateFD.jsxingzoutime--;
            } else {
                CreateFD.jsfx = 0;
            }
        } else if (CreateFD.jsfx == 3) {
            if (CreateFD.jsxingzoutime > 0) {
                hero.draleft(canvas, paint, hero.getNow_hx() - 5, hero.getNow_hy());
                hero.setNow_hx(hero.getNow_hx() - 5);
                CreateFD.jsxingzoutime--;
            } else {
                CreateFD.jsfx = 0;
            }
        } else if (CreateFD.jsfx == 4) {
            if (CreateFD.jsxingzoutime > 0) {
                hero.draright(canvas, paint, hero.getNow_hx() + 5, hero.getNow_hy());
                hero.setNow_hx(hero.getNow_hx() + 5);
                CreateFD.jsxingzoutime--;
            } else {
                CreateFD.jsfx = 0;
            }
        } else if (CreateFD.jsfx == 5) {
            if (CreateFD.jsxingzoutime > 0) {
                hero.dragongji(canvas, paint, hero.getNow_hx(), hero.getNow_hy());
                CreateFD.jsxingzoutime--;
            } else {
                CreateFD.jsfx = 0;
            }

        } else {
            //  hero.drayuandi(canvas,paint,hero.getNow_hx(),hero.getNow_hy());
        }


        if (Data.getJinbitanum() == 0) {
            buffPaint.setAlpha(100);
            canvas.drawBitmap(jinbibuff, null, new Rect(xW * 2 - xW / 3,
                    xH * 11 - xH / 3 + xH / 5, xW * 3, xH * 12), buffPaint);
            canvas.drawBitmap(gongjibuff, null, new Rect(xW * 3 - xW / 3 + xW / 5,
                    xH * 11 - xH / 3 + xH / 5, xW * 4 + xW / 5, xH * 12), buffPaint);
        } else {
            buffPaint.setAlpha(250);
            canvas.drawBitmap(jinbibuff, null, new Rect(xW * 2 - xW / 3,
                    xH * 11 - xH / 3 + xH / 5, xW * 3, xH * 12), buffPaint);
            canvas.drawBitmap(gongjibuff, null, new Rect(xW * 3 - xW / 3 + xW / 5,
                    xH * 11 - xH / 3 + xH / 5, xW * 4 + xW / 5, xH * 12), buffPaint);
        }
        //绘制 美人鱼大图
        if (mrytime == 1) {
            meirenyutime++;
            if (meirenyutime != 0) {
                canvas.drawBitmap(meirenyuda, null, new Rect(xW * 2,
                        xH * 4, xW * 10, xH * 12), buffPaint);
            }
            if (meirenyutime >= 60) {
                mrytime = 0;
                meirenyutime = 0;
            }
        }

        //建造飞碟
        if (CreateFD.isjz > 0) {
            creteFdWord(30 * xW, 1 * xH, (CreateFD.jzfdx) * xW, (CreateFD.jzfdy - 1) * xH);
        }
        if (CreateFD.ptgj == 1) {
            CreateFD.ptgj = 0;


        }

        CreateFD.ptgj = 0;

        Log.e("ssas", String.valueOf(meirenyutime));


        //粗体
        Typeface font = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
        linePaint.setTypeface(font);
        //绘制金钱
        linePaint.setTextSize(40);
        linePaint.setColor(resources.getColor(R.color.jinhuangse));
        canvas.drawBitmap(jinbipic, null, new Rect(xW * 2, xH * 1 + xH / 5, xW * 3, xH * 2 + xH / 5), paint);
        canvas.drawText("" + Data.money, xW * 3 + xW / 5, xH * 2 - xH / 6, linePaint);


        if (tishinum >= 0) {
            linePaint.setColor(Color.RED);
            canvas.drawText("你的钱不够用了，需要 ¥" + tishimoney, xW * 22 + xW / 3, xH * 12 - xH / 2, linePaint);//绘制金钱
            tishinum -= 1;
        }

        guaiwucreat.start();

        Log.e("kxid", String.valueOf(CreateFD.yxkxid));
    }


    public void pushdown(int x, int y)//按下的动作
    {
        // canvas.drawColor(Color.WHITE);
    }

    public void move(int x, int y)//移动的动作
    {

    }

    public void pushup(int x, int y)//离开屏幕的动作
    {
        //如果点击的是屏幕第二行或以下
        if (fangY(y) >= 1) {
            //此处课拓展 if (nul==1)
            //点击属性菜单
            //ShuXing(fangX(x),fangY(y));
            Paint tPaint = new Paint();
            tPaint.setTextSize(50);
            tPaint.setColor(Color.WHITE);
            //点击暂停或返回
            if (((fangX(x) == pauseX && fangY(y) == pauseY) || (fangX(x) == (pauseX + 1)) && fangY(y) == pauseY) ||
                    (fangX(x) == (pauseX)) && fangY(y) == (pauseY + 1)) {
                Log.e("点击屏幕暂停", "x+" + String.valueOf(x));
                Log.e("点击屏幕", "fangX(x)+" + String.valueOf(fangX(x)) + "--" + String.valueOf(fangY(y)));
                //暂停 开始处理
                isPause();
                //todo 点击属性面板
            } else if (fangX(x) == 13 && fangY(y) == 11) {
                CreateFD.shuxingflag = 1;
                Log.e("点击屏幕属性", "x+" + onX);
                //英雄操作模块
//                }else if(fangX(x)==5&&fangY(y)==8) {
//                    jsxingzoutime=5;
//                    jsfx=1;
//                }else if(fangX(x)==5&&fangY(y)==10) {
//                    jsxingzoutime=5;
//                    jsfx=2;
//                }else if(fangX(x)==3&&fangY(y)==9) {
//                    jsxingzoutime=5;
//                    jsfx=3;
//                }else if(fangX(x)==7&&fangY(y)==9) {
//                    jsxingzoutime=5;
//                    jsfx=4;
//                }else if(fangX(x)==28&&fangY(y)==9||fangX(x)==29&&fangY(y)==9||
//                        fangX(x)==28&&fangY(y)==8||fangX(x)==29&&fangY(y)==8) {
//                    jsxingzoutime=10;
//                    gongji();
//                    jsfx=5;
                //点击道路
            } else {

                //region 绘制选塔菜单
                if (!showupdate || (showupdate && (onX != fangX(x) || Math.abs(onY - fangY(y)) < 1))) {
                    if (map[fangY(y)][fangX(x)] == 0)//如果可以画。注意这里横纵坐标竟然对调！！！
                    {
                        if (!showtamenu)//没有显示选塔菜单
                        {
                            onX = fangX(x);
                            onY = fangY(y);
                            canvas.drawBitmap(ta1c, null, new Rect((onX + 2) * xW, onY * xH, (onX + 2) * xW + xW, onY * xH + xH), paint);
                            canvas.drawBitmap(ta2c, null, new Rect((onX + 3) * xW, onY * xH, (onX + 3) * xW + xW, onY * xH + xH), paint);
                            canvas.drawBitmap(ta3c, null, new Rect((onX + 4) * xW, onY * xH, (onX + 4) * xW + xW, onY * xH + xH), paint);
                            canvas.drawBitmap(jinbita, null, new Rect((onX + 2) * xW, (onY + 1) * xH, (onX + 2) * xW + xW, (onY + 1) * xH + xH), paint);
                            showtamenu = true;
                        } else//有菜单
                        {
                            if (fangY(y) == onY && Math.abs(fangX(x) - onX) <= 10000)//在菜单内
                            {
                                if (onX < W - 6) {
                                    if (fangX(x) == (onX + 3)) {
                                        if (!qianbugou(JL2))//钱够用了
                                        {
                                            //建立塔
                                            //建造飞碟工作
                                            if (CreateFD.isjz == 0) {
                                                CreateFD.jzfdx = fangX(x) - 3;
                                                CreateFD.jzfdy = fangY(y);
                                                CreateFD.isjz = 1;
                                                CreateFD.isjz2 = 2;
                                                Log.e("建造状态", "开始建造...");
                                            } else {

                                                Log.e("建造状态", "建造冷却中...");
                                            }
                                            Log.e("建造", String.valueOf(fangX(x)) + "--" + String.valueOf(fangY(y)));
                                            //creteFdWord(fangY(y),fangX(x)-3);
//                                            map3[fangY(y)][fangX(x)-3]=-2;//////这里修改  建立塔 减钱
//                                            Data.jianzhu();//放塔的声音
//                                            Data.money-=JL2;
                                        }
                                    } else if (fangX(x) == (onX + 2)) {
                                        if (!qianbugou(JL1))//钱够用了
                                        {
                                            if (CreateFD.isjz == 0) {
                                                CreateFD.jzfdx = fangX(x) - 2;
                                                CreateFD.jzfdy = fangY(y);
                                                CreateFD.isjz = 1;
                                                CreateFD.isjz2 = 1;
                                                Log.e("建造状态", "开始建造...");
                                            } else {
                                                Log.e("建造状态", "建造冷却中...");
                                            }
                                        }
                                    } else if (fangX(x) == (onX + 4)) {
                                        if (!qianbugou(JL3))//钱够用了
                                        {
                                            if (CreateFD.isjz == 0) {
                                                CreateFD.jzfdx = fangX(x) - 4;
                                                CreateFD.jzfdy = fangY(y);
                                                CreateFD.isjz = 1;
                                                CreateFD.isjz2 = 3;
                                                Log.e("建造状态", "开始建造...");
                                            } else {
                                                Log.e("建造状态", "建造冷却中...");
                                            }

                                        }
                                    }
                                    //在地图右6格放置防御塔菜单点击事件
                                } else {
                                    if (fangX(x) == (onX - 3)) {
                                        if (!qianbugou(JL2))//钱够用了
                                        {
                                            if (CreateFD.isjz == 0) {
                                                CreateFD.jzfdx = fangX(x) + 3;
                                                CreateFD.jzfdy = fangY(y);
                                                CreateFD.isjz = 1;
                                                CreateFD.isjz2 = 2;
                                                Log.e("建造状态", "开始建造...");
                                            } else {
                                                Log.e("建造状态", "建造冷却中...");
                                            }
                                        }
                                    } else if (fangX(x) == (onX - 4)) {
                                        if (!qianbugou(JL1))//钱够用了
                                        {
                                            if (CreateFD.isjz == 0) {
                                                CreateFD.jzfdx = fangX(x) + 4;
                                                CreateFD.jzfdy = fangY(y);
                                                CreateFD.isjz = 1;
                                                CreateFD.isjz2 = 1;
                                                Log.e("建造状态", "开始建造...");
                                            } else {
                                                Log.e("建造状态", "建造冷却中...");
                                            }
                                        }
                                    } else if (fangX(x) == (onX - 2)) {
                                        if (!qianbugou(JL3))//钱够用了
                                        {
                                            if (CreateFD.isjz == 0) {
                                                CreateFD.jzfdx = fangX(x) + 2;
                                                CreateFD.jzfdy = fangY(y);
                                                CreateFD.isjz = 1;
                                                CreateFD.isjz2 = 3;
                                                Log.e("建造状态", "开始建造...");
                                            } else {
                                                Log.e("建造状态", "建造冷却中...");
                                            }
                                        }
                                    }
                                }
                                //减钱
                                showtamenu = false;
                            } else {
                                onX = fangX(x);
                                onY = fangY(y);
                                canvas.drawBitmap(ta1c, null, new Rect((onX + 2) * xW, onY * xH, (onX + 2) * xW + xW, onY * xH + xH), paint);
                                canvas.drawBitmap(ta2c, null, new Rect((onX + 3) * xW, onY * xH, (onX + 3) * xW + xW, onY * xH + xH), paint);
                                canvas.drawBitmap(ta3c, null, new Rect((onX + 4) * xW, onY * xH, (onX + 4) * xW + xW, onY * xH + xH), paint);
                                showtamenu = true;
                            }
                        }
                    } else//地图不是土地 解决塔和地图重合问题
                    {
                        if (showtamenu) {
                            if (fangY(y) == onY && Math.abs(fangX(x) - onX) <= 10000)//在菜单内
                            {
                                // 地图最右6格
                                if (onX < W - 6) {
                                    if (fangX(x) == (onX + 3)) {
                                        if (!qianbugou(JL2))//钱够用了
                                        {
                                            if (CreateFD.isjz == 0) {
                                                CreateFD.jzfdx = fangX(x) - 3;
                                                CreateFD.jzfdy = fangY(y);
                                                CreateFD.isjz = 1;
                                                CreateFD.isjz2 = 2;
                                                Log.e("建造状态", "开始建造...");
                                            } else {
                                                Log.e("建造状态", "建造冷却中...");
                                            }
//                                            map3[fangY(y)][fangX(x)-3] = -2;//////这里修改
//                                            Data.jianzhu();//放塔的声音
//                                            Data.money -= JL2;
                                        }
                                    } else if (fangX(x) == (onX + 2)) {
                                        if (!qianbugou(JL1))//钱够用了
                                        {
                                            if (CreateFD.isjz == 0) {
                                                CreateFD.jzfdx = fangX(x) - 2;
                                                CreateFD.jzfdy = fangY(y);
                                                CreateFD.isjz = 1;
                                                CreateFD.isjz2 = 1;
                                                Log.e("建造状态", "开始建造...");
                                            } else {
                                                Log.e("建造状态", "建造冷却中...");
                                            }
                                        }
                                    } else if (fangX(x) == (onX + 4)) {
                                        if (!qianbugou(JL3))//钱够用了
                                        {
                                            if (CreateFD.isjz == 0) {
                                                CreateFD.jzfdx = fangX(x) - 4;
                                                CreateFD.jzfdy = fangY(y);
                                                CreateFD.isjz = 1;
                                                CreateFD.isjz2 = 3;
                                                Log.e("建造状态", "开始建造...");
                                            } else {
                                                Log.e("建造状态", "建造冷却中...");
                                            }
                                        }
                                    }
                                } else {
                                    if (fangX(x) == (onX - 3)) {
                                        if (!qianbugou(JL2))//钱够用了
                                        {
                                            if (CreateFD.isjz == 0) {
                                                CreateFD.jzfdx = fangX(x) + 3;
                                                CreateFD.jzfdy = fangY(y);
                                                CreateFD.isjz = 1;
                                                CreateFD.isjz2 = 2;
                                                Log.e("建造状态", "开始建造...");
                                            } else {
                                                Log.e("建造状态", "建造冷却中...");
                                            }
                                        }
                                    } else if (fangX(x) == (onX - 4)) {
                                        if (!qianbugou(JL1))//钱够用了
                                        {
                                            if (CreateFD.isjz == 0) {
                                                CreateFD.jzfdx = fangX(x) + 4;
                                                CreateFD.jzfdy = fangY(y);
                                                CreateFD.isjz = 1;
                                                CreateFD.isjz2 = 1;
                                                Log.e("建造状态", "开始建造...");
                                            } else {
                                                Log.e("建造状态", "建造冷却中...");
                                            }
                                        }
                                    } else if (fangX(x) == (onX - 2)) {
                                        if (!qianbugou(JL3))//钱够用了
                                        {
                                            if (CreateFD.isjz == 0) {
                                                CreateFD.jzfdx = fangX(x) + 2;
                                                CreateFD.jzfdy = fangY(y);
                                                CreateFD.isjz = 1;
                                                CreateFD.isjz2 = 3;
                                                Log.e("建造状态", "开始建造...");
                                            } else {
                                                Log.e("建造状态", "建造冷却中...");
                                            }
                                        }
                                    }
                                }

                                //减钱
                            }
                            showtamenu = false;
                        }
                    }

                }
            }

            //endregion
            //region 绘制升级菜单

            if (showupdate)//有升级菜单
            {
                if (onX == fangX(x)) {
                    // map1[onY][onX]=-111;
                    // Log.d("减钱",fangX(x)+"");
                    if (onY == (fangY(y) + 1))//升级减钱
                    {
                        if (map[onY][onX] == -1) {
                            if (!qianbugou(SJ1_11)) {
                                map[onY][onX] = -11;
                                Data.money -= SJ1_11;
                                Data.shengji();
                            }
                        }//减钱 如果钱不够就不升级 下同
                        else if (map[onY][onX] == -11) {
                            if (!qianbugou(SJ11_111)) {
                                map[onY][onX] = -111;
                                Data.money -= SJ11_111;
                                Data.shengji();
                            }
                        }//减钱
                        else if (map[onY][onX] == -111) {
                            map[onY][onX] = -111;
                        }//
                        else if (map[onY][onX] == -2) {
                            if (!qianbugou(SJ2_22)) {
                                map[onY][onX] = -22;
                                Data.money -= SJ2_22;
                                Data.shengji();
                            }
                        }//减钱
                        else if (map[onY][onX] == -22) {
                            if (!qianbugou(SJ22_222)) {
                                map[onY][onX] = -222;
                                Data.money -= SJ22_222;
                                Data.shengji();
                            }
                        }//减钱
                        else if (map[onY][onX] == -222) {
                            map[onY][onX] = -222;
                        }//
                        else if (map[onY][onX] == -3) {
                            if (!qianbugou(SJ3_33)) {
                                map[onY][onX] = -33;
                                Data.money -= SJ3_33;
                                Data.shengji();
                            }
                        }//减钱
                        else if (map[onY][onX] == -33) {
                            if (!qianbugou(SJ33_333)) {
                                map[onY][onX] = -333;
                                Data.money -= SJ33_333;
                                Data.shengji();
                            }
                        }//减钱
                        else if (map[onY][onX] == -333) {
                            map[onY][onX] = -333;
                        }//
                        showtamenu = false;

                    } else if (onY == fangY(y)) {
                        showtamenu = false;
                        //showupdate=false;
                    } else if (onY == (fangY(y) - 1))//删除加钱
                    {
                        //如果删除3号防御塔，减去加钱buff
                        if (map[onY][onX] == -3 || map[onY][onX] == -33 || map[onY][onX] == -333) {
                            if (Data.getJinbitanum() - 1 <= 0) {
                                Data.setJinbitanum(0);
                            } else {
                                Data.setJinbitanum(Data.getJinbitanum() - 1);
                            }

                        }

                        if (map[onY][onX] == -1) {
                            map[onY][onX] = 0;
                            Data.money += M1_0;
                            Data.moneyjia();
                        }//加钱
                        else if (map[onY][onX] == -11) {
                            map[onY][onX] = 0;
                            Data.money += M11_0;
                            Data.moneyjia();
                        } else if (map[onY][onX] == -111) {
                            map[onY][onX] = 0;
                            Data.money += M111_0;
                            Data.moneyjia();
                        } else if (map[onY][onX] == -2) {
                            map[onY][onX] = 0;
                            Data.money += M2_0;
                            Data.moneyjia();
                        } else if (map[onY][onX] == -22) {
                            map[onY][onX] = 0;
                            Data.money += M22_0;
                            Data.moneyjia();
                        } else if (map[onY][onX] == -222) {
                            map[onY][onX] = 0;
                            Data.money += M222_0;
                            Data.moneyjia();
                        } else if (map[onY][onX] == -3) {
                            map[onY][onX] = 0;
                            Data.money += M3_0;
                            Data.moneyjia();
                        } else if (map[onY][onX] == -33) {
                            map[onY][onX] = 0;
                            Data.money += M33_0;
                            Data.moneyjia();
                        } else if (map[onY][onX] == -333) {
                            map[onY][onX] = 0;
                            Data.money += M333_0;
                            Data.moneyjia();
                        }


                        showtamenu = false;
                    }
                }
            }
            // if(!showupdate)
            if (!showtamenu)//没有选择菜单
            {
                if (map[fangY(y)][fangX(x)] < 0 && !showupdate)//如果是塔楼
                {
                    onX = fangX(x);
                    onY = fangY(y);
                    canvas.drawBitmap(update, null, new Rect((onX) * xW, (onY - 1) * xH, (onX) * xW + xW, (onY - 1) * xH + xH), paint);
                    canvas.drawBitmap(delete, null, new Rect((onX) * xW, (onY + 1) * xH, (onX) * xW + xW, (onY + 1) * xH + xH), paint);
                    canvas.drawCircle(onX * xW + xW / 2, onY * xH + xH / 2, xW * 3, paint);
                    showupdate = true;
                } else {
                    showupdate = false;
                }

            } else {
                if (map[fangY(y)][fangX(x)] < 0 && fangY(y) != onY && Math.abs(onX - fangX(x)) > 1)//如果是塔楼并且不在菜单里面
                {
                    onX = fangX(x);
                    onY = fangY(y);
                    paint.setColor(Color.BLACK);
                    canvas.drawBitmap(update, null, new Rect((onX) * xW, (onY - 1) * xH, (onX) * xW + xW, (onY - 1) * xH + xH), paint);
                    canvas.drawBitmap(delete, null, new Rect((onX) * xW, (onY + 1) * xH, (onX) * xW + xW, (onY + 1) * xH + xH), paint);
                    canvas.drawCircle(onX * xW + xW / 2, onY * xH + xH / 2, xW * 3, paint);
                    showupdate = true;
                    showtamenu = false;
                } else {
                    showupdate = false;
                }
            }


            //endregion

        } else//否则点击的是第一行
        {
            if (fangX(x) == backX && fangY(y) == backY + 10000)//点击返回按键
            {
                Data.anniu();
                Data.beijing();
                //Data.gamepause();
                mysurfaceview.setselectgame();
                // mysurfaceview.TouchListener();
                //设置gamestate为选择
            }
            Log.e("点击屏幕-属性菜单", "x+" + String.valueOf(x));
            //todo 开始游戏 (29,0)(30,0)(29,1)(30,1)

        }

        guaiwucreat.setmap(map);//更新信息

    }

    private void gongji() {

    }

    private void isPause() {
        if (pause) {
            //Data.gamestart();
            pause = false;
            guaiwucreat.Resume();

        } else {
            //Data.gamepause();
            pause = true;
            guaiwucreat.Pause();
        }
    }


    //绘制建造飞碟
    private void creteFdWord(int qfx, int qfy, int mdx, int mdy) {
        if (CreateFD.isjz == 1) {
            //计算距离
            int x1 = Math.abs(qfx - mdx);
            int y1 = Math.abs(qfy - mdy);
            int length = (int) Math.sqrt(x1 * x1 + y1 * y1);
            CreateFD.cfenshu = (length / xW) * 2;
            Log.e("建造飞碟速度", String.valueOf(CreateFD.cfenshu * 2));
            CreateFD.cfenshu = 100;
            CreateFD.zidan = CreateFD.cfenshu;
            CreateFD.isjz = 2;
        }
        Paint cfdPaint = new Paint();
        int dingzhix = (qfx - (mdx)) / CreateFD.cfenshu;
        int dingzhiy = (qfy - (mdy)) / CreateFD.cfenshu;
        //开始飞船飞到建造地点
        if (CreateFD.isjz == 2) {
            if (CreateFD.zidan < 0) {
                CreateFD.zidan = CreateFD.cfenshu;
                //开始建造
                CreateFD.isjz = 3;
            } else {
                Log.e("位置", String.valueOf((qfx + xW / 2 + xW / 3) + CreateFD.zidan * dingzhix));
                canvas.drawBitmap(feidie, null, new Rect(
                        (mdx) + CreateFD.zidan * dingzhix,
                        mdy + CreateFD.zidan * dingzhiy,
                        mdx + 80 + CreateFD.zidan * dingzhix,
                        mdy + 50 + CreateFD.zidan * dingzhiy), cfdPaint);
                // canvas.drawLine(qfx,qfy,mdx,mdy,paint);
            }
            CreateFD.zidan--;
        }

        //20开始建造
        if (CreateFD.isjz == 3) {
            Log.e("isjz数值", String.valueOf((CreateFD.isjz)));
            if (CreateFD.zidan2 < 0) {
                CreateFD.zidan2 = 50;
                //建造完成
                CreateFD.isjz = 4;
                cretaType();
            } else {
                canvas.drawBitmap(feidie, null, new Rect(
                        (mdx),
                        mdy,
                        mdx + 80,
                        mdy + 50), cfdPaint);
                cfdPaint.setColor(Color.RED);
                cfdPaint.setTextSize(20);
                cfdPaint.setTypeface(Typeface.DEFAULT_BOLD);
                canvas.drawText("build...", mdx + xW / 5, mdy + xH + xH / 3, cfdPaint);
            }

            CreateFD.zidan2--;
        }
        if (CreateFD.isjz == 4) {
            Log.e("isjz数值", String.valueOf((CreateFD.isjz)));
            if (CreateFD.zidan3 < 0) {
                CreateFD.zidan3 = 50;
                //建造完成
                CreateFD.isjz = 0;
                CreateFD.zidan = CreateFD.cfenshu;
                CreateFD.zidan2 = 50;
                CreateFD.zidan3 = 50;
                CreateFD.isjz2 = 0;
            } else {
                //canvas.drawText("建造完成",mdx,qfy,mdx,mdy,paint);
            }
            CreateFD.zidan3--;
        }
        //重置标志

    }

    private void cretaType() {
        //建造第二种
        if (CreateFD.isjz2 == 1) {
            map[CreateFD.jzfdy][CreateFD.jzfdx] = -1;//////这里修改  建立塔 减钱
            Data.jianzhu();//放塔的声音
            Data.money -= JL1;
        }
        if (CreateFD.isjz2 == 2) {
            map[CreateFD.jzfdy][CreateFD.jzfdx] = -2;//////这里修改  建立塔 减钱
            Data.jianzhu();//放塔的声音
            Data.money -= JL2;
        }
        if (CreateFD.isjz2 == 3) {
            map[CreateFD.jzfdy][CreateFD.jzfdx] = -3;//////这里修改  建立塔 减钱
            Data.jianzhu();//放塔的声音
            Data.money -= JL3;
            //加钱Buff
            Data.setJinbitanum(Data.getJinbitanum() + 1);
            //美人鱼大图
            mrytime = 1;
        }
    }

    private int fangX(int x)//返回x,y所在的方块的横坐标
    {
        return x / xW;
    }

    private int fangY(int y)//返回x,y所在的方块的纵坐标
    {
        return y / xH;
    }

    private boolean qianbugou(int costmoney) {
        if (Data.money - costmoney < 0)//钱不够用
        {
            Paint textPaint = new Paint();
            textPaint.setColor(Color.RED);
            textPaint.setTextSize(xH / 2);
            this.tishinum = tishinumzong;
            this.tishimoney = costmoney;
            return true;
        } else//钱够用
        {
            return false;
        }

    }

}
