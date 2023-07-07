package d202307;

import java.util.*;
import java.io.*;
public class 문자열폭발 {
    public static void main(String[] args) throws Exception{
        BufferedReader bf =new BufferedReader(new InputStreamReader(System.in));
        String first = bf.readLine();
        String target = bf.readLine();

        Stack<Character> al = new Stack<Character>();
        for (int i =0; i<first.length(); i++) {
            al.push(first.charAt(i));
            //match 인지 확인하기
            if (al.size() >= target.length()) {
                boolean match = false;
                int len = target.length() - 1;
                for (int j = al.size() - 1; j >= 0; j--) {
                    if (target.charAt(len) != al.get(j)) {
                        break;
                    } else {
                        len--;
                        if (len == -1) {
                            match = true;
                            break;
                        }
                    }
                }
                if (match) {
                    for (int j = 0; j < target.length(); j++) {
                        al.pop();
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!al.isEmpty()) {
            sb.append(al.pop());
        }
        System.out.println(sb.length() == 0 ? "FRULA" : sb.reverse().toString());

    }
}
