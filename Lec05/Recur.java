package Lec05;

import java.util.Scanner;
import java.util.Stack;


public class Recur {
	
    //--- 순수 재귀 메서드 ---//
	/*
    static void recur(int n) {
        if (n > 0) {
            recur(n - 1);
            System.out.println(n);
            recur(n - 2);
        }
   }*/
	
	//재귀의 제거 : while문으로
	static void recur(int n) {
		
		Stack<Integer> s = new Stack<Integer>();		//stack은 후입선출(LIFO)
		
		while (true) {
			
            if (n > 0) {
                s.push(n);                              // stack s에 n을 푸시
                n = n - 1;
                continue;								//while루프 다시 돌림
            }
            
            if (s.isEmpty() != true) {    				// 스택이 비어 있지 않으면
                n = s.pop();                            // 저장하고 있던 값을 n에 팝 
                System.out.println(n);
                n = n - 2;
                continue;
            }
            
            break;
		}
   }

	public static void main(String[] args) {
		
	       try (Scanner stdIn = new Scanner(System.in)) {
	    	   
			System.out.print("정수를 입력하세요 : ");
	        int x = stdIn.nextInt();

	        recur(x);
	        
		}
	}
}
