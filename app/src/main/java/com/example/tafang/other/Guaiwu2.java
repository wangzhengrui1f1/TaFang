package com.example.tafang.other;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.example.tafang.R;


public class Guaiwu2 {
    private int position_x,position_y;//怪物的位置,指的是图片左下角的坐标
    private int toX,toY;//怪物要去的位置
    private int leixing;//怪物的类型
    private Bitmap gw1,gw2,gw3;
    private int xW,xH;//小地图的宽和高
    private Bitmap jl1[][]=new Bitmap[4][4];
    private Bitmap jl2[][]=new Bitmap[4][4];
    private Bitmap jl3[][]=new Bitmap[4][4];
    private Bitmap jl4[][]=new Bitmap[4][4];
    private Bitmap texiao;
    private Bitmap texiao2[][]=new Bitmap[2][2];
    private Bitmap jingbipic;
    private int fangxiang=2;//怪物行走的方向，向下为0，向左为1，向右为2，向上为3默认为向右
    private float dangqianzhen;//dangqian是第几张图片
    private final float xingzousudu=(float)0.2;//设置行走摆动速度，0-1之间且不为0
    private int sudu=4;//设置行走速度，1以上
    private final float gw1Wbili=(float) 1.3,gw1Hbili=(float) 1.3;//设置怪物宽和高是小地图的几倍
    private final float gw2Wbili=(float)0.8,gw2Hbili=(float)0.8;//设置怪物宽和高是小地图的几倍
    private final float gw3Wbili=(float)1.1,gw3Hbili=(float)1.1;//设置怪物宽和高是小地图的几倍
    private Resources resources;
    private int gwW,gwH;//怪物的宽和高
    private boolean alive=true;//还活着吗？
    private boolean arrived=true;//抵达目的地了吗
    private int map[][];
    private int gw1W,gw1H;//怪物的图片宽高度，包含16个小图的图片
    private int gw2W,gw2H;
    private int gw3W,gw3H;
    int first=0;
    //飞碟的x和y 状态 0123 sxzy  持续时间
    int fdx=3,fdy=3,leidiantime=0;
    int fdzt=-1,firstfd=0;
    private Bitmap leidian,feidie,paodanmei;
    int zidan=20;//攻击特效速度 (越低越快)
    int jiguang=35;//激光攻击特效速度 (越低越快)
    int jiguang2=35;//激光攻击特效速度 (越低越快)
    Paint jgPaint;
    int firstz1=0;
    int jinbiadd=20;
    int addM=0;
    private int zuo,you,shang,xia;//定义在范围内的几个小地图的位置
    private int dapaosudu=20;//大炮速度
    private int cfdint=100,cfflag=0;
    private int blood;//怪物的血量为
    private int nowblood;//怪物当前血量

