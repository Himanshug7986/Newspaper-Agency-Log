package customerDashboard;

public class cdBean {
	
	String mobile;
	String name;
	String spapers;
	String dos;
	String address;
	
	public cdBean() {}
	//===for Areas====
	public cdBean(String name, String mobile, String address, String spapers, String dos) {
		super();
		this.mobile = mobile;
		this.name = name;
		this.spapers = spapers;
		this.dos=dos;
		this.address = address;
	}
	//=========
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpapers() {
		return spapers;
	}
	public void setSpapers(String spapers) {
		this.spapers = spapers;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getDos() {
		return dos;
	}
	public void setDos(String dos) {
		this.dos = dos;
	}
	
	

}
