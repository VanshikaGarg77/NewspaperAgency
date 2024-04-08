package BillBoard;

public class BillBean
{
	String mobile;
	String datefrom;
	String dateto;
	String amount;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getDatefrom() {
		return datefrom;
	}
	public void setDatefrom(String datefrom) {
		this.datefrom = datefrom;
	}
	public String getDateto() {
		return dateto;
	}
	public void setDateto(String dateto) {
		this.dateto = dateto;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public BillBean()
	{
		
	}
	public BillBean(String mobile, String datefrom, String dateto, String amount) {
		super();
		this.mobile = mobile;
		this.datefrom = datefrom;
		this.dateto = dateto;
		this.amount = amount;
	}
	
}