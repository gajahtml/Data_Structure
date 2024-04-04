package dStructure.ch08;
//단순한 linked list에서 insert, delete하는 알고리즘을 코딩: 1단계

import java.util.Random;
import java.util.Scanner;

class Node1 {
	int data;
	Node1 link;

	public Node1(int element) {
		data = element;
		link = null;
	}
}

class LinkedList1 {
	Node1 first;

	public LinkedList1() {
		first = null;
	}

	public void Add(int element) {// 임의 값을 삽입할 때 리스트가 오름차순으로 정렬이 되도록 한다
		Node1 newNode = new Node1(element);
		Node1 p = first, q = null;
		
		if (first == null) {// insert into empty list
			first = newNode;
			return;
		}	
		
		while (p != null) {			
			q = p;
			p = p.link;
			
			// 초기값 2개가 생성 될때까지 조건
			if (newNode.data < first.data) {	// newNode의 값이 first 보다 작을때
				first = newNode;				// first에 newNode를 대입하고
				newNode.link = q;				// newNode의 링크를 q(이전 first값)에 연결
				return;
			} else if(p == null){				// newNode의 값이 first 보다 클때 & 2번째 데이터가 없을때 (p == null)
				q.link = newNode;				// q.link(== first.link)에 newNode 값을 대입
				return;
			}
			
			if(newNode.data < p.data) {			// newNode의 값이 p.data의 값보다 작을때 (q < newNode < p)
				q.link = newNode;				// q.link => newNode, newNode.link => p
				newNode.link = p;
				return;
			}
			
			if(p.link == null) {				// 위 조건을 거쳐서 newNode의 값이 p.data보다 클때 p.link가 null이면 
				p.link = newNode;				// p.link(꼬리노드)에 newNode값 대입
				return;							// 꼬리노드가 아니면 while 루프 돌아서 q, p 오른쪽으로 이동
			}
		}
	}
	
	public boolean Delete(int element) {// 전달된 element 값이 존재 하면 삭제하고 true로 리턴
		Node1 q, current = first;		
		
		while(current.link != null) {			
			if(element == current.data) {		// 삭제할 값이 first값(current.data)이랑 일치하면
				first = current.link;			// first에 그 다음 연결값(current.link)을 대입
				return true;
			}
			q = current;						// 삭제할 값이 first값이 아니면 q, current 값을 오른쪽으로 이동
			current = current.link;
			if(element == current.data) {		// 이동중 current값이랑 일치하면
				q.link = current.link;			// 이전값의 링크를 current값의 링크로 연결(중간의 current 연결 끊김)
				return true;
			}
		}
		return false;		// 찾는값이 없으면 false 리턴
	}

	public void Show() { // 전체 리스트를 순서대로 출력한다.
		Node1 p = first;
		int num = 0;

		while (p != null) {
			System.out.print(p.data + " ");
			p = p.link;
		}
		System.out.println();
	}


	public boolean Search(int data) { // 전달된 data 값을 찾아 존재하면 true로 리턴, 없으면 false로 리턴
		Node1 ptr = first;
		
		while(ptr != null) {
			if(data == ptr.data)
				return true;
			ptr = ptr.link;
		}

		return false;
	}

	void Merge(LinkedList1 b) {
		/*
		 * 연결리스트 a,b에 대하여 a = a + b merge하는 알고리즘 구현으로 in-place 방식으로 합병/이것은 새로운 노드를 만들지
		 * 않고 합병하는 알고리즘 구현 난이도 등급: 최상 a = (3, 5, 7), b = (2,4,8,9)이면 a = (2,3,4,5,8,9)가
		 * 되도록 구현하는 코드
		 */
		Node1 i = first, j = null;
		Node1 p = b.first, q = null;
		
//		System.out.println(p.data + ", " + p.link);
		
		while(i != null && p != null) {
			
			if(i.data >= p.data) {				
				while(i.data >= p.data) {
					q = p;
					p = p.link;
					if(p == null)
						break;
				}
				q.link = i;
				System.out.println("New LinkedList - [ " + q.data + " -> " + i.data + " ]");
			}
			
			if(p == null)
				break;
			
			if(i.data < p.data) {		
				while(i.data < p.data) {
					j = i;
					i = i.link;
					if(i == null)
						break;
				}
				j.link = p;
				System.out.println("Old LinkedList - [ " + j.data + " -> " + p.data + " ]");
			}
		}
		System.out.println("Merge 완료");
	}
}

