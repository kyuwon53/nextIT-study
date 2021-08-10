package lec_nested;

import lec_nested.Tv.Button;

public class SamsungTV extends Television {

	private int volume = 0;
	private Button button = new Tv.Button() {
		
		@Override
		public void onDbClick() {
			System.out.println("버튼 더블클릭 이벤트 발생");
		}
		
		@Override
		public void onClick() {
			System.out.println("버튼 클릭 이벤트 발생");
		}
	}; 
	
	public void touch() {
		button.onClick();
	}
	
		//SamsungRemoteController 라는 클래스 이름이 존재 
		//protected RemoteControl rc = new SamsungRemoteController();
	
	// RemoteControl 구현한 Anonymous class (익명, 무명 클래스)
	protected RemoteControl rc = new RemoteControl() {
		private Television device = null;
		@Override
		public void on() {
			System.out.print("Remote : ");
			device.powerOn();
		}

		@Override
		public void off() {
			System.out.print("Remote : ");
			device.powerOff();
		}

		@Override
		public void up() {
			System.out.print("Remote : ");
			device.volumeUp();
		}

		@Override
		public void down() {
			System.out.print("Remote : ");
			device.volumeDown();
		}

		@Override
		public void setDevice(Television device) {
			this.device = device;
		}
	};
	
	public SamsungTV(String company, String model, int price) {
		super(company, model, price);
		rc.setDevice(this);  // 현재 TV 객체를 rc에 지정
	}
	
	@Override
	public void powerOn() {
		System.out.println( company  + " TV의 전원을 켜요..");
	}

	@Override
	public void powerOff() {
		System.out.println( company  + " TV의 전원을 끕니다. 안녕히 주무세요");
	}

	@Override
	public void volumeUp() {
		volume++;
		System.out.println( company  + " volume : " + volume);
	}

	@Override
	public void volumeDown() {
		volume--;
		System.out.println( company  + " volume : " + volume);
	}
	
	public RemoteControl getRemoteControl() {
		return rc;
	}

	
}
