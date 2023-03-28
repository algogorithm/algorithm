import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class 괄호의값 {

    public static Stack<Character> stack=new Stack<Character>();
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String s=br.readLine();
        boolean flag=false;
        long ans=0;
        int tmp=1;
        for(int i=0; i<s.length(); i++) {

            if(s.charAt(i)=='(') {
                stack.add(s.charAt(i));
                tmp*=2;
            }
            if(s.charAt(i)=='[') {
                stack.add(s.charAt(i));
                tmp*=3;
            }
            if(s.charAt(i)==')') {
                if(stack.isEmpty() || stack.peek()!='(') {
                    flag=true;
                    break;
                }if(s.charAt(i-1)=='(')
                    ans+=tmp;
                stack.pop();
                tmp/=2;

            }else if(s.charAt(i)==']') {
                if(stack.isEmpty() || stack.peek()!='[') {
                    flag=true;
                    break;
                }
                if(s.charAt(i-1)=='[')
                    ans+=tmp;
                stack.pop();
                tmp/=3;
            }
            //System.out.println("ans : "+ans);
            //System.out.println("tmp : "+tmp);
        }
        if(flag==true || !stack.empty()) {
            System.out.println(0);
        }else {
            System.out.println(ans);
        }
    }

}