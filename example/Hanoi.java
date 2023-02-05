package example;

import java.util.Scanner;

//재귀알고리즘
//하노이의 탑 : 기둥 세개 사이에서 움직임
public class Hanoi {
	
	//원반을 옮기는 함수
	//no : 원반 개수
	//x번 기둥에서 y번 기둥으로 옮김
	static void move (int no, int x, int y) {
		
		//기둥 1,2,3번이 있으므로 기둥의 합은 6
		//x번 기둥에서 중간기둥으로 옮김
		if(no>1)	move(no-1, x, 6-x-y);
		
		System.out.printf("원반[%d]을(를) %d기둥에서 %d기둥으로 옮김\n", no, x, y);
		
		//
		if(no > 1)	move(no-1, 6-x-y, y);
		
	}

	public static void main(String[] args) {

		try (Scanner stdIn = new Scanner(System.in)) {
			int x;
			
			System.out.println("하노이의 탑 ====");
			do {
				System.out.println("원반의 개수 : ");
				x = stdIn.nextInt();	
			} while (x <= 0);
			
			move(x, 1, 3);
		}
		
	}

}
