package lec_lambda;

public class Ex04LambdaReturn {
	public static void main(String[] args) {
		Dinos dinos = new Dinos();
		dinos.say(1,10);
		dinos.say(5, 10);
		dinos.say(1, 100);
	}
}

class Dinos{
	// 가우스의 합 
	// OnlyTwo two = (a, b) -> {return ((a + b)*(b - a + 1))/ 2;};
	// 람다식은 하나의 추상메서드만 매칭된다. 
	OnlyTwo two = (a, b) -> ((a + b)*(b - a + 1))/ 2;
	
	public void say(int x, int y) {
		int res = two.sum(x, y);
		System.out.println(x + " ~ " + y + " sum = " + res);
	}
}
