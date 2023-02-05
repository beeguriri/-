package example;

import java.util.Scanner;

class Factorial {
	
	//양의정수 n의 팩토리얼값 반환하는 메서드
	static int factorial (int n) {
		
		if(n>0) return n*factorial(n-1);
		else	return 1;
	}

	public static void main(String[] args) {

		try (Scanner stdIn = new Scanner(System.in)) {
			int x;
			
			//n이 0보다 큰 값이 입력될때까지 수행
			do {
				System.out.print("양의 정수를 입력하세요 : ");
				x = stdIn.nextInt();
			} while (x<=0);

			System.out.println("결과 : " + x + "! = " + factorial(x));
			
			
			
			/*n=3일때, factorial(3) 실행 =>
			리턴 3*factorial(2)
			      -> 리턴 2*factorial(1)
			               -> 리턴 1*factorial(0)
			                        -> 리턴 1
			  => 3 * 2 * 1 * 1 = 6
			*/
		}
	
	}

}