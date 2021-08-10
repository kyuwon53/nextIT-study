package lec_lambda;

import java.time.LocalDate;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class Ex07Supplier {
	public static void main(String[] args) {
		Supplier<String> today = () -> {
			LocalDate ld = LocalDate.now();
			return ld.toString();
		};

		String re = today.get();
		System.out.println(re);

		BiFunction<String, Integer, String> bi = (a, b) -> {
			String str = "";
			for(int i =0; i< b; i++) {
				str += a;
			}
			return str;
		};
		String r = bi.apply("Abc", 3); // r = "AbcAbcAbc"
		System.out.println("r= " + r);
	}
}
