package Week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class BOJ_12904 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();
        /*
            문제에서 알려준 두가지 연산은 다음과 같다.
            - 문자열의 뒤에 A를 추가한다.
            - 문자열을 뒤집고 뒤에 B를 추가한다.
            이는 S를 T로 만드는 과정에서의 연산이다.
            하지만 이를 반대로 생각해보면,
            - 문자열의 뒤에 A를 지우기
            - 뒤에 B를 지우고 문자열을 뒤집기
            둘 다 문자열 뒤의 한글자를 지운다는 공통점이 생긴다.
            다음 특징을 활용해서 문제를 접근해보자.
         */
        int result = 0;
        while(T.length() > S.length()){
            char lastChar = T.charAt(T.length()-1);
            // 공통적으로 지워준다.
            T = T.substring(0, T.length()-1);
            // B일 경우만 T를 뒤집어준다.
            if(lastChar == 'B'){
                T = reverse(T);
            }
        }
        if(T.equals(S)){
            result = 1;
        }
            System.out.println(result);
    }

    private static String reverse(String before){
        String after = "";
        char[] array = before.toCharArray();
        for(int i = 0; i < array.length; i++){
            after+=array[array.length-1-i];
        }
        return after;
    }
}
