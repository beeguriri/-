package example;

public class QueenA {

	//기본가정 : 같은 열에는 퀸을 놓지 않는다!
	//n=4일때 생각
	//i는 열, j는 행
	static int [] pos = new int [4]; //체스판의 크기, 퀸의 갯수
	static int count = 0;			 //정답횟수 세기
	
	static void print () {
		for(int i=0; i<4; i++) {
			System.out.printf("%2d", pos[i]);
		}
		
//		for (int i=0; i<4; i++) {
//			for (int j=0; j<4; j++) {
//				System.out.printf("%s", j == pos[i] ? "■" : "□");  
//			}
//			System.out.println();

//		}
		System.out.println();
	}
	
	//i번째 열과 k번째 열의 값 확인
	static boolean isPossible (int i) {
		
		//확인은 열 순서대로 하므로 해당열의 앞열 까지만 확인하면 됨
		for (int k = 0; k < i; k++) {
			
			// 같은 행에 있는 경우
			if (pos[i] == pos[k]) return false;
			
			// 대각선에 있는 경우
			else if (Math.abs(i - k) == Math.abs(pos[i] - pos[k]))	return false;
	
		}
		
		return true;
	}
	
	//i번째열에 퀸 배치
	static void setQueen (int i) {
		
		if(i==4) {
			
			++count;
			System.out.printf("%3d번째 답 : ", count);
			print();
			return;
		}
		
		for (int j = 0; j < 4; j++) {
			
			pos[i] = j;
			
			//해당열에 퀸 배치할수 있는지 확인 -> 할 수 있으면 열 증가
			if (isPossible(i)) 	setQueen(i + 1);
			
		}
	}
	
	
	public static void main(String[] args) {
		
		setQueen(0);

	}
}