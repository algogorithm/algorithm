package Week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_3107 {
    static final String[] zeroString = {"","0","00","000","0000"};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        // split으로 나누면 ::의 경우 빈칸으로 나온다.
        for(String s : br.readLine().split(":")){
            if(!s.equals("")){
                s = zeroString[4-s.length()] + s;
            }
            list.add(s);
        }
//        System.out.println(list);
        if(list.isEmpty()){
            System.out.println("0000:0000:0000:0000:0000:0000:0000:0000");
            return;
        }
        // ::이 들어가서 빈칸이 생긴 위치값
        // 해당하는게 없으면 -1반환
        int coloneIdx = list.indexOf("");
        // ::없이 전체 주소가 들어왔다면 아래 조건을 만족한다.
        if(list.size() == 8 && coloneIdx == -1){
            System.out.println(makeFullIpAdd(list, sb));
            return;
        }
        // ::가 맨 앞에있을때(list의 맨 앞이 ""임)
        // ::1
        //[, , 0001]
        if(list.get(0).equals("")){
            while(list.size() <= 9){
                list.add(coloneIdx, zeroString[4]);
            }
        }
        // ::가 맨 뒤에있을때(colon값의 변화는 없지만 list.size가 8이 아님)
        // 1::
        //[0001]
        else if(list.size() != 8 && coloneIdx == -1){
            while(list.size() < 8){
                list.add(zeroString[4]);
            }
        }
        // ::가 중간에 있을때
        else{
            while(list.size() <= 8){
                list.add(coloneIdx, zeroString[4]);
            }
        }

        System.out.println(makeFullIpAdd(list, sb));

    }

    private static String makeFullIpAdd(List<String> list, StringBuilder sb) {
        for(int i = 0; i < list.size(); i++){
            if(!list.get(i).equals("")){
                sb.append(list.get(i));
                if(i != list.size()-1){
                    sb.append(":");
                }
            }
        }
        return sb.toString();
    }
}