    private final int SW1=50;//定义怪物死亡挣到的钱为
    private final int SW2=100;//定义怪物死亡挣到的钱为
    private final int SW3=150;//定义怪物死亡挣到的钱为
    private int gw1blood= Data.getGw1blood();//定义怪物1血量
    private int gw2blood= Data.getGw2blood();//定义怪物2血量
    private  int gw3blood= Data.getGw3blood();//定义怪物12血量
    //炮弹图1 flag 1 2控制炮弹 3控制生成x
    private Bitmap paos1,baozha1;
    int paox,paoy,paoflag1=0,paoflag2=0,paoflag3;
    private int[] left=new int[]{R.drawable.js1gj6,R.drawable.js1gj7,R.drawable.js1gj8,R.drawable.js1gj9};
    public Guaiwu2(int[][] map, int leixing, Resources resources, int xW, int xH)
    {
      this.leixing=leixing;
        this.map=map;
        if(leixing==1)
        {
            this.blood=gw1blood;
            this.nowblood=gw1blood;
        }else if(leixing==2)
        {
            this.blood=gw2blood;
            this.nowblood=gw2blood;
        }else
        {
            this.blood=gw3blood;
            this.nowblood=gw3blood;
        }
      this.resources=resources;
        this.xW=xW;
        this.xH=xH;
        texiao= BitmapFactory.decodeResource(resources, R.drawable.gstart2);
        texiao2[0][0]= BitmapFactory.decodeResource(resources, R.drawable.gstart2);
        texiao2[0][1]= BitmapFactory.decodeResource(resources, R.drawable.texiao2);
        texiao2[1][0]= BitmapFactory.decodeResource(resources, R.drawable.texiao6);
        texiao2[1][1]= BitmapFactory.decodeResource(resources, R.drawable.gstart2);
        jingbipic= BitmapFactory.decodeResource(resources, R.drawable.jinbi);
        feidie=BitmapFactory.decodeResource(resources, R.drawable.feidie);
        leidian=BitmapFactory.decodeResource(resources, R.drawable.leidian2);
        paodanmei=BitmapFactory.decodeResource(resources,R.drawable.pao1);
        paos1=BitmapFactory.decodeResource(resources,R.drawable.paos1);
        baozha1=BitmapFactory.decodeResource(resources,R.drawable.baozha1);

        jgPaint=new Paint();
        initializie();//初始化
       // gw1= BitmapFactory.decodeResource(resources, R.drawable.jingling1);
    }
    private void initializie()
    {
        if(leixing==1)
        {
            gw1= BitmapFactory.decodeResource(resources, R.drawable.jingling1);
            gw1W=gw1.getWidth();gw1H=gw1.getHeight();
            for(int i=0;i<4;i++)//将怪物图分割为4*4的图像
            {
                for(int j=0;j<4;j++)
                {
                    jl1[i][j]=Bitmap.createBitmap(gw1,j*gw1W/4,i*gw1H/4,gw1W/4,gw1H/4);
                }
            }
//            for(int is=0;is<4;is++){
//                jl1[2][is]=BitmapFactory.decodeResource(resources, left[is]);
//            }

            gwH=(int)(xH*gw1Hbili);gwW=(int)(xW*gw1Wbili);//怪物的高和宽
        }else if(leixing==2)
        {
            gw2= BitmapFactory.decodeResource(resources, R.drawable.jingling2);
            gw2W=gw2.getWidth();gw2H=gw2.getHeight();
            for(int i=0;i<4;i++)//将怪物图分割为4*4的图像
            {
                for(int j=0;j<4;j++)
                {
                    jl2[i][j]=Bitmap.createBitmap(gw2,j*gw2W/4,i*gw2H/4,gw2W/4,gw2H/4);
                }
            }
            gwH=(int)(xH*gw2Hbili);gwW=(int)(xW*gw2Wbili);
        }else
        {
              gw3= BitmapFactory.decodeResource(resources, R.drawable.jingling3);
              gw3W=gw3.getWidth();gw3H=gw3.getHeight();
            for(int i=0;i<4;i++)//将怪物图分割为4*4的图像
            {
                for(int j=0;j<4;j++)
                {
                    jl3[i][j]=Bitmap.createBitmap(gw3,j*gw3W/4,i*gw3H/4,gw3W/4,gw3H/4);
                }
            }
            gwH=(int)(xH*gw3Hbili);gwW=(int)(xW*gw3Wbili);
        }


    }
    public void setmap(int[][] map)//设置map
    {
        this.map=map;
    }
    public void canbeattacked()//可以遭受攻击或者说在打击范围内
    {

    }
    public void settoPositopn(int tx,int ty)//设置走到哪里
    {
        this.toX=tx;
        this.toY=ty;
    }
    public int getFangxiang()
    {
        return this.fangxiang;
    }
    public boolean isalive()//还活着吗？
    {
        return alive;
    }
    public void gotodeath()//死了
    {
        this.alive=false;
    }
    public void setFangxiang(int fangxiang)
    {
        this.fangxiang=fangxiang;
    }
    public void drawgw(Canvas canvas, Paint paint)//绘制自己
    {
        //全屏炮弹
        if(CreateFD.qflag==1|| CreateFD.isgetpao==0){
            paodanmei2(canvas,paint);
        }

       if(alive)//如果还活着？
        {
            if(leixing==1)//第一种类型的怪物
            {
               canvas.drawBitmap(jl1[fangxiang][(int)dangqianzhen],null,new Rect(position_x,position_y-gwH,position_x+gwW,position_y),paint);
            }else if(leixing==2)
            {
                canvas.drawBitmap(jl2[fangxiang][(int)dangqianzhen],null,new Rect(position_x,position_y-gwH,position_x+gwW,position_y),paint);
            }else {
               canvas.drawBitmap(jl3[fangxiang][(int)dangqianzhen],null,new Rect(position_x,position_y-gwH,position_x+gwW,position_y),paint);
            }

            paint.setColor(Color.RED);
            paint.setStrokeWidth(1+xW/23);
            //绘制血量  设置 血条
            canvas.drawRect(position_x,position_y-gwH,position_x+(int)(gwW*(nowblood*1.0/blood)),position_y-gwH*8/9,paint);
            if(!arrived)//没有抵达那么就运动
            {
                if(fangxiang==0)
                {
                    this.position_y+=sudu;
                }else if(fangxiang==1)
                {
                    this.position_x+=sudu;
                }else if(fangxiang==2)
                {
                    this.position_x-=sudu;
                }else
                {
                    this.position_y-=sudu;
                }
                dangqianzhen+=xingzousudu;//变换步形
                if(dangqianzhen>4){
                    dangqianzhen=0;
                }

            }else//正常抵达
            {

            }
            //如果···则抵达了
            if(Math.abs(position_x-toX)+Math.abs(position_y-toY)<=2*sudu)
            {
                arrived=true;
                position_x=toX;
                position_y=toY;
            }
         //region 下面判断并绘制子弹  绘制激光代替子弹 子弹有点麻烦 其实就是画线
            zuo=zuoX(this.position_x)>0?zuoX(this.position_x):0;
            you=youX(this.position_x)<31?youX(this.position_x):31;
            shang=shangY(this.position_y)>0?shangY(this.position_y):0;
            xia=xiaY(this.position_y)<12?xiaY(this.position_y):12;
            this.sudu=4;
            initPaint();
            for(int i=shang;i<=xia;i++) {
                for(int j=zuo;j<=you;j++) {

                    //如果在范围内 this.position_x目标X  zhongX(j)防御塔x
                    if(length(this.position_x+xW,this.position_y-xH,zhongX(j),zhongY(i))<10*xW)
                    {
                        if(map[i][j]==-1)
                        {
                            if(jiguang<0) {
                                jiguang = 35;
                            }else if(jiguang<35&&jiguang>27){
                                jgPaint.setStrokeWidth(6);
                                jgPaint.setColor(Color.rgb(170,6,245));//紫色
                                jgPaint.setAlpha(120);
                                canvas.drawLine(this.position_x+xW/2,this.position_y-xH/2,zhongX(j),zhongY(i),jgPaint);
                            }else {

                            }
                            jiguang--;

                            this.nowblood-= CreateFD.ta1gj1;
                        }else if(map[i][j]==-11)
                        {
                            paint.setStrokeWidth(1+xW/23);
                            paint.setColor(Color.RED);//紫色
                            canvas.drawLine(this.position_x+xW/2,this.position_y-xH/2,zhongX(j),zhongY(i),paint);
                            this.nowblood-= CreateFD.ta1gj2;
                        }else if(map[i][j]==-111) {
                            paint.setStrokeWidth(1+xW/25);
                            paint.setColor(Color.RED);
                            if(jiguang2<0) {
                                jiguang2 = 35;
                            }else if(jiguang2<35&&jiguang2>27){
                                jgPaint.setStrokeWidth(6);
                                jgPaint.setColor(Color.rgb(170,6,245));//紫色
                                jgPaint.setAlpha(120);
                                canvas.drawLine(this.position_x+xW/2,this.position_y-xH/2,zhongX(j),zhongY(i),jgPaint);
                            }else {

                            }
                            jiguang2--;
                            CreatFeidie(canvas,paint);
                            this.nowblood-= CreateFD.ta1gj3;
                        }else if(map[i][j]==-2)
                        {
                            paint.setStrokeWidth(1+xW/23);
                            paint.setColor(Color.YELLOW);
                            canvas.drawLine(this.position_x+xW/2,this.position_y-xH/2,zhongX(j),zhongY(i),paint);
                            canvas.drawBitmap(texiao2[1][0],null,new Rect(position_x,position_y-gwH,
                                    position_x+gwW,position_y),paint);
                            this.nowblood-= CreateFD.ta2gj1;
                        }else if(map[i][j]==-22)
                        {
                            paint.setStrokeWidth(1+xW/23);
                            paint.setColor(Color.rgb(243,104,11));//橘色
                            canvas.drawLine(this.position_x+xW/2,this.position_y-xH/2,zhongX(j),zhongY(i),paint);
                            this.nowblood-= CreateFD.ta2gj2;
                        }else if(map[i][j]==-222)
                        {
                            paint.setStrokeWidth(1+xW/23);
                            paint.setColor(Color.rgb(22,124,227));//青色
                            int dingzhix=(zhongX(j)-(this.position_x+xW/2))/20;
                            int dingzhiy=(zhongY(i)-(this.position_y-xH/2))/20;
                            if(zidan<0) {
                                zidan = 20;
                                firstz1=1;
                            }else {
                                canvas.drawBitmap(drawgj(texiao2[1][0]),(this.position_x + xW / 2+xW/3) + zidan * dingzhix- gwW/2,
                                        this.position_y - xH / 2 + xH / 3+ zidan * dingzhiy-gwH/2 ,paint);
                            }
                            if(firstz1==1){
                                canvas.drawBitmap(drawgj(texiao2[0][1]),(this.position_x + xW / 2+xW/3) + zidan * dingzhix- gwW/2,
                                        this.position_y - xH / 2 + xH / 3+ zidan * dingzhiy-gwH/2 ,paint);
                            }
                            zidan--;
                            this.nowblood-= CreateFD.ta2gj3;
                        }else if(map[i][j]==-3)
                        {
                            paint.setStrokeWidth(1+xW/23);
                            paint.setColor(Color.rgb(226,116,19));//黄色
                            canvas.drawLine(this.position_x+xW/2,this.position_y-xH/2,zhongX(j),zhongY(i),paint);
                            //加钱Buff
                            if(addM>150){
                                addM=0;
                                Data.money=Data.money+Data.getJinbitanum()*1;
                            }
                            if(addM>80){
                                canvas.drawBitmap(jingbipic,null,new Rect(
                                        zhongX(j),zhongY(i),zhongX(j)+xW/2,zhongY(i)+xH/2),paint);
                            }
                            addM++;
                            this.sudu=3;
                            this.nowblood-= CreateFD.ta3gj1;
                        }else if(map[i][j]==-33)
                        {
                            paint.setStrokeWidth(1+xW/23);
                            paint.setColor(Color.rgb(56,224,64));//青色
                            canvas.drawLine(this.position_x+xW/2,this.position_y-xH/2,zhongX(j),zhongY(i),paint);
                            //加钱Buff
                            if(addM>150){
                                addM=0;
                                Data.money=Data.money+Data.getJinbitanum()*1;
                            }
                            if(addM>80){
                                canvas.drawBitmap(jingbipic,null,new Rect(
                                        zhongX(j),zhongY(i),zhongX(j)+xW/2,zhongY(i)+xH/2),paint);
                            }
                            addM++;
                            this.sudu=3;
                            this.nowblood-= CreateFD.ta3gj2;
                        }else if(map[i][j]==-333)
                        {
                            paint.setStrokeWidth(1+xW/23);
                            paint.setColor(Color.rgb(72,207,201));//青色

                            //canvas.drawLine(this.position_x+xW/2,this.position_y-xH/2,zhongX(j),zhongY(i),paint);
                            int dingzhix=(zhongX(j)-(this.position_x+xW/2))/20;
                            int dingzhiy=(zhongY(i)-(this.position_y-xH/2))/20;
                            //加钱Buff
                            if(addM>150){
                                addM=0;
                                Data.money=Data.money+Data.getJinbitanum()*1;
                            }
                            if(addM>80){
                                canvas.drawBitmap(jingbipic,null,new Rect(
                                        zhongX(j),zhongY(i),zhongX(j)+xW/2,zhongY(i)+xH/2),paint);
                            }
                            addM++;
                            //子弹
                            if(zidan<0) {
                                zidan = 20;
                                firstz1=1;
                                Data.jiguangmp3();
                            }else {
                                canvas.drawBitmap(drawgj(texiao),(this.position_x + xW / 2+xW/3) + zidan * dingzhix- gwW/2,
                                        this.position_y - xH / 2 + xH / 3+ zidan * dingzhiy-gwH/2 ,paint);
                            }
                            if(firstz1==1){
                                //小泡泡
                                //canvas.drawBitmap(drawgj(),position_x+xW/6,position_y-gwH+xH/6,paint);
                                //全身大泡泡
                                Paint jsPaint=new Paint();
                                jsPaint.setColor(Color.RED);
                                jsPaint.setTextSize(15);
                                 canvas.drawBitmap(drawgj(texiao),null,new Rect(position_x,
                                         position_y-gwH,position_x+gwW,position_y),paint);
                                 canvas.drawText("减速.",position_x,position_y-gwH,jsPaint);
//                                canvas.drawBitmap(drawgj(texiao2[1][0]),null,new Rect(position_x-xW/2,
//                                        position_y-gwH,position_x+gwW-xW/2,position_y),paint);
//                                canvas.drawBitmap(drawgj(texiao2[1][0]),null,new Rect(position_x+xW/2,
//                                        position_y-gwH,position_x+gwW+xW/2,position_y),paint);
                            }
                            zidan--;
                            this.sudu=3;
                            this.nowblood-= CreateFD.ta3gj3;
                        }

                    }

                }
            }
            //endregion
            //如果被打死了 怪死亡
            if(this.nowblood<=0)
            {
                alive=false;
                if(this.leixing==1) {
                    Data.money+=SW1;
                    Log.e("加金币","x:"+this.position_x+"y:"+this.position_y);
                    //Data.guai1();//吼叫一声
                }else if(this.leixing==2) {
                    Data.money+=SW2;
                    //Data.guai2();//吼叫一声
                }else {
                     Data.money+=SW3;
                     //Data.guai3();//吼叫一声
                }

            }
        }

    }

