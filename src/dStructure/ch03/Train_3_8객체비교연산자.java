package dStructure.ch03;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

//5번 실습 - 2장 실습 2-14를 수정하여 객체 배열의 정렬 구현
class PhyscData3 {
	String name;
	int height;
	double vision;
	
	public PhyscData3(String name, int height, double vision) {
		this.name = name;
		this.height = height;
		this.vision = vision;
	}
	
	@Override
	public String toString() {
		return "PhyscData3 [name= " + name + ", height= " + height + ", vision= " + vision + "]";
	}		
}

class HeightOrderComparator2 implements Comparator<PhyscData3>{

	@Override
	public int compare(PhyscData3 o1, PhyscData3 o2) {
		if((o1.name.compareTo(o2.name)) != 0)
			return o1.name.compareTo(o2.name);
		
		if((o1.height - o2.height) != 0)
			return o1.height - o2.height;
		
		return (int) (o1.vision - o2.vision);
	}
	
}

public class Train_3_8객체비교연산자 {	
	
	static final Comparator<PhyscData3> HEIGHT_ORDER = new HeightOrderComparator2();

	private static void showData(String title, PhyscData3[] data) {
		System.out.println(title);
		for(PhyscData3 item : data)
			System.out.println(item);
		System.out.println("=".repeat(50));
	}
	
	public static void main(String[] args) {
		
		PhyscData3[] data = {
				new PhyscData3("홍길동", 162, 0.3),
				new PhyscData3("홍동", 164, 1.3),
				new PhyscData3("홍길", 152, 0.7),
				new PhyscData3("김홍길동", 172, 0.3),
				new PhyscData3("길동", 182, 0.6),
				new PhyscData3("길동", 167, 0.2),
				new PhyscData3("길동", 167, 0.5),
		};
		
		showData("정렬전 객체 배열", data);
		
		PhyscData3[] data1 = data.clone();
		Arrays.sort(data1, HEIGHT_ORDER);		
		showData("정렬후 객체 배열", data1);
		
//		PhyscData3[] data2 = data.clone();
		PhyscData3 key = new PhyscData3("홍길", 152, 0.7);
		int idx = Arrays.binarySearch(data1, key, HEIGHT_ORDER);
		System.out.println("\nArrays.binarySearch(): result = " + idx);
	}
}
