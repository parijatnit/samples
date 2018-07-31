package com.solution;

import java.util.*;

public class ThreeSumNewSolution {

    public static void main(String[] args) {
        ThreeSumNewSolution solution = new ThreeSumNewSolution();
        //int[] nums = {-1, 0, 1, 2, -1, -4};
        int[] nums = {0,0,0,0};
        List<List<Integer>> list = solution.threeSum(nums);

        System.out.println("Done");

    }

    public List<List<Integer>> threeSum(int[] nums) {
        final Set<List<Integer>> triplets = new HashSet<>();
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            int j = i + 1;
            int k = (n - 1);

            while (j < k) {
                while (j<k && (nums[j] == nums[j + 1])) {
                    j++;
                }

                while (j<k && nums[k] == nums[k - 1]) {
                    k--;
                }

                if (nums[i] + nums[j] + nums[k] == 0) {
                    List<Integer> triplet = new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k]));
                    triplets.add(triplet);
                }
                j++;
                k--;
            }
        }
        return new ArrayList<>(triplets);
    }
}


