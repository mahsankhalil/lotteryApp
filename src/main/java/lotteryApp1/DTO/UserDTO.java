package lotteryApp1.DTO;

public class UserDTO {
	int userID;
	String email;
	int age;
	String code;
	int id;
	
	public UserDTO() {
		
	}

	public UserDTO(int userID, String email, int age, String code, int lotteryID) {
		super();
		this.userID = userID;
		this.email = email;
		this.age = age;
		this.code = code;
		this.id = lotteryID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getId() {
		return id;
	}

	public void setId(int lotteryID) {
		this.id = lotteryID;
	}
	
	
}
