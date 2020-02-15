package lotteryApp1.DTO;

public class LotteryDTO {
	private String title;
	private int limit;
	private String startDate;
	private String endDate;
	private boolean activeStatus;
	private int participants;
	private int id;
	private String winnerCode;
	
	public LotteryDTO() {
		
	}
	

	public LotteryDTO(int lotteryId,String lotteryTitle, int maxLimit, String startDate, String endDate, boolean activeStatus,int participants) {
		super();
		this.title = lotteryTitle;
		this.limit = maxLimit;
		this.startDate = startDate;
		this.endDate = endDate;
		this.activeStatus = activeStatus;
		this.participants = participants;
		this.id = lotteryId;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String lotteryTitle) {
		this.title = lotteryTitle;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int maxLimit) {
		this.limit = maxLimit;
	}



	public String getStartDate() {
		return startDate;
	}



	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}



	public boolean isActiveStatus() {
		return activeStatus;
	}



	public void setActiveStatus(boolean activeStatus) {
		this.activeStatus = activeStatus;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public int getParticipants() {
		return participants;
	}


	public void setParticipants(int participants) {
		this.participants = participants;
	}


	public int getId() {
		return id;
	}


	public void setId(int lotteryId) {
		this.id = lotteryId;
	}


	public String getWinnerCode() {
		return winnerCode;
	}


	public void setWinnerCode(String winnerCode) {
		this.winnerCode = winnerCode;
	}
	
	
	
	
}
