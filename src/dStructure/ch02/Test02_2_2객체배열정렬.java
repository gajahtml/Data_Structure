package dStructure.ch02;

import java.util.Arrays;

//5번 실습 - 2장 실습 2-14를 수정하여 객체 배열의 정렬 구현
// interface - comparable, comparator 사용

class PhyscData implements Comparable<PhyscData> {
	String name;
	int height;
	double vision;

	public PhyscData(String name, int height, double vision) {
		this.name = name;
		this.height = height;
		this.vision = vision;
	}

	@Override
	public String toString() {
		return "PhyscData [name=" + name + ", height=" + height + ", vision=" + vision + "]";
	}

	@Override
	public int compareTo(PhyscData p) {
		if (this.name.compareTo(p.name) != 0) {
			return this.name.compareTo(p.name);
		}
		if ((this.height - p.height) != 0) {
			return this.height - p.height;
		}
		return (int) (this.vision - p.vision);
	}

//	public int equals(PhyscData p) {
//
//	}
}

public class Test02_2_2객체배열정렬 {

	static void swap(PhyscData[] arr, int ind1, int ind2) {

		PhyscData temp = arr[ind1];
		arr[ind1] = arr[ind2];
		arr[ind2] = temp;
	}

	static void sortData(PhyscData[] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i].compareTo(arr[j]) > 0) {
					swap(arr, i, j);
				}
			}
		}
	}

	public static void main(String[] args) {
		PhyscData[] data = {
				new PhyscData("홍길동", 164, 0.3),
				new PhyscData("홍동", 164, 1.3),
				new PhyscData("홍길", 152, 0.7),
				new PhyscData("김홍길동", 172, 0.3),
				new PhyscData("이길동", 182, 0.6),
				new PhyscData("박길동", 167, 0.2),
				new PhyscData("최길동", 169, 0.5), 
		};

		System.out.println("정렬 전 :");
		showData(data);
		System.out.println("=".repeat(50));

		System.out.println("정렬 후 :");
		sortData(data);
		showData(data);
		System.out.println("=".repeat(50));

		// Arrays.sort(null, null);//comparator가 필요하다

	}

	static void showData(PhyscData[] arr) {

		for (PhyscData s : arr) {
			System.out.println(s);
		}
	}
}
