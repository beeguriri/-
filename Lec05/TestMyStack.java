package Lec05;

import java.util.Random;
import java.util.Scanner;

public class TestMyStack {

	public static void main(String[] args) {
		System.out.println("**Stack Test**");

		Scanner stdIn = new Scanner(System.in);
		MyStack s = new MyStack(8); // 최대 8 개를 push할 수 있는 stack
		Random random = new Random();
		
		int rndx = 0, rndy = 0;
		Point p = null;
		
		while (true) {
			System.out.println(); // 메뉴 구분을 위한 빈 행 추가
			System.out.printf("현재 데이터 개수: %d / %d\n", s.size(), s.getCapacity());
			System.out.print("(1)push　(2)pop　(3)peek　(4)dump　(0)종료: ");

			int menu = stdIn.nextInt();
			if (menu == 0)
				break;

			switch (menu) {
			case 1: // 푸시
				System.out.print("데이터: ");
				rndx = random.nextInt() % 20;
				rndy = random.nextInt() % 20;
				p = new Point(rndx,rndy);
				try {
					s.push(p);
					System.out.println("push한 데이터는 " + p + "입니다.");
				} catch(MyStack.OverflowMyStackException e) {	//genericStack 클래스의
																		//이너클래스 OverflowGenericStackException
																		//의 변수 e
					System.out.println("stack이 가득찼있습니다.");
				}
				break;

			case 2: // 팝
				try {
					p = s.pop();
					System.out.println("pop한 데이터는 " + p + "입니다.");
				} catch(MyStack.EmptyMyStackException e) {
					System.out.println("stack이 비어있습니다.");
				}
				break;

			

			case 4: // 덤프
				s.print();
				break;
			}
		}
	}

}
