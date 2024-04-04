package dStructure.ch03;

/*
 * Comparable 구현
 */
/*
 * 함수(메소드)에 parameter 전달 방식을 표현하는 능력의 숙달 훈련
 * 함수(메소드) 전체를 작성하는 훈련 
 */
import java.util.Arrays;

//5번 실습 - 2장 실습 2-14를 수정하여 객체 배열의 정렬 구현
class PhyscData2 implements Comparable<PhyscData2>{
	String name;
	int height;
	double vision;

	public PhyscData2(String name, int height, double vision) {
		this.name = name;
		this.height = height;
		this.vision = vision;
	}

	@Override
	public String toString() {
		return "PhyscData2 [name= " + name + ", height= " + height + ", vision= " + vision + "]";
	}
	
	@Override
	public int compareTo(PhyscData2 p) {
		if((this.name.compareTo(p.name)) != 0)
			return this.name.compareTo(p.name);
		if((this.height - p.height) != 0)
			return this.height - p.height;
		return (int) (this.vision - p.vision);		
	}
	
//	public int equals(PhyscData2 p) {
//		
//	}
}

public class Train_3_6_1객체배열이진탐색 {
	
	private static void showData(String title, PhyscData2[] data) {
		System.out.println(title);
		for(PhyscData2 item : data) {
			System.out.println(item);
		}
		System.out.println("=".repeat(50));
	}
	
	private static void sortData(PhyscData2[] data) {
		for(int i = 0; i < data.length - 1; i++) {
			for(int j = i + 1; j < data.length; j++) {
				if(data[i].compareTo(data[j]) > 0)
					swap(data, i, j);
			}
		}		
	}
	
	private static void swap(PhyscData2[] data, int i, int j) {
		PhyscData2 t = data[i];
		data[i] = data[j];
		data[j] = t;		
	}

	private static void reverse(PhyscData2[] data) {
		for(int i = 0; i < data.length / 2; i++) {
			swap(data, i, data.length - i - 1);
		}
	}	
	
	private static int linearSearch(PhyscData2[] data, PhyscData2 key) {
		int i = 0;
		while(true) {
			if(i == data.length - 1)
				return -1;
			if((data[i].compareTo(key)) == 0)
				return i;
			i++;
		}
	}
	
	private static int binarySearch(PhyscData2[] data, PhyscData2 key) {
		int pl = 0;
		int pr = data.length - 1;
		
		do {
			int pc = (pl + pr) / 2;
			if((data[pc].compareTo(key)) == 0) {
				return pc;
			} else if((data[pc].compareTo(key)) > 0) {
				pr = pc - 1;
			} else
				pl = pc + 1;				
		} while(pl <= pr);
		return 0;
	}
	
	public static void main(String[] args) {
		
		PhyscData2[] data = {
				new PhyscData2("홍길동", 162, 0.3),
				new PhyscData2("나동", 164, 1.3),
				new PhyscData2("최길", 152, 0.7),
				new PhyscData2("김홍길동", 172, 0.3),
				new PhyscData2("박동", 182, 0.6),
				new PhyscData2("이동", 167, 0.2),
				new PhyscData2("길동", 167, 0.5),
		};
		
		showData("정렬전", data);
		
		PhyscData2[] data1 = data.clone();
		sortData(data1);
		showData("정렬후", data1);
		
		PhyscData2[] data2 = data.clone();
		reverse(data2);
		showData("역순 재배치후", data2);
		
		PhyscData2[] data3 = data.clone();
		Arrays.sort(data3);//사용된다 그 이유는?
		showData("Arrays.sort() 정렬후", data3);
		
		PhyscData2[] data4 = data.clone();
		PhyscData2 key = new PhyscData2("길동", 167, 0.2);
		int resultIndex = linearSearch(data4, key);
		System.out.println("\nlinearSearch(<길동,167,02>): result = " + (resultIndex + 1));
		
		PhyscData2[] data5 = data.clone();
		key = new PhyscData2("박동", 182, 0.6);
		/*
		 * 교재 109~113
		 */
		resultIndex = binarySearch(data5, key);//comparable를 사용
		System.out.println("\nbinarySearch(<박동,182,0.6>): result = " + (resultIndex + 1));
		
		PhyscData2[] data6 = data.clone();
		key = new PhyscData2("이동", 167, 0.6);
		/*
		 * 교재 115 Arrays.binarySearch에 의한 검색
		 */
		resultIndex = Arrays.binarySearch(data6, key);//comparable를 사용
		System.out.println("\nArrays.binarySearch(<이동,167,0.6>): result = " + (resultIndex + 1));
	}
}
