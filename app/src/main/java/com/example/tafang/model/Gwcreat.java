package com.example.tafang.model;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;

import com.example.tafang.map.SelectMap;
import com.example.tafang.mySurfaceView.Mysurfaceview;
import com.example.tafang.R;

import java.util.Random;

import static com.example.tafang.model.CreateFD.boshu;

import static com.example.tafang.model.CreateFD.gwnowx;
import static com.example.tafang.model.CreateFD.gwnowy;
import static com.example.tafang.model.CreateFD.yibojieshu;
import static com.example.tafang.model.CreateFD.yxkxid;
import static com.example.tafang.model.Data.getGw1blood;
import static com.example.tafang.model.Data.getGw2blood;
import static com.example.tafang.model.Data.getGw3blood;
import static com.example.tafang.model.Data.setGw1blood;
import static com.example.tafang.model.Data.setGw2blood;
import static com.example.tafang.model.Data.setGw3blood;

/*
 *
 */
public class Gwcreat {//} implements Runnable{
    //自己设置的变量
    private int xW, xH;//小图的宽和高
    private int timejiange = 20000;//时间间隔
    private int gwjiange = 50;//guaiwu间隔
    private int numofgwalive = 0;//活着的怪物的个数
    private int zheyibowan = 1;//这一波完了吗？1完了 0没玩
    private Resources resources;
    private Guaiwu[] guaiwu1 = new Guaiwu[10];//每次出现的怪物数量
    private Guaiwu2[] guaiwu2 = new Guaiwu2[10];//自己的怪物
    private Bitmap jianke;
    private int numofgw = 0;//定义当前怪物的个数
    private int[][] map;
    private int map123;//第几幅地图
    private int numofwave;//波总数 分别为5,10,15
    private int nowwave = 0;//当前波数
    private int sX, sY;//开始的坐标
    private Canvas canvas;
    private Paint paint;
    private int time = 0;
    private int win = 0;//赢了么？ 赢了1 在玩0 输了-1
    //private Thread thread;
    private boolean pause = false;//怪物暂停出现
    int ssh = 0;
    //飞碟的x和y 状态 0123 sxzy
    int fdx = 3, fdy = 3;
    int fdzt = -1;
    private Bitmap leidian, feidie;
    Mysurfaceview mysurfaceview;

    public Gwcreat(Mysurfaceview mysurfaceview, Context context, int[][] map, int map123, Resources resources,
                   Canvas canvas, Paint paint, int numofwave, int sX, int sY, int xW, int xH) {
        this.mysurfaceview = mysurfaceview;
        this.numofwave = numofwave;//总波数
        this.sX = sX;
        this.sY = sY;
        this.xW = xW;
        this.xH = xH;
        this.canvas = canvas;
        this.paint = paint;
        this.resources = resources;
        this.map123 = map123;
        this.map = map;


        if (map123 == 1) {
            this.jianke = BitmapFactory.decodeResource(resources, R.drawable.guaiwu1);
            this.timejiange = 20000;
        } else if (map123 == 2) {
            this.jianke = BitmapFactory.decodeResource(resources, R.drawable.guaiwu2);
            this.timejiange = 20000;
        } else {
            this.jianke = BitmapFactory.decodeResource(resources, R.drawable.guaiwu3);
            this.timejiange = 20000;
        }


    }

    public void setmap(int[][] map) {
        this.map = map;
    }

