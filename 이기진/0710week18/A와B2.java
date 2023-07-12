package d202307;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A와B2 {
    static String s;
    static boolean flag =false;

    public static void main(String[] args)throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        s =br.readLine();
        String T= br.readLine();

        function(T);

        if(flag){
            System.out.println("1");
        }else{
            System.out.println("0");
        }
    }

    private static void function(String t) {
        if(s.length()==t.length()){
            if(s.equals(t)) flag =true;
            return;
        }
        if(t.charAt(0)=='B'){
            String reverseStr="";
            for (int i = t.length()-1; 0<i  ; i--) {
                reverseStr+=String.valueOf(t.charAt(i));
            }
            function(reverseStr);
        }

        if(t.charAt(t.length()-1)=='A'){
            String str="";
            for (int i = 0; i<t.length()-1; i++) {
                str+=String.valueOf(t.charAt(i));
            }
            function(str);
        }

    }
}
