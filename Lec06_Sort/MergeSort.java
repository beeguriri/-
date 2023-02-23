package Lec06_Sort;

import java.util.Arrays;

//PhyscData 클래스를 Comparable로 수정!
class PhyscData implements Comparable <PhyscData> {
	
    String name;              // 이름
    int    height;            // 키
    double vision;            // 시력

    //--- 생성자(constructor) ---//
    PhyscData(String name, int height, double vision) {
        this.name = name;  this.height = height;  this.vision = vision;
    }

    @Override
    public int compareTo(PhyscData p) {
    	
    	//이름으로 비교
    	//s1.compareTo(s2) : s1 - s2
    	// 결과 양수 : s1이 큰 값 (ㅎ) 
    	// 결과 음수 : s1이 작은 값 (ㄱ)
    	
    	//if(this.name.compareTo(p.name)>0)	return 1;
		return this.name.compareTo(p.name);
    }
    
    //--- 신체검사 데이터를 문자열로 반환 --//
    public String toString() {
        return name + " " + height + " " + vision;
    }   

}

public class MergeSort {

	static int[] buff;	//작업용 배열
	
	static void merge(PhyscData[] a, int lefta, int righta, int leftb, int rightb ) {
		
	}

	static void mergeSort(PhyscData[] a, int left, int right) {
		
		if(left < right) {
			
			int i;
			
			int mid = (left+right)/2;
			int p = 0;
			int j = 0;
			int k = left;
			
			mergeSort(a, left, mid);
			mergeSort(a, mid+1, right);
			
			for (i = left; i <= mid; i++)	buff[p++] = a[i].height;
			
			while (i <=right && j <p) 
				a[k++] = (buff[j] <= a[i].height) ? buff[j++]: a[i++].height;
			
			while (j < p) 
				a[k++] = buff[j++];

		
			if (left == right) return;

			merge(a, left, mid, mid+1, right);

		}				
	}
	
	public static void main(String[] args) {
		
		PhyscData[] x = {
		         new PhyscData("강민하", 162, 0.3),
		         new PhyscData("유서범", 171, 1.5),
		         new PhyscData("황지안", 169, 0.8),
		         new PhyscData("박준서", 171, 2.0),
		         new PhyscData("김찬우", 173, 0.7),
		         new PhyscData("이수연", 168, 0.4),
		         new PhyscData("장경오", 171, 1.2),
		     };
		
		int nx = x.length;

		mergeSort(x, 0, nx - 1); 
		   
		System.out.println("오름차순으로 정렬했습니다.");
		System.out.println("■ 신체검사 리스트 ■");
		System.out.println(" 이름     키  시력");
		System.out.println("------------------");
		
		for (int i = 0; i < x.length; i++)
		         System.out.printf("%-8s%3d%5.1f\n", x[i].name, x[i].height, x[i].vision);
	}
}