    public void newguaiwu(int map123, int nowwave, int numofgw)//第map123幅地图（不同地图初始坐标不同，1为0,4 2为0,1 3为0,5）；当前第几波怪物;new 一个第几个怪物
    {
        boshu = nowwave;

        if (map123 == 1) {
            Random random = new Random();
            int types = random.nextInt(100);
            int chukou = 6;
            if (types % 2 == 0) {
                chukou = 6;
            } else if (types % 2 == 1) {
                chukou = 6;
            }
            switch (nowwave)//当前为第nowwave波怪物
            {
                default:
                    //增加难度
                    setGw1blood(getGw1blood() + 30);
                    setGw2blood(getGw2blood() + 50);
                    setGw3blood(getGw3blood() + 100);
                    //随机生成怪物
                    guaiwu1[numofgw] = new Guaiwu(map, RandCreatGW(), resources, xW, xH);
                    guaiwu1[numofgw].setPosition_x(xW * 0);
                    guaiwu1[numofgw].setPosition_y(xH * (chukou));

                    ssh++;
            }

        } else if (map123 == 2) {
            Random random = new Random();
            int types = random.nextInt(100);
            int chukou = 9;
            if (types % 2 == 0) {
                chukou = 9;
            } else if (types % 2 == 1) {
                chukou = 9;
            }
            switch (nowwave)//当前为第nowwave波怪物
            {
                default:
                    //增加难度
                    setGw1blood(getGw1blood() + 30);
                    setGw2blood(getGw2blood() + 50);
                    setGw3blood(getGw3blood() + 100);
                    //随机生成怪物
                    guaiwu1[numofgw] = new Guaiwu(map, RandCreatGW(), resources, xW, xH);
                    guaiwu1[numofgw].setPosition_x(xW * 0);
                    guaiwu1[numofgw].setPosition_y(xH * (chukou));

                    ssh++;
            }
        } else {
            Random random = new Random();
            int types = random.nextInt(100);
            int chukou = 5;
            if (types % 2 == 0) {
                chukou = 5;
            } else if (types % 2 == 1) {
                chukou = 5;
            }
            switch (nowwave)//当前为第nowwave波怪物
            {

                default:
                    //增加难度
                    setGw1blood(getGw1blood() + 30);
                    setGw2blood(getGw2blood() + 50);
                    setGw3blood(getGw3blood() + 100);
                    //随机生成怪物
                    guaiwu1[numofgw] = new Guaiwu(map, RandCreatGW(), resources, xW, xH);
                    guaiwu1[numofgw].setPosition_x(xW * 0);
                    guaiwu1[numofgw].setPosition_y(xH * (chukou));
                    ssh++;

                    break;
            }
        }

    }

    private int RandCreatGW() {
        Random random = new Random();
        int types = random.nextInt(3);
        return types;
    }

