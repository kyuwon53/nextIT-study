package lec_stream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

public class Ex05Work2 {
	
	static double AVG = 0.0;

	public static void main(String[] args) throws Exception {
		List<Member> members = new ArrayList<Member>();
		members.add(new Member("hong", "홍석진", "서울 중구 태평동", 1200));
		members.add(new Member("bonhack", "구본학", "서울 은평구 대치동", 500));
		members.add(new Member("soo", "정현수", "서울 서대문구 아파트", 300));
		members.add(new Member("ok", "임규옥", "서울 동작대구 현충원", 700));
		members.add(new Member("hong", "미나리", "서울 유성구 노은동", 3000));
		
		// 몇 명 있지?
		long r = 0;
		r = members.stream().count();
		System.out.println("그냥 count r =" + r);
		// 중복제거하고 
		r = members.stream().distinct().count(); // distinct : hashcode, equals 가 동일하면 true 
		System.out.println("distinct count r =" + r);
		
		members.stream().distinct().forEach(m -> System.out.println(m.getName()));
		System.out.println("-------");
		// 평균 마일리지를 출력해주세요.
		// ToIntFunction<Member> mif = m -> m.getMileage();
		 ToIntFunction<Member> mif = Member :: getMileage; // 메서드 참조 
		
		// double avg = members.stream().mapToInt(Member :: getMileage).average().getAsDouble();
		
		members.stream().mapToInt(mif).average().ifPresent(d-> {System.out.println("값이 존재합니다." + d); AVG = d;});
		
		System.out.println("평균 마일리지 : " + AVG);
		
		Stream<Member> st =  members.stream()
								.peek(e -> System.out.println(e.getName() + "님의 마일리지는 " + e.getMileage()));
		System.out.println("--- peek 수행 후 ---");
		Thread.sleep(2000);
		System.out.println("--- 시간이 흐른 후에 최종결과 처리 ---");
		long cnt = st.count();
		System.out.println("cnt= " + cnt);
		// members 의 모든 회원은 "서울" 사람인가
		boolean allSeoul = members.stream().allMatch(m -> m.getAddress().startsWith("서울"));
		if(allSeoul) {
			System.out.println("모두 서울");
		}else {
			System.out.println("모든 회원이 서울은 아닙니다. ");
		}
	}
}
