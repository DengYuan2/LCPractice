import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Test1 {
    @Test
    public void test1(){
        String str="abc";
        for (int i = 0; i <=str.length() ; i++) {
            System.out.println(str.substring(i) + "*******");
            if (str.substring(i).length()==0){
                System.out.println("欸呦喂");
            }
        }
    }

    @Test
    public void test2(){
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(1,1);
        map.put(2,3);
        map.put(3,4);
        Integer res = map.get(4);
        System.out.println(res);

    }
}
