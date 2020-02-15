package lotteryApp1.DTO;

public class LotteryStatusResponseDTO {
	private String reason;
	private String id;
	private String status;
	
	public LotteryStatusResponseDTO(){
		
	}
	
	

	public LotteryStatusResponseDTO(String reason, String id, String status) {
		super();
		this.reason = reason;
		this.id = id;
		this.status = status;
	}



	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
