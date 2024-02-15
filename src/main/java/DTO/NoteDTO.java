package DTO;

public class NoteDTO {
	private int noteno;
	private String content;
	private String wrdate;
	private String member_mid;
	//수신자
	private String recipients;
	private String reMbti;
	
	
	public String getReMbti() {
		return reMbti;
	}
	public void setReMbti(String reMbti) {
		this.reMbti = reMbti;
	}
	public int getNoteno() {
		return noteno;
	}
	public void setNoteno(int noteno) {
		this.noteno = noteno;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWrdate() {
		return wrdate;
	}
	public void setWrdate(String wrdate) {
		this.wrdate = wrdate;
	}
	public String getMember_mid() {
		return member_mid;
	}
	public void setMember_mid(String member_mid) {
		this.member_mid = member_mid;
	}
	public String getRecipients() {
		return recipients;
	}
	public void setRecipients(String recipients) {
		this.recipients = recipients;
	}

}
