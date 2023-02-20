package Lec06;

import java.util.Scanner;

public class BubbleSort {

    //--- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
    static void swap(int[] a, int idx1, int idx2) {
        int t = a[idx1]; a[idx1] = a[idx2]; a[idx2] = t;
    }

    //--- 버블 정렬 ---//
    static void bubbleSort1(int[] a, int n) {
    	
    	int count1 = 0;
    	
        for (int i = 0; i < n - 1; i++) {

            for (int j = n - 1; j > i; j--) {
            	
                if (a[j - 1] > a[j])	swap(a, j - 1, j);

            }
            count1++;

        }
    }
    
    
    static void bubbleSort2(int[] a, int n) {
    	
    	int count2 = 0;

        for (int i = 0; i < n - 1; i++) {
        	
        	int excg=0;

            for (int j = n - 1; j > i; j--) {
            	
                if (a[j - 1] > a[j]) {
                    swap(a, j - 1, j);
        			excg++;
        		}
            	count2++;
                //System.out.println("excg : " + excg);	

            }
            if(excg==0) break;
        }
    }
    

    public static void main(String[] args) {
        try (Scanner stdIn = new Scanner(System.in)) {
			System.out.print("요솟수: ");
			int nx = stdIn.nextInt();
			int[] x = new int[nx];

			for (int i = 0; i < nx; i++) {

				double d = Math.random();	
				x[i] = (int) (d*20);
				
			    System.out.print(" "+ x[i]);
			    //x[i] = stdIn.nextInt();
			}
			System.out.println("\n");

			System.out.println("버블정렬 (버전1)");
			bubbleSort1(x, nx);                // 배열 x를 단순교환정렬
			for (int i = 0; i < nx; i++) 
			    System.out.print(" "+ x[i]);
			
			System.out.println();
			
			System.out.println("\n버블정렬 (버전2)");
			bubbleSort2(x, nx);                // 교환횟수 0이면 정렬작업 멈춤
			for (int i = 0; i < nx; i++)
			    System.out.print(" "+ x[i]);
		}
    }

}
