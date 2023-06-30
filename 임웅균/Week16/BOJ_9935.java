package Week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 문자열의 길이는 1보다 크거나 같고, 1,000,000보다 작거나 같다
        char[] word = br.readLine().toCharArray();
        // 길이는 1보다 크거나 같고, 36보다 작거나 같다.
        char[] boom = br.readLine().toCharArray();
        StringBuilder sb = new StringBuilder();
        // word의 문자열 처음부터 넣어가며 확인해보자
        for(char curr : word){
            // 우선 현재 문자열을 sb안에 넣어준다.
            sb.append(curr);
            // 현재 문자가 boom의 끝문자와 같고, 문자열의 길이가 boom의 길이 이상이면 탐색
            if(curr == boom[boom.length-1] && sb.length() >= boom.length){
                // 폭발문자열인지 확인하기 위한 변수 생성
                boolean isBoom = true;
                // 폭발문자열 길이만큼 탐색하기 위한 반복문
                for(int j = 0; j < boom.length -1; j++){
                    // 폭발 문자열의 처음부터 끝까지를 sb에 해당하는 index의 값과 비교한 후
                    // 다른 값이 나온다면 false처리
                    if(sb.charAt(sb.length() - boom.length + j) != boom[j]){
                        isBoom = false;
                        break;
                    }
                }
                // 위에서 폭발문자열이란걸 확인했다면?
                if(isBoom){
                    // 그 만큼의 문자열을 지워준다.
                    sb.delete(sb.length() - boom.length, sb.length());
                }
            }
        }

        System.out.println(sb.length() > 0 ? sb.toString() : "FRULA");
    }
}
