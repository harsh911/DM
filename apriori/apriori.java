package apriori;
import java.util.*;
import java.io.*;

public class Apriori {
    public static void main(String []args) throws IOException{
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        
        //System.out.print("Enter minimum support count : ");
        int msc = 1;
        //msc = new Scanner(System.in).nextInt();
      try{
            FileReader fr = new FileReader("/home/student/NetBeansProjects/apriori/src/apriori/transactions.txt");
            BufferedReader br = new BufferedReader(fr);
            String s;
            ArrayList<String> tid = new ArrayList();
            ArrayList<String> next = new ArrayList();
            ArrayList<ArrayList<String>> nexts = new ArrayList();
            Map<ArrayList,Integer> items = new HashMap<>();

            while((s = br.readLine()) != null){
                StringTokenizer st = new StringTokenizer(s," ");
                tid.add(st.nextToken());
                ArrayList<String> tmp = new ArrayList(Arrays.asList(st.nextToken().split(",")));
                Collections.sort(tmp);
                  //System.out.println(tmp)  ; 
                for(String obj:tmp){
                    if(!next.contains(obj)){
                        next.add(obj);
                    }

                }
                list.add(tmp);
            }
            System.out.println("Transactions");
            list.forEach(k -> System.out.println(k));
            int sz = next.size();
            Collections.sort(next);
            System.out.println("\nDistinct items : "+next);

            for(int n=2;!next.isEmpty();n++){
                permutate(next,new LinkedList<Integer>(),items,sz-n+1,n,-1);
            System.out.println("\n\n")  ;  
            next.clear();
            System.out.println(items);  
            for(ArrayList k: items.keySet()){
                for(ArrayList j: list){
                    if(j.containsAll(k)){
                        items.put(k, items.get(k)+1);
                    }
                }
            }
            System.out.println("Items : " +items); 
            for(ArrayList k: items.keySet()){
                if(items.get(k) >= msc){
                    nexts.add(k);
                }
            }
            System.out.println("Candidate items:");
            nexts.forEach(k -> System.out.println(k));
            for(ArrayList a : nexts){
                for(Object b : a){
                    if(!next.contains(b)) next.add(b.toString());
                }
            }
            System.out.println("\nNext items : "+next); 
            items.clear();
            nexts.clear();
            sz = next.size();
            }
        }
        catch(IOException e){
            System.out.print("error :"+e);
        }
    }
    public static void permutate(ArrayList next,LinkedList l,Map items,int size,int n,int it){
        for(int i=it+1;i<size;i++){ 
           l.add(i);
           if(n==1){
               ArrayList alist = new ArrayList();  
                l.forEach(k -> alist.add(next.get((int)(k))));
                items.put(alist,0);
            }
           else
            permutate(next,l,items,size+1,n-1,i);
           l.pollLast();
        } 
    }
}
