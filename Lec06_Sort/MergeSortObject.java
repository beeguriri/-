package Lec06_Sort;

//import java.util.Arrays;

//PhyscData 클래스를 Comparable로 수정!
class PhyscExamSort {
	
	//--- 신체검사 데이터 ---//
    static class PhyscData implements Comparable <PhyscData> {
        String name;              // 이름
        int    height;            // 키
        double vision;            // 시력

        //--- 생성자(constructor) ---//
        PhyscData(String name, int height, double vision) {
            this.name = name;  this.height = height;  this.vision = vision;
        }

        //--- 신체검사 데이터를 문자열로 반환 --//
        public String toString() {
            return name + " " + height + " " + vision;
        }

        @Override
        public int compareTo(PhyscData p) {
        	
        	//키순 정렬 후 같으면 이름순 정렬
        	//s1.compareTo(s2) : s1 - s2
        	// 결과 양수 : s1이 큰 값 (ㅎ) 
        	// 결과 음수 : s1이 작은 값 (ㄱ)
        	
        	//키 순으로 먼저 정렬하고
        	if(this.height > p.height)	return 1;
        	else if (this.height < p.height) return -1;
        	
        	//키가 같으면 이름으로 정렬
        	else	return this.name.compareTo(p.name);
        	
        }
        
//      //--- 키의 오름차순용 comparator ---//
//        static final Comparator<PhyscData> HEIGHT_ORDER = new HeightOrderComparator();
//
//        private static class HeightOrderComparator implements Comparator<PhyscData> {
//            public int compare(PhyscData d1, PhyscData d2) {
//                return (d1.height > d2.height) ?  1 :
//                	   (d1.height < d2.height) ? -1 : 0;
//            }
//        }
    }
}


public class MergeSortObject {

	static int[] buff;	//작업용 배열
	
	// --- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
	static void merge(PhyscExamSort.PhyscData[] a, int lefta, int righta, int leftb, int rightb ) {
		
		//왼쪽배열 : lefta = left, righta = mid
		//오른쪽 배열 : leftb = mid + 1, rightb = right
		PhyscExamSort.PhyscData[] temp = new PhyscExamSort.PhyscData[rightb - lefta + 1];
		int idx = 0;
		int px = lefta;
		
		//Lec01_03, 중복없는리스트Merge 참고
		//왼쪽, 오른쪽 배열의 값 비교해서 temp에 먼저 넣어주고
		while (lefta <= righta && leftb <= rightb) {
			
			int n = a[lefta].compareTo(a[leftb]);
			if (n<0)	temp[idx++] = a[lefta++];
			else if (n>0)	temp[idx++] = a[leftb++];
			else {
				temp[idx++] = a[lefta++];
				leftb++;
			}	
		}
		
		while (lefta <= righta)	temp[idx++] = a[lefta++];
		
		while (leftb <= rightb) temp[idx++] = a[leftb++];
				
		for (int i =0; i < idx; i++)
			a[px++] = temp[i] ;
	}

	// --- 퀵 정렬(비재귀 버전)---//
	static void MergeSort(PhyscExamSort.PhyscData[] a, int left, int right) {
		int mid = (left+right)/2;
		if (left == right) return;
		MergeSort(a, left, mid);
		MergeSort(a, mid+1, right);
		merge(a, left, mid, mid+1, right);
		return;		
	}
	
	public static void main(String[] args) {
		
		PhyscExamSort.PhyscData[] x = {
		         new PhyscExamSort.PhyscData("강민하", 162, 0.3),
		         new PhyscExamSort.PhyscData("김찬우", 173, 0.7),
		         new PhyscExamSort.PhyscData("박준서", 171, 2.0),
		         new PhyscExamSort.PhyscData("유서범", 171, 1.5),
		         new PhyscExamSort.PhyscData("이수연", 168, 0.4),
		         new PhyscExamSort.PhyscData("장경오", 171, 1.2),
		         new PhyscExamSort.PhyscData("황지안", 169, 0.8),
		     };
		
		int nx = x.length;

		MergeSort(x, 0, nx - 1); 
		   
		System.out.println("오름차순으로 정렬했습니다.");
		System.out.println("■ 신체검사 리스트 ■");
		System.out.println(" 이름     키  시력");
		System.out.println("------------------");
		
		for (int i = 0; i < x.length; i++)
		         System.out.printf("%-8s%3d%5.1f\n", x[i].name, x[i].height, x[i].vision);
	}
}
