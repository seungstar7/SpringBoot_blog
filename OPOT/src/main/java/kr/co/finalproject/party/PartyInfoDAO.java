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

public class PartyInfoDAO {

	private DBopen dbopen=null;
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private StringBuilder sql=null;
	
	public PartyInfoDAO() {
		dbopen=new DBopen();
	}
	
	
	public int insert(PartyInfoDTO dto) {
		int cnt=0;
		
		try {
			con=dbopen.getConnection();//DB연결
			sql=new StringBuilder();
			sql.append(" INSERT INTO party_info(mem_id, ott_name, ott_price, ott_id, ott_pw, ott_cdate, bank_name, bank_account, payback_amount, party_id) ");
			sql.append(" VALUES(?, ?, ?, ?, ?, now(), ?, ?, ?, ? ) ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getMem_id());
			pstmt.setString(2, dto.getOtt_name());
			pstmt.setInt(3, dto.getOtt_price());;
			pstmt.setString(4, dto.getOtt_id());
			pstmt.setString(5, dto.getOtt_pw());
			pstmt.setString(6, dto.getBank_name());
			pstmt.setString(7, dto.getBank_account());
			pstmt.setInt(8, dto.getPayback_amount());
			pstmt.setString(9, dto.getParty_id());

			cnt=pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("파티 생성 실패: " + e);
		} finally {
			DBclose.close(con, pstmt);
		}//try end
		
		
		return cnt;
		
	}//insert() end
	
	
	public PartyInfoDTO readBank(String s_mem_id) {
		
		PartyInfoDTO dto=null;
		
		try {
			con=dbopen.getConnection();//DB연결
			sql=new StringBuilder();
			sql.append(" SELECT mem_id, bank_name, bank_account ");
			sql.append(" FROM party_info ");
			sql.append(" WHERE mem_id=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, s_mem_id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new PartyInfoDTO();//커서가 가리키는 한 줄 저장
				dto.setMem_id(rs.getString("mem_id"));
				dto.setBank_name(rs.getString("bank_name"));
				dto.setBank_account(rs.getString("bank_account"));
				
			}//if end
		}catch (Exception e) {
			System.out.println("계좌 불러오기 실패: " + e);
		}finally{
			DBclose.close(con, pstmt, rs);
		}//try end
		
		return dto;
	}
	
	
	public ArrayList<PartyInfoDTO> partylist() {
		PartyInfoDTO dto=null;
		
		ArrayList<PartyInfoDTO> list=null;
		
		try {
			con=dbopen.getConnection();//DB연결
			sql=new StringBuilder();
			sql.append(" SELECT party_id, mem_id, ott_name, ott_cdate, matching_no ");
			sql.append(" FROM party_info ");
			sql.append(" ORDER BY party_id ");
			pstmt = con.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				list=new ArrayList<PartyInfoDTO>();				
				do {
					dto = new PartyInfoDTO();//커서가 가리키는 한 줄 저장
					dto.setParty_id(rs.getString("party_id"));
					dto.setMem_id(rs.getString("mem_id"));
					dto.setOtt_name(rs.getString("ott_name"));
					dto.setOtt_cdate(rs.getString("ott_cdate"));
					dto.setMatching_no(rs.getInt("matching_no"));
					list.add(dto);
				}while(rs.next());
			}//if end
			
		}catch (Exception e) {
			System.out.println("파티 리스트 불러오기 실패: " + e);
		}finally{
			DBclose.close(con, pstmt, rs);
		}//try end
		
		return list;
		
	}
	
	
	
	
	public PartyInfoDTO partyread(String party_id) {
		PartyInfoDTO dto=null;
		
		try {
			con=dbopen.getConnection();//DB연결
			sql=new StringBuilder();
			sql.append(" SELECT party_id, mem_id, ott_name, ott_price, ott_id, ott_pw, ott_cdate, bank_name, bank_account, payback_amount, payback_result, matching_no ");
			sql.append(" FROM party_info ");
			sql.append(" WHERE party_id=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, party_id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
					dto = new PartyInfoDTO();
					dto.setParty_id(rs.getString("party_id"));
					dto.setMem_id(rs.getString("mem_id"));
					dto.setOtt_name(rs.getString("ott_name"));
					dto.setOtt_price(rs.getInt("ott_price"));
					dto.setOtt_id(rs.getString("ott_id"));
					dto.setOtt_pw(rs.getString("ott_pw"));
					dto.setOtt_cdate(rs.getString("ott_cdate"));
					dto.setBank_name(rs.getString("bank_name"));
					dto.setBank_account(rs.getString("bank_account"));
					dto.setPayback_amount(rs.getInt("payback_amount"));
					dto.setPayback_result(rs.getString("payback_result"));
					dto.setMatching_no(rs.getInt("matching_no"));
			}//if end
			
		}catch (Exception e) {
			System.out.println("파티 상세보기 실패: " + e);
		}finally{
			DBclose.close(con, pstmt, rs);
		}//try end
		
		return dto;
		
	}

	
	public PartyInfoDTO read(String ott_name) {
	      PartyInfoDTO dto=null;
	      try {
	         con=dbopen.getConnection();
	         
			sql=new StringBuilder();
			sql.append(" SELECT party_id, mem_id, ott_name, ott_cdate, matching_no ");
			sql.append(" FROM party_info ");
			sql.append(" WHERE ott_cdate =(SELECT MIN(ott_cdate) FROM party_info WHERE ott_name=? AND matching_no<4  ) ");
	         
	         pstmt=con.prepareStatement(sql.toString());
	         pstmt.setString(1, ott_name);
	         
	         rs=pstmt.executeQuery();
	         if(rs.next()) {
	            dto=new PartyInfoDTO();
				dto.setParty_id(rs.getString("party_id"));
	            dto.setMem_id(rs.getString("mem_id"));
	            dto.setOtt_name(ott_name);
	            dto.setOtt_cdate(rs.getString("ott_cdate"));
	            dto.setMatching_no(rs.getInt("matching_no"));
	         }//if end
	         
	      } catch (Exception e) {
	         System.out.println("읽기 실패 : " + e);
	      }finally {
	         DBclose.close(con,pstmt,rs);
	      }//end
	      return dto;
	   }//end
	
		
	
