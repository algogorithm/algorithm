import java.io.*;
import java.util.*;

public class BOJ24060_알고리즘_수업_병합_정렬_1 {

    static int[] A;
    static int[] temp;
    static int cnt;
    static int k;
    static int answer = -1;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");

        A = new int[n];
        temp = new int[n];

        for(int i = 0; i < n; ++i) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        mergeSort(0, A.length - 1);
        System.out.println(answer);
    }

    public static void mergeSort(int left, int right) {
        if(left < right) {
            int mid = (left + right) / 2;

            mergeSort(left, mid);
            mergeSort(mid + 1, right);
            merge(left, mid, right);
        }
    }

    public static void merge(int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int t = 0;

        while(i <= mid && j <= right) {
            if(A[i] <= A[j]) {
                temp[t++] = A[i++];
            } else {
                temp[t++] = A[j++];
            }
        }
        while(i <= mid) {
            temp[t++] = A[i++];
        }
        while(j <= right) {
            temp[t++] = A[j++];
        }

        i = left;
        t = 0;

        while(i <= right) {
            if(k == ++cnt) {
                answer = temp[t];
            }
            A[i++] = temp[t++];
        }
    }
}
