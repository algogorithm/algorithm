package Week23;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2116_주사위_쌓기 {
	static int[][] dice;
	static int[] opposite = { 1, 6, 2, 4, 3, 5 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		dice = new int[T][6];

		for (int i = 0; i < dice.length; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 6; ++j) {
				dice[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int result = 0;
		for (int i = 0; i < 6; ++i) {
			result = Math.max(result, countMax(0, dice[0][i], new boolean[T][6]));
		}

		System.out.println(result);
	}

	private static int countMax(int curDice, int num, boolean[][] v) {
		if (curDice == dice.length) {
			int sum = 0;

			for (int i = 0; i < dice.length; ++i) {
				int max = 0;

				for (int j = 0; j < 6; ++j) {
					if (!v[i][j]) {
						max = Math.max(max, dice[i][j]);
					}
				}
				sum += max;
			}
			
			return sum;
		}

		return countMax(curDice+1, findOppositeAndCheck(curDice, num, v), v);
	}

	private static int findOppositeAndCheck(int diceNum, int num, boolean[][] v) {
		int diceLoc = 0;
		int opposite = 0;

		for (int i = 0; i < 6; ++i) {
			if (dice[diceNum][i] == num) {
				diceLoc = i;
				break;
			}
		}

		switch(diceLoc) {
		case 0: opposite = 5; break;
		case 1: opposite = 3; break;
		case 2: opposite = 4; break;
		case 3: opposite = 1; break;
		case 4: opposite = 2; break;
		case 5: opposite = 0; break;
		}
		
		v[diceNum][diceLoc] = true;
		v[diceNum][opposite] = true;
		
		return dice[diceNum][opposite];
	}
}
