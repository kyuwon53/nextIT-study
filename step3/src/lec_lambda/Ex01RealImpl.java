package lec_lambda;

public class Ex01RealImpl {
	public static void main(String[] args) {
		Eagles eagles = new Eagles();
		eagles.say();
	}
}

class Eagles{
	OnlyOne one = new EaglesOnlyOne();
	
	public void say() {
		one.hello();
	}
}

class EaglesOnlyOne implements OnlyOne{
	@Override
	public void hello() {
		System.out.println("이글스 화이팅! ");
	}
}
