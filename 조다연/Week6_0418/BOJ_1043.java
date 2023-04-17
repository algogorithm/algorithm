package week6_0418;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1043 {
	//거짓말
	static int N, M;
	static boolean knowPeople[];
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//지민이는 진실을 아는 자가 없을 때만 거짓말을 할 수 있음
		//거짓말 할 수 있는 파티 개수 최대
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//사람 수 
		N = Integer.parseInt(st.nextToken());
		//파티 수
		M = Integer.parseInt(st.nextToken());

		//진실 아는 사람 정보 받아오기
		//knowPeople[그사람번호] = true
		st = new StringTokenizer(br.readLine());
		//진실을 아는 사람 수
		int knowCnt = Integer.parseInt(st.nextToken());
		//진실을 아는 사람이 없다면 파티 수 출력
		if(knowCnt == 0 ) {
			System.out.println(M);
			return;
		}
		//진실을 아는 사람 배열
		knowPeople = new boolean[N+1];
		for(int i=0; i<knowCnt; i++) {
			int num = Integer.parseInt(st.nextToken());
			knowPeople[num] = true;
		}
		
		//union-find 초기화
		parent = new int[N+1];
		for(int i=1;i<=N; i++) {
			parent[i] = i;
		}
		
		//각 파티 참여자 목록
		//같은 파티에 참석한 사람들 union
		ArrayList<Integer>[] party = new ArrayList[M+1];
        for(int i=1; i<=M; i++) {
            party[i] = new ArrayList<>();
        }
        
        for(int i=1; i<=M; i++) {
        	st = new StringTokenizer(br.readLine());
        	//파티 참여 인원 수
        	int attend = Integer.parseInt(st.nextToken());
        	
        	for(int j=0; j<attend; j++) {
        		party[i].add(Integer.parseInt(st.nextToken()));
        		
        		if(j != 0) {
        			//같은 파티에 참여한 사람 union
        			int a = party[i].get(j);
            		int b = party[i].get(j-1);
            		
            		union(a,b);
        		}
        	}
        }
        
    	//진실을 아는 사람과 같이 파티에 참여한 사람도 true로 
    	boolean v[] = new boolean[N+1];
    	for(int i=1; i<=N; i++) {
    		if(knowPeople[i] && !v[i]) {
    			int root = find(i);
    			
    			for(int j=1; j<=N; j++) {
    				if(root == find(j)) {
    					knowPeople[j] = true;
    					v[j] = true;
    				}
    			}
    		}
    	}

    	//파티에 진실을 아는 사람이 있는지
    	boolean goParty[] = new boolean[M+1];
    	for(int i=1; i<=M; i++) {
    		for(int j=1; j<=N; j++) {
    			if(knowPeople[j]) { //진실을 아는 사람이
    				if(party[i].contains(j)) //해당 파티에 참석했으면
    					goParty[i] = true;
    			}
    		}
    	}
    	
    	int answer = 0;
    	for(int i=1; i<=M; i++) {
    		if(!goParty[i]) answer++;
    	}
    	
    	System.out.println(answer);
	}

	//부모 찾기
	private static int find(int a) {
		if (a == parent[a]) return a;
		return parent[a] = find(parent[a]);
	}

	//합치기
	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a != b) {
			if(a < b) {
				parent[b] = a;
			} else {
				parent[a] = b;
			}
		}
	}

}
