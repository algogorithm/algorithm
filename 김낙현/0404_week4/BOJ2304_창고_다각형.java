import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2304_창고_다각형 {
    static class Pillar implements Comparable<Pillar> {
        int idx;
        int height;

        Pillar(int idx, int height) {
            this.idx = idx;
            this.height = height;
        }

        @Override
        public int compareTo(Pillar o) {
            return this.idx - o.idx;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Pillar[] pillars = new Pillar[n];

        int maxHeight = 0;
        int maxIdx = n;
        for(int i = 0; i < n; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int idx = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());

            pillars[i] = new Pillar(idx, height);

            if(maxHeight <= height) {
                if(maxHeight == height && maxIdx < idx) continue;
                maxHeight = height;
                maxIdx = idx;
            }
        }

        Arrays.sort(pillars);

        int answer = maxHeight;

        // From front to Max
        int previousHeight = pillars[0].height;
        int previousIdx = pillars[0].idx;
        for(int i = 1; i < n && pillars[i].idx <= maxIdx; ++i) {
            if(previousHeight > pillars[i].height) continue;

            answer += (pillars[i].idx - previousIdx) * previousHeight;

            previousIdx = pillars[i].idx;
            previousHeight = pillars[i].height;
        }

        // From back to Max
        previousHeight = pillars[n-1].height;
        previousIdx = pillars[n-1].idx;

        for(int i = n - 2; i >= 0 && pillars[i].idx >= maxIdx; --i) {
            if(previousHeight > pillars[i].height) continue;

            answer += (previousIdx - pillars[i].idx) * previousHeight;

            previousIdx = pillars[i].idx;
            previousHeight = pillars[i].height;
        }

        System.out.println(answer);
    }
}
