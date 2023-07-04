package Baekjoon;

import java.io.*;
import java.util.*;

public class gold4_9935_문자열폭발 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String bomb = br.readLine();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char tmp = str.charAt(i);
            sb.append(tmp);

            // 폭발 문자열 마지막 단어와 같으면 탐색
            if(sb.length() >= bomb.length() && tmp == bomb.charAt(bomb.length()-1)){
                // 폭발 문자열과 같으면 지우기
                if(sb.substring(sb.length()-bomb.length()).equals(bomb)){
//                    System.out.println(sb.substring(sb.length()-bomb.length()));
                    sb.delete(sb.length() - bomb.length(), sb.length());
                }
            }
        }

        if(sb.length() == 0){
            System.out.println("FRULA");
        } else {
            System.out.println(sb);
        }
    }
}
