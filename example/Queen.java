package example;

public class Queen {

	//n=4일때 생각해보기
	
	static int [] pos = new int [4]; //체스판의 크기, 퀸의 갯수
	
	//배치되면 true, 아니면 false
	static boolean[] flag_a = new boolean[4]; //각 행에 퀸을 배치 했는지 체크
	static boolean[] flag_b = new boolean[7]; //대각선 "/"방향 (i+j=3)
	static boolean[] flag_c = new boolean[7]; //대각선 "\"방향 (i-j=0)
	
	static void print () {
		for(int i=0; i<4; i++) {
			System.out.printf("%2d", pos[i]);
		}
		System.out.println();

	}
	
	static void set (int i) {
		//i는 열, j는 행
		//i=0일때 최초 호출
		for (int j = 0; j<4; j++) {
			
			if(flag_a[j] == false &&			//각 행과 대각선에 Q가 없으면
				flag_b[i+j]==false &&
				flag_c[i-j+3]==false) {
				
				pos[i] = j;					//j행에 배치
				
				if (i==3)  print();
				else {
					flag_a[j] = flag_b[i+j] = flag_c[i-j+3] = true;
					set(i+1);
					flag_a[j] = flag_b[i+j] = flag_c[i-j+3] = false;
				}
			
			}
		}	
	}
	
	
	public static void main(String[] args) {
		
		set(0);

	}
}
