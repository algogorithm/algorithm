import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class 최대힙 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();

        for(int i=0; i<n ; i++){
            int num = Integer.parseInt(bf.readLine());

            if(num==0){
                if(queue.isEmpty()){
                    System.out.println("0");
                }
                else{
                    System.out.println(queue.poll()*-1);
                }
            }
            else{
                queue.add(-1*num);
            }
        }

    }
}
