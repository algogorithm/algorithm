import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 감시 {
    static class cctv {
        int row;
        int col;
        int type;

        public cctv(int row, int col, int type) {
            super();
            this.row = row;
            this.col = col;
            this.type = type;
        }

        @Override
        public String toString() {
            return "cctv [row=" + row + ", col=" + col + ", type=" + type + "]";
        }

    }

    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, 1, -1 };
    static int r, c;
    static int map[][];
    static int typdir[] = { 0, 4, 2, 4, 4, 1 };
    static int[][] visit;
    static int ans;
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        ArrayList<cctv> al = new ArrayList<cctv>();
        map = new int[r][c];
        ans =r*c;
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] >= 1 && map[i][j] <= 5) {
                    al.add(new cctv(i, j, map[i][j]));
                }
            }
        }
        int[] dir = new int[al.size()];
        per(al, dir, 0);
        System.out.println(ans);
    }

    private static void per(ArrayList<cctv> al, int[] dir, int idx) {
        // TODO Auto-generated method stub
        if (idx == al.size()) {
            visit = new int[r][c];
            clone(map);
            for (int i = 0; i < al.size(); i++) {
                if (al.get(i).type == 1) {
                    one(al.get(i).row, al.get(i).col, dir[i], visit);
                }
                if (al.get(i).type == 2) {
                    two(al.get(i).row, al.get(i).col, dir[i], visit);
                }
                if (al.get(i).type == 3) {
                    three(al.get(i).row, al.get(i).col, dir[i], visit);
                }
                if (al.get(i).type == 4) {
                    four(al.get(i).row, al.get(i).col, dir[i], visit);
                }
                if (al.get(i).type == 5) {
                    five(al.get(i).row, al.get(i).col, dir[i], visit);
                }
            }
            int cnt =0;
            for(int i=0; i<visit.length; i++) {
                for(int j=0; j<visit[0].length; j++) {
                    if(visit[i][j]==0)
                        cnt++;
                }
            }
            ans=Math.min(ans, cnt);
            return;
        }
        for (int j = 0; j < typdir[al.get(idx).type]; j++) {
            dir[idx] = j;
            per(al, dir, idx + 1);
        }
    }

    static void print(int[][] arr) {
        for (int tmp[] : arr) {
            for (int t : tmp) {
                System.out.print(t + " ");
            }
            System.out.println();
        }
    }
    static void clone(int[][] arr) {
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr[0].length; j++) {
                visit[i][j]=arr[i][j];
            }
        }
    }
    static void one(int row, int col, int dir, int[][] visit) {
        // 한방향 이동
        // dir 0
        if (dir == 0) { // 밑
            for (int i = row; i < map.length; i++) {
                if (map[i][col] == 6) {
                    break;
                }
                visit[i][col]++;
            }
        }
        // dir 1
        if (dir == 1) { // 위
            for (int i = row; i >= 0; i--) {
                if (map[i][col] == 6) {
                    break;
                }
                visit[i][col]++;
            }
        }
        // dir 2
        if (dir == 2) { // 좌
            for (int i = col; i >= 0; i--) {
                if (map[row][i] == 6) {
                    break;
                }
                visit[row][i]++;
            }
        }
        // dir 3
        if (dir == 3) { // 우
            for (int i = col; i < map[0].length; i++) {
                if (map[row][i] == 6) {
                    break;
                }
                visit[row][i]++;
            }
        }
    }

    static void two(int row, int col, int dir, int[][] visit) {
        // dir 0
        if (dir == 0) { // 밑 //위
            for (int i = row; i < map.length; i++) {
                if (map[i][col] == 6) {
                    break;
                }
                visit[i][col]++;
            }
            for (int i = row; i >= 0; i--) {
                if (map[i][col] == 6) {
                    break;
                }
                visit[i][col]++;
            }
        }

        // dir 1
        if (dir == 1) {
            for (int i = col; i >= 0; i--) {
                if (map[row][i] == 6) {
                    break;
                }
                visit[row][i]++;
            }
            for (int i = col; i < map[0].length; i++) {
                if (map[row][i] == 6) {
                    break;
                }
                visit[row][i]++;
            }
        }
    }

    static void three(int row, int col, int dir, int[][] visit) {
        // dir 0
        if(dir==0) {
            for (int i = row; i < map.length; i++) {
                if (map[i][col] == 6) {
                    break;
                }
                visit[i][col]++;
            }

            for (int i = col; i >= 0; i--) {
                if (map[row][i] == 6) {
                    break;
                }
                visit[row][i]++;
            }

        }
        // dir 1
        if(dir==1) {
            for (int i = row; i < map.length; i++) {
                if (map[i][col] == 6) {
                    break;
                }
                visit[i][col]++;
            }



            for (int i = col; i < map[0].length; i++) {
                if (map[row][i] == 6) {
                    break;
                }
                visit[row][i]++;
            }
        }
        // dir 2
        if(dir==2) {


            for (int i = row; i >= 0; i--) {
                if (map[i][col] == 6) {
                    break;
                }
                visit[i][col]++;
            }
            for (int i = col; i >= 0; i--) {
                if (map[row][i] == 6) {
                    break;
                }
                visit[row][i]++;
            }

        }
        // dir 3
        if(dir==3) {


            for (int i = row; i >= 0; i--) {
                if (map[i][col] == 6) {
                    break;
                }
                visit[i][col]++;
            }

            for (int i = col; i < map[0].length; i++) {
                if (map[row][i] == 6) {
                    break;
                }
                visit[row][i]++;
            }
        }

    }

    static void four(int row, int col, int dir, int[][] visit) {
        // dir 0
        if (dir == 0) {

            for (int i = row; i >= 0; i--) {
                if (map[i][col] == 6) {
                    break;
                }
                visit[i][col]++;
            }
            for (int i = col; i >= 0; i--) {
                if (map[row][i] == 6) {
                    break;
                }
                visit[row][i]++;
            }
            for (int i = col; i < map[0].length; i++) {
                if (map[row][i] == 6) {
                    break;
                }
                visit[row][i]++;
            }
        }
        // dir 1
        if (dir == 1) {
            for (int i = row; i < map.length; i++) {
                if (map[i][col] == 6) {
                    break;
                }
                visit[i][col]++;
            }

            for (int i = col; i >= 0; i--) {
                if (map[row][i] == 6) {
                    break;
                }
                visit[row][i]++;
            }
            for (int i = col; i < map[0].length; i++) {
                if (map[row][i] == 6) {
                    break;
                }
                visit[row][i]++;
            }
        }

        // dir 2
        if (dir == 2) {
            for (int i = row; i < map.length; i++) {
                if (map[i][col] == 6) {
                    break;
                }
                visit[i][col]++;
            }

            for (int i = row; i >= 0; i--) {
                if (map[i][col] == 6) {
                    break;
                }
                visit[i][col]++;
            }

            for (int i = col; i < map[0].length; i++) {
                if (map[row][i] == 6) {
                    break;
                }
                visit[row][i]++;
            }
        }

        // dir 3
        if (dir == 3) {
            for (int i = row; i < map.length; i++) {
                if (map[i][col] == 6) {
                    break;
                }
                visit[i][col]++;
            }

            for (int i = row; i >= 0; i--) {
                if (map[i][col] == 6) {
                    break;
                }
                visit[i][col]++;
            }
            for (int i = col; i >= 0; i--) {
                if (map[row][i] == 6) {
                    break;
                }
                visit[row][i]++;
            }

        }
    }

    static void five(int row, int col, int dir, int[][] visit) {
        // dir 0
        for (int i = row; i < map.length; i++) {
            if (map[i][col] == 6) {
                break;
            }
            visit[i][col]++;
        }

        for (int i = row; i >= 0; i--) {
            if (map[i][col] == 6) {
                break;
            }
            visit[i][col]++;
        }
        for (int i = col; i >= 0; i--) {
            if (map[row][i] == 6) {
                break;
            }
            visit[row][i]++;
        }
        for (int i = col; i < map[0].length; i++) {
            if (map[row][i] == 6) {
                break;
            }
            visit[row][i]++;
        }
    }
}
