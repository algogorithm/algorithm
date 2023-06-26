package d202306;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 숫자고르기 {

    static int N;
    static int[] numbers;
    static boolean[] visit;
    static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        numbers = new int[N+1];
        for(int i = 1; i<=N; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }
        //입력 완료

        visit = new boolean[N+1];
        for(int i = 1; i<=N; i++) {
            dfs(i, i);
        }

        System.out.println(list.size());
        for(int i: list) {
            System.out.println(i);
        }
    }

    private static void dfs(int start, int num) {

        if(numbers[num] == start) {
            list.add(start);
        }

        visit[num] = true;

        if(!visit[numbers[num]]) {
            dfs(start, numbers[num]);
        }

        visit[num] = false;

    }
}