import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14500_테트로미노 {

    static final Polygon[] poly = new Polygon[]{
        // ㅡ
        Polygon.getPrimitivePolygon(0),
        Polygon.getPrimitivePolygon(0).rotate(90),

        // ㅁ
        Polygon.getPrimitivePolygon(1),

        // ㄴ
        Polygon.getPrimitivePolygon(2),
        Polygon.getPrimitivePolygon(2).rotate(90),
        Polygon.getPrimitivePolygon(2).rotate(180),
        Polygon.getPrimitivePolygon(2).rotate(270),
        // ㄴ x축 대칭
        Polygon.getPrimitivePolygon(2).reflect(0),
        Polygon.getPrimitivePolygon(2).reflect(0).rotate(90),
        Polygon.getPrimitivePolygon(2).reflect(0).rotate(180),
        Polygon.getPrimitivePolygon(2).reflect(0).rotate(270),

        // ㄴ+ㄱ
        Polygon.getPrimitivePolygon(3),
        Polygon.getPrimitivePolygon(3).rotate(90),
        // ㄴ+ㄱ x축 대칭
        Polygon.getPrimitivePolygon(3).reflect(0),
        Polygon.getPrimitivePolygon(3).reflect(0).rotate(90),

        // ㅜ
        Polygon.getPrimitivePolygon(4),
        Polygon.getPrimitivePolygon(4).rotate(90),
        Polygon.getPrimitivePolygon(4).rotate(180),
        Polygon.getPrimitivePolygon(4).rotate(270),
    };

    static class Polygon {

        private int[][] vertex;

        private Polygon() {
        }

        static private Polygon getPrimitivePolygon(int type) {
            Polygon p = new Polygon();
            switch (type) {
                // ㅡ
                case 0:
                    p.setVertex(new int[][]{
                        {+0, +0},
                        {+0, +1},
                        {+0, +2},
                        {+0, +3}
                    });
                    break;
                // ㅁ
                case 1:
                    p.setVertex(new int[][]{
                        {+0, +0},
                        {+0, +1},
                        {+1, +0},
                        {+1, +1}
                    });
                    break;
                // ㄴ
                case 2:
                    p.setVertex(new int[][]{
                        {+0, +0},
                        {+1, +0},
                        {+2, +0},
                        {+2, +1}
                    });
                    break;
                // ㄴ+ㄱ
                case 3:
                    p.setVertex(new int[][]{
                        {+0, +0},
                        {+1, +0},
                        {+1, +1},
                        {+2, +1}
                    });
                    break;
                // ㅜ
                case 4:
                    p.setVertex(new int[][]{
                        {+0, +0},
                        {+0, +1},
                        {+0, +2},
                        {+1, +1}
                    });
                    break;
                default:
                    System.out.println("Something Wrong!");
                    break;
            }
            return p;
        }

        public void setVertex(int[][] vertex) {
            this.vertex = vertex;
        }

        public Polygon rotate(double degree) {
            if (degree == 0 || degree == 360) {
                return this;
            }

            double rad = Math.toRadians(degree);
            double[][] rotMat = new double[][]{
                {Math.cos(rad), -Math.sin(rad)},
                {Math.sin(rad), Math.cos(rad)}
            };

            int[][] rotatedVertx = new int[this.vertex.length][this.vertex[0].length];

            for (int i = 0; i < this.vertex.length; ++i) {
                rotatedVertx[i][0] =
                    (int) rotMat[0][0] * this.vertex[i][0] + (int) rotMat[0][1] * this.vertex[i][1];
                rotatedVertx[i][1] =
                    (int) rotMat[1][0] * this.vertex[i][0] + (int) rotMat[1][1] * this.vertex[i][1];
            }

            setVertex(rotatedVertx);
            return this;
        }

        ;

        // axis (0: x, 1: y)
        public Polygon reflect(int axis) {
            int[][] reflectedVertex = new int[this.vertex.length][this.vertex[0].length];

            for (int i = 0; i < this.vertex.length; ++i) {
                reflectedVertex[i][0] = (axis == 0) ? this.vertex[i][0] : -this.vertex[i][0];
                reflectedVertex[i][1] = (axis == 0) ? -this.vertex[i][1] : this.vertex[i][1];
            }

            setVertex(reflectedVertex);
            return this;
        }

        public boolean isOutOfRange(int r, int c, int height, int width) {
            for (int[] vtx : this.vertex) {
                int curR = vtx[0] + r;
                int curC = vtx[1] + c;
                if (curR < 0 || curR >= height || curC < 0 || curC >= width) {
                    return true;
                }
            }
            return false;
        }

        public int getSum(int r, int c, int[][] grid) {
            if (isOutOfRange(r, c, grid.length, grid[0].length)) {
                return -1;
            }

            int sum = 0;
            for (int[] vtx : this.vertex) {
                int curR = r + vtx[0];
                int curC = c + vtx[1];

                sum += grid[curR][curC];
            }
            return sum;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] grid = new int[n][m];

        for (int r = 0; r < n; ++r) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int c = 0; c < m; ++c) {
                grid[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        for (int r = 0; r < n; ++r) {
            for (int c = 0; c < m; ++c) {
                for (Polygon p : poly) {
                    answer = Math.max(p.getSum(r, c, grid), answer);
                }
            }
        }

        System.out.println(answer);
    }
}
