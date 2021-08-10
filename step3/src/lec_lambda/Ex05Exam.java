package lec_lambda;

public class Ex05Exam {
	public static void main(String[] args) {
		Tigers tigers = new Tigers();
		tigers.say();

	}
}

class Tigers {
	OnlyThree<Integer, Integer> f1 = (x, y, z) -> {
		return x + y + z;
	};
	OnlyThree<String, Integer> f2 = (x, y, z) -> {
		return x.length() + y.length() + z.length();
	};

	public void say() {
		int r1 = f1.calc(10, 20, 30); // 60
		int r2 = f2.calc("I", "Love", "You"); // 8
		System.out.println("r1= " + r1 + ", r2 = " + r2);
	}
}