import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ9935_문자열_폭발 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String originStr = br.readLine();
        String explosiveStr = br.readLine();

        StringBuilder remains = new StringBuilder();

        for(int i = 0; i < originStr.length(); ++i) {
            remains.append(originStr.charAt(i));

            if(remains.length() >= explosiveStr.length() && explosiveStr.charAt(explosiveStr.length() - 1) == remains.charAt(remains.length() - 1)) {
                boolean isExplosive = true;

                for(int j = 0; j < explosiveStr.length(); ++j) {
                    if(remains.charAt(remains.length() - explosiveStr.length() + j) != explosiveStr.charAt(j)) {
                        isExplosive = false;
                        break;
                    }
                }

                if(isExplosive) {
                    remains.delete(remains.length() - explosiveStr.length(), remains.length());
                }
            }
        }

        System.out.println(remains.length() == 0 ? "FRULA" : remains);
    }
}
