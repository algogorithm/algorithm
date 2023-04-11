package re;

import java.io.BufferedReader;

import java.io.InputStreamReader;


public class zero만들기 {

    static int n = 0;


    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bf.readLine());

        for(int i=0;i<t;i++) {
            n = Integer.parseInt(bf.readLine());
            String c = "1";

            dfs(0,1,1,1,c);

            System.out.println(" ");
        }

    }
    static void dfs(int sum,int oper, int num, int cnt, String c) {
        if(cnt == n) {

            sum = sum + (num * oper);
            if(sum == 0)
                System.out.println(c);
            return ;
        }

        dfs(sum, oper, num*10 + (cnt+1), cnt+1, c + " "+String.valueOf(cnt+1));
        dfs(sum + (oper* num), 1, cnt+1, cnt+1, c + "+" + String.valueOf(cnt+1));
        dfs(sum + (oper*num), -1, cnt+1, cnt+1, c + "-" + String.valueOf(cnt+1));


    }

}
