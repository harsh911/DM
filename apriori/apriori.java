package apriori;
import java.util.*;
import java.io.*;

public class apriori {
    public static void main(String []args) throws IOException{
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        
        //System.out.print("Enter minimum support count : ");
        int msc = 2;
        double confidence = 0.5;
        //msc = new Scanner(System.in).nextInt();
      try{
             FileReader fr = new FileReader("C:\\Users\\Harshad\\Documents\\NetBeansProjects\\DataMining\\src\\apriori\\transactions.txt");
            BufferedReader br = new BufferedReader(fr);
            String s;
            ArrayList<String> tid = new ArrayList();
            ArrayList<String> next = new ArrayList();
            ArrayList<ArrayList<String>> nexts = new ArrayList();
            Object assoc = null;
            
            Map<ArrayList,Integer> items = new HashMap<>();
            
            while((s = br.readLine()) != null){
                StringTokenizer st = new StringTokenizer(s," ");
                tid.add(st.nextToken());
                ArrayList<String> tmp = new ArrayList(Arrays.asList(st.nextToken().split(",")));
                Collections.sort(tmp);
                
                tmp.stream().filter(p ->!next.contains(p)).forEach(m -> next.add(m));
                list.add(tmp);
            }
            System.out.println("Transactions");
            list.forEach(k -> System.out.println(k));
            int sz = next.size();
            Collections.sort(next);
            System.out.println("Distinct items : "+next);

            for(int n=2;!next.isEmpty();n++){
                items.clear();
                nexts.clear();
                
                permute(next,new LinkedList<>(),items,next.size()-n+1,n,-1);
                next.clear();
                items.keySet().stream().forEach( k -> {
                    list.stream().filter(j -> j.containsAll(k)).forEach( m -> items.put(k,items.get(k)+1));
                }); 
                items.keySet().stream().filter(b -> items.get(b) >=msc).forEach(b -> nexts.add(b));
                
                if(!nexts.isEmpty()){
                    next.clear();
                    nexts.stream().forEach( a -> {
                        a.stream().filter(b->!next.contains(b)).forEach( c -> next.add(c));
                    });
                }
                
                if(!next.isEmpty()){
                    System.out.println("Items : " +items);
                    System.out.println("Candidate items: "+nexts);
                    System.out.println("Next items : "+next); 
                    assoc = nexts.clone();
                }
            }
            System.out.println("Next items : "+assoc+"  items: "+items); 
            for(ArrayList l : (ArrayList<ArrayList<String>>)assoc){
                System.out.println(l);
            }
 
        }
        catch(IOException e){
            System.out.print("error :"+e);
        }
    }
    public static void permute(ArrayList next,LinkedList l,Map items,int size,int n,int it){
        for(int i=it+1;i<size;i++){ 
           l.add(i);
           if(n==1){
               ArrayList alist = new ArrayList();  
                l.forEach(k -> alist.add(next.get((int)(k))));
                items.put(alist,0);
            }
           else  permute(next,l,items,size+1,n-1,i);
           l.pollLast();
        } 
    }
}
