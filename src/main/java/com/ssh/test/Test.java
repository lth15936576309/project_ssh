package com.ssh.test;

public class Test {

    public static void main(String[] args) {
        /*System.out.println(getGcd(50, 30));
        System.out.println(getLcm(50, 30));
        System.out.println("getGcdByArray:" + getGcdByArray(new int[]{1, 1, 1}));*/
        //printArray(5);
        //printArray2(5);
        //printArray(4, 3);
        System.out.println(countNum(12223322,22));
        System.out.println(countOfStr("((()))))("));

        int[] array = sort(new int[]{99,21,88,45,46,70,43});
        for(int i : array) {
            System.out.println(i);
        }
    }

    /**
     * 最大公约数
     *
     * @param number1
     * @param number2
     * @return
     */
    public static int getGcd(int number1, int number2) {
        int num = 0;
        while (number2 != 0) {
            num = number2;
            number2 = number1 % number2;
            number1 = num;
        }
        return num;
    }

    /**
     * 获取最小公倍数
     *
     * @param number1
     * @param number2
     * @return
     */
    public static int getLcm(int number1, int number2) {
        return number1 * number2 / getGcd(number1, number2);
    }

    /**
     * 获取一个数组的最大公约数
     *
     * @param array
     * @return
     */
    public static int getGcdByArray(int[] array) {
        int num = array[0];
        for (int i = 0; i < array.length - 1; i++) {
            num = getGcd(num, array[i + 1]);
        }
        return num;
    }

    /**
     * 根据n值打印数组图形
     *
     * @param n
     */
    public static void printArray(int n) {
        for (int i = 1; i <= n; i++) {
            int num = 1;
            if (i != 1 && i < n) {
                num = i * n + 1;
            } else if (i == n) {
                num = n + 1;
            }
            for (int j = num; j <= num + n - 1; j++) {
                System.out.print(j + "*");
            }
            System.out.println();
        }
    }

    public static void printArray2(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                if (j >= 2) {
                    System.out.print("*" + i);
                } else {
                    System.out.print(i);
                }
            }
            System.out.println();
        }
        for (int i = n; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                if (j == 1) {
                    System.out.print(i);
                } else {
                    System.out.print("*" + i);
                }
            }
            System.out.println();
        }
    }

    public static void printArray(int N, int S) {
        for (int i = S; i <= N + S - 1; i++) {
            for (int j = S; j <= i; j++) {
                System.out.print(i);
            }
            System.out.println();
        }
        for (int i = N + S - 1; i >= S; i--) {
            for (int j = 1; j <= i - S + 1; j++) {
                System.out.print(i);
            }
            System.out.println();
        }
    }

    /**
     * 统计数字num在数字n中出现的次数
     * @param n
     * @param num
     * @return
     */
    public static int countNum(Integer n, Integer num) {
        String str = n.toString();
        String strOfNum = num.toString();
        int lengthOfNum = strOfNum.length();
        char[] chars = str.toCharArray();
        int count = 0;
        for(int i = 0; i < chars.length - lengthOfNum + 1; i++) {
            String item = "";
            for(int j = i; j < i + lengthOfNum; j++) {
                item += String.valueOf(chars[j]);
            }
            if(strOfNum.equals(item)) {
                count ++;
            }
        }
        return count;
    }

    public static int countOfStr(String str) {
        String left = "(";
        String right = ")";
        int leftNum = str.indexOf(left);
        int rightNum = str.indexOf(right);
        int count = 0;
        if(leftNum == -1 || rightNum == -1 || leftNum > rightNum) {
            return -1;
        }
        for(int i = leftNum; i < str.length(); i++) {
            leftNum = str.indexOf(left, i);
            rightNum = str.indexOf(right, rightNum);
            if(leftNum != -1 && rightNum != -1 && leftNum < rightNum) {
                count ++;
            } else {
                break;
            }
            rightNum ++;
        }
        return count;
    }

    public static int[] sort(int[] array) {
        for(int i = array.length - 1;i > 0; i--) {
            for(int j = 0;j < i;j++) {
                if(array[j] > array[j+1]) {
                    int num = array[j];
                    array[j] = array[j+1];
                    array[j+1] = num;
                }
            }
        }
        return array;
    }
}
