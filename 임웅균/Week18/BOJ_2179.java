package Week18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2179 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] words = new String[N];
        LinkedHashMap<String, ArrayList<Integer>> prefixMap = new LinkedHashMap<>();

        //  단어의 모든 접두사를 구하고, 각 접두사와 그 접두사를 가진 단어들을 prefixMap에 저장
        /*
            예를들어 'abc', 'abd'가 입력됐다고 한다면
            a접두사에 0추가
            ab접두사에 0추가
            abc접두사에 0추가
            --------------
            a접두사에 1추가
            ab접두사에 1추가
            abd접두사에 1추가
         */
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
            StringBuilder prefix = new StringBuilder();
            for (char ch : words[i].toCharArray()) {
                prefix.append(ch);
                if(!prefixMap.containsKey(prefix.toString())){
                    prefixMap.put(prefix.toString(), new ArrayList<>());
                }
                prefixMap.get(prefix.toString()).add(i);
            }
        }
        // 접두사 순차탐색
        // 접두사의 최고길이
        int maxLength = -1;
        // 최고길이를 가지는 접두사
        String maxPrefix = "";
        // prefixMap의 모든 접두사들을 순차탐색
        for(Map.Entry<String, ArrayList<Integer>> entry : prefixMap.entrySet()){
            String prefix = entry.getKey();
            // 해당 접두사를 가지는 word들의 인덱스를 가지고 있는 리스트 가져오기
            ArrayList<Integer> list = prefixMap.get(prefix);
            // 해당 접두사를 가지는 word가 2개 이상이고, maxLength보다 긴 접두사라면 갱신.
            if(list.size() >= 2 && prefix.length() > maxLength){
                maxLength = prefix.length();
                maxPrefix = prefix;
            }
        }
        // 위에서 찾은 가장 긴 접두사를 가지는 word 인덱스 리스트 가져오기
        ArrayList<Integer> list = prefixMap.get(maxPrefix);
        // 앞에서부터 2개 출력.
        System.out.println(words[list.get(0)]);
        System.out.println(words[list.get(1)]);
    }
}