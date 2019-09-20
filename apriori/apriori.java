package apriori;
import java.util.*;
import java.io.*;

public class apriori {
    public static void main(String []args) throws IOException{
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        
        //System.out.print("Enter minimum support count : ");
        int msc = 2;
        //msc = new Scanner(System.in).nextInt();
        //try{
        FileReader fr = new FileReader("C:\\Users\\Harshad\\Documents\\NetBeansProjects\\DataMining\\src\\apriori\\transactions.txt");
        BufferedReader br = new BufferedReader(fr);
        String s;
        ArrayList<String> tid = new ArrayList();
        ArrayList<String> next = new ArrayList();
        ArrayList test = new ArrayList();
        ArrayList<ArrayList<String>> nexts = new ArrayList();
        Map<String,Integer> item = new HashMap<>();
        Map<ArrayList,Integer> items = new HashMap<>();

        while((s = br.readLine()) != null){
            StringTokenizer st = new StringTokenizer(s," ");
            tid.add(st.nextToken());
            ArrayList<String> tmp = new ArrayList(Arrays.asList(st.nextToken().split(",")));
            Collections.sort(tmp);
            for(String obj:tmp){
                if(!item.containsKey(obj)){
                    item.put(obj,1);
                }
                else{
                    item.put(obj, item.get(obj)+1);
                }
            }
            list.add(tmp);
        }
        System.out.println(list);
       // Collections.sort(item);
        System.out.println(item);
        for(String k: item.keySet()){
            if(item.get(k) >= msc){
                next.add(k);
            }
        }
        item.clear();
        System.out.println(next);
        int sz = next.size();
        Collections.sort(next);
        int n = 2;
        permutate(next,new LinkedList<Integer>(),items,sz-n+1,n,-1);
            
        next.clear();
        System.out.println(items);  
        for(ArrayList k: items.keySet()){
            for(ArrayList j: list){
                if(j.containsAll(k)){
                    //System.out.println(j+ " contains " +k);
                    items.put(k, items.get(k)+1);
                }
                
            }//System.out.println(k);
        }
        System.out.println(items); 
        for(ArrayList k: items.keySet()){
            if(items.get(k) >= msc){
                nexts.add(k);
            }
        }
        System.out.println(nexts); 
        for(ArrayList a : nexts){
            for(Object b : a){
                if(!next.contains(b)) next.add(b.toString());
            }
        }
        System.out.println(next); 
        items.clear();
        sz = next.size();
        /*
        for(int i=0;i<sz;i++){
            for(int j=i;j<sz;j++){
                a.add(next.get(i));
                for(int k=j;k<j+2&&j<sz;k++) a.add(next.get(k));
                
            }
        }
        
        System.out.println(items);*/
        Collections.sort(next);
        n = 4;
        permutate(next,new LinkedList<Integer>(),items,sz-n+1,n,-1);
        System.out.println(items);
        next.clear();
        nexts.clear();
        //System.out.println(items);  
        for(ArrayList k: items.keySet()){
            for(ArrayList j: list){
                if(j.containsAll(k)){
                    //System.out.println(j+ " contains " +k);
                    items.put(k, items.get(k)+1);
                }
                
            }//System.out.println(k);
        }
        System.out.println(items); 
        for(ArrayList k: items.keySet()){
            if(items.get(k) >= msc){
                nexts.add(k);
            }
        }
        System.out.println(nexts); 
        for(ArrayList a : nexts){
            for(Object b : a){
                if(!next.contains(b)) next.add(b.toString());
            }
        }
        System.out.println(next); 
        items.clear();
        sz = next.size();
        /*
        //round 4
        Collections.sort(next);
        n = 4;
        for(int i=0; i<sz-n+1;i++){
            for(int j=i+1;j<sz-n+2;j++){
                for(int k=j+1;k<sz-n+3;k++){
                   for(int l=k+1;l<sz-n+4;l++){
                    ArrayList a = new ArrayList();
                    a.add(next.get(i));
                    a.add(next.get(j));
                    a.add(next.get(k));
                    a.add(next.get(l));
                    items.put(a,0);
                   }
                }
            }
        }
        System.out.println(items);
        next.clear();
        nexts.clear();
        //System.out.println(items);  
        for(ArrayList k: items.keySet()){
            for(ArrayList j: list){
                if(j.containsAll(k)){
                    //System.out.println(j+ " contains " +k);
                    items.put(k, items.get(k)+1);
                }
                
            }//System.out.println(k);
        }
        System.out.println(items); 
        for(ArrayList k: items.keySet()){
            if(items.get(k) >= msc){
                nexts.add(k);
            }
        }
        /*
        items.keySet().stream().filter((k) -> (items.get(k) >= msc)).forEach((k) -> {
            nexts.add(k);
        });
        System.out.println(nexts); 
        for(ArrayList a : nexts){
            for(Object b : a){
                if(!next.contains(b)) next.add(b.toString());
            }
        }
        */
        System.out.println(next); 
                System.out.println("Permutate"); 
        n=4;
        permutate(next,new LinkedList<Integer>(),items,sz-n+1,n,-1);
        System.out.println(items);
        System.out.println("\n\n");
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
//}
       // catch(IOException e){
        //    System.out.print("error :"+e);
       // }
