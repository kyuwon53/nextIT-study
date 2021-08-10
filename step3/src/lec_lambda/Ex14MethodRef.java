package lec_lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;

public class Ex14MethodRef {
	public static void main(String[] args) {
		
		BinaryOperator<Integer> bi = (a, b) -> Math.max(a, b);
		
		int r = bi.apply(45, 68);
		System.out.println(" max = " + r);
		
		bi = Math::max;
		r = bi.apply(1004, 999);
		System.out.println(" max = " + r);
		
		List<String> list = Arrays.asList("홍석진","임규옥", "구본학", "정현수");
		list.forEach(a -> System.out.println(a) );
		System.out.println("-----------------");
		// 위 식을 메서드참조 식으로 변경해 보세요. 
		list.forEach(System.out::println);
	}
}
