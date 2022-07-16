package kr.co.finalproject.party;

public class PartyMemberDTO {

    private String mem_id;
    private String party_id;
    private int party_pcost;
    private String party_pdate;
    private String party_ordnumber;
	
    public PartyMemberDTO() {}

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

	public int getParty_pcost() {
		return party_pcost;
	}

	public void setParty_pcost(int party_pcost) {
		this.party_pcost = party_pcost;
	}

	public String getParty_pdate() {
		return party_pdate;
	}

	public void setParty_pdate(String party_pdate) {
		this.party_pdate = party_pdate;
	}



	public String getParty_ordnumber() {
		return party_ordnumber;
	}

	public void setParty_ordnumber(String party_ordnumber) {
		this.party_ordnumber = party_ordnumber;
	}

	@Override
	public String toString() {
		return "PartyMemberDTO [mem_id=" + mem_id + ", party_id=" + party_id + ", party_pcost=" + party_pcost
				+ ", party_pdate=" + party_pdate + ", party_ordnumber=" + party_ordnumber + "]";
	}


    
    
    
    
    
	
}
