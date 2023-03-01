package Lec08;

import java.util.Comparator;
import java.util.Scanner;

class Node2 {
	SimpleObject data; // 데이터
	Node2 llink; // 좌측포인터(앞쪽 노드에 대한 참조)
	Node2 rlink; // 우측포인터(뒤쪽 노드에 대한 참조)

	// --- 생성자(constructor) ---//
	Node2(SimpleObject so) {
		this.data = so;
		llink = rlink = this;
	}
	Node2() {
		this.data = null;
		llink = rlink = this;
	}
	Node2(String sno, String sname) {
		data = new SimpleObject(sno, sname);
		llink = rlink = this;
	}
	public int compareNode(Node2 n2) {
		SimpleObject so1 = this.data;
		if (SimpleObject.NO_ORDER.compare(so1, n2.data) < 0) return -1;
		else if (SimpleObject.NO_ORDER.compare(so1, n2.data) > 0)return 1;
		else return 0;
	}
}

class DoubledLinkedList2 {
	private Node2 first; // 머리 포인터(참조하는 곳은 더미노드)

// --- 생성자(constructor) ---//
	public DoubledLinkedList2() {
		first = new Node2(); // dummy(first) 노드를 생성
		
	}

// --- 리스트가 비어있는가? ---//
	public boolean isEmpty() {
		return first.rlink == first;
	}

// --- 노드를 검색 (번호로 검색) ---//
	public SimpleObject searchNo(SimpleObject obj, Comparator<? super SimpleObject> c) {
		Node2 ptr = first.rlink; // 현재 스캔 중인 노드

		while(ptr.data != null) {
			
			if(c.compare(ptr.data, obj) == 0)	return ptr.data;
			
			ptr = ptr.rlink;
		}
		
		return null;
	}
	
// --- 노드를 검색 (이름으로 검색) ---//
		public SimpleObject searchName(SimpleObject obj, Comparator<? super SimpleObject> c) {
			Node2 ptr = first.rlink; // 현재 스캔 중인 노드

			while(ptr.data != null) {
				
				if(c.compare(ptr.data, obj) == 0)	return ptr.data;
				
				ptr = ptr.rlink;
			}
			
			return null;
		}

// --- 전체 노드 표시 ---//
	public void show() {
		
		Node2 ptr = first;
		
		if(ptr.rlink.data == null)	System.out.println("출력할 데이터가 없습니다.");
		
		while(ptr.rlink != first) {
			System.out.println(ptr.rlink.data);
			ptr = ptr.rlink;
			
			if(ptr.rlink.data == null)	break;
		}
	}

// --- 올림차순으로 정렬이 되도록 insert ---//
	public void add(SimpleObject obj, Comparator<? super SimpleObject> c) {
		Node2 temp = new Node2(obj);
		Node2 ptr = first;
		
		//DoubledLinkedList2 생성자 호출 시 first = new Node2();		
		// 비어있는 리스트에 처음 삽입
		if(ptr.rlink.data == null) {
			
			//ptr.rlink == first (?)
			
			ptr.rlink = temp;
			temp.llink = ptr;
			
			ptr.llink = temp;
			temp.rlink = ptr;		
			
		} else {
		
			while(ptr.rlink != first) {
				
				if(c.compare(ptr.rlink.data, obj)>0) {
					
					//데이터 삽입
					//원래 ptr 오른쪽 노드에 있던걸 처리해줘야 순서 안꼬임
					ptr.rlink.llink = temp;
					temp.rlink = ptr.rlink;

					ptr.rlink = temp;
					temp.llink = ptr;
					
					break;
					
				} else 	ptr = ptr.rlink;	//ptr 위치 증가
			}
			
			//제일 마지막에 노드 추가할 경우
			if(ptr.rlink == first) {

				ptr.rlink = temp;
				temp.llink = ptr;
				
				temp.rlink = first;
				first.llink = temp;
			}
		}
	}

// --- list에 삭제할 데이터가 있으면 해당 노드를 삭제 ---//
	public SimpleObject delete(SimpleObject obj, Comparator<? super SimpleObject> c) {

		Node2 ptr = first;
		
		if(ptr.rlink == first)	System.out.println("삭제할 데이터가 없습니다.");
		
		while (ptr.rlink != first) {
			
			if(c.compare(ptr.rlink.data, obj) == 0) {
				
				SimpleObject temp = ptr.rlink.data;
				
				ptr.rlink.rlink.llink = ptr;
				ptr.rlink = ptr.rlink.rlink;
				
				return temp;
				
			} else ptr = ptr.rlink;
		}
		return null;
	}
	
	//list1과 list2 병합
	public DoubledLinkedList2 merge(DoubledLinkedList2 lst2, Comparator<? super SimpleObject> c) {
		DoubledLinkedList2 lst3 = new DoubledLinkedList2();
		
		//ai : list1의 pointer, bi : list2의 pointer
		Node2 ai = this.first.rlink, bi = lst2.first.rlink;
		
		//비교 확인
		while(ai != first && bi != first) {
			
			if(c.compare(ai.data, bi.data)>0) {
				//list2 데이터 삽입 후 list2 pointer 이동
				
				lst3.add(bi.data, SimpleObject.NO_ORDER);
				bi = bi.rlink;
				
			} else if (c.compare(ai.data, bi.data) < 0) {
				//list1 데이터 삽입 후 list1 pointer 이동
				
				lst3.add(ai.data, SimpleObject.NO_ORDER);
				ai = ai.rlink;
						
			} else {
				
				//no order가 같을경우, name order로 비교
				lst3.add(ai.data, SimpleObject.NAME_ORDER);
				lst3.add(bi.data, SimpleObject.NAME_ORDER);
				ai = ai.rlink;
				bi = bi.rlink;	
				
			}
		}
		
		//남은 노드 넣기
		//비교할 값이 없으므로 nullpoint error => 비교없이 추가하는 함수 만들기
		while(ai != first) {
			lst3.mergeAdd(ai.data);
			ai = ai.rlink;
			
			if(ai.data == null)	break;
		}
		
		while (bi != first) {
			lst3.mergeAdd(bi.data);
			bi = bi.rlink;
			
			if(bi.data == null)	break;
		}
		
		return lst3;
	}
	