public class Train_8_1정수연결리스트 {
	enum Menu {
		Add("삽입"), Delete("삭제"), Show("인쇄"), Search("검색"), Merge("합병"), Exit("종료");

		private final String message; // 표시할 문자열

		static Menu MenuAt(int idx) { // 순서가 idx번째인 열거를 반환
			for (Menu m : Menu.values())
				if (m.ordinal() == idx)
					return m;
			return null;
		}

		// "Add" 상수가 정의될 때 Menu("삽입") 생성자가 호출되어 message 필드가 "삽입"으로 초기화
		// 생성자는 각 상수가 정의될 때 호출되며, 해당 상수의 message 필드를 초기화하는 역할
		// enum 상수가 언제 정의되는가를 찾아 보아야 한다
		Menu(String string) { // 생성자(constructor)가 언제 호출되는지 파악하는 것이 필요하다
			message = string;
			System.out.println("\nMenu 생성자 호출:: " + string);
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
				// n%3은 3으로 나누어 나머지를 계산한다
				if ((m.ordinal() % 3) == 2 && m.ordinal() != Menu.Exit.ordinal())// 메뉴 출력시에 다음행에 출력하라는 의미
					System.out.println();
			}
			System.out.print(" : ");
			key = sc.nextInt();// 메뉴 선택 번호로 입력된 값이 key이다
		} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());// 입력된 key가 음수이거나 Exit에 대한 enum 숫자보다 크면 다시 입력받는다
		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Menu menu; // 메뉴 참조 변수일 뿐이다
		Random rand = new Random();
		LinkedList1 l = new LinkedList1();
		Scanner sc = new Scanner(System.in);
		int count = 10; // 난수 생성 갯수
		int data = 0;
		do {
			switch (menu = SelectMenu()) {// Menu 생성자 호출 - menu 객체를 리턴한다
			case Add: // 난수를 삽입하는데 올림차순으로 정렬되도록 구현
				for (int i = 0; i < count; i++) {
					data = rand.nextInt(20);
					l.Add(data);
				}
				System.out.println();
				break;
			case Delete:
				System.out.print("삭제할 값을 입력: ");
				data = sc.nextInt();
				boolean tag = l.Delete(data);
				System.out.println("삭제 데이터 존재여부: " + tag + "\n");
				break;
			case Show: // 리스트 전체를 출력
				l.Show();
				System.out.println();
				break;
			case Search: // 입력 숫자 n을 검색한다.
				System.out.print("검색할 숫자를 입력: ");
				int n = sc.nextInt();
				boolean result = l.Search(n);
				if (!result)
					System.out.println("검색 값 = " + n + " 데이터가 없습니다.\n");
				else
					System.out.println("검색 값 = " + n + " 데이터가 존재합니다.\n");
				break;
			case Merge:// 리스트 l과 l2를 합병하여 올림차순 정렬이 되게 구현한다
				LinkedList1 l2 = new LinkedList1();
				for (int i = 0; i < count; i++) {
					data = rand.nextInt(20);
					l2.Add(data);
				}
				System.out.println("새로 생성된 연결리스트 :");
				l2.Show();
				l.Merge(l2);// merge 실행후 show로 결과 확인 - 새로운 노드를 만들지 않고 합병 - 난이도 상
				break;
			case Exit: // 꼬리 노드 삭제
				break;
			}
		} while (menu != Menu.Exit);
		sc.close();
	}
}
