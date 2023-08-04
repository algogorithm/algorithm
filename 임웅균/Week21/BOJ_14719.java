package Week21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14719 {
    static int H, W;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        int[] Hmap = new int[W];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < W; i++){
            Hmap[i] = Integer.parseInt(st.nextToken());
        }
        int leftToRightMaxHeight = 0;
        int[] leftToRightMap = new int[W];
        int rightToLeftMaxHeight = 0;
        int[] rightToLeftMap = new int[W];
        for(int start = 0, end = W-1; start < W; start++, end--){
            // 왼쪽에서 오른쪽으로 이동하면서 왼쪽방향의 최대 높이를 저장
            leftToRightMaxHeight = Math.max(leftToRightMaxHeight, Hmap[start]);
            leftToRightMap[start] = leftToRightMaxHeight;
            // 오른쪽에서 왼쪽으로 이동하면서 오른쪽방향의 최대 높이를 저장
            rightToLeftMaxHeight = Math.max(rightToLeftMaxHeight, Hmap[end]);
            rightToLeftMap[end] = rightToLeftMaxHeight;
        }
        int result = 0;
        for(int i = 0; i < W; i++){
            int minHeight = Math.min(leftToRightMap[i], rightToLeftMap[i]);
            if(minHeight > Hmap[i]){
                result += minHeight - Hmap[i];
            }
        }
        System.out.println(result);
    }
}
