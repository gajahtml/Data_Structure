package dStructure.ch06;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

interface MaxHeap {
	public void Insert(int x);

	public int DeleteMax();
}

class Heap implements MaxHeap {
	final int heapSize = 100;
	private int[] heap;
	private int n; // MaxHeap의 현재 입력된 element 개수
	private int MaxSize; // Maximum allowable size of MaxHeap

	public Heap(int sz) {
		n = 0;
		MaxSize = sz;
		heap = new int[MaxSize];
	}

	public void display() {// heap 배열을 출력한다. 배열 인덱스와 heap[]의 값을 출력한다.
		System.out.print("Heap Index : ");
		for(int i = 1; i <= n; i++) {
			System.out.print("[" + i + "] ");
		}
		System.out.println();
		System.out.print("Heap Value : ");
		for(int j = 1; j <= n; j++) {
			System.out.print(heap[j] > 9 ? " " + heap[j] + " " : " " + heap[j] + "  ");
		}
		System.out.println("\n");
	}

	@Override
	public void Insert(int x) {// max heap이 되도록 insert한다. 삽입후 complete binary tree가 유지되어야 한다.
		int i;
		if (n == MaxSize) {
			HeapFull();
			return;
		}
		n++;	// Heap에 입력된 개수를 +1
		for(i = n; i >= 1;) {
			if(i == 1)
				break;				// 처음 들어온 숫자 이므로 for문을 벗어나서 heap[1]에 대입
			if(x <= heap[i / 2])
				break;				// n의 인덱스를 가진 x의 값이 부모노드(i/2)보다 작으면 for문 벗어나서 heap[i]에 대입
			heap[i] = heap[i / 2];	// 위 조건을 통과(x > 부모노드값)했으면 현재 인덱스(자식노드)와 부모노드값을 swap
			i /= 2;					// 인덱스를 부모노드 인덱스로 이동
		}
		heap[i] = x;
	}

	@Override
	public int DeleteMax() {// heap에서 가장 큰 값을 삭제하여 리턴한다.
		int x=0;
		int i, j;
		if (n == 0) {
			HeapEmpty();
			int elm = 0;
			return elm;
		}

		return x;
	}

	private void HeapEmpty() {
		System.out.println("Heap Empty");
	}

	private void HeapFull() {
		System.out.println("Heap Full");
	}
}

public class Test12_6_3HeapSort {
	static void showData(int[] d) {
		System.out.println(Arrays.toString(d));
	}

	public static void main(String[] args) {
		Random rnd = new Random();
		int select = 0;
		Scanner stdIn = new Scanner(System.in);
		Heap heap = new Heap(20);
		final int count = 10;// 난수 생성 갯수
		int[] x = new int[count + 1];// x[0]은 사용하지 않으므로 11개 정수 배열을 생성한다
		int[] sorted = new int[count];// heap을 사용하여 deleted 정수를 배열 sorted[]에 보관후 출력한다

		do {
			System.out.println("Max Tree. Select: 1 insert, 2 display, 3 sort, 4 exit => ");
			select = stdIn.nextInt();
			switch (select) {
			case 1:// 난수를 생성하여 배열 x에 넣는다 > heap에 insert한다.
				for(int i = 0; i < x.length; i++) {
					x[i] = rnd.nextInt(20) + 1;					
					heap.Insert(x[i]);
				}

				showData(x);
				break;
			case 2: // heap 트리구조를 배열 인덱스를 사용하여 출력한다.
				heap.display();
				break;
			case 3:// heap에서 delete를 사용하여 삭제된 값을 배열 sorted에 넣는다 > 배열 sorted[]를 출력하면 정렬 결과를 얻는다

				break;

			case 4:
				return;

			}
		} while (select < 5);

		return;
	}
}