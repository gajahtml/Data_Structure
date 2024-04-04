
package dStructure.ch03;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//중복이 없는 리스트를 merge하는 버젼

public class Test05_3_3중복없는리스트합병 {
//string 정렬, binary search 구현
//1단계: string, 2단계: 객체,  Person 객체들의 list\
//file1: 서울,북경,상해,서울,도쿄, 뉴욕,부산
//file2: 런던, 로마,방콕, 도쿄,서울,부산
//file > string split() > 배열 > ArrayList > sort > iterator 사용하여 merge > 중복 제거 > string 배열 > file에 저장
	
	static ArrayList<String> removeDuplicate(ArrayList<String> lst) {
		/*
		 * 구현할 부분 : 리스트에서 중복을 제거한다 - 배열로 변환하여 구현하는 것이 아님
		 * 리스트를 정렬한후에 이 함수가 호출된다
		*/
		
		// 방법1 : Set()을 이용한 중복값 제거 -> List를 Set으로 변환, 중복값 제거 후 다시 List로 변환
//		// 정렬된 리스트를 Set()으로 중복값 제거시 순서가 보장되지 않음
//		Set<String> set = new HashSet<>(lst);		
//		ArrayList<String> list1 = new ArrayList<>(set);
//
//		return list1;
		
		// 방법2 : contains()를 이용한 중복값 제거
		// 정렬된 리스트를 contains()로 중복값 제거시 순서 보장
		ArrayList<String> list1 = new ArrayList<>();
		for(String city : lst) {
			if(!list1.contains(city)) {
				list1.add(city);				
			}
		}
		return list1;
	}

	static List<String> mergeList(List<String> list1, List<String> list2) {
		/*
		 * list3 = merge(list1, list2);으로서 새로운 리스트에 정렬 값 순서로 merge하는 알고리즘 구현 
		 */
		ArrayList<String> list3 = new ArrayList<>();
		// ------- ArrayList의 get()을 사용한 merge
		int i = 0, j = 0;
		
		while(i < list1.size() && j < list2.size()) {		// i, j 중 하나라도 배열을 다돌면 루프 끝
			if(list1.get(i).compareTo(list2.get(j)) < 0)	// get()이용하여 list1, list2 문자열 비교
				list3.add(list1.get(i++));					// 비교하여 작은 값을 list3에 넣고 인덱스 증가
			else
				list3.add(list2.get(j++));				
		}
		
		while(i < list1.size()) {			// list1의 값이 남아있으면
			list3.add(list1.get(i++));		// list3에 병합
		}
		
		while(j < list2.size()) {			// list2의 값이 남아있으면
			list3.add(list2.get(j++));		// list3에 병합
		}
		
		return removeDuplicate(list3);		// 병합 후 중복값 제거한 리스트를 리턴
	}
	
	private static void showData(String title, String[] arr) {
		System.out.println(title);
//		System.out.println(Arrays.toString(arr));
		for(String item : arr) {
			System.out.print(item + "|");
		}
		System.out.println();
	}
	
	private static void trimSpace(String[] arr) {
		for(int i = 0; i < arr.length; i++) {
			arr[i] = arr[i].trim();
		}
	}

	private static void makeList(String[] sarray1, ArrayList<String> list1) {
		list1.addAll(Arrays.asList(sarray1));
	}	

	private static void showList(String title, ArrayList<String> list1) {
		System.out.println(title);
		System.out.println(list1.toString());
	}
	
	private static void showList(String title, List<String> list3) {
		System.out.println(title);
		System.out.println(list3.toString());
	}
	
