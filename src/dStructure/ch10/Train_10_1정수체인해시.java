package dStructure.ch10;

import java.util.Arrays;
import java.util.Scanner;

//체인법에 의한 해시
//--- 해시를 구성하는 노드 ---//
class Node {
	int key; // 키값
	Node next; // 뒤쪽 포인터(뒤쪽 노드에 대한 참조)

	public Node(int key) {
		this.key = key;
		next = null;
	}
}

class SimpleChainHash {
	private int size; // 해시 테이블의 크기
	private Node[] table; // 해시 테이블

	public SimpleChainHash(int i) {
		size = i;
		table = new Node[size];
	}

	// --- 키값이 key인 요소를 검색(데이터를 반환) ---//
	public int search(int key) {
		int hash = key % size;
		Node p = table[hash];
		
		while(p != null) {
			if(key != p.key) {
				p = p.next;
			} else {
				return 1;
			}				
		}
		return 0;
	}

	// --- 키값이 key인 데이터를 data의 요소로 추가 ---//
	public int add(int key) {
		int hash = key % size;

		Node newNode = new Node(key);
		Node p = table[hash];
		Node q = null;

		if (p == null) {
			table[hash] = newNode;
			return 1;
		}

		while (p != null) {
			if (key > p.key) {
				q = p;
				p = p.next;
			} else {
				if (key == p.key)
					return 0;

				if (p == table[hash]) {
					newNode.next = p;
					table[hash] = newNode;
					return 1;
				} else {
					q.next = newNode;
					newNode.next = p;
					return 1;
				}
			}
		}
		q.next = newNode;
		return 1;
	}

	// --- 키값이 key인 요소를 삭제 ---//
	public int delete(int key) {
		int hash = key % size;
		Node p = table[hash], q = null;
		
		if(p == null)
			return 0;
		
		if(key == p.key) {
			table[hash] = p.next;
			return 1;
		}
		
		while(p != null) {
			if(key != p.key) {
				q = p;
				p = p.next;
			} else {
				q.next = p.next;
				return 1;
			}
		}
		
		return 0;		
		
//		if(key == p.key) {
//			table[hash] = p.next;
//			return 1;
//		}
//		
//		while(key != p.next.key) {
//		
//			p = p.next;
//		}
//		if(p.next.next == null) {
//			p.next = null;
//			return 1;
//		} else {					
//			p.next = p.next.next;
//			return 1;			
//		}	

	}

	// --- 해시 테이블을 덤프(dump) ---//
	public void dump() {

		for (int i = 0; i < size; i++) {
			Node p = table[i];
			System.out.printf("[%02d] ", i);

			if (p == null) {
				System.out.print("▶ null");				
			}

			while (p != null) {
				System.out.printf("▶ %s ", p.key);
				p = p.next;
			}
			System.out.println();
		}
	}
}

public class Train_10_1정수체인해시 {

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

//체인법에 의한 해시 사용 예
	public static void main(String[] args) {
		Menu menu;
		SimpleChainHash hash = new SimpleChainHash(11);
		Scanner stdIn = new Scanner(System.in);
		int select = 0, result = 0, val = 0;
		final int count = 15;
		do {
			switch (menu = SelectMenu()) {
			case Add:

				int[] input = new int[count];
				for (int ix = 0; ix < count; ix++) {
					double d = Math.random();
					input[ix] = (int) (d * 50);
					System.out.print(" " + input[ix]);
				}
				System.out.println();
				for (int i = 0; i < count; i++) {
					if ((hash.add(input[i])) == 0)
						System.out.println("Insert Duplicated data ▷ " + input[i]);
				}
				break;
			case Delete:
				// Delete
				System.out.println("Delete Value:: ");
				val = stdIn.nextInt();
				result = hash.delete(val);
				if (result == 1)
					System.out.println(val + " -> 삭제 완료");
				else
					System.out.println(val + " -> 해당 데이터가 없음");
				System.out.println();
				break;
			case Search:
				System.out.println("Search Value:: ");
				val = stdIn.nextInt();
				result = hash.search(val);
				if (result == 1)
					System.out.println("검색 데이터가 존재한다");
				else
					System.out.println("검색 데이터가 없음");
				System.out.println();
				break;

			case Show:
				hash.dump();
				break;
			}
		} while (menu != Menu.Exit);

	}
}
