package lec_stream;

import java.util.Objects;

public class Member {
	private String id;
	private String name;
	private String address;
	private int mileage;
	
	// 모든 필드의 생성자 (id, name, address, mileage)
	public Member(String id, String name, String address, int mileage) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.mileage = mileage;
	}
	
	// equals id가 같으면 동일한 회원 
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Member) {
			Member t = (Member )obj;
			if(this.id.equals(t.id))return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	// get/setter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
}
