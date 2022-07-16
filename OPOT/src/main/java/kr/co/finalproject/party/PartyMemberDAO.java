package kr.co.finalproject.party;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import kr.co.finalproject.member.SubscribeInfoDAO;
import kr.co.finalproject.member.SubscribeInfoDTO;
import net.utility.DBclose;
import net.utility.DBopen;

public class PartyMemberDAO {

	private DBopen dbopen=null;
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private StringBuilder sql=null;
	
	public PartyMemberDAO() {
		dbopen=new DBopen();
	}
	
	
	
	public ArrayList<PartyMemberDTO> readParty(String party_id) {
		
		ArrayList<PartyMemberDTO> list=null;
		
		PartyMemberDTO dto=null;
		
		try {
			con=dbopen.getConnection();//DB연결
			sql=new StringBuilder();
			sql.append(" SELECT mem_id, party_id, party_pcost, party_pdate, party_ordnumber ");
			sql.append(" FROM party_member ");
			sql.append(" WHERE party_id=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, party_id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				list=new ArrayList<PartyMemberDTO>();
				do {
					dto=new PartyMemberDTO();
					dto.setMem_id(rs.getString("mem_id"));
					dto.setParty_id(rs.getString("party_id"));
					dto.setParty_pcost(rs.getInt("party_pcost"));
					dto.setParty_pdate(rs.getString("party_pdate"));
					dto.setParty_ordnumber(rs.getString("party_ordnumber"));
					list.add(dto);
				}while(rs.next());
			}//if end
		}catch (Exception e) {
			System.out.println("파티원 불러오기 실패: " + e);
		}finally{
			DBclose.close(con, pstmt, rs);
		}//try end
		
		return list;
	}//readParty() end
	
	
    public int ordersheet(PartyInfoDTO partyInfoDTO, PartyMemberDTO PartyMemberDTO) {
	   	int result=0; //성공 또는 실패 여부 반환
		try {
			con=dbopen.getConnection();

			sql=new StringBuilder();
			sql.append(" INSERT INTO party_member(mem_id, party_id, party_pcost, party_pdate ,party_ordnumber) ");
	        sql.append(" VALUES(?, ?, ?, now(), ? ) ");

	        pstmt=con.prepareStatement(sql.toString());
	        pstmt.setString(1, PartyMemberDTO.getMem_id()); 
	        pstmt.setString(2, partyInfoDTO.getParty_id()); 
	        pstmt.setInt(3, PartyMemberDTO.getParty_pcost());
	        pstmt.setString(4, PartyMemberDTO.getParty_ordnumber());

			result=pstmt.executeUpdate();

			if(result==1) {
				String party_id=partyInfoDTO.getParty_id();
	        	String party_role="파티원";

	        	//party주문번호 생성
				Date now = new Date();
				SimpleDateFormat orddateFrm = new SimpleDateFormat("yyyyMMddHHmmss");
				String nowStr = orddateFrm.format(now);

	        	SubscribeInfoDAO subdao=new SubscribeInfoDAO();
        		SubscribeInfoDTO subdto=new SubscribeInfoDTO();
    			String subscribe_no="";
    			SimpleDateFormat dateFrm = new SimpleDateFormat("yyyyMMdd");
    			nowStr = dateFrm.format(now);
    			subscribe_no=subdao.subnoCreate(nowStr);
    			subdto.setSubscribe_no(subscribe_no);
    			subdto.setMem_id(PartyMemberDTO.getMem_id());
    			subdto.setParty_id(party_id);
    			int subcnt=subdao.subinsert(subdto, party_role);
    			if(subcnt==0) {
    				System.out.println("파티원 구독정보 등록 실패");
    			}else{
    				System.out.println("파티원 구독정보 등록 성공");
    			}

			}else {
				System.out.println("파티매칭(주문서 생성) 실패");
			}

		} catch (Exception e) {
			System.out.println("행 수정 실패 : " + e);
		}finally {
			DBclose.close(con,pstmt);
		}//end
		return result;
	}//end

    
    public int memexit(String party_id, String mem_id) {
    	int cnt=0;
    	try {
			con=dbopen.getConnection();

			sql=new StringBuilder();
			sql.append(" DELETE FROM party_member ");
			sql.append(" WHERE party_id=? && mem_id=? ");

			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, party_id);
			pstmt.setString(2, mem_id);
			cnt=pstmt.executeUpdate();
			System.out.println("멤버 행 삭제완료");
			if(cnt==1) {
				sql=new StringBuilder();
				sql.append(" DELETE FROM subscribe_info ");
				sql.append(" WHERE party_id=? && mem_id=? ");

				pstmt=con.prepareStatement(sql.toString());
				pstmt.setString(1, party_id);
				pstmt.setString(2, mem_id);
				cnt=pstmt.executeUpdate();
				System.out.println("모든 행 삭제완료");
				if(cnt==1) {
					sql=new StringBuilder();
					sql.append(" UPDATE party_info ");
					sql.append(" SET matching_no=matching_no-1 ");
					sql.append(" WHERE party_id=? ");

					pstmt=con.prepareStatement(sql.toString());
					pstmt.setString(1, party_id );
					cnt=pstmt.executeUpdate();
					System.out.println("파티정보 수정 완료");
				}
			}	
		} catch (Exception e) {
			System.out.println("행삭제 실패 : " + e);
		}finally {
			DBclose.close(con,pstmt);
		}//end
		return cnt;
	}//memexit end

    
    
    public int partyexit(String party_id, String ott_name) {
    	int cnt=0;
    	try {
    		con=dbopen.getConnection();

    		sql=new StringBuilder();
			sql.append(" DELETE FROM subscribe_info ");
			sql.append(" WHERE party_id=? ");

			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, party_id);
			cnt=pstmt.executeUpdate();
			System.out.println(cnt + "구독정보"); //파티장 및 파티원 구독정보 지우기
			if(cnt!=0) {//구독정보가 지워 졌으면
				cnt=0;
				//파티 아이디로 구독한 멤버정보를 불러와서
				ArrayList<PartyMemberDTO> list=this.readParty(party_id);
				//System.out.println(list);
				//불러온 멤버정보를 반복으로 통해서 대기리스트를 생성
				PartyMemberDTO dto=new PartyMemberDTO();
				for(int i=0; i<list.size(); i++) {
					dto=list.get(i);
					//dto.getMem_id();
					PartyWaitingDTO wdto=new PartyWaitingDTO();
					PartyWaitingDAO wdao=new PartyWaitingDAO();

					wdto.setOtt_name(ott_name);
					wdto.setMem_id(dto.getMem_id());
					int cnt1=wdao.memberwait(wdto);
					if(cnt1==0) {
						System.out.println(cnt1 + "대기목록 생성실패");
					}else {
						//대기목록 생성했으면 생성한 파티 멤버정보 지우기
						System.out.println(cnt1 + "대기목록 생성성공");
					}//if end
				}//for end
				con=dbopen.getConnection();

				sql=new StringBuilder();
				sql.append(" DELETE FROM party_member ");
				sql.append(" WHERE party_id=? ");

				pstmt=con.prepareStatement(sql.toString());
				pstmt.setString(1, party_id);
				cnt=pstmt.executeUpdate();
				System.out.println(cnt + "멤버 행 삭제 완료");


				if(cnt!=0) {
					sql=new StringBuilder();
					sql.append(" DELETE FROM party_info ");
					sql.append(" WHERE party_id=? ");

					pstmt=con.prepareStatement(sql.toString());
					pstmt.setString(1, party_id );
					cnt=pstmt.executeUpdate();
					System.out.println(cnt + "파티삭제");
				}else { //지울 멤버가 없다면 
					sql=new StringBuilder();
					sql.append(" DELETE FROM party_info ");
					sql.append(" WHERE party_id=? ");

					pstmt=con.prepareStatement(sql.toString());
					pstmt.setString(1, party_id );
					cnt=pstmt.executeUpdate();
					System.out.println(cnt + "파티삭제");
				}
			}else if(cnt==0){
				sql=new StringBuilder();
				sql.append(" DELETE FROM party_info ");
				sql.append(" WHERE party_id=? ");

				pstmt=con.prepareStatement(sql.toString());
				pstmt.setString(1, party_id );
				cnt=pstmt.executeUpdate();
				System.out.println(cnt + "파티삭제");

			}//if end
		} catch (Exception e) {
			System.out.println("행삭제 실패 : " + e);
		}finally {
			DBclose.close(con,pstmt);
		}//end
		return cnt;
	}//exit end
	
	
	
	
}//class end
