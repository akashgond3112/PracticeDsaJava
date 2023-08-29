package main.slidingWindow.variableSize;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {

    public static int longestKSubstr(String s) {
        if(s.isEmpty()) return 0;
        int startIndex = 0;
        int endIndex = 0;
        int max = Integer.MIN_VALUE; // Initialize max with a smaller value

        Map<Character, Integer> characterHashMap = new HashMap<>();

        while (endIndex < s.length()) {
            char ch = s.charAt(endIndex);
            characterHashMap.put(ch, characterHashMap.getOrDefault(ch, 0) + 1);

            if (characterHashMap.size() > (endIndex - startIndex + 1)) {
                endIndex++;
            } else if (characterHashMap.size() == (endIndex - startIndex + 1)) {
                max = Math.max(max, endIndex - startIndex + 1);
                endIndex++;
            } else {
                while (characterHashMap.size() < (endIndex - startIndex + 1)) {

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
        return  max;
    }

    public static void main(String[] args) {
        String txt = "bbbbb";
        System.out.println(longestKSubstr(txt));

    }

}
