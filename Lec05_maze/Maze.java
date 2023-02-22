package Lec05_maze;

import Lec05_maze.MazeStack.EmptyMazeStackException;

public class Maze {

	// Output a path (if any) in the maze; 
	//rows is m, cols is p;
	static void solveMaze(int[][] input, int m, int p) {
		
		//가장자리 1로 채워주기 위하여 maze[m+2][p+2]로 선언
		int[][] maze = new int[m+2][p+2];
		int[][] mark = new int[m+2][p+2];
		
		//maze판 초기화
		for (int i=0; i<m+2; i++) {
			for (int j=0; j<p+2; j++) {
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
		MazeStack s = new MazeStack(100);
		
		int[][] moves = {
				{0,1},  //E(0)
				{1,1},  //SE(1)
				{1,0},  //S(2)
				{1,-1}, //SW(3)
				{0,-1}, //W(4)
				{-1,-1}, //NW(5)
				{-1,0}, //N(6)
				{-1,1}, //NE(7)
		};
		
		//start at (1,1)
		Point p1 = new Point (1,1,0); //처음 방향 East
		s.push(p1);
		mark[1][1] = 1;	//지나온 길 확인용(백트래킹)
		maze[1][1] = 8;	//지나온 길 확인용(출력시)
		
		boolean check = false; //정답확인용
		
		//stack not empty
		while(!s.isEmpty() && !check) {
			
			try {
				p1 = s.pop();
				int ix = p1.getX(); int iy = p1.getY(); int d = p1.getDir();	
				
				//8방향 (0~7) 탐색 혹은 경로를 찾을때까지 반복
				while (d < 8) {
					
					int fx = ix + moves[d][0];
					int fy = iy + moves[d][1];

					//new position
					//mark=1이면 지나간 위치, mark=0이면 지나가지 않은 위치
					if(maze[fx][fy] ==0 && mark[fx][fy]!=1) {
						mark[fx][fy] = 1;
						
						p1 = new Point (ix, iy, d);
						s.push(p1);
						maze[ix][iy] = 8;
						ix = fx; iy = fy; d=0; //moves to (g,h)

					} else d++;	//try next direction
					
					//reached exit (성공)
					if((ix == m) && (iy == p)) {
						
						p1 = new Point (ix, iy, d);
						s.push(p1);
						maze[ix][iy] = 8;
						check = true;
					}
				}
				
			} catch (EmptyMazeStackException e) {
				e.printStackTrace();
			}

		}
		
		//정답 출력
		if(check) {
			
			for(int i=1; i<13; i++) {
				for(int j=1; j <16; j++) {
					if(maze[i][j]==8) System.out.print("▲");
					else System.out.print(maze[i][j]);
				}
				System.out.println();
			}
			System.out.println("미로찾기 완료!");
		} else System.out.println("더이상 길이 없습니다.");
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

		solveMaze(input, 12, 15); //m,p 넘겨줌
		
	}

}