    private void initPaint() {
        Paint jsPaint=new Paint();
        jsPaint.setColor(Color.RED);

    }

    public int getPosition_x()
    {
        return position_x;
    }
    public int getPosition_y()
    {
        return position_y;
    }
    public void setPosition_x(int x)
    {
        this.position_x=x;
    }
    public void setPosition_y(int y)
    {
        this.position_y=y;
    }
    public boolean isArrived()//抵达了目的地了吗？
    {
        return arrived;
    }
    public void setArrived(boolean arrived)
    {
        this.arrived=arrived;
    }

    private int zuoX(int x)//返回x,y所在的方块的左三格的横格子位置
    {
        return (x-3*xW+xW/5)/xW;
    }
    private int youX(int x)//返回x,y所在的方块的右三格的横格子位置
    {
        return (x+3*xW+xW/5)/xW;
    }
    private int shangY(int y)//返回x,y所在的方块的上三格的纵格子位置
    {
        return (y-3*xH+xH/5)/xH-1;
    }
    private int xiaY(int y)///返回x,y所在的方块的下三格的纵格子位置
    {
        return (y+3*xH+xH/5)/xH-1;
    }

    private int zhongX(int X)//根据所在方块 返回方块中间横坐标
    {
        return (X)*xW+xW/2;
    }
    private int zhongY(int Y)//根据所在方块 返回方块中间纵坐标
    {
        return (Y)*xH+xH/2;
    }
    private int length(int x1,int y1,int x2,int y2)//两点间的距离
    {
        double xx=x1-x2;
        double yy=y1-y2;
        return (int)Math.sqrt(xx*xx+yy*yy);
        //return 300;

    }
private void CreatFeidie(Canvas canvas,Paint paint) {
    if(fdzt==-1){
        fdx++;
    }
    if(fdx>200){
        fdzt=0;
    }
    if(fdzt==0){
        fdx--;
    }
    if(fdx<=1){
        fdzt=-1;
    }
        canvas.drawBitmap(feidie,null,new Rect(this.position_x-xW/8,
                this.position_y-3*xH,this.position_x+xW-xW/8,this.position_y-2*xH-xH/2),paint);
        if(leidiantime<10){
            leidiantime++;
            canvas.drawBitmap(leidian,null,new Rect(this.position_x,
                    this.position_y-2*xH-xH/3,this.position_x+xW,this.position_y-xH/6),paint);
        }else {
            leidiantime++;
        }
        if(leidiantime>50){
            leidiantime=0;
        }

    // 1.生成随机数 2.判定方向 3.判定是否越界 4.不越界 执行  5.越界重新生成

}
    public Bitmap drawgj(Bitmap bitmap){
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        //设置想要的大小
        int newWidth=xW*2/3;
        int newHeight=xH*2/3;

        //计算压缩的比率
        float scaleWidth=((float)newWidth)/width;
        float scaleHeight=((float)newHeight)/height;

        //获取想要缩放的matrix
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth,scaleHeight);

