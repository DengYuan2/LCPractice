package linkedList;

//分隔链表 https://leetcode-cn.com/problems/split-linked-list-in-parts/description/
public class M_725 {
    public static void main(String[] args) {
        M_725 m = new M_725();
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        ListNode n7 = new ListNode(7);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        ListNode[] listNodes = m.splitListToParts(n1, 4);
        for (ListNode node : listNodes) {
            System.out.println(node);
        }
    }

    //我的想法，确定每组的个数，然后根据个数分好组
    //跟大神的思路一致，写的有点差别，但不大，就不把他的拿过来了
    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] listNodes = new ListNode[k];
        ListNode cur = root;
        //获得链表长度
        int len = 0;
        while (cur != null) {
            len++;
            cur = cur.next;
        }

        cur = root;
        ListNode temp = null;
        int index = 0; //记录数组下标
        int count = len / k; //每组count个，也有可能是0个
        int extra = len % k; //不能平均分时前extra组多分一个 2
        int plus = 0; //
        while (cur != null) {
            listNodes[index++] = cur;
            plus = extra > 0 ? 1 : 0; //确定该组是否要多放一个
            for (int i = 1; i < count + plus; i++) {
                cur = cur.next; //结束时是这一组的最后一个
            }
            temp = cur.next;
            cur.next = null; //将这一组的最后一个的指针指向null
            cur = temp; //放到下一组的第一个
            extra--;
        }
        //应对k>len的情况，额，它本来就是null啊，真是多此一举
//        while (index < k) {
//            listNodes[index++] = null;
//        }
        return listNodes;
    }
}


