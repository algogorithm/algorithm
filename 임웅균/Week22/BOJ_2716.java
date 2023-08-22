package Week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class BOJ_2716 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++){
            System.out.println(countMonkey(br.readLine()));
        }
    }

    private static int countMonkey(String s) {
        int result = 1;
        int depth = 0;
        int[] monkeyCnt = new int[150];
        // 빈 문자열일경우 무조건 한마리만 가능함.
        if(s.isEmpty()){
            return result;
        }
        // 문자열을 확인하며 괄호를 기준으로 트리의 뎁스를 +-해주며 원숭이 카운트
        for(char c : s.toCharArray()){
            // 덩굴이 나눠지는 지점을 나타내므로 깊이를 1 증가
            if(c == '['){
                //
                monkeyCnt[depth++]++;
            }
            // 덩굴이 합쳐지는 지점이므로 깊이를 1 감소
            else{
                depth--;
            }
        }
        // 각 깊이에서의 원숭이 수를 합산
        for(int i = 0; i < monkeyCnt.length; i++){
            // 원숭이 수가 0이면 그 이후는 확인 안함.
            if(monkeyCnt[i] == 0){
                break;
            }
            result *= 2;
        }
        return result;
    }
}
