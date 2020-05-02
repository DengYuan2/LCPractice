package queueAndstack;

import java.util.Stack;

// 括号匹配
// https://leetcode-cn.com/problems/valid-parentheses/
public class E_20 {
    public static void main(String[] args) {
    }

    //我的写法和大神思路相似，只是写法不同，大神的写法比我的简单搞笑
    public boolean isValid1(String s) {
        Stack<Character> stack = new Stack<Character>();
        Character c = ' ';
        for (int i = 0; i < s.length(); i++) {
            c=s.charAt(i);
            if (stack.isEmpty()){
                stack.push(c);
            }else {
                Character peek = stack.peek();
                if (isMatch(peek,c)){
                    stack.pop();
                }else {
                    stack.push(c);
                }

            }
        }
        return stack.isEmpty();
    }

    public boolean isMatch(char c1,char c2){
        if ((c1=='('&&c2==')')||(c1=='['&&c2==']')||(c1=='{'&&c2=='}')){
            return true;
        }
        return false;
    }

    public boolean isValid2(String s){
        Stack<Character> stack = new Stack<Character>();
        for (char c:s.toCharArray()) {
            if (c=='('||c=='['||c=='{'){ //我的方法中，左括号入栈前要比较，而此时直接入栈即可，比我的简单
                stack.push(c);
            }else {
                if (stack.isEmpty()){
                    return false;
                }
                if (!isMatch(stack.pop(),c)){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
