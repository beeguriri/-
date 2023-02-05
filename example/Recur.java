package example;

//재귀에 대한 이해를 돕는 순수 재귀 메서드
import java.util.Scanner;

class Recur {
	//--- 순수 재귀 메서드 ---//
	static void recur(int n) {
		if (n > 0) {
			recur(n - 1);
			System.out.println(n);
			recur(n - 2);
		}
	}
 
	//--- 재귀를 제거 ---//
	static void recur1 (int n) {
		
		IntStack s = new IntStack(n);
		
		while(true) {
			
			//머리재귀제거 (recur(n-1))
			if (n > 0) {
				s.push(n);
				n = n-1;
				continue;
			}
			
			//꼬리재귀 제거 (recur(n-2))
			if (s.isEmpty() != true) {
				n = s.pop();
				System.out.println(n);
				n = n-2;
				continue;
			}
		break;
		}
	}
 
 
 public static void main(String[] args) {
     try (Scanner stdIn = new Scanner(System.in)) {
		System.out.print("정수를 입력하세요 : ");
		 int x = stdIn.nextInt();
		 System.out.println();
		 System.out.println("recur 메서드 호출");
		 recur(x);
		 System.out.println();
		 System.out.println("recur1 메서드 호출");
		 recur1(x);
	}
 }
}
