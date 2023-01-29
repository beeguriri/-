package Lec01_03;

import java.io.FileOutputStream;

//12장 입출력 작업하기 Test06_2를 수정하여 스트링 정렬하기, Test11/596페이지
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class 문자열배열정렬 {

public static void main(String[] args) {

  try {

    // 파일 읽어서 string 객체를 정렬하는 프로그램 구성
    Path input = Paths.get("a.txt");

    byte[] bytes = Files.readAllBytes(input);	//파일에 있는걸모두 다 읽어서 bytes 배열에 저장
    System.out.println("== readAllBytes ==");
    System.out.println(new String(bytes));		//읽어온거 출력
    
    
    // ------------------- 추가한 코드
    // file 입출력 + String 처리 + 정렬 코딩
    String s = new String(bytes);
//    System.out.println("s = " + s);
    String [] sa = s.split(" |//\n");	// 블랭크 또는 줄바꿈 기준 스플릿 (regular expression)

    System.out.println("\n====split 결과====");
//    for(int i = 0; i < sa.length; i++) {
//    
//    	System.out.print(sa[i] + " | ");
//    }
    System.out.println(Arrays.toString(sa));
    
	//단어 올림차순으로 정렬
    //정수는 < , >, == 등 비교연산자 사용할 수 있으나
    //스트링 타입은 compareTo 사용
    //0 : 두개의 문자열이 동일
    //양수 : compareTo()를 호출하는 객체가 인자보다 사전적으로 순서가 앞설 때
    //음수 : 인자가 객체보다 사전적으로 순서가 앞설 때
    
    System.out.println("\n\n====정렬 결과====");
    
    String temp = "";
    
    for (int i=0; i < sa.length; i++) {
	
    	for (int j = i+1; j < sa.length; j++) {
    	
    		if(sa[i].compareTo(sa[j])>0) {
    			temp = sa[j];
    			sa[j] = sa[i];
    			sa[i] = temp;
    		}     		
    	}
    	System.out.println(sa[i]);    	
    }

    //ArrayList의 collection sort 기능 써보기
    System.out.println("\n==== Collections.sort()메서드 사용 결과====");
	ArrayList<String> list = new ArrayList<>(Arrays.asList(sa));
	Collections.sort(list);
	for (String arr : list)
		System.out.println(arr);
	System.out.println();	
	
    // ---------------------------
	//정렬한 값 file에 저장

    int bufferSize = 10240;
    String b = " ";
    ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
    
    for (String sx : sa) {
//  	  System.out.println(" " + sx);
  	  buffer.put(sx.getBytes());
  	  buffer.put(b.getBytes());
    }
	  buffer.flip();
    FileOutputStream file = new FileOutputStream("c.txt");
    FileChannel channel = file.getChannel();
    channel.write(buffer);
    file.close();

  } catch(IOException e) {
    e.printStackTrace();
  }
}
}
