package kr.co.finalproject.member;

public class SubscribeInfoDTO {

    private String subscribe_no;
    private String mem_id;
    private String party_id;
    private String party_role;
    private String subscribe_start;
    private String subscribe_end;
	private String ott_name;
    private int ott_price;
    private String ott_id;
    private String ott_pw;

	
    public SubscribeInfoDTO () {}

	public String getSubscribe_no() {
		return subscribe_no;
	}

	public void setSubscribe_no(String subscribe_no) {
		this.subscribe_no = subscribe_no;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}



	public String getParty_id() {
		return party_id;
	}

	public void setParty_id(String party_id) {
		this.party_id = party_id;
	}

	public String getParty_role() {
		return party_role;
	}

	public void setParty_role(String party_role) {
		this.party_role = party_role;
	}

	public String getSubscribe_start() {
		return subscribe_start;
	}

	public void setSubscribe_start(String subscribe_start) {
		this.subscribe_start = subscribe_start;
	}

	public String getSubscribe_end() {
		return subscribe_end;
	}

	public void setSubscribe_end(String subscribe_end) {
		this.subscribe_end = subscribe_end;
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

	
	
	
	
	@Override
	public String toString() {
		return "SubscribeInfoDTO [subscribe_no=" + subscribe_no + ", mem_id=" + mem_id + ", party_id=" + party_id
				+ ", party_role=" + party_role + ", subscribe_start=" + subscribe_start + ", subscribe_end="
				+ subscribe_end + "]";
	}

    
    	
	
	
	
}//class end
