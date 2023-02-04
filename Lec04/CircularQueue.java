package Lec04;

//원형큐

public class CircularQueue {
	
    private Point [] data;        // 큐용 배열
    private int capacity;         // 큐의 크기
    private int front;            // 맨 처음 요소 커서
    private int rear;             // 맨 끝 요소 커서
    
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
    	
    	//생성자가 실행되면 초기값 세팅 = 비어있는상태
    	front = rear = -1;
        this.capacity = capacity;
        
        try {
        	data = new Point [capacity];          // 큐 본체용 배열을 생성
        } catch (OutOfMemoryError e) {        // 생성할 수 없음
            capacity = 0;
        }
    }    
    
    //--- 원형큐에 데이터를 푸쉬 : rear가 이동 ---//
    public Point push(Point x) throws OverflowCircularQueueException {
    	  	
        if (isFull())						// 큐가 가득 차면 예외처리
            throw new OverflowCircularQueueException();         
        
        else  {
        	
        	//큐에 데이터가 없으면, front를 0으로 세팅 -> 데이터 있다고 티내기..
        	if (front == -1)  {		
        	
        		front = 0;
            	//rear 증가 시키고 거기에 값 넣어주기 
        		//push 함수 첫 호출 시, rear = ( -1 + 1) % 4 = 0
                rear = (rear+1) % capacity;
                data[rear] = x;				//data[0]에 x 추가, front=0, rear = 0
        	}
        	
        	else {
            	//데이터가 있는 상태에서 push 함수 호출 시 
        		//rear 먼저 증가 시키고 거기에 값 넣어주기
        		//rear = (0+1)%4 = 1
                rear = (rear+1) % capacity;
                data[rear] = x;				//data[1]에 x 추가, front =0, rear = 1;
        	}
        	
//        	System.out.println("Front의 위치" + front);
//        	System.out.println("Rear의 위치" + rear);  
            return x;
        }		
    }       
    
    //--- 원형큐에서 데이터를 팝 : front가 이동---//
    public Point pop() throws EmptyCircularQueueException {
    	    	
        if (isEmpty())											// 큐가 비어있으면 예외처리
            throw new EmptyCircularQueueException();            
        
        else {
        	
        	Point x = data[front];
        	
        	//큐에 데이터가 하나만 있으면, pop 후 front=rear=-1 (데이터없음 티내기)
        	if(front==rear)  	front = rear = -1;

        	//데이터가 여러개 있으면, front의 데이터 pop
        	else 				front = (front+1) % capacity; 
            
//        	System.out.println("Front의 위치" + front);
//        	System.out.println("Rear의 위치" + rear);
        	
            return x;
        	
        }
    }

    //--- 큐가 비어있는가? ---//
    public boolean isEmpty() {
    	
    	//생성자 호출만 된 상태
    	if(front == -1)     	return true;	
    	
    	else                    return false;
    }

    //--- 큐가 가득 찼는가? ---//
    public boolean isFull() {
    	
    	//데이터 입력만 이루어진 상태에서 발생
    	if(front == 0 && rear==capacity-1) return true;
    	//데이터가 지워지고, 다시 입력 될때 생길 수 있는 경우
    	else if (front == rear + 1) 	   return true;
    	else   					   		   return false;
	}
    
    //--- 큐 안의 모든 데이터를 프런트 → 리어 순으로 출력 ---//
    public void dump() {
        if (isEmpty())
            System.out.println("큐가 비어있습니다.");
       
        else if(front<=rear) {
        	System.out.printf("*** :: 현재 저장된 데이터 :: %2d개\n", rear-front+1);
            for (int i = front; i <= rear; i++) {
            	System.out.print(data[i] + " ");
            }
            
            System.out.println();
        } 
        
        //데이터 지워지고, 다시 채워져서 front !=0, rear < front 
        else {
        	System.out.printf("*** :: 현재 저장된 데이터 :: %2d개\n", rear-front+1);
        	
        	//front에서부터 마지막index까지 출력하고
            for (int i = front; i <capacity; i++) {
            	System.out.print(data[i] + " ");
            }
            
            //index 0부터 다시 출력 
            //(front =2, rear = 0부터 들어있을때, 2부터 끝까지 출력 후 0부터 다시 출력)

            for(int i = 0; i <= rear; i++) {
            	System.out.print(data[i] + " ");
            }
            
            System.out.println();
            
        }
    }
}
