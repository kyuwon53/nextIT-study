package lec_lambda;

import java.util.function.BiConsumer;

public class Ex09AndThen {
	public static void main(String[] args) {
		BiConsumer<String, Integer> luv = (s, l) -> {
			for (int i = 0; i < l; i++) {
				System.out.println(i + "만큼 " + s + "사랑해요~");
			}
		};

		BiConsumer<String, Integer> bi = (a, b) -> {
			String str = "";
			for (int i = 0; i < b; i++) {
				str += a;
			}
			System.out.println("결과= " + str);
		};
		luv.andThen(bi).accept("독도새우 ", 5);
		System.out.println("---------------------");
	}
}
