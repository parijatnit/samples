package com.practice.parameterized;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static junitparams.JUnitParamsRunner.$;

@RunWith(JUnitParamsRunner.class)
public class Solution {


    @Test
    @Parameters({
            "-121,false",
            "121,true",
            "1,true",
            "0,true",
            "10,false",
            "1001,true"
    })
    public void testPalindromeNumber(final int x, final boolean expected) {
        // validate for direct use cases
        //       - return false
        //       1 digit true
        // else
        //    find number of digits
        //


    }

    static class Record {
        int index;
        boolean done;

        Record(int index, boolean done) {
            this.index = index;
            this.done = done;
        }
    }


    @Test
    @Parameters(method="testParams")
    public void test2Sum(final int[] nums, final int sum, final List<List<Integer>> expected) {
        // Loop through
        // if( map NOT contains)
            //    put =>
            //    key : complement
            //    val : index
        // else
        //      record in return list

        Map<Integer, Record> map = new HashMap<>();
        List<List<Integer>> actualList = new ArrayList<>();
        for(int i=0; i< nums.length; i++) {
            if(!map.containsKey(nums[i])) {
                map.put(sum - nums[i], new Record(i, false));
            } else {
                Record record = map.get(nums[i]);
                if(!record.done) {
                    List<Integer> list = new ArrayList<>(Arrays.asList(nums[i], nums[map.get(nums[i]).index]));
                    Collections.sort(list);
                    actualList.add(list);
                    record.done = true;
                }
            }
        }
        assertEquals(actualList, expected);
    }

    public Object testParams() {
        int[] a1 = {1,12,11,10,9,13,13,13};
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(11,12));
        list.add(Arrays.asList(10,13));

        return $(
                $(a1, 23, list),
                $(a1, 23, list)
        );
    }







}
