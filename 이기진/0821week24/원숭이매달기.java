package d202308;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 원숭이매달기 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        /*
        3
        []

        [[ -] [[ -]]]
         */
        int n = Integer.parseInt(bf.readLine());
        for(int t = 0 ; t< n ; t++){
            String str = bf.readLine();
            int cnt = 0, result = 1;
            for (int j = 0; j < str.length(); j++) {
                if(str.charAt(j) == ']'){
                    cnt--;
                } else {
                    cnt++;
                    result = (int) Math.max(Math.pow(2,cnt), result);
                }
            }

            System.out.println(result);
        }
    }
}
