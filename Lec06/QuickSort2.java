package Lec06;

import example.IntStack;

public class QuickSort2 {

	//--- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
    static void swap(int[] a, int idx1, int idx2) {
        int t = a[idx1];  a[idx1] = a[idx2];  a[idx2] = t;
    }

    //--- 퀵 정렬(비재귀 버전)---//
    static void quickSort(int[] a, int left, int right) {
    	
    	//스택을 하나만 쓰는 방법으로!
    	//Stack s = new Stack(size);
    	//Point p = new Point (left, right)
    	
    	IntStack lstack = new IntStack(right - left + 1);
        IntStack rstack = new IntStack(right - left + 1);

        lstack.push(left);
        rstack.push(right);

        while (lstack.isEmpty() != true) {
            int pl = left  = lstack.pop();        // 왼쪽 커서
            int pr = right = rstack.pop();        // 오른쪽 커서
            
            //p = s.pop();
            //left = p.getX();
            //right = p.getY();
            
            int x = a[(left + right) / 2];        // 피벗은 가운데 요소

            do {
                while (a[pl] < x) pl++;
                while (a[pr] > x) pr--;
                if (pl <= pr)
                    swap(a, pl++, pr--);
                
            } while (pl <= pr);

            if (left < pr) {
                lstack.push(left);           // 왼쪽 그룹 범위의
                rstack.push(pr);             // 인덱스를 푸시
            }
            if (pl < right) {
                lstack.push(pl);             // 오른쪽 그룹 범위의
                rstack.push(right);          // 인덱스를 푸시
            }
        }
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

        int [] x = {5, 8, 4, 2, 6, 1, 3, 9, 7};
        int nx = x.length;
		
        quickSort(x, 0, nx - 1);            // 배열 x를 퀵정렬
        
        System.out.println("\n오름차순으로 정렬했습니다.");
        for (int i = 0; i < nx; i++)
            System.out.print(" " + x[i]);
        
	}

}
