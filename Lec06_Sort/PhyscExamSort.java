package Lec06_Sort;

//import java.util.Comparator;

public class PhyscExamSort {
	
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
