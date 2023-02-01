package Lec04;

import java.util.Random;
import java.util.Scanner;

//Point 클래스는 같은 패키지 내에 만들어놨으므로.. 쓸수있음!!


public class Chap4_Test_Queue {

	public static void main(String[] args) {
		
		System.out.println("**Queue Test**");
		Scanner stdIn = new Scanner(System.in);
		genericQueue s = new genericQueue(8); // 최대 8 개를 push할 수 있는 큐
		Random random = new Random();
		
		int rndx = 0, rndy = 0;
		Point p = null;

        while (true) {
        	System.out.println("===================="); // 메뉴 구분을 위한 빈 행 추가
            System.out.printf("현재 데이터 개수: %d / %d\n", s.size(), s.getCapacity());
            System.out.print("(1)push　(2)pop　(3)peek　(4)dump　(0)종료: ");

            int menu = stdIn.nextInt();
            if (menu == 0) break;

            switch (menu) {
             case 1:                                // 푸쉬
                System.out.print("데이터: ");
                rndx = random.nextInt() % 20;
                rndy = random.nextInt() % 20;
				p = new Point(rndx,rndy);
 
                try {
                    s.push(p);
					System.out.println("push한 데이터는 " + p + "입니다.");
                 } catch (genericQueue.OverflowgenericQueueException e) {
                    System.out.println("큐가 가득 찼습니다.");
                }
                break;

             case 2:                                // 팝
                try {
                     p = s.pop();
                    System.out.println("pop한 데이터는 " + p + "입니다.");
                 } catch (genericQueue.EmptygenericQueueException e) {
                    System.out.println("큐가 비어 있습니다.");
                }
                break;

             case 3:                                // 피크
                try {
                     p = s.peek();
                    System.out.println("피크한 데이터는 " + p + "입니다.");
                 } catch (genericQueue.EmptygenericQueueException e) {
                    System.out.println("큐가 비어 있습니다.");
                }
                break;

             case 4:                                // 덤프
                s.dump();
                break;
            }
        }
	}

}
