package com.company.string;

public class AlternativeString {

    public static String toAlternativeString(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < string.length(); i++){
            if(Character.isUpperCase(string.charAt(i))){
                stringBuilder.append(Character.toLowerCase(string.charAt(i)));
            }
            else{
                stringBuilder.append(Character.toUpperCase(string.charAt(i)));
            }
        }
        return stringBuilder.toString();
    }

}
