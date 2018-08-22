package com.es.datasearch.util;

public class ConvertArray {

    public static int[] convertArray(String str) {
        char[] arr;
        int[] intArr = new int[str.length()];
        arr = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            intArr[i] = arr[i] - 48;//数字0~9的ASCII码为48~57
        }
        return intArr;
    }

}
