package Week21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1062 {
    // 기본적으로 알아야 하는 글자들
    static final String baseAlphabetes = "antic";
    static final int baseWordBit = 532741;
    static int N,K, result;
    // 입력 받은 단어들(비트마스킹을 위해 int 사용)
    static int[] words;
    static List<Character> usedAlphabets;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        // 모든 단어 앞 뒤에 들어가는 anta, tica에 해당하는 글자들의 집합인 baseAlphabete
        // 이보다 적은 수의 K개가 들어오면 기본 단어들도 읽을 수 없기 때문에 무조건 0이다.
        if (K < baseAlphabetes.length()) {
            System.out.println("0");
            return;
        }
        // 전체 알파벳을 알면 모든 단어를 읽을 수 있다.
        if (K == 26) {
            System.out.println(N);
            return;
        }
        words = new int[N];
        usedAlphabets = new ArrayList<>();
        String inputWord = "";
        for (int i = 0; i < N; i++) {
            inputWord = br.readLine();
            // 기본으로 들어가는 글자들인 anta, tica를 제거한 나머지 문자를 저장한다.
            inputWord = inputWord.substring(4, inputWord.length() - 4);
            // 입력된 데이터로 비트마스킹 한 값을 저장.
            for (char c : inputWord.toCharArray()) {
                if(!isBaseAlphabet(c)){
                    words[i] |= makeChatToBit(c);
                    if (!usedAlphabets.contains(c)) {
                        usedAlphabets.add(c);
                    }
                }
            }
        }
        // 만약 선택된 알파벳들의 개수가 K-5개보다 적으면 모든 문자들을 읽을 수 있다.
        if (usedAlphabets.size() < K-5) {
            System.out.println(N);
            return;
        }
        result = 0;
        dfs(0, 5, 0);
        System.out.println(result);
    }
    private static void dfs(int index, int cnt, int selected){
        // 종료조건
        if(cnt == K){
            int readableWords = 0;
            for(int word : words){
                if((selected & word) == word){
                    readableWords++;
                }
            }
            result = Math.max(result, readableWords);
            return;
        }
        if(index == usedAlphabets.size())
            return;
        // 본문
        // 현재 알파벳을 선택하지 않을경우
        dfs(index + 1, cnt, selected);
        // 현재 알파벳을 선택할 경우
        dfs(index + 1, cnt + 1, selected | makeChatToBit(usedAlphabets.get(index)));
    }

    // 현재 char값이 기본문자열인 "antic"에 포함되는지 확인.
    private static boolean isBaseAlphabet(char c){
        int cBit = makeChatToBit(c);
        return (baseWordBit & cBit) == cBit;
//        return baseAlphabetes.indexOf(c) >= 0;
    }
    // char를 비트마스킹 된 bit값으로 변환해준다.
    private static int makeChatToBit(char c){
        return 1 << (c - 'a');
    }
}