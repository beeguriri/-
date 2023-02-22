package Lec06;

public class ShellSort {
	
	//--- 셸 정렬 ---//
    static void shellSort(int[] a, int n) {
        for (int h = n / 2; h > 0; h /= 2)	//h : 8/2=4, h : 4/2=2, h : 2/2=1
        	      	
            for (int i = h; i < n; i++) { 
            	
                //정렬 과정 출력
            	System.out.print("h=" + h + " |"); 
            	
            	for(int num : a) 
        			System.out.print(" " + num);
            	System.out.println();
            	
                int j;
                int tmp = a[i];
                
                for (j = i - h; j >= 0 && a[j] > tmp; j -= h)
                    a[j + h] = a[j];
                
                a[j + h] = tmp;
                
            }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Scanner stdIn = new Scanner(System.in);

        System.out.println("셸 정렬(버전 1)");
/*
        System.out.print("요솟수: ");
        int nx = stdIn.nextInt();
        int[] x = new int[nx];

        for (int i = 0; i < nx; i++) {
            System.out.print("x[" + i + "]: ");
            x[i] = stdIn.nextInt();
        }
*/
        
        int [] x = {8, 1, 4, 2, 7, 6, 3, 5};
        int nx = x.length;
        
        shellSort(x, nx);            // 배열 x를 셸정렬

        System.out.println("\n오름차순으로 정렬했습니다.");
        for (int i = 0; i < nx; i++)
            System.out.print(" " + x[i]);
	}

}
