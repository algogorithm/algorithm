package Week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2304_창고_다각형 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		List<int[]> list = new ArrayList<>();
		int highestNum = 0, ans = 0;

		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			if (highestNum <= y) {
				highestNum = y;
			}
			list.add(new int[] { x, y });
		}

		Collections.sort(list, (o1, o2) -> {
			return o1[0] - o2[0];
		});

		int[] highestRow = new int[2];
		int[] tmp = new int[2];
		for(int i=0; i<list.size(); i++) {
			int[] now = list.get(i);
			
			if(tmp[1] < now[1]) {
				ans += (now[0] - tmp[0]) * tmp[1];
				
				tmp[0] = now[0];
				tmp[1] = now[1];
			}
			if(now[1] == highestNum) {
				highestRow[0] = now[0];
				break;
			}
		}
		
		tmp[0] = list.get(list.size()-1)[0]+1;
		tmp[1] = 0;
		for(int i=list.size()-1; i>=0; i--) {
			int[] now = list.get(i);
			
			if(tmp[1] < now[1]) {
				ans += (tmp[0] - now[0]) * tmp[1];
				
				tmp[0] = now[0];
				tmp[1] = now[1];
			}
			
			if(now[1] == highestNum) {
				highestRow[1] = now[0] + 1;
				break;
			}
		}

		ans += (highestRow[1] - highestRow[0]) * highestNum;
		
		System.out.println(ans);

	}

}
