package Lec04;

//원형큐

public class CircularQueue {
	
    private Point [] data;        // 큐용 배열
    private int capacity;         // 큐의 크기
    private int front;            // 맨 처음 요소 커서
    private int rear;             // 맨 끝 요소 커서
    private int num;              // 현재 데이터 개수 (num = rear - front + 1)
    
    //--- 실행시 예외: 큐가 비어있음 ---//
    public class EmptyCircularQueueException extends RuntimeException {
		private static final long serialVersionUID = 1L;
        public EmptyCircularQueueException() { }
    }

    //--- 실행시 예외: 큐가 가득 찼음 ---//
    public class OverflowCircularQueueException extends RuntimeException {
		private static final long serialVersionUID = 1L;
        public OverflowCircularQueueException() { }
    }
	
    //--- 생성자(constructor) ---//
    public CircularQueue(int capacity) {
        num = 0; 
        front = rear = -1;
        this.capacity = capacity;
        try {
        	data = new Point [capacity];          // 큐 본체용 배열을 생성
        } catch (OutOfMemoryError e) {        // 생성할 수 없음
            capacity = 0;
        }
    }    
    
    //--- 원형큐에 데이터를 푸쉬 ---//
    public Point push(Point x) throws OverflowCircularQueueException {
    	  	
        if (isFull())						// 큐가 가득 차면 예외처리
            throw new OverflowCircularQueueException();         
                   													
        num++;
        rear = (rear+1) % capacity;						//rear의 위치를 옮겨줌
        data[rear++] = x;
        
        if (rear == capacity)
            rear = -1;
        return x;								
    }       
    
    //--- 원형큐에서 데이터를 팝 ---//
    public Point pop() throws EmptyCircularQueueException {
        if (isEmpty())											// 큐가 비어있으면 예외처리
            throw new EmptyCircularQueueException();            
        
        num--;        
        front = (front+1) % capacity;								//front위치 옮겨줌
        if (front == capacity)
            front = -1;
        return data[front++];
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
                System.out.print(data[(i + front +1) % capacity] + " ");
            System.out.println();
        }
    }
    

}