	public int match(PartyInfoDTO dto) {
    	int cntmatch=0; //성공 또는 실패 여부 반환
		try {
			con=dbopen.getConnection();
			
	        sql=new StringBuilder();
			sql.append(" UPDATE party_info ");
			sql.append(" SET matching_no=matching_no+1 ");
			sql.append(" WHERE party_id=? ");
			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getParty_id());
			
			cntmatch=pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("매칭인원 수정 실패 : " + e);
		}finally {
			DBclose.close(con,pstmt);
		}//end
		return cntmatch;
    }//end
	
	
	
	public int autoMatching(PartyInfoDTO dto, PartyWaitingDTO PartyWaitingDTO) {
    	int cntmatch=0;  //파티원 테이블에 추가 성공 또는 실패 여부 반환
    	int waiting_no=0; //대기리스트 번호
		try {
			con=dbopen.getConnection();
			
			//1)대기리스트에 있는지 확인
	        sql=new StringBuilder();
			sql.append(" SELECT mem_id, ott_name, waiting_date, waiting_no ");
			sql.append(" FROM party_waiting ");
			sql.append(" WHERE waiting_no=(SELECT MIN(waiting_no) FROM party_waiting WHERE ott_name=?) ");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getOtt_name());
			
			System.out.println(dto.getOtt_name());
			
	         rs=pstmt.executeQuery();
	         if(rs.next()) {
	        	//2)대기리스트에 있다면 매칭을 진행함
	        	waiting_no=rs.getInt("waiting_no");
	        	String mem_id=rs.getString("mem_id");
	        	String party_id=dto.getParty_id();
	        	String party_role="파티원";
	        	
	        	//party주문번호 생성
				Date now = new Date();
				SimpleDateFormat orddateFrm = new SimpleDateFormat("yyyyMMddHHmmss");
				String nowStr = orddateFrm.format(now);
	        	
				PartyWaitingDAO waitdao=new PartyWaitingDAO();
				String party_ordnumber=waitdao.ordnoCreate(nowStr);
				
	        	//3)파티원 테이블에 추가
				sql=new StringBuilder();
				sql.append(" INSERT INTO party_member(mem_id, party_id, party_pcost, party_pdate ,party_ordnumber) ");
		        sql.append(" VALUES(?, ?, ?, now(), ? ) ");
		        pstmt=con.prepareStatement(sql.toString());
		        pstmt.setString(1, rs.getString("mem_id")); 
		        pstmt.setString(2, dto.getParty_id()); 
		        pstmt.setInt(3, dto.getOtt_price()/4+500); 
		        pstmt.setString(4, party_ordnumber); 
		        cntmatch=pstmt.executeUpdate();
		        
		        if(cntmatch==1) {//파티원 테이블에 추가되면 (주문서가 만들어지면)
		        	
		        	//4)대기리스트에서는 삭제함
		        	PartyWaitingDAO dao=new PartyWaitingDAO();
		        	dao.waitingDelete(waiting_no);

	        		//5)매칭인원은 1증가 시킴
	        		match(dto);
	        		
	        		//6)파티원 관련 정보를 구독OTT정보에 저장
	    			//구독 OTT정보 행추가
	        		
	        		// 1) 파티원과 파티장이 같이 등록될 경우 subscribe_no이 중복되는 문제 발생!
	        		// 2) 파티역할=파티장 으로만 들어감 (쿼리 수정해야함) 
	        		 
	        		SubscribeInfoDAO subdao=new SubscribeInfoDAO();
	        		SubscribeInfoDTO subdto=new SubscribeInfoDTO();
	    			String subscribe_no="";
	    			SimpleDateFormat dateFrm = new SimpleDateFormat("yyyyMMdd");
	    			nowStr = dateFrm.format(now);
	    			subscribe_no=subdao.subnoCreate(nowStr);
	    			subdto.setSubscribe_no(subscribe_no);
	    			subdto.setMem_id(mem_id);
	    			subdto.setParty_id(party_id);
	    			int subcnt=subdao.subinsert(subdto, party_role);
	    			if(subcnt==0) {
	    				System.out.println("파티원 구독정보 등록 실패");
	    			}else{
	    				System.out.println("파티원 구독정보 등록 성공");
	    			}
	    			
		        	
		        }else {
		        	System.out.println("파티원 추가 실패");
		        }
	         }else{//대기리스트에 없다면 매칭을 진행하지 않음
	        	 System.out.println("현재 대기자 없음");
	         }
			
		} catch (Exception e) {
			System.out.println("매칭인원 수정 실패 : " + e);
		}finally {
			DBclose.close(con,pstmt,rs);
		}//end
		return cntmatch;
    }//end
	
	
	
	public int matchingNoRead(PartyInfoDTO dto) {
		int matching_no=0;
		
		try {
			con=dbopen.getConnection();
			
	        sql=new StringBuilder();
			sql.append(" SELECT matching_no ");
			sql.append(" FROM party_info ");
			sql.append(" WHERE party_id=? ");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getParty_id());
			
	         rs=pstmt.executeQuery();
	         if(rs.next()) {
	        	 matching_no=rs.getInt("matching_no");
	         }
	         
	 		} catch (Exception e) {
				System.out.println("매칭인원 불러오기 실패 : " + e);
			}finally {
				DBclose.close(con,pstmt,rs);
			}//end
		
		return matching_no;
	}


	public int count(String col, String word) {
    	int totalRecord=0;
    	try {
			con=dbopen.getConnection();
			
			sql=new StringBuilder();
			sql.append(" SELECT COUNT(*) as cnt ");
			sql.append(" FROM party_info ");
			
			if(word.length()>=1) {
				String search="";
				if(col.equals("ott_name")) {
					search+= " WHERE ott_name='" + word +"' ";
				}else if(col.equals("matching_no")) {
					search+= " WHERE matching_no=" + word;
				}//if end
				sql.append(search);
			}//if end
			
			pstmt=con.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				totalRecord=rs.getInt("cnt");
			}//if end
			
		} catch (Exception e) {
			System.out.println("카운팅 실패 : " + e );
		}finally {
			DBclose.close(con,pstmt,rs);
		}//end
    	return totalRecord;
    }//end
	
	
	
	public ArrayList<PartyInfoDTO> partylist(String col, String word, int nowPage, int recordPerPage){
    	ArrayList<PartyInfoDTO> list=null;
    	
    	int startRow = ((nowPage-1) * recordPerPage) ;
    	try {
    		con=dbopen.getConnection(); 
			sql=new StringBuilder();

			sql.append(" SELECT party_id, mem_id, ott_name, ott_cdate, matching_no ");
            sql.append(" FROM party_info ");
				            
            if(word.length()!=0) { 
		        String search="";
				if(col.equals("ott_name")) {
					search+= " WHERE ott_name='" + word +"' ";
				}else if(col.equals("matching_no")) {
					search+= " WHERE matching_no=" + word;
				}//if end
				sql.append(search);	            
            }	

            sql.append(" ORDER BY party_id ");
            sql.append(" LIMIT " + startRow + ", " + recordPerPage);
			
		pstmt=con.prepareStatement(sql.toString());
	    rs=pstmt.executeQuery();
	    if(rs.next()) {
	    	list=new ArrayList<>();
	    	do {
	    		PartyInfoDTO dto = new PartyInfoDTO();//커서가 가리키는 한 줄 저장
				dto.setParty_id(rs.getString("party_id"));
				dto.setMem_id(rs.getString("mem_id"));
				dto.setOtt_name(rs.getString("ott_name"));
				dto.setOtt_cdate(rs.getString("ott_cdate"));
				dto.setMatching_no(rs.getInt("matching_no"));
				
				list.add(dto);
	    	}while(rs.next());
	    }
    		
    	}catch (Exception e) {
			System.out.println("목록 불러오기 실패 : " + e);
		}finally {
			DBclose.close(con,pstmt,rs);
		}
    	
    	return list;
    }//list() end

	
	public int updatebank(PartyInfoDTO dto) {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" UPDATE party_info ");
			sql.append(" SET bank_name = ?, bank_account = ? ");
			sql.append(" WHERE mem_id = ? ");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getBank_name());
			pstmt.setString(2, dto.getBank_account());
			pstmt.setString(3, dto.getMem_id());
			
			cnt = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("계좌 변경 실패: "+ e);
		}finally {
			DBclose.close(con, pstmt);
		}
		return cnt;
	}


	public int insertbank(PartyInfoDTO dto) {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" INSERT INTO party_info(mem_id, bank_name, bank_account) ");
			sql.append(" VALUES(?,?,?) ");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getMem_id());
			pstmt.setString(2, dto.getBank_name());
			pstmt.setString(3, dto.getBank_account());
			
			cnt = pstmt.executeUpdate();
			
		}catch (Exception e) {
			System.out.println("계좌등록 실패: "+ e);
		}finally {
			DBclose.close(con, pstmt);
		}
		return cnt;
	}
	
	
	
}//class end
