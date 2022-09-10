package com.example.tafang.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.example.tafang.R;

public class Hero {
    private int now_hx, now_hy;//英雄现在的位置
    private int xW, xH;
    private Resources resources;
    private Bitmap jsgj[] = new Bitmap[10];
    private int jszhen = 0, jszhen2 = 0, jszhen3 = 0, jszhen4 = 0;
    //  private int[] gj=new int[]{R.drawable.js1gj10,R.drawable.js1gj10,R.drawable.js1gj11,R.drawable.js1gj11,
    //     R.drawable.js1gj12,R.drawable.js1gj12,R.drawable.js1gj13,R.drawable.js1gj13,R.drawable.js1gj14,R.drawable.js1gj14};
    private int[] gj = new int[]{R.drawable.tjs1gj10, R.drawable.tjs1gj10, R.drawable.tjs1gj11, R.drawable.tjs1gj11,
            R.drawable.tjs1gj12, R.drawable.tjs1gj12, R.drawable.tjs1gj13, R.drawable.tjs1gj13, R.drawable.tjs1gj14, R.drawable.tjs1gj14};
    //private int[] gj=new int[]{R.drawable.a10,R.drawable.a10,R.drawable.a11,R.drawable.a11,
    //       R.drawable.a12,R.drawable.a12,R.drawable.a13,R.drawable.a13,R.drawable.a14,R.drawable.a14};
    private Bitmap jsl[] = new Bitmap[4];
    private Bitmap jsr[] = new Bitmap[4];
    private int[] jsleft = new int[]{R.drawable.tjs1gj6, R.drawable.tjs1gj7,
            R.drawable.tjs1gj8, R.drawable.tjs1gj9};
    private int[] jsright = new int[]{R.drawable.js1gj6, R.drawable.js1gj7,
            R.drawable.js1gj8, R.drawable.js1gj9};
    // private int[] jsleft=new int[]{R.drawable.a06,R.drawable.a07,
    //    R.drawable.a08,R.drawable.a09};
    private Bitmap zhanli;
    private int map[][];
    private Canvas canvas;
    private Paint paint;
    int top1, top2;

    public Hero(int[][] map, Resources resources, Canvas canvas, Paint paint, int xW, int xH, int now_hx, int now_hy) {
        this.map = map;
        this.resources = resources;
        this.xW = xW;
        this.xH = xH;
        this.canvas = canvas;
        this.paint = paint;
        this.now_hx = now_hx;
        this.now_hy = now_hy;
        this.top1 = (xH + xH);
        this.top2 = (xH);
        initializie();//初始化
        // gw1= BitmapFactory.decodeResource(resources, R.drawable.jingling1);
    }

    private void initializie() {
        for (int s = 0; s < gj.length; s++) {
            jsgj[s] = BitmapFactory.decodeResource(resources, gj[s]);
        }
        for (int s = 0; s < jsleft.length; s++) {
            jsl[s] = BitmapFactory.decodeResource(resources, jsleft[s]);
        }
        for (int s = 0; s < jsright.length; s++) {
            jsr[s] = BitmapFactory.decodeResource(resources, jsright[s]);
        }
        zhanli = BitmapFactory.decodeResource(resources, R.drawable.js1gj1);

    }

    public void setmap(int[][] map)//设置map
    {
        this.map = map;
    }

    public void drawgw(Canvas canvas, Paint paint, int to_hx, int to_hy)//绘制自己
    {
        //上下移动
        moveTopBottom();
        //左右移动
        moveLeftRight();


    }

    private void moveTopBottom() {


    }

    private void moveLeftRight() {
        if (getNow_hy() > 13 * xH || getNow_hy() < xH) {
            setNow_hy(xH);
        }
        if (getNow_hx() > 31 * xW || getNow_hx() < xW) {
            setNow_hx(xW);
        }
        //在2格以内跑到安全范围
        if (Math.abs(CreateFD.gwnowx - now_hx) <= xW * 1) {
            setNow_hx(getNow_hx() + 10);
            draright(canvas, paint, getNow_hx(), getNow_hy());
            //2-4格子 内攻击  gwnowx>xW*3刚开始三格不在攻击范围
        } else if (Math.abs(CreateFD.gwnowx - now_hx) >= xW * 1 && Math.abs(CreateFD.gwnowx - now_hx) <= xW * 2 && CreateFD.gwnowx > xW * 3 && Math.abs(CreateFD.gwnowy - now_hy) < 5) {
            dragongji(canvas, paint, getNow_hx(), getNow_hy());
            CreateFD.yxkx = 1;
            //4格子外 内追击
        } else if (Math.abs(CreateFD.gwnowx - now_hx) >= xW * 2) {
            if (CreateFD.gwnowx < now_hx) {
                setNow_hx(getNow_hx() - 10);
            } else {
                setNow_hx(getNow_hx() + 10);
            }

            draleft(canvas, paint, getNow_hx(), getNow_hy());
        } else //在2格以内跑到安全范围
            if (Math.abs(CreateFD.gwnowy - now_hy) > 5) {
                if (CreateFD.gwnowy - now_hy < 0) {
                    setNow_hy(getNow_hy() - 10);
                    dratop(canvas, paint, getNow_hx(), getNow_hy());
                } else {
                    setNow_hy(getNow_hy() + 10);
                    drabottom(canvas, paint, getNow_hx(), getNow_hy());
                }


            } else {
                drayuandi(canvas, paint, getNow_hx(), getNow_hy());
            }
    }