	public static void main(String[] args) {
		try {
			/*
			 * 자바 교재 547: 이클립스 > edu 프로젝트 - 마우스 우측 > New>File >a.txt 생성
			 * 입력 데이터를 다음과 같이 만든다:
			 *    서울,도쿄,북경,상해,서울,도쿄, 뉴욕,부산
			 *        상해,도쿄
			 *          서울, 도쿄
			 * 자바 교재 580: Path 클래스 - 파이썬 유사 
			 */
			Path input1 = Paths.get("a1.txt");
			byte[] bytes1 = Files.readAllBytes(input1);
			//readAllBytes: 파일의 모든 바이트를 읽어오는 메서드입니다. 
			//이 메서드는 파일을 열고 파일의 크기만큼 바이트를 읽어서 바이트 배열로 반환합니다.
			System.out.println("bytes[]의 길이 = "+bytes1.length + '\n');
			
			Path input2 = Paths.get("a2.txt");
			byte[] bytes2 = Files.readAllBytes(input2);
			
			String s1 = new String(bytes1);
			String s2 = new String(bytes2);
			System.out.println("입력 스트링: s1 = \n" + s1);
			System.out.println("=".repeat(50));
			System.out.println("입력 스트링: s2 = \n" + s2);
			System.out.println("=".repeat(50));
			String[] sarray1 = s1.split("[,\\r\n]+");// [,\\s]+\r\n은 쉼표나 공백이 하나 이상 나오고 이어서 캐리지 리턴과 개행 문자가 있는 패턴
			String[] sarray2 = s2.split("[,\\r\n]+");//file에서 enter키는 \r\n으로 해야 분리됨
			
			showData("스트링 배열 sarray1", sarray1);
			System.out.println("=".repeat(50));
			showData("스트링 배열 sarray2", sarray2);
			System.out.println("=".repeat(50));

			trimSpace(sarray1);
			trimSpace(sarray2);

			showData("trimSpace() 실행후 :스트링 배열 sarray1", sarray1);
			System.out.println("=".repeat(50));
			showData("trimSpace() 실행후 :스트링 배열 sarray2", sarray2);
			System.out.println("=".repeat(50));
			
			// file1에서 read하여 list1.add()한다.
			// 배열을 list로 만드는 방법
			// 방법1:
			ArrayList<String> list1 = new ArrayList<>();
			makeList(sarray1, list1);
			showList("ArrayList 변환 후 list1: ", list1);
			
			// 방법2
			ArrayList<String> list2 = new ArrayList<>(Arrays.asList(sarray2));
			showList("리스트2: ", list2);
			
			System.out.println("+++++++ collection.sort()::");
			Collections.sort(list1);
			showList("정렬후 리스트1: ", list1);

			//Arrays.sort(list2, null);//에러 발생 확인하고 이유는?
			Collections.sort(list2);
			showList("정렬후 리스트2: ", list2);	
	
			//정렬된 list에서 중복 제거 코드
			list1 = removeDuplicate(list1);
			list2 = removeDuplicate(list2);
			showList("중복 제거후 리스트1: ", list1);	
			showList("중복 제거후 리스트2: ", list2);	
	
			
			List<String> list3 = new ArrayList<>();
			
			// 방법3:
			list3 = mergeList(list1, list2);
			showList("merge후 합병리스트: ", list3);

			// ArrayList를 배열로 전환
			String[] st = list3.toArray(new String[list3.size()]);
			// binary search 구현
			// binSearch(st, st.length, "key");
			
			String key = "부산";
			int idx = binSearch(st, st.length, key);
			System.out.print("\n찾는 도시명 : " + key);
			if(idx == -1)
				System.out.println(" => 찾는 도시가 없습니다");
			else
				System.out.println(" => " + (idx+1) + "번째에 위치");

			
			// 정렬된 list3을 file에 출력하는 코드 완성
			System.out.println("\n" + "file에 출력:");
			int bufferSize = 10240;
			
			ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
			writeFile(list3, buffer);
			
			FileOutputStream file = new FileOutputStream("c.txt");
			FileChannel channel = file.getChannel();
			channel.write(buffer);
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static int binSearch(String[] st, int len, String key) {	// 이진탐색 메서드 구현
		int pl = 0, pr = len - 1;
		
		do {
			int pc = (pl + pr) / 2;
			
			if(st[pc].compareTo(key) == 0) {
				return pc;
			} else if(st[pc].compareTo(key) < 0) {
				pl = pc + 1;
			} else
				pr = pc - 1;
			
		} while(pl <= pr);
		
		return -1;
	}

	static void writeFile(List<String> list3, ByteBuffer buffer) {
		String b = " ";
		for (String sx : list3) {
			System.out.println(" " + sx);
			buffer.put(sx.getBytes());
			buffer.put(b.getBytes());
		}
		buffer.flip();
	}
}
