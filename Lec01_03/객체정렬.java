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

    //price를 integer type으로 wrapping
    public Integer getPrice() {
    	return this.price;
    }
    
    @Override
    public String toString() {
        return "<" + name + ", " + price + ">";
    }

	@Override
	public int compareTo(Fruit o) {

		//object 객체의 타입 캐스팅 (fruit 타입으로)
		Fruit a = (Fruit) o;
		return getPrice().compareTo(a.getPrice());
	}
	
/*	public int getPrice() {
		return this.price;
	}*/
	
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
		
//	    System.out.println(arr);
	    System.out.println("리스트1 정렬 전::");
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
	    System.out.println("리스트1 정렬 후::");
	    for (Fruit city: arr)
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
		
		System.out.println("\n리스트2 생성::");
	    for (Fruit city: lst2)
	    	System.out.print(" " + city);
	    
//	    Arrays.sort(lst2);
	    //lst2는 배열이 아니므로 에러 발생
	    
	    //인터페이스 Comparable에 오버라이딩 안해주면 Collections.sort 효과 없음
	    //오버라이딩 했는데 왜 글자순으로는 정렬을 안해줄까? 
	    Collections.sort(lst2);
		System.out.println();
		System.out.println("리스트2 정렬 후::");
	    for (Fruit city: lst2)
	    	System.out.print(" " + city);  
	    
	    
	    //이터레이터 사용해서 lst3(lst1 + lst2) merge 
	    //lst1, lst2 객체 비교
	    //스트링이 아니므로 compareTo 사용 못함
	    ArrayList<Fruit> lst3 = new ArrayList<Fruit>();
		
		Iterator<Fruit> iter1 = lst1.iterator();
		Iterator<Fruit> iter2 = lst2.iterator();
		Fruit data1 = iter1.next();
		Fruit data2 = iter2.next();
	    
		//구현할 부분		
		//lst1과 lst2에 모두 값이 있으면
		while(iter1.hasNext() && iter2.hasNext()) {
			
			if (data1.compareTo(data2)< 0) {
				lst3.add(data1);
				data1 = iter1.next();
			}
			
			if (data1.compareTo(data2) > 0) {
				lst3.add(data2);
				data2 = iter2.next();
			}
			
			else {				//data1과 data2가 같은 경우

				lst3.add(data1);
				lst3.add(data2);
				data1 = iter1.next();
				data2 = iter2.next();
			}
			
		}
		
		//lst1에만 값이 있으면
		while(iter1.hasNext()) {
			
			if (data1.compareTo(data2)> 0) {	//data1이 더 크면
				lst3.add(data2);				//data2를 먼저 추가하고
				lst3.add(data1);				//data1을 추가한다
				do {
					data1 = iter1.next();		
					lst3.add(data1);
				} while(iter1.hasNext());
				
			} else if (data1.compareTo(data2)< 0) {	//data1이 더 작으면
				lst3.add(data1);					//data1을 먼저 추가하고
				data1 = iter1.next();				//lst1의 다음데이터와 data2를 비교 수행
				
				if (iter1.hasNext()) continue;		//lst1에 값이 남아있으면 계속 수행
				else lst3.add(data2);				//lst1에 값이 없으면 data2를 추가

			} else {								//data1과 data2가 같으면
				lst3.add(data1);					//data1, data2 추가
				lst3.add(data2);					

				while (iter1.hasNext()) {			//lst1에 값이 남아있으면
					data1 = iter1.next();			//다음 data1를 전부
					lst3.add(data1);				//lst3에 추가
				}
			}
		}
		
		//lst2에만 값이 있으면
		while(iter2.hasNext()) {
			
			if (data2.compareTo(data1)> 0) {	//data2이 더 크면
				lst3.add(data1);				//data1를 먼저 추가하고
				lst3.add(data2);				//data2을 추가한다
				do {
					data2 = iter2.next();		
					lst3.add(data2);
				} while(iter2.hasNext());
				
			} else if (data2.compareTo(data1)< 0) {	//data2이 더 작으면
				lst3.add(data2);					//dat2을 먼저 추가하고
				data2 = iter2.next();				//lst2의 다음데이터와 data1를 비교 수행
				
				if (iter2.hasNext()) continue;		//lst2에 값이 남아있으면 계속 수행
				else lst3.add(data1);				//lst1에 값이 없으면 data1를 추가

			} else {								//data1과 data2가 같으면
				lst3.add(data1);					//data1, data2 추가
				lst3.add(data2);					

				while (iter2.hasNext()) {			//lst2에 값이 남아있으면
					data2 = iter2.next();			//다음 data2를 전부
					lst3.add(data2);				//lst3에 추가
				}
			}
		}

		System.out.println();
	    System.out.println("merge:: ");
	    for (Fruit city: lst3)
	    	System.out.print(city+ " ");
	    
    	System.out.print("\n====================\n");

	    
	    //binary search
	    //Comparator사용 (두 매개변수 비교)
	    //익명클래스 사용
	    Fruit newFruit = new Fruit("참외", 100);
	    Fruit newFruit2 = new Fruit("딸딸기", 30);


	    Comparator<Fruit> cc = new Comparator<Fruit>() {	
	    	
	        public int compare(Fruit u1, Fruit u2) {
	          return u1.compareTo(u2);
	        }
	    };
	    
	    //??data1이 iter.next()인데 무슨 의미가 있나...?
	    //newFruit2로 비교하면 나옴!!ㅋㅋ
	    int res = cc.compare(newFruit2, newFruit);
	     
	    if (res > 0)  	   System.out.println("\ndata1 > newFruit\n");
	    else if (res < 0)  System.out.println("\ndata1 < newFruit\n");
	    else 			   System.out.println("\ndata1 = newFruit\n");
	      /*
	    System.out.println();
	    int result = Collections.binarySearch(lst3, newFruit, cc);
			System.out.println("\nCollections.binarySearch() 조회결과::" + lst3.get(result));
		*/

		//lst3를 배열로 만듦(binarySearch 사용하려고)

		Fruit [] fa = new Fruit[lst3.size()];
		
		fa = lst3.toArray(fa);
		
	    int result3 = Arrays.binarySearch(fa, newFruit, cc); 
	    
	    if (result3 < 0) System.out.println("\nArrays.binarySearch() 조회결과:: 없음");
	    else 	System.out.println("\nArrays.binarySearch() 조회결과::" + fa[result3]);
		
		/*
		int result2 = binSearch(fa, lst3.size(), newFruit);
		System.out.println("\nbinSearch() 조회결과:" + lst3.get(result2));
		*/
		}
	
		// 교재 109 페이지(실습3-4) 참조하여 구현, 비교연산자 부분을 바꿔줘야 함
		static int binSearch(Fruit[]a, int n, Fruit f) {
		//구현할 부분
			
			int pl = 0; //검색범위의 첫 인덱스
			int pr = n-1; //검색범위의 마지막 인덱스
			
			do {
				int pc = (pl + pr) /2 ;	//중앙 요소의 인덱스
				if(a[pc] == f)  return pc;		//찾는값이 key값과 같으면 해당 인덱스 반환
				else if (a[pc].compareTo(f) < 0)  pl = pc + 1;	//검색범위를 뒤쪽으로
				else pr = pc -1;								//검색범위 앞쪽으로
			} while (pl <= pr);				//첫 인덱스가 마지막인덱스와 같거나 작을떄까지만 실행
				
			return -1;				//검색실패			
		}
		
}

