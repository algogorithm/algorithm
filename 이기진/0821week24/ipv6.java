package d202308;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class ipv6 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String ipv6 = bf.readLine();
        StringTokenizer st = new StringTokenizer(ipv6,":");
        //25:09:1985:aa:091:4846:374:bb
        int count = st.countTokens();
        System.out.println(count);
        int delete = 8-count;
        int index=0;
        String[] str=new String[8];
        int i=0;
        if(ipv6.charAt(0)==':'){
            System.out.println("앞");
            i=delete;
            while(i<8){
                str[i]=st.nextToken();
                System.out.println(i+"번째 "+str[i]);
                while(index!=ipv6.length()&&ipv6.charAt(index++)!=':'){
                }
                i++;
            }
        } else{
            System.out.println("뒤");
            while(i<8){
                str[i]=st.nextToken();
                System.out.println(i+"번째 "+str[i]);
                while(index!=ipv6.length()&&ipv6.charAt(index++)!=':'){
                }
                if(index!=ipv6.length()&&ipv6.charAt(index)==':')
                    i+=delete;
                i++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int j=0;j<8;j++){
            if(str[j]==null){
                str[j]="0000";
            } else{
                int len = str[j].length();
                if(len==1){
                    str[j]= "000" + str[j];
                }else if(len==2){
                    str[j]= "00" + str[j];
                }else if(len==3){
                    str[j]= "0" + str[j];
                }
            }
            sb.append(str[j]).append(":");
        }
        sb.setLength(sb.length()-1);
        System.out.println(sb);
        bf.close();
    }
}