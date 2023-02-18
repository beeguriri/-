package Lec05_EightQueen_Problem;

import Lec05_EightQueen_Problem.genericStack.EmptyGenericStackException;

public class EightQueenB_Allsolution {
	
	//현재 답 1개만 구할 수 있음
	//스택사이즈와 배열사이즈가 같으면 모든 퀸 배치 완료
	//출력 후 배열 초기화 하고
	//row=0, col => 한칸 증가시킬수 있으면 될거 같은데...
	
	static int[][] board;
	static int size;
	
	static genericStack s;
	static Point p;
	
	//생성자 (호출 시, 보드크기, 스택, 포인트 세팅)
	EightQueenB_Allsolution(int n) {
		size = n;
		board = new int[size][size];
		s = new genericStack(10);
		p = new Point (0,0);
	}
	
	//문제 풀기 메서드
	boolean solveQ() {
		
		int row=0;
		int col=0;
		int ans=0;		//정답갯수 카운트
		
		while (s.size() <= size) {
		
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
		//프린트 완료하면 보드 초기화 (0)
	}
	
	//프린트 후 큐 초기화
	void clearQ() {
		for (int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				board[i][j] = 0;
			}
		}
	}

	public static void main(String[] args) {
		
		EightQueenB_Allsolution ans = new EightQueenB_Allsolution(8);
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
