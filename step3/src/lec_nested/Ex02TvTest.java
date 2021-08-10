package lec_nested;

public class Ex02TvTest {
	public static void main(String[] args) {
		SamsungTV tv = new SamsungTV("Samsung", "smg-0001", 1500000);
		RemoteControl rc = tv.getRemoteControl();
		rc.on();
		rc.up();
		tv.info();
		rc.off();
		tv.touch();
		rc.off();
		
	}
}
