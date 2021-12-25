package com.dltour;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class HorseChessBoard {
    //添加一个棋盘
    //添加一个是否浏览过得其他；
    public static int X;//列
    public static int Y;//行
    public static  boolean[] visited;
    public static boolean finished=false;

    public static void main(String[] args) {
        X=8;
        Y=8;
        int row=1;
        int col=1;
        int[][] chessBoard= new int[X][Y];
        visited=new boolean[X*Y];
        travelNext(row-1,col-1,chessBoard,1);
        for (int[] rows:chessBoard){
            for (int sttwp:rows){
                System.out.print(sttwp+"\t");
            }
            System.out.println();
        }

    }


    //走下一步
    public static void travelNext(int row,int column,int[][] chessBoard,int step){
        ArrayList list=hasNext(new Point(row,column));
        visited[row*X+column]=true;
        chessBoard[row][column]=step;
        //如果有左上角的点
        sort(list);
        while (!list.isEmpty()) {
            Point point1=(Point) list.remove(0);
            if (!visited[point1.y*X+point1.x]) {
                travelNext(point1.y, point1.x, chessBoard, step + 1);
            }
        }
        if (step<X*Y&&!finished){
            chessBoard[row][column]=0;
            visited[row*X+column]=false;
        }else {
            finished=true;
        }
    }

    //判断是否有下一步
    public static ArrayList<Point> hasNext(Point curPoint){
        ArrayList<Point> ps=new ArrayList<>();
        Point p1=new Point();
        if ((p1.x=curPoint.x-2)>=0 && (p1.y= curPoint.y-1)>=0){
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        return ps;
    }
    public static void sort(ArrayList<Point> ps) {
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                // 获取o1 点的下一步的所有位置
                /*int count1 = next(o1).size();
                int count2 = next(o2).size();
                if (count1 < count2) {
                    return -1;
                } else if (count1 == count2) {
                    return 0;
                } else {
                    return 1;
                }*/
                // 注释部分简化
                return Integer.compare(hasNext(o1).size(), hasNext(o2).size());
            }
        });
    }
}

//import java.util.Comparator;
//
//public class HorseChessBoard {
//    private static int X;   // 棋盘的列
//    private static int Y;   // 棋盘的行
//    // 创建一个数组，标记棋盘的各个位置是否被访问过
//    private static boolean[] visited;
//    // 使用一个属性，标记是否棋盘的所有位置都被访问
//    private static boolean finished;    // 如果为true，表示成功
//
//    public static void main(String[] args) {
//        // 测试骑士周游算法是否正确
//        X = 6;
//        Y = 6;
//        int row = 4;    // 马儿初始位置的行，从1开始编号
//        int column = 6; // 马儿初始位置的列，从1开始编号
//        // 创建棋盘
//        int[][] chessboard = new int[X][Y];
//        visited = new boolean[X * Y];   // 初始值都是false
//        // 测试一下耗时
//        long start = System.currentTimeMillis();
//        traversalChessBoard(chessboard, row - 1, column - 1, 1);
//        long end = System.currentTimeMillis();
//        System.out.println("共耗时：" + (end - start) + "毫秒");
//        // 输出棋盘
//        for (int[] rows : chessboard) {
//            for (int step : rows) {
//                System.out.print(step + "\t");
//            }
//            System.out.println();
//        }
//    }
//
//    /**
//     * 完成马踏棋盘（骑士周游问题）的算法
//     *
//     * @param chessboard 棋盘
//     * @param row        马儿当前位置的行 从0开始
//     * @param column     马儿当前位置的列 从0开始
//     * @param step       是第几步执行，初始为1
//     */
//    public static void traversalChessBoard(int[][] chessboard, int row, int column, int step) {
//        chessboard[row][column] = step; // 当前位置记录为第几步
//        visited[row * X + column] = true;   // 当前位置标记为已访问
//        // 获取当前位置可以走的下一个位置的集合
//        ArrayList<Point> ps = next(new Point(column, row));
//        // 对ps进行排序，排序的规则就是对ps所有的Point对象的下一步的位置，进行非递减排序
//        sort(ps);
//        // 遍历ps
//        while (!ps.isEmpty()) {
//            Point p = ps.remove(0); // 取出下一个可以走的位置
//            // 判断该点是否已经访问过
//            if (!visited[p.y * X + p.x]) {  // 说明还没有访问过
//                traversalChessBoard(chessboard, p.y, p.x, step + 1);
//            }
//        }
//        // 判断马儿是否完成了任务，使用step和应该走的步数比较，
//        // 如果没有达到数量，则表示没有完成任务，将整个棋盘置0
//        // 说明：step < X * Y成立的情况有两种
//        // ① 棋盘到目前为止，仍然没有走完
//        // ② 棋盘已经走完，目前处于回溯过程中
//        if (step < X * Y && !finished) {
//            chessboard[row][column] = 0;
//            visited[row * X + column] = false;
//        } else {
//            finished = true;
//        }
//    }
//
//    /**
//     * 功能：根据当前位置（Point对象），计算马儿还能走哪些位置（Point）
//     * 并放入到一个集合中（ArrayList），最多八个位置
//     *
//     * @param curPoint 当前位置
//     * @return 能走的位置集合
//     */
//    public static ArrayList<Point> next(Point curPoint) {
//        // 创建一个ArrayList
//        ArrayList<Point> ps = new ArrayList<>();
//        // 创建一个point
//        Point p1 = new Point();
//        // 周遭所有可能
//        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
//            ps.add(new Point(p1));
//        }
//        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
//            ps.add(new Point(p1));
//        }
//        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
//            ps.add(new Point(p1));
//        }
//        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
//            ps.add(new Point(p1));
//        }
//        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
//            ps.add(new Point(p1));
//        }
//        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
//            ps.add(new Point(p1));
//        }
//        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
//            ps.add(new Point(p1));
//        }
//        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
//            ps.add(new Point(p1));
//        }
//        return ps;
//    }
//
//    // 根据当前这一步的所有的下一步的选择位置，进行非递减排序，减少回溯的次数
//    public static void sort(ArrayList<Point> ps) {
//        ps.sort(new Comparator<Point>() {
//            @Override
//            public int compare(Point o1, Point o2) {
//                // 获取o1 点的下一步的所有位置
//                /*int count1 = next(o1).size();
//                int count2 = next(o2).size();
//                if (count1 < count2) {
//                    return -1;
//                } else if (count1 == count2) {
//                    return 0;
//                } else {
//                    return 1;
//                }*/
//                // 注释部分简化
//                return Integer.compare(next(o1).size(), next(o2).size());
//            }
//        });
//    }
//
//}