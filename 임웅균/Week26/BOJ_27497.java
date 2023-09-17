package Week26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_27497 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        Deque<Character> deque = new ArrayDeque<>();
        Stack<Integer> stack = new Stack<>();
        for(int n = 0; n < N; n++){
            st = new StringTokenizer(br.readLine());
            if(st.countTokens() == 1){
                // 무조건 3임
                if(!stack.empty()) {
                    if(stack.pop() == 1) {
                        // 맨 뒤 문자 제거
                        deque.pollLast();
                    }
                    else {
                        // 맨 앞 문자 제거
                        deque.pollFirst();
                    }
                }
            }
            else{
                // 문자열 맨 뒤에 c가 적힌 블록 추가
                stack.push(Integer.parseInt(st.nextToken()));
                char c = st.nextToken().charAt(0);
                if(stack.peek() == 1){
                    deque.addLast(c);
                }
                else{
                    deque.addFirst(c);
                }
            }
        }
        System.out.println(printDeque(deque));
    }

    private static String printDeque(Deque<Character> deque) {
        StringBuilder sb = new StringBuilder();
        if(deque.size() == 0){
            sb.append(0);
            return sb.toString();
        }
        while(!deque.isEmpty()){
            sb.append(deque.pollFirst());
        }
        return sb.toString();
    }
}
