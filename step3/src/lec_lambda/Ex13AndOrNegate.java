package lec_lambda;

import java.util.function.Predicate;

public class Ex13AndOrNegate {
	public static void main(String[] args) {
		
		// 문자열에서 안도훈이 있으면 참
		Predicate<String> dohoon = (s) ->  s.indexOf("안도훈") > -1;
		Predicate<String> hong = (s) ->  s.indexOf("홍석진") > -1;
		String str = "홍석진씨 짝꿍의 이름은 임규옥, 구본학, 정현수";
		// 문자열에 "안도훈"
		boolean r = dohoon.test(str);
		System.out.println("r= "+ r);
		// 문자열에 "홍석진"
		r = hong.test(str);
		System.out.println("r= "+ r);
		
		// 문자열에 "안도훈", "홍석진"
		r = dohoon.and(hong).test(str);
		System.out.println("r= "+ r);
		
		r = dohoon.or(hong).test(str);
		System.out.println("r= "+ r);
	}
}
