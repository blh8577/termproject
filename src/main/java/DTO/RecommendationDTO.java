package DTO;

public class RecommendationDTO {
	private int recommendationno;
	private int board_bordno;
	private String member_mid;
	//게시판인지 댓글인지 확인
	private String boardcheck;
	//게시판인지 댓글인지 확인하고 주인 찾기
	private int owner;
	public int getOppno() {
		return recommendationno;
	}
	public void setOppno(int recommendationno) {
		this.recommendationno = recommendationno;
	}
	public int getBoard_bordno() {
		return board_bordno;
	}
	public void setBoard_bordno(int board_bordno) {
		this.board_bordno = board_bordno;
	}
	public String getMember_mid() {
		return member_mid;
	}
	public void setMember_mid(String member_mid) {
		this.member_mid = member_mid;
	}
	public String getBoardcheck() {
		return boardcheck;
	}
	public void setBoardcheck(String boardcheck) {
		this.boardcheck = boardcheck;
	}
	public int getOwner() {
		return owner;
	}
	public void setOwner(int owner) {
		this.owner = owner;
	}
}


