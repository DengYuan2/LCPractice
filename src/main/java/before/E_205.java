package before;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class E_205 {
    public static void main(String[] args) {
        String s = "accddd";
        String t = "cddeee";
        int count1 = 1;
        int count2 = 1;
        int a[];
        int b[];
        int len1 = s.length();
        int len2 = t.length();
        if (len1 != len2 || len1 == 0 || len2 == 0) System.out.println("异构无疑");
        if (len1 == 0 & len2 == 0) System.out.println("同构");
        a = new int[len1];
        b = new int[len2];
        a[0] = 1;
        b[0] = 1;
        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();
        for (int i = 0; i < len1; i++) {
            if (a[i] == 0) a[i] = ++count1;
            if (b[i] == 0) b[i] = ++count2;
            for (int j = i + 1; j < len1; j++) {
                if (chars1[i] == chars1[j])
                    a[j] = a[i];
                if (chars2[i] == chars2[j])
                    b[j] = b[i];
            }
            if (a[i] != b[i]) {
                System.out.println("此处可知异构: " + i);
//                    System.out.println(Arrays.toString(a));
//                    System.out.println(Arrays.toString(b));
                return;
            }
        }
        System.out.println("同构呀");


    }

    //别人的写法1：
    //思路：因为indexOf会返回遍历这个字符串的遇到一个第一个指定值的下标，所以判断两个下标是否一样即可
    // 注意：用indexof查找字符串有O(n)复杂度，代码看着简单但是整体复杂度达O(n*n)
    @Test
    public void test1() {
        String s = "accddd";
        String t = "cddeee";
        if (s.length()!=t.length()) System.out.println("绝对异构");

        List<Integer> ss = new ArrayList<Integer>();
        List<Integer> tt = new ArrayList<Integer>();
        for (int i = 0; i < s.length(); i++) {
            ss.add(s.indexOf(s.substring(i,i+1)));
            tt.add(t.indexOf(t.substring(i,i+1)));
            if (ss.get(i)!=tt.get(i)) System.out.println("异构");

        }
        System.out.println("同构");

    }

    //别人的写法2：与1思路相同，但更为简洁,根本没必要用list
    @Test
    public void test2(){
        long start = System.currentTimeMillis();
        long end;
        String s = "abbccdefghhijkllmnnabbccdefghhijkllmnnabbccdefghhijkllmnnabbccdefghhijkllmnnabbccdefghhijkllmnnabbccdefghhijkllmnnabbccdefghhijkllmnnabbccdefghhijkllmnnabbccdefghhijkllmnnabbccdefghhijkllmnnabbccdefghhijkllmnn";
        String t = "abbccdefghhijkllmnnabbccdefghhijkllmnnabbccdefghhijkllmnnabbccdefghhijkllmnnabbccdefghhijkllmnnabbccdefghhijkllmnnabbccdefghhijkllmnnabbccdefghhijkllmnnabbccdefghhijkllmnnabbccdefghhijkllmnnabbccdefghhijkllmnn";
        if (s.length()!=t.length()) {
            System.out.println("绝对异构");//return false;
            return;
        }
        for (int i = 0; i <s.length() ; i++) {
            if (s.indexOf(s.substring(i,i+1))!=t.indexOf(t.substring(i,i+1))) {
                System.out.println("异构");//return true;
                return;
            }
        }
        System.out.println("同构");//return true;
    }


}
