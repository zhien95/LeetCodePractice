package com.leetcode.practice.solution.backtracking;

import java.util.*;

public class MediumBackTrackingSolution {
    /**
     * [Letter Combinations of a Phone Number]
     * <p>
     * Given a string containing digits from 2-9 inclusive, returns all possible letter combinations
     * that the number could represent based on traditional telephone button mappings.
     * Uses backtracking to generate all combinations.
     *
     * @param digits String containing digits from 2-9
     * @return List of all possible letter combinations
     */
//https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/?envType=study-plan-v2&envId=top-interview-150
    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) {
            return new ArrayList<>();
        }

        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        List<String> result = new ArrayList<>();
        letterCombinationsBackTrack(digits, result, new StringBuilder(), map, 0);
        return result;
    }

    public void letterCombinationsBackTrack(String digits, List<String> res, StringBuilder builder, Map<Character, String> map, int index) {
        if (index == digits.length()) {
            res.add(builder.toString());
            return;
        }

        for (char c : map.get(digits.charAt(index)).toCharArray()) {
            builder.append(c);
            letterCombinationsBackTrack(digits, res, builder, map, index + 1);
            builder.deleteCharAt(builder.length() - 1);
        }
    }


    /**
     * [Combinations]
     * <p>
     * Returns all possible combinations of k numbers chosen from the range [1, n].
     * Uses backtracking to generate all valid combinations.
     *
     * @param n The upper bound of the range [1, n]
     * @param k The number of elements in each combination
     * @return List of all possible combinations
     */
//https://leetcode.com/problems/combinations/description/?envType=study-plan-v2&envId=top-interview-150
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        combineBackTrack(n, res, new ArrayList<>(), k, 1);

        return res;
    }


    public void combineBackTrack(int n, List<List<Integer>> res, List<Integer> temp, int k, int startIdx) {
        if (temp.size() == k) {
            res.add(new ArrayList<>(temp));
            return;
        }

        for (int i = startIdx; i <= n; i++) {
            temp.add(i);
            combineBackTrack(n, res, temp, k, i + 1);
            temp.remove(temp.size() - 1);
        }
    }

    /**
     * [Permutations]
     * <p>
     * Returns all possible permutations of an array of distinct integers.
     * Uses backtracking to generate all permutations by tracking used elements.
     *
     * @param nums Array of distinct integers
     * @return List of all possible permutations
     */
