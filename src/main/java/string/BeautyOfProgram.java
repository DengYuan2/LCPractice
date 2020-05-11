package string;

import org.junit.Test;

// 非leetCode中题目，是《编程之美》中的
//https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20%E9%A2%98%E8%A7%A3%20-%20%E5%AD%97%E7%AC%A6%E4%B8%B2.md#
public class BeautyOfProgram {

    /**
     * 编程之美 3.1
     * 给定两个字符串 s1 和 s2，要求判定 s2 是否能够被 s1 做循环移位得到的字符串包含
     */
    @Test
    public void test1() {
        String str1 = "AABCD";
        String str2 = "CDAA";
        boolean res = isContained(str1, str2);
        System.out.println(res);
    }

    //大神没有给出解答,不晓得自己写的对不对
    public boolean isContained(String str1, String str2) {
        //放一：直接这样好像不太好哈
//        return str1.contains(str2);
        //方二：KMP算法，应该要用到大神说的思想：s1 进行循环移位的结果是 s1s1 的子字符串，因此只要判断 s2 是否是 s1s1 的子字符串即可

        //感觉以前用以上的思想做过类似的题目
        //方三；暴力破解法
        int len = str1.length();
        int i = 0, j = 0;
        while (i < 2 * len && j < str2.length()) {
            char c1 = str1.charAt(i % len);
            char c2 = str2.charAt(j);
            if (c1 == c2) {
                i++;
                j++;
            } else {
                i = i - j + 1; //这里哦，还是看了学数据结构时的记录
                j = 0;
            }
        }
        return j == str2.length();

    }


    /**
     * 编程之美 2.17
     * 字符串循环移位:将字符串向右循环移动 k 位。
     * 网上的解法：
     * https://blog.csdn.net/valdc_morning/article/details/76736662
     * 大神的思路是第二种：
     * 例如:s = "abcd123" k = 3   结果为："123abcd"
     * 将 abcd123 中的 abcd 和 123 单独翻转，得到 dcba321，然后对整个字符串进行翻转，得到 123abcd。
     */
    @Test
    public void test2() {
        String str = "abcd123";
        String res = move(str, 3);
        System.out.println(res);
    }

    // 感觉写法怪累赘的，指69-71
    public String move(String str, int k) {
        int len = str.length();
        if (k > len) {
            return "请输入正确的移动位置";
        }
        char[] array = str.toCharArray();
        array = reverseString(array, 0, str.length() - k - 1);
        array = reverseString(array, str.length() - k, array.length - 1);
        array = reverseString(array, 0, str.length() - 1);
        return String.valueOf(array);

    }

    //该方法中，start和end都包括
    public char[] reverseString(char[] charArray, int start, int end) {
        char tmp = 0;
        for (int i = start, j = end; i < j; i++, j--) {
            tmp = charArray[i];
            charArray[i] = charArray[j];
            charArray[j] = tmp;
        }
        return charArray;
    }

    /**
     * 不是《编程之美中的》
     * 是：程序员代码面试指南
     * 字符串中单词的翻转。例子：
     * s = "I am a student"
     * Return "student a am I"
     * 将每个单词翻转，然后将整个字符串翻转。
     */
    @Test
    public void test3() {
        String s = "I am a student ";
        String res = reverseWord(s);
        System.out.println(res);
    }

    public String reverseWord(String str) {
        //也可以用if判断是否结尾有空格，没有的话就加个空格
        str = str.trim() + " "; //因为如果结尾没有空格的话，下面的for中的if就不通用了
        char[] array = str.toCharArray();
        int j = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == ' ') {
                array = reverseString(array, j, i - 1);
                j = i + 1;
            }
        }
        array = reverseString(array, 0, array.length - 1);
        return String.valueOf(array);
    }
}
