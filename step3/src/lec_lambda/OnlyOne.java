package lec_lambda;

public interface OnlyOne {
	//public abstract 자동으로 붙는다. 
	void hello(); // 반환이 없고, 매개변수도 없다. 
}

@FunctionalInterface
interface OnlyTwo{
	// 매개변수 2개, 리턴이 숫자 
	public abstract int sum(int a , int b);
	
//	public abstract int max(int a , int b);
	
}

@FunctionalInterface
interface OnlyThree <T, R> {
	 R calc(T a, T b, T c); 
}

// 