package lec_nested;

public class Ex01Nested {

	public static int sum(int a, int b) { // 2,6
		// 가우스의 합 (등차수열의 합)
		int r = (a + b) * (b - a + 1) / 2;
		return r;
	}

	public Ex01Nested() {
		System.out.println("Ex01Nested 생성되었습니다. ");
	}

	// 인스턴스 맴버 클래스
	class B {
		public B() {
			System.out.println("인스턴스 맴버 클래스 B 생성자 호출");
		}

		// 클래스를 생성 new()
		// B 클래스는 메인(Outer)클래스가 생성되어야 합니다.
		// 정적 맴버(변수, 메서드) 선언 불가
		int a = 5;

		// static b = 10;
		public void methodIns(String love) {
			System.out.println("methodIns call a = " + a);
			for (int i = 0; i < a; i++) {
				System.out.println(love);
			}
		}
		// public static void methodStatic() { }
	}

	static class C {
		public C() {
			System.out.println("정적클래스 생성자 입니다. ");
		}

		int a = 10;
		static int b = 10;

		public void m1() {
			System.out.println("me1 호출");
		}

		public static void st1() {
			System.out.println("st1 호출");
		}
	} // static 맴버 class
	
	// 메서드내에 있는 지역클래스에서 외부의 변수 사용시 자동으로 final 처리된다. 
	// 때문에 값 변경시 오류가 난다. 
	public void methodOuter(int arg1, int arg2) {
		int a = 10;
		a = a * a;
		// arg1 = arg1 + a;
		arg2 = arg2 + a;
		System.out.println("methodOuter Call...arg1 = " + arg1 + ", arg2 = " + arg2 + ", a = " + a);
		class LocalD {
			public LocalD() {
				System.out.println("LocalD 메서드내에 있는 클래스 생성");
			}

			int localVar = 10;

			// static int sVar = 10;
			// public static void staticMethod() {}
			public void localMethod(String str) {
				System.out.println("localMethod str = " + str + ", var = " + localVar + "arg1 = " + arg1);
			}

		} // LocalD
			// 로컬클래스는 메서드내에서는 생성(new)하고 사용가능
			// 메서드내의 지역변수는 메서드 수행시 선언되고, 메서드가 종료되면 메모리에서 사라진다.
			// 로컬클래스도 동일하다.
			// 로컬클래스의 정적맴버(변수, 메서드)는 선언불가
		LocalD d = new LocalD();
		d.localMethod("중첩 클래스를 배워보아요 ");

	}// methodOuter

	// -- test
	public static void main(String[] args) {
		Ex01Nested outer = new Ex01Nested();
		outer.methodOuter(1, 100);
		System.out.println("------------------------------");

		Ex01Nested ex = new Ex01Nested();
		Ex01Nested.B x = ex.new B();
		// Ex01Nested.B c = new Ex01Nested().new B();
		x.methodIns("쉬는 시간...");
		System.out.println("------------------------------");
		// 정적 맴버는 바로 호출
		int y = Ex01Nested.sum(1, 100);
		System.out.println(y);

		Ex01Nested.C.st1(); // 정적클래스, 정적메서드
		System.out.println(Ex01Nested.C.b); // 정적클래스, 정적필드
		// 정적클래스 생성
		Ex01Nested.C c = new Ex01Nested.C();
		c.m1();
		System.out.println(c.a);
	}

}// outer class
