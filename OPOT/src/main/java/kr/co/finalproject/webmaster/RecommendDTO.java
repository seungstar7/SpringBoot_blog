package kr.co.finalproject.webmaster;

import org.springframework.web.multipart.MultipartFile;

public class RecommendDTO {

	private int t_num;
	private int r_num;
	private String r_title;
	private String r_date;
	private String r_content;
	private String r_photo;
	private String mcodes;

	private MultipartFile r_photoMF;

	
	public RecommendDTO() {}

	public int getT_num() {
		return t_num;
	}

	public void setT_num(int t_num) {
		this.t_num = t_num;
	}

	public int getR_num() {
		return r_num;
	}

	public void setR_num(int r_num) {
		this.r_num = r_num;
	}

	public String getR_title() {
		return r_title;
	}

	public void setR_title(String r_title) {
		this.r_title = r_title;
	}

	public String getR_date() {
		return r_date;
	}

	public void setR_date(String r_date) {
		this.r_date = r_date;
	}

	public String getR_content() {
		return r_content;
	}

	public void setR_content(String r_content) {
		this.r_content = r_content;
	}

	public String getR_photo() {
		return r_photo;
	}

	public void setR_photo(String r_photo) {
		this.r_photo = r_photo;
	}

	public String getMcodes() {
		return mcodes;
	}

	public void setMcodes(String mcodes) {
		this.mcodes = mcodes;
	}

	public MultipartFile getR_photoMF() {
		return r_photoMF;
	}

	public void setR_photoMF(MultipartFile r_photoMF) {
		this.r_photoMF = r_photoMF;
	}

	@Override
	public String toString() {
		return "RecommedDTO [t_num=" + t_num + ", r_num=" + r_num + ", r_title=" + r_title + ", r_date=" + r_date
				+ ", r_content=" + r_content + ", r_photo=" + r_photo + ", mcodes=" + mcodes + ", r_photoMF="
				+ r_photoMF + "]";
	}


	
	
	
	
	
	
}//class end
