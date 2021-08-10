package lec_lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

public class Ex06Consumer {
	public static void main(String[] args) {

		love("밀키스", 3);
		BiConsumer<String, Integer> luv = (s, l) -> {
			for (int i = 0; i < l; i++) {
				System.out.println(i + "만큼 " + s + "사랑해요~");
			}
		};
		luv.accept("독도새우 ", 5);
		System.out.println("---------------------");
		loves(luv);
	}
	
	public static void loves(BiConsumer<String, Integer> bi) {
		List<String> names = Arrays.asList("한수진","이옥득","이규원","김다나");
		for(int i = 0; i < names.size(); i++) {
			bi.accept(names.get(i), i+1);
		}
	}

	// 문자, 숫자
	public static void love(String str, int cnt) {
		for (int i = 0; i < cnt; i++) {
			System.out.println(i + "만큼 " + str + "사랑해요~");
		}
	}
}
