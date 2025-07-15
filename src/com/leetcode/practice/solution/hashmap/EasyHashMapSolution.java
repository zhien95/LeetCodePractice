package com.leetcode.practice.solution.hashmap;

import java.util.*;

public class EasyHashMapSolution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Integer> map = new HashMap<>();

        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (char c : t.toCharArray()) {
            if (!map.containsKey(c)) return false;
            map.put(c, map.get(c) - 1);
            if (map.get(c) < 0) return false;
        }

        return true;
    }

    //https://leetcode.com/problems/ransom-note/description/?envType=study-plan-v2&envId=top-interview-150
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> countMap = new HashMap<>();

        for (char c : magazine.toCharArray()) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }

        for (char c : ransomNote.toCharArray()) {
            int count = countMap.getOrDefault(c, 0);
            if (count == 0) return false;
            countMap.put(c, --count);
        }

        return true;
    }

    //https://leetcode.com/problems/isomorphic-strings/description/?envType=study-plan-v2&envId=top-interview-150
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Character> sToT = new HashMap<>();
        Map<Character, Character> tToS = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);

            if (sToT.containsKey(sc)) {
                if (sToT.get(sc) != tc) {
                    return false;
                }
            } else {
                if (tToS.containsKey(tc)) {
                    return false;
                }
                sToT.put(sc, tc);
                tToS.put(tc, sc);
            }
        }

        return true;
    }

    //https://leetcode.com/problems/word-pattern/?envType=study-plan-v2&envId=top-interview-150
    public boolean wordPattern(String pattern, String s) {
        Map<Character, String> charToWord = new HashMap<>();
        Map<String, Character> wordToChar = new HashMap<>();
        String[] words = s.split(" ");

        if (pattern.length() != words.length) {
            return  false;
        }

        for (int i =0; i < pattern.length(); i++){
            char c = pattern.charAt(i);
            String word = words[i];

            if(charToWord.containsKey(c)){
                if (!charToWord.get(c).equals(word)){
                    return false;
                }
            } else {
                if(wordToChar.containsKey(word)){
                    return false;
                }
                charToWord.put(c, word);
                wordToChar.put(word,c);

            }
        }
        return true;
    }

    //https://leetcode.com/problems/happy-number/description/?envType=study-plan-v2&envId=top-interview-150
    public boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<>();
        int sum;
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            sum = 0;
            String numStr = String.valueOf(n);
            int size = numStr.length();
            for (int i =0; i < size; i++){
                int x = Character.getNumericValue((numStr.charAt(i)));
                sum += x * x;
            }
            n = sum;
        }
        return n == 1;
    }

    //https://leetcode.com/problems/contains-duplicate-ii/description/?envType=study-plan-v2&envId=top-interview-150
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();  // num -> last index

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            if (map.containsKey(num)) {
                int prevIndex = map.get(num);
                if (i - prevIndex <= k) {
                    return true;
                }
            }

            map.put(num, i);  // update latest index
        }

        return false;
    }
}
