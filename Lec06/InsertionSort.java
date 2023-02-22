package Lec06;

//import java.util.Scanner;

public class InsertionSort {
	
	
	static void insertionSort(int[] a, int n) {
        for (int i = 1; i < n; i++) {
        	
        	//정렬 과정 출력
        	for (int num : a) 	
        		System.out.print(" " + num);
        	
        	
        	System.out.println();
        	
            int j;
            int tmp = a[i];
            
            for (j = i; j > 0 && a[j - 1] > tmp; j--)
                a[j] = a[j - 1];
            
            a[j] = tmp; //insert
         
        }
    }
	
	public static void main(String[] args) {
		
//		Scanner stdIn = new Scanner(System.in);

        System.out.println("단순 삽입 정렬");
/*
        System.out.print("요솟수: ");
        int nx = stdIn.nextInt();
        int[] x = new int[nx];

        for (int i = 0; i < nx; i++) {
            System.out.print("x[" + i + "] : ");
            x[i] = stdIn.nextInt();
        }
*/
        //int [] x = {6, 4, 1, 7, 3, 9, 8};
        //int [] x = {8, 1, 4, 2, 7, 6, 3, 5};
        int [] x = {1, 2, 3, 4, 5, 0, 6};
        
        int nx = x.length;
        
        insertionSort(x, nx);        // 배열 x를 단순삽입정렬

        System.out.println("\n오름차순으로 정렬했습니다.");
        for (int i = 0; i < nx; i++)
            System.out.print(" " + x[i]);
        
	}
}
