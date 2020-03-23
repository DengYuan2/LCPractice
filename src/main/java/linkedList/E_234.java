package linkedList;

import java.util.Arrays;

//判断一个链表是否为回文链表【回文就是反转以后和以前一样的就是回文结构，例如 1->2->3->2->1】
//要求：用 O(n) 时间复杂度和 O(1) 空间复杂度
// https://leetcode-cn.com/problems/palindrome-linked-list/description/
public class E_234 {
    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(2);
        ListNode n5 = new ListNode(1);
        ListNode n6 = new ListNode(1);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
//        n5.next = n6;
//        boolean palindrome = isPalindrome(n1);
//        System.out.println(palindrome);

//        System.out.println(isPalindrome2(n1));
        E_234 e = new E_234();
//        boolean palindrome3 = e.isPalindrome3(n1);
//        System.out.println(palindrome3);
        System.out.println(e.isPalindrome4(n1));

    }

    //最开始的思路：遍历链表，将数据存到数组中，再用两个指针（一头一尾）两两比较；但空间复杂度不满足要求
    //我的思路1：将链表反转，再一个个对比
    // 但该思路1有问题：新的链表将节点抢去了，原链表的结构已经被破坏了，没法比较
    //我的思路2：只反转后半部分的节点，然后将后半部分已反转的节点和前半部分比较
    public static boolean isPalindrome(ListNode head) {

        if (head == null || head.next == null) { //注：空的也是回文链表
            return true;
        }
        //获得节点数，确定中点的位置
        int count = 0;
        ListNode cur = head;
        while (cur != null) {
            count++;
            cur = cur.next;
        }

        cur = head;
        for (int i = 0; i < count / 2; i++) {
            cur = cur.next; //如果count为偶数，则直接到达第二部分的开头,此时第一部分的最后一个节点也是该开头，而且比较时前半部分也多这一个顶点
        }
        //如果count为奇数,cur还有再往后移一位才行
        if (!(count % 2 == 0)) {
            cur = cur.next;
        }
        //反转后半部分
        ListNode node = new ListNode(-1);
        ListNode temp = cur;
        while (cur != null) {
            temp = cur.next;
            cur.next = node.next;
            node.next = cur;
            cur = temp;
        }

        //比较前后两部分，只要比较前count/2个节点即可 【注：前后部分长度并不一定一致，可能相差1-2各节点】
        cur = head;
        temp = node.next;
        int c = 1;
        while (c <= count / 2) {
            if (cur.val != temp.val) {
                return false;
            }
            cur = cur.next;
            temp = temp.next;
            c++;
        }
        return true;
    }

    //大神的思路和我的思路2一致，但其中的步骤有很多不同之处，尤其是用快慢指针分割链表的方式
    //下面是我先看了一遍大神的过程，再自己写的，并不跟大神的完全一致，大神的写法在下面
    public static boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        //todo 从未接触过的东西：快慢指针！！！
        //慢指针每次一步，快指针每次两步
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        //如果是偶数个的话，会因为fast.next==null跳出循环，此时slow指在第一部分的最后一个；
        //如果是奇数个的话，会因为fast==null跳出上面的循环，此时slow指在中间的部分
        ListNode rear = slow.next; //第二部分的开头

        //反转第二部分，跟普通的反转方法有点不一样，直接把每个节点都当作头节点
        ListNode two = null; //
        ListNode temp = null;
        while (rear != null) {
            temp = rear.next;
            rear.next = two; //将新的节点放在第一个，当作头节点
            two = rear;
            rear = temp;
        } //第二部分的头节点就是two

        //比较两部分的链表，以第二个链表的长度为停止标准，因为第一部分的链表可能过长，而第二部分的链表无论原链表的奇偶都是要比较的长度
        while (two != null) {
            if (two.val != head.val) {
                return false;
            }
            two = two.next;
            head = head.next;
        }

        //另外：如果不想破坏原来链表的结构，就要恢复链表：把第二部分再反转一次，再用slow指向这部分

        return true;

    }

    //大神写法，主要是分割的方式不一样
    public boolean isPalindrome3(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) slow = slow.next;  //偶数节点，让 slow 指向下一个节点 是为了让前半部分是完全要比较的节点，见cut方法
        cut(head, slow);                     // 切成两个链表
        return isEqual(head, reverse(slow));
    }

    private void cut(ListNode head, ListNode cutNode) { //让前半部分是完全要比较的节点
        while (head.next != cutNode) {
            head = head.next;
        }
        head.next = null;
    }

    private ListNode reverse(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode nextNode = head.next;
            head.next = newHead;
            newHead = head;
            head = nextNode;
        }
        return newHead;
    }

    private boolean isEqual(ListNode l1, ListNode l2) {
        while (l1 != null && l2 != null) {
            if (l1.val != l2.val) return false;
            l1 = l1.next;
            l2 = l2.next;
        }
        return true;
    }

    //官方分析的递归法，并特别之处递归方法不满足空间复杂度为O(1),而是O(n)
    //对内存的分析值得好好看一看(跟自己以为的不一样)：https://leetcode-cn.com/problems/palindrome-linked-list/solution/hui-wen-lian-biao-by-leetcode/
    public ListNode front = null;

    public boolean isPalindrome4(ListNode head) {
        front = head;

        return tail(head);
    }
    //这递归厉害
    public boolean tail(ListNode node) {
        if (node!=null){
            if (!tail(node.next)){
                return false;
            }
            if (node.val!=front.val){
                return false;
            }
            front=front.next;
        }
        return true;
    }
}
