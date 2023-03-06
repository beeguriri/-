package Lec10;

import java.util.Comparator;
import java.util.Scanner;

class SimpleObject2 {

    static final int NO   = 1;        // 번호를 읽어 들일까요?
    static final int NAME = 2;        // 이름을 읽어 들일까요?
	
	String sno; // 회원번호
	String sname; // 이름
	Scanner stdIn = new Scanner(System.in);

	public SimpleObject2() {
//		this.sno = sno;
//		this.sname = sname;
	}
	
    //--- 데이터를 읽어 들임 ---//
    void scanData(String guide, int sw) {
        System.out.println(guide + "할 데이터를 입력하세요.");

        if ((sw & NO) == NO) {
            System.out.print("번호: ");
            sno = stdIn.next();
        }
        if ((sw & NAME) == NAME) {
            System.out.print("이름: ");
            sname = stdIn.next();
        }
    }
	
    //--- 키값 ---//
    String keyCode() {
        return sno;
    }
    
    //--- 문자열 표현을 반환 ---//
    public String toString() {
        return sname;
    }

//	// --- 문자열 표현을 반환 ---//
//	public String toString() {
//		return "(" + sno + ") " + sname;
//	}

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

//V : simpleObject Version (comparator 사용)
//K : 미사용
//add, remove 작성
//체인법에 의한 해시
public class ChainHash_SimpleObject<V> {	
	
 //--- 해시를 구성하는 노드 ---//
 class Node2<V> {
     
     private V data;                // 데이터
     private Node2<V> next;        // 뒤쪽 포인터(뒤쪽 노드에 대한 참조)

     //--- 생성자(constructor) ---//
     Node2(SimpleObject2 data, Node2<V> next) {
         this.data = (V) data;
         this.next = next;
     }

     //--- 데이터를 반환 ---//
     V getValue() {
         return data;
     }
 }

 private int    size;              // 해시 테이블의 크기
 private Node2<V>[] table;        // 해시 테이블

 //--- 생성자(constructor) ---//
 public ChainHash_SimpleObject(int capacity) {
     try {
         table = new Node2[capacity];
         this.size = capacity;
     } catch (OutOfMemoryError e) {        // 테이블을 생성할 수 없음
         this.size = 0;
     }
 }

 //--- 해시값을 구함 ---// : 해싱함수
 public int hashValue(SimpleObject2 key) {
	 int num = Integer.parseInt(key.sno);
     return num % size;
 }

 //--- 키값이 key인 요소를 검색(데이터를 반환) ---//
 public V search(SimpleObject2 data) {
     int hash = hashValue(data);            // 검색할 데이터의 해시값
     Node2<V> p = table[hash];            // 선택 노드 (포인터로 사용)

     while (p != null) {
         if (p.data.equals(data))
             return p.getValue();                // 검색 성공
         p = p.next;                             // 다음 노드를 선택
     }
     return null;                                // 검색 실패
 }

 //--- 키값이 key인 데이터를 data의 요소로 추가 ---//
 public int add(SimpleObject2 data, Comparator<? super SimpleObject2> c) {

     int hash = hashValue(data);            // 추가할 데이터의 해시값
     Node2<V> p = table[hash];            // 선택 노드 (포인터로 사용)

     while (p != null) {
         if (p.getValue().equals(hash))       
             return 1;
         p = p.next;                       // 다음 노드를 선택
     }
     Node2<V> temp = new Node2<V>(data, table[hash]);
     table[hash] = temp;                   // 노드 삽입
     return 0;
     
 }

 //--- 키값이 key인 요소를 삭제 ---//
 public int remove(V data) {

     return 1;                             // 찾는 키값이 없음
 }

 //--- 해시 테이블을 덤프(dump) ---//
 public void dump() {
     for (int i = 0; i < size; i++) {
         Node2<V> p = table[i];
         System.out.printf("%02d  ", i);
         while (p != null) {
//             System.out.printf("→ %s (%s)  ", p.getKey(), p.getValue());
             p = p.next;
         }
         System.out.println();
     }
 }
 
 static Scanner stdIn = new Scanner(System.in);

 //--- 메뉴 열거형 ---//
 enum Menu {
     ADD(      "추가"),
     REMOVE(   "삭제"),
     SEARCH(   "검색"),
     DUMP(     "표시"),
     TERMINATE("종료");

     private final String message;            // 표시할 문자열

     static Menu MenuAt(int idx) {            // 순서가 idx번째인 열거를 반환
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
     int key;
     do {
         for (Menu m : Menu.values())
             System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
         System.out.print(" : ");
         key = stdIn.nextInt();
     } while (key < Menu.ADD.ordinal() || key > Menu.TERMINATE.ordinal());

     return Menu.MenuAt(key);
 }

 public static void main(String[] args) {
     Menu menu;                                // 메뉴 
     SimpleObject2 data;                                // 추가용 데이터 참조
     SimpleObject2 temp = new SimpleObject2();        			// 읽어 들일 데이터

     ChainHash_SimpleObject<SimpleObject2> hash = new ChainHash_SimpleObject<SimpleObject2>(13);

     do {
         switch (menu = SelectMenu()) {
          case ADD :                               // 추가
                 data = new SimpleObject2();
                 data.scanData("추가", SimpleObject2.NO | SimpleObject2.NAME);
                 hash.add(data, SimpleObject2.NO_ORDER);
                  break;

//          case REMOVE :                       // 삭제
//                  temp.scanData("삭제", SimpleObject2.NO);
//                  hash.remove(temp.keyCode());
//                  break;
//
//          case SEARCH :                       // 검색
//                 temp.scanData("검색", SimpleObject2.NO);
//                 SimpleObject2 t = hash.search(temp.keyCode());
//                  if (t != null)
//                      System.out.println("그 키를 갖는 데이터는 " + t + "입니다.");
//                 else
//                      System.out.println("해당 데이터가 없습니다.");
//                  break;

          case DUMP :                            // 표시
                  hash.dump();
                  break;
         }
     } while (menu != Menu.TERMINATE);
 }
}
