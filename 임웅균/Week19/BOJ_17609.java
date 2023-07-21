package Week19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_17609 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        String word;
        StringBuilder sb = new StringBuilder();
        int answer = 0;
        char startChar, endChar;

        for(int t = 0; t < T; t++){
            answer = 0;
            word = br.readLine();
            int s = 0, e = word.length()-1;
            while(s < e){
                startChar = word.charAt(s);
                endChar = word.charAt(e);
                // 회문이 아닌경우, 유사회문인지 확인하기 위한 조건문
                if(startChar != endChar){
                    // 앞에를 지울때
                    if(word.charAt(s+1) == endChar && isPalindrome(word, s+1, e)){
                        answer = 1;
                        break;
                    }
                    // 뒤에를 지울때
                    else if(startChar == word.charAt(e-1) && isPalindrome(word, s, e-1)){
                        answer = 1;
                        break;
                    }
                    // 두 조건을 모두 만족하지 못하면 유사회문.
                    else{
                        answer = 2;
                        break;
                    }
                }
                s++;
                e--;
            }
            // 문자열 그 자체로 회문이면 0, 유사회문이면 1, 그 외는 2를 출력
            sb.append(answer+"\n");
        }
        System.out.println(sb);
    }


    private static boolean isPalindrome(String word, int s, int e) {
        while(s < e){
            if(word.charAt(s++) != word.charAt(e--)){
                return false;
            }
        }
        return true;
    }
}
