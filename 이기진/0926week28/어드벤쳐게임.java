import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 어드벤쳐게임 {
    static ArrayList<Integer>[] linkRoom;
    static char[] rType;
    static int[] mInfo;
    static int N;
    static boolean flag;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder ans = new StringBuilder();
        N = 0;
        while((N = Integer.parseInt(br.readLine())) != 0) {
            rType = new char[N+1];
            linkRoom = new ArrayList[N+1];
            mInfo = new int[N+1];
            visited = new boolean[N+1];
            for(int i=1; i<=N; i++) {
                st = new StringTokenizer(br.readLine());
                rType[i] = st.nextToken().charAt(0);
                mInfo[i] = Integer.parseInt(st.nextToken());
                linkRoom[i] = new ArrayList<>();
                int roomNum = 0;
                while((roomNum = Integer.parseInt(st.nextToken())) != 0) {
                    linkRoom[i].add(roomNum);
                }
            }
            flag = false;
            goDfs(1, 0);
            if(flag) {
                System.out.println("Yes");
            }
            else
                System.out.println("No");
        }
    }

    private static void goDfs(int room, int money) {
        if(flag) return;
        if(room == N) {
            flag = true;
            return;
        }

        for(int i =0; i<linkRoom[room].size(); i++) {

            int rNum = linkRoom[room].get(i);

            if(visited[rNum]) continue;
            if(rType[rNum] == 'L') {
                if(mInfo[rNum] > money) {
                    money = mInfo[rNum];
                }
            } else if(rType[rNum] == 'T') {
                if(mInfo[rNum] <= money) {
                    money -= mInfo[rNum];
                } else {
                    return;
                }
            }
            visited[rNum] = true;
            goDfs(rNum, money);
            visited[rNum] = false;
        }
        return;
    }
}