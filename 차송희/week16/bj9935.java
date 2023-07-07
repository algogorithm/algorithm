
/*
 * 백준 9935 문자열폭발
 * https://www.acmicpc.net/problem/9935
 * 
 * 
 */

import java.util.Scanner;

public class BJ9935 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 원래문자열
        String str = sc.next();
        // 폭발문자열
        String bomb = sc.next();

        // 스트링빌더
        StringBuilder sb = new StringBuilder();

        // 원래문자열길이만큼 반복하면서
        for (int i = 0; i < str.length(); i++) {
            // 한개씩 sb에 달아조
            char tmp = str.charAt(i);
            sb.append(tmp);

            // sb에 달다가 폭발 문자열길이 이상이 되면
            if (sb.length() >= bomb.length()) {
                boolean fire = true;
                // 폭발문자열길이 인지 검사해
                for (int j = 0; j < bomb.length(); j++) {
                    //
                    char a = sb.charAt(sb.length() - bomb.length() + j);
                    // System.out.print ("a: " + a+" ");
                    char b = bomb.charAt(j);
                    // System.out.println("b: " + b);
                    if (a != b) {
                        fire = false;
                        break;
                    }
                }
                if (fire) {
                    sb.delete(sb.length() - bomb.length(), sb.length());
                }
            }
        }
        if (sb.length() == 0) {
            System.out.println("FRULA");
        } else {
            System.out.println(sb.toString());
        }

    }

}

// -----시간초과 코드
// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;

// public class BJ9935 {
// public static void main(String[] args) throws IOException {
// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

// String origin = br.readLine();
// StringBuffer str = new StringBuffer(origin);

// String bomb = br.readLine();

// int idx = 0;

// // 폭탄 안나올때 까지
// while (idx != -1) {
// idx = str.indexOf(bomb);
// if (idx == -1)
// break;
// // System.out.println("bomb idx: " + idx);
// str.delete(idx, idx + bomb.length());
// // System.out.println("after str: " + str);
// }
// // 폭탄 없음
// if (idx == -1) {
// System.out.println(str);
// }
// }

// }
