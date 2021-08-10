package lec_lambda;

public class Ex03LambdaImpl {
	public static void main(String[] args) {
		Giant giant = new Giant();
		giant.say();
	}
}

class Giant{
	// 람다식 "(매개변수) -> {실행코드}"
	OnlyOne one = ()->{ System.out.println("롯데 자이언트 화이팅 ~");
	System.out.println("lambda this = " + this.getClass().getSimpleName());
	System.out.println("lambda this = " + this.hashCode() + ", Giant = " + Giant.this.hashCode());
	};
	public void say() {
		one.hello();
	}
}
