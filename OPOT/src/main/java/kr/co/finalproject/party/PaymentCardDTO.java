package kr.co.finalproject.party;

public class PaymentCardDTO {

	String mem_id;	
	String card_exp;
	String card_no;
	int card_pw;
	String card_com;
	
	public PaymentCardDTO() {}
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getCard_exp() {
		return card_exp;
	}
	public void setCard_exp(String card_exp) {
		this.card_exp = card_exp;
	}
	public String getCard_no() {
		return card_no;
	}
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}
	public int getCard_pw() {
		return card_pw;
	}
	public void setCard_pw(int card_pw) {
		this.card_pw = card_pw;
	}
	public String getCard_com() {
		return card_com;
	}
	public void setCard_com(String card_com) {
		this.card_com = card_com;
	}


}//class end