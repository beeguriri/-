package Lec09;

import java.util.Scanner;

//정수를 저정하는 이진트리 만들기 실습
class TreeNode {
	
	TreeNode LeftChild;
	int data;
	TreeNode RightChild;
	
	public TreeNode() {
		LeftChild = RightChild = null;
	}
	
	public TreeNode(int data) {
		LeftChild = RightChild = null;
		this.data = data;
	}
}

class Tree {
	
	private TreeNode root;
	
	//생성자
	Tree() {
		root = null;
	}
	
	TreeNode inorderSucc(TreeNode current) {
		
		TreeNode temp = current.RightChild;	//현재노드의 오른쪽 탐색
		
		if (current.RightChild != null)
			while (temp.LeftChild != null)
				temp = temp.LeftChild;
		
		return temp;
	}
	
	boolean isLeafNode(TreeNode current) {
		if (current.LeftChild == null && current.RightChild == null) return true;
		else return false;
	}
	
	public void inorder() {
		inorder(root);
	}
	
	void preorder() {
		preorder(root);
	}
	
	void postorder() {
		postorder(root);
	}
	
	// 탐색순서 : TL -> x -> TR
	private void inorder(TreeNode CurrentNode) {			
		if (CurrentNode != null) {
			inorder(CurrentNode.LeftChild);	//왼쪽노드 탐색
			System.out.print(" " + CurrentNode.data);	//x노드 출력
			inorder(CurrentNode.RightChild);	//오른쪽노드 탐색
		}
	}

	// 탐색순서 : x -> TL -> TR
	void preorder(TreeNode CurrentNode) {
		if (CurrentNode != null) {
			System.out.print(CurrentNode.data + " ");
			preorder(CurrentNode.LeftChild);
			preorder(CurrentNode.RightChild);
		}
	}
	
	// 탐색순서 : TL -> TR -> x
	void postorder(TreeNode CurrentNode) {
		if (CurrentNode != null) {
			postorder(CurrentNode.LeftChild);
			postorder(CurrentNode.RightChild);
			System.out.print(CurrentNode.data + " ");
		}
	}

	// binary search tree를 만드는 입력 
	//=> A + B * C을 tree로 만드는 방법: 입력 해결하는 알고리즘 작성 방법을 설계하여 구현 
	boolean insert(int x) {

		TreeNode p = root;
		TreeNode q = null;

		//트리가 비어있을때(root == null)
		if(p==null)		root = new TreeNode(x);
		while (p!=null) {
			if(x < p.data)	{
				q=p;
				p=p.LeftChild;
			}
			else if (x>p.data) {
				q=p;
				p=p.RightChild;
			}
			else return false;	//중복데이터 입력 안함
		}
		
		if(q!=null && p==null)	{
			
			if(x < q.data)	q.LeftChild = new TreeNode(x);
			else			q.RightChild = new TreeNode(x);
		}
		
		return true;
	}
	
	boolean delete(int num) {
		TreeNode p = root, q = null;
		//int branchMode = 0; //1은 left, 2는 right
	 		
		//데이터 탐색 : pointer 이동
		while(p != null && p.data != num) {
			q = p;
			if(num < p.data)	p = p.LeftChild;
			else	p = p.RightChild;
		}
		
		//찾는 데이터 없음
		if(p==null) 			return false;
		
		//삭제할 노드가 리프노드인 경우
		if(p.LeftChild == null && p.RightChild == null) {
			
			//root만 남아서 삭제할때 null point error
			if (p!=root) {
				if(q.LeftChild == p)	q.LeftChild = null;
				else					q.RightChild = null;
			}
			else	root = null;
		}
		
		//삭제할 노드가 리프노드가 아닌경우
		//자식노드가 두개인 경우
		else if (p.LeftChild != null && p.RightChild != null) {

			//inorderSucc의 값을 삭제할 위치에 넣어주고
			TreeNode temp = inorderSucc(p);
			int tempData = temp.data;
			
			//inorderSucc의 기존노드는 삭제하고
			delete(tempData);
			
			//삭제할 노드의 데이터만 대체
			p.data = tempData;

		}
		
		//자식노드가 한개
		else {
			
			//root에 자식노드 하나만 있을때 root 삭제할경우 null point error
			if (p!=root) {
				if(q.LeftChild==p)	{
					if(p.LeftChild!=null)	q.LeftChild = p.LeftChild;
					else 					q.LeftChild = p.RightChild;
				} else {
					if(p.LeftChild!=null)	q.RightChild = p.LeftChild;
					else					q.RightChild = p.RightChild;
				}
			} else {
				
				TreeNode temp = inorderSucc(p);
				int tempData = temp.data;
				
				//inorderSucc의 기존노드는 삭제하고
				delete(tempData);
				
				//삭제할 노드의 데이터만 대체
				p.data = tempData;
				
			}

		}
	
		return true;
		
	}
	
	boolean search(int num) {

		TreeNode p = root;
		TreeNode q = null;
		
		if(p==null)	{
			System.out.println("BinaryTree가 비어있습니다.");
			return false;
		}
		
		while(p!=null) {
			
			if (p.data == num)	return true;
			else {
				if(num < p.data) {
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

public class BinaryTree_int {
	
	enum Menu {
	     Add(      "삽입"),
	     Delete(   "삭제"),
	     Search(   "검색"),
	     InorderPrint(    "표시"),
	     Exit("종료");
	     private final String message;        // 표시할 문자열
	     static Menu MenuAt(int idx) {        // 순서가 idx번째인 열거를 반환
	         for (Menu m : Menu.values())
	             if (m.ordinal() == idx)
	                 return m;
	         return null;
	     }
	     Menu(String string) {                // 생성자(constructor)
	         message = string;
	     }
	     String getMessage() {                // 표시할 문자열을 반환
	         return message;
	     }
	 }

	 //--- 메뉴 선택 ---//
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
	
		 Scanner stdIn = new Scanner(System.in);
		 Tree t = new Tree();
	     Menu menu;                                // 메뉴 
	     int count = 0;
	     int num;
	     boolean result;
	     do {
	         switch (menu = SelectMenu()) {
	          case Add :              // 노드 삽입
					System.out.println("The number of items = ");

					count = stdIn.nextInt();
					int[] input = new int[10];
					for (int ix = 0; ix < count; ix++) {
						double d = Math.random();
						input[ix] = (int) (d * 30);
					}
//					int [] input = {15, 20, 5, 17, 33};
//					int [] input = {33, 27, 5, 40, 25, 66, 35, 34};
//					count = input.length;
					for (int i = 0; i < count; i++) {
						if (t.insert(input[i]) == false)
							System.out.println("Insert Duplicated data");
					}	    
	                break;

	          case Delete :           // 노드 삭제 - 어렵다: 난이도 상
	        	    System.out.println("삭제할 데이터:: ");
	        	  	num = stdIn.nextInt();
	                result = t.delete(num);
	                if (result == true)
		                     System.out.println(" 데이터를 삭제하였습니다");
                  	else
		                      System.out.println("삭제할 데이터가 없습니다.");

                	break;

	          case Search :           // 노드 검색
	        	  	System.out.println("검색할 데이터:: ");

					num = stdIn.nextInt();
	                result = t.search(num);
	                  if (result == true)
	                     System.out.println(" 데이터 = " + num + "존재합니다.");
	                 else
	                      System.out.println("해당 데이터가 없습니다.");
	                  break;

	          case InorderPrint :            // 전체 노드를 키값의 오름차순으로 표시
	                 t.inorder();
	                 break;
	         }
	     } while (menu != Menu.Exit);

	}

}
