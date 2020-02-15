package lotteryApp1.DTO;

public class UserStatusDTO {
	private String status;
	private String reason;
	
	public UserStatusDTO(){
		
	}

	public UserStatusDTO(String status, String reason) {
		super();
		this.status = status;
		this.reason = reason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
}
