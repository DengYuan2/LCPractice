package queueAndstack;

import java.util.Stack;

// 用栈实现队列，看起来是用两个栈，不过我写起来还是有问题啊
// https://leetcode-cn.com/problems/implement-queue-using-stacks/description/
public class E_232 {
    public static void main(String[] args) {
        MyQueue obj = new MyQueue();
        obj.push(1);
        obj.push(2);
        int param_2 = obj.pop();
        int param_3 = obj.peek();
        boolean param_4 = obj.empty();
        System.out.println(param_2);
        System.out.println(param_3);
        System.out.println(param_4);
    }
}

// 用的大神的写法，也是官方的第二种方法
// 思路见这里：https://leetcode-cn.com/problems/implement-queue-using-stacks/solution/shi-yong-liang-ge-zhan-yi-ge-zhuan-men-ru-dui-yi-g/
class MyQueue {
    Stack<Integer> in; //来的都先放这里
    Stack<Integer> out; //从in倒到这里

    public MyQueue() {
        in = new Stack<Integer>();
        out = new Stack<Integer>();
    }

    public void push(int x) {
        in.push(x);
    }

    // todo 此处！！！
    public void reverse() {
        //只有out是空的的时候，才会从in中放进去，即；in和out中都可能有数据，不是将in中的数据全部倒进out
        //举个例子，先放了个1，然后peek一下（此时out中有1，in中为空），后来放了个2，又peek了一下（此时
        // out中还是只有1，in中放了2），后来又放进了个3（在in里）；当pop时，out中的1没了，在下次使用peek或pop
        // 的时候，in里才会从上到下是2和3 【这里说的peek和pop都是这个类里的方法】
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }

    }

    public int pop() {
        reverse();
        return out.pop();
    }

    public int peek() {
        reverse();
        return out.peek();
    }

    //结合reverse()中的注释
    public boolean empty() {
        return in.isEmpty()&&out.isEmpty();
    }
}


