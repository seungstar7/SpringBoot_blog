package kr.co.finalproject.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.utility.DBclose;
import net.utility.DBopen;

public class SubscribeInfoDAO {

	private DBopen dbopen=null;
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private StringBuilder sql=null;
	
	public SubscribeInfoDAO() {
		dbopen=new DBopen();
	}
	
	
	public ArrayList<SubscribeInfoDTO> subread(String mem_id) {
		
		ArrayList<SubscribeInfoDTO> sublist=null;
		SubscribeInfoDTO dto=null;
		
		try {
			con=dbopen.getConnection();//DB연결
			sql=new StringBuilder();
			sql.append(" SELECT subscribe_no, mem_id, party_id, party_role, subscribe_start, subscribe_end ");
			sql.append(" FROM subscribe_info ");
			sql.append(" WHERE mem_id=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, mem_id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				sublist= new ArrayList<SubscribeInfoDTO>();
				do {
					dto = new SubscribeInfoDTO();
					dto.setSubscribe_no(rs.getString("subscribe_no"));	
					dto.setMem_id(rs.getString("mem_id"));
					dto.setParty_id(rs.getString("party_id"));
					dto.setParty_role(rs.getString("party_role"));
					dto.setSubscribe_start(rs.getString("subscribe_start"));
					dto.setSubscribe_end(rs.getString("subscribe_end"));
					sublist.add(dto);
				}while(rs.next());
			}//if end
			
		}catch (Exception e) {
			System.out.println("구독정보 상세보기 실패: " + e);
		}finally{
			DBclose.close(con, pstmt, rs);
		}//try end
		
		return sublist;
		
	}

	
	
	
	public ArrayList<SubscribeInfoDTO> mySubread(String mem_id) {
		
		ArrayList<SubscribeInfoDTO> sublist=null;
		SubscribeInfoDTO dto=null;
		
		try {
			con=dbopen.getConnection();//DB연결
			sql=new StringBuilder();
			sql.append(" SELECT subscribe_no, A.mem_id, A.party_id, party_role, subscribe_start, subscribe_end, ott_name, ott_id, ott_pw, ott_price ");
			sql.append(" FROM subscribe_info A JOIN party_info B ");
			sql.append(" ON A.party_id=B.party_id ");
			sql.append(" WHERE A.mem_id=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, mem_id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				sublist= new ArrayList<SubscribeInfoDTO>();
				do {
					dto = new SubscribeInfoDTO();
					dto.setSubscribe_no(rs.getString("subscribe_no"));	
					dto.setMem_id(rs.getString("A.mem_id"));
					dto.setParty_id(rs.getString("A.party_id"));
					dto.setParty_role(rs.getString("party_role"));
					dto.setSubscribe_start(rs.getString("subscribe_start"));
					dto.setSubscribe_end(rs.getString("subscribe_end"));
					
					dto.setOtt_name(rs.getString("ott_name"));
					dto.setOtt_id(rs.getString("ott_id"));
					dto.setOtt_pw(rs.getString("ott_pw"));
					dto.setOtt_price(rs.getInt("ott_price"));
					
					sublist.add(dto);
				}while(rs.next());
			}//if end
			
		}catch (Exception e) {
			System.out.println("구독정보 상세보기 실패: " + e);
		}finally{
			DBclose.close(con, pstmt, rs);
		}//try end
		
		return sublist;
		
	}

	
	public int totalPay(String mem_id) {
		
		int totalOttFee=0;
		
		try {
			con=dbopen.getConnection();//DB연결
			sql=new StringBuilder();
			sql.append(" SELECT SUM(ott_price) ");
			sql.append(" FROM subscribe_info A JOIN party_info B ");
			sql.append(" ON A.party_id=B.party_id ");
			sql.append(" WHERE A.mem_id=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, mem_id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				
				totalOttFee=rs.getInt("SUM(ott_price)");
				
			}//if end
			
		}catch (Exception e) {
			System.out.println("총 이용금액 조회 실패: " + e);
		}finally{
			DBclose.close(con, pstmt, rs);
		}//try end
		
		return totalOttFee;
	}
	
	
	public int subinsert(SubscribeInfoDTO dto) {
		
		int cnt=0;

		try {
			con=dbopen.getConnection();//DB연결
			sql=new StringBuilder();
			sql.append(" INSERT INTO subscribe_info( ");
			sql.append("     subscribe_no, mem_id, party_id, party_role, subscribe_start, subscribe_end) ");
			sql.append(" VALUES(?, ?, ?, '파티장', now(), adddate(now(), interval 1 month)) ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getSubscribe_no());
			pstmt.setString(2, dto.getMem_id());
			pstmt.setString(3, dto.getParty_id());

			cnt=pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("파티장 구독 정보 생성 실패: " + e);
		} finally {
			DBclose.close(con, pstmt);
		}//try end
		
		
		return cnt;
		
	}//subinsert() end
	
	
	public int subinsert(SubscribeInfoDTO dto, String party_role) {
		
		int cnt=0;

		try {
			con=dbopen.getConnection();//DB연결
			sql=new StringBuilder();
			sql.append(" INSERT INTO subscribe_info( ");
			sql.append("     subscribe_no, mem_id, party_id, party_role, subscribe_start, subscribe_end) ");
			sql.append(" VALUES(?, ?, ?, ?, now(), adddate(now(), interval 1 month)) ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getSubscribe_no());
			pstmt.setString(2, dto.getMem_id());
			pstmt.setString(3, dto.getParty_id());
			pstmt.setString(4, party_role);


			cnt=pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("파티원 구독 정보 생성 실패: " + e);
		} finally {
			DBclose.close(con, pstmt);
		}//try end
		
		
		return cnt;
		
	}//subinsert() end

	
	public String subnoCreate(String nowStr){
		
		String subscribe_no="";
		String lastsubno="";
		
		try {
			con=dbopen.getConnection();//DB연결
			sql=new StringBuilder();
			sql.append(" SELECT subscribe_no ");
			sql.append(" FROM subscribe_info ");
			sql.append(" WHERE subscribe_no=(SELECT MAX(subscribe_no) ");
			sql.append(" FROM subscribe_info ");
			sql.append(" WHERE subscribe_no LIKE '" + nowStr + "%') ");

			pstmt = con.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				lastsubno=rs.getString("subscribe_no");
			}//if end
			
			if(lastsubno=="") {//오늘 만들어진 구독번호가 없음

				subscribe_no += nowStr + "_" + "10000";
				
			}else {
				int underbar=lastsubno.lastIndexOf("_");
				lastsubno.substring(underbar+1);
				
				int lastidxsubno = Integer.parseInt(lastsubno.substring(underbar+1));
				lastidxsubno++;
				
				subscribe_no += nowStr + "_" + lastidxsubno;
				
			}
			
		}catch (Exception e) {
			System.out.println("구독번호 불러오기 실패: " + e);
		}finally{
			DBclose.close(con, pstmt, rs);
		}//try end		
		
		
		return subscribe_no;
	}//subnoCreate() end
	
	
	
	public String autosubnoCreate(String nowStr, int fixno){//자동매칭시 파티원 구독번호 생성
		
		String subscribe_no="";
		String lastsubno="";
		
		try {
			con=dbopen.getConnection();//DB연결
			sql=new StringBuilder();
			sql.append(" SELECT subscribe_no ");
			sql.append(" FROM subscribe_info ");
			sql.append(" WHERE subscribe_no=(SELECT MAX(subscribe_no) ");
			sql.append(" FROM subscribe_info ");
			sql.append(" WHERE subscribe_no LIKE '" + nowStr + "%') ");

			pstmt = con.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				lastsubno=rs.getString("subscribe_no");
			}//if end
			
			if(lastsubno=="") {//오늘 만들어진 구독번호가 없음

				subscribe_no += nowStr + "_" + (10000 + fixno);
				
			}else {
				int underbar=lastsubno.lastIndexOf("_");
				lastsubno.substring(underbar+1);
				
				int lastidxsubno = Integer.parseInt(lastsubno.substring(underbar+1));
				
				subscribe_no += nowStr + "_" + (lastidxsubno + fixno);
				
			}
			
		}catch (Exception e) {
			System.out.println("구독번호 불러오기 실패: " + e);
		}finally{
			DBclose.close(con, pstmt, rs);
		}//try end		
		
		
		return subscribe_no;
	}//subnoCreate() end

	
	public Map<String, String> subDateread(String mem_id) {
		
		Map<String, String> submap=null;
		
		try {
			con=dbopen.getConnection();//DB연결
			sql=new StringBuilder();
			sql.append(" SELECT subscribe_no, A.mem_id, A.party_id, party_role, subscribe_start, subscribe_end, ott_name, ott_id, ott_pw, ott_price ");
			sql.append(" FROM subscribe_info A JOIN party_info B ");
			sql.append(" ON A.party_id=B.party_id ");
			sql.append(" WHERE A.mem_id=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, mem_id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				submap= new HashMap<>();
				do {
					
		        	submap.put("title", rs.getString("ott_name"));
		        	submap.put("start", rs.getString("subscribe_start"));
		        	submap.put("end", rs.getString("subscribe_end"));

				}while(rs.next());
			}//if end
			
		}catch (Exception e) {
			System.out.println("구독날짜 읽기 실패: " + e);
		}finally{
			DBclose.close(con, pstmt, rs);
		}//try end
		
		return submap;
		
	}
	
	
	
	
	
}//class end
