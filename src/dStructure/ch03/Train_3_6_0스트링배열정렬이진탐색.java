package dStructure.ch03;

//comparator 구현 실습
/*
* 교재 121 실습 3-6 
* 함수(메소드)에 parameter 전달 방식을 표현하는 능력의 숙달 훈련
* 함수(메소드) 전체를 작성하는 훈련 
*/

import java.util.Arrays;

public class Train_3_6_0스트링배열정렬이진탐색 {

	private static void showData(String title, String[] data) {
		System.out.println(title);
		for(String item : data) {			
			System.out.print(item + " ");
		}
		System.out.println();
		System.out.println("=".repeat(40));
		
	}
	
	private static void sortData(String[] data) {
		for(int i = 0; i < data.length; i++) {
			for(int j = i + 1; j < data.length; j++) {
				if(data[i].compareTo(data[j]) > 0) {
					swap(data, i, j);				
				}
			}
		}		
	}

	private static void swap(String[] data, int i, int j) {
		String temp = data[i];
		data[i] = data[j];
		data[j] = temp;		
	}

	static void reverse(String[] a) {//교재 67페이지
		for(int i = 0; i < a.length / 2; i++) {
			swap(a, i, a.length - i - 1);
		}

	}
	
	private static int linearSearch(String[] data, String key) {
		for(int i = 0; i < data.length; i++) {
			if(data[i].compareTo(key) == 0) {
				return i;
			}
		}
		return 0;
	}
	
	private static int binarySearch(String[] data, String key) {
		int pl = 0;
		int pr = data.length - 1;
		
		do {
			int pc = (pl+pr) / 2;
			if(data[pc].compareTo(key) == 0) {
				return pc;
			} else if(data[pc].compareTo(key) > 1) {
				pr = pc - 1;
			} else
				pl = pc + 1;
		} while(pl <= pr);
				
		return 0;
	}
	
	
	public static void main(String[] args) {
		String []data = {"사과","포도","복숭아", "감", "산딸기", "블루베리", "대추", "수박", "참외"};//홍봉희 재배 과수

		showData("정렬전", data);
		
		String []data1 = data.clone();
		sortData(data1);
		showData("정렬후", data1);
		
		String []data2 = data.clone();
		reverse(data2); //역순으로 재배치
		showData("역순 재배치후", data2);
		
		String []data3 = data.clone();
		Arrays.sort(data3); //Arrays.sort(Object[] a);
		showData("Arrays.sort()로 정렬후",data3);
    
		String key = "포도";
		int resultIndex = linearSearch(data, key);
		System.out.println("\nlinearSearch(포도): result = " + (resultIndex + 1) + " 번째 요소");

		key = "수박";
		/*
		 * 교재 109~113
		 */
		resultIndex = binarySearch(data, key);
		System.out.println("\nbinarySearch(배): result = " + (resultIndex + 1) + " 번째 요소");
		key = "산딸기";
		/*
		 * 교재 115 Arrays.binarySearch에 의한 검색
		 */
		resultIndex = Arrays.binarySearch(data, key);
		System.out.println("\nArrays.binarySearch(산딸기): result = " + (resultIndex + 1) + " 번째 요소");

	}


}
