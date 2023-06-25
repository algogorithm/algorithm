package d202306;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 전화번호목록 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(bf.readLine());
            String[] phones = new String[n];
            for (int j = 0; j < n; j++) {
                phones[j] = bf.readLine();
            }
            String answer = solution(phones);

            System.out.println(answer);
        }

    }

    private static String solution(String[] phones) {
        Arrays.sort(phones);
        Map<String, Integer> phoneMaps = new HashMap<>();

        for (String phone : phones) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < phone.length(); i++) {
                sb.append(phone.charAt(i));
                if(phoneMaps.containsKey(sb.toString())) return "NO";
            }
            phoneMaps.put(sb.toString(), 1);
            /*
            for(String tmp :phoneMaps.keySet()){
                System.out.println(tmp);
            }
            */
        }
        return "YES";
    }
}
