package dStructure.ch03;

/*
 * 함수(메소드)에 parameter 전달 방식을 표현하는 능력의 숙달 훈련
 * 함수(메소드) 전체를 작성하는 훈련 
 */
import java.util.Arrays;
//import java.util.List;

public class Test03_3_1스트링배열합병 {
	
	static void showList(String topic, String[] list) {
		System.out.println(topic);
		for (String item : list)
			System.out.print(item + " ");
		System.out.println();
	}

	static String[] mergeList(String[] s1, String[] s2) {	// 합병 정렬 메서드
		int i = 0, j = 0, k = 0;		// 각각 index가 될 변수 선언, i=s1 idx, j=s2 idx, k=s3 idx
		String[] s3 = new String[10];	// 합병 정렬하여 입력할 새로운 배열 s3

		while (i < s1.length && j < s2.length) {	// i, j 중 배열을 다 돌때까지 while 루프
			if ((s1[i].compareTo(s2[j])) < 0) {		// s1[i] 요소와 s2[j] 요소를 비교하여 
				s3[k++] = s1[i++];					// s1[i]가 작으면 s3[k]에 넣고 각 index를 1씩 증가
			} else {
				s3[k++] = s2[j++];					// 반대인 경우 s2[j]를 넣고 각 index 1증가
			}
		}

		if (i == s1.length) {			// s1의 배열이 s3에 다채워졌을경우(== s2의 배열이 남았을 경우)
			while (j < s2.length) {		
				s3[k++] = s2[j++];		// 남아있는 s2의 배열을 s3에 채움
			}
		}
		if (j == s2.length) {			// s2의 배열이 s3에 다채워졌을경우(== s1의 배열이 남았을 경우)
			while (i < s1.length) {
				s3[k++] = s1[i++];		// 남아있는 s1의 배열을 s3에 채움
			}
		}

		return s3;						// 다 채워진 s3 배열을 리턴
	}

	public static void main(String[] args) {
		String[] s1 = { "홍길동", "강감찬", "을지문덕", "계백", "김유신" };
		String[] s2 = { "독도", "울릉도", "한산도", "영도", "우도" };
		Arrays.sort(s1); // compareTo() 생략
		Arrays.sort(s2);

		showList("s1배열 = ", s1);
		System.out.println("=".repeat(50));
		showList("s2배열 = ", s2);
		System.out.println("=".repeat(50));

		String[] s3 = mergeList(s1, s2);
		showList("스트링 배열 s3 = s1 + s2:: ", s3);
	}
}
