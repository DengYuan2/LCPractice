package linkedList;


import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import java.util.Stack;

//链表求和2,不改变原链表结构 https://leetcode-cn.com/problems/add-two-numbers-ii/description/
//链表求和1:逆序排的 M_2
public class M_445 {
    public static void main(String[] args) {
        ListNode n1 = new ListNode(5);
        ListNode n2 = new ListNode(6);
        ListNode n3 = new ListNode(4);
        ListNode n4 = new ListNode(7);
        ListNode n5 = new ListNode(2);
        ListNode n6 = new ListNode(4);
        ListNode n7 = new ListNode(3);
        n1.next = n2;
        n2.next = n3;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        // 24+9976
        ListNode listNode = addTwoNumbers(n1, n4);
        System.out.println(listNode);
        ListNode numbers2 = addTwoNumbers2(n1, n4);
        System.out.println(numbers2);
        ListNode numbers3 = addTwoNumbers3(n1, n4);
        System.out.println(numbers3);

    }

    //自己实在想不出，先看了一下别人的思路，自己写的
    //方法一：补齐长度，递归（递归的方法在下面）
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //题目中已说是两个非空链表哦
        //因为要补齐长度，而要补的0应该放在最前面，故增加一个哑节点
        ListNode h1 = new ListNode(-1);
        ListNode h2 = new ListNode(-1);
        h1.next = l1;
        h2.next = l2;
        //补齐长度(也可以先得到两链表的长度，再在短链上补长度差)
        while (l1.next != null && l2.next != null) {
            l1 = l1.next;
            l2 = l2.next;
        }
        //如果是l2短，补l2
        while (l1.next != null) {
            l1 = l1.next;
            ListNode node = new ListNode(0);
            node.next = h2.next;
            h2.next = node;
        }
        //如果是l1短，补l1
        while (l2.next != null) {
            l2 = l2.next;
            ListNode node = new ListNode(0);
            node.next = h1.next;
            h1.next = node;
        }
        ListNode res = new ListNode(0);
        temp(h1.next, h2.next, res); //将进位放在哑节点上,这样递归传递时只用传递这个节点即可，不用费心再去传一个进位
        //处理最后要进位的情况
        if (res.val == 1) {
            ListNode listNode = new ListNode(1);
            listNode.next = res.next;
            res.next = listNode;
        }
        return res.next;
    }

    //长度一致，为了从后往前计算，进行递归,该写法其实有点参考了反转链表的递归写法：先找到最后一个，再不断往前走
    public static ListNode temp(ListNode l1, ListNode l2, ListNode res) {
        if (l1 == null) {
            return res;
        }
        res = temp(l1.next, l2.next, res);
        int value = l1.val + l2.val + res.val;
        res.val = value / 10; //将进位放在哑节点上
        ListNode node = new ListNode(value % 10);
        node.next = res.next;
        res.next = node;
        return res;
    }

    //方法二，使用栈,更为简便
    //其实就是通过栈使题目变成了链表求和1
    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        //放入栈中
        Stack<ListNode> nodes1 = new Stack<ListNode>();
        Stack<ListNode> nodes2 = new Stack<ListNode>();
        while (l1 != null) {
            nodes1.push(l1);
            l1 = l1.next;
        }
        while (l2 != null) {
            nodes2.push(l2);
            l2 = l2.next;
        }

        int v1 = 0;
        int v2 = 0;
        int sum = 0;
        int tail = 0;
        ListNode res = new ListNode(-1);
        while (nodes1.size() > 0 || nodes2.size() > 0) {
            v1 = nodes1.size() > 0 ? nodes1.pop().val : 0;
            v2 = nodes2.size() > 0 ? nodes2.pop().val : 0;
            sum = v1 + v2 + tail;
            tail = sum / 10;
            //放入结果链表的第一个位置
            ListNode listNode = new ListNode(sum % 10);
            listNode.next = res.next;
            res.next = listNode;
        }
        if (tail == 1) {
            ListNode node = new ListNode(1);
            node.next = res.next;
            res.next = node;
        }

        return res.next;
    }

    //大神(github上)对方法二的写法：栈中直接存放数字，并且不单独判断最后的carry
    public static ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = buildStack(l1);
        Stack<Integer> stack2 = buildStack(l2);
        ListNode res = new ListNode(-1);
        int sum = 0;
        int carry = 0;
        int v1 = 0;
        int v2 = 0;
        while (stack1.size() > 0 || stack2.size() > 0 || carry == 1) {
            v1 = stack1.size() > 0 ? stack1.pop() : 0;
            v2 = stack2.size() > 0 ? stack2.pop() : 0;
            sum = v1 + v2 + carry;
            carry = sum / 10;
            ListNode listNode = new ListNode(sum % 10);
            listNode.next = res.next;
            res.next = listNode;
        }
        return res.next;
    }

    public static Stack<Integer> buildStack(ListNode node) {
        Stack<Integer> stack = new Stack<Integer>();
        while (node != null) {
            stack.push(node.val);
            node = node.next;
        }
        return stack;
    }


}

