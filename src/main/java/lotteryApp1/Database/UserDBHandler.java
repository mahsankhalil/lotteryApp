package lotteryApp1.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import lotteryApp1.DTO.LotteryDTO;
import lotteryApp1.DTO.UserDTO;

public class UserDBHandler extends DB {
	
	public ArrayList<UserDTO> getAllUsers() {
        ArrayList<UserDTO> array = null; //statement
        UserDTO obj = null;
        String query = "SELECT * FROM User";
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            if (rs != null) {
                array = new ArrayList<UserDTO>();
                while (rs.next()) {
                    obj = new UserDTO();
                    obj.setUserID(rs.getInt("user_id"));
                    obj.setEmail(rs.getString("email"));
                    obj.setAge(rs.getInt("age"));
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
	
	public ArrayList<UserDTO> getAllUsersWithLottery(int id) {
        ArrayList<UserDTO> array = null; //statement
        UserDTO obj = null;
        String query = "SELECT * from USER,lotteryParticipants where USER.user_id = lotteryParticipants.user_id and lottery_id="+id;
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            if (rs != null) {
                array = new ArrayList<UserDTO>();
                while (rs.next()) {
                    obj = new UserDTO();
                    obj.setUserID(rs.getInt("user_id"));
                    obj.setEmail(rs.getString("email"));
                    obj.setAge(rs.getInt("age"));
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

    public UserDTO getUserByID(int id) {
        if (id < 0) {
            return null;
        }
        UserDTO obj = null;
        String query = "Select * from user where user_id=?";
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            System.out.println(pst);
            rs = pst.executeQuery();
            if (rs != null) {
                if (rs.next()) {

                	obj = new UserDTO();
                    obj.setUserID(rs.getInt("user_id"));
                    obj.setEmail(rs.getString("email"));
                    obj.setAge(rs.getInt("age"));

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
     public int insertUser(UserDTO obj) {
        int affectedRows = 0;
        String query = "INSERT INTO `user`(`email`, `age`) VALUES (?,?)";
        PreparedStatement pst = null;
        try {
             pst = conn.prepareStatement(query);
            
            
            pst.setString(1,obj.getEmail());
            pst.setInt(2, obj.getAge());
       

            System.out.println(pst);

            affectedRows = pst.executeUpdate();
            
            query = "Select max(user_id) max from user";
            pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            if(rs.next())
            	return rs.getInt("max");

        } catch (SQLException ex) {
            Logger.getLogger(LotteryDBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public UserDTO getUserEmail(String email) {
        String query = "Select * from user where lower(email)=lower(?)";
        PreparedStatement pst = null;
        ResultSet rs = null;
        UserDTO obj = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, email);
            System.out.println(pst);
            rs = pst.executeQuery();
            if (rs != null) {
                if (rs.next()) {
                	obj = new UserDTO();
                    obj.setUserID(rs.getInt("user_id"));
                    obj.setEmail(rs.getString("email"));
                    obj.setAge(rs.getInt("age"));
                	return obj;

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

}
