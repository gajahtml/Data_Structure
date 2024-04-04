package dStructure.ch03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Train_3_1스트링리스트정렬 {


	static void getList(List<String> list) {
		list.add("서울");
		list.add("북경");
		list.add("상해");
		list.add("서울");
		list.add("도쿄");
		list.add("뉴욕");

		list.add("런던");
		list.add("로마");
		list.add("방콕");
		list.add("북경");
		list.add("도쿄");
		list.add("서울");

		list.add(1, "LA");
	}

	static void showList(String topic, List<String> list) {
		System.out.println(topic + " ::");
		
		for(String st : list) {
			System.out.print(st + "\t");
			
		}
	}

	static void sortList(List<String> list) {
		// 방법 1: list.sort(null);
		list.sort(null);
		
		
		// 방법 2: 리스트를 스트링 배열로 변환

	}

	static String[] removeDuplicateList(List<String> list) {
		// 리스트를 배열로 변환 -> 배열에서 중복을 캐치
		String cities[] = new String[0];
		cities = list.toArray(cities);	// list -> array로 변환, 배열을 체크해서 size넘어서면 공간할당
		// for 문으로 도시가 중복인 것을 체크
		String city = "";
		for(int i = 0; i < cities.length-1; i++) {
			for(int j = i + 1; j < cities.length; j++) {
				if(cities[i].compareTo(cities[j]) == 0) {
					city = cities[i];
					cities = removeElement1(cities, city);
				}				
			}
		}
		return removeElement1(cities, city);				
	}

	public static String[] removeElement1(String[] arr, String item) {
		// 현재 배열에서 중복제거하려면 코드가 길어진다
		// 배열을 리스트로 변환 : aslist(), 중복제거 : list.remove()
		// 다시 배열로 변환하여 반환
		ArrayList<String> lst = new ArrayList<>(Arrays.asList(arr));
		
//		int cnt = Collections.frequency(lst, item);
		
//		while(cnt != 1) {
//			lst.remove(item);
//			cnt--;
//		}
		
		lst.remove(item);
		System.out.println(lst);		
		
		return lst.toArray(String[]::new);	// String[]::new -> method reference, 람다식: 함수형 인터페이스	
	}
	
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>();
		getList(list);
		showList("입력후", list);
		// sort - 오름차순으로 정렬, 내림차순으로 정렬, 중복 제거하는 코딩

		// Collections.sort(list);

		// 배열의 정렬
		sortList(list);
		System.out.println();
		
		showList("정렬후", list);
		// 배열에서 중복제거
		System.out.println();
		
		String[] cities = removeDuplicateList(list);
		ArrayList<String> lst = new ArrayList<>(Arrays.asList(cities));
		showList("중복제거후", lst);
	}	
}
