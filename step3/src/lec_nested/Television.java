package lec_nested;

public abstract class Television {
	protected String company;
	protected String model;
	protected int price;
	
	public Television(String company, String model, int price) {
		this.company = company;
		this.model = model;
		this.price = price;		
	}
	 
  public abstract void powerOn(); 
  public abstract void powerOff();
  public abstract void volumeUp();
  public abstract void volumeDown();
  public void info() {  	
  	System.out.println("--------------");
  	System.out.println("Company  : " + company);
  	System.out.println("Model No : " + model);
  	System.out.println("Serial No : " + this.hashCode());
  	System.out.printf("Price : %,d \n" , price);
  };
}
