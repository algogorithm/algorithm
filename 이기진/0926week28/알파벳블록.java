
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

public class 알파벳블록 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n  = Integer.parseInt(bf.readLine());
        StringTokenizer st;
        Deque<Character> deque = new ArrayDeque<>();
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(bf.readLine());
            if(st.countTokens() == 1){
                if(!stack.empty()) {
                    if(stack.pop() == 1) {
                        deque.pollLast();
                    }
                    else {
                        deque.pollFirst();
                    }
                }
            }
            else{
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
        StringBuilder sb = new StringBuilder();

        if(deque.size() == 0){
            sb.append(0);
        }
        else{
            while(!deque.isEmpty()) {
                sb.append(deque.pollFirst());
            }
        }
        System.out.println(sb);
    }

}