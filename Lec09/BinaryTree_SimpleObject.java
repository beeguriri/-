package Lec09;

import java.util.Comparator;
import java.util.Scanner;

class SimpleObject2 {

	String sno; // 회원번호
	String sname; // 이름

	public SimpleObject2(String sno, String sname) {
		this.sno = sno;
		this.sname = sname;
	}

	// --- 문자열 표현을 반환 ---//
	public String toString() {
		return "(" + sno + ") " + sname;
	}

	// --- 회원번호로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject2> NO_ORDER = new NoOrderComparator();

	private static class NoOrderComparator implements Comparator<SimpleObject2> {
		public int compare(SimpleObject2 d1, SimpleObject2 d2) {
			return (d1.sno.compareTo(d2.sno) > 0) ? 1 : ((d1.sno.compareTo(d2.sno) < 0)) ? -1 : 0;
		}
	}

	// --- 이름으로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject2> NAME_ORDER = new NameOrderComparator();

	private static class NameOrderComparator implements Comparator<SimpleObject2> {
		public int compare(SimpleObject2 d1, SimpleObject2 d2) {
			return (d1.sname.compareTo(d2.sname) > 0) ? 1 : ((d1.sname.compareTo(d2.sname) < 0)) ? -1 : 0;
		}
	}
}

//정수를 저정하는 이진트리 만들기 실습
class TreeNode4 {
	TreeNode4 LeftChild;
	SimpleObject2 data;
	TreeNode4 RightChild;

	public TreeNode4() {
		LeftChild = RightChild = null;
	}

	TreeNode4(SimpleObject2 so) {
		data = so;
		LeftChild = RightChild = null;
	}
}

class Tree4 {
	TreeNode4 root;

	Tree4() {
		root = null;
	}

	TreeNode4 inorderSucc(TreeNode4 current) {
		TreeNode4 temp = current.RightChild;
		if (current.RightChild != null)
			while (temp.LeftChild != null) {
				temp = temp.LeftChild;
			}
		return temp;
	}

	TreeNode4 findParent(TreeNode4 current, Comparator<? super SimpleObject2> c) {
		TreeNode4 p = root, temp = null;

		return null;
	}

	boolean isLeafNode(TreeNode4 current) {
		if (current.LeftChild == null && current.RightChild == null)
			return true;
		else
			return false;
	}

	void inorder() {
		inorder(root);
	}

	void preorder() {
		preorder(root);
	}

	void postorder() {
		postorder(root);
	}

	void inorder(TreeNode4 CurrentNode) {
		if (CurrentNode != null) {
			inorder(CurrentNode.LeftChild);
			System.out.print(" " + CurrentNode.data);
			inorder(CurrentNode.RightChild);
		}
	}

	void preorder(TreeNode4 CurrentNode) {
		if (CurrentNode != null) {
			System.out.print(CurrentNode.data + " ");
			preorder(CurrentNode.LeftChild);
			preorder(CurrentNode.RightChild);
		}
	}

	void postorder(TreeNode4 CurrentNode) {
		if (CurrentNode != null) {
			postorder(CurrentNode.LeftChild);
			postorder(CurrentNode.RightChild);
			System.out.print(CurrentNode.data + " ");
		}
	}

	public boolean add(SimpleObject2 obj, Comparator<? super SimpleObject2> c) {
		TreeNode4 p = root;
		TreeNode4 q = null;

		if(p==null)	root = new TreeNode4(obj);
		while(p!=null) {
			
			if(c.compare(p.data, obj)>0)	{
				q = p;
				p = p.LeftChild;
			} else if (c.compare(p.data, obj)<0) {
				q=p;
				p=p.RightChild;
			}
			else return false;
		}
		
		if(q!=null && p==null) {
			
			if(c.compare(q.data, obj)>0)	q.LeftChild = new TreeNode4(obj);
			else							q.RightChild = new TreeNode4(obj);
		}
		
		return true;
	}

