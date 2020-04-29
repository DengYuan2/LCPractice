package queueAndstack;

import java.util.Stack;

//
//
public class E_115 {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin();   // -3.
        minStack.pop();
        minStack.top();      // 0.
        minStack.getMin();   //-2.


    }
}

//看了大神的思路,属于数据栈与辅助栈同步的做法
class MinStack {
    Stack<Integer> minStack;
    Stack<Integer> dataStack;
    int min;
    public MinStack() {
        minStack = new Stack<Integer>(); //存放每次的最小值
        dataStack = new Stack<Integer>(); //存放数据
        min=Integer.MAX_VALUE;
    }

    public void push(int x) {
        //大神写法
//        min=Math.min(min,x);
//        dataStack.push(x);
//        minStack.push(min);

        //官方没有用min这个变量;按照这种方式，下面用到min的都可以修改，不过我没改
        dataStack.push(x);
        if (minStack.isEmpty()||x<=minStack.peek()){
            minStack.push(x);
        }else {
            minStack.add(minStack.peek());
        }

    }

    public void pop() {
        dataStack.pop();
        minStack.pop();
        min=minStack.isEmpty()?Integer.MAX_VALUE:minStack.peek();
    }

    public int top() {
        return dataStack.peek();
    }

    public int getMin() {
        return min;
    }

// *************************************************************************************
//栈与辅助栈不同步：思路；https://leetcode-cn.com/problems/min-stack/solution/shi-yong-fu-zhu-zhan-tong-bu-he-bu-tong-bu-python-/
    //上面的同步方法其实就挺好的，便于理解
    public void push2(int x){
        dataStack.push(x);
        if (minStack.isEmpty()||minStack.peek()>=x){ //此处必须有=
            minStack.push(x);
        }
    }

    public void pop2(){
        if (!dataStack.isEmpty()){
            Integer pop = dataStack.pop();
            if (pop==minStack.peek()){
                minStack.pop();
            }
        }

    }
}
