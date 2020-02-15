package lotteryApp1.Services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import lotteryApp1.DTO.LotteryDTO;
import lotteryApp1.DTO.LotteryStatusResponseDTO;
import lotteryApp1.Database.LotteryDBHandler;

@Service
public class LotteryService {
	List<LotteryDTO> lotteryList = new ArrayList();
	LotteryDBHandler lotteryDBhandler = new LotteryDBHandler();
	
	
	int isLotteryExist(String name) {
//		for(int i = 0;i<lotteryList.size();i++ ) {
//			if(lotteryList.get(i).getTitle().compareToIgnoreCase(name)==0) {
//				return i;
//			}
//		}
//		return -1;
		
		LotteryDTO lotteryDTO = lotteryDBhandler.isLotteryWithTitleExists(name);
		if(lotteryDTO==null) {
			return -1;
		}
		return lotteryDTO.getId();
		
	}
	
	public int isLotteryWithIDExist(int id) {
//		for(int i = 0;i<lotteryList.size();i++) {
//			if(lotteryList.get(i).getId()==id)
//				return i;
//		}
//		return -1;
		LotteryDTO lotteryDTO = lotteryDBhandler.getLotteryByID(id);
		if(lotteryDTO==null) {
			return -1;
		}
		return lotteryDTO.getId();
	}

	
	public List<LotteryDTO> getAllLotteries(){
		//return lotteryList;
		return lotteryDBhandler.getAllLotteries();
	}

	public LotteryStatusResponseDTO registerNewLottery(LotteryDTO lottery) {
//		int existId = isLotteryExist(lottery.getTitle());
//		LotteryStatusResponseDTO result = new LotteryStatusResponseDTO("Lottery Already Exists","","Fail");
//		if(existId==-1) {
//			lottery.setActiveStatus(true);
//			String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
//			lottery.setStartDate(date);
//			int id = lotteryList.size()+1;
//			lottery.setId(id);
//			LotteryDTO temp = lottery;
//			lotteryList.add(temp);
//			result.setId(Integer.toString(id));
//			result.setStatus("OK");
//			result.setReason("");
//		}
//		return result;
		
		int existId = isLotteryExist(lottery.getTitle());
		LotteryStatusResponseDTO result = new LotteryStatusResponseDTO("Lottery Already Exists","","Fail");
		if(existId==-1) {
			lottery.setActiveStatus(true);
			String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			lottery.setStartDate(date);
			
			int id = lotteryDBhandler.insertLottery(lottery);
			
			result.setId(Integer.toString(id));
			result.setStatus("OK");
			result.setReason("");
		}
	
		return result;
	}
	
	public LotteryDTO getLotteryWithID(int id) {
//		for(int i = 0;i<lotteryList.size();i++) {
//			if(lotteryList.get(i).getId()==id)
//				return lotteryList.get(i);
//		}
//		return null;
		return lotteryDBhandler.getLotteryByID(id);
	}

	public LotteryStatusResponseDTO stopRegistration(int id) {
//		int pos = isLotteryWithIDExist(id); 
//		if(pos==-1) {
//			return new LotteryStatusResponseDTO("Lottery with id "+id+" doesn't Exist","","Fail");
//		}
//		lotteryList.get(pos).setActiveStatus(false);
//		String endDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
//		lotteryList.get(pos).setEndDate(endDate);
//		
//		return new LotteryStatusResponseDTO("","","OK");
		
		int pos = isLotteryWithIDExist(id); 
		if(pos==-1) {
			return new LotteryStatusResponseDTO("Lottery with id "+id+" doesn't Exist","","Fail");
		}
		
		String endDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		lotteryDBhandler.stopRegistration(pos,endDate);
		
		return new LotteryStatusResponseDTO("","","OK");
	}

	public void setWinnerCode(int id,String winnerCode) {
//		int pos = isLotteryWithIDExist(id); 
//		if(pos!=-1) {
//			lotteryList.get(pos).setWinnerCode(winnerCode);
//		}
		
		int pos = isLotteryWithIDExist(id); 
		if(pos!=-1) {
			lotteryDBhandler.updateWinnerCode(pos, winnerCode);

		}
		
	}

	public void incrementParticipant(int id) {
//		int pos = isLotteryWithIDExist(id);
//		if(pos!=-1) 
//		{
//			lotteryList.get(pos).setParticipants(lotteryList.get(pos).getParticipants()+1);
//		}
		LotteryDTO lottery = lotteryDBhandler.getLotteryByID(id);
		if(lottery!=null) 
		{
			lotteryDBhandler.updateParticipant(lottery.getId(),lottery.getParticipants()+1);
		}
		
		
	}

	
	
	
}