        //获取新的bitmap
        bitmap=Bitmap.createBitmap(bitmap,0,0,width,height,matrix,true);
        bitmap.getWidth();
        bitmap.getHeight();
        return bitmap;
    }


    public void paodanmei2(Canvas canvas,Paint paint){

        //炮弹落点
        int mo=0;
        int h=0;
        CreateFD.isgetpao=0;
        //炮弹妹
  //      canvas.drawBitmap(paodanmei,null,new Rect(xW*2,
//                xH*4,xW*10,xH*12),paint);
        //确定炮弹落点
        if(paoflag3==0) {
            if(fangxiang==0||fangxiang==3){
                paox=this.position_x;
                paoy=this.position_y;
            }else {
                paox=this.position_x+100;
                paoy=this.position_y+50;
            }
            paoflag3++;
        }
        //计算 每次落下距离
        h=(this.position_y)/30;
        paoflag1++;
        //炮弹落下30
        if(paoflag1<30){
            paint.setColor(Color.RED);
            canvas.drawBitmap(paos1,null,new Rect(paox,
                    0+h*paoflag1-50,paox+50,100+h*paoflag1-50),paint);
        }
        if(paoflag1>30){
            canvas.drawBitmap(baozha1,null,new Rect(paox,
                    h*30,paox+100,h*30+50),paint);
            paoflag2++;
        }
        if(paoflag2>=10){
            this.nowblood-=300+(int) (nowblood*0.03)+ CreateFD.boshu*25;
            //sudu++;
            paoflag1=1;
            paoflag2=0;
            paoflag3=0;
            CreateFD.isgetpao=1;
        }



    }

    public void kouxue(int jian){
        this.nowblood-=jian;
    }
}
