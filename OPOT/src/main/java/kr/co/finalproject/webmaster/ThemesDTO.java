package kr.co.finalproject.webmaster;

import org.springframework.web.multipart.MultipartFile;

public class ThemesDTO {

	private int t_num;
	private String t_title;
	private String t_photo;
	
	private MultipartFile t_photoMF;
	
	public ThemesDTO () {}

	public int getT_num() {
		return t_num;
	}

	public void setT_num(int t_num) {
		this.t_num = t_num;
	}

	public String getT_title() {
		return t_title;
	}

	public void setT_title(String t_title) {
		this.t_title = t_title;
	}

	public String getT_photo() {
		return t_photo;
	}

	public void setT_photo(String t_photo) {
		this.t_photo = t_photo;
	}

	public MultipartFile getT_photoMF() {
		return t_photoMF;
	}

	public void setT_photoMF(MultipartFile t_photoMF) {
		this.t_photoMF = t_photoMF;
	}

	@Override
	public String toString() {
		return "ThemesDTO [t_num=" + t_num + ", t_title=" + t_title + ", t_photo=" + t_photo + ", t_photoMF="
				+ t_photoMF + "]";
	}
	
	
	
	
	
	
}//class end
