package Lec08;

import java.util.Comparator;
import java.util.Scanner;

class SimpleObject {
	
	static final int NO = 1; // 번호를 읽어 들일까요?
	static final int NAME = 2; // 이름을 읽어 들일까요?

	private String no; // 회원번호
	private String name; // 이름

	// 생성자
	public SimpleObject(String no, String name) {
		
		this.no = no;
		this.name = name;
	}
	
	// 생성자 (검색용)
	public SimpleObject(String data) {
		
		this.no = data;
		this.name = data;
	}
	
	// --- 문자열 표현을 반환 ---//
	public String toString() {
		return "(" + no + ") " + name;
	}

	// --- 데이터를 읽어 들임 ---//
	void scanData(String guide, int sw) {
		System.out.println(guide + "할 데이터를 입력하세요."+ sw);
		Scanner stdIn = new Scanner(System.in);

		if ((sw & NO) == NO) { //& 는 bit 연산자임 
			System.out.print("번호: ");
			no = stdIn.next();
		}
		if ((sw & NAME) == NAME) {
			System.out.print("이름: ");
			name = stdIn.next();
		}
	}

	// --- 회원번호로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject> NO_ORDER = new NoOrderComparator();

	private static class NoOrderComparator implements Comparator<SimpleObject> {
		public int compare(SimpleObject d1, SimpleObject d2) {
			
			// no를 string으로 입력받았으나, 연산자 사용하기 위해 int로 타입변환
//			return (Integer.parseInt(d1.no) > Integer.parseInt(d2.no)) ? 1 
//					: (Integer.parseInt(d1.no) < Integer.parseInt(d2.no)) ? -1 : 0;
			
				return d1.no.compareTo(d2.no);
		}
	}

	// --- 이름으로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject> NAME_ORDER = new NameOrderComparator();

	private static class NameOrderComparator implements Comparator<SimpleObject> {
		public int compare(SimpleObject d1, SimpleObject d2) {
			return d1.name.compareTo(d2.name);
		}
	}
}

class Node1 {
	
	SimpleObject data;
	Node1 link;
	
	public Node1(SimpleObject element) {
		data = element;
		link = null;
	}
}

class LinkedList1 {
	
	Node1 first;
	
	public LinkedList1() {
		first = null;
	}
	
	public SimpleObject Delete(SimpleObject element, Comparator<? super SimpleObject> c) { //delete the element
		
		Node1 p = first, q=null;
		
		if(first==null)	System.out.println("삭제할 데이터가 없습니다.");

		//first = newNode (비어있지않으면)
		while (p != null) {
						
			if(c.compare(p.data, element)==0) {
				
				if(q==null) first = p.link;
				else q.link = p.link;
				return p.data;	
				
			} else {
				
				//p,q이동
				q = p;
				p = p.link;
			}
		}
		return null;
	}
	
	public void Show() { // 전체 리스트를 순서대로 출력한다.

		Node1 p = first;
		
		if(first==null)	System.out.println("출력할 데이터가 없습니다.");
		
		while ( p != null) {

			System.out.println(p.data);
			p = p.link;
			if (p==null)	break;	
		}
	}
	
	//임의 값을 삽입할 때 리스트가 오름차순으로 정렬이 되도록 한다
	//no순으로 정렬
	public void Add(SimpleObject element, Comparator<? super SimpleObject> c) {  
	
		Node1 newNode = new Node1(element);
		Node1 p = first, q = null;
		
		//맨 처음에 데이터 생성 시
		if (first == null)	first = newNode;
		else {
			
			while (p!=null) {
								
//				System.out.println(p.data);
				if(c.compare(p.data, element)>0) {
					
					newNode.link = p;
					
					//제일 처음에 데이터 삽입
					if (q==null)	first = newNode;
					
					//그 외의 경우
					else	q.link = newNode;
					
					break;
				} else {
					
					q = p;
					p = p.link;
				}
			}
			
			// 데이터 제일 마지막에 넣을때 (p=null)
			if(p==null)	q.link = newNode;
		}
	}
	
	// 리스트 검색 (번호로)
	public SimpleObject SearchNo(SimpleObject element, Comparator<? super SimpleObject> c) { 	
		
		Node1 p = first, q=null;
		
		while ( p != null ) {
			
			if (c.compare(p.data, element)==0) {	//검색성공
				
				q = p;
				return p.data;
			}		
			p = p.link;
		}
		
		return null;	//검색실패
		}
	
	
	// 리스트 검색 (이름으로)
	public SimpleObject SearchName(SimpleObject element, Comparator<? super SimpleObject> c) { 	
		
		Node1 p = first, q=null;
		
		while ( p != null ) {
			
			if (c.compare(p.data, element)==0) {	//검색성공
				
				q = p;
				return p.data;
			}		
			p = p.link;
		}
		
		return null;	//검색실패
		}
}

public class SimpleObjectList {

	enum Menu {
        Add( "삽입"),
        Delete( "삭제"),
        Show( "인쇄"),
        Search_No( "번호검색"),
        Search_Name( "이름검색"),
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
        } while (key < Menu.Add.ordinal() || 
                                            key > Menu.Exit.ordinal());
        return Menu.MenuAt(key);
    }

	public static void main(String[] args) {
		
		Menu menu;                                // 메뉴 
		System.out.println("Linked List1");
		LinkedList1 l = new LinkedList1();
		Scanner sc = new Scanner(System.in);
		SimpleObject data = null;
		System.out.println("inserted");	
		
	    do {
	        switch (menu = SelectMenu()) {	             
	        
	         case Add :                           // 머리노드 삽입
	         	
	        	 System.out.println("번호를 입력하세요: ");
	        	 String anum = sc.next();
	        	 System.out.println("이름을 입력하세요: ");
	        	 String aname = sc.next();
	        	 
	        	 data = new SimpleObject(anum, aname);
		         l.Add(data, SimpleObject.NO_ORDER);            
	                 break;
	                 
	         case Delete :                          // 머리 노드 삭제
	        	 System.out.println("삭제 할 번호를 입력하세요 : ");
	        	 String dnum = sc.next();
	        	 
	        	 data = new SimpleObject(dnum);
	        	 SimpleObject d = l.Delete(data, SimpleObject.NO_ORDER);
	        	 System.out.println("삭제된 데이터는 " + d);
	                break;
	                
	         case Show :                           // 꼬리 노드 삭제
	                l.Show();
	                break;
	                
	         case Search_No :                           // 회원 번호 검색
	        	 System.out.println("검색 할 번호를 입력하세요 : ");
	        	 String snum = sc.next();
	        	 
	        	 data = new SimpleObject(snum);
	        	 SimpleObject result = l.SearchNo(data, SimpleObject.NO_ORDER);
	        	 
	        	 if(result != null) System.out.println("데이터 있음 : " + result);
	        	 else System.out.println("데이터 없음");  
	             break;
	             
	         case Search_Name :                           // 회원 이름 검색
	        	 System.out.println("검색 할 이름를 입력하세요 : ");
	        	 String sname = sc.next();
	        	 
	        	 data = new SimpleObject(sname);
	        	 SimpleObject result2 = l.SearchName(data, SimpleObject.NAME_ORDER);
	        	 
	        	 if(result2 != null) System.out.println("데이터 있음 : " + result2);
	        	 else System.out.println("데이터 없음");  
	             break;
	        	 
	         case Exit :                           // 꼬리 노드 삭제
	                break;
	        }
	    } while (menu != Menu.Exit);
	}
}
