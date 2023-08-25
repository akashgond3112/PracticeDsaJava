package main.slidingWindow.fixedSize;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CountOccurenceOfAnagrams {

    /*Expected Output
    Input:
    txt = forxxorfxdofr
    pat = for
    Output: 3
    Explanation: for, orf and ofr appears
    in the txt, hence answer is 3.
    */

    public static int solution(String pat, String txt) {
        int startIndex = 0;
        int endIndex = 0;
        int ans = 0;

        if (txt.length() <= 0 || pat.length() <= 0) return ans;

        Map<Character, Integer> characterHashMap = new HashMap();

        for (int z = 0; z < pat.length(); z++) {
            characterHashMap.put(pat.charAt(z), characterHashMap.getOrDefault(pat.charAt(z), 0) + 1);
        }
        int cnt = characterHashMap.size();

        while (endIndex < txt.length()) {
            if (characterHashMap.containsKey(txt.charAt(endIndex))) {
                char currentChar = txt.charAt(endIndex);
                int count = characterHashMap.get(currentChar);
                count -= 1;
                characterHashMap.put(currentChar, count);
                if (count == 0) cnt--;
            }

            if (endIndex - startIndex + 1 < pat.length()) {
                endIndex++;
            } else if (endIndex - startIndex + 1 == pat.length()) {
                if (cnt == 0) ans++;

                /*Reverse the changes*/
                if (characterHashMap.containsKey(txt.charAt(startIndex))) {
                    char currentChar = txt.charAt(startIndex);
                    int count = characterHashMap.get(currentChar);
                    count += 1;
                    characterHashMap.put(currentChar, count);
                    if (count == 1) cnt++;
                }
                startIndex++;
                endIndex++;

            }


        }
        return ans;

    }

    public static void main(String[] args) {
        String pat = "for";
        String txt = "forxxorfxdofr";
        System.out.println(solution(pat, txt));
    }
}
