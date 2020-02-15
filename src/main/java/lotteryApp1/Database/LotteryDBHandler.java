package lotteryApp1.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import lotteryApp1.DTO.LotteryDTO;


public class LotteryDBHandler extends DB {

	
	public ArrayList<LotteryDTO> getAllLotteries() {
        ArrayList<LotteryDTO> array = null; //statement
        LotteryDTO obj = null;
        String query = "SELECT * FROM lottery";
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            if (rs != null) {
                array = new ArrayList<LotteryDTO>();
                while (rs.next()) {
                    obj = new LotteryDTO();
                    obj.setId(rs.getInt("id"));
                    obj.setTitle(rs.getString("title"));
                    int a = rs.getInt("activeStatus");
                    boolean flag = false;
                    if(a==1) {
                    	flag = true;
                    }
                    obj.setActiveStatus(flag);
                    obj.setLimit(rs.getInt("maxLimit"));
                    obj.setParticipants(rs.getInt("participants"));
                    obj.setStartDate(rs.getString("startDate"));
                    obj.setEndDate(rs.getString("endDate"));
                    obj.setWinnerCode(rs.getString("winner_code"));
                    array.add(obj);

                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(LotteryDBHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LotteryDBHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LotteryDBHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (array.isEmpty()) {
            return null;
        }
        return array;
    }

    public LotteryDTO getLotteryByID(int id) {
        if (id < 0) {
            return null;
        }
        LotteryDTO obj = null;
        String query = "Select * from lottery where id=?";
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            System.out.println(pst);
            rs = pst.executeQuery();
            if (rs != null) {
                if (rs.next()) {
                	obj = new LotteryDTO();
                	obj.setId(rs.getInt("id"));
                    obj.setTitle(rs.getString("title"));
                    int a = rs.getInt("activeStatus");
                    boolean flag = false;
                    if(a==1) {
                    	flag = true;
                    }
                    obj.setActiveStatus(flag);
                    obj.setLimit(rs.getInt("maxLimit"));
                    obj.setParticipants(rs.getInt("participants"));
                    obj.setStartDate(rs.getString("startDate"));
                    obj.setEndDate(rs.getString("endDate"));
                    obj.setWinnerCode(rs.getString("winner_code"));

                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(LotteryDBHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LotteryDBHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LotteryDBHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return obj;
    }
     public int insertLottery(LotteryDTO obj) {
        int affectedRows = 0;
        String query = "INSERT INTO "
        		+ "`lottery`(`title`, `maxLimit`, "
        		+ "`startDate`,`activeStatus`,participants) "
        		+ "VALUES"
        		+ "(?,?,?,?,?)";
        PreparedStatement pst = null;
        try {
             pst = conn.prepareStatement(query);
            
            
            pst.setString(1,obj.getTitle());
            pst.setInt(2, obj.getLimit());
            pst.setString(3, obj.getStartDate());
            pst.setInt(4, 1);
            pst.setInt(5, 0);

            System.out.println(pst);

            affectedRows = pst.executeUpdate();
            
            query = "Select max(id) max from lottery";
            PreparedStatement pst1 = conn.prepareStatement(query);
            ResultSet rs = pst1.executeQuery();
            if(rs.next())
            	return rs.getInt("max");

        } catch (SQLException ex) {
            Logger.getLogger(LotteryDBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
     
     public int insertLotteryParticipation(int user_id,int lottery_id,String code) {
         int affectedRows = 0;
         String query = "INSERT INTO `lotteryParticipants`(`user_id`, `lottery_id`, `code`) VALUES (?,?,?)";
         PreparedStatement pst = null;
         try {
              pst = conn.prepareStatement(query);
             
             
             pst.setInt(1,user_id);
             pst.setInt(2, lottery_id);
             pst.setString(3, code);
 
             

             System.out.println(pst);

             affectedRows = pst.executeUpdate();
             
             query = "Select max(id) max from lottery";
             PreparedStatement pst1 = conn.prepareStatement(query);
             ResultSet rs = pst1.executeQuery();
             if(rs.next())
             	return rs.getInt("max");

         } catch (SQLException ex) {
             Logger.getLogger(LotteryDBHandler.class.getName()).log(Level.SEVERE, null, ex);
         }
         return 0;
     }


    public LotteryDTO isLotteryWithTitleExists(String title) {
        String query = "Select * from lottery where lower(title)=lower(?)";
        PreparedStatement pst = null;
        ResultSet rs = null;
        LotteryDTO obj = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, title);
            System.out.println(pst);
            rs = pst.executeQuery();
            if (rs != null) {
                if (rs.next()) {
                	obj = new LotteryDTO();
                    obj.setId(rs.getInt("id"));
                    obj.setTitle(rs.getString("title"));
                    int a = rs.getInt("activeStatus");
                    boolean flag = false;
                    if(a==1) {
                    	flag = true;
                    }
                    obj.setActiveStatus(flag);
                    obj.setLimit(rs.getInt("maxLimit"));
                    obj.setParticipants(rs.getInt("participants"));
                    obj.setStartDate(rs.getString("startDate"));
                    obj.setEndDate(rs.getString("endDate"));
                    obj.setWinnerCode(rs.getString("winner_code"));
                    


                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(LotteryDBHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LotteryDBHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LotteryDBHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return obj;

    }
    
    public boolean stopRegistration(int lotteryID,String time)//id will never be updated
    {
        int affectedRows = 0;
        String query = "update lottery set endDate=?,activeStatus=? where id=?";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(query);
            
            
            pst.setString(1,time);
            pst.setInt(2, 0);
            pst.setInt(3, lotteryID);
            System.out.println(pst);

            affectedRows = pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(LotteryDBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return affectedRows > 0;
    }
    
    public boolean updateParticipant(int lotteryID,int participant)//id will never be updated
    {
        int affectedRows = 0;
        String query = "update lottery set participants=? where id=?";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(query);
            
            
            pst.setInt(1, participant);
            pst.setInt(2, lotteryID);
            System.out.println(pst);

            affectedRows = pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(LotteryDBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return affectedRows > 0;
    }
    public boolean updateWinnerCode(int lotteryID,String winnerCode)//id will never be updated
    {
        int affectedRows = 0;
        String query = "update lottery set winner_code=? where id=?";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(query);
            
            
            pst.setString(1, winnerCode);
            pst.setInt(2, lotteryID);
            System.out.println(pst);

            affectedRows = pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(LotteryDBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return affectedRows > 0;
    }
    
    public boolean isUserRegistered(int userId,String code)//id will never be updated
    {
        
        String query = "select * from lotteryParticipants where user_id = ? and "
        		+ "code = ?";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(query);
            
            
            pst.setInt(1, userId);
            pst.setString(2, code);
            System.out.println(pst);

            ResultSet rs = pst.executeQuery();
            if(rs.next()) {
            	return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(LotteryDBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public String getCodeWithUserAndLottery(int userId,int lottery_id)//id will never be updated
    {
        
        String query = "select * from lotteryParticipants where user_id = ? and "
        		+ "lottery_id = ?";
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(query);
            
            
            pst.setInt(1, userId);
            pst.setInt(2, lottery_id);
            System.out.println(pst);

            ResultSet rs = pst.executeQuery();
            if(rs.next()) {
            	return rs.getString("code");
            }

        } catch (SQLException ex) {
            Logger.getLogger(LotteryDBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    public boolean isCodeExists(String code)//id will never be updated
    {
        
        String query = "select * from lotteryParticipants where code = ?";
        		
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(query);
            
            
            
            pst.setString(1, code);
            System.out.println(pst);

            ResultSet rs = pst.executeQuery();
            if(rs.next()) {
            	return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(LotteryDBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
	
}
