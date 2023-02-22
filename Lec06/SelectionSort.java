package Lec06;

//import java.util.Scanner;

public class SelectionSort {
	
	//--- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
    static void swap(int[] a, int idx1, int idx2) {
        int t = a[idx1]; a[idx1] = a[idx2]; a[idx2] = t;
    }
    
    //--- 단순 선택 정렬 ---//
    static void selectionSort(int[] a, int n) {
        for (int i = 0; i < n - 1; i++) {
        	
        	//정렬 과정 출력
            for(int num : a) 
            	System.out.print(" " + num);
            
        	System.out.println();

        	int min = i;                // 아직 정렬되지 않은 부분에서 가장 작은 요소의 인덱스를 저장
            
        	for (int j = i + 1; j < n; j++)
                if (a[j] < a[min])
                	min = j;
        	
            swap(a, i, min);           // 아직 정렬되지 않은 부분의 첫 요소와 가장 작은 요소를 교환
        }
    }

	public static void main(String[] args) {

//		Scanner stdIn = new Scanner(System.in);

        System.out.println("단순 선택 정렬");
        
        /*
        System.out.print("요솟수: ");
        int nx = stdIn.nextInt();
        int[] x = new int[nx];

        for (int i = 0; i < nx; i++) {
            System.out.print("x[" + i + "] : ");
            x[i] = stdIn.nextInt();
        }
		*/
        
        int [] x = {6, 4, 8, 3, 1, 9, 7};
        int nx = x.length;
        
        selectionSort(x, nx);            // 배열 x를 단순선택정렬

        System.out.println("\n오름차순으로 정렬했습니다.");
        for (int i = 0; i < nx; i++)
            System.out.print(" " + x[i]);
    }
}
