package Lec05_EightQueen_Problem;

class QPoint {
	
	private int x;
	private int y;
	
	public QPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
}

class MyStack {
	
	private int top; 		// 스택 포인터
	private QPoint data [];	// 스택용 배열
	
	public MyStack() {
		top = 0;
		data = new QPoint[100];
	}
	
	public void push (QPoint p) {
		data[top++] = p;
	}
	
	public QPoint pop() {	
		return data[--top];
	}
	
	public boolean isEmpty() {
		if(top == 0) return true;
		else return false;
	}
}

public class EightQueenStudy {

    public static boolean checkMove(int x, int y, int[][] array) {
        if(!(checkRow(x, array))) return false;
        if(!(checkCol(y, array))) return false;
        if(!(checkDiagSW(x, y, array))) return false;
        if(!(checkDiagSE(x, y, array))) return false;

        return true;
     }
	
    //오른쪽대각선
	private static boolean checkDiagSE(int x, int y, int[][] array) {
        int dx = x;
        int dy = y;
        while (dx >= 0 && dx < array[y].length && dy >= 0 && dy < array[x].length) {
            if(array[dx][dy] == 1)
                return false;
            dx++;
            dy--;
        }
        dx = x;
        dy = y;
        while (dx >= 0 && dx < array[y].length && dy >= 0 && dy < array[x].length) {
            if(array[dx][dy] == 1)
                return false;
            dx--;
            dy++;
        }
        return true;

    }

	//왼쪽대각선
    private static boolean checkDiagSW(int x, int y, int[][] array) {
        int dx = x;
        int dy = y;
        while (dx >= 0 && dx < array[y].length && dy >= 0 && dy < array[x].length) {
            if(array[dx][dy] == 1)
                return false;
            dx++;
            dy++;
        }
        dx = x;
        dy = y;
        while (dx >= 0 && dx < array[y].length && dy >= 0 && dy < array[x].length) {
            if(array[dx][dy] == 1)
                return false;
            dx--;
            dy--;
        }
        return true;
    }

    private static boolean checkCol(int y, int[][] array) {
        for (int i = 0; i < array[y].length; i++) {
            if (array[i][y] == 1)
                return false;
        }
        return true;
    }

    private static boolean checkRow(int x, int[][] array) {
        for (int i = 0; i < array[x].length; i++) {
            if (array[x][i] == 1)
                return false;
        }
        return true;
    }
    
    static int cnt = 0; //정답갯수 사용
    
    static void printQ(int[][] array) {
    	
    	System.out.println(++cnt + "번째 답");
    	for(int i=0; i<array.length; i++) {
    		for(int j=0; j<array[0].length; j++) {
    			System.out.print(array[i][j] + " ");
    		}
    		System.out.println();
    	}
    	System.out.println("=========");
    }
	
	static void solveQueen (int row, int col, int[][] array) {
		
		MyStack s = new MyStack();
		QPoint p = new QPoint (0,0);
		
		int x = p.getX(); int y = p.getY();
//		int flag = 0; //2중루프 빠져나오려고 사용
		
		while (true) {
			while (x < row) {
				while (y < col) {
					
					if (checkMove(x,y,array)) {
						array[x][y] = 1;
						s.push(new QPoint(x,y));
						y = 0;
						break;
					}
					y++;
				}
				x++;
				if(y >= col ) {
					//스택에 값이 있으면 진행
					if(!s.isEmpty()) {	
						p = s.pop();
						x = p.getX(); y = p.getY();
						array[x][y] = 0;
						y++;
					} 
					else {
//						flag = 1;
						break;
					}
				}
			}
			
//			if(flag==1) break; 
			if(s.isEmpty()) break;
				
			printQ(array);
			
			//출력 후 pop
			p = s.pop();
			x = p.getX(); y = p.getY();
			array[x][y] = 0;
			y++;
		}
	}
	
	public static void main(String[] args) {
		
		int row = 8;
		int col = 8;
		int [][] array = new int [row][col];
		
		for(int i=0; i<row; i++) {
			for(int j=0; j<col; j++) {
				array[i][j]=0;
			}
		}
		
		solveQueen(row, col, array);

	}

}
