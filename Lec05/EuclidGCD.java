package Lec05;

import java.util.Scanner;

public class EuclidGCD {

    //--- 정수 x, y의 최대공약수를 구하여 반환 ---//
	//큰수, 작은수를 굳이 안나누어줘도 됨
    static int gcd(int x, int y) {
    	
    	System.out.println("x = " + x + ", y = " + y);
    	
        if (y == 0)
            return x;
        else
            return gcd(y, x % y);
    }
    
	public static void main(String[] args) {
		
        try (Scanner stdIn = new Scanner(System.in)) {
        	
			System.out.println("두 정수의 최대공약수를 구합니다.");

			System.out.print("정수를 입력하세요 : ");  int x = stdIn.nextInt();
			System.out.print("정수를 입력하세요 : ");  int y = stdIn.nextInt();

			System.out.println("최대공약수는 " + gcd(x, y) + "입니다.");
			
		}
	}

}
