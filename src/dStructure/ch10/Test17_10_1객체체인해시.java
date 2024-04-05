package dStructure.ch10;

import java.util.Comparator;
//hash node가 student 객체일 때를 구현하는 과제
//체인법에 의한 해시
import java.util.Scanner;

//체인법에 의한 해시

class SimpleObject5 {
	static final int NO = 1;
	static final int NAME = 2;
	
	int no; // 회원번호
	String name; // 이름
	
	// 구현내용
	public SimpleObject5() {	// 기본 생성자
		no = 0;
		name = null;
	}

	public SimpleObject5(int no, String name) {	// 매개변수가 있는 생성자
		this.no = no;
		this.name = name;
	}

	@Override
	public String toString() {		// toString() Override
		return "[" + no + "]" + name;
	}
	
	void scanData(String guide, int sw) {	// scanData() 메서드 -> 객체정보 입력받는 메서드
		Scanner sc = new Scanner(System.in);
		System.out.println(guide + "할 데이터를 입력하세요." + sw);

		if ((sw & NO) == NO) { // & 는 bit 연산자임
			System.out.print("번호: ");
			no = sc.nextInt();
		}
		if ((sw & NAME) == NAME) {
			System.out.print("이름: ");
			name = sc.next();
		}
	}
	
	public static final Comparator<SimpleObject5> NO_ORDER = new NoOrderComparator();

	private static class NoOrderComparator implements Comparator<SimpleObject5> {
		@Override
		public int compare(SimpleObject5 d1, SimpleObject5 d2) {
			return d1.no - d2.no;
		}
	}
	
	public static final Comparator<SimpleObject5> NAME_ORDER = new NameOrderComparator();

	private static class NameOrderComparator implements Comparator<SimpleObject5> {
		@Override
		public int compare(SimpleObject5 d1, SimpleObject5 d2) {
			return d1.name.compareTo(d2.name);
		}
	}
}

class ChainHash5 {
//--- 해시를 구성하는 노드 ---//
	class Node2 {
		private SimpleObject5 data; // 키값
		private Node2 next; // 뒤쪽 포인터(뒤쪽 노드에 대한 참조)
		
		// --- 생성자(constructor) ---//
		public Node2() {
			data = null;
			next = null;
		}
		
		public Node2(SimpleObject5 data) {
			this.data = data;
			next = null;
		}
	}

	private int size; // 해시 테이블의 크기
	private Node2[] table; // 해시 테이블

//--- 생성자(constructor) ---//
	public ChainHash5(int capacity) {
		try {
			size = capacity;
			table = new Node2[size];			
		} catch (OutOfMemoryError e) {
			size = 0;
		}
	}
	
	// 해당키에 대한 해쉬값(인덱스)을 반환해주는 메서드
	public int hashValue(int key) {
		return key % size;
	}

	//--- 키값이 key인 데이터를 data의 요소로 추가 ---//
	public int add(SimpleObject5 st, Comparator<? super SimpleObject5> c) {
		int hash = hashValue(st.no);
		
		Node2 newNode = new Node2(st);
		Node2 p = table[hash];
		Node2 q = null;
		
		if(p == null) {
			table[hash] = newNode;
			return 0;
		}
		
		while(p != null) {
			if(c.compare(st, p.data) == 0)
				return 1;
			
			if(c.compare(st, p.data) < 0) {
				if(p == table[hash]) {
					table[hash] = newNode;
					newNode.next = p;					
				} else {
					q.next = newNode;
					newNode.next = p;					
				}
				return 0;
			} else {
				q = p;
				p = p.next;
			}
				
		}
		q.next = newNode;
		return 0;				
	}

	//--- 키값이 key인 요소를 삭제 ---//
	public int delete(SimpleObject5 st, Comparator<? super SimpleObject5> c) {
		
		return 0;

	}

	//--- 키값이 key인 요소를 검색(데이터를 반환) ---//
	public int search(SimpleObject5 st, Comparator<? super SimpleObject5> c) {
		
		return 0;
	}

	//--- 해시 테이블을 덤프(dump) ---//
	public void dump() {
		
		for(int i = 0; i < size; i++) {
			Node2 current = table[i];
			System.out.printf("[%02d]", i);
			
			while(current != null) {
				System.out.print(" ▷ " + current.data);
				current = current.next;
			}
			System.out.println();
		}
		System.out.println();
	}
}

public class Test17_10_1객체체인해시 {
	enum Menu {
		Add("삽입"), Delete("삭제"), Search("검색"), Show("출력"), Exit("종료");

		private final String message; // 표시할 문자열

		static Menu MenuAt(int idx) { // 순서가 idx번째인 열거를 반환
			for (Menu m : Menu.values())
				if (m.ordinal() == idx)
					return m;
			return null;
		}

		Menu(String string) { // 생성자(constructor)
			message = string;
		}

		String getMessage() { // 표시할 문자열을 반환
			return message;
		}
	}

	// --- 메뉴 선택 ---//
	static Menu SelectMenu() {
		Scanner sc = new Scanner(System.in);
		int key;
		do {
			for (Menu m : Menu.values()) {
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
				if ((m.ordinal() % 3) == 2 && m.ordinal() != Menu.Exit.ordinal())
					System.out.println();
			}
			System.out.print(" : ");
			key = sc.nextInt();
		} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());
		return Menu.MenuAt(key);
	}
	
	int randNo() {
		int num = (int) (Math.random() * 70);
		return num;
	}
	
	String randName() {
		String [] rName = {"kim", "lee", "park", "john", "tom", "kay", "choi", "jang", "jane", "anna"};
		int num = (int) Math.round(Math.random() * (rName.length - 1));
		return rName[num];
	}

	public static void main(String[] args) {
		Menu menu;
		Scanner stdIn = new Scanner(System.in);
		ChainHash5 hash = new ChainHash5(15);
		SimpleObject5 data;
		SimpleObject5 ranData;
		
		final int count = 10;
		
		int select = 0, result = 0;
		do {
			switch (menu = SelectMenu()) {
			case Add:
				
				for(int i = 0; i < count; i++) {
				}
					
				
//				data = new SimpleObject5();
//				data.scanData("삽입", SimpleObject5.NO | SimpleObject5.NAME);
//				result = hash.add(data, SimpleObject5.NO_ORDER);
				if (result == 1)
					System.out.println(" 중복 데이터가 존재한다");
				else
					System.out.println(" 입력 처리됨");
				break;
			case Delete:
				// Delete
				data = new SimpleObject5();
				data.scanData("삭제", SimpleObject5.NO);
				result = hash.delete(data, SimpleObject5.NO_ORDER);
				if (result == 1)
					System.out.println(" 삭제 처리");
				else
					System.out.println(" 삭제 데이터가 없음");
				break;
			case Search:
				data = new SimpleObject5();
				data.scanData("검색", SimpleObject5.NO);
				result = hash.search(data, SimpleObject5.NO_ORDER);
				if (result == 1)
					System.out.println(" 검색 데이터가 존재한다");
				else
					System.out.println(" 검색 데이터가 없음");
				break;
			case Show:
				hash.dump();
				break;
			}
		} while (menu != Menu.Exit);
	}
}
