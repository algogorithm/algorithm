
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

    public class 별찍기 {
        static String[][] map;
        public static void main(String[] args) throws  IOException {

            //3
            //AAA
            //A A
            //AAA

            //9
            //AAAAAAAAA
            //A AA AA A
            //AAAAAAAAA
            //AAA   AAA
            //A A   A A
            //AAA   AAA
            //AAAAAAAAA
            //A AA AA A
            //AAAAAAAAA

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            int N = Integer.parseInt(br.readLine());
            map = new String[N][N];

            printstar(0, 0, N,false);

            for(int i = 0 ; i < N ; i++) {
                for(int j = 0 ; j < N ; j++) {
                    bw.write(map[i][j]);
                }
                bw.write("\n");
            }

            br.close();
            bw.flush();
            bw.close();


        }

        public static void printstar(int x, int y, int n, boolean flag) {

            if(n == 1) {
                if(flag){
                    map[x][y]=" ";
                }
                else {
                    map[x][y] = "*";
                }
                return;
            }

            for(int i = 0 ; i < 3 ; i++) {
                for(int j = 0 ; j < 3 ; j++) {
                    if(flag){
                        printstar(x + i*(n/3), y + j*(n/3), n/3, true);
                    }
                    else if(!flag &&(i == 1 && j == 1)) {
                        printstar(x + i*(n/3), y + j*(n/3), n/3, true);
                    }
                    else if(!flag &&!(i == 1 && j == 1)){
                        printstar(x + i*(n/3), y + j*(n/3), n/3, false);
                    }
                }
            }
        }
    }
