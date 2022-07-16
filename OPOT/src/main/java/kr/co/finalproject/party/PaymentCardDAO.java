package kr.co.finalproject.party;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import net.utility.DBclose;
import net.utility.DBopen;

public class PaymentCardDAO {
	private DBopen dbopen=null;
    private Connection con=null;
    private PreparedStatement pstmt=null;
    private ResultSet rs=null;
    private StringBuilder sql=null;

    public PaymentCardDAO() {
    	dbopen=new DBopen();
    }

    public int cardIns(PaymentCardDTO dto) {
    	int cnt = 0;
    	try {
            con = dbopen.getConnection();
            sql = new StringBuilder();
            sql.append(" INSERT INTO payment_card(mem_id, card_exp, card_no, card_pw, card_com) ");
            sql.append(" VALUES( ?, ?, ?, ?, ?) "); 

            pstmt = con.prepareStatement(sql.toString());
            pstmt.setString(1, dto.getMem_id()); 
            pstmt.setString(2, dto.getCard_exp()); 
            pstmt.setString(3, dto.getCard_no());
            pstmt.setInt(4, dto.getCard_pw());
            pstmt.setString(5, dto.getCard_com());
            cnt = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("카드등록 실패 : " + e);
        } finally {
            DBclose.close(con, pstmt);
        }//end
        return cnt;
    }//cardIns() end
    
    
	public PaymentCardDTO cardRead(String mem_id) {
		  
		  PaymentCardDTO dto=null;
	      try {
	         con=dbopen.getConnection();
	         
			sql=new StringBuilder();
			sql.append(" SELECT mem_id, card_exp, card_no, card_pw, card_com ");
			sql.append(" FROM payment_card ");
			sql.append(" WHERE mem_id=? ");
	         
	         pstmt=con.prepareStatement(sql.toString());
	         pstmt.setString(1, mem_id);
	         
	         rs=pstmt.executeQuery();
	         if(rs.next()) {
	            dto=new PaymentCardDTO();
				dto.setMem_id(rs.getString("mem_id"));
	            dto.setCard_exp(rs.getString("card_exp"));
	            dto.setCard_no(rs.getString("card_no"));
	            dto.setCard_pw(rs.getInt("card_pw"));
	            dto.setCard_com(rs.getString("card_com"));

	         }//if end
	         
	      } catch (Exception e) {
	         System.out.println("카드 정보 읽기 실패 : " + e);
	      }finally {
	         DBclose.close(con,pstmt,rs);
	      }//end
	      return dto;
	}//cardRead() end
    
	
	
	public int updatecard(PaymentCardDTO dto) {
		int cnt=0; //성공 또는 실패 여부 반환
		try {

			con=dbopen.getConnection();
			sql=new StringBuilder();
			sql.append(" UPDATE payment_card ");
			sql.append(" SET card_exp=?, card_no=?, card_pw=?, card_com=?  ");
			sql.append(" WHERE mem_id=? ");

			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getCard_exp());
			pstmt.setString(2, dto.getCard_no());
			pstmt.setInt(3, dto.getCard_pw());
			pstmt.setString(4, dto.getCard_com());
			pstmt.setString(5, dto.getMem_id());

			cnt=pstmt.executeUpdate(); 

		} catch (Exception e) {
			System.out.println("행수정 실패 : " + e);
		}finally {
			DBclose.close(con,pstmt);
		}//end
		return cnt;
	}

    


}//class end
