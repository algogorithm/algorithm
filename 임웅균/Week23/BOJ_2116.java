package Week23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2116 {
    static int[] diceFaceRelation = {5,3,4,1,2,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] diceArr = new int[N][6];
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 6; j++){
                diceArr[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        int resultDiceValuesSum = 0; // 최종 결과값
        for(int face = 0; face < 6; face++){ // face는 현재 기둥 가장 아래 주사위의 면
            int tempResultDiceValuesSum = 0; // 임시 주사위 옆면 합산값
            // 첫번째 주사위 기준으로 옆면의 최대값을 tempResultDiceValuesSum에 갱신
            tempResultDiceValuesSum += findMaxDiceFaceValue(diceArr[0], face);
            int diceTopFace = diceFaceRelation[face];
            // 두번째 주사위부터 끝 주사위까지 모든 옆면값을 계산해서 최종값 반환
            for(int diceN = 1; diceN < N; diceN++){
                // 이전 주사위의 diceFacingSide을 기준으로 해당 값을 찾고, 현재 주사위의 값과 같은 face를 찾는다.
                int diceBottomFace = findMatchingDiceFace(diceArr[diceN-1], diceArr[diceN], diceTopFace);
                tempResultDiceValuesSum += findMaxDiceFaceValue(diceArr[diceN],diceBottomFace);
                diceTopFace = diceFaceRelation[diceBottomFace];
            }
            // 최종 계산값 갱신
            resultDiceValuesSum = Math.max(resultDiceValuesSum, tempResultDiceValuesSum);
        }
        System.out.println(resultDiceValuesSum);
    }
    // 주사위 면의 위치값을 기준으로 해당 면을 바당으로 둔 상태의 옆면값 4개중 최대값 반환
    public static int findMaxDiceFaceValue(int[] dice, int nonIncludeFace){
        int max = -1;
        for(int i = 0; i < 6; i++){
            if(i != nonIncludeFace && i != diceFaceRelation[nonIncludeFace]){
                max = Math.max(max, dice[i]);
            }
        }
        return max;
    }

    // 이전주사위, 현재주사위, 이전주사위와 현재주사위가 맞닿은면의 실제값을 가지고 현재 주사위의 아래면을 구해줌.
    public static int findMatchingDiceFace(int[] pastDice, int[] currDice, int pastFace){
        int pastDiceFaceValue = pastDice[pastFace];
        for(int i = 0; i < 6; i++){
            if(currDice[i] == pastDiceFaceValue){
                return i;
            }
        }
        return -1;
    }
}
