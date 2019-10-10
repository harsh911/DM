
package decistiontree41;
import java.io.*;
import java.util.*;
public class DecistionTree41 {

    public static void main (String[] args) throws IOException {
        try{
            FileReader file = new FileReader("C:\\Users\\student\\Documents\\NetBeansProjects\\decistionTree41\\src\\decistiontree41\\data.csv");
            BufferedReader br = new BufferedReader(file);
            LinkedList<Shapes> data = new LinkedList<>();
            String s;
            while((s=br.readLine())!=null){
                String[] tmp = (s.split(","));
                data.add(new Shapes(tmp[0],tmp[1],tmp[2],tmp[3],tmp[4]));
            }
            
            data.stream().forEach(d -> System.out.println(d.id +"  "+d.outline+"  "+d.shape));
            System.out.println();
        }
        catch(Exception e){
            System.out.println(e);
        }
        
    }
    
}
class Shapes{
    int id;
    String color;
    String outline;
    String dotted;
    String shape;
    
    Shapes(String s1,String s2,String s3,String s4,String s5){
        this.id = Integer.parseInt(s1);
        this.color = s2;
        this.outline = s3;
        this.dotted = s4;
        this.shape = s5;
    }
}
