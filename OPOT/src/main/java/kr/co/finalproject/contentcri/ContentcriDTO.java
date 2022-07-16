package kr.co.finalproject.contentcri;

public class ContentcriDTO {
	private int mcode;
	private int cri_like;
	private int cri_watch;
	private int cri_point;
	private String mem_id;
	private int cri_code;

	public ContentcriDTO() {}

	public int getMcode() {
		return mcode;
	}
	public void setMcode(int mcode) {
		this.mcode = mcode;
	}
	public int getCri_like() {
		return cri_like;
	}
	public void setCri_like(int cri_like) {
		this.cri_like = cri_like;
	}
	public int getCri_watch() {
		return cri_watch;
	}
	public void setCri_watch(int cri_watch) {
		this.cri_watch = cri_watch;
	}
	public int getCri_point() {
		return cri_point;
	}
	public void setCri_point(int cri_point) {
		this.cri_point = cri_point;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public int getCri_code() {
		return cri_code;
	}
	public void setCri_code(int cri_code) {
		this.cri_code = cri_code;
	}

	@Override
	public String toString() {
		return "ContentcriDTO [mcode=" + mcode + ", cri_like=" + cri_like + ", cri_watch=" + cri_watch + ", cri_point="
				+ cri_point + ", mem_id=" + mem_id + ", cri_code=" + cri_code + "]";
	}

	
}