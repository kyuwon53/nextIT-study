package lec_stream;

import java.util.Arrays;
import java.util.function.IntPredicate;

public class Ex04Work {
	
	public static void main(String[] args) {
		int arr[] = {23, 122, 566, 233, 243 ,1,2,3, 5465,67,78,23,5,90,233,600};
		// 100보다 크고
		// 짝수이고 
		// 합 (또는 평균, 최대 , 최소 등)
		// Arrays.stream(arr)  // Arrays.stream 매개변수 타입 별로 IntStream, LongStream, ...
		// 	       .filter(람다식) 결과가 참인 것만 스트림 생성
		IntPredicate gt100 = (x) -> {return x>100;}; // 결과 boolean
		IntPredicate even = x ->  x % 2 == 0;
		
		
		double avgResult = Arrays.stream(arr).filter(gt100).filter(even).average().getAsDouble();
		int maxResult = Arrays.stream(arr).filter(gt100).filter(even).max().getAsInt();
		int minResult = Arrays.stream(arr).filter(gt100).filter(even).min().getAsInt();
		int sumResult = Arrays.stream(arr).filter(gt100).filter(even).sum();
		long cntResult = Arrays.stream(arr).filter(gt100).filter(even).count();
		
		System.out.println("average : " + avgResult );
		System.out.println("max : " + maxResult);
		System.out.println("min : " + minResult);
		System.out.println("sum : " + sumResult);
		System.out.println("conunt : " + cntResult);
	}
}
