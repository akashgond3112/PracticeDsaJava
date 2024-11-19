package main.dsa.slidingWindow.variableSize;

import java.util.HashMap;
import java.util.Map;

public class PickMaximumNumberOfToys {

    public static int longestKSubstr(String s) {
        int k=2;
        if (s.length() < k) return -1;
        int startIndex = 0;
        int endIndex = 0;
        int max = Integer.MIN_VALUE; // Initialize max with a smaller value
        Map<Character, Integer> characterHashMap = new HashMap<>();

        while (endIndex < s.length()) {
            char ch = s.charAt(endIndex);
            characterHashMap.put(ch, characterHashMap.getOrDefault(ch, 0) + 1);

            if (characterHashMap.size() < k) {
                endIndex++;
            } else if (characterHashMap.size() == k) {
                max = Math.max(max, endIndex - startIndex + 1);
                endIndex++;
            } else {
                while (characterHashMap.size() > k) {

                    if (characterHashMap.containsKey(s.charAt(startIndex))) {
                        int freq = characterHashMap.get(s.charAt(startIndex));
                        freq--;
                        characterHashMap.put(s.charAt(startIndex), freq);
                    }
                    if (characterHashMap.get(s.charAt(startIndex)) == 0) {
                        characterHashMap.remove(s.charAt(startIndex));
                    }
                    startIndex++;
                }
                endIndex++;
            }
        }
        return (characterHashMap.size() < k) ? 0 : max;
    }

    public static void main(String[] args) {
        String txt = "ababccbbba";
        System.out.println(longestKSubstr(txt));

    }
}
