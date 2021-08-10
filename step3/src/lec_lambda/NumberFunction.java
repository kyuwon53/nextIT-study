package lec_lambda;

//함수적 인터페이스 : 추상메서드가 오직 한개인 인터페이스
@FunctionalInterface
public interface NumberFunction {

	/**
	 * <b style= " color: red;"> 여러 입력된 숫자중 가장 큰 값을 리턴</b><br>
	 * <code>int result = maxValue(3,4,5,6) </code>
	 * <p> 위 결과 result 에는 <b>6</b>이 됩니다. 
	 * @param values
	 * @return 가장 큰 정수 
	 */
	public abstract int maxValue(int ... a); // 동적매개변수는 배열 
}
