package Lec05;

import Lec05.MyStack.EmptyMyStackException;

public class EightQueen {

//8  Queen 문제 풀기: 스택 사용하여 backtracking 코딩 실습
//stack에 Point 객체를 생성하여 push, pop::(x, y, move) => move는 다음 이동 가능 candidate의 column임
	
	static void printQ(int [][] d) {
			
			for (int i=0; i< d.length; i++) {
				for (int j=0; j<d[0].length; j++) {
					System.out.print(" " + d[i][j]);
				}
				System.out.println("");
			}
			System.out.println("============");
			
	}
	
	static void clearQ (int[][]d) {
		for (int i = 0; i < d.length ; i++) {
			for (int j=0; j < d[0].length; j++) {
				d[i][j] = 0;
			}
		}
	}
	
	// 함수 호출로 구현 훈련
	static void SolveQueen(int [][] d) {
        
        int count = 0;				//stack에 쌓인 데이터의 갯수
        int ix = 0;					//초기행
        int iy = 0;					//초기열
        int ans = 0;				//정답갯수 카운트
        
        MyStack s = new MyStack(50) ; //스택 size
        Point p = new Point(ix,iy); //초기포인트

//    	d[ix][iy] = 1;
//    	s.push(p);
//    	count++;
    	
        while (count < d.length) {
        	
        	d[ix][iy] = 1;
        	System.out.println("첫while문의 ix : "+ ix);
        	System.out.println("첫while문의 iy : "+ iy);
        	System.out.println("첫while문의 d[ix][iy]" + d[ix][iy]);

        	s.push(p);
        	System.out.println("point"+p);
        	count++;
    		
        	//0,2에 1을 두고
        	while (ix < d.length) {

        		iy = 0; //0,0부터 확인

	    		while (iy < d.length) {
	    			
	    			iy = nextMove(d,ix,iy);
	    			
	    			if (iy != -1) {
	    				
	    				Point pc = new Point (ix, iy);
	    				d[ix][iy] = 1;
	    				s.push(pc);
	    				count++;
	    				break;
	    			}    			
	    			else {
	    				
						Point px = null;
						try {
							px = s.pop();
						} catch (EmptyMyStackException e) {
							e.printStackTrace();
						}
						
	    				ix = px.getX();
	    				iy = px.getY();
	    				d[ix][iy] = 0;
	    				count--;
	    				iy++;
	    			}

	    		}
	    		
//	        	System.out.println("배열순서");
//		        printQ(d);
		        ix++;
//	        	System.out.println("완료전 ix :"+ix);
//	        	System.out.println("완료전 iy :"+iy);
//	        	System.out.println("완료전 count"+count);
	        	
        	}
        	      		
        	System.out.println(++ans + "번째 정답");
	        printQ(d);
			System.out.println("체스판초기화");
	        clearQ(d);
	        printQ(d);
        	ix=0;
        	p.setX(ix);
        	p.setY(iy);
        	count=0;
        	System.out.println("clear후 ix :"+ix);
        	System.out.println("clear후 iy :"+iy);
        	System.out.println("clear후 count"+count);
        	System.out.println("pointx :"+p.getX());
        	System.out.println("pointy :"+p.getY());
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
		for(int i=x, j=y; 0<=i && 3>=i && 0<=j && 3>=j; i--, j++) {
			if(d[i][j] == 1) return false;
		}
		
		//현재 위치(x, y)에서 왼쪽 아랫방향 체크
		for(int i=x, j=y; 0<=i && 0<=j && 3>=i && 3>=j; i++, j--) {
			if(d[i][j] == 1) return false;
		}
		
		return true;
		
	}
	
	//왼쪽 대각선 체크 x++, y++ or x--, y-- where 0<= x,y <= 7
	static boolean checkDiagSE(int [][] d, int x, int y) {
		
		//현재 위치(x, y)에서 오른쪽 아랫방향 체크
		for(int i=x, j=y; 0<=i && 3>=i && 0<=j && 3>=j; i++, j++) {
			if(d[i][j] == 1) return false;
		}
		
		//현재 위치(x, y)에서 왼쪽 아랫방향 체크
		for(int i=x, j=y; 0<=i && 3>=i && 0<=j && 3>=j; i--, j--) {
			if(d[i][j] == 1) return false;
		}
		
		return true;
	}
 
	//currentRow에 대한 다음 배치 가능한 모든 column을 조사하고 move[]에 1로 설정
	static int nextMove(int [][]d, int cx, int y) {
		
		while(y < d[0].length) {
			
			if (checkMove(d,cx,y))	return y;	
			y++;
		}
		return -1;			//해당 coloumn에 만족하는 자리가 없다는 뜻
	}
	
	public static void main(String[] args) {
		
		//8*8 배열 선언
		int [][] data = new int [4][4];
		
		//8*8 배열 0으로 초기화
		for (int i = 0; i < data.length ; i++) {
			for (int j=0; j < data[0].length; j++) {
				data[i][j] = 0;
			}
		}
		
		SolveQueen(data);
		
//		for (int i=0; i< data.length; i++) {
//			for (int j=0; j<data[0].length; j++) {
//				System.out.print(" " + data[i][j]);
//			}
//			System.out.println();
//		}
	}
}