//https://leetcode.com/problems/permutations/?envType=study-plan-v2&envId=top-interview-150
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permuteBackTrack(nums, result, new ArrayList<>(), new boolean[nums.length]);
        return result;
    }

    void permuteBackTrack(int[] nums, List<List<Integer>> result, List<Integer> temp, boolean[] used) {
        if (temp.size() == nums.length) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            temp.add(nums[i]);
            //mark used
            used[i] = true;
            //recursive call down and backtrack
            permuteBackTrack(nums, result, temp, used);
            //mark unused
            temp.remove(temp.size() - 1);
            used[i] = false;
        }
    }


    //https://leetcode.com/problems/permutations-ii/
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        permuteUniqueBackTrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
        return list;
    }

    private void permuteUniqueBackTrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, boolean[] used) {
        if (tempList.size() == nums.length) {
            list.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) continue;
            used[i] = true;
            tempList.add(nums[i]);
            permuteUniqueBackTrack(list, tempList, nums, used);
            used[i] = false;
            tempList.remove(tempList.size() - 1);
        }

    }

    //https://leetcode.com/problems/subsets/
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        subsetsBacktrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void subsetsBacktrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
        list.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            subsetsBacktrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    //https://leetcode.com/problems/subsets-ii/
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        subsetsWithDupBacktrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void subsetsWithDupBacktrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
        list.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue; // skip duplicates
            tempList.add(nums[i]);
            subsetsWithDupBacktrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }


    /**
     * 生成数组的所有子数组
     *
     * @param nums 输入数组
     * @return 所有子数组的列表
     */
    public List<List<Integer>> getAllSubarrays(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            List<Integer> currentSubarray = new ArrayList<>();
            for (int j = i; j < nums.length; j++) {
                currentSubarray.add(nums[j]);
                // 每次扩展子数组时都添加到结果中
                result.add(new ArrayList<>(currentSubarray));
            }
        }

        return result;
    }

    //https://leetcode.com/problems/combination-sum/
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums == null || nums.length == 0 || target < 0) {
            return list;
        }
        Arrays.sort(nums);
        // 如果最小值大于目标值，则不可能有解
        if (nums[0] > target) {
            return list;
        }
        combinationSumBackTrack(list, new ArrayList<>(), nums, target, 0);
        return list;
    }

    private void combinationSumBackTrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start) {
        if (remain < 0) return;
        else if (remain == 0) list.add(new ArrayList<>(tempList));
        else {
            for (int i = start; i < nums.length; i++) {
                tempList.add(nums[i]);
                combinationSumBackTrack(list, tempList, nums, remain - nums[i], i); // not i + 1 because we can reuse same elements
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    //Combination Sum II (can't reuse same element) : https://leetcode.com/problems/combination-sum-ii/
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        combinationSum2BackTrack(list, new ArrayList<>(), nums, target, 0);
        return list;

    }

    private void combinationSum2BackTrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start) {
        if (remain < 0) return;
        else if (remain == 0) list.add(new ArrayList<>(tempList));
        else {
            for (int i = start; i < nums.length; i++) {
                if (i > start && nums[i] == nums[i - 1]) continue; // skip duplicates
                tempList.add(nums[i]);
                combinationSum2BackTrack(list, tempList, nums, remain - nums[i], i + 1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    /**
 * [Palindrome Partitioning]
 */
//https://leetcode.com/problems/palindrome-partitioning/
    public List<List<String>> partition(String s) {
        List<List<String>> list = new ArrayList<>();
        if (s == null) {
            return list;
        }
        if (s.length() == 0) {
            list.add(new ArrayList<>());
            return list;
        }
        partitionBacktrack(list, new ArrayList<>(), s, 0);
        return list;
    }

    private void partitionBacktrack(List<List<String>> result, List<String> tempList, String s, int startIdx) {
        //end of string, add to result
        if (startIdx >= s.length()) {
            result.add(new ArrayList<>(tempList));
        }

        for (int i = startIdx; i < s.length(); i++) {
            if (isPalindrome(s, startIdx, i)) {
                tempList.add(s.substring(startIdx, i + 1));
                partitionBacktrack(result, tempList, s, i + 1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int start, int end) {
        while (start <= end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }

        return true;
    }

    public class PermuteWithDuplicates {

        public static List<List<Integer>> permutePrune(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            boolean[] used = new boolean[nums.length];
            int[] lastPerm = new int[nums.length]; // last full permutation
            Arrays.fill(lastPerm, Integer.MIN_VALUE); // sentinel
            backtrack(nums, new ArrayList<>(), used, res, lastPerm);
            return res;
        }

        private static void backtrack(int[] nums, List<Integer> path, boolean[] used, List<List<Integer>> res, int[] lastPerm) {
            if (path.size() == nums.length) {
                // Add current permutation
                res.add(new ArrayList<>(path));
                // Update lastPerm
                for (int i = 0; i < nums.length; i++) lastPerm[i] = path.get(i);
                return;
            }

            for (int i = 0; i < nums.length; i++) {
                if (used[i]) continue; // already used in current path

                // Prune: check if current path + nums[i] is already smaller than lastPerm
                boolean prune = false;
                for (int j = 0; j < path.size(); j++) {
                    if (path.get(j) < lastPerm[j]) {
                        prune = true;
                        break;
                    } else if (path.get(j) > lastPerm[j]) {
                        break;
                    }
                }
                // Check current number at this depth
                if (!prune && path.size() < lastPerm.length && nums[i] < lastPerm[path.size()]) {
                    prune = true;
                }

                if (prune) continue;

                // Choose
                path.add(nums[i]);
                used[i] = true;

                backtrack(nums, path, used, res, lastPerm);

                // Backtrack
                used[i] = false;
                path.remove(path.size() - 1);
            }
        }

        public static void main(String[] args) {
            int[] nums = {3, 1, 2, 2}; // duplicates allowed
            List<List<Integer>> permutations = permutePrune(nums);
            for (List<Integer> p : permutations) {
                System.out.println(p);
            }
        }
    }

}