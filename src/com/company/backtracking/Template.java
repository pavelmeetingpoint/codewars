package com.company.backtracking;

import java.util.List;

public class Template {

    public static void solve(int n){
        var solutions = List.<String>of();
        var state = List.<String>of();
        search(state, solutions, n);
    }

    private static void search(List<String> state, List<String> solutions, int n){
        if(isStateValid(state, n)){
            solutions.addAll(state);
            // return; one or many
        }
        for(String candidate : getCandidates(state)){
            state.add(candidate);
            search(state, solutions, n);
            state.remove(candidate);
        }

    }

    // state usually is number of found solutions, and n is input of required solutions
    private static boolean isStateValid(List<String> state, int n){
        return true;
    }

    private static List<String> getCandidates(List<String> state){
        return List.of();
    }

}
