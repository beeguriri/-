package Lec01_03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;


//Comparable 인터페이스를 사용하려면 compareTo() method를 구현
//implements : 인터페이스 구현
class Fruit implements Comparable<Fruit> {
	
    private String name;
    private int price;
    
    //생성자
    public Fruit(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "<" + name + ", " + price + ">";
    }

	@Override
	public int compareTo(Fruit o) {
	//구현할 부분
		return 0;
	}
	
	public int getPrice() {
		return price;
	}
}

//정렬의 종류
//Collections.sort(List)
//Arrays.sort(Array)
//Arrays.stream()

public class 객체정렬 {
	
	public static void main(String[] args) {
		
//		String[] s = { "sort", "string", "array" };
		
//		Arrays.sort(s);							   //오름차순
//		Arrays.sort(s, Comparator.naturalOrder()); //자연정렬
//		Arrays.sort(s, Comparator.reverseOrder()); //내림차순
		
		/*
		Arrays.sort(s, new Comparator<String>() {
		    @Override
		    public int compare(String o1, String o2) {
		        return o2.compareTo(o1);            // 내림차순으로 정렬
		    }
		});
		*/
		
		//스트림에서 처리
//		s = Arrays.stream(s).sorted().toArray(String[]::new); 	
//		s = Arrays.stream(s).sorted(Collections.reverseOrder()).toArray(String[]::new);
//		Collections.sort(Arrays.asList(s));
		
		Fruit[] arr = {						//객체의 배열
				
		        new Fruit("사과", 200),
		        new Fruit("키위", 500),
		        new Fruit("오렌지", 200),
		        new Fruit("바나나", 50),
		        new Fruit("수박", 880),
		        new Fruit("체리", 10)
		};
		
	    System.out.println(arr);
	    System.out.println("정렬전::");
	    for (Fruit city : arr)
	    	System.out.print(" " + city);
	    
	    //정렬 : (a,b) -> a.getPrice() - b.getPrice()
	    //람다식 : 익명클래스, 객체의 생성, 메서드 구현 동시에
	    //Fruit에 compareTo()가 있어도 람다식 우선 적용
		Arrays.sort(arr, (a,b) -> a.getPrice() - b.getPrice()); 

//	    int count = arr.length;
//	    for (int i = 0; i < count; i++)
//	    	for (int j = i +1; j < count; j++) {
//	    		if (arr[i].compareTo(arr[j]) > 0)
//	    		{
//	    			Fruit temp; temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
//	    		}
//	    	}
		
		System.out.println();
	    System.out.println("정렬후::");
	    for ( Fruit city: arr)
	    	System.out.print(" " + city);

	    //배열을 리스트로 만듦
		ArrayList<Fruit> lst1 = new ArrayList<Fruit>(Arrays.asList(arr));
		
		//배열인자 불러와서 리스트로 만듦
		ArrayList<Fruit> lst2 = new ArrayList<Fruit>();
		lst2.add(new Fruit("복숭아", 200));
		lst2.add(new Fruit("포도", 300));
		lst2.add(new Fruit("참외", 100));
		lst2.add(new Fruit("딸기", 50));
		lst2.add(new Fruit("블루베리", 500));
		lst2.add(new Fruit("구지뽕", 300));
		
		System.out.println();
		System.out.println("새로운 리스트2::");
	    for (Fruit city: lst2)
	    	System.out.print(" " + city);
	    
//	    Arrays.sort(lst2);
	    //lst2는 배열이 아니므로 에러 발생
	    
	    Collections.sort(lst2);
		System.out.println();
		System.out.println("새로운 리스트2 정렬::");
	    for (Fruit city: lst2)
	    	System.out.print(" " + city);
	    
	    //이터레이터 사용해서 lst3 (lst1 + lst2)
	    //lst1, lst2 객체 비교
	    //스트링이 아니므로 compareTo 사용 못함
	    ArrayList<Fruit> lst3 = new ArrayList<Fruit>();
		
		Iterator<Fruit> iter1 = lst1.iterator();
		Iterator<Fruit> iter2 = lst2.iterator();
		Fruit data1 = iter1.next();
		Fruit data2 = iter2.next();
	    	//구현할 부분
		System.out.println();
	    System.out.println("merge:: ");
	    for ( Fruit city: lst3)
	    	System.out.print(city+ " ");
	    Fruit newFruit = new Fruit("참외", 100);
	    
	    
	    //binary search
	    Comparator<Fruit> cc = new Comparator<Fruit>() {	//익명클래스 사용
	    	
	        public int compare(Fruit u1, Fruit u2) {
	          return u1.compareTo(u2);
	        }
	    };
	    
	     int res = cc.compare(data1, newFruit);
	     
	     if (res > 0)
	    	 System.out.println("\ndata1 > newFruit\n");
	      /*
	    System.out.println();
	    int result = Collections.binarySearch(lst3, newFruit, cc);
			System.out.println("\nCollections.binarySearch() 조회결과::" + lst3.get(result));
		*/

		Fruit [] fa = new Fruit[lst3.size()];
		
		//lst3를 배열로 만듦(binarySearch 사용하려고)
		fa = lst3.toArray(fa);
		
	    int result3 = Arrays.binarySearch(fa, newFruit, cc); 
	    
		System.out.println("\nArrays.binarySearch() 조회결과::" + lst3.get(result3));
		
		/*
		int result2 = binSearch(fa, lst3.size(), newFruit);
		System.out.println("\nbinSearch() 조회결과:" + lst3.get(result2));
		*/
		}
	
		// 교재 109 페이지(실습3-4) 참조하여 구현, 비교연산자 부분을 바꿔줘야 함
		static int binSearch(Fruit[]a, int n, Fruit f) {
		//구현할 부분
			return 0;
		}
		
}

