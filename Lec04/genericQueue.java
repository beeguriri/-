package Lec04;

//Point라는 클래스에 대하여 사용하게 만들거임 (스택에서 만들어놈)
//Int형 데이터는 책에 있음

public class genericQueue {

    private Point [] data;            // 큐용 배열
    private int capacity;         // 큐의 크기
    private int front;            // 맨 처음 요소 커서
    private int rear;             // 맨 끝 요소 커서
    private int num;              // 현재 데이터 개수 (num = rear - front + 1)

    //--- 실행시 예외: 큐가 비어있음 ---//
    public class EmptygenericQueueException extends RuntimeException {
		private static final long serialVersionUID = 1L;
        public EmptygenericQueueException() { }
    }

    //--- 실행시 예외: 큐가 가득 찼음 ---//
    public class OverflowgenericQueueException extends RuntimeException {
		private static final long serialVersionUID = 1L;
        public OverflowgenericQueueException() { }
    }

    //--- 생성자(constructor) ---//
    public genericQueue(int capacity) {
        num = front = rear = 0;
        this.capacity = capacity;
        try {
        	data = new Point [capacity];          // 큐 본체용 배열을 생성
        } catch (OutOfMemoryError e) {        // 생성할 수 없음
            capacity = 0;
        }
    }    

    //--- 큐에 데이터를 푸쉬 ---//
    public Point push(Point x) throws OverflowgenericQueueException {
    	  	
        if (num >= capacity)
            throw new OverflowgenericQueueException();            // 큐가 가득 찼음
        num++;
        if (rear == capacity)
            rear = 0;
        return data[rear++] = x;								//큐는 데이터를 앞에서부터 뒤로 넣어주고
    }    

    //--- 큐에서 데이터를 팝 ---//
    public Point pop() throws EmptygenericQueueException {
        if (num <= 0)
            throw new EmptygenericQueueException();            // 큐가 비어있음
        num--;
        if (front == capacity)
            front = 0;
        return data[front++];									//데이터를 뺄때는 제일 앞에있는것부터 뺀다!
    }

    //--- 큐에서 데이터를 피크(프런트 데이터를 들여다봄) ---//
    public Point peek() throws EmptygenericQueueException {
        if (num <= 0)
            throw new EmptygenericQueueException();            // 큐가 비어있음
        return data[front];
    }

    //--- 큐를 비움 ---//
    public void clear() {
       front = rear = 0;
    }

    //--- 큐에서 x를 검색하여 인덱스(찾지 못하면 –1)를 반환 ---//
    public int indexOf(Point x) {
        for (int i = 0; i < num; i++) {
            int idx = (i + front) % capacity;
            if (data[idx] == x)                // 검색 성공
                return idx;
        }
        return -1;                            // 검색 실패
    }

    //--- 큐의 크기를 반환 ---//
    public int getCapacity() {
        return capacity;
    }

    //--- 큐에 쌓여 있는 데이터 개수를 반환 ---//
    public int size() {
        return num;
    }

    //--- 큐가 비어있는가? ---//
    public boolean isEmpty() {
        return num <= 0;
    }

    //--- 큐가 가득 찼는가? ---//
    public boolean isFull() {
        return num >= capacity;
    }

    //--- 큐 안의 모든 데이터를 프런트 → 리어 순으로 출력 ---//
    public void dump() {
        if (num <= 0)
            System.out.println("큐가 비어있습니다.");
        else {
            for (int i = 0; i < num; i++)
                System.out.print(data[(i + front) % capacity] + " ");
            System.out.println();
        }
    }
	
	
}