    public void start()//启动绘制
    {
        Paint textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(xH / 2);

        //canvas.drawText(""+(timejiange-time%timejiange),sY*xH,(sX-1)*xW,textPaint);//绘制下一波还剩多少时间
        canvas.drawText("" + (timejiange - time % timejiange), sY * xH, (sX - 1) * xW, textPaint);
        if (nowwave >= numofwave && win != -1)//当前波数大于总波数，且没输说明执行完毕
        {
            textPaint.setTextSize(xH * 2);
            textPaint.setColor(Color.BLUE);
            win = 1;
            canvas.drawText("你赢了！！888！", 4 * xH, 5 * xW, textPaint);
            //pause=true;
            //跳转到结束页
            //赢了这一关
            mysurfaceview.setselectgame();
        }
        if (win == -1) {
            textPaint.setTextSize(xH * 2);
            textPaint.setColor(Color.RED);
            canvas.drawText("你输了！！！", 4 * xH, 5 * xW, textPaint);
            pause = true;
            mysurfaceview.setselectgame();
        }
        //生命条
        LiveDra();
        //波束 提示
        Boshu();
        //逻辑
        if (!pause) {
            time++;
            numofgwalive = 0;
            for (int x = 0; x < guaiwu1.length; x++) {
                if (guaiwu1[x] != null) {
                    //如果怪还活着
                    if (guaiwu1[x].isalive()) {
                        numofgwalive++;
                        zheyibowan = 0;
                    }
                }
            }

            //如果怪数量《=0 下一波 0没完
            if (zheyibowan == 0) {
                //如果活着的怪物为0  （没有活着的怪物了）那么这一波完了 初始时间
                if (numofgwalive <= 0) {
                    time = timejiange;
                    zheyibowan = 1;
                }
            }

            //下一波 定义怪个数为0
            if (time % timejiange == 0 && time > 0) {

                nowwave++;//波数增加
                numofgw = 0;
                yibojieshu = 1;
                Log.e("一波结束", "--");

            }
            //如果怪个数（numofgw）<要生成的怪个数，继续生成怪
            if (numofgw < guaiwu1.length) {
                //时间到了
                if (time % gwjiange == 0 && time > 0) {
                    //nowwave 当前的关卡数
                    if (map123 == 1) {
                        newguaiwu(1, nowwave, numofgw);
                    } else if (map123 == 2) {
                        newguaiwu(2, nowwave, numofgw);
                    } else {
                        newguaiwu(3, nowwave, numofgw);
                    }
                    numofgw++;
                }
            }
            //绘制怪物
            //region 绘制怪物 运动绘制  后来想到不用那么多guaiwu1 guaiwu2 所以就用guaiwu1代替所有怪物 不改了
            for (int i = 0; i <= numofgw; i++) {   //怪存活
                if (guaiwu1[i].isalive()) {   //达到目的地
                    if (guaiwu1[i].isArrived()) {   //怪移动的方向
                        if (map[fangY(guaiwu1[i].getPosition_y())][fangX(guaiwu1[i].getPosition_x())] == 2)//向下运动
                        {
                            guaiwu1[i].setFangxiang(0);
                            guaiwu1[i].setArrived(false);
                            guaiwu1[i].settoPositopn(zuoxiaX(fangX(guaiwu1[i].getPosition_x())), zuoxiaY(fangY(guaiwu1[i].getPosition_y()) + 1));//新目的地
                        } else if (map[fangY(guaiwu1[i].getPosition_y())][fangX(guaiwu1[i].getPosition_x())] == 4)//向上运动
                        {
                            guaiwu1[i].setFangxiang(3);
                            guaiwu1[i].setArrived(false);
                            guaiwu1[i].settoPositopn(zuoxiaX(fangX(guaiwu1[i].getPosition_x())), zuoxiaY(fangY(guaiwu1[i].getPosition_y()) - 1));//新目的地
                        } else if (map[fangY(guaiwu1[i].getPosition_y())][fangX(guaiwu1[i].getPosition_x())] == 1 ||
                                map[fangY(guaiwu1[i].getPosition_y())][fangX(guaiwu1[i].getPosition_x())] == 3)//向右运动
                        {
                            guaiwu1[i].setFangxiang(2);
                            guaiwu1[i].setArrived(false);
                            guaiwu1[i].settoPositopn(zuoxiaX(fangX(guaiwu1[i].getPosition_x()) + 1), zuoxiaY(fangY(guaiwu1[i].getPosition_y())));//新目的地
                        } else {
                            guaiwu1[i].setArrived(false);
                            if (guaiwu1[i].getFangxiang() == 0)//向下走
                            {
                                guaiwu1[i].settoPositopn(zuoxiaX(fangX(guaiwu1[i].getPosition_x())), zuoxiaY(fangY(guaiwu1[i].getPosition_y()) + 1));//新目的地
                            } else if (guaiwu1[i].getFangxiang() == 3)//向上走
                            {
                                guaiwu1[i].settoPositopn(zuoxiaX(fangX(guaiwu1[i].getPosition_x())), zuoxiaY(fangY(guaiwu1[i].getPosition_y()) - 1));//新目的地
                            } else//向右走
                            {
                                guaiwu1[i].settoPositopn(zuoxiaX(fangX(guaiwu1[i].getPosition_x()) + 1), zuoxiaY(fangY(guaiwu1[i].getPosition_y())));//新目的地
                            }

                        }
                        for (int x = 0; x < guaiwu1.length; x++) {
                            if (guaiwu1[x] != null) {
                                //如果怪还活着
                                if (guaiwu1[x].isalive()) {
                                    gwnowx = guaiwu1[x].getPosition_x();
                                    gwnowy = guaiwu1[x].getPosition_y();
                                    gwnowy -= 10;
                                    yxkxid = x;
                                    break;
                                }
                            }
                        }
                        if (map[fangY(guaiwu1[i].getPosition_y())][fangX(guaiwu1[i].getPosition_x())] == 0)//走到草地上就死了 不测试说明
                        {
                            guaiwu1[i].gotodeath();
                            if (map123 == 1) {
                                Data.jk1bloodnow -= 1;
                                Data.diaoxue();//吼叫一声
                                if (Data.jk1bloodnow == 0) {
                                    win = -1;
                                    nowwave = 5;
                                    //gameover 输了
                                }
                            } else if (map123 == 2) {
                                Data.jk2bloodnow -= 1;
                                Data.diaoxue();//吼叫一声
                                if (Data.jk2bloodnow == 0) {
                                    win = -1;
                                    nowwave = 5;
                                    //gameover 输了
                                }
                            } else {
                                Data.jk3bloodnow -= 1;
                                Data.diaoxue();//吼叫一声
                                if (Data.jk3bloodnow == 0) {
                                    win = -1;
                                    nowwave = 0;
                                    //gameover 输了
                                }
                            }

                        }
                        // guaiwu1[0].drawgw(canvas,paint);
                    }
                    guaiwu1[i].drawgw(canvas, paint);
                    guaiwu1[i].setmap(map);
                }
            }
            //攻击逻辑

        } else//否则按了暂停键
        {

        }

    }

    private void Boshu() {
        Paint linePaint = new Paint();
        linePaint.setColor(Color.WHITE);
        linePaint.setStrokeWidth(1 + xW / 15);
        linePaint.setTextSize(40);
        //粗体
        Typeface font = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
        linePaint.setTypeface(font);
        canvas.drawText("第" + (nowwave + 1) + "波/共" + numofwave + "波", xW * 2, xH * 1, linePaint);
    }

