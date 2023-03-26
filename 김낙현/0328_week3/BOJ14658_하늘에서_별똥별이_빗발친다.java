import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14658_하늘에서_별똥별이_빗발친다 {
    static class Star {
        int r;
        int c;

        Star(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Star[] stars = new Star[k];
        for(int i = 0; i < k; ++i) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            stars[i] = new Star(r, c);
        }

        int answer = 101;
        for(int i = 0; i < k; ++i) {
            for(int j = 0; j < k; ++j) {
                Star mostWestStar = stars[i];
                Star mostSouthStar = stars[j];

                int westBoundary = mostWestStar.c;
                int eastBoundary = westBoundary + l;
                int southBoundary = mostSouthStar.r;
                int northBoundary = southBoundary + l;

                int bouncedStarCnt = 0;
                for(Star s : stars) {
                    if(s.r >= southBoundary && s.r <= northBoundary && s.c >= westBoundary && s.c <= eastBoundary) {
                        ++bouncedStarCnt;
                    }
                }
                answer = Math.min(answer, k - bouncedStarCnt);
            }
        }
        System.out.println(answer);
    }
}
