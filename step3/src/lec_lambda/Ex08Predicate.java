package lec_lambda;

import java.util.function.BiPredicate;

public class Ex08Predicate {
	
	private static int[] arr = {45,63,67,23,94,1000,34,92,45,567,876,999};
	
	public static void main(String[] args) {
//		BiPredicate<Integer, Integer> max = (x, y) -> {return x > y; };
//		maxPrint(max);
		maxPrint((x,y) -> x > y);
	}
	
	public static void maxPrint(BiPredicate<Integer, Integer> bi) {
	 	for(int i = 0; i < arr.length -1 ; i++) {
	 		if(bi.test(arr[i], arr[i+1])) {
	 			System.out.println(arr[i] + "가 " + arr[i+1] + " 보다 큽니다. ");
	 		}
	 	}
	}
}
