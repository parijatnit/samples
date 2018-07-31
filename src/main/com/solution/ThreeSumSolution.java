package com.solution;

import java.util.*;

public class ThreeSumSolution {

    public static void main(String[] args) {

        int[] nums = {-1, 0, 1, 2, -1, -4};

        ThreeSumSolution solution = new ThreeSumSolution();
        List<List<Integer>> list = solution.threeSum(nums);
        System.out.println("Done");
    }

    static class Pair {
        int a;
        int b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

    }

    public List<List<Integer>> threeSum(int[] nums) {
        // create empty map
        // list retList
        // Loop i=1 .. n
        //      j=i+1 .. n
        //          sum = a[i] + a[j]
        //          map.put(sum, pair(i,j))
        //          if map has key (target - nums[i)
        //              if(list NOT contains triplet)
        //
        //                  list.add(pair + nums[i])

        final int target = 0;
        Map<Integer, List<Pair>> map = new HashMap<>();
        final List<List<Integer>> triplets = new ArrayList<>();

        for(int i=0; i< nums.length; i++) {
            for(int j=i+1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                List<Pair> pairs = map.get(sum);
                if(pairs == null) {
                    pairs = new ArrayList<>();
                }
                pairs.add(new Pair(i, j));
                map.put(sum, pairs);
                List<Pair> compPairs = map.get(target - nums[i]);
                if(compPairs != null) {
                    for(Pair pair : compPairs) {
                        if (i != pair.a && i != pair.b) {
                            List<Integer> triplet = Arrays.asList(nums[i], nums[pair.a], nums[pair.b]);
                            Collections.sort(triplet);
                            if(!triplets.contains(triplet)) {
                                triplets.add(triplet);
                            }
                        }
                    }
                }
            }
        }
        return triplets;
    }




}
