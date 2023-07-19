import java.util.PriorityQueue;
import java.util.Scanner;

public class BJ15903 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        PriorityQueue<Long> cards = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            cards.add(sc.nextLong());
        }

        for (int i = 0; i < m; i++) {
            long card1 = cards.poll();
            long card2 = cards.poll();
            long newCard = card1 + card2;
            cards.add(newCard);
            cards.add(newCard);
        }

        long sum = 0;
        for (long card : cards) {
            sum += card;
        }

        System.out.println(sum);
    }
}

