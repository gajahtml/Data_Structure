package dStructure.ch02;

//메소드에 배열 전달 실습부터
//매개변수로 배열 전달
import java.util.Random;

public class Train_2_4메소드배열전달 {

	public static void main(String[] args) {
		int[] data = new int[10];
		
		inputData(data);
		showData(data);
		
		int max = findMax(data);
		System.out.println("\nmax = " + max);
		
		int n = 50;
		boolean existValue = findValue(data, n);
		System.out.println("찾는 값 = " + n + ", 존재여부 = " + existValue);
	}

	static void showData(int[] data) {
		for (int num : data) {
			System.out.print(num + " ");
		}
	}

	public static void inputData(int[] data) {// 교재 63 - 난수의 생성
		
		Random rd = new Random();
		
//		for(int i : data) {		// for 확장문은 입력 X
//			i = rd.nextInt(100);
//		}		
		
		for(int i = 0; i < data.length; i++) {
			data[i] = rd.nextInt(100);
		}

	}

	static int findMax(int[] items) {
		
		int m = 0;
		for(int i : items) {
			if(i > m) {
				m = i;				
			}
		}
		return m;
	}

	static boolean findValue(int[] items, int value) {
		// items[]에 value 값이 있는지를 찾는 알고리즘 구현
		
		for(int i : items) {
			if(value == i) {
				return true;
			}
		}
		return false;
	}
}
