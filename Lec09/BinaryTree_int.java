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
		
		TreeNode parent = current;			//현재노드를 부모노드로 설정
		TreeNode temp = current.RightChild;	//현재노드의 오른쪽 탐색
		
		
		//if (current.RightChild != null) {
		
			//자식노드 왼쪽이 비어있으면
			//오른쪽으로 내려와서 다시 왼쪽으로 탐색
			if(temp.LeftChild == null) {
				parent.RightChild = temp.RightChild;
				
				if(parent.RightChild != null)
					parent.RightChild.parent = parent;
			}
			
			
			while (temp.LeftChild != null) {
				parent = temp;
				temp = temp.LeftChild;
			}
	//	}
				
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
		int branchMode = 0; //1은 left, 2는 right
	 
		//inorderSucc 기존위치가 리프노드인지 아닌지 판별 => isLeafNode 
		//리프노드 이면 삭제 쉬움 
		
		//삭제할 노드에 inorderSucc 넣고
		while(p!=null) {
			
			if(p.data==num)	{
				TreeNode temp = null;
				temp = inorderSucc(p);
				
				//리프노드면 삭제
				if(isLeafNode(p)) {
					
				}
				
				//리프노드 아니면?
					
				
			}
			else {
				if(num < p.data) {
					q = p;
					p = p.LeftChild;
				} else {
					q = p;
					p = p.RightChild;
				}
			}
			
			return true;
		}
	
		return false;
		
	}
	
	boolean search(int num) {

		TreeNode p = root;
		TreeNode q = null;
		
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
					//int [] input = {15, 20, 5, 17, 33};
					//int [] input = {33, 27, 5, 40, 25, 66, 35, 34};
					//count = input.length;
					for (int i = 0; i < count; i++) {
						if (t.insert(input[i]) == false)
							System.out.println("Insert Duplicated data");
					}	    
	                break;

	          case Delete :           // 노드 삭제 - 어렵다: 난이도 상
	        	    System.out.println("삭제할 데이터:: ");
	        	  	num = stdIn.nextInt();
	                t.delete(num);
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