    private void LiveDra() {
        Paint tPaint = new Paint();
        tPaint.setTextSize(35);
        tPaint.setColor(Color.RED);
        Paint jdtPaint = new Paint();
        jdtPaint.setColor(Color.RED);
        jdtPaint.setStrokeWidth(xH);
        paint.setColor(Color.RED);
        if (SelectMap.mapSum == 1) {
            if (Data.jk1bloodnow != 0) {
                canvas.drawText("生命值", 16 * xW, 1 * xH, tPaint);
                canvas.drawRect(16 * xW, 1 * xH + xH / 2 - xH / 5, 16 * xW + (int) (Data.jk3bloodnow * 21), 1 * xH * 8 / 9 + xH / 2 - xH / 5, jdtPaint);
                canvas.drawRect(16 * xW, 1 * xH + xH / 2 - xH / 5, 16 * xW + (int) (210), 1 * xH * 8 / 9 + xH / 2 - xH / 5, paint);
            } else {
                canvas.drawText("生命值", 16 * xW, 1 * xH, tPaint);
                canvas.drawRect(16 * xW, 1 * xH - xH / 5 + xH / 2, 16 * xW + (int) (210), 1 * xH * 8 / 9 + xH / 2 - xH / 5, paint);
            }
        } else if (SelectMap.mapSum == 2) {
            if (Data.jk2bloodnow != 0) {
                canvas.drawText("生命值", 16 * xW, 1 * xH, tPaint);
                canvas.drawRect(16 * xW, 1 * xH + xH / 2 - xH / 5, 16 * xW + (int) (Data.jk3bloodnow * 21), 1 * xH * 8 / 9 + xH / 2 - xH / 5, jdtPaint);
                canvas.drawRect(16 * xW, 1 * xH + xH / 2 - xH / 5, 16 * xW + (int) (210), 1 * xH * 8 / 9 + xH / 2 - xH / 5, paint);
            } else {
                canvas.drawText("生命值", 16 * xW, 1 * xH, tPaint);
                canvas.drawRect(16 * xW, 1 * xH - xH / 5 + xH / 2, 16 * xW + (int) (210), 1 * xH * 8 / 9 + xH / 2 - xH / 5, paint);
            }
        } else {
            if (Data.jk3bloodnow != 0) {
                canvas.drawText("生命值", 16 * xW, 1 * xH, tPaint);
                canvas.drawRect(16 * xW, 1 * xH + xH / 2 - xH / 5, 16 * xW + (int) (Data.jk3bloodnow * 21), 1 * xH * 8 / 9 + xH / 2 - xH / 5, jdtPaint);
                canvas.drawRect(16 * xW, 1 * xH + xH / 2 - xH / 5, 16 * xW + (int) (210), 1 * xH * 8 / 9 + xH / 2 - xH / 5, paint);
            } else {
                canvas.drawText("生命值", 16 * xW, 1 * xH, tPaint);
                canvas.drawRect(16 * xW, 1 * xH - xH / 5 + xH / 2, 16 * xW + (int) (210), 1 * xH * 8 / 9 + xH / 2 - xH / 5, paint);
            }
        }
    }

    public void kx() {

        //扣血
//        guaiwu1[yxkxid].kouxue((int) (guaiwu1[yxkxid].getxue()*0.1));
        guaiwu1[yxkxid].kouxue(15);
    }


    private void drawguaiwu() {

    }

    public void Pause()//设置暂停
    {
        this.pause = true;
    }

    public void Resume()//继续游戏
    {
        this.pause = false;
    }

    private int fangX(int x)//返回x,y所在的方块的横坐标
    {
        return (x + xW / 5) / xW;
    }

    private int fangY(int y)//返回x,y所在的方块的纵坐标
    {
        return (y + xH / 5) / xH - 1;
    }

    private int zuoxiaX(int X)//根据所在方块 返回方块左下角横坐标
    {
        return (X) * xW;
    }

    private int zuoxiaY(int Y)//根据所在方块 返回方块左下角纵坐标
    {
        return (Y + 1) * xH;
    }

    public int Weizhi(int i)//第几个乖的位置
    {
        int we = 0;
        for (int x = 0; x < guaiwu1.length; x++) {
            if (guaiwu1[x] != null) {
                //如果怪还活着
                if (guaiwu1[x].isalive()) {
                    we = guaiwu1[x].getPosition_x();
                    break;
                }
            }
        }
        return we;
    }

}
