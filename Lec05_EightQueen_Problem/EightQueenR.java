package Lec05_EightQueen_Problem;

public class EightQueenR {
	
	static int[][] board;
	static int size;
	static int ans = 0;
	
	//생성자
	EightQueenR(int n) {
		size = n;
		board = new int[size][size];
	}
	
	//프린트함수
	void printQ() {
		
		System.out.printf("====%3d번째 정답====\n", ++ans);
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				if(board[i][j]==1) {
					
					System.out.print(" ●");
				} else {
					System.out.print(" ○");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	
	void solveQ(int row) {
		
		if(row == size) {
			printQ();
			return;
		}
		
		for (int col = 0; col < size; col++) {
			if(isSafe(row,col)) {
				board[row][col]=1;
				solveQ(row+1);
				board[row][col]=0;
			}
		}
	}
	
	//충돌여부 체크
	boolean isSafe(int row, int col) {
		
		//윗쪽 행 체크
		for(int i=0; i<row; i++) {
			if (board[i][col]==1)	return false;
		}
		
		//왼쪽 대각선 체크
		for(int i=row, j=col; i >= 0 && j >= 0; i--, j--) {
			if (board[i][j]==1)		return false;
		}
		
		//오른쪽 대각선 체크
		for (int i=row, j=col; i >=0 && j < size; i--, j++) {
			if (board[i][j]==1)		return false;
		}
		
		return true;
		
	}
	
	

	public static void main(String[] args) {
	
		EightQueenR ans = new EightQueenR(8);
		ans.solveQ(0);
	}

}
