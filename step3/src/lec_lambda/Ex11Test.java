package lec_lambda;

import java.util.Arrays;
import java.util.List;

public class Ex11Test {
	private static int[] arr = {45,63,67,23,94,1000,34,92,45,567,876,999};
	
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(45,63,67,23,94,1000,34,92,45,567,876,999);
		
		// list : 전통적 for, for each 문 
		// JDK 8 
		list.forEach(x -> System.out.println(x));
		System.out.println("--------");
		// 짝수만 출력 
		list.stream().filter(x -> x % 2 == 0).forEach(x-> System.out.println(x));
		
	}
}
