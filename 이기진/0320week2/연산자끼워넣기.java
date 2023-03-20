import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 연산자끼워넣기 {
    static int [] num;
    static int max =-987654321;
    static int min = 987654321;
    public static void main(String[] args) throws IOException {
       BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
       int n = Integer.parseInt(bf.readLine());
       StringTokenizer st = new StringTokenizer(bf.readLine());
       num = new int [n];
       for(int i =0; i<n; i++){
           num[i]=Integer.parseInt(st.nextToken());
       }

       st = new StringTokenizer(bf.readLine());
       ArrayList<Integer> al = new ArrayList<>();
       ArrayList<Integer> current = new ArrayList<>();
       int []op= new int[4];
       for(int i=0; i<4; i++){
           op[i]=Integer.parseInt(st.nextToken());

           for(int j = 0; j<op[i]; j++){
               al.add(i);
           }
       }
       //중복 순열
        boolean [] visit = new boolean[al.size()];
       dfs(current,al,visit);
        System.out.println(max);
        System.out.println(min);
    }

    private static void dfs(ArrayList<Integer> current, ArrayList<Integer> al, boolean[] visit) {
        if(current.size() == al.size()){
            int sum = num[0];
            for (int i=0; i<current.size(); i++){
                if(current.get(i)==0){
                    sum = sum+num[i+1];
                }
                else if(current.get(i)==1){
                    sum = sum-num[i+1];
                }
                else if(current.get(i)==2){
                    sum = sum * num[i+1];
                }
                else{
                    sum = sum/num[i+1];
                }
            }
            max = Math.max(sum,max);
            min = Math.min(sum,min);
            return;
        }
        for(int i=0; i<al.size() ; i++){
            if(!visit[i]){
                visit[i]=true;
                current.add(al.get(i));
                dfs(current,al,visit);
                visit[i]=false;
                current.remove(current.size()-1);
            }

        }
    }
}
