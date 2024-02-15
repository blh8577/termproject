package DTO;

public class CommentsDTO {
	private int commentno;
	private String content;
	//대댓글 주인 번호
	private int mgrno;
	//추천수
	private int recommendation;
	//반대수
	private int opposition;
	private String viewcheck;
	private int board_boardno;
	private String member_mid;
	private int level;
	private String wrdate;
	private String mbti_idx;
	
	
	public String getMbti_idx() {
		return mbti_idx;
	}
	public void setMbti_idx(String mbti_idx) {
		this.mbti_idx = mbti_idx;
	}
	public String getWrdate() {
		return wrdate;
	}
	public void setWrdate(String wrdate) {
		this.wrdate = wrdate;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getCommentno() {
		return commentno;
	}
	public void setCommentno(int commentno) {
		this.commentno = commentno;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getMgrno() {
		return mgrno;
	}
	public void setMgrno(int mgrno) {
		this.mgrno = mgrno;
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
	public String getViewcheck() {
		return viewcheck;
	}
	public void setViewcheck(String viewcheck) {
		this.viewcheck = viewcheck;
	}
	public int getBoard_boardno() {
		return board_boardno;
	}
	public void setBoard_boardno(int board_boardno) {
		this.board_boardno = board_boardno;
	}
	public String getMember_mid() {
		return member_mid;
	}
	public void setMember_mid(String member_mid) {
		this.member_mid = member_mid;
	}
}
