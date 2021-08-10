package lec_nested;

@Deprecated
public class SamsungRemoteController implements RemoteControl {

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

	
	
}
