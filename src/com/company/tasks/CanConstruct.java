package com.company.tasks;

import java.util.ArrayList;
import java.util.List;

public class CanConstruct {
    public static boolean canConstruct(String n, String[] array){
        if(n.isEmpty()){
            return true;
        }

        for (int i = 0; i < array.length; i++) {
            if(n.indexOf(array[i]) == 0) {
                String subString = n.substring(array[i].length());
                if (canConstruct(subString, array)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int canConstructCount(String n, String[] array){
        if(n.isEmpty()){
            return 1;
        }

        int count = 0;

        for (int i = 0; i < array.length; i++) {
            if(n.indexOf(array[i]) == 0) {
                String subString = n.substring(array[i].length());
                if (canConstructCount(subString, array) == 1) {
                    ++count;
                }
            }
        }
        return count;
    }

    public static List<String> canConstructPossible(String n, String[] array){
        if(n.isEmpty()){
            return new ArrayList<>();
        }

        for (int i = 0; i < array.length; i++) {
            if(n.indexOf(array[i]) == 0) {
                String subString = n.substring(array[i].length());
                List<String> how = canConstructPossible(subString, array);
                if (how != null) {
                    how.add(array[i]);
                    return how;
                }
            }
        }
        return null;
    }

    public static List<List<String>> canConstructAll(String n, String[] array){
        if(n.isEmpty()){
            List<List<String>> subList = new ArrayList<>();
            List<String> inner = new ArrayList<>();
            subList.add(inner);
            return subList;
        }

        List<List<String>> list = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            if(n.indexOf(array[i]) == 0) {
                String subString = n.substring(array[i].length());
                var how = canConstructAll(subString, array);
                if (!how.isEmpty()) {
                    how.get(0).add(array[i]);
                    list.add(how.get(0));
                }
            }
        }
        return list;
    }

}
