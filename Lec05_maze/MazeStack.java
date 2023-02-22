package Lec05_maze;

class Point {
	private int ix;
	private int iy;
	private int dir;

	public Point(int x, int y, int dir) {
		ix = x;
		iy = y;
		this.dir = dir;
	}

	public String toString() {
		return "<" + ix + ", " + iy + ", " + dir + ">";
	}

	public int getX() {
		return ix;
	}

	public int getY() {
		return iy;
	}
	
	public int getDir() {
		return dir;
	}

	public void setX(int x) {
		ix = x;
	}

	public void setY(int y) {
		iy = y;
	}
	
	public void setDir(int dir) {
		this.dir = dir;
	}
}

public class MazeStack {
    private Point[] stk; // 스택용 배열
    private int capacity; // 스택의 크기
    private int ptr; // 스택 포인터

    // --- 실행시 예외 : 스택이 비어있음 ---//
    public class EmptyMazeStackException extends RuntimeException {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public EmptyMazeStackException() {
        }
    }

    // --- 실행시 예외 : 스택이 가득 참 ---//
    public class OverflowMazeStackException extends RuntimeException {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public OverflowMazeStackException() {
        }
    }

    // --- 생성자(constructor) ---//
    public MazeStack(int maxlen) {
        ptr = 0;
        capacity = maxlen;
        try {
            stk = new Point[capacity]; // 스택 본체용 배열을 생성
        } catch (OutOfMemoryError e) { // 생성할 수 없음
            capacity = 0;
        }
    }

    // --- 스택에 x를 푸시 ---//
    public Point push(Point p) throws OverflowMazeStackException {
        if (ptr >= capacity) // 스택이 가득 참
            throw new OverflowMazeStackException();
        return stk[ptr++] = p;
    }

    // --- 팝 : 하나 꺼냄.  ---//
    public Point pop() throws EmptyMazeStackException {
        if (ptr <= 0) // 스택이 빔
            throw new EmptyMazeStackException();
        return stk[--ptr];
    }

    // --- 스택에서 데이터를 피크(peek, 정상에 있는 데이터를 들여다봄) ---//
    public Point peek() throws EmptyMazeStackException {
        if (ptr <= 0) // 스택이 빔
            throw new EmptyMazeStackException();
        return stk[ptr - 1];
    }

    // --- 스택을 비움 ---//
    public void clear() {
        ptr = 0;
    }

    // --- 스택에 쌓여있는 데이터 갯수를 반환 ---//
    public int size() {
        return ptr;
    }

    // --- 스택이 비어있는가? ---//
    public boolean isEmpty() {
        return ptr <= 0;
    }

    // --- 스택이 가득 찼는가? ---//
    public boolean isFull() {
        return ptr >= capacity;
    }

    // --- 스택 안의 모든 데이터를 바닥 → 정상 순서로 표시 ---//
    public void dump() {
		for(int i = 0; i < ptr; i++) {
			System.out.print(stk[i] + " ");
		}
		System.out.println();
    }
    
}
