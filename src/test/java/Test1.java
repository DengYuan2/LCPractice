import org.junit.Test;

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
}
