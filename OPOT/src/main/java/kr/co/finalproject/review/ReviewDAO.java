package kr.co.finalproject.review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import net.utility.DBclose;
import net.utility.DBopen;

public class ReviewDAO {

   private DBopen dbopen = null;
   private Connection con = null;
   private PreparedStatement pstmt = null;
   private ResultSet rs = null;
   private StringBuilder sql = null;

   public ReviewDAO() {
      dbopen = new DBopen();
   }// end

   public ReviewDTO reviewAll(int mcode) {  // rev_read랑 동일하게봄
      ReviewDTO dto = null;
      try {
         con = dbopen.getConnection();
         sql = new StringBuilder();
         sql.append(" SELECT rev_code, mem_id, rev_title, rev_reg, rev_cont, rev_spo, rev_rate ");
         sql.append(" FROM review ");
         sql.append(" WHERE mcode=? ");
         sql.append(" ORDER BY rev_code ASC ");

         pstmt = con.prepareStatement(sql.toString());
         pstmt.setInt(1, mcode);

         rs = pstmt.executeQuery();
         if (rs.next()) {
            dto = new ReviewDTO();
            dto.setMcode(mcode);
            dto.setRev_code(rs.getInt("rev_code"));
            dto.setMem_id(rs.getString("mem_id"));
            dto.setRev_title(rs.getString("rev_title"));
            dto.setRev_reg(rs.getString("rev_reg"));
            dto.setRev_cont(rs.getString("rev_cont"));
            dto.setRev_spo(rs.getString("rev_spo"));
            dto.setRev_rate(rs.getInt("rev_rate"));

         }
         while (rs.next())
            ;

      } catch (Exception e) {
         System.out.println("리뷰 목록 불러오기 실패 : " + e);
      } finally {
         DBclose.close(con, pstmt, rs);
      } // end
      return dto;
   }// class end
   
   
    public int rev_ins(ReviewDTO dto) {
          int cnt=0;
         try {
            con=dbopen.getConnection();
            
            sql=new StringBuilder();
            sql.append(" INSERT INTO Review(mem_id, mcode, rev_title, rev_reg, rev_cont, rev_spo, rev_rate) ");
            sql.append(" values( ?, ?, ?, now(), ?, ?, ?) ");
            
            pstmt=con.prepareStatement(sql.toString());
            pstmt.setString(1, dto.getMem_id());   
            pstmt.setInt(2, dto.getMcode());   
            pstmt.setString(3, dto.getRev_title());
            pstmt.setString(4, dto.getRev_cont());
            pstmt.setString(5, dto.getRev_spo());
            pstmt.setInt(6, dto.getRev_rate());
            cnt=pstmt.executeUpdate();
            
         } catch (Exception e) {
            System.out.println("리뷰 작성 실패 : " + e);
         }finally {
            DBclose.close(con, pstmt);
         }//end
         return cnt;
      }//create end

    public double rev_rateHap(int mcode) {  
          double rev_rate =0.0;
         try {
            con = dbopen.getConnection();
            sql = new StringBuilder();
            sql.append(" SELECT ROUND(AVG(rev_rate),1) AS rev_rate ");
            sql.append(" FROM review ");
            sql.append(" WHERE mcode=? ");

            pstmt = con.prepareStatement(sql.toString());
            pstmt.setInt(1, mcode);

            rs = pstmt.executeQuery();
            if (rs.next()) {
               rev_rate=rs.getDouble("rev_rate");
            }
            while (rs.next());

         } catch (Exception e) {
            System.out.println("리뷰 평점 불러오기 실패 : " + e);
         } finally {
            DBclose.close(con, pstmt, rs);
         } // end
         return rev_rate;
      }// class end
    
    
    public int delete(int rev_code, int mcode){
         int cnt=0; //성공 또는 실패 여부 반환
         try {
            con=dbopen.getConnection();
            
            sql=new StringBuilder();
            sql.append(" DELETE FROM review ");
            sql.append(" WHERE rev_code=? AND mcode=? ");
            
            pstmt=con.prepareStatement(sql.toString());
            pstmt.setInt(1, rev_code);
            pstmt.setInt(2, mcode);
            cnt=pstmt.executeUpdate();
            
         } catch (Exception e) {
            System.out.println("행삭제 실패 : " + e);
         }finally {
            DBclose.close(con,pstmt);
         }//end
         return cnt;
      }//delete end 
    
    
      public ReviewDTO readReviewOne(int rev_code) {  // rev_read랑 동일하게봄
         ReviewDTO dto = null;
         try {
            con = dbopen.getConnection();
            sql = new StringBuilder();
            sql.append(" SELECT rev_code, mem_id,  mcode,  rev_title, rev_reg, rev_cont, rev_spo, rev_rate ");
            sql.append(" FROM review ");
            sql.append(" WHERE rev_code=? ");

            pstmt = con.prepareStatement(sql.toString());
            pstmt.setInt(1, rev_code);

            rs = pstmt.executeQuery();
            if (rs.next()) {
               dto = new ReviewDTO();
               dto.setMcode(rs.getInt("mcode"));
               dto.setRev_code(rs.getInt("rev_code"));
               dto.setMem_id(rs.getString("mem_id"));
               dto.setRev_title(rs.getString("rev_title"));
               dto.setRev_reg(rs.getString("rev_reg"));
               dto.setRev_cont(rs.getString("rev_cont"));
               dto.setRev_spo(rs.getString("rev_spo"));
               dto.setRev_rate(rs.getInt("rev_rate"));

            }

         } catch (Exception e) {
            System.out.println("리뷰 불러오기 실패 : " + e);
         } finally {
            DBclose.close(con, pstmt, rs);
         } // end
         return dto;
      }// class end
    
    
    
