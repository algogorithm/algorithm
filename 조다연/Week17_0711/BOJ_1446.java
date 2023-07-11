package week17_0711;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1446 {
	//������
	static ArrayList<Point>[] path; //������ ���� ���� ����Ʈ

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//0���� �����ؼ� D�� ����
		int N = Integer.parseInt(st.nextToken()); //������ ����
		int D = Integer.parseInt(st.nextToken()); // ��ӵ��� ����
		
		int[] distance = new int[D+1];
		path = new ArrayList[10001]; //d�� ���� 10000���� �۰ų� ����
		
		Arrays.fill(distance, Integer.MAX_VALUE); //�Ÿ��迭 �ִ����� �ʱ�ȭ
		for(int i=0; i<10001; i++) {
			path[i] = new ArrayList<>();
		}
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()); //������ġ
			int e = Integer.parseInt(st.nextToken()); //������ġ
			int dist = Integer.parseInt(st.nextToken()); //������ ����
			
			//�������� ���������� �ִ� �Ÿ��� �ƴ� -> �������� ��츸 ����
			if(e-s > dist) {
				path[e].add(new Point(s,dist));
			}
		}
		
		//�ִܰŸ� ���ϱ�
		distance[0] = 0; //0->0���� �̵��Ÿ� 0
		for(int i=1; i<=D; i++) {
			if(path[i].size()>0) {
				//i������ �����ϴ� �������� �ִٸ� 
				//������ �� ���� �ִ� �Ÿ��� ����
				for(Point p : path[i]) {
					//�̹� ���ŵǾ��ٸ�
					if(distance[p.start]+p.dist > distance[i]) continue;
					
					distance[i] = Math.min(distance[i-1]+1, distance[p.start]+p.dist);
				}
				continue;
			}
			distance[i] = distance[i-1]+1;
		}
		
		System.out.println(distance[D]);
	}
	
	static class Point {
		int start;
		int dist;
		
		Point(int start, int dist) {
			this.start = start;
			this.dist = dist;
		}
	}

}
