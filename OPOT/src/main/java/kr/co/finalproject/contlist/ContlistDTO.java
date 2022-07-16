package kr.co.finalproject.contlist;

import org.springframework.web.multipart.MultipartFile;

public class ContlistDTO {
	   
	   private String mtitle;
	   private String mthum;
	   private double mrate;
	   private String netflix;
	   private String watcha;
	   private String tving;
	   private String disney;
	   private String mdate;
	   private String key_code;
	   private int cri_like;
	   private int mcode;
	   private String maudio;
	   private String director;
	   private String actor;
	   private String mtitle_eng;
	   
	   private MultipartFile mthumMF;
	   
	   public ContlistDTO() {}

	public String getMtitle() {
		return mtitle;
	}

	public void setMtitle(String mtitle) {
		this.mtitle = mtitle;
	}

	public String getMthum() {
		return mthum;
	}

	public void setMthum(String mthum) {
		this.mthum = mthum;
	}

	public double getMrate() {
		return mrate;
	}

	public void setMrate(double mrate) {
		this.mrate = mrate;
	}

	public String getNetflix() {
		return netflix;
	}

	public void setNetflix(String netflix) {
		this.netflix = netflix;
	}

	public String getWatcha() {
		return watcha;
	}

	public void setWatcha(String watcha) {
		this.watcha = watcha;
	}

	public String getTving() {
		return tving;
	}

	public void setTving(String tving) {
		this.tving = tving;
	}



	public String getDisney() {
		return disney;
	}

	public void setDisney(String disney) {
		this.disney = disney;
	}

	public String getMdate() {
		return mdate;
	}

	public void setMdate(String mdate) {
		this.mdate = mdate;
	}

	public String getKey_code() {
		return key_code;
	}

	public void setKey_code(String key_code) {
		this.key_code = key_code;
	}

	public int getCri_like() {
		return cri_like;
	}

	public void setCri_like(int cri_like) {
		this.cri_like = cri_like;
	}

	public int getMcode() {
		return mcode;
	}

	public void setMcode(int mcode) {
		this.mcode = mcode;
	}

	public String getMaudio() {
		return maudio;
	}

	public void setMaudio(String maudio) {
		this.maudio = maudio;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}
	
	public String getMtitle_eng() {
		return mtitle_eng;
	}

	public void setMtitle_eng(String mtitle_eng) {
		this.mtitle_eng = mtitle_eng;
	}

	public MultipartFile getMthumMF() {
		return mthumMF;
	}

	public void setMthumMF(MultipartFile mthumMF) {
		this.mthumMF = mthumMF;
	}
	
	
	
	
	@Override
	public String toString() {
		return "ContlistDTO [mtitle=" + mtitle + ", mthum=" + mthum + ", mrate=" + mrate + ", netflix=" + netflix
				+ ", watcha=" + watcha + ", tving=" + tving + ", disney=" + disney + ", mdate=" + mdate + ", key_code="
				+ key_code + ", cri_like=" + cri_like + ", mcode=" + mcode + ", maudio=" + maudio + ", director="
				+ director + ", actor=" + actor + ", mthumMF=" + mthumMF + "]";
	}


	
	

	   
	   
	   

	   
	   
}//class end