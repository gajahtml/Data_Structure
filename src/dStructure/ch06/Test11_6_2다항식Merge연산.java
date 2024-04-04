package dStructure.ch06;

import java.util.Arrays;

/*
 * 6장 구현과제1
 */

class Polynomial3 implements Comparable<Polynomial3>{
    double coef;           // 계수
    int    exp;            // 지수

    // 파라미터 없는 생성자
    public Polynomial3() {
		super();
	}

	//--- 생성자(constructor) ---//
    Polynomial3(double coef, int exp) {
        this.coef = coef; 
        this.exp = exp; 
    }

    //--- 신체검사 데이터를 문자열로 반환 --//
    @Override
	public String toString() {
        return coef + "x**" + exp + " ";
    }
    @Override
    public int compareTo(Polynomial3 d2) { //지수가 같으면 계수로 비교
    	return exp - d2.exp;
    	
    }
}
public class Test11_6_2다항식Merge연산 {

	// --- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
	static void merge(Polynomial3[] a, int lefta, int righta, int leftb, int rightb ) {
		 //body를 지우고 작성 훈련 연습이 도움이 된다 
		Polynomial3 temp[] = new Polynomial3[30];
		//구현코드
		
		int pa = lefta;
		int pb = leftb;
		int tIdx = 0;
		
		while(pa <= righta && pb <= rightb) {
			if((a[pa].compareTo(a[pb])) >= 0) {
				temp[tIdx++] = a[pa++];
			} else {
				temp[tIdx++] = a[pb++];
			}
		}
		
		if(pb > rightb) {
			while(pa <= righta) {
				temp[tIdx++] = a[pa++];
			}
		}
		
		if(pa > righta) {
			while(pb <= rightb) {
				temp[tIdx++] = a[pb++];
			}
		}
		
//		System.out.println("현재 lefta 위치 : " + lefta);
//		System.out.println(Arrays.toString(temp));
//		System.out.println(Arrays.toString(a) + "\n");
		
		for(int i = 0; i < tIdx; i++) {
			a[lefta + i] = temp[i];
		}		
	}

	// --- 퀵 정렬(비재귀 버전)---//
	static void MergeSort(Polynomial3[] a, int left, int right) {
		int mid = (left+right)/2;
		if (left == right) return;
		MergeSort(a, left, mid);
		MergeSort(a, mid+1, right);
		merge(a, left, mid, mid+1, right);
		return;
	}
	
	static void ShowPolynomial(String str, Polynomial3[] x, int count) {
		//str 변수는 다항식 이름으로 스트링이다
		//count가 -1이면 다항식 x의 length로 계산하고 0이상이면 count가 다항식 항의 숫자이다 
		//정렬후 다항식 x = 2.5x**7  + 3.8x**5  + 3.1x**4  + 1.5x**3  + 3.3x**2  + 4.0x**1  + 2.2x**0
		int n = 0;
		if (count < 0)
			n = x.length;
		else
			n = count;
		//구현코드
		
		System.out.print(str);
		
		for(int i = 0; i < n; i++) {
			if(i != n - 1)
				System.out.printf("%.1fx**%d + ", x[i].coef, x[i].exp);
			else
				System.out.printf("%.1fx**%d", x[i].coef, x[i].exp);
		}
		System.out.println();		
	}
	static int AddPolynomial(Polynomial3[]x,Polynomial3[]y,Polynomial3[]z) {
		//z = x + y, 다항식 덧셈 결과를 z로 주고 z의 항의 수 terms을 리턴한다 
		int p=0, q=0, r=0;
		int terms = 0;
		//구현코드
		
		while(p < x.length && q < y.length) {
			if(x[p].compareTo(y[q]) > 0){
				z[terms++] = x[p++];				
			} else if(x[p].compareTo(x[q]) < 0){
				z[terms++] = y[q++];
			} else {
				z[terms].coef = x[p].coef + y[q].coef;
				z[terms].exp = x[p].exp;
				p++;
				q++;
				terms++;
			}				
		}
		
		while(p < x.length)
			z[terms++] = x[p++];
		
		while(q < y.length)
			z[terms++] = y[q++];
		
		return terms;		
	}
	
