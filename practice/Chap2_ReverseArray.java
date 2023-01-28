package practice;

import java.util.Arrays;
import java.util.Scanner;

public class Chap2_ReverseArray {

	
	//요소값 바꾸기
	static void swap(int [] a, int idx1, int idx2) {
		
		int temp = a[idx2];	//임시값에 2번 저장해놓고
		a[idx2] = a[idx1];	//2번에 1번값 저장
		a[idx1] = temp;		//1번에 2번값 저장
	}
	
	//요소값 역순으로 정렬
	static void reverse (int [] a) {			//[0]번째 요소와 [a.length-1] 요소 바꾸기
												//[1]번째 요소와 [a.length-1-1] 요소 바꾸기 ...
		for(int i = 0; i<a.length/2; i++) {
			
			swap(a, i, a.length-1-i);
			System.out.printf("a[%d]와 a[%d]를 교환합니다.\n", i, a.length-1-i);
			System.out.println(Arrays.toString(a));
		}
	}
	
	//배열 a의 모든 요소의 합계 반환
	static int sumOf(int[] a) {
		
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
		}
		return sum;
	}
	
	//배열 a의 요소를 배열 b에 복사하는 메서드
	static void copy (int[] a, int[] b) {
		
		for(int i =0; i < a.length; i++) {
			b[i] = a[i];
		}
	}
		
	//배열 a의 요소를 역순으로 복사하는 배열 c
	static void rcopy (int[] a, int [] c) {
		
		for(int i= 0; i < a.length; i++) {
			c[a.length-1-i] = a[i];
		}
	}
	
	public static void main(String[] args) {

		try (Scanner stdIn = new Scanner(System.in)) {
			System.out.println("요소개수 : ");
			int num = stdIn.nextInt();
			
			int[] a = new int [num];
			int[] b = new int [a.length]; //a를 복사하는 배열
			int[] c = new int [a.length]; //a를 역순으로 복사하는 배열
			
			for (int i = 0; i < num; i++) {
				System.out.print("a[" + i + "] = ");
				a[i] = stdIn.nextInt();					//입력한 요소개수만큼 입력받음!
			}

			System.out.println("\na = " + Arrays.toString(a));
			
			//배열 a 역순 정렬 : 결과 x에 저장
			System.out.println("\n==== 역순 정렬====");
			reverse(a);
			System.out.println("역순정렬을 마쳤습니다.");
			
			//배열 a의 모든 요소의 합계
			System.out.printf("\n==== 배열 a의 모든 요소의 합계 : %d ====\n", sumOf(a));
			
			//배열 a의 요소를 배열 b에 복사
			System.out.println("\n==== 배열 a의 요소를 배열 b에 복사 ====");
			copy(a,b);
			System.out.println("b = " + Arrays.toString(b));
			
			//함수사용 clone()
			b = a.clone();
			System.out.println("a.clone() = " + Arrays.toString(b));

			//배열 a의 요소를 역순으로 복사하는 배열 c
			System.out.println("\n==== 배열 a의 요소를 배열 c에 역순으로 복사 ====");
			rcopy(a,c);
			System.out.println("c = " + Arrays.toString(c));
			
		}
		
	}


}
