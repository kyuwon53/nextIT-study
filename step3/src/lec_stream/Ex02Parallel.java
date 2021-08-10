package lec_stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Ex02Parallel {
	public static void main(String[] args) {
		Ex02Parallel myClass = new Ex02Parallel();
		// 쓰레드가 별도로 생성되지 않으면 한개의 쓰레드만 존재
		System.out.println("Thread Name = " + Thread.currentThread().getName());
		DoHoon.print("안녕");
		List<String> list = Arrays.asList("홍석진", "임규옥", "구본학", "정현수", "안도훈", "오민영");
		
		//순차적인 스트리림
		Stream<String> st1 = list.stream();
		st1.forEach( s -> DoHoon.print( s)); // 
		System.out.println("----");
		
		// 병렬처리 스트림 
		Stream<String> st2 = list.parallelStream();
		st2.forEach( DoHoon::print); // 
		System.out.println("----");
		
		
	}
}

class DoHoon{
	
	public static void print(String str) {
		System.out.println("Thread Name = " + Thread.currentThread().getName() + ", str= " + str);
	}
}
