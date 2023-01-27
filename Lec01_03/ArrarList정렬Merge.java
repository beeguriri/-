package Lec01_03;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

//10장 Collection, Test01, Test02를 사용

//string 정렬, binary search 구현
//1단계: string, 2단계: string 객체,  Person 객체들의 list\
//file1: 서울,북경,상해,서울,도쿄,뉴욕,부산
//file2: 런던,로마,방콕,도쿄,서울,부산
//file > string split() > 배열 > ArrayList > sort > iterator 사용하여 merge > 중복 제거 > string 배열 > file에 저장

public class ArrarList정렬Merge {
	/*
	static int binSearch(String[] s, int n, String key) {
		//자료구조 책 페이지 115 코드 사용한다.
	}
	*/
	public static void main(String[] args) {
		try {
			Path input1 = Paths.get("list1.txt");
			byte[] bytes1 = Files.readAllBytes(input1);

			Path input2 = Paths.get("list2.txt");
			byte[] bytes2 = Files.readAllBytes(input2);
			
		    System.out.println("== readAllBytes ==");
		    System.out.println(new String(bytes1));		//읽어온거 출력
		    System.out.println(new String(bytes2));		//읽어온거 출력

//			String[] sarray1 = new String[10];
//			String[] sarray2 = new String[10];

		    String s1 = new String(bytes1);
		    String s2 = new String(bytes2);
		    
//		    System.out.println("s1 = " + s1);
//		    System.out.println("s2 = " + s2);

		    String [] sa1 = s1.split(",");
		    String [] sa2 = s2.split(",");
			
			// file1에서 read하여 list1.add()한다.
			
			// 배열을 list로 만드는 방법
			// 방법1: for문으로
		    System.out.println("\n== for문으로 list 생성 ==");

			ArrayList<String> arrayList1 = new ArrayList<>();
			for (String temp : sa1) {
				arrayList1.add(temp);
			}
			System.out.println(arrayList1);
			
			ArrayList<String> arrayList2 = new ArrayList<>();
			for (String temp : sa2) {
				arrayList2.add(temp);
			}
			System.out.println(arrayList2);
			
			// 방법2 : asList함수를 불러서
		    System.out.println("\n== asList 함수로 list 생성 ==");
			ArrayList<String> list1 = new ArrayList<>(Arrays.asList(sa1));
			ArrayList<String> list2 = new ArrayList<>(Arrays.asList(sa2));
			System.out.println(list1);
			System.out.println(list2);
			//ArrayList<String> list2 = new ArrayList<String>();
			// file2에서 read하여 list1.add()한다.

			//
			System.out.println("\ncollection.sort()::");	//Collections.sort() : 오름차순 정렬
			
			System.out.print("list1:: ");
			
			Collections.sort(list1);
			for (String city : list1)
				System.out.print(city + " ");
			System.out.println();
			
			System.out.print("list2:: ");
			
			Collections.sort(list2);
			for (String city : list2)
				System.out.print(city + " ");
			System.out.println();
			
			System.out.println("\nIterator::"); 	// iterator를 사용하여 merge

			ArrayList<String> list3 = new ArrayList<String>();

			Iterator<String> iter1 = list1.iterator();
			Iterator<String> iter2 = list2.iterator();
			
			String data1 = iter1.next();
			String data2 = iter2.next();
//			System.out.println("data1 = " + data1);
//			System.out.println("data2 = " + data2);
			
			//두 리스트에 모두 값이 있으면
			while(iter1.hasNext() && iter2.hasNext()) {
				
				if(data1.compareTo(data2) < 0) {	//-1반환 : data1이 작다는 이야기
					list3.add(data1);
					data1 = iter1.next();
				} else if(data1.compareTo(data2) > 0) { // 1반환 : data2가 크다는 이야기
					list3.add(data2);				
					data2 = iter2.next();
				} else {								//0반환 : data1 == data2 
					list3.add(data1);					//(일단 데이터 다 받아오고 나중에 중복제거 하겠다) 
					list3.add(data2);
					data1 = iter1.next();
					data2 = iter2.next();
				}
			}
					
//			System.out.println("첫번째 while문: " + list3);
			
			//list1에만 값이 있을 때
			//마지막값을 반환 하지 못하는 단점이 있음 (hasNext가 없으므로 while문 빠져나옴)
			while(iter1.hasNext()) {
				list3.add(data1);
				data1 = iter1.next();
			}
			
//			System.out.println("두번째 while문: " + list3);
			//list2에만 값이 있을 때
			while(iter2.hasNext()) {
				list3.add(data2);				
				data2 = iter2.next();
			}
//			System.out.println("세번째 while문: " + list3);
			System.out.println(list3);

			//list3의 중복제거 (???)
			Iterator<String> iter3 = list3.iterator();
			String data3 = iter3.next();
			
			while(iter3.hasNext()) {
				if(data3.compareTo(iter3.next())==0) {
					list3.remove(data3);
					data3 = iter3.next();
				}
			}
			System.out.println("중복제거 : " + list3);



			
			
			
			
			
			//ArrayList를 merge하는 코드 구현 list3 = list1 + list2(merge)

			// binary search 구현
			// 방법1:
//			Arrays.binarySearch(null, null, null);// ArrayList에는 binarySearch()가 없다.
			// 방법2:
			// int result = Collections.binarySearch(list3, newFruit, cc);

			// 방법3:

			System.out.println();
			System.out.println("merge:: ");
			for (String city : list3)
				System.out.print(city + " ");
			// ArrayList를 배열로 전환
			String[] st = list3.toArray(new String[list3.size()]);
			// binary search 구현
			// binSearch(st, st.length, "key");
			
			// 정렬된 list3을 file에 출력하는 코드 완성
			int bufferSize = 10240;
			String b = " ";
			ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
			for (String sx : list3) {
				System.out.println(" " + sx);
				buffer.put(sx.getBytes());
				buffer.put(b.getBytes());
			}
			buffer.flip();
			FileOutputStream file = new FileOutputStream("list3.txt");
			FileChannel channel = file.getChannel();
			channel.write(buffer);
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
