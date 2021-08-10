package lec_lambda;

public class Ex11StudyAgain {
	
	public static void main(String[] args) {
		MyClass class1 = new MyClass();
		YouClass class2 = new YouClass();
		class1.say();
		class2.say();
	}
}

class MyClass{
	// 익명 클래스 구성 
	NumberFunction myFunction = new NumberFunction() {

		@Override
		public int maxValue(int... arr) {
			int max = arr[0];
			for(int i = 0; i < arr.length; i++) {
				if(max < arr[i]) max = arr[i];
			}
			return max;
		}
		
	} ;
	
	public void say() {
		int r = myFunction.maxValue(63, 64, 61,67,64,69,66);
		System.out.println(r);
	}
}

class YouClass{
	// 람다식 구성
	NumberFunction myFunction = arr -> {
		int max = arr[0];
		for(int i = 0; i < arr.length; i++) {
			if(max < arr[i]) max = arr[i];
		}
		return max;
	};
	
	public void say() {
		int r = myFunction.maxValue(71, 75, 73,70 ,77 ,78, 74,72,79);
		System.out.println(r);
	}
}