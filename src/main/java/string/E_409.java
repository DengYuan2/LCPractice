package string;

// 计算一组字符集合可以组成的回文字符串的最大长度
// https://leetcode-cn.com/problems/longest-palindrome/description/

/**
 * 不小心看到了大神的思路：
 * 使用长度为 256 的整型数组来统计每个字符出现的个数，每个字符有偶数个可以用来构成回文字符串。
 * 因为回文字符串最中间的那个字符可以单独出现，所以如果有单独的字符就把它放到最中间。
 */
public class E_409 {
    public static void main(String[] args) {
        E_409 e = new E_409();
        String s = "abccccdd"; //7
        String s1 = "ccc";
        int res1 = e.longestPalindrome(s1);
        System.out.println(res1);
    }

    //题中说只是包含了大写字母和小写字母，所以我觉得没必要搞256个吧
    //A=65...Z=90....其他非字母的符号...a=97 ......z=122，所以122-65+1=68
    //细节处看的是大神的。。。。，后来又看了一下官方的
    public int longestPalindrome(String s) {
        int[] arr = new int[68]; //官方是128，大神是
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'A']++;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num / 2 * 2; //todo 注意此处的处理方式哦

            //判断有没有奇数个的值，且只能有一个奇数个的值
            //官方的写法；
            if (num % 2 == 1 && sum % 2 == 0) {
                sum++;
            }
        }
        //大神的写法：
        //也可以将上面for循环中的if判断删除，这么做：
//        if (sum < s.length()) {
//            sum++; //todo 巧妙!!! 如此判断是否有奇数个的值
//        }
        return sum;
    }
}
