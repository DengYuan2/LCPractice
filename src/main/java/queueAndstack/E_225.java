package queueAndstack;

import java.util.LinkedList;
import java.util.Queue;

// 用队列实现栈
// https://leetcode-cn.com/problems/implement-stack-using-queues/description/
public class E_225 {
    public static void main(String[] args) {
        MyStack obj = new MyStack();
        obj.push(1);
        int param_2 = obj.pop();
        int param_3 = obj.top();
        boolean param_4 = obj.empty();
        System.out.println(param_2);
        System.out.println(param_3);
        System.out.println(param_4);
    }
}



/**
 * 看了大神的思路；
 * 在将一个元素 x 插入队列时，为了维护原来的后进先出顺序，
 * 需要让 x 插入队列首部。而队列的默认插入顺序是队列尾部，
 * 因此在将 x 插入队列尾部之后，需要让除了 x 之外的所有元素出队列，再入队列。
 *
 * 官方的第三个方法也是如此，其他两个方法的思路也很值得一看哦
 */
class MyStack {
    Queue<Integer> queue;

    public MyStack() {
        queue = new LinkedList<Integer>();
    }

    public void push(int x) {
//        queue.offer(x);
//        while (queue.peek() != x) {
//            queue.offer(queue.poll());
//        }
        //或者这么写
        queue.add(x);
        int size = queue.size();
        while (size>1){
            queue.add(queue.remove());
            size--;
        }


    }

    public int pop() {
        return queue.poll();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
