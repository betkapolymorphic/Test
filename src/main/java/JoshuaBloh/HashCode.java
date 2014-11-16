package JoshuaBloh;

/**
 * Created by Beta on 8/6/14.
 */
public class HashCode {
    static class Test
    {
        int a;
        int b;
    }
    public static void main(String[] args) {
        Test t = new Test();
        System.out.println(t.hashCode());
        System.out.println(t.hashCode());
    }
}
