package d202306;

import java.util.*;
import java.io.*;


public class A와B {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String S = bf.readLine();
        String T = bf.readLine();

        while(S.length() < T.length()){
            if(T.charAt(T.length()-1) == 'A'){
                // A 빼기
                T = T.substring(0, T.length()-1);
            }
            else{
                // B를 빼고 + 문자열 뒤집기
                StringBuilder sb = new StringBuilder(T.substring(0, T.length()-1));
                T = sb.reverse().toString();
            }
        }

        if(S.equals(T)){
            System.out.println(1);
        }
        else{
            System.out.println(0);
        }
    }
}