	public boolean delete(SimpleObject2 obj, Comparator<? super SimpleObject2> c) {
		TreeNode4 p = root, q = null;
		
		//데이터 탐색 : pointer 이동
		while(p != null && c.compare(p.data, obj)!=0) {
			q = p;
			if(c.compare(p.data, obj) >0)	p = p.LeftChild;
			else	p = p.RightChild;
		}
		
		//찾는 데이터 없음
		if(p==null)	return false;
		
		//삭제할 노드가 리프노드
		if(p.LeftChild==null && p.RightChild ==null) {
			
			if (p!=root) {
				if(q.LeftChild == p)	q.LeftChild = null;
				else					q.RightChild = null;
			}	else root = null;
		} 
		//삭제할 노드가 자식노드 두개
		else if(p.LeftChild != null && p.RightChild != null) {
			
			TreeNode4 temp = inorderSucc(p);
			SimpleObject2 tempData = temp.data;
			
			//inorderSucc 노드 삭제하고, p의 위치에 데이터 대체
			delete(tempData, c);
			p.data = tempData;	
		}
		//자식노드 한개
		else {
			
			//루트에 자식노드 하나만 있을때 루트삭제
			if (p!=root) {
				if(q.LeftChild==p) {
					if(p.LeftChild!=null)	q.LeftChild = p.LeftChild;
					else					q.LeftChild = p.RightChild;
				} else {
					if(p.LeftChild!=null)	q.RightChild = p.LeftChild;
					else					q.RightChild = p.RightChild;
				}
			} else {
				
				TreeNode4 temp = inorderSucc(p);
				SimpleObject2 tempData = temp.data;
				
				//inorderSucc 노드 삭제하고, p의 위치에 데이터 대체
				delete(tempData, c);
				p.data = tempData;	
			}
		}
		return true;
	}

	boolean search(SimpleObject2 obj, Comparator<? super SimpleObject2> c) {
		TreeNode4 p = root, q = null;
		
		if(p==null) {
			System.out.println("BinaryTree가 비었습니다.");
			return false;
		}
		
		while(p!=null) {
			
			if (c.compare(p.data, obj)==0) return true;
			else {
				if(c.compare(p.data, obj)>0) {
					q = p;
					p = p.LeftChild;
				} else {
					q = p;
					p = p.RightChild;
				}
			}
		}

		return false;
	}
}

public class BinaryTree_SimpleObject {

	enum Menu {
		Add("삽입"), Delete("삭제"), Search("검색"), InorderPrint("정렬인쇄"), Exit("종료");

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
		Scanner stdIn = new Scanner(System.in);
		int key;
		do {
			System.out.println();
			for (Menu m : Menu.values())
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
			System.out.print(" : ");
			key = stdIn.nextInt();
		} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());

		return Menu.MenuAt(key);
	}
	
	public static void main(String[] args) {
		Scanner sc2 = new Scanner(System.in);
		Tree4 t = new Tree4();
		Menu menu; // 메뉴
		String sno1, sname1;
		SimpleObject2 so;
		int count = 0;
		int num;
		boolean result;
		do {
			switch (menu = SelectMenu()) {
			case Add: // 머리노드 삽입
				SimpleObject2[] sox = { new SimpleObject2("33", "ee"), new SimpleObject2("55", "tt"),
						new SimpleObject2("22", "ww"), new SimpleObject2("66", "yy"), new SimpleObject2("21", "wq") };
				for (SimpleObject2 soz : sox)
					t.add(soz, SimpleObject2.NO_ORDER);
				break;
			case Delete: // 머리 노드 삭제
				System.out.println(" 삭제할 회원번호: ");
				sno1 = sc2.next();
//				System.out.println(" 삭제할 회원이름: ");
//				sname1 = sc2.next();
				so = new SimpleObject2(sno1, "");
				result = t.delete(so, SimpleObject2.NO_ORDER);
				
				if(result == true)
					System.out.println("데이터를 삭제하였습니다.");
				else System.out.println("삭제할 데이터가 없습니다.");
				
				break;
			case Search: // 회원 번호 검색
				System.out.println(" 검색할 회원번호: ");
				sno1 = sc2.next();
//				System.out.println(" 검색할 회원이름: ");
//				sname1 = sc2.next();
				so = new SimpleObject2(sno1, "");
				result = t.search(so, SimpleObject2.NO_ORDER);
				if (result == false)
					System.out.println("검색 값 = " + so + "데이터가 없습니다.");
				else
					System.out.println("검색 값 = " + so + "데이터가 존재합니다.");
				break;

			case InorderPrint: // 전체 노드를 키값의 오름차순으로 표시
				t.inorder();
				break;
			case Exit:
				break;
			}
		} while (menu != Menu.Exit);
	}

}
