
package apriori;
import java.util.*;
public class test {
    public static void main(String[] args){
        ArrayList a = new ArrayList();
        a.add(5);
        a.add(6);
        change(a);
        System.out.print(a);
        change(a);
    }
    public static void change(ArrayList a){
        a.add(7);
        change(a);
    }
}
