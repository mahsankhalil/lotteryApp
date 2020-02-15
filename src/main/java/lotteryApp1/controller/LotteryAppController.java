package lotteryApp1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lotteryApp1.DTO.LotteryDTO;
import lotteryApp1.DTO.LotteryStatusResponseDTO;
import lotteryApp1.DTO.UserDTO;
import lotteryApp1.DTO.UserStatusDTO;
import lotteryApp1.DTO.WinnerStatusDTO;
import lotteryApp1.Services.LotteryService;
import lotteryApp1.Services.UserService;


@RestController
public class LotteryAppController {
	
	@Autowired
	LotteryService lotteryService;
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping("/stats")
	public List<LotteryDTO> getLotterywithID() {
		return lotteryService.getAllLotteries() ;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/start-registration")
	public LotteryStatusResponseDTO startRegistration(@RequestBody LotteryDTO lottery)
	{
		return lotteryService.registerNewLottery(lottery);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/stop-registration")
	public LotteryStatusResponseDTO stopRegistration(@RequestBody LotteryDTO lottery)
	{
		
		return lotteryService.stopRegistration(lottery.getId());
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/choose-winner")
	public WinnerStatusDTO chooseWinner(@RequestBody LotteryDTO lottery)
	{
		
		int pos = lotteryService.isLotteryWithIDExist(lottery.getId());
		if(pos==-1) {
			return new WinnerStatusDTO("Fail","Lottery Does'nt Exists");
		}
		LotteryDTO lot = lotteryService.getLotteryWithID(lottery.getId());
		if(lot.isActiveStatus()) {
			return new WinnerStatusDTO("Fail","Lottery in progress");
		}
		
		if(lot.getWinnerCode()==null ||lot.getWinnerCode().compareTo("")==0) {
			String winnerCode = userService.getWinnerCode(lottery.getId());
			
			lotteryService.setWinnerCode(lottery.getId(),winnerCode);
			
			return new WinnerStatusDTO("OK",winnerCode);
			
		}
		
		return new WinnerStatusDTO("Fail","Already Winner Selected");
	}

	
	@RequestMapping(method=RequestMethod.POST,value="/register")
	public UserStatusDTO insertUser(@RequestBody UserDTO user) {
		LotteryDTO lottery = lotteryService.getLotteryWithID(user.getId());
		if(lottery==null) {
			return new UserStatusDTO("Fail","Lottery Doesn't exist");
		}
		if(!lottery.isActiveStatus()) {
			return new UserStatusDTO("Fail","Lottery is Expired");
			
		}
		if(lottery.getParticipants()==lottery.getLimit()) {
			return new UserStatusDTO("Fail","Lottery Limit Exceeded");
		}
		
		return userService.insertUser(user);	
	}
	
	@RequestMapping(path ="/status",method = RequestMethod.GET)
	public UserStatusDTO getStatus(@RequestParam String email,@RequestParam int id,@RequestParam String code) {
		LotteryDTO lottery = lotteryService.getLotteryWithID(id);
		if(lottery==null) {
			return new UserStatusDTO("Error","Lottery Doesn't exist");
		}
		int pos = userService.getUserWithEmailAndCode(email,code);
		if(pos==-1) {
			return new UserStatusDTO("Error","Input not Valid");
		}
		if(lottery.isActiveStatus()) {
			return new UserStatusDTO("Pending","Lottery Doesn't exist");
		}
		String cod = lottery.getWinnerCode(); 
		if(cod.compareTo(code)==0) {
			return new UserStatusDTO("Win","");
		}
		if(cod.compareTo("")==0) {
			return new UserStatusDTO("Error","User doesn't exist");
		}
		return new UserStatusDTO("Lose","");
	}
	
	@RequestMapping("/getAllUsers")
	public List<UserDTO> getAllUsers() {
		return userService.getAllUsers();
	}
	
	
}
