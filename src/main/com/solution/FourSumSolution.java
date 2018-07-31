package com.solution;

import java.util.*;
import java.util.stream.Collectors;

public class FourSumSolution {

    public static void main(String[] args) {

        FourSumSolution soln = new FourSumSolution();

        int[] nums = {-3, -2, -1, 0, 0, 1, 2, 3};
        List<List<Integer>> quadruplets = soln.fourSum(nums, 0);
        System.out.println("done");
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        final Set<List<Integer>> fourSumSet = new HashSet<>();
        final Map<Integer, List<List<Integer>>> pairSumMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                List<List<Integer>> indexPairs = pairSumMap.get(target - sum);

                if (indexPairs != null) {
                    for (List<Integer> indexPair : indexPairs) {
                        if (!indexPair.contains(i) && !indexPair.contains(j)) {
                            // quadruplets identified
                            List<Integer> quadruplets = new ArrayList<>(Arrays.asList(nums[indexPair.get(0)], nums[indexPair.get(1)], nums[i], nums[j]));
                            Collections.sort(quadruplets);
                            fourSumSet.add(quadruplets);
                        }
                    }
                }
                List<Integer> indexPair = new ArrayList<>(Arrays.asList(i, j));
                indexPairs = pairSumMap.get(sum);

                if (indexPairs == null) {
                    indexPairs = new ArrayList<>();
                    indexPairs.add(indexPair);
                } else {
                    indexPairs.add(indexPair);
                }
                pairSumMap.put(sum, indexPairs );
            }
        }
        return new ArrayList<>(fourSumSet);
    }

    // [-3,0, 1,2]
    // [-3,0,0,3]
}
