package Week19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_10703 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        char[][] map = new char[R][C];
        int[] meteoHeight = new int[C];
        Arrays.fill(meteoHeight, -1);
        int[] groundHeight = new int[C];
        Arrays.fill(groundHeight, Integer.MAX_VALUE);
        boolean isMeteo = true;
        int airCnt = 0;
        for(int r = 0; r < R; r++){
            String temp = br.readLine();
            if(airCnt == C){
                isMeteo = false;
            }else{
                airCnt = 0;
            }
            for(int c = 0; c < C; c++){
                map[r][c] = temp.charAt(c);
                if(map[r][c] == '.'){
                    airCnt++;
                }
                if(isMeteo && map[r][c] == 'X'){
                    meteoHeight[c] = Math.max(meteoHeight[c], r);
                }
                else if(!isMeteo && map[r][c] == '#'){
                    groundHeight[c] = Math.min(groundHeight[c], r);
                }
            }
        }
        int minGap = Integer.MAX_VALUE;
        for(int i = 0; i < C; i++){
            if(meteoHeight[i] == -1)
                continue;
            minGap = Math.min(groundHeight[i] - meteoHeight[i]-1, minGap);
        }
        for(int r = R-1; r >= 0; r--){
            for(int c = 0; c < C; c++){
                if(map[r][c] == 'X'){
                    map[r+minGap][c] = 'X';
                    map[r][c] = '.';
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(char[] a : map){
            for(char b : a){
                sb.append(b);
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }
}