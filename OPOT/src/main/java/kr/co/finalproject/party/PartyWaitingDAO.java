package kr.co.finalproject.party;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import net.utility.DBclose;
import net.utility.DBopen;

public class PartyWaitingDAO {
	private DBopen dbopen=null;
    private Connection con=null;
    private PreparedStatement pstmt=null;
    private ResultSet rs=null;
    private StringBuilder sql=null;
    
    public PartyWaitingDAO() {
    	dbopen=new DBopen();
    }
    
    public int memberwait(PartyWaitingDTO dto) {
    	int cnt = 0;
    	try {
            con = dbopen.getConnection();
            sql = new StringBuilder();
            sql.append(" INSERT INTO party_waiting(mem_id, ott_name, waiting_date) ");
            sql.append(" VALUES( ?, ?, now()) "); 

            pstmt = con.prepareStatement(sql.toString());
            pstmt.setString(1, dto.getMem_id()); 
            pstmt.setString(2, dto.getOtt_name()); 
            cnt = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("카드등록 실패 : " + e);
        } finally {
            DBclose.close(con, pstmt);
        }//end
        return cnt;
    }//end
    
    public PartyWaitingDTO read(String ott_name) {
		PartyWaitingDTO dto=null;
		try {
			con=dbopen.getConnection();
					
			sql=new StringBuilder();
			sql.append(" SELECT mem_id, ott_name, waiting_date, waiting_no ");
			sql.append(" FROM party_waiting  ");
			sql.append(" WHERE ott_name=? ");
			
			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, ott_name);
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto=new PartyWaitingDTO();
				dto.setMem_id(rs.getString("mem_id"));
				dto.setOtt_name(ott_name);
				dto.setWaiting_date(rs.getString("waiting_date"));
				dto.setWaiting_no(rs.getInt("waiting_no"));
			}//if end
			
		} catch (Exception e) {
			System.out.println("읽기 실패 : " + e);
		}finally {
			DBclose.close(con,pstmt,rs);
		}//end
		return dto;
	}//end
    
    
    
    public int waitingDelete(int waiting_no) {
       	int cnt = 0;
    	try {
            con = dbopen.getConnection();
            sql = new StringBuilder();
            sql.append(" DELETE FROM party_waiting ");
            sql.append(" WHERE waiting_no=? "); 
            pstmt = con.prepareStatement(sql.toString());
            pstmt.setInt(1, waiting_no); 
            cnt = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("대기목록 삭제 실패 : " + e);
        } finally {
            DBclose.close(con, pstmt);
        }//end
        return cnt;
    }
    
    
    
	public String ordnoCreate(String nowStr){
		
		String party_ordnumber="";
		String index="";
		
		try {
			con=dbopen.getConnection();//DB연결
			sql=new StringBuilder();
			sql.append(" SELECT party_ordnumber ");
			sql.append(" FROM party_member ");
			sql.append(" WHERE party_ordnumber=(SELECT MAX(party_ordnumber) ");
			sql.append(" FROM party_member ");
			sql.append(" WHERE party_ordnumber LIKE '" + nowStr + "%') ");

			pstmt = con.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				index=rs.getString("party_ordnumber");
			}//if end
			
			if(index=="") {//오늘 만들어진 주문번호가 없음

				party_ordnumber += nowStr + "_" + "100";
				
			}else {
				int underbar=index.lastIndexOf("_");
				
				int newindex = Integer.parseInt(index.substring(underbar+1));
				newindex++;
				
				
				party_ordnumber += nowStr + "_" + newindex;
				
			}
			
		}catch (Exception e) {
			System.out.println("주문번호 불러오기 실패: " + e);
		}finally{
			DBclose.close(con, pstmt, rs);
		}//try end		
		
		
		return party_ordnumber;
	}//subnoCreate() end
    
	
	   public ArrayList<PartyWaitingDTO> read() {
		   ArrayList<PartyWaitingDTO> list=null;
		   PartyWaitingDTO dto=null;
			try {
				con=dbopen.getConnection();
						
				sql=new StringBuilder();
				sql.append(" SELECT mem_id, ott_name, waiting_date, waiting_no ");
				sql.append(" FROM party_waiting  ");				
				
				pstmt=con.prepareStatement(sql.toString());
				
				rs=pstmt.executeQuery();
				if(rs.next()) {
					list=new ArrayList<PartyWaitingDTO>();
					do {
						dto=new PartyWaitingDTO();
						dto.setMem_id(rs.getString("mem_id"));
						dto.setOtt_name(rs.getString("ott_name"));
						dto.setWaiting_date(rs.getString("waiting_date"));
						dto.setWaiting_no(rs.getInt("waiting_no"));
						list.add(dto);
					}while(rs.next());
					
				}//if end
				
			} catch (Exception e) {
				System.out.println("대기목록 읽기 실패 : " + e);
			}finally {
				DBclose.close(con,pstmt,rs);
			}//end
			return list;
		}//end
	
	
    

}//class end