    public int updateproc(ReviewDTO dto, int rev_code, String mem_id) {
         int cnt=0; //성공 또는 실패 여부 반환
         try {

            con=dbopen.getConnection();
            sql=new StringBuilder();
            sql.append(" UPDATE review ");
            sql.append(" SET rev_rate=?, rev_title=?, rev_cont=?, rev_spo=? ");
            sql.append(" WHERE rev_code=? AND mem_id = ?");
            
            pstmt=con.prepareStatement(sql.toString());
            pstmt.setInt(1, dto.getRev_rate());
            pstmt.setString(2, dto.getRev_title());
            pstmt.setString(3, dto.getRev_cont());
            pstmt.setString(4, dto.getRev_spo());
            pstmt.setInt(5, dto.getRev_code());
            pstmt.setString(6, mem_id);
            
            cnt=pstmt.executeUpdate(); 
            
         } catch (Exception e) {
            System.out.println("행수정 실패 : " + e);
         }finally {
            DBclose.close(con,pstmt);
         }//end
         return cnt;
      }//updateproc() end

 
    public ArrayList<ReviewDTO> list(int nowPage, int recordPerPage, int mcode){
        ArrayList<ReviewDTO> list=null;
        
        int startRow = ((nowPage-1) * recordPerPage) ;
        try {
           con=dbopen.getConnection(); 
           sql=new StringBuilder();

           sql.append(" SELECT rev_code, mem_id,  mcode,  rev_title, rev_reg, rev_cont, rev_spo, rev_rate ");
           sql.append(" FROM review ");
           sql.append(" WHERE mcode=? ");
           sql.append(" ORDER BY rev_code DESC ");           
           sql.append(" LIMIT " + startRow + ", " + recordPerPage);
          
           pstmt=con.prepareStatement(sql.toString());
           pstmt.setInt(1, mcode);
           rs=pstmt.executeQuery();
           if(rs.next()) {
        	   list=new ArrayList<>();
        	   do {
        		   ReviewDTO dto = new ReviewDTO();//커서가 가리키는 한 줄 저장
        		   dto = new ReviewDTO();
                   dto.setMcode(rs.getInt("mcode"));
                   dto.setRev_code(rs.getInt("rev_code"));
                   dto.setMem_id(rs.getString("mem_id"));
                   dto.setRev_title(rs.getString("rev_title"));
                   dto.setRev_reg(rs.getString("rev_reg"));
                   dto.setRev_cont(rs.getString("rev_cont"));
                   dto.setRev_spo(rs.getString("rev_spo"));
                   dto.setRev_rate(rs.getInt("rev_rate"));
             
             list.add(dto);
           }while(rs.next());
        }
           
        }catch (Exception e) {
          System.out.println("리뷰 목록 불러오기 실패 : " + e);
       }finally {
          DBclose.close(con,pstmt,rs);
       }
        
        return list;
     }//list3() end
    
    
    
    public int count(int mcode) {
        int cnt=0;
        try {
          con=dbopen.getConnection();
          
          sql=new StringBuilder();
          sql.append(" SELECT COUNT(*) as cnt ");
          sql.append(" FROM review ");
          sql.append(" WHERE mcode=? ");
          
          pstmt=con.prepareStatement(sql.toString());
          pstmt.setInt(1, mcode);
          rs=pstmt.executeQuery();
          if(rs.next()) {
             cnt=rs.getInt("cnt");
          }//if end
          
       } catch (Exception e) {
          System.out.println("카운팅 실패 : " + e );
       }finally {
          DBclose.close(con,pstmt,rs);
       }//end
        return cnt;
     }//end

    


}// class end