	static int addTerm(Polynomial3[]z, Polynomial3 term, int terms) {
		//다항식 z에 새로운 항 term을 추가한다. 지수가 같은 항이 있으면 계수만 합한다
		//추가된 항의 수를 count하여 terms으로 리턴한다.
		//구현코드
		
		for(int i = 0; i < terms; i++) {
			if(term.compareTo(z[i]) == 0) {
				z[i].coef += term.coef;
				return terms;
			}
		}
		
		z[terms] = term;			
		return ++terms;			
	}
	
	static int MultiplyPolynomial(Polynomial3[]x,Polynomial3[]y,Polynomial3[]z) {
		//z = x * y, 다항식 z의 항의 수는 terms으로 리턴한다
		//terms = addTerm(z, term, terms);사용하여 곱셈항을 추가한다.
		int terms = 0;
		//구현코드
		
		Polynomial3 term;
		double zcoef;
		int zexp;
		
		for(int i = 0; i < x.length; i++) {
			for(int j = 0; j < y.length; j++) {
				zcoef = x[i].coef * y[j].coef;
				zexp = x[i].exp + y[j].exp;
				
				term = new Polynomial3(zcoef, zexp);
				terms = addTerm(z, term, terms);
			}
		}		
		return terms;
	}
	
	static double EvaluatePolynomial(Polynomial3[]z, int zTerms, int value) {
		//zTerms는 다항식 z의 항의 수, value는 f(x)를 계산하기 위한 x 값
		//다항식 계산 결과를 double로 리턴한다 
		double result = 0.0;
		//구현 코드
		
		// 방법 1 : for문으로 제곱 계산
//		for(int i = 0; i < zTerms; i++) {
//			int square = 1;
//			for(int j = 0; j < z[i].exp; j++) {
//				square *= value;
//			}
//			result += z[i].coef * (double)square;
//		}
		
		// 방법 2 : Math.pow()메서드 사용
		for(int i = 0; i < zTerms; i++) {
			result += z[i].coef * (Math.pow(value, z[i].exp));
		}
		
		
		return result;
	}
	
	public static void main(String[] args) {
		Polynomial3[] x = {
		         new Polynomial3(1.5, 3),
		         new Polynomial3(2.5, 7),
		         new Polynomial3(3.3, 2),
		         new Polynomial3(4.0, 1),
		         new Polynomial3(2.2, 0),
		         new Polynomial3(3.1, 4),
		         new Polynomial3(3.8, 5),
		     };
		Polynomial3[] y = {
		         new Polynomial3(1.5, 1),
		         new Polynomial3(2.5, 2),
		         new Polynomial3(3.3, 3),
		         new Polynomial3(4.0, 0),
		         new Polynomial3(2.2, 4),
		         new Polynomial3(3.1, 5),
		         new Polynomial3(3.8, 6),
		     };
		
		int nx = x.length;

		ShowPolynomial("다항식 x = ", x, -1);
		ShowPolynomial("다항식 y = ", y, -1);
		System.out.println("=".repeat(50));
		
		MergeSort(x, 0, x.length - 1); // 배열 x를 퀵정렬
		MergeSort(y, 0, y.length - 1); // 배열 x를 퀵정렬
		ShowPolynomial("정렬후 다항식 x = ", x, -1);
		ShowPolynomial("정렬후 다항식 y = ", y, -1);
		System.out.println("=".repeat(50));
		
		Polynomial3[] z = new Polynomial3[50];
		
		for (int i =0; i < z.length; i++)
			z[i] = new Polynomial3();
	
		int zTerms = AddPolynomial(x,y,z);//다항식 덧셈 z = x + y
		ShowPolynomial("덧셈후 다항식 z = ", z, zTerms);
		System.out.println("=".repeat(50));
		
		// 정답 : 9.5x^{13}+7.75x^{12}+19.94x^{11}+31.81x^{10}+29.92x^9+40.3x^8+58.46x^7+46.42x^6+50.11x^5+40.94x^4+28.21x^3+24.7x^2+19.3x+8.8
		zTerms = MultiplyPolynomial(x,y,z);//다항식 곱셈 z = x * y
		MergeSort(z, 0, zTerms); // 배열 z를 퀵정렬
		ShowPolynomial("곱셈후 다항식 z = ", z, zTerms);
		System.out.println("=".repeat(50));
		
		int xValue = 2;
		double result = EvaluatePolynomial(z, zTerms, xValue);//다항식 값 계산 함수 z(10) 값 계산한다 
		System.out.println("x의 값이 " + xValue + " 일때 : ");
		System.out.printf("result = %.1f", result);
	}
}
