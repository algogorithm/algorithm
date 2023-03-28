import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 하늘에서별똥별이빗발친다 {
    static class xy {
        int i, j;

        public xy(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
    static int n, m, l, k;
    static ArrayList<xy> al;
    static int ans;

    public static void startCount() {
        for (xy p1 : al) {
            for (xy p2 : al) {
                int minRow = Math.min(p1.i, p2.i);
                int minCol = Math.min(p1.j, p2.j);

                int count = 0;
                for (xy p : al) {
                    if (minRow <= p.i && p.i <= minRow + l && minCol <= p.j && p.j <= minCol + l) {
                        count++;
                    }
                }
                ans = Math.min(ans, k - count);
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        al = new ArrayList<>();
        ans = k;

        for (int t = 0; t < k; t++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            al.add(new xy(i, j));
        }

        startCount();

        System.out.println(ans);
    }


}