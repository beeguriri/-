package Lec08;

//단순한 linked list에서 insert, delete하는 알고리즘을 코딩: 1단계

import java.util.Scanner;

class Node {
	
	//노드는 데이터와 링크로 구성되어 있음
	int data;
	Node link;
	
	//생성자
	public Node(int element) {
		data = element;
		link = null;
	}
}

class LinkedList {
	
	Node first;
	
	public LinkedList() {
		first = null;
	}
	
	public boolean Delete(int element) { //delete the element
		
		Node p = first, q=null;
		
		if(first==null)	System.out.println("삭제할 데이터가 없습니다.");

		//first = newNode (비어있지않으면)
		while (p != null) {
						
			if(p.data==element) {
				
				if(q==null) first = p.link;
				else q.link = p.link;
				return true;	
				
			} else {
				
				//p,q이동
				q = p;
				p = p.link;
			}
		}
			
		return false;
	}
	
	public void Show() { //전체 리스트를 순서대로 출력한다.

		Node p = first, q=null;
		
		if(first==null)	System.out.println("출력할 데이터가 없습니다.");
		
		while ( p != null) {

			System.out.println(p.data);
			p = p.link;
			if (p==null)	break;	
		}
	}
	
	public void Add(int element) {//임의 값을 삽입할 때 리스트가 오름차순으로 정렬이 되도록 한다 
	
		Node newNode = new Node(element);
				
		Node p = first, q = null;	

		// 맨처음에 데이터 생성 시
		if(first==null)		first = newNode;
		else {
			
			while (p != null ) {
				
				if (p.data > element) {
					
					newNode.link = p;
					
					//제일 처음에 데이터가 삽입 될 경우
					if(q == null)	first = newNode;
					
					//그 외의 경우
					else	q.link = newNode;
					
					//입력하고나면 빠져나오기
					break;
				
				} else { //p.data <=element
					
					//p,q 이동
					q = p;
					p = p.link;
				}
			}
			
			//데이터 제일 마지막에 넣어야할때 : p=null 
			if(p == null)	q.link = newNode;
		}
	}
	
	public boolean Search(int data) { // 전체 리스트를 순서대로 출력한다.
		
		Node p = first, q=null;
		
		while ( p != null ) {
			
			if (p.data == data) {
				
				q = p;
				return true;
			}
			
			p = p.link;
		}
		
		return false;
	}
}

public class Chap8_Test_SimpleList {

	enum Menu {
		
		Add( "삽입"),
		Delete( "삭제"),
		Show( "인쇄"),
		Search( "검색"),
		Exit( "종료");

		private final String message;                // 표시할 문자열

		static Menu MenuAt(int idx) {                // 순서가 idx번째인 열거를 반환
			for (Menu m : Menu.values())
				if (m.ordinal() == idx)
					return m;
			return null;
		}

      Menu(String string) {                        // 생성자(constructor)
          message = string;
      }

      String getMessage() {                        // 표시할 문자열을 반환
          return message;
      }
	}

	//--- 메뉴 선택 ---//
	static Menu SelectMenu() {
		Scanner sc = new Scanner(System.in);
      int key;
      do {
          for (Menu m : Menu.values()) {
              System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
              if ((m.ordinal() % 3) == 2 &&
                    m.ordinal() != Menu.Exit.ordinal())
                  System.out.println();
          }
          System.out.print(" : ");
          key = sc.nextInt();
      } while (//key < Menu.Insert.ordinal() || 
                                          key > Menu.Exit.ordinal());
      return Menu.MenuAt(key);
  }

	public static void main(String[] args) {
		
		Menu menu;                                // 메뉴 
		System.out.println("Linked List");
		LinkedList l = new LinkedList();
		Scanner sc = new Scanner(System.in);
		int data = 0;
		System.out.println("inserted");
		
		l.Show();		
		
	      do {
	          switch (menu = SelectMenu()) {	             
	          
	           case Add :                           // 노드 삽입
	  	         double d = Math.random();
	  	         data = (int) (d * 50);
	  	         l.Add(data);            
	  	         System.out.println("입력데이터 : " + data);
	             break;
	             
	           case Delete :                          // 노드 삭제
	        	 System.out.println("삭제 할 데이터를 입력하세요 : ");
	          	 int num = sc.nextInt(); 
	          	 boolean result = l.Delete(num);
	          	 if (result)	System.out.println("삭제된 데이터는 " + num); 
	             break;
	             
	           case Show :                           // 노드 출력
	        	 l.Show();
	  	         break;
	                  
	           case Search :                           // 노드 검색
	  	         System.out.println("검색 할 데이터를 입력하세요 : ");
	  	         int n = sc.nextInt();
	             boolean result1 = l.Search(n);
	             if (result1 == false)
                      System.out.println("검색 값 = " + n + ": 데이터가 없습니다.");
                 else
                      System.out.println("검색 값 = " + n + " : 데이터가 존재합니다.");
                 break;
	                   
	           case Exit :                           // 꼬리 노드 삭제
	                  break;
	          }
	      } while (menu != Menu.Exit);
  }
}
