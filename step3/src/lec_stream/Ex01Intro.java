package lec_stream;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class Ex01Intro {
	public static void main(String[] args) {
		
		List<String> list = Arrays.asList("홍석진","임규옥", "구본학", "정현수");
		for(String s : list) {
			System.out.println(s);
		}
		System.out.println("-----------");
		System.out.println("컴파일 된 진짜 소스");
		
		Iterator var3 = list.iterator();

		while (var3.hasNext()) {
			String s = (String) var3.next();
			System.out.println(s);
		}
		System.out.println("----------");
		
		list.stream().forEach(System.out::println);
	}
}
