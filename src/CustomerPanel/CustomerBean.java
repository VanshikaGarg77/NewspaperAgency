package CustomerPanel;

public class CustomerBean
{
	String mobile;
	String cname;
	String papers;
	String area;
	String dos;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getPapers() {
		return papers;
	}
	public void setPapers(String papers) {
		this.papers = papers;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getDos() {
		return dos;
	}
	public void setDos(String dos) {
		this.dos = dos;
	}
	public CustomerBean()
	{
		
	}
	public CustomerBean(String mobile, String cname, String papers, String area, String dos) {
		super();
		this.mobile = mobile;
		this.cname = cname;
		this.papers = papers;
		this.area = area;
		this.dos = dos;
	}
	
}