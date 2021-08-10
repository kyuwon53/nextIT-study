package lec_lambda;

public class Ex02NestedImpl {
	public static void main(String[] args) {
		Lion lion = new Lion();
		lion.say();
	}
}

class Lion{
	// 무명객체 생성 
	OnlyOne one = new OnlyOne() {
		@Override
		public void hello() {
			System.out.println("anonymous this = " + this.hashCode() + ", lion = " + Lion.this.hashCode());
			System.out.println("삼성 라이온즈 화이팅~!");
		}
	};
	public void say() {
		one.hello();
	}
}
