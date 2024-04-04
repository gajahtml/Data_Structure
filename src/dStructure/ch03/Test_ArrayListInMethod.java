package dStructure.ch03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test_ArrayListInMethod {
	
	public static void main(String[] args) {
		String [] arr1 = {"a","j", "k","l","f","d","w","t"};
		System.out.println(Arrays.toString(arr1));
		
		ArrayList<String> lst1 = new ArrayList<>();
		
		makeList(arr1, lst1);
		
		System.out.println(lst1);
		
	}

	private static void makeList(String[] arr1, ArrayList<String> lst1) {
		lst1.addAll(Arrays.asList(arr1));
		
	}
}
