import java.io.*;
import java.util.*;

public class BOJ2447_별_찍기_10 {

    static char[][] starMap;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        starMap = new char[n][n];

        fillStarMap(n, 0, 0, false);
        printStarMap();
    }

    static public void fillStarMap(int size, int r, int c, boolean isBlank) {
        if (size == 1) {
            starMap[r][c] = (isBlank) ? ' ' : '*';
            return;
        }

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                fillStarMap(size / 3, r + i * (size / 3), c + j * (size / 3), (isBlank) ? true : (i == 1 && j == 1));
            }
        }
    }

    static public void printStarMap() {
        for(char[] row : starMap) {
            System.out.println(String.copyValueOf(row));
        }
    }
}
