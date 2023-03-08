package Lec10_Hash;

import java.util.Comparator;
//hash node가 student 객체일 때를 구현하는 과제
//체인법에 의한 해시
import java.util.Scanner;

class SimpleObject2 {

	String sno; // 회원번호
	String sname; // 이름

	public SimpleObject2(String sno, String sname) {
		this.sno = sno;
		this.sname = sname;
	}
	
	//일단 임시로 만들어줬음
	public SimpleObject2(SimpleObject2 data) {
		this.sno = data.sno;
		this.sname = data.sname;
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

class ChainHash2 {
	
	//--- 해시를 구성하는 노드 ---//
	class Node2 {
	   private SimpleObject2 data;                 // 키값
	   private Node2 next;        				// 뒤쪽 포인터(뒤쪽 노드에 대한 참조)

	   //--- 생성자(constructor) ---//
	   public Node2(SimpleObject2 s) {
	       this.data  = new SimpleObject2(s);
	       this.next = null;
	   }
	   Node2(SimpleObject2 s, Node2 p) {
	       this.data  = s;
	       this.next = p;

	   }
	   Node2() {
	       this.data  = null;
	       this.next = null;
	   }
	   
	   //--- 키값을 반환 ---// (회원번호를 키로 씀)
	   Integer getKey(SimpleObject2 data) {
		   int num = Integer.parseInt(data.sno);
	       return num;
	   }

//	   //--- 키의 해시값을 반환 ---//
//	   public int hashCode(SimpleObject2 data) {
//	   	int hash = 11;
//	   	hash = 31* hash * getKey(data);
//	   	hash = hash * hash;
//	       return hash;
//	   }
	}

	private int    size;              // 해시 테이블의 크기
	private Node2[] table;        // 해시 테이블

	//--- 생성자(constructor) ---//
	public ChainHash2(int capacity) {
	   try {
	       table = new Node2[capacity];
	       this.size = capacity;
	   } catch (OutOfMemoryError e) {        // 테이블을 생성할 수 없음
	       this.size = 0;
	   }
	}

	//--- 해시값을 구함 ---//
	public int hashValue(SimpleObject2 key) {
		 int num = Integer.parseInt(key.sno);
	     return num % 10;
	}

	//--- 키값이 key인 요소를 검색(데이터를 반환) ---//
	public int search(SimpleObject2 data) {
	   int hash = hashValue(data);            // 검색할 데이터의 해시값
	   Node2 p = table[hash];            // 선택 노드

	   while (p != null) {
	       if (String.valueOf(p.getKey(data)).equals(p.data.sno)) {
	    	   System.out.printf("검색성공 (%s) %s", p.data.sno, p.data.sname);
	    	   return p.getKey(data);                // 검색 성공
	       }
	       p = p.next;                             // 다음 노드를 선택
	   }
	   return 0;                                // 검색 실패
	}

	//--- 키값이 key인 데이터를 data의 요소로 추가 ---//
	public int add(SimpleObject2 data) {
	   int hash = hashValue(data);            // 추가할 데이터의 해시값
	   Node2 p = table[hash];            	  // 선택 노드	
	   
	   //add 함수 구현
	   while(p!=null) {

//		   System.out.println("while문안");
//		   System.out.println("p.getKey(st) : " + p.getKey(st));
//		   System.out.println("p.data.sno : " + p.data.sno);
//		   System.out.println("st.sno : " + st.sno);
		   
		   //키값 중복 체크
		   if(String.valueOf(p.getKey(data)).equals(p.data.sno)) {
			   System.out.println("중복된 키값 존재. 데이터 입력 불가.");
			   return 1;
		   } 
		   p = p.next;
	   }
	   
	   //현재는 입력하는 값이 무조건 제일 앞에 위치함
	   Node2 temp = new Node2(data, table[hash]);
	   table[hash] = temp;
	   return 0;
	   
	   //입력하는 값 Comparator를 이용하여 비교 -> 순서대로 저장하도록 구현하려면
	   //아ㅏㅏㅏㅏㅏ...아.....
	   
	}

	//--- 키값이 key인 요소를 삭제 ---//
	public int remove(SimpleObject2 data) {
	   int hash = hashValue(data);       // 삭제할 데이터의 해시값
	   Node2 p = table[hash];            // 선택 노드
	   Node2 pp = null;                  // 바로 앞의 선택 노드
	   //구현실습 
	   while(p!=null) {
		   
		   // 해당하는 key값 찾으면 링크 끊어줌
		   if(String.valueOf(p.getKey(data)).equals(p.data.sno)) {
			   if(pp==null)
				   table[hash] = p.next;
			   else 
				   pp.next = p.next;
			   
	    	   System.out.printf("데이터삭제 (%s) %s", p.data.sno, p.data.sname);
			   return 0;
		   }
		   pp = p;
		   p = p.next;
	   }
	   
	   return 1;                             // 찾는 키값이 없음
	}

	//--- 해시 테이블을 덤프(dump) ---//
	public void dump() {
	   for (int i = 0; i < size; i++) {
	       Node2 p = table[i];
	       System.out.printf("%02d  ", i);
	       while (p != null) {
	           System.out.printf("→ %s ", p.data.sno);
	           p = p.next;
	       }
	       System.out.println();
	   }
	}
}


public class ChainHash_SimpleObject {

	static Scanner stdIn = new Scanner(System.in);
	
	public static void main(String[] args) {
		ChainHash2 hash = new ChainHash2(11);
		SimpleObject2 data;
		int select = 0;
		final int count = 3;
		while (select != 6) {
			System.out.println();
			System.out.println(
					"SimpleChainHash. Select 1:Add, 2. Delete, 3:Search, 4. PrintDump, 5. Quit =>");

			select = stdIn.nextInt();
			switch (select) {
			case 1:
				SimpleObject2[] input = new SimpleObject2[3];
				String sno = null;
				String sname = null;
				for (int ix = 0; ix < count; ix++) {

	                 System.out.println("입력 데이터(sno, sname):: ");

	                 System.out.print("번호: ");
	                 sno = stdIn.next();

	                 System.out.print("이름: ");
	                 sname = stdIn.next();
//	                 System.out.print("sno =  " + sno);
	                 data = new SimpleObject2(sno, sname);
	                 input[ix] = data;
	                 hash.add(input[ix]);
				
					System.out.print(" " + input[ix]);
				}
				break;
			case 2:
				System.out.println("Delete Value:: ");
				String val = stdIn.next();
				hash.remove(new SimpleObject2(val, val));
				System.out.println();				
				break;
			case 3:
				System.out.println("Search Value:: ");
				String val1 = stdIn.next();
				hash.search(new SimpleObject2(val1, val1));
				System.out.println();
				break;
			case 4:
				hash.dump();
				break;
			case 5:
				System.out.println("Quit");
				break;

			default:
				System.out.println("WRONG INPUT  ");
				System.out.println("Re-Enter");
				break;
			}
		}
	}

}
