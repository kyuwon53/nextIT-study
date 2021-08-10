package lec_lambda;

import java.util.function.Function;

public class Ex12FunctionCompose {
	
	// 문자열을 받아서 길이를 반환 
	// 숫자를 받아서 3으로 나눈 나머지 반환
	
	Function<String, Integer> lenFn = s -> s.length();
	Function<Integer, Integer> modFn = i -> i % 3;
	Function<String, Integer> myFn = lenFn.andThen(modFn);
	Function<String, Integer> myFn2 = modFn.compose(lenFn);
	
	public static void main(String[] args) {
		
		Ex12FunctionCompose myclass = new Ex12FunctionCompose();
		String str = "포카리 스웨트 비싸요";
		int len = myclass.lenFn.apply(str);
		System.out.println( "len = " + len);
		int m = myclass.modFn.apply(len);
		System.out.println("m = " + m);
		System.out.println("--------------");
//		int mo = myclass.lenFn.andThen(myclass.modFn).apply(str);
		int mo = myclass.myFn.apply(str);
		System.out.println("mo = " + mo);
		System.out.println("--------------");
		
		int mo2 = myclass.myFn2.apply(str);
		System.out.println("mo2= " + mo2);
	}
}
