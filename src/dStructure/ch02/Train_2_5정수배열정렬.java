package dStructure.ch02;

import java.util.Arrays;
//교재 67 - 실습 2-5
//2번 실습
import java.util.Random;

public class Train_2_5정수배열정렬 {

	public static void main(String[] args) {
		int []data = new int[10];
		
		inputData(data);
		System.out.println("input Data");
		showData(data);

		int []data1 = data.clone();
		sortData(data1);
		System.out.println("sort Data");
		showData(data1);
		
		int []data2 = data.clone();
		reverse(data2);//역순으로 재배치 - 정렬 아님 
		System.out.println("reverse Data");
		showData(data2);

		int []data3 = data.clone();
		reverseSort(data3);//역순으로 재배치 - 정렬 아님 
		System.out.println("reverseSort Data");
		showData(data3);		
		
		System.out.println("라이브러리 Arrays.sort Data");
		Arrays.sort(data);	// 자바 라이브러리 - 정렬
		showData(data);
				
	}
	static void showData(int[]data) {
		
		for(int i : data) {
			System.out.print(i + " ");
		}
		System.out.println();
		System.out.println("=".repeat(30));

	}
	static void inputData(int []data) {	// 난수 생성해서 데이터 입력
		
		Random rd = new Random();
		
		for(int i = 0; i < data.length; i++) {
			data[i] = rd.nextInt(100);			
		}

	}
	static void swap(int[]arr, int ind1, int ind2) {//교재 67페이지
		
		int temp = arr[ind1];
		arr[ind1] = arr[ind2];
		arr[ind2] = temp;
		
	}
	
	static void sortData(int []arr) {	// 오름차순 정렬
		for(int i = 0; i < arr.length; i++) {
			for(int j = i + 1; j < arr.length; j++) {
				if(arr[i] > arr[j]) {
					swap(arr, i, j);
				}
			}
		}

	}
	static void reverse(int[] a) {//교재 67페이지
		
		for(int i = 0; i < a.length / 2; i++) {
			swap(a, i, a.length - i - 1);
		}

	}
	static void reverseSort(int []arr) {	// 내림차순 정렬
		
		for(int i = 0; i < arr.length; i++) {
			for(int j = i + 1; j < arr.length; j++) {
				if(arr[i] < arr[j]) {
					swap(arr, j, i);
				}
			}
		}
	}
}
