import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16987_계란으로_계란치기 {

    static class Egg {
        int weight;
        int durability;

        Egg(int durability, int weight) {
            this.durability = durability;
            this.weight = weight;
        }

        public Egg hitBy(Egg o) {
            this.durability -= o.weight;
            return this;
        }

        public Egg recover(Egg o) {
            this.durability += o.weight;
            return this;
        }

        public boolean isBroken() {
            return this.durability <= 0;
        }
    }

    static Egg[] eggs;
    static int answer = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        eggs = new Egg[n];
        for (int i = 0; i < n; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int durability = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            eggs[i] = new Egg(durability, weight);
        }

        simulation(0, 0);

        System.out.println(answer);
    }

    public static void simulation(int inHand, int cnt) {
        if(inHand == eggs.length) {
            answer = Math.max(answer, cnt);
            return;
        }

        // 잡은 계란이 깨지거나, 나머지 계란은 모두 깨져있음
        if(eggs[inHand].isBroken() ||
            cnt == eggs.length - 1) {
            simulation(inHand+1, cnt);
            return;
        }

        // 계란 치기
        for(int target = 0; target < eggs.length; ++target) {
            if(inHand == target ||
                eggs[target].isBroken()) continue;

            int brokenCnt = 0;
            if(eggs[inHand].hitBy(eggs[target]).isBroken()) ++brokenCnt;
            if(eggs[target].hitBy(eggs[inHand]).isBroken()) ++brokenCnt;

            simulation(inHand + 1, cnt + brokenCnt);

            eggs[inHand].recover(eggs[target]);
            eggs[target].recover(eggs[inHand]);
        }
    }
}
