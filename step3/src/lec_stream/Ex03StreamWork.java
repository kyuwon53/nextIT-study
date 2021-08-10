package lec_stream;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Ex03StreamWork {
	// 숫자배열 100보다 작은 수 -> 짝수를 구하고 -> 합, 평균
	public static void main(String[] args) {
		int[] arr = new int[1000];
		for (int i = 0; i < 1000; i++) {
			// 1 부터 300 사이의 랜덤한 값 담아주세요
			arr[i] = (int) (Math.random() * 300 + 1);
		}
		//
		for (int i = 0; i < 10; i++) {
			System.out.print(arr[i] + ", ");
		}
		System.out.print("...");
		for (int i = 0; i < 10; i++) {
			System.out.print(arr[990 + i] + ", ");
		}
		System.out.println();

		IntStream st = Arrays.stream(arr).filter(x -> x < 100).filter(x -> x % 2 == 0);

		// 최종 단계인 반복, 집계 등 하고 나면 스트림이 닫힘
		// st.forEach(e -> System.out.println(e + ", "));
		int r = st.sum();
		System.out.println("결과 r = " + r);
		System.out.println();
	}
}
