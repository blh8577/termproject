package DTO;

public class BoardDTO {
		
	//번호
	private int boardno;
	//작성일
	private String wrdate;
	//제목
	private String title;
	//넌 뭐냐?
	private String boardcol;
	//조회수
	private int count;
	//삭제여부
	private String viewcheck;
	//카테고리
	private int category;
	//추천수
	private int recommendation;
	//반대수
	private int opposition;
	//작성자
	private String member_mid;
	//작성자mbti
	private String mbti_idx;
	//내용
	private String content;
	
	public int getBoardno() {
		return boardno;
	}
	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}
	public String getWrdate() {
		return wrdate;
	}
	public void setWrdate(String wrdate) {
		this.wrdate = wrdate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBoardcol() {
		return boardcol;
	}
	public void setBoardcol(String boardcol) {
		this.boardcol = boardcol;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getViewcheck() {
		return viewcheck;
	}
	public void setViewcheck(String viewcheck) {
		this.viewcheck = viewcheck;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public int getRecommendation() {
		return recommendation;
	}
	public void setRecommendation(int recommendation) {
		this.recommendation = recommendation;
	}
	public int getOpposition() {
		return opposition;
	}
	public void setOpposition(int opposition) {
		this.opposition = opposition;
	}
	public String getMember_mid() {
		return member_mid;
	}
	public void setMember_mid(String member_mid) {
		this.member_mid = member_mid;
	}
	public String getMbti_idx() {
		return mbti_idx;
	}
	public void setMbti_idx(String mbti_idx) {
		this.mbti_idx = mbti_idx;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	
}
