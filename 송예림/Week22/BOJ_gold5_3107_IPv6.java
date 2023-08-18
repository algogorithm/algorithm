package BAEKJOON;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class gold5_3107_IPv6 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
        String str = br.readLine();
        String[] arr = str.split(":");
        int cnt = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i].length() == 0){
                continue;
            } else if(arr[i].length() < 4){
                int len = arr[i].length();
                for (int j = 0; j < 4 - len; j++) {
                    arr[i] = "0" + arr[i];
                }
            }
            cnt++;
        }

        if(cnt == 8){
            for (String s : arr) {
                sb.append(s+":");
            }
        } else {
        	boolean chk = false;
            for (int i = 0; i < arr.length; i++) {
                if(!chk && arr[i].length() == 0){
                    for (int j = 0; j < 8-cnt; j++) {
                        sb.append("0000:");
                        chk = true;
                    }
                } else if(arr[i].length() != 0){
                    sb.append(arr[i] + ":");
                }
            }
        }
        
        // 맨끝에 :: 일 경우 ..
        if(sb.length() < 40) {
        	for (int j = 0; j < 8-cnt; j++) {
                sb.append("0000:");
            }
        }
        
        sb.delete(sb.length()-1, sb.length());
        System.out.println(sb);
	}

}
