import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 거짓말 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int parent[] = new int [n+1];
        for (int i = 1; i < parent.length; i++) {
            parent[i]=i;
        }
        st = new StringTokenizer(bf.readLine());
        int k = Integer.parseInt(st.nextToken());
        int arr[] = new int [k];
        for (int i = 0; i < k; i++) {
            arr[i]=Integer.parseInt(st.nextToken());
        }

        //System.out.println(Arrays.toString(arr));

        ArrayList<int[]> al = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st= new StringTokenizer(bf.readLine());
            int pn = Integer.parseInt(st.nextToken());
            int tmp[] = new int [pn];
            //int min = 987654321;
            for (int j = 0; j < tmp.length; j++) {
                tmp[j]=Integer.parseInt(st.nextToken());
                //min=Math.min(min, tmp[j]);
                if(j==0) {
                    continue;
                }
                unionParent(tmp[0], tmp[j], parent);
            }

            al.add(tmp);

        }
        int ans =0;
        for (int i = 0; i < al.size(); i++) {
            int tmp[] = al.get(i);
            int flag= 0;
            for (int j = 0; j < arr.length; j++) {
                if(!check(arr[j],tmp, parent)) {
                    flag=1;
                    break;
                }
            }
            if(flag==1) {
                continue;
            }
            else {

                //System.out.println(Arrays.toString(tmp));
                ans++;
            }

        }

        //System.out.println(Arrays.toString(parent));
        System.out.println(ans);




    }
    private static boolean check(int i, int[] tmp,int [] parent) {
        // TODO Auto-generated method stub
        int a = getParent(i, parent);
        for (int j = 0; j < tmp.length; j++) {
            if(a==getParent(tmp[j], parent)) {
                return false;
            }
        }
        return true;
    }
    static int getParent(int x,int parent[]) {
        if(x==parent[x]) {
            return x;
        }
        else {
            return parent[x]=getParent(parent[x], parent);
        }
    }

    static public void unionParent(int x, int y,int parent[]){
        x = getParent(x, parent);
        y = getParent(y, parent);
        if(x>y) {
            parent[x]=y;
        }
        else {
            parent[y]=x;
        }
    }
}