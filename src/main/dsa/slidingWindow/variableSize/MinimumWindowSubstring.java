package main.dsa.slidingWindow.variableSize;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    public static String minWindow(String s, String t) {

        if (s.equals(t)) return s;
        if (s.length() < t.length()) return "";
        int startIndex = 0;
        int endIndex = 0;
        int max = Integer.MAX_VALUE; // Initialize max with a smaller value
        int count = 0;

        Map<Character, Integer> characterHashMap = new HashMap<>();

        for (char c : t.toCharArray()
        ) {
            characterHashMap.put(c, characterHashMap.getOrDefault(c, 0) + 1);
        }

        count = characterHashMap.size();

        int maxStart = 0; // to track Start index of substring
        int maxEnd = 0; // to track End index of substring

        while (endIndex < s.length()) {
            char ch = s.charAt(endIndex);
            if (characterHashMap.containsKey(ch)) {
                int freq = characterHashMap.get(s.charAt(endIndex));
                freq--;
                characterHashMap.put(ch, freq);
                if (characterHashMap.get(ch) == 0) count--;

            }

            while (count == 0) {

                //Get best solution
                if (max > endIndex - startIndex + 1) {
                    max = endIndex - startIndex + 1;
                    maxStart = startIndex;
                    maxEnd = endIndex + 1;
                }

                char tempCharStart = s.charAt(startIndex);
                if (characterHashMap.containsKey(tempCharStart)) {
                    int freq = characterHashMap.get(tempCharStart);
                    freq++;
                    characterHashMap.put(tempCharStart, freq);
                    if (characterHashMap.get(tempCharStart) > 0) {
                        count++;
                    }
                }
                startIndex++;
            }
            endIndex++;
        }

        return s.substring(maxStart, maxEnd);

    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
//        System.out.println(solution(s, t));
        System.out.println(minWindow(s, t));

    }
}
