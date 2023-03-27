import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14888_연산자_끼워넣기 {
	static char[] CHS = {'+','-','*','/'};
	static char[] OPERATOR;
	static int[] OPERAND;
	static int MIN, MAX;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		OPERATOR = new char[N-1];
		OPERAND = new int[N];
		MIN = Integer.MAX_VALUE;
		MAX = Integer.MIN_VALUE;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			OPERAND[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		
		int opNum = 0;
		for(int i=0; i<4; i++) {
			int operators = Integer.parseInt(st.nextToken());
			for(int j=0; j<operators; j++) {
				OPERATOR[opNum++] = CHS[i];
			}
		}
		
		perm(new char[OPERATOR.length], new boolean[OPERATOR.length], 0);
		
		System.out.println(MAX+"\n"+MIN);
	}

	private static void perm(char[] selected, boolean[] visited, int depth) {
		if(depth == selected.length) {
			int tmp = OPERAND[0];
			
			for(int i=1; i<OPERAND.length; i++) {
				if(selected[i-1] == '+') tmp += OPERAND[i];
				else if(selected[i-1] == '-') tmp -= OPERAND[i];
				else if(selected[i-1] == '*') tmp *= OPERAND[i];
				else if(selected[i-1] == '/') tmp /= OPERAND[i];
			}
			
			MIN = Math.min(MIN, tmp);
			MAX = Math.max(MAX, tmp);
			return;
		}
		
		for(int i=0; i<selected.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				selected[depth] = OPERATOR[i];
				perm(selected, visited, depth+1);
				visited[i] = false;
			}
		}
	}
	

}