	//merge용 데이터 추가 (비교필요없음)
	public void mergeAdd(SimpleObject obj) {
		
		Node2 temp = new Node2(obj);
		Node2 ptr = first.llink;			//list3의 제일 마지막 노드에 넣어주므로
	
		ptr.rlink = temp;
		temp.llink = ptr;
		
		temp.rlink = first;
		first.llink = temp;
	}
	
}


public class SimpleObjectCircularDList {

	enum Menu {
		Add("List1 삽입"), Add2("List2 삽입"), Delete("List1 삭제"), Delete2("List2 삭제"), Show("인쇄"), SearchNo("번호검색"), SearchName("이름검색"), Merge("병합"), Exit("종료");

		private final String message; // 표시할 문자열

		static Menu MenuAt(int idx) { // 순서가 idx번째인 열거를 반환
			for (Menu m : Menu.values())
				if (m.ordinal() == idx)
					return m;
			return null;
		}

		Menu(String string) { // 생성자(constructor)
			message = string;
		}

		String getMessage() { // 표시할 문자열을 반환
			return message;
		}
	}

	// --- 메뉴 선택 ---//
	static Menu SelectMenu() {
		Scanner sc1 = new Scanner(System.in);
		int key;
		do {
			for (Menu m : Menu.values()) {
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
				if ((m.ordinal() % 3) == 2 && m.ordinal() != Menu.Exit.ordinal())
					System.out.println();
			}
			System.out.print(" : ");
			key = sc1.nextInt();
		} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());
		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Menu menu; // 메뉴
		System.out.println("Linked List2");
		DoubledLinkedList2 lst1 = new DoubledLinkedList2();
		DoubledLinkedList2 lst2 = new DoubledLinkedList2();
		DoubledLinkedList2 lst3 = null;

		String sno1 = null, sname1 = null;
		Scanner sc = new Scanner(System.in);
		
		do {
			switch (menu = SelectMenu()) {
			
			case Add: // 노드 삽입
				System.out.println("===== List1 Data 입력 =====");
				System.out.println(" 회원번호: ");
				sno1 = sc.next();
				System.out.println(" 회원이름: ");
				sname1 = sc.next();
				SimpleObject so = new SimpleObject(sno1, sname1);
				lst1.add(so, SimpleObject.NO_ORDER);
				break;
				
			case Add2: // 노드 삽입
				System.out.println("===== List2 Data 입력 =====");
				System.out.println(" 회원번호: ");
				sno1 = sc.next();
				System.out.println(" 회원이름: ");
				sname1 = sc.next();
				SimpleObject so1 = new SimpleObject(sno1, sname1);
				lst2.add(so1, SimpleObject.NO_ORDER);
				break;
				
			case Delete: // 노드 삭제
				System.out.println(" 회원번호: ");
				sno1 = sc.next();
				SimpleObject so2 = new SimpleObject(sno1);
				System.out.println("삭제된 데이터 : " + lst1.delete(so2, SimpleObject.NO_ORDER));
				break;
				
			case Delete2: // 노드 삭제
				System.out.println(" 회원번호: ");
				sno1 = sc.next();
				SimpleObject so3 = new SimpleObject(sno1);
				System.out.println("삭제된 데이터 : " + lst2.delete(so3, SimpleObject.NO_ORDER));
				break;
				
			case Show: // 리스트 출력
				System.out.println("===== List1 Data 출력 =====");
				lst1.show();
				System.out.println("===== List2 Data 출력 =====");
				lst2.show();
				break;
				
			case SearchNo: // 회원 번호 검색

				System.out.println(" 회원번호: ");
				sno1 = sc.next();
				
				SimpleObject so4 = new SimpleObject(sno1);
				SimpleObject result1 = lst1.searchNo(so4, SimpleObject.NO_ORDER);
				SimpleObject result2 = lst2.searchNo(so4, SimpleObject.NO_ORDER);
				
				if (result1 != null || result2 != null)	{		
					System.out.println("List1 데이터 : " + result1);
					System.out.println("List2 데이터 : " + result2);
				}
				else	System.out.println("데이터 없음");
				break;				
				
			case SearchName: // 회원 이름 검색

				System.out.println(" 회원번호: ");
				sno1 = sc.next();
				
				SimpleObject so5 = new SimpleObject(sno1);
				SimpleObject result3 = lst1.searchName(so5, SimpleObject.NAME_ORDER);
				SimpleObject result4 = lst2.searchName(so5, SimpleObject.NAME_ORDER);

				if (result3 != null || result4 != null)	{		
					System.out.println("List1 데이터 : " + result3);
					System.out.println("List2 데이터 : " + result4);
				}
				else	System.out.println("데이터 없음");
				break;			
				
			case Merge: // 병합
				
				System.out.println("===== List1과 List2 병합 =====");
				lst3 = lst1.merge(lst2, SimpleObject.NO_ORDER);
				lst3.show();
				break;	
				
			case Exit: // 꼬리 노드 삭제
				break;
			}
		} while (menu != Menu.Exit);
	}

}
