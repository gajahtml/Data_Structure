package dStructure.ch06;

import java.util.Arrays;

/*
 * 6장 구현 실습과제1 
 */
class PhyscData implements Comparable<PhyscData> {
	String name; // 이름
	int height; // 키
	double vision; // 시력

	public PhyscData(String name, int height, double vision) {
		this.name = name;
		this.height = height;
		this.vision = vision;
	}

	@Override
	public int compareTo(PhyscData obj) {
		int nComp = this.name.compareTo(obj.name);
		int hComp = this.height - obj.height;
		int vComp = (int) (this.vision - obj.vision);

//		if(nComp != 0)
//			return nComp;
		if (hComp != 0)
			return hComp;
		return vComp;
	}

	@Override
	public String toString() {
		return "[ " + name + ", " + height + ", " + vision + " ]";
	}

}

public class Test10_6_1ObjectMergeSort {
	// --- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
//	static void swap(PhyscData[] a, int idx1, int idx2) {
//		PhyscData temp = a[idx1];
//		a[idx1] = a[idx2];
//		a[idx2] = temp;
//	}

	static void merge(PhyscData[] a, int lefta, int righta, int leftb, int rightb) {
		PhyscData[] temp = new PhyscData[rightb-lefta+1];
		int pa = lefta;
		int pb = leftb;
		int idx = 0;

		while (pa <= righta && pb <= rightb) {
			if (a[pa].compareTo(a[pb]) < 0) {
//				System.out.println("pa < pb : " + idx + " , " + a[pa].name + " , " + a[pb].name);
				temp[idx++] = a[pa++];
			} else {
//				System.out.println("pa > pb : " + idx + " , " + a[pa].name + " , " + a[pb].name);
				temp[idx++] = a[pb++];
			}
		}

		if (pb > rightb) {
			while (pa <= righta) {
				temp[idx++] = a[pa++];
			}
		}

		if (pa > righta) {
			while (pb <= rightb) {
				temp[idx++] = a[pb++];
			}
		}
		
		System.out.println("현재 lefta 위치 : " + lefta);
		System.out.println("temp배열 : \n" + Arrays.toString(temp));
		System.out.println("a배열 : \n" + Arrays.toString(a) + "\n");
		for(int i = 0; i < idx; i++ ) {
			a[lefta + i] = temp[i];
		}

//		int pointa = lefta;		// 왼쪽부분 시작점
//		int pointb = leftb;		// 오른쪽부분 시작점

//		for(int i = lefta; i <= righta; i++) {
//			for(int j = leftb; j <= rightb; j++) {
//				if(a[i].compareTo(a[j]) >= 0) {
//					swap(a, i, j);
//					System.out.println("swap : " + a[i].toString() + ", " + a[j].toString());
//				}
//			}
//		}

//		while(pointa <= righta && pointb <= rightb) {
//			if(a[pointa].compareTo(a[pointb]) >= 0) {
//				swap(a, pointa, pointb);
//				System.out.println("swap : " + a[pointa].toString() + ", " + a[pointb].toString());
//				pointa++;
//				pointb++;
//			} else {
//				pointa++;
//				pointb++;
//			}
//		}
	}

	// --- 퀵 정렬(비재귀 버전)---//
	static void MergeSort(PhyscData[] a, int left, int right) {
		int mid = (left + right) / 2;
		if (left == right)
			return;
		MergeSort(a, left, mid);
		MergeSort(a, mid + 1, right);
		merge(a, left, mid, mid + 1, right);
		return;
	}

	public static void main(String[] args) {
		PhyscData[] x = { new PhyscData("강민하", 162, 0.3),
				  		  new PhyscData("김찬우", 173, 0.7),
				  		  new PhyscData("박준서", 171, 2.0),
				  		  new PhyscData("유서범", 171, 1.5), 
				  		  new PhyscData("이수연", 168, 0.4),
				  		  new PhyscData("장경오", 171, 1.2),
				  		  new PhyscData("황지안", 169, 0.8), };

		int nx = x.length;

		MergeSort(x, 0, nx - 1); // 배열 x를 퀵정렬
		System.out.println("오름차순으로 정렬했습니다.");
		System.out.println("■ 신체검사 리스트 ■");
		System.out.println(" 이름     키  시력");
		System.out.println("------------------");
		for (int i = 0; i < x.length; i++)
			System.out.printf("%-8s%3d%5.1f\n", x[i].name, x[i].height, x[i].vision);
	}
}
