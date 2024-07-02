package allhawkers;

public class hawkerBean {
  String name;
  String mobile;
  String adhaarno;
  String address;
  String alloareas;
  String dojoining;
public hawkerBean() {}

public hawkerBean(String name, String mobile, String adhaarno, String address, String alloareas, String dojoining) {
	
	super();
	this.name = name;
	this.mobile = mobile;
	this.adhaarno = adhaarno;
	this.address = address;
	this.alloareas = alloareas;
	this.dojoining = dojoining;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}
public String getAdhaarno() {
	return adhaarno;
}
public void setAdhaarno(String adhaarno) {
	this.adhaarno = adhaarno;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getAlloareas() {
	return alloareas;
}
public void setAlloareas(String alloareas) {
	this.alloareas = alloareas;
}
public String getDojoining() {
	return dojoining;
}
public void setDojoining(String dojoining) {
	this.dojoining = dojoining;
}
}
