package string;

import java.util.Collection;
import java.util.HashMap;

// 同构字符串
// https://leetcode-cn.com/problems/isomorphic-strings/description/
public class E_205 {
    public static void main(String[] args) {
        E_205 e = new E_205();
        String s = "egg";
        String t = "add";
        String s1 = "foo";
        String t1 = "bar";
        String s2 = "paper";
        String t2 = "title";
        String s3 = "ab";
        String t3 = "aa";
        boolean res1 = e.isIsomorphic(s3, t3);
        System.out.println(res1);
        boolean res2 = e.isIsomorphic2(s, t);
        System.out.println(res2);
    }

    //我的写法：
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        HashMap<Character, Character> map = new HashMap<>();
        char[] cs = s.toCharArray();
        char[] ct = t.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            Character ca = map.get(cs[i]);
            if (ca == null) {
                //也可以不用下面这个if，而是再重写一个方法，返回isIsomorphic(s,t)&&isIsomorphic(t,s)。这样就可以两面检查了
                if (map.values().contains(ct[i])) { //针对s3和t3的情况啊，要求一个字母只能对应一个字母，反之也是啊
                    return false;
                }
                map.put(cs[i], ct[i]);
            } else {
                if (!(ca == ct[i])) {
                    return false;
                }
            }

        }
        return true;

    }

    //大神的写法，很简单啊，没我的那么复杂，而且直接解决了两者一一对应的情况，不用像我上面那样处理。因为map只能保证key唯一不重复
    public boolean isIsomorphic2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] is = new int[256];
        int[] it = new int[256];
        for (int i = 0; i < s.length(); i++) {
            char cs = s.charAt(i);
            char ct = t.charAt(i);
            if (is[cs] != it[ct]) {
                return false;
            }
            is[cs] = i + 1;
            it[ct] = i + 1;
        }
        return true;
    }
}
