package com.example.tafang.other;

public class SelectMap {
    public static int mapSum=0;//第几张地图
    public static int[][] map1={// 4上转弯 6上 638上右转 526左下转
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,3,3,3,8,8,8,8,8,8,8,8,2,2,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,3,3,3,8,8,8,8,8,8,8,8,2,6,0,0,0,0,0,0,0,0,0,0,0},
            {1,5,5,5,5,5,5,5,5,5,6,0,0,0,0,0,0,0,6,6,6,0,0,0,0,0,0,0,0,0,0,0},
            {1,5,5,5,5,5,5,5,5,4,4,0,0,0,0,0,0,0,6,6,6,0,0,0,0,0,0,0,0,0,0,0},
            {1,5,5,5,5,5,5,5,5,4,4,0,0,0,0,0,0,0,6,6,6,0,0,8,8,8,8,8,8,8,8,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,3,8,8,8,8,8,8,8,8,8,8,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,8,8,8,8,8,8,8,8,8,8,8,8,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
    };//H行W列
    public static int[][] map2={
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,8,8,8,8,8,8,2,2,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,6,8,8,8,8,8,8,2,2,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,6,0,0,0,0,0,6,6,6,0,0,0,0,0},
            {0,0,0,0,0,0,0,3,3,8,8,8,8,8,8,8,8,4,4,0,0,0,0,0,3,3,3,8,8,8,8,0},
            {0,0,0,0,0,0,0,6,3,8,8,8,8,8,8,8,8,4,4,0,0,0,0,0,3,3,3,8,8,8,8,0},
            {1,5,5,5,5,5,5,6,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {1,5,5,5,5,5,5,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
    };//H行W列
    public static int[][] map3={
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,8,8,1,1,1},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,6,1,1,1,1},
            {1,5,5,5,5,5,5,5,5,5,5,5,5,5,2,2,0,0,0,0,0,0,0,0,0,0,6,6,0,0,0,0},
            {1,5,5,5,5,5,5,5,5,5,5,5,5,5,2,2,0,0,0,0,0,0,0,0,0,0,6,6,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,6,6,6,0,0,0,0,0,0,0,0,0,0,6,6,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,6,6,6,0,0,0,0,0,0,0,0,0,0,6,6,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,6,3,8,8,8,8,8,8,8,8,8,8,8,4,6,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,6,8,8,8,8,8,8,8,8,8,8,8,8,8,8,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    };//H行W列
    public static int[][] map4={
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,8,8,8,8,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,6,0,0,0,0},
            {1,5,5,5,5,5,5,5,5,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,6,0,0,0,0},
            {1,5,5,5,5,5,5,5,5,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,6,0,0,0,0},
            {0,0,0,0,0,0,0,0,6,6,6,0,0,6,6,6,0,0,0,0,0,0,0,0,0,0,6,6,0,0,0,0},
            {0,0,0,0,0,0,0,0,6,6,6,0,0,6,6,6,0,0,0,0,0,0,0,0,0,0,6,6,0,0,0,0},
            {0,0,0,0,0,0,0,0,3,3,3,0,0,6,3,8,8,8,8,8,8,8,8,8,8,8,4,6,0,0,0,0},
            {0,0,0,0,0,0,0,0,3,3,3,0,0,6,8,8,8,8,8,8,8,8,8,8,8,8,8,8,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    };//H行W列
}