    //左移动
    public void draleft(Canvas canvas, Paint paint, int to_hx, int to_hy)//绘制自己
    {

        // Log.e("全屏炮弹状态",String.valueOf(quanpignpaodan)+"--"+String.valueOf(qflag)+"--"+String.valueOf(isgetpao));
        Log.e("状态英雄", String.valueOf(CreateFD.isgetpao));
        if (jszhen >= jsl.length) {
            jszhen = 0;
        }

        canvas.drawBitmap(jsl[jszhen], null, new Rect(to_hx, to_hy - top1,
                to_hx + xW * 3, to_hy + top2), paint);
        jszhen++;

    }

    public void draright(Canvas canvas, Paint paint, int to_hx, int to_hy)//绘制自己
    {

        // Log.e("全屏炮弹状态",String.valueOf(quanpignpaodan)+"--"+String.valueOf(qflag)+"--"+String.valueOf(isgetpao));
        Log.e("状态英雄", String.valueOf(CreateFD.isgetpao));
        if (jszhen2 >= jsr.length) {
            jszhen2 = 0;
        }

        canvas.drawBitmap(jsr[jszhen2], null, new Rect(to_hx, to_hy - top1,
                to_hx + xW * 3, to_hy + top2), paint);
        jszhen2++;

    }

    public void drabottom(Canvas canvas, Paint paint, int to_hx, int to_hy)//绘制自己
    {
        // Log.e("全屏炮弹状态",String.valueOf(quanpignpaodan)+"--"+String.valueOf(qflag)+"--"+String.valueOf(isgetpao));
        Log.e("状态英雄", String.valueOf(CreateFD.isgetpao));
        if (jszhen3 >= jsl.length) {
            jszhen3 = 0;
        }

        canvas.drawBitmap(jsl[jszhen3], null, new Rect(to_hx, to_hy - top1,
                to_hx + xW * 3, to_hy + top2), paint);
        jszhen3++;

    }

    public void dratop(Canvas canvas, Paint paint, int to_hx, int to_hy)//绘制自己
    {

        // Log.e("全屏炮弹状态",String.valueOf(quanpignpaodan)+"--"+String.valueOf(qflag)+"--"+String.valueOf(isgetpao));
        Log.e("状态英雄", String.valueOf(CreateFD.isgetpao));
        if (jszhen4 >= jsl.length) {
            jszhen4 = 0;
        }

        canvas.drawBitmap(jsl[jszhen4], null, new Rect(to_hx, to_hy - top1,
                to_hx + xW * 3, to_hy + top2), paint);
        jszhen4++;

    }

    public void drayuandi(Canvas canvas, Paint paint, int to_hx, int to_hy)//绘制自己
    {

        canvas.drawBitmap(zhanli, null, new Rect(to_hx, to_hy - top1,
                to_hx + xW * 3, to_hy + top2), paint);


    }

    public void dragongji(Canvas canvas, Paint paint, int to_hx, int to_hy)//绘制自己
    {

        if (jszhen >= gj.length) {
            jszhen = 0;
            CreateFD.ptgj = 1;
        }
        canvas.drawBitmap(jsgj[jszhen], null, new Rect(to_hx, to_hy - top1,
                to_hx + xW * 3, to_hy + top2), paint);

        jszhen++;

    }

    public int getNow_hx() {
        return now_hx;
    }

    public void setNow_hx(int now_hx) {
        this.now_hx = now_hx;
        CreateFD.yxx = this.now_hx;
    }

    public int getNow_hy() {
        return now_hy;
    }

    public void setNow_hy(int now_hy) {
        this.now_hy = now_hy;
        CreateFD.yyy = this.now_hy;
    }


}

