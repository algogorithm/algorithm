package d202305;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 암호만들기 {
    /*
     * 4 6 a t c i s w
     */
    static int l;
    static char mo [] = {'a','e','i','o','u'};
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        l = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        char abc[] = new char[n];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            abc[i] = st.nextToken().charAt(0);
        }

        //System.out.println(l);
        //System.out.println(n);
        Arrays.sort(abc);
        //System.out.println(Arrays.toString(abc));
        ArrayList<Character> cl = new ArrayList<>();
        dfs(abc, 0, cl);
    }

    private static void dfs(char[] abc, int start, ArrayList<Character> cl) {
        // TODO Auto-generated method stub
        //System.out.println(cl.toString()+" "+ start);
        if(cl.size()==l) {
            int cnt = 0;
            for (int i = 0; i < mo.length; i++) {
                if(cl.contains(mo[i])) {
                    cnt++;
                }
            }
            if(cl.size()-cnt>=2 && cnt>=1) {
                String ans ="";
                for (char tmp: cl) {
                    ans += tmp;
                }
                System.out.println(ans);
            }
            return;
        }
        for (int i = start; i < abc.length; i++) {
            cl.add(abc[i]);
            dfs(abc,i+1,cl);
            cl.remove(cl.size()-1);
        }
    }
}