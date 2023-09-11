package Week24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2512 {
    static int countryN, tempSumBudget, totalBudget, priceLimit;
    static int[] countryBudgets;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        priceLimit = 0;
        countryN = Integer.parseInt(br.readLine());
        countryBudgets = new int[countryN];
        tempSumBudget = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < countryBudgets.length; i++){
            countryBudgets[i] = Integer.parseInt(st.nextToken());
            tempSumBudget += countryBudgets[i];
        }
        // 배열 정렬
        Arrays.sort(countryBudgets);
        totalBudget = Integer.parseInt(br.readLine());
        // 본격 로직 시작
        // 모든 요청이 배정될 수 있는 경우
        if(totalBudget >= tempSumBudget){
            System.out.println(countryBudgets[countryBudgets.length-1]);
            return;
        }
        // 모든 요청이 배정될 수 있는 경우
        // 이분탐색으로 특정한 상한액 생성.
        // 범위는 0 ~ totalBudget의 중간
        binarySearch(0, totalBudget);
        System.out.println(priceLimit);
    }

    private static void binarySearch(int start, int end) {
        // 종료조건
        if (start > end) return;
        // 이 mid값을 특정 상한액으로 지정한다.
        int mid = (start + end) / 2;
        // 이제 mid값을 기준으로 그 이상인 예산요청에는 모두 상한액을 배정한다.
        // 상한액 이하의 예산요청에 대해서는 요청한 금액을 그대로 배정
        // 그리고 이 합들이 totalBudget을 넘어버리면, 탈락, 넘지 않으면 통과. 이때의 최대값 찾기
        int tempSum = 0;
        for(int i = 0; i < countryBudgets.length; i++){
            tempSum += countryBudgets[i] >= mid ? mid : countryBudgets[i];
        }
        if(tempSum > totalBudget){
            binarySearch(start, mid - 1);
        }
        else if(tempSum < totalBudget){
            priceLimit = Math.max(mid, priceLimit);
            binarySearch(mid + 1, end);
        }
        else{
            priceLimit = mid;
            return;
        }
    }

}
