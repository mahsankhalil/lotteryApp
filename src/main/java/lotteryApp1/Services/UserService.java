package lotteryApp1.Services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import lotteryApp1.DTO.UserDTO;
import lotteryApp1.DTO.UserStatusDTO;
import lotteryApp1.Database.LotteryDBHandler;
import lotteryApp1.Database.UserDBHandler;

@Service
public class UserService {
	
	private List<UserDTO> userList = new ArrayList();
	LotteryDBHandler lotteryDBhandler =  new LotteryDBHandler();
	UserDBHandler userDBhandler = new UserDBHandler();
	
	public int getUserWithEmailAndCode(String email,String code) {
//		for(int i= 0;i<userList.size();i++) {
//			if(userList.get(i).getEmail().compareToIgnoreCase(email)==0 && userList.get(i).getCode().compareTo(code)==0) {
//				return i;
//			}
//		}
//		return -1;
		
		UserDTO user = userDBhandler.getUserEmail(email);
		if(user==null)
			return -1;
		
		if(lotteryDBhandler.isUserRegistered(user.getUserID(),code)) {
			return user.getUserID();
		}
		
		return -1;
	}
	
	boolean isCodeExist(String code) {
//		for(int i = 0;i<userList.size();i++) {
//			if(code.compareTo(code)==0) {
//				return true;
//			}
//		}
//		return false;
		return lotteryDBhandler.isCodeExists(code);
	}
	
	public String generateFirst8Digit(String email) {
		String code = new SimpleDateFormat("ddMMyy").format(new Date());
		int size = email.length();
		if(size<10) {
			code+="0";
		}
		code+=Integer.toString(size);
		return code;
	}
	
	String generateCode(String email) {
		String code = generateFirst8Digit(email);
		
		
		Random rnd = new Random();
		String last8DigitCode = "";
		boolean flag = true;
		while(flag) {
			int n = 10000000 + rnd.nextInt(90000000);
			last8DigitCode = Integer.toString(n);
			String temp = code + last8DigitCode;
			if(!isCodeExist(temp)) {
				flag = false;
			}
		}
		
		code+= last8DigitCode;
		
		
		
		return code;
	}
	
	int isExistUser(int id) {
//		for(int i= 0;i<userList.size();i++) {
//			if(userList.get(i).getUserID()==id) {
//				return i;
//			}
//		}
//		return -1;
		
		
		UserDTO user = userDBhandler.getUserByID(id);
		if(user==null) {
			return -1;
		}
		return user.getUserID();
		
	}
	
	int isExistUserWithEmail(String email) {
//		for(int i= 0;i<userList.size();i++) {
//			if(userList.get(i).getEmail().compareToIgnoreCase(email)==0) {
//				return i;
//			}
//		}
//		return -1;
		
		UserDTO user = userDBhandler.getUserEmail(email);
		if(user==null) {
			return -1;
		}
		return user.getUserID();
	}
	
	public UserDTO getUserWithID(int id) {
//		int position = isExistUser(id);
//		if(position!=-1) {
//			return userList.get(position);
//		}
//		return null;
		return userDBhandler.getUserByID(id);
	}
	
	public List<UserDTO> getAllUsers(){
		//return userList;
		
		return userDBhandler.getAllUsers();
	}
	
	public List<UserDTO> getAllUsersWithLotteryID(int id){
//		ArrayList<UserDTO> tempList = new ArrayList();
//		for(int i = 0;i<userList.size();i++) {
//			if(userList.get(i).getId()==id) {
//				tempList.add(userList.get(i));
//			}
//		}
//		return tempList;
		
		return userDBhandler.getAllUsersWithLottery(id);
	}
	
	public String getWinnerCode(int id) {
//		List<UserDTO> temp = getAllUsersWithLotteryID(id);
//		Random rnd = new Random();
//		int n = rnd.nextInt(temp.size()-1);
//		return temp.get(n).getCode();
		
		List<UserDTO> temp = getAllUsersWithLotteryID(id);
		if(temp!=null) {
			Random rnd = new Random();
			int n = rnd.nextInt(temp.size()-1);
			
			int user_id = temp.get(n).getUserID();
			
			return lotteryDBhandler.getCodeWithUserAndLottery(user_id, id);
		}
		
		return "";
	}
	
	public UserStatusDTO insertUser(UserDTO user) {
//		UserStatusDTO status = new UserStatusDTO("Fail","User Already Exists");
//		if(user.getAge()<21) {
//			status.setStatus("Fail");
//			status.setReason("Participation allowed only from 21 years");
//			return status;
//		}
//		if(isCodeExist(user.getCode())) {
//			return new UserStatusDTO("Fail","Code Already Exists");
//		}
//		
//		if(!isValidEmail(user.getEmail())) {
//			return new UserStatusDTO("Fail","Email is not valid");
//		}
//		
//		if(getUserWithEmailAndCode(user.getEmail(),user.getCode())==-1) {
//			int id = userList.size()+1;
//			user.setUserID(id);
//			boolean flag = isValidateCode(user.getEmail(),user.getCode());
//			if(flag) {
//				return new UserStatusDTO("Fail","Invalid Code");
//			}
////			String code = generateCode(user.getEmail());
////			user.setCode(code);			
//			userList.add(user);
//			return new UserStatusDTO("OK","");
//		}
//		return status;
		
		UserStatusDTO status = new UserStatusDTO("Fail","User Already Exists");
		if(user.getAge()<21) {
			status.setStatus("Fail");
			status.setReason("Participation allowed only from 21 years");
			return status;
		}
		if(isCodeExist(user.getCode())) {
			return new UserStatusDTO("Fail","Code Already Exists");
		}
		
		if(!isValidEmail(user.getEmail())) {
			return new UserStatusDTO("Fail","Email is not valid");
		}
		
		if(getUserWithEmailAndCode(user.getEmail(),user.getCode())==-1) {
			
			UserDTO tempUser = userDBhandler.getUserEmail(user.getEmail());
			int userId;
			if(tempUser==null) {
				boolean flag = isValidateCode(user.getEmail(),user.getCode());
				if(flag==false) {
					return new UserStatusDTO("Fail","Invalid Code");
				}
//				String code = generateCode(user.getEmail());
//				user.setCode(code);			
				//userList.add(user);
				userId = userDBhandler.insertUser(user);
			}else {
				userId = tempUser.getUserID();
			}
			
			if(userId!=0)
			{
				lotteryDBhandler.insertLotteryParticipation(userId,user.getId(),user.getCode());
				LotteryService lotteryService = new LotteryService();
				lotteryService.incrementParticipant(user.getId());
				return new UserStatusDTO("OK","");
			}
		}
		return status;
	}
	
	boolean isValidateCode(String email,String code) {
		String first8Digit = generateFirst8Digit(email);
		System.out.println(first8Digit);
		String userCode = code;
		boolean flag = true;
		for(int i = 0;i<8;i++) {
			if(userCode.charAt(i)!=first8Digit.charAt(i)) {
				flag = false;
				break;
			}
		}
		return flag;
	}
	
	boolean isValidEmail(String email) {
		if(email.contains("@") && email.length()>=6 && (email.charAt(0)<'0'|| email.charAt(0)>'9') &&email.charAt(email.length()-1)!='@' ) {
			return true;
		}
		return false;
	}
}
