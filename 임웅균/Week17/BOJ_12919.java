package Week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_12919 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(solution(br.readLine(), br.readLine()) ? 1 : 0);
    }

    private static boolean solution(String s, String t) {
        if(t.length() <= s.length()){
            return t.equals(s);
        }
        if(t.endsWith("A") && solution(s, t.substring(0, t.length()-1))){
            return true;
        }
        else if(t.startsWith("B") && solution(s, new StringBuilder(t.substring(1)).reverse().toString())){
            return true;
        }
        return false;
    }
}
