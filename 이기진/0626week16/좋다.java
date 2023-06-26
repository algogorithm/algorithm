package d202306;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class 좋다 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        int result = 0;
        int arr[] = new int[N];

        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        Arrays.sort(arr);

        for(int k=0; k<N; k++){
            long find = arr[k];
            int i = 0;
            int j = N-1;

            while(i < j) {
                if(arr[i] + arr[j] == find){

                    if(i != k && j != k){
                        result++;
                        break;
                    }else if(i == k){
                        i++;
                    }else {
                        j--;
                    }

                }else if(arr[i] + arr[j] < find){
                    i++;
                }else {
                    j--;
                }
            }
        }

        System.out.println(result);
    }
}