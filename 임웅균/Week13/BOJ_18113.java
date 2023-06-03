package Week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_18113 {
    /*
        문제
        김밥 N개에 대해서, 김밥 꼬다리를 잘라내고 손질된 김밥을 김밥조각으로 만드는 작업을 한다.
        꼬다리를 잘라낼 때에는 양쪽에서 균일하게 K cm만큼 잘라낸다.
        김밥의 길이가 2K cm보다 짧아서 한쪽밖에 자르지 못한다면, 한쪽만 꼬다리를 잘라낸다.
        김밥 길이가 K cm이거나 그보다 짧으면 그 김밥은 폐기한다
        손질된 김밥들은 모두 일정한 길이 P로 잘라서 P cm의 김밥조각들로 만든다.
        일정한 길이 P cm로 자른 김밥조각을 최소 M개 만들고 싶다. P를 최대한 길게 하고 싶을 때, P는 얼마로 설정해야 하는지 구하기
        김밥조각의 길이 P를 최대로 할 때, P를 출력한다. 만족하는 P가 없는 경우, -1을 출력한다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 손질해야 하는 김밥의 개수
        long N = Long.parseLong(st.nextToken());
        // 꼬다리의 길이
        long K = Long.parseLong(st.nextToken());
        // 김밥 조각의 최소 개수
        long M = Long.parseLong(st.nextToken());
        // N개의 김밥들의 길이가 들어갈 변수
        ArrayList<Long> gimbabs = new ArrayList<>();
        long maxLength = 0;
        long resultP = -1;
        // N개의 김밥들을 손질해야하기때문에 반복문 수행
        for(int n = 0; n < N; n++){
            // 김밥의 길이 L
            long L = Long.parseLong(br.readLine());
//            System.out.println(L);
            // 꼬다리 자르는 작업
            // 김밥 길이가 K cm이거나 그보다 짧으면 그 김밥은 폐기한다
            if(L <= K){
                continue;
            }
            // 김밥의 길이가 2K cm보다 짧아서 한쪽밖에 자르지 못한다면, 한쪽만 꼬다리를 잘라낸다.
            else if(L < 2*K){
                L -= K;
            }
            // 양쪽에서 균일하게 K cm만큼 잘라낸다.
            else{
                L -= 2*K;
            }
            maxLength = Math.max(L, maxLength);
            // 잘라낸 김밥들을 gimbabs에 넣어준다.
            gimbabs.add(L);
        }
//        System.out.println(Arrays.toString(gimbabs.toArray()));
        // 김밥넣는 작업은 완료했으니 일정한 길이 P를 최대한 길게 하고 자른 김밥조각을 최소 M개 만들 수 있는지 구하기
        // 우선 김밥들중 가장 길이가 긴 김밥의 길이를 구한다.(위에서 구한 maxLength)
        // 그 후 김밥들을 해당 길이의 절반을 P로 설정한 후, P로 잘랐을때 모두 몇조각이 나오는지 구한다.
        // 만약 M개 이상이면 해당 P값을 저장한 후, P의값+1을 시작값으로하는 이분탐색(이분탐색의 오른쪽 범위)
        // 만약 M개 미만이면 해당 P값-1을 끝값으로 하는 또 하나의 이분탐색 시작(이분탐색의 왼쪽범위)
        long start = 1;
        long end = maxLength;
//        System.out.println("maxLength = "+maxLength);
        long resultM = 0;
        while(start <= end){
            // 김밥 길이 P구하기.
            long P = (start + end) / 2;
            // P의 값을 기준으로 김밥 다 잘라서 몇조각이 나오는지 구해보기.
            long tempM = 0;
            for(long gimbab : gimbabs){
                tempM += gimbab/P;
            }
            // 구해진 tempM과 M을 비교하기
            // 만약 tempM이 M보다 많다면 해당 P값을 저장한 후, P의값을 시작값으로하는 이분탐색(이분탐색의 오른쪽 범위)
            if(tempM >= M){
//                System.out.println("tempM이 M보다 많다");
//                System.out.println("P = "+P);
                resultP = Math.max(resultP, P);
                resultM = tempM;
                // start다시지정
                start = P+1;
            }
            // 만약 tempM이 M보다 작다면 해당 P값을 끝값으로 하는 또 하나의 이분탐색 시작(이분탐색의 왼쪽범위)
            else if(tempM < M){
//                System.out.println("tempM이 M보다 작다");
//                System.out.println("P = "+P);
                // end 다시지정
                end = P-1;
            }
        }
//        System.out.println("P가 "+resultP+"개일때, M은 "+ resultM+"개이다.");
        System.out.println(resultP);
    }
}
