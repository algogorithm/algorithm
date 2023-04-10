package re;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class 괄호의값 {

    //(()[[]])([])
    //  (2[3])(3)
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();
        Stack<Character> sl = new Stack<>();
        Stack<Integer> sl2= new Stack<>();
        int ans =0;
        for(int i =0; i<s.length(); i++){
            /*if(i==s.length()-1){
                break;
            }*/
            if(!sl.isEmpty() && sl.peek() == '(' && s.charAt(i)== ')'){
                sl.pop();
                if(sl.isEmpty() && i!=s.length()-1){
                    int sum = 0;
                    while (!sl2.isEmpty()){
                        int tmp= sl2.pop();
                        if(tmp==0){
                          continue;
                        }
                        sum +=tmp;
                    }
                    sl2.add(sum*2);
                }
                else {
                    if (sl2.isEmpty()) {
                        sl2.add(2);
                    } else {
                        if (sl2.peek() != 0) {
                            int tmp = sl2.pop();
                            tmp = tmp * 2;
                            sl2.add(tmp);
                        } else {
                            sl2.add(2);
                        }
                    }
                }
                System.out.println(i+ " "+ sl.toString());
                System.out.println(i+ " "+ sl2.toString());
            }
            else if(!sl.isEmpty() && sl.peek() == '[' && s.charAt(i)== ']'){
                sl.pop();
                if(sl.isEmpty() && i!=s.length()-1){
                    int sum = 0;
                    while (!sl2.isEmpty()){
                        int tmp= sl2.pop();
                        if(tmp==0){
                            continue;
                        }
                        sum +=tmp;
                    }
                    sl2.add(sum*3);
                }
                else {
                    if (sl2.isEmpty()) {
                        sl2.add(3);
                    } else {
                        if (sl2.peek() != 0) {
                            int tmp = sl2.pop();
                            tmp = tmp * 3;
                            sl2.add(tmp);
                        } else {
                            sl2.add(3);
                        }
                    }
                }
                System.out.println(i+ " "+ sl.toString());
                System.out.println(i+ " "+ sl2.toString());
            }
            else {
                sl.add(s.charAt(i));
                if(!sl2.isEmpty()&& sl2.peek()!=0){
                    sl2.add(0);
                }
                System.out.println(i+ " "+ sl.toString());
                System.out.println(i+ " "+ sl2.toString());
            }
        }
        int fans =0;
        while (!sl2.isEmpty()){
            int tmp= sl2.pop();
            if(tmp==0){
                continue;
            }
            fans +=tmp;
        }

        if(sl.isEmpty()){
            System.out.println(fans);
        }
        else{
            System.out.println("0");
        }

    }
}
