package kr.co.finalproject.party;

public class PartyInfoDTO {

	private String party_id;
	private String mem_id;
	private String ott_name;
    private int ott_price;
    private String ott_id;
    private String ott_pw;
    private String ott_cdate;
    private String bank_name;
    private String bank_account;
    private int payback_amount;	
    private String payback_result;
    private int matching_no;
	
    public PartyInfoDTO() {}


	public String getParty_id() {
		return party_id;
	}


	public void setParty_id(String party_id) {
		this.party_id = party_id;
	}


	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getOtt_name() {
		return ott_name;
	}

	public void setOtt_name(String ott_name) {
		this.ott_name = ott_name;
	}

	public int getOtt_price() {
		return ott_price;
	}

	public void setOtt_price(int ott_price) {
		this.ott_price = ott_price;
	}

	public String getOtt_id() {
		return ott_id;
	}

	public void setOtt_id(String ott_id) {
		this.ott_id = ott_id;
	}

	public String getOtt_pw() {
		return ott_pw;
	}

	public void setOtt_pw(String ott_pw) {
		this.ott_pw = ott_pw;
	}

	public String getOtt_cdate() {
		return ott_cdate;
	}

	public void setOtt_cdate(String ott_cdate) {
		this.ott_cdate = ott_cdate;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getBank_account() {
		return bank_account;
	}

	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}

	public int getPayback_amount() {
		return payback_amount;
	}

	public void setPayback_amount(int payback_amount) {
		this.payback_amount = payback_amount;
	}

	public String getPayback_result() {
		return payback_result;
	}

	public void setPayback_result(String payback_result) {
		this.payback_result = payback_result;
	}

	public int getMatching_no() {
		return matching_no;
	}

	public void setMatching_no(int matching_no) {
		this.matching_no = matching_no;
	}

	@Override
	public String toString() {
		return "PartyInfoDTO [party_id=" + party_id + ", mem_id=" + mem_id + ", ott_name=" + ott_name + ", ott_price="
				+ ott_price + ", ott_id=" + ott_id + ", ott_pw=" + ott_pw + ", ott_cdate=" + ott_cdate + ", bank_name="
				+ bank_name + ", bank_account=" + bank_account + ", payback_amount=" + payback_amount
				+ ", payback_result=" + payback_result + ", matching_no=" + matching_no + "]";
	}
	
    
	
}//class end
