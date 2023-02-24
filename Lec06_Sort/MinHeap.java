package Lec06_Sort;

/* Heap
 * 배열을 사용하며, i=1부터 사용
 * parent 노드에서 child 노드 : 2*i (좌), 2*i + 1 (우)
 * child 노드에서 parent 노드 : i/2
 * */  

interface MinPQ {	//interface정의
	
	public void Insert(Element x);

	public Element DeleteMin(Element x);
}

public class MinHeap implements MinPQ {	//interface구현
	
	final int heapSize = 1000;
	
	private Element[] heap;
	private int n; // current size of MinHeap
	private int MaxSize; // Maximum allowable size of MinHeap
	
	//생성자
	public MinHeap(int sz) {
		MaxSize = sz;
		n = 0;
		heap = new Element[MaxSize + 1]; // Don't want to use heap[0]
	}
	
	//출력
	public void display() {
		int i;
		for (i = 1; i <= n; i++)
			System.out.print("  (" + i + ", " + heap[i].key + ") ");
		System.out.println();
	}
	
	//Insert 함수 구현
	//배열 제일 마지막에 넣은 후 parent < child 만족하는 포지션으로 이동
	//root에는 제일 작은 값이 와야함!
	public void Insert(Element x) {
		
		int i;
		
		//n : 현재 힙에 저장된 갯수
		if(n == MaxSize) {
			HeapFull();
			return;
		}
		
		//배열 제일 마지막에 넣어놓은 후
		n++;
		
		//트리 제일 마지막에서부터 비교 해가며 올라감
		for(i = n; i >= 1;) {	
			
			//i==1 : 루트위치
			if(i==1) break;
			
			//부모의 값이 자식의 값이 보다 작으면 break
			if(heap[i/2].key <= x.key)	break;
			
			//부모의 값이 자식의 값보다 크면, 부모와 자식의 값을 바꿔줌
			heap[i] = heap[i/2];
			i /= 2;
		}
		
		heap[i] = x;
	}
	
	//Delete 함수 구현
	//root 제거 후 배열 제일 마지막 요소가 root로 이동 후
	//parent < child 만족하는 포지션으로 이동
	public Element DeleteMin(Element x) {
		
		int i, j;
		
		//저장된 갯수가 없으면 elm = 0으로 초기화 
		if(n == 0) {
			HeapEmpty();
			Element elm = new Element(0);
			return elm;
		}
		
		//x에 heap root에 있는 값 (최소값)을 넣고
		//k에 heap 제일 마지막 노드에 있는 값을 넣어줌
		x = heap[1];
		Element k = heap[n];
		n--;
		
		for (i=1, j=2; j<=n;) {
			
			//child노드의 왼쪽과 오른쪽 비교해서 작은쪽으로 내려감
			if(j < n)
				if(heap[j].key > heap[j+1].key)		j++;
			
			//k값이 자식노드보다 작거나 같으면 break (현위치 유지)
			if(k.key <= heap[j].key)	break;
			
			//트리 위에서부터 비교 해가며 내려감
			heap[i] = heap[j];
			i = j;
			j *= 2;
		}
		
		heap[i] = k;		
		return x;
	}
	
	private void HeapEmpty() {
		System.out.println("Heap Empty");
	}

	private void HeapFull() {
		System.out.println("Heap Full");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

















