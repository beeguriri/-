package Lec05;

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


public class MyStack {
	
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
	
	//--- 스택에서 데이터를 팝(정상에 있는 데이터를 꺼냄) ---//
	public Point pop() throws EmptyMyStackException  {
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
