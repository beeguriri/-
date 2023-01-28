package practice;

import java.util.Scanner;

public class Chap2_Date {
	
	static class YMD {
	
		int y;	//년
		int m;	//월(1~12)
		int d;	//일(1~31)
		
		//--- 각 월의 일수 ---//
		static int[][] mdays = {
			{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31},	// 평년
			{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31},	// 윤년
		};
		
		//--- year 년은 윤년인가?(윤년 : 1/평년 : 0) ---//
		//4로 나누어 떨어지고 100으로 나누어 떨어지지않고 400으로 나누어 떨어지는 해는 윤년
		static int isLeap(int year) {
			return (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) ? 1 : 0;
		}
	
		//생성자
		YMD(int y, int m, int d) {
			this.y = y;
			this.m = m;
			this.d = d;
		}
		
		//--- n일 후의 날짜를 반환 ---//
		YMD after(int n) {
			
			YMD temp = new YMD(y, m, d);
			
			if (n < 0)	return before(-n);	//-n일 후는 n일전을 말한다 (-n : 양수를 만들어줌)

			temp.d += n;	//temp.d = d + n

			while (temp.d > mdays[isLeap(temp.y)][temp.m - 1]) {	//d가 29,30,31보다 크면
				
				temp.d -= mdays[isLeap(temp.y)][temp.m - 1];		//해당월의 최후날짜를 빼고
				
				if (++temp.m > 12) {								//그 월이 12보다 크면
					temp.y++;										//년을 더해주고
					temp.m = 1;										//월은 1로 넘어간다
				}
			}
			return temp;
		}
		
		//--- n일 전의 날짜를 반환 ---//
		YMD before(int n) {
			
			YMD temp = new YMD(y, m, d);
			
			if (n < 0)
				return after(-n);		//-n일 전은 n일후을 말한다 (-n : 양수를 만들어줌)

			temp.d -= n;

			while (temp.d < 1) {
				
				if (--temp.m < 1) {
					
					temp.y--;
					temp.m = 12;
				}
				
				temp.d += mdays[isLeap(temp.y)][temp.m - 1];
			}
			return temp;
		}
	}
	
	public static void main(String[] args) {
		
		try (Scanner stdIn = new Scanner(System.in)) {
			System.out.print("날짜를 입력하세요.\n");
			System.out.print("년 : "); int y = stdIn.nextInt();
			System.out.print("월 : "); int m = stdIn.nextInt();
			System.out.print("일 : "); int d = stdIn.nextInt();
			
			YMD date = new YMD(y, m, d);

			System.out.print("며칠 전/후의 날짜를 구할까요? : ");
			int n = stdIn.nextInt();

			YMD d1 = date.after(n);
			System.out.printf("%d일 후의 날짜는 %4d년%2d월%2d일입니다.\n", n, d1.y, d1.m, d1.d);

			YMD d2 = date.before(n);
			System.out.printf("%d일 전의 날짜는 %4d년%2d월%2d일입니다.\n", n, d2.y, d2.m, d2.d);

		}
	}
}
