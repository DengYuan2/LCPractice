package tree.trie;

//前缀树：以前没有接触过，遂直接跟着大神的写法过一遍
// 思路看：https://blog.csdn.net/weixin_40374341/article/details/94028364?depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-3&utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-3
// 官方题解文档值得一看，更容易理解啊：https://leetcode-cn.com/problems/implement-trie-prefix-tree/solution/shi-xian-trie-qian-zhui-shu-by-leetcode/
public class Trie {

    private class Node {
        Node[] childs = new Node[26];
        boolean isLeaf;
    }

    Node root = new Node();

    public void insert(String word) {
        insert(word, root);
    }

    private void insert(String word, Node node) {
        if (node == null) {
            return;
        }
        if (word.length() == 0) {
            node.isLeaf = true;
            return;
        }
        int index = getIndex(word.charAt(0));
        if (node.childs[index] == null) {
            node.childs[index] = new Node();
        }
        //最后一次的substring得到的为空字符串哦
        insert(word.substring(1), node.childs[index]);
    }

    private int getIndex(char ch) {
        return ch - 'a';
    }

    public boolean search(String word) {
        return search(word, root);
    }

    //search方法需要理解
    private boolean search(String word, Node node) {
        if (node==null){
            return false;
        }
        if (word.length()==0){
            return node.isLeaf;
        }
        int index = getIndex(word.charAt(0));
        return search(word.substring(1),node.childs[index]);
    }

    public boolean startsWith(String prefix){
        return startsWith(prefix,root);
    }

    //同search方法一个道理
    private boolean startsWith(String prefix, Node node) {
        if (node==null){
            return false;
        }
        if (prefix.length()==0){
            return true;
        }
        int index=getIndex(prefix.charAt(0));
        return startsWith(prefix.substring(1),node.childs[index]);
    }

    //官方都是用的迭代法，且更好理解哦
}
