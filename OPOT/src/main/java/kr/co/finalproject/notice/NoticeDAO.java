package kr.co.finalproject.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import net.utility.DBclose;
import net.utility.DBopen;

public class NoticeDAO {
	
	private DBopen dbopen=null;
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
    private StringBuilder sql=null;
    
    public NoticeDAO() {
    	dbopen = new DBopen();
    }
    
    public int create(NoticeDTO dto) {
		int cnt=0;
		try {
			con=dbopen.getConnection();
			
			sql=new StringBuilder();
			sql.append(" INSERT INTO notice(n_title, n_date, n_content) ");
			sql.append(" values( ?, now(), ?) ");
			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getN_title());	
			pstmt.setString(2, dto.getN_content());	
			
			cnt=pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("추가 실패 : " + e);
		}finally {
			DBclose.close(con, pstmt);
		}//end
		return cnt;
	}//create end
    
    public NoticeDTO read(int n_num) {
		
		 NoticeDTO dto=null;
		
		try {
			con=dbopen.getConnection();//DB연결
			sql=new StringBuilder();
			sql.append(" SELECT n_num, n_title, n_date, n_content, n_readcnt ");
			sql.append(" FROM notice ");
			sql.append(" WHERE n_num=? ");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, n_num);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new NoticeDTO();//커서가 가리키는 한 줄 저장
				dto.setN_num(n_num);
				dto.setN_title(rs.getString("n_title"));
				dto.setN_date(rs.getString("n_date"));
				dto.setN_content(rs.getString("n_content"));	
				dto.setN_readcnt(rs.getInt("n_readcnt"));
				
			}//if end
		}catch (Exception e) {
			System.out.println("공지 불러오기 실패: " + e);
		}finally{
			DBclose.close(con, pstmt, rs);
		}//try end
		
		return dto;
	}
    
    public ArrayList<NoticeDTO> list(){
		ArrayList<NoticeDTO> list=null;
		try {
			con=dbopen.getConnection();
			
			sql=new StringBuilder();
			sql.append(" select n_num, n_title, n_date, n_content, n_readcnt ");
			sql.append(" from notice ");
			sql.append(" order by n_num asc ");
			
			
			pstmt=con.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				list=new ArrayList<NoticeDTO>();
				do {
					NoticeDTO dto=new NoticeDTO();//한줄담기
					dto.setN_num(rs.getInt("n_num"));
					dto.setN_title(rs.getString("n_title"));;
					dto.setN_date(rs.getString("n_date"));;
					dto.setN_content(rs.getString("n_content"));;
					dto.setN_readcnt(rs.getInt("n_readcnt"));;
					list.add(dto); //list에 모으기
				}while(rs.next());
			}else {
				list=null;
			}//if end
			
		} catch (Exception e) {
			System.out.println("목록 실패 : " + e);
		}finally {
			DBclose.close(con,pstmt,rs);
		}//end		
		return list;
	}//list() end
    
    public void incrementCnt(int n_num) {
		
		try {
			con=dbopen.getConnection();
			
			sql=new StringBuilder();
			sql.append(" update notice ");
			sql.append(" set n_readcnt=n_readcnt+1  ");
			sql.append(" where n_num=? ");
			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, n_num);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("목록 실패 : " + e);
		}finally {
			DBclose.close(con,pstmt,rs);
			
		}//end
	}//incrementCnt end
    
    public int delete(int n_num){
		int cnt=0; //성공 또는 실패 여부 반환
		try {
			con=dbopen.getConnection();
			
			sql=new StringBuilder();
			sql.append(" DELETE FROM notice ");
			sql.append(" WHERE n_num=? ");
			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, n_num);
			cnt=pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("행삭제 실패 : " + e);
		}finally {
			DBclose.close(con,pstmt);
		}//end
		return cnt;
	}//delete end
    
    
    public int updateproc(NoticeDTO dto,int n_num) {
		int cnt=0; //성공 또는 실패 여부 반환
		try {

			con=dbopen.getConnection();
			sql=new StringBuilder();
			sql.append(" UPDATE notice ");
			sql.append(" SET n_title=?, n_content=? ");
			sql.append(" WHERE n_num=? ");
			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getN_title());
			pstmt.setString(2, dto.getN_content());
			pstmt.setInt(3, n_num);
			
			//con=dbopen.getConnection(); 실수 하시건 같아요
			cnt=pstmt.executeUpdate(); //실행 명령어가 누락되었습니다. 
			
		} catch (Exception e) {
			System.out.println("행수정 실패 : " + e);
		}finally {
			DBclose.close(con,pstmt);
		}//end
		return cnt;
	}//updateproc() end
    
    public int count(String col, String word) {
    	int cnt=0;
    	try {
			con=dbopen.getConnection();
			
			sql=new StringBuilder();
			sql.append(" SELECT COUNT(*) as cnt ");
			sql.append(" FROM notice ");
			
			if(word.length()>=1) {
				String search="";
				if(col.equals("n_title_n_content")) {
					search+= " WHERE n_title LIKE '%" + word + "%' ";
					search+= " OR n_content LIKE '%" + word + "%' ";
				}else if(col.equals("n_title")) {
					search+= " WHERE n_title LIKE '%" + word + "%' ";
				}else if(col.equals("n_content")) {
					search+= " WHERE n_content LIKE '%" + word + "%' ";
				}//if end
				sql.append(search);
			}//if end
			
			pstmt=con.prepareStatement(sql.toString());
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
    
    public int count() {
    	int cnt=0;
    	try {
			con=dbopen.getConnection();
			
			sql=new StringBuilder();
			sql.append(" SELECT COUNT(*) as cnt ");
			sql.append(" FROM notice ");
			
			pstmt=con.prepareStatement(sql.toString());
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
    
  
    public ArrayList<NoticeDTO> list(String col,String word, int nowPage, int recordPerPage){
    	ArrayList<NoticeDTO> list=null;
    	
        int startRow = ((nowPage-1) * recordPerPage);			
    	try {
    		con=dbopen.getConnection(); 
			sql=new StringBuilder();
			
			sql.append(" SELECT n_num, n_title, n_date, n_content, n_readcnt ");
			sql.append(" FROM notice ");

			
			if(word.length()!=0) { 
				
				String search="";
				if(col.equals("n_title_n_content")) {
					search+= " WHERE n_title LIKE '%" + word + "%' ";
					search+= " OR n_content LIKE '%" + word + "%' ";
				}else if(col.equals("n_title")) {
					search+= " WHERE n_title LIKE '%" + word + "%' ";
				}else if(col.equals("n_content")) {
					search+= " WHERE n_content LIKE '%" + word + "%' ";
				}//if end
				sql.append(search);
			
			}
        sql.append(" ORDER BY n_date DESC ");
        sql.append(" LIMIT " + startRow + ", " + recordPerPage);
		
        pstmt=con.prepareStatement(sql.toString());
	    rs=pstmt.executeQuery();
	    if(rs.next()) {
	    	list=new ArrayList<>();
	    	do {
	    		NoticeDTO dto=new NoticeDTO();
	    		dto.setN_num(rs.getInt("n_num"));
				dto.setN_title(rs.getString("n_title"));;
				dto.setN_date(rs.getString("n_date"));;
				dto.setN_content(rs.getString("n_content"));;
				dto.setN_readcnt(rs.getInt("n_readcnt"));;
				list.add(dto); //list에 모으기
	    	}while(rs.next());
	    }
    		
    	}catch (Exception e) {
			System.out.println("목록 불러오기 실패 : " + e);
		}finally {
			DBclose.close(con,pstmt,rs);
		}
    	
    	return list;
    }//list3() end



}//class end