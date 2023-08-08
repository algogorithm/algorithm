package week21_0808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14719 {
	//����

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		int[] rain = new int[W];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<W; i++) {
			rain[i] = Integer.parseInt(st.nextToken());
		}
		
		int answer = 0;
		
		int h = rain[0], left = 0, right = W;
		
		//���� ��պ��� �������� ����� ū ���
		//�¿��� ��� ��ȸ
		//���� ����� ���̸� h, ��ġ�� left�� ����
		for (int i = 1; i < W; i++) {
            if (h <= rain[i]) {
                h = Math.min(h, rain[i]);

                while (left++ < i - 1) {
                    answer += h - rain[left];
                }

                h = rain[i];
            }
        }
		
		// ���� ��պ��� �������� ����� ���� ���
		//�쿡�� �·� 1���� ���� ������ ���� ��ձ��� ��ȸ
		h = rain[W - 1];
        for (int i = W - 1; i >= left; i--) {
            if (h <= rain[i]) {
                h = Math.min(h, rain[i]);

                while (right-- > i + 1) {
                    answer += h - rain[right];
                }

                h = rain[i];
            }
        }
		

		System.out.println(answer);
	}

}
