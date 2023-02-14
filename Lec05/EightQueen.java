package Lec05;

import Lec05.MyStack.EmptyMyStackException;

class MyStack {
	
    public Point data[];	// 스택용 배열
	public int capacity;	// 스택의 크기
	public int top; 		// 스택 포인터
	
	//--- 실행시 예외: 스택이 비어있음 ---//
	public class EmptyMyStackException extends Exception {
		private static final long serialVersionUID = 1L;
		public EmptyMyStackException() {
		}
	}

	//--- 실행시 예외: 스택이 가득 참 ---//
	public class OverflowMyStackException extends RuntimeException {
		private static final long serialVersionUID = 1L;
		public OverflowMyStackException() {
		}
	}

	//--- 생성자(constructor) ---//
	public MyStack(int capacity) {
		top = 0;							
		this.capacity = capacity;
		try {
			data = new Point[capacity];		 
		} catch (OutOfMemoryError e) {
			capacity = 0;
		}	
	}

	//--- 스택에 x를 푸시 ---//
	public Point push(Point x) throws OverflowMyStackException {
		if (top >= capacity)							//top이 용량을 넘어서면
			throw new OverflowMyStackException();	//예외처리
		return data[top++] = x;	
	}
	
	//--- 스택에서 데이터를 팝---//
	public Point pop() throws EmptyMyStackException {
		if (top <= 0)
			throw new EmptyMyStackException();
		return data[--top];
	}
	
	//--- 스택이 비어있는가? ---//
	public boolean isEmpty() {
		return top <= 0;
	}

	//--- 스택이 가득 찼는가? ---//
	public boolean isFull() {
		return top >= capacity;
	}
	
	//--- 스택의 크기를 반환 ---//
	public int getCapacity() {
		return capacity;
	}

	//--- 스택에 쌓여있는 데이터 갯수를 반환 ---//
	public int size() {
		return top;
	}
	
	//--- 스택 출력---//
	public void print() {
		if (top <= 0)
			System.out.println("stack이 비어있습니다.");
		else {
			for (int i = 0; i < top; i++)
				System.out.print(data[i] + " ");
			System.out.println();
		}
	}

	//--- 스택을 비움 ---//
	public void clear() {
		top = 0;
	}

} 

class Point {
	
	private int ix;
	private int iy;

	public Point(int x, int y) {
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


public class EightQueen {

//8  Queen 문제 풀기: 스택 사용하여 backtracking 코딩 실습
//stack에 Point 객체를 생성하여 push, pop::(x, y, move) => move는 다음 이동 가능 candidate의 column임
	
	// 함수 호출로 구현 훈련
	static void SolveQueen(int [][]d) throws EmptyMyStackException {
		
        MyStack s = new MyStack(8) ; //스택 size 8
        Point p = new Point(0,0);    //포인트 초기위치 0,0으로 세팅
        
        int ix = p.getX(); //초기행
        int iy = p.getY(); //초기열
        
        int cx = ix;		//현재행
        int cy = iy;		//현재열
        //System.out.println("현재 위치 (" + cx + ", " + cy + ")"); 

        int cnt = 0;	  //정답의 갯수 구하기
        
        d[ix][iy] = 1;	//프로그램 실행 시 (0,0)에 값 일단 넣고 시작함
        s.push(p);		//그때의 위치(0,0) stack에 push
         
        //스택에 쌓인 데이터의 갯수가 8개 미만이면 반복실행
        while (s.size()<8) {
        	
        	//data[0][j]에 데이터 넣기 시작
        	cx++;
        	cy=0;
        	
        	//
        	while (cx <8) {
        		
        		nextMove(d, cx, cy); //열을 이동
        		
        		while (cy < 8) {
        			
        			d[cx][cy] = 1;
        			Point cp = new Point (cx, cy);
        			s.push(cp);
        			break;
        		}
        		
        		if (cy != d.length) break;
        		else if (cy == d.length) {
        			p=s.pop();
        			cx = p.getX();
        			cy = p.getX();
        			d[cx][cy] = 0;
        			cy++;
        		}
        	}
        }
        
    	//stack이 full이 되면 정답이므로 출력 후 stack 비워고, 포인트 이동해서 시작
    	if(s.size() == 8) {
    		
    		++cnt;			//정답갯수
			System.out.printf("%3d번째 답 : ", cnt);
    		s.print();
    		cx++;
    		cy++;
    		s.clear();
    		return;	
    	}

	}
		
	//현재위치(x,y)에서 가로, 세로, 대각선에 대한 충돌 체크 함수 코딩
	//currentRow에 대하여 queen을 (x,y)에 배치 가능하면 true
	static boolean checkMove(int [][]d, int x, int y) {

		//행,열,대각선 체크해서 모두 true면 이동할 수 있다
		if (checkRow(d, x) && checkCol(d, y) 
			&& checkDiagSW(d, x, y) && checkDiagSE(d, x, y)) 
			return true;  

	    return false;	        
	}

	//현재 행(x)의 각 열에 값이 있으면 false
	static boolean checkRow(int [][] d, int x) {
		
		//d의 열의 갯수 d[0].length 만큼 반복
		for(int i=0; i < d[0].length; i++) {
			if(d[x][i] == 1) return false;
		}
		
		return true;
	}

	//현재 열(y)의 각 행에 값이 있으면 false
	static boolean checkCol(int [][] d, int y) {
		
		//d의 행의 갯수 d.length 만큼 반복
		for(int i=0; i < d.length; i++) {
			if(d[i][y] == 1) return false;
		}
		
		return true;
	}
	
	//오른쪽 대각선 체크 x++, y-- or x--, y++ where 0<= x,y <= 7
	static boolean checkDiagSW(int [][] d, int x, int y) {
		
		//현재 위치(x, y)에서 오른쪽 윗방향 체크
		for(int i=x, j=y; 0<=i && 7>=i && 0<=j && 7>=j; i--, j++) {
			if(d[i][j] == 1) return false;
		}
		
		//현재 위치(x, y)에서 왼쪽 아랫방향 체크
		for(int i=x, j=y; 0<=i && 0<=j && 7>=i && 7>=j; i++, j--) {
			if(d[i][j] == 1) return false;
		}
		
		return true;
		
	}
	
	//왼쪽 대각선 체크 x++, y++ or x--, y-- where 0<= x,y <= 7
	static boolean checkDiagSE(int [][] d, int x, int y) {
		
		//현재 위치(x, y)에서 오른쪽 아랫방향 체크
		for(int i=x, j=y; 0<=i && 7>=i && 0<=j && 7>=j; i++, j++) {
			if(d[i][j] == 1) return false;
		}
		
		//현재 위치(x, y)에서 왼쪽 아랫방향 체크
		for(int i=x, j=y; 0<=i && 7>=i && 0<=j && 7>=j; i--, j--) {
			if(d[i][j] == 1) return false;
		}
		
		return true;
	}
 
	//currentRow에 대한 다음 배치 가능한 모든 column을 조사하고 move[]에 1로 설정
	static void nextMove(int [][]d, int x, int y) {
		
		while(y < d[0].length) {
			
			if (checkMove(d,x,y))	d[x][y]=1;	

			y++;
		}
	}
	
	public static void main(String[] args) {
		
		//8*8 배열 선언
		int [][] data = new int [8][8];
		
		//8*8 배열 0으로 초기화
		for (int i = 0; i < data.length ; i++) {
			for (int j=0; j < data[0].length; j++) {
				data[i][j] = 0;
			}
		}
		
		SolveQueen(data);
		
	}
}
