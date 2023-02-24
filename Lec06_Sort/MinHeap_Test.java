package Lec06_Sort;

import java.util.Scanner;

class Element {
	public int key;

	public int getElement() {
		return key;
	}

	public Element(int key) {
		this.key = key;
	}
}

public class MinHeap_Test {

	public static void main(String[] args) {
		
		int select = 0;
		Scanner stdIn = new Scanner(System.in);
		MinHeap heap = new MinHeap(10);			//힙 사이즈 생성자
		Element ele = null;
		final int count = 5;
	    int[] x = new int[count];

		Element deletedEle = null;

		do {
			System.out.println("Max Tree. Select: 1 insert, 2 display, 3 delete,  4 sort, 5 exit => ");
			select = stdIn.nextInt();
			switch (select) {
			
			//insert : 갯수만큼 난수 생성해서 heap 만들어짐 (count=5 이므로 난수 5개 생성)
			case 1:
			     for (int i = 0; i < count; i++) {
			         x[i] = (int)(Math.random() * 30);
						heap.Insert(new Element(x[i]));
			     }
				break;
				
			case 2:
				heap.display();
				break;
				
			//delete	
			case 3:
				deletedEle = heap.DeleteMin(ele);
				if (deletedEle != null) {
					System.out.println("deleted element: " + deletedEle.key);
				}
				System.out.println("current max heap: ");
				heap.display();
				break;
			
			//sort
			case 4:
				for (int j = 0; j < count; j++) {
					deletedEle = heap.DeleteMin(ele);
					x[j] = deletedEle.getElement();
				}
				for (int num: x)
					System.out.println(" " + num);
				
			case 5:
				return;

			}
		} while (select < 5);

		return;
	}

}
