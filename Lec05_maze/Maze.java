package Lec05_maze;

public class Maze {

	// Output a path (if any) in the maze; 
	//rows is m, cols is p;
	void solveMaze(int[][] input) {
		
		int[][] moves = {
				{-1,0}, //N(0)
				{-1,1}, //NE(1)
				{0,1},  //E(2)
				{1,1},  //SE(3)
				{1,0},  //S(4)
				{1,-1}, //SW(5)
				{0,-1}, //W(6)
				{-1,-1} //NW(7)
		};
		
		//가장자리 1로 채워주기 위하여 maze[m+2][p+2]로 선언
		int[][] maze = new int[14][17];
		int[][] mark = new int[14][17];
		
		//maze판 초기화
		for (int i=0; i<14; i++) {
			for (int j=0; j<17; j++) {
				//border 모두 1로 선언
				if (i==0 || j==0 || i==13 || j==16) {
					maze[i][j] = 1;
					mark[i][j] = 1;
				} 
				//maze선언 (input 덮어쓰기) mark는 모두 0으로 선언
				else {
					maze[i][j] = input[i-1][j-1];
					mark[i][j] = 0;
				}
			}
		}
		
		//스택객체 생성
		genericStack s = new genericStack(100);
		
		//start at (1,1)
		boolean check = false;
		
		mark[1][1] = 1;
		Point p = new Point (1,1,2); //처음 방향 East(2)
		s.push(p);
		
		//stack not empty
		while(!s.isEmpty()) {
			
			p = s.pop();
			int i = p.getX(); int j = p.getY(); int d = p.getDir();	
			
			//8방향 (0~7) 탐색 혹은 경로를 찾을때까지 반복
			while (d < 8) {
				
				int x = i + moves[d][0];
				int y = i + moves[d][0];
								
				//reached exit
				if((x == 12) && (y== 15)) {
					
					p = new Point(x,y,d);
					s.push(p);
					System.out.printf("the term near the exit: %d, %d", i, j);
					System.out.printf("exit: %d, %d", x, y);
					return;				
				}
				
			}
			
			
			
			
			
		}
		

		
	}
	

	
	public static void main(String[] args) {

		//12x15 배열
		int[][] input = {
	            { 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1 },
	            { 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1 },
	            { 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1 },
	            { 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0 },
	            { 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1 },
	            { 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1 },
	            { 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1 },
	            { 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
	            { 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1 },
	            { 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0 },
	            { 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 },
	            { 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0 },
	        };

	}

}
