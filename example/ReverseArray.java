package example;

import java.util.Arrays;
import java.util.Scanner;

public class ReverseArray {
	
	static void swap(int [] a, int idx1, int idx2) {
		
		int temp = a[idx2];	//임시값에 2번 저장해놓고
		a[idx2] = a[idx1];	//2번에 1번값 저장
		a[idx1] = temp;		//1번에 2번값 저장
	}
	
	static void reverse (int [] a) {			//[0]번째 요소와 [a.length-1] 요소 바꾸기
												//[1]번째 요소와 [a.length-1-1] 요소 바꾸기 ...
		for(int i = 0; i<a.length/2; i++) {
			swap(a, i, a.length-1-i);
		}
	}

	public static void main(String[] args) {

		try (Scanner stdIn = new Scanner(System.in)) {
			System.out.println("요소개수 : ");
			int num = stdIn.nextInt();
			
			int[] x = new int [num];
			
			for (int i = 0; i < num; i++) {
				System.out.print("x[" + i + "] = ");
				x[i] = stdIn.nextInt();					//입력한 요소개수만큼 입력받음!
			}
			
			reverse(x);
			System.out.println("\nreverse 정렬 결과 : ");
			System.out.println("x = " + Arrays.toString(x));
		}
		
	}

}
