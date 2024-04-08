package HawkerTable;

public class HawkerBean
{
	String hname;
	String mobile;
	String areas;
	String daj;
	public HawkerBean() {
	
	}
	public String getHname() {
		return hname;
	}
	public void setHname(String hname) {
		this.hname = hname;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAreas() {
		return areas;
	}
	public void setAreas(String areas) {
		this.areas = areas;
	}
	public String getDaj() {
		return daj;
	}
	public void setDaj(String daj) {
		this.daj = daj;
	}
	public HawkerBean(String hname, String mobile, String areas, String daj) {
		super();
		this.hname = hname;
		this.mobile = mobile;
		this.areas = areas;
		this.daj = daj;
	}
	
	
}