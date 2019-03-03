package com.zy.util;

import java.util.ArrayList;
import java.util.List;

public  class myGame {
    public static void main(String[] args){
        int length = 20;
//        String strX = "3/1,9/6,7/4,4,2,1/6,4,1/1,1,9/6,3/2,1,5/2,9/2,1,2,5/12/1,1,4,1/9,2,1/15/1,3";
//        String strY = "1,2/3,1,4/5,3,2/3,2,2,2/1,3,1,4/3,5,2/6,1,1,2/1,1,6,2/15/3,2,2,2,2/15/13/2,2,3,1/2,1,2,1/5,5";
//        String strX = "3,2,2/3,2,3/1,11/1,1,13/3,1,13/3,11,2/1,3,6,1/3,3,5,1/3,4,3,7/16/17/9,5,1/2,3,4,1/2,13,2/16/18/6,7/3,2,6/4,2,4/4,1";
//        String strY = "1,2,1,1,1,2/1,1,1,2,5/2,2,2,2,6/1,3,2,7/5,10/9/15/12,3,2/8,3,3,2/4,4,3/4,4,5/4,3,6/5,3,6/18/19/18/11,3/3,5,3/3,6/6";
//        String strX = "2,4/4,6/7,1/1,9/2,6,2/1,6/2,6,3/1,5/3,5,2/1,1,10/1,2,7,2/15,2/11/15,3/1,2,1,2,1/1,2,4/3,2/1,3/2,1/1,3";
//        String strY = "2,1,1,1,1,1,1/5,1,1,4/4,4/1,1/3/7/6/3/1,3/2,4/3,8/13/12/12/15/6,2,1/6,1,2,1/2,1,1,2,3,1,1/2,2,1,4,1,2,1/5,1,2,1,2,1,2";
        String strX = "3/3,4/2,4,3/2,3,3/3,3,5/3,4,2,4/3,4,1,8/2,6,8/3,3,1,6/11,5/3,2,4,6/2,3,3,8/1,2,5,8/3,5,7/3,6,6/8,9/6,3/1,3/3/3";
        String strY = "4,3/5,3/4,2,4/4,6/9/7,3/6,1,8/7,1,8/10,5,2/3,9,1/1,10/2,2,2/1,3,3,1/4,5/6,6/14/3,11/2,11/1,11/1,8,2";
//        String strX = "0/2,1/5/2,1/0";
//        String strY = "3/1/3/3/1";
        mainMethod(length,dynamicArr(strX),dynamicArr(strY));

//        int length = 5;
//        int[][] x = {{3},{3},{2},{2},{5}};
//        int[][] y = {{4},{5},{2,1},{1,1},{1}};
//        mainMethod(length,x,y);
    }

