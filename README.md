# Data_Structure
자바 자료구조 수업 (\lec** )
+ 0127 배열정렬, ArrayList, Iterator
+ 0130 list 중복 제거, 정렬
+ 0131 인터페이스 Comparable, comparator 구현, binarysearch
+ 0201 Stack 생성, Queue 생성, Circular Queue(출력함수 수정완료)
  + `circular queue`
    + push(data) : rear 증가(rear = (rear+1)%capacity) 후, 그 위치에 data push
    + pop() : data pop 후 front 증가(front = (front+1)%capacity)
    + dump() : front -> rear까지 출력 또는 front -> capacity-1까지 출력 후 0 -> rear 까지 출력
+ 0214 backtracking - recursive / stack (non-recursive)
  + `8-Queen` problem
    + Mystack, Point Class 이용 (non-recursive방법으로 구현)
    + SolveQueen 메서드 작성중
------
자바 자료구조 예제 (\example)
+ 0128 Chapter2 기본자료구조
+ 0205 Chapter5 재귀알고리즘
+ 0212 Chapter5 재귀알고리즘 `퀸배치`
------
자바 자료구조 연습문제 (\practice)
+ 0128 Chapter2 기본자료구조 연습문제
----------
로컬 저장소
+ local path : D:\HJ\java_test\edu\자료구조src
+ local_home : D:\Coding Practice\edu\src_DataStructures
