package com.solution;

import java.util.*;
import java.util.stream.Collectors;

public class NearByCharacterSolution {

    public List<Character> getNearBy(Character ch) {
        return new ArrayList<>();
    }

    public boolean isWord(String s) {
        return true;
    }

    public Set<String> nearByWords(String word) {
        Set<String> combinations = nearByCombinations(word, new HashSet<>(Collections.singleton("")));
        return combinations.stream().filter(this::isWord).collect(Collectors.toSet());
    }

    private Set<String> nearByCombinations(String combination, Set<String> currentList) {
        if(combination.length() == 0) {
            return currentList;
        }
        List<Character> nearByChars = getNearBy(combination.charAt(0));
        Set<String> newList = new HashSet<>();
        for(String s : currentList) {
            for (char ch : nearByChars) {
                String newCombination = s + ch;
                newList.add(newCombination);
            }
        }

        return nearByCombinations(combination.substring(1, combination.length()), newList);
    }







}
