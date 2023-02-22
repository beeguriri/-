package Lec06;

public class QuickSort {
	
	//--- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
    static void swap(int[] a, int idx1, int idx2) {
        int t = a[idx1];  a[idx1] = a[idx2];  a[idx2] = t;
    }
    
    //--- 퀵 정렬 ---//
    static void quickSort(int[] a, int left, int right) {
        int pl = left;                   // 왼쪽 커서
        int pr = right;                  // 오른쪽 커서
        int x = a[(pl + pr) / 2];        // 피벗(가운데 요소)
        
        System.out.println("피벗의 값은 " + x + "입니다.");

        do {
        	
            for (int num : a) System.out.print(" " + num);
            System.out.println();
        	
            while (a[pl] < x) pl++;
            while (a[pr] > x) pr--;
            if (pl <= pr)
                swap(a, pl, pr);
            
            System.out.println("pl= "+pl+",pr= "+pr);
            pl++; pr--;
            
        } while (pl <= pr);
        
        /*
        System.out.println("피벗 이하 그룹 ");
        for (int i = 0; i <= left; i++)              // a[0] ～ a[pl - 1]
            System.out.print(a[i] + " ");
        System.out.println();

        System.out.println("피벗 이상 그룹 ");
        for (int i = pr + 1; i <= right; i++)              // a[pr + 1] ～ a[n - 1]
            System.out.print(a[i] + " ");
        System.out.println();
        */
        
        //recursive
        if (left < pr)  quickSort(a, left, pr);
        if (pl < right) quickSort(a, pl, right);
    }

	public static void main(String[] args) {

        int [] x = {5, 8, 4, 2, 6, 1, 3, 9, 7};

        int nx = x.length;

        quickSort(x, 0, nx - 1);            // 배열 x를 퀵정렬

        System.out.println("\n오름차순으로 정렬했습니다.");
        for (int i = 0; i < nx; i++)
            System.out.print(" " + x[i]);
		
	}

}
