package com.miaomiao.lunch.util;

import java.util.ArrayList;


public class BaseUtil {
	// Get the index of a particular string in an Array consists of string
	public static Integer indexOf(String[] arr, String str) {
		Integer index = -1;
		Integer i = 0;
		for (String temp : arr) {
			if (str.equals(temp)) {
				index = i;
				break;
			}
			i++;
		}
		return index;
	}

	// Replace a string
	public static String replaceStr(String str, String problemStr,
			String replace) {
		for (int i = str.lastIndexOf(problemStr); i >= 0; i = str.lastIndexOf(
				problemStr, i - 1)) {
			if (i == 0) {
				str = replace + str.substring(i + 1, str.length());
			} else {
				str = str.substring(0, i) + replace
						+ str.substring(i + 1, str.length());
			}
		}
		return str;
	}
	
	//Convert a string list to array list
	public static ArrayList<String> convertToList(String[] str){
		ArrayList<String> list = new ArrayList<String>();
		for(String temp : str){
			list.add(temp);
		}
		
		return list;
	}
}
