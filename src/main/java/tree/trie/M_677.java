package tree.trie;

// 键值映射，方法为：用前缀树求前缀和
// https://leetcode-cn.com/problems/map-sum-pairs/description/
public class M_677 {
    public static void main(String[] args) {
        M_677 m = new M_677();
        MapSum obj = new MapSum();
        obj.insert("aa",3);
        obj.insert("aa",2);
        obj.insert("aaa",3);
        obj.insert("aewfwaefjeoawefjwoeajfowajfoewajfoawefjeowajfowaj",111);
//        obj.insert("aa",3);
//        int param_2 = obj.sum("ap");
//        System.out.println(param_2);
//        obj.insert("app",2);
        System.out.println(obj.sum("a"));

        //["MapSum", "insert", "sum", "insert", "sum", "sum", "insert", "sum", "sum", "sum", "insert", "sum", "sum"]
        //[[], ["aa",3], ["a"], ["aa",2], ["a"], ["aa"], ["aaa", 3], ["aaa"], ["bbb"], ["ccc"], ["aewfwaefjeoawefjwoeajfowajfoewajfoawefjeowajfowaj", 111], ["aa"], ["a"]]
    }


}



//改写一个前缀树【按照M_208的官方迭代方法，非大神的写法】
class MapSum{
    Nodes root;
    int curIndex;

    public MapSum() {
        root=new Nodes(0);
        curIndex = 0;
    }


    public void insert(String word, int val){
        insert(root,word,val);
    }

    private void insert(Nodes nodes, String word,int val) {
        for (int i = 0; i < word.length(); i++) {
            curIndex=getCurIndex(word.charAt(i));
            int value = i==word.length()-1?val:0;
            if (nodes.child[curIndex]==null){
                nodes.child[curIndex]=new Nodes(value);
            }else {
                if (value!=0){ // !!! 比如：原先“aa,2”,后来插入“aaa,3”，则原来的2不应该改为0
                    nodes.child[curIndex].val=value;
                }
            }
            nodes=nodes.child[curIndex];
        }
        nodes.isLeaf=true;
    }
    public int sum(String prefix){
        Nodes node = search(prefix, root);
        if (node==null){
            return 0;
        }
        return sums(node,0);
    }

    public int sums(Nodes nodes,int sum){
        sum+=nodes.val;
        for (int i = 0; i < 26; i++) {

            if (nodes.child[i]!=null){
                sum+=sums(nodes.child[i],0); //注意第2个参数为0哦，不是sum啊
            }
        }
        return sum;
    }

    public boolean search(String word){
        Nodes nodes = search(word,root);
        return nodes!=null&&nodes.isLeaf;
    }

    private Nodes search(String word, Nodes nodes) {
        for (int i = 0; i < word.length(); i++) {
            curIndex=getCurIndex(word.charAt(i));
            if (nodes.child[curIndex]!=null){
                nodes=nodes.child[curIndex];
            }else {
                return null;
            }

        }
        return nodes;
    }

    public boolean startsWith(String prefix){
        Nodes nodes = search(prefix,root);
        return nodes!=null;
    }



    private int getCurIndex(char c){
        return c-'a';
    }

}

//前缀树的节点
class Nodes{
    Nodes[] child= new Nodes[26];
    boolean isLeaf;
    int val;

    public Nodes() {
    }

    public Nodes(int val) {
        this.val = val;
    }
}
