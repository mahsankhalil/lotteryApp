package lotteryApp1.DTO;

public class WinnerStatusDTO {
	private String status;
	private String winnerCode;
	
	public WinnerStatusDTO() {}
	
	
	
	public WinnerStatusDTO(String status, String winnerCode) {
		super();
		this.status = status;
		this.winnerCode = winnerCode;
	}



	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getWinnerCode() {
		return winnerCode;
	}
	public void setWinnerCode(String winnerCode) {
		this.winnerCode = winnerCode;
	}
	
}
