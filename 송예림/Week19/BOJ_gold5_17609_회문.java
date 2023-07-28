package Baekjoon;

import java.io.*;

public class gold5_17609_회문 {
    static int result = 2;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            String str = br.readLine();
            result = 2;
            search_str(0, str.length()-1, 0, str);

            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }

    private static void search_str(int start, int end, int cnt, String str) {
        if(start > end){
            if(cnt == 0){
                result = 0;
            } else {
                result = 1;
            }
            return;
        }

        if(cnt > 1){
            return;
        }

        if(str.charAt(start) == str.charAt(end)){
            search_str(start+1, end-1, cnt, str);
        } else {
            search_str(start+1, end, cnt+1, str);
            search_str(start, end-1, cnt+1, str);
        }
    }
}
