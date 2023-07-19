package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class B_2179 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		ArrayList<String> originList = new ArrayList<String>();
		ArrayList<String> sortList = new ArrayList<String>();
		
		for (int i = 0; i < N; i++) {
			String word = br.readLine();
			
			if(!map.containsKey(word)) {
				map.put(word, i);
				originList.add(word);
				sortList.add(word);
			}
		}
		
		Collections.sort(sortList);
		
		HashSet<String> wordSet = new HashSet<String>();
		int maxLength = 0, indexS = 0, indexT = 0;
		
		for(int i=0; i<sortList.size()-1; i++) {
			if(sortList.get(i).charAt(0) != sortList.get(i+1).charAt(0))	continue;
			
			int length = 0;
			for(int j=0; j<Math.min(sortList.get(i).length(), sortList.get(i+1).length()); j++) {
				if(sortList.get(i).charAt(j) == sortList.get(i+1).charAt(j))	length++;
				else	break;
			}
			
			if(length < maxLength || wordSet.contains(sortList.get(i).substring(0, length)))	continue;
			
			String prefix = sortList.get(i).substring(0, length);
			ArrayList<Integer> indexList = new ArrayList<Integer>();
			wordSet.add(prefix);
			indexList.add(map.get(sortList.get(i)));
			indexList.add(map.get(sortList.get(i+1)));
			
			for(int j=i+2; j<sortList.size(); j++) {
				if(!sortList.get(j).startsWith(prefix))	break;
				indexList.add(map.get(sortList.get(j)));
			}
			
			Collections.sort(indexList);

			if(maxLength < length) {
				indexS = indexList.get(0);
				indexT = indexList.get(1);
				maxLength = length;
			} else if(indexS > indexList.get(0) || (indexS == indexList.get(0) && indexT > indexList.get(1))) {
				indexS = indexList.get(0);
				indexT = indexList.get(1);
			}
		}

		System.out.print(originList.get(indexS)+"\n"+originList.get(indexT));
	}
}