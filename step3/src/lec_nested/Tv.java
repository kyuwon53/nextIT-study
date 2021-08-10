package lec_nested;

public class Tv {

	interface Button{
		void onClick();
		void onDbClick();
	};
	
	interface Panel{
		void onClick();
		void onDrag();
	}
}
