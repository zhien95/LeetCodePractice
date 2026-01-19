package com.leetcode.practice.solution.dsu;

import java.util.*;

/**
 * @author limzhien
 * @date 18/1/26
 */
public class DsuMediumSolution {
    //disjoint union set
    //use first email as anchor (parent/root) for each list of accounts
    //do a union to accounts for each grow
    //union find parent to establish all the emails based on anchor
    //merge the name and all the emails tgt as output

    private Map<String, String> nodeToParent = new HashMap<>();

    //method to find parent
    private String find(String x) {
        if (!nodeToParent.get(x).equals(x)) {
            nodeToParent.put(x, find(nodeToParent.get(x)));
        }

        return nodeToParent.get(x);
    }


    private void union(String a, String b) {
        String rootA = find(a);
        String rootB = find(b);

        if (!rootA.equals(rootB)) {
            nodeToParent.put(rootB, rootA);
        }

    }

    /**
     *
     * Disjoined set union
     *
     * @param accounts list of accounts, where account[0] = name and the rest are emails
     * @return merged accounts where account[0] = name, and remaining are email sorted lexigraphically
     */
    //https://leetcode.com/problems/accounts-merge/
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> emailToName = new HashMap<>();

        // Step 1: Initialize Union Find
        for (List<String> account : accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);

                nodeToParent.put(email, email); //init email as parents of itself
                emailToName.put(email, name); //record email and owner
            }
        }

        // step 2: union emails of the same account
        for (List<String> account : accounts) {
            String firstEmail = account.get(1);
            for (int i = 2; i < account.size(); i++) {
                String email = account.get(i);
                union(firstEmail, email);
            }
        }

        Map<String, List<String>> groups = new HashMap<>();
        //step 3 group emails by parent
        for (String email : nodeToParent.keySet()) {
            String root = find(email);
            groups.computeIfAbsent(root, k -> new ArrayList<>()).add(email);
        }

        //step 4 form result
        List<List<String>> result = new ArrayList<>();

        for (String key : groups.keySet()) {
            List<String> merged = new ArrayList<>();
            List<String> emails = groups.get(key);
            Collections.sort(emails);
            String name = emailToName.get(emails.get(0));
            merged.add(name);
            merged.addAll(emails);

            result.add(merged);
        }

        return result;
    }

}
