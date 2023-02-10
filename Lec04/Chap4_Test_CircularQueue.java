package Lec04;

import java.util.Random;
import java.util.Scanner;

//Point1 클래스
class Point1 {
	
	private int ix;
	private int iy;

	public Point1(int x, int y) {
		ix = x;
		iy = y;
	}

	public String toString() {
		return "<" + ix + ", " + iy + ">";
	}

	public int getX() {
		return ix;
	}

	public int getY() {
		return iy;
	}

	public void setX(int x) {
		ix = x;
	}

	public void setY(int y) {
		iy = y;
	}
	
}



public class Chap4_Test_CircularQueue {

	public static void main(String[] args) {

		System.out.println("**Circular Test**");
		try (Scanner stdIn = new Scanner(System.in)) {
			CircularQueue s = new CircularQueue(4); // 최대 4 개를 push할 수 있는 큐
			Random random = new Random();
			
			int rndx = 0, rndy = 0;
			Point1 p = null;

			while (true) {
				System.out.println("\n===================="); 
			    System.out.print("(1)push　(2)pop　(3)dump　(0)종료: ");

			    int menu = stdIn.nextInt();
			    if (menu == 0) break;

			    switch (menu) {
			     case 1: // 푸쉬
			        rndx = random.nextInt() % 20;
			        rndy = random.nextInt() % 20;
					p = new Point1(rndx,rndy);
 
			        try {
			            s.push(p);
						System.out.println("push한 데이터는 " + p + "입니다.");
			         } catch (CircularQueue.OverflowCircularQueueException e) {
			            System.out.println("큐가 가득 찼습니다.");
			        }
			        break;

			     case 2: // 팝
			        try {
			             p = s.pop();
			            System.out.println("pop한 데이터는 " + p + "입니다.");
			         } catch (CircularQueue.EmptyCircularQueueException e) {
			            System.out.println("큐가 비어 있습니다.");
			        }
			        break;

			     case 3: // 덤프
			        s.dump();
			        break;
			    }
			}
		}
	}

}
