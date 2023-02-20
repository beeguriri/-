package Lec05_maze;

public class ㄴㄴ {
	
	static void Sol(int[][] in,int sx, int sy, int dx, int dy) {
		// maze, mark : in보다 1칸씩 많은 배열 (모서리 1로 만듦.)
		int[][] maze = new int[14][17]; //
		int[][] mark = new int[14][17]; //

		
		// (1) 가장자리 1로 채움. (2) mark x와 같게,  
		for (int i = 0; i < 14; i++) {
			for (int j = 0; j < 17; j++) {
				if ((i == 0) || (j == 0) || (i == 13) || (j == 16)) {
					maze[i][j] = 1;
					mark[i][j] = 1;

				}
				else {
					maze[i][j] = in[i-1][j-1];
					mark[i][j] = 0;
				}
			}
		}
	
	// 스택, move 생성
	MazeStack st = new MazeStack(100);
	int[][] move = {
			{1,0}, // E
			{1,1}, // SE
			{0,1}, // S
			{-1,1}, // SW
			{-1,0}, // W
			{-1,-1}, // NW
			{0,-1}, // N
			{1,-1},	// NE
	};
	// 초기 위치 설정한 뒤 스택에 푸시
	int px , py, pdir,Fx, Fy;
	boolean chk = false;
	
	MazeP p = new MazeP(sx,sy,0);
	st.push(p); mark[sx][sx] = 1; 

	while(!st.isEmpty() && !chk) {
		p = st.pop();
		px = p.getX(); py = p.getY(); pdir = p.getDir();	
		while(pdir < 8 && !chk) { 
			// 8방향 다 탐색하거나 경로를 찾을 때까지
			Fx = px + move[pdir][0];
			Fy = py + move[pdir][1];
			if(maze[Fy][Fx] == 0 && mark[Fy][Fx] != 1) {
				mark[Fy][Fx] = 1; 
				// 가능
				p = new MazeP(px,py,pdir);
				st.push(p);
				px = Fx; py = Fy; pdir = 0;
			} else {
				pdir++;
			}
			if(px == dx && py == dy){  
				// 성공!!
				p = new MazeP(px,py,pdir);
				st.push(p);
				chk = true;
			}
		}
	}
	
	if(chk){
		for(int i = 0;i<st.Answer().length; i++) {
			for(int j = 0;j<st.Answer()[0].length;j++) {
				System.out.print(st.Answer()[i][j] + " ");
				if(j==st.Answer()[0].length-1)System.out.println("");
			}
		}
		System.out.println();
	} else {
		System.out.println("경로를 찾지 못했습니다.");
		}
	}
}

public static void main(String[] args) {
	int[][] in = {
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
			{ 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0 }};
	Sol(in,1,1,15,12);
	// sol(미로, 출발x, 출발y, 도착x, 도착y)
}