    /**
     * 根据1,2,3/2,3/3/1,1,2/3,2这种格式的字符串生成动态二维数组
     * @param str
     * @return
     */
    public static int[][] dynamicArr(String str){
//        String str = "2,4/4,6/7,1/1,9/2,6,2/1,6/2,6,3/1,5/3,5,2/1,1,10/1,2,7,2/15,2/11/15,3/1,2,1,2,1/1,2,4/3,2/1,3/2,1/1,3";
        String[] arr = str.split("/");
        int[][] result = new int[arr.length][];
        for (int i = 0;i<arr.length;i++){
            String[] a = arr[i].split(",");
            int[] temp = new int[a.length];
            for(int j = 0;j<a.length;j++){
                temp[j] = Integer.parseInt(a[j]);
            }
            result[i] = temp;
        }
        return result;
    }
    public static char[][] mainMethod(int length,int[][] x,int[][]y){
        char[][] zuoBiao = new char[length][length];
        for (int i = 0;i<length;i++){
            for (int j = 0;j<length;j++){
                zuoBiao[i][j] = '0';
            }
        }

//        int[][] x = {{2,6},{3,3},{2,2},{1,1,1},{3,1},{4},{3,1},{2,6},{3},{10}};
//        int[][] y = {{1,4,2},{3,2,3},{10},{2,1},{1,2,1},{1,1,1},{1,1,1},{2,1,1},{3,1,1},{5,1,1}};
//        int[][] x = {{3},{1,5},{2,6},{8,1},{2,6},{1,5},{5},{2},{2},{2}};
//        int[][] y = {{1},{5},{3},{1,2},{3,4},{7},{7},{7},{3,3},{5}};
//        int[][] x = {{5},{1,1,2,1},{3,1,4},{2,7},{7,1},{1,2,1},{3,1,2},{1,3,1},{4,2,1},{1,3,1}};
//        int[][] y = {{3,4},{1,3,1,2},{1,1,3,1},{2,3,3},{1,3,2,1},{2,2,3},{6,1},{3,1},{2,3},{4}};
        for(int i = 0;i<x.length;i++){
            List<String> strs = math(x[i],length);
            strs = mate(getRowCol(zuoBiao,"x",i),strs);
            zuoBiao = setZuoBiao(zuoBiao,compare(strs),"x",i);
        }
        for(int i = 0;i<y.length;i++){
            List<String> strs = math(y[i],length);
            strs = mate(getRowCol(zuoBiao,"y",i),strs);
            zuoBiao = setZuoBiao(zuoBiao,compare(strs),"y",i);
        }
        for(int i = 0;i<x.length;i++){
            List<String> strs = math(x[i],length);
            strs = mate(getRowCol(zuoBiao,"x",i),strs);
            zuoBiao = setZuoBiao(zuoBiao,compare(strs),"x",i);
        }
        for(int i = 0;i<y.length;i++){
            List<String> strs = math(y[i],length);
            strs = mate(getRowCol(zuoBiao,"y",i),strs);
            zuoBiao = setZuoBiao(zuoBiao,compare(strs),"y",i);
        }
        for(int i = 0;i<x.length;i++){
            List<String> strs = math(x[i],length);
            strs = mate(getRowCol(zuoBiao,"x",i),strs);
            zuoBiao = setZuoBiao(zuoBiao,compare(strs),"x",i);
        }
        for(int i = 0;i<y.length;i++){
            List<String> strs = math(y[i],length);
            strs = mate(getRowCol(zuoBiao,"y",i),strs);
            zuoBiao = setZuoBiao(zuoBiao,compare(strs),"y",i);
        }
        for(int i = 0;i<x.length;i++){
            List<String> strs = math(x[i],length);
            strs = mate(getRowCol(zuoBiao,"x",i),strs);
            zuoBiao = setZuoBiao(zuoBiao,compare(strs),"x",i);
        }
        for(int i = 0;i<y.length;i++){
            List<String> strs = math(y[i],length);
            strs = mate(getRowCol(zuoBiao,"y",i),strs);
            zuoBiao = setZuoBiao(zuoBiao,compare(strs),"y",i);
        }
//        System.out.println(getRowCol(zuoBiao,"y",0));
        printResult(zuoBiao);
        printResult2(zuoBiao);
        return zuoBiao;
//        strs = mate("? * ? ? * * * * ? ? ",strs);
//
//        System.out.println(compare(strs));
    }
    public static String getRowCol(char[][] zuoBiao,String type,int index){
        StringBuffer sb = new StringBuffer();
        if(type.equals("y")){
            for (int i = 0;i<zuoBiao.length;i++){
                sb.append(zuoBiao[index][i]+" ");
            }
        }
        if(type.equals("x")){
            for (int i = 0;i<zuoBiao.length;i++){
                sb.append(zuoBiao[i][index]+" ");
            }
        }
        return sb.toString();
    }
    public static char[][] setZuoBiao(char[][] zuoBiao,String str,String type,int index){
        char[] org = str.toCharArray();
        if(type.equals("x")){
            for (int i = 0;i<zuoBiao.length;i++){
                if(org[i] != '0'){
                    zuoBiao[i][index] = org[i];
                }
            }
        }else if(type.equals("y")){
            for (int i = 0;i<zuoBiao.length;i++){
                if(org[i] != '0') {
                    zuoBiao[index][i] = org[i];
                }
            }
        }
        return zuoBiao;
    }
    public static List<String> math(int[] arr,int maxLength){
//        int maxLength = 10;//总方格
        int nowLength = 0;//当前长度
        int trueNum = 0;
        List<String> strs = new ArrayList<String>();

//        int[] arr = {1,3,2};
        int[] arrLeft = new int[arr.length];
        for (int i = 0;i<arr.length;i++){
            if(i == 0){
                arrLeft[i] = 0;
            }else{
                arrLeft[i] = 1;
            }
            trueNum = arr[i] + trueNum;
        }
        //* - * * * - * - - -   //- * - * * * - * - -   //- - * - * * * - * -   //- - - * - * * * - *
        //* - * * * - - * - -   //- * - * * * - - * -   //- - * - * * * - - *
        //* - * * * - - - * -   //- * - * * * - - - *
        //* - * * * - - - - *

        //* - - * * * - * - -   //- * - - * * * - * -   //- - * - - * * * - *
        //* - - * * * - - * -   //- * - - * * * - - *
        //* - - * * * - - - *

        //* - - - * * * - * -   //- * - - - * * * - *
        //* - - - * * * - - *

        //* - - - - * * * - *
        boolean flag = true;

        char[] common = {};
        while(flag) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < arr.length; i++) {
                for (int k = 0; k < arrLeft[i]; k++) {
                    sb.append("- ");
                }
                for (int j = 0; j < arr[i]; j++) {
                    sb.append("* ");
                }
                nowLength = nowLength + arr[i] + arrLeft[i];
            }
            if (nowLength < maxLength) {
                for (; nowLength < maxLength; nowLength++) {
                    sb.append("- ");
                }
                arrLeft[arrLeft.length-1] = arrLeft[arrLeft.length-1]+1;
            }else if(nowLength == maxLength){
                for(int i = arr.length-1;i>=0;i--){
                    if(i == 0){flag = false;break;}
                    if(arrLeft[i] > 1){
                        arrLeft[i] = 1;
                        if(i > 0){
                            arrLeft[i-1] += 1;
                        }else{
                            flag = false;
                        }
                        break;
                    }
                }
            }
            nowLength = 0;
            strs.add(sb.toString());
//            System.out.println(sb.toString());
        }
        return strs;
    }
    /**
     * 求所有字符串交集项
     * @return
     */
    public static String compare(List<String> strs){
        char[] char1 = {};
        char[] char2 = {};
        for (String s:strs){
            if(char1.length == 0){
                char1 = s.toCharArray();
            }else{
                char2 = s.toCharArray();
                for(int i = 0;i<char1.length;i++){
                    if(char1[i] != char2[i]){
                        char1[i] = '0';
                    }
                }
            }
        }
        return String.valueOf(char1).replaceAll(" ","");
    }
    /**
     * 找出和源字符串匹配的字符串
     * @param org
     * @param targets
     * @return
     */
    public static List<String> mate(String org,List<String> targets){
        char[] char1 = org.toCharArray();
        char[] char2 = {};
        boolean flag = true;
        List<String> result = new ArrayList<String>();
        for (String s : targets) {
            char2 = s.toCharArray();
            for (int i = 0; i < char1.length; i++) {
                if (char1[i] != '0' && char1[i] != ' ') {
                    if (char1[i] != char2[i]) {
                        flag = false;
                    }
                }
            }
            if(flag){
                result.add(s);
            }
            flag = true;
        }
        return result;
    }

    public static void printResult(char[][] xy){
//        int[][] xy = new int[10][10];
        for (int i = 0;i<xy.length;i++){
            for (int j = 0;j<xy[i].length;j++){
                if(i == 0 && j == 0){
                    System.out.print("  "+"\t");
                    for (int k = 0;k<xy[j].length;k++) {
                        System.out.print("x" + k + "\t");
                    }
                    System.out.println();
                }
                if (j == 0){
                    System.out.print("y"+i+"\t");
                }
//                System.out.print(i+""+j+"\t");
                System.out.print(xy[i][j]+" \t");
            }
            System.out.println();
        }
    }
    public static String printResult2(char[][] xy){
        StringBuffer sb = new StringBuffer();
        for (int i = 0;i<xy.length;i++){
            for (int j = 0;j<xy[i].length;j++){
                if(String.valueOf(xy[i][j]).equals("-")){
                    System.out.print(" ");
                    sb.append(" ");
                }else{
                    System.out.print("█");
                    sb.append("█");
                }
//                System.out.print(String.valueOf(xy[i][j]).replaceAll("\\*","■"));
            }
            System.out.println();
            sb.append("\n");
        }
        return sb.toString();
    }
    class XNum{
        private int x;
        private int length;
        private boolean ifLeft;
        private int left;
        private boolean ifRigth;
        private int rigth;
        private int arrIndex;

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public boolean isIfLeft() {
            return ifLeft;
        }

        public void setIfLeft(boolean ifLeft) {
            this.ifLeft = ifLeft;
        }

        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public boolean isIfRigth() {
            return ifRigth;
        }

        public void setIfRigth(boolean ifRigth) {
            this.ifRigth = ifRigth;
        }

        public int getRigth() {
            return rigth;
        }

        public void setRigth(int rigth) {
            this.rigth = rigth;
        }

        public int getArrIndex() {
            return arrIndex;
        }

        public void setArrIndex(int arrIndex) {
            this.arrIndex = arrIndex;
        }
    }
    static class YNum{
        private int y;
        private int length;
        private boolean ifLeft;
        private int left;
        private boolean ifRigth;
        private int rigth;
        private int arrIndex;

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public boolean isIfLeft() {
            return ifLeft;
        }

        public void setIfLeft(boolean ifLeft) {
            this.ifLeft = ifLeft;
        }

        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public boolean isIfRigth() {
            return ifRigth;
        }

        public void setIfRigth(boolean ifRigth) {
            this.ifRigth = ifRigth;
        }

        public int getRigth() {
            return rigth;
        }

        public void setRigth(int rigth) {
            this.rigth = rigth;
        }

        public int getArrIndex() {
            return arrIndex;
        }

        public void setArrIndex(int arrIndex) {
            this.arrIndex = arrIndex;
        }
    }
    class ZuoBiao {
        private int x;
        private int y;
        private boolean ifKnow;
        private boolean ifTure;

        public boolean isIfKnow() {
            return ifKnow;
        }

        public void setIfKnow(boolean ifKnow) {
            this.ifKnow = ifKnow;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public boolean isIfTure() {
            return ifTure;
        }

        public void setIfTure(boolean ifTure) {
            this.ifTure = ifTure;
        }
    }
    static class xy{
        private String type;
        private int index;
        private int[] arr;
    }
}
