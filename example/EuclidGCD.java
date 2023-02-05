package example;

import java.util.Scanner;

public class EuclidGCD {
	
	static int gcd(int x, int y) {
		
		int min, max;
		
		//min, max 정하기
		if (x>y) {			
			max = x;
			min = y;
		} else {
			max = y;
			min = x;
		}
		
		//n과 0의 최대공약수는 n이라고 한다.
		if (min==0) return max;
		else 	  	return gcd(min,max%min);
		
}

	public static void main(String[] args) {

		try (Scanner stdIn = new Scanner(System.in)) {
			
			int x, y;
			System.out.println("두 정수의 최대공약수 ======");
			do {
				System.out.print("정수 x를 입력하세요: ");
				x = stdIn.nextInt();
			} while (x<0);
			do {
				System.out.print("정수 y를 입력하세요: ");
				y = stdIn.nextInt();
			} while (y<0);

			System.out.printf("입력한 숫자 x, y : %2d, %2d\n", x, y);
			System.out.println("최대공약수 = " + gcd(x,y));
		}		
		
	}

}
