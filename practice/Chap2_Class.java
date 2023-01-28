package practice;

//import java.util.Scanner;

public class Chap2_Class {

    static final int VMAX = 21;        // 시력의 분포(0.0부터 2.0까지 0.1 단위 : 21개)

    static class PhyscData {
    	
        String name;            // 이름
        int    height;          // 키
        double vision;          // 시력

        //--- 생성자(constructor) ---//
        PhyscData(String name, int height, double vision) {
        	
            this.name = name;
            this.height = height;
            this.vision = vision;
        }
    }

    //--- 키의 평균값을 구하는 메서드 ---//
    static double aveHeight(PhyscData[] dat) {
        double sum = 0;

        for (int i = 0; i < dat.length; i++)
            sum += dat[i].height;

        return sum / dat.length;
    }

    //--- 시력의 분포를 구하는 메서드 ---//
    static void distVision(PhyscData[] dat, int[] dist) {

    	int i = 0;
        dist[i] = 0;
        
        for (i = 0; i < dat.length; i++)
            if (dat[i].vision >= 0.0 && dat[i].vision <= (VMAX-1) / 10.0)	//0.0~2.0이면
                dist[(int)(dat[i].vision * 10)]++;							//dist[i] <- i=
    }

    public static void main(String[] args) {
//        Scanner stdIn = new Scanner(System.in);

        PhyscData[] x = {						//생성자(초기값 세팅) 배열
            new PhyscData("강민하", 162, 0.3),
            new PhyscData("김찬우", 173, 0.7),
            new PhyscData("박준서", 175, 2.0),
            new PhyscData("유서범", 171, 1.5),
            new PhyscData("이수연", 168, 1.2),
            new PhyscData("장경오", 174, 1.2),
            new PhyscData("황지안", 169, 0.8)
        };
        
        int[] vdist = new int[VMAX];                    // 시력의 분포

        System.out.println("■ 신체검사 리스트 ■");
        System.out.println(" 이름    키   시력");
        System.out.println("--------------------");
        
        for (int i = 0; i < x.length; i++) {
        	
            System.out.printf("%-6s%3d%5.1f\n", x[i].name, x[i].height, x[i].vision);
        }
        
        System.out.printf("\n평균 키: %5.1fcm\n", aveHeight(x));

        distVision(x, vdist);                           // 시력의 분포를 구함

        System.out.println("\n시력 분포");
        for (int i = 0; i < VMAX; i++) {

//          System.out.printf("%3.1f～: %2d명\n", i / 10.0, vdist[i]);

        	//사람수를 기호문자로 대체하기
        	String str = "*"; 
            System.out.printf("%3.1f～: %s\n", i / 10.0, str.repeat(vdist[i]));
        }
    }
}
