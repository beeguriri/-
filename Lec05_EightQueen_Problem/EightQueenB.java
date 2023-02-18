package Lec05_EightQueen_Problem;

import Lec05_EightQueen_Problem.genericStack.EmptyGenericStackException;

public class EightQueenB {
	
	static int[][] board;
	static int size;
	
	static genericStack s;
	static Point p;
	
	//생성자 (호출 시, 보드크기, 스택, 포인트 세팅)
	EightQueenB(int n) {
		size = n;
		board = new int[size][size];
		s = new genericStack(10);
		p = new Point (0,0);
	}
	
	//문제 풀기 메서드
	boolean solveQ() {
		
		int row=0;
		int col=0;
		
		while (row<size) {	//한 행에 하나 배치, 한 행마다 열 검사
			
			while (col < size) {	//col++ 반복
				
				if(checkMove(row,col)) {	
					
					//해당 위치가 유효하면 해당위치를 스택에 푸쉬
					board[row][col] =1;
					Point pc = new Point (row, col);
					s.push(pc);
					col = 0;		//다음행으로 넘어가서 다시 col=0부터 확인
					break;
				}
				
				else col++;
				
			}
			
			//현재 row, col에서 유효한 위치를 찾지못했을 경우, 현재값은 0으로 되돌리고
			//이전 행의 col값 pop후 col+1하여 다시 확인
			
			if (col == size) {
				
				if(s.isEmpty())	return false;	//스택이 비어있으면 return false
				
				try {
					p = s.pop();
					int px = p.getX();
					int py = p.getY();
					board[px][py] = 0;
					row = px;
					col = py+1;

				} catch (EmptyGenericStackException e) {
					e.printStackTrace();
				}
			}
			
			else	row++;
			
		}
		
		return true;
	}
	
	//퀸 충돌여부 검사 메서드 
	//현재 행 기준 위쪽 행만 검사, 아래쪽에는 아직 퀸 배치하지 않음
	boolean checkMove(int row, int col) {
		
		//현재 열에 대하여 각 행 검사
		for(int i=0; i < row; i++) {
			if (board[i][col]==1)		return false;
		}
		
		//대각선 검사 (왼쪽 위)
		for (int i = row, j=col; i >= 0 && j >=0; i--, j--) {
			if (board[i][j]==1)			return false;
		}
		
		//대각선 검사 (오른쪽 위)
		for (int i=row, j=col; i >= 0 && j < size; i--, j++) {
			if (board[i][j]==1)			return false;
		}
		
		return true;
	}
	
	//정답 프린트
	void printQ () {
		for (int i=0; i< size; i++) {
			for (int j=0; j<size; j++) {
				if(board[i][j]==1) {
					
					System.out.print(" ■");
				} else {
					System.out.print(" □");
				}			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		
		EightQueenB ans = new EightQueenB(8);
		if(ans.solveQ()) {
			ans.printQ();
			System.out.printf("저장된 스택데이터 : %d개\n", s.size()); 
			s.dump();
		}
		else {
			System.out.println("정답이 없습니다.");
		}

	}

}
