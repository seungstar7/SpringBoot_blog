package kr.co.finalproject.contentcri;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import kr.co.finalproject.contlist.ContlistDTO;
import net.utility.DBclose;
import net.utility.DBopen;

public class ContentcriDAO {
	
	private DBopen dbopen=null;
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
    private StringBuilder sql=null;
    
    public ContentcriDAO() {
	
		dbopen = new DBopen();
	}
    
    public int insert(ContentcriDTO dto) {
    	int cnt = 0;
    	try {
    		con = dbopen.getConnection();
    		sql = new StringBuilder();
    		sql.append(" INSERT INTO content_critic(mcode, cri_like, cri_watch, cri_point, mem_id) ");
    		sql.append(" VALUES(?, ?, ?, ?, ?) ");
    		
    		pstmt= con.prepareStatement(sql.toString());
    		pstmt.setInt(1, dto.getMcode());
    		pstmt.setInt(2, dto.getCri_like());
    		pstmt.setInt(3, dto.getCri_watch());
    		pstmt.setInt(4, dto.getCri_point());
    		pstmt.setString(5, dto.getMem_id());
    		
    		cnt = pstmt.executeUpdate();
    	}catch(Exception e) {
			System.out.println("컨텐츠 평가 등록 실패: "+ e);
		}finally {
			DBclose.close(con, pstmt);
		}
		return cnt;
    		
    }//insert() end    

    
    public int like_update(ContentcriDTO dto) {
    	int cnt = 0;
    	try {
    		con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" UPDATE content_critic ");
			sql.append(" SET cri_like =? ");
			sql.append(" WHERE mcode=? and mem_id=? ");
			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, dto.getCri_like());
			pstmt.setInt(2, dto.getMcode());
			pstmt.setString(3, dto.getMem_id());
			
			cnt = pstmt.executeUpdate();
			
    	}catch (Exception e) {
			System.out.println("좋아요 수정 실패: "+ e);
		}finally {
			DBclose.close(con, pstmt);
		}
    	
    	return cnt;
    }//like_update() end
    
    	
    public int listlike_update(ContlistDTO dto, int like_check) {
    	int cnt = 0;
    	try {
    		con = dbopen.getConnection();
			sql = new StringBuilder();
			if(like_check == 1) {
				sql.append(" UPDATE contlist ");
				sql.append(" SET cri_like = cri_like +1 ");
				sql.append(" WHERE mcode = ? ");
			} else {
				sql.append(" UPDATE contlist ");
				sql.append(" SET cri_like = cri_like-1 ");
				sql.append(" WHERE mcode = ? ");
			}
			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, dto.getMcode());
			
			cnt = pstmt.executeUpdate();
			System.out.println("dao 성공");
    	}catch (Exception e) {
			System.out.println("컨텐츠 좋아요 합 수정 실패: "+ e);
		}finally {
			DBclose.close(con, pstmt);
		}
    	
    	return cnt;
    }//listlike_update() end

    
    
    public int watch_update(ContentcriDTO dto) {
		int cnt = 0;
    	try {
    		con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" UPDATE content_critic ");
			sql.append(" SET cri_watch =? ");
			sql.append(" WHERE mcode=? and mem_id=? ");
			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, dto.getCri_watch());
			pstmt.setInt(2, dto.getMcode());
			pstmt.setString(3, dto.getMem_id());
			
			cnt = pstmt.executeUpdate();
			
    	}catch (Exception e) {
			System.out.println("봤어요 수정 실패: "+ e);
		}finally {
			DBclose.close(con, pstmt);
		}
    	
    	
    	return cnt;
	}//watch_update() end
	
	public int point_update(ContentcriDTO dto) {
		int cnt = 0;
    	try {
    		con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" UPDATE content_critic ");
			sql.append(" SET cri_point =? ");
			sql.append(" WHERE mcode=? and mem_id=? ");
			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, dto.getCri_point());
			pstmt.setInt(2, dto.getMcode());
			pstmt.setString(3, dto.getMem_id());
			
			cnt = pstmt.executeUpdate();
			
    	}catch (Exception e) {
			System.out.println("찜하기 수정 실패: "+ e);
		}finally {
			DBclose.close(con, pstmt);
		}
    	
    	
    	return cnt;
	}//point_update() end

	public ContentcriDTO read(int mcode, String mem_id) {
		ContentcriDTO dto = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT mcode, mem_id, cri_like, cri_watch, cri_point ");
			sql.append(" FROM content_critic ");
			sql.append(" WHERE mcode=? AND mem_id=?");
			
			pstmt= con.prepareStatement(sql.toString());
			pstmt.setInt(1, mcode);
			pstmt.setString(2, mem_id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new ContentcriDTO();
				dto.setCri_like(rs.getInt("cri_like"));
				dto.setCri_watch(rs.getInt("cri_watch"));
				dto.setCri_point(rs.getInt("cri_point"));
			}
		}catch(Exception e) {
			System.out.println("조회 실패: "+ e);
		}
		
		return dto;
	}//read() end

	
	public ArrayList<ContlistDTO> likecontent(String mem_id) {
		ContlistDTO dto = null;
		ArrayList<ContlistDTO> list = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT contlist.mcode, mthum, mtitle, netflix, watcha, tving, disney, mdate, mrate  ");
			sql.append(" FROM contlist JOIN content_critic ");
			sql.append(" ON contlist.mcode = content_critic.mcode ");
			sql.append(" WHERE content_critic.cri_like = 1 AND mem_id = ? ");
			sql.append(" ORDER BY contlist.mcode ASC ");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				list=new ArrayList<ContlistDTO>();
				do {
					dto = new ContlistDTO();
					dto.setMcode(rs.getInt("mcode"));
					dto.setMthum(rs.getString("mthum"));
					dto.setMtitle(rs.getString("mtitle"));
					dto.setNetflix(rs.getString("netflix"));
					dto.setWatcha(rs.getString("watcha"));
					dto.setTving(rs.getString("tving"));
					dto.setDisney(rs.getString("disney"));
					dto.setMdate(rs.getString("mdate"));
					dto.setMrate(rs.getDouble("mrate"));
					list.add(dto);
				}while(rs.next());
			}
			
		}catch(Exception e) {
			System.out.println("좋아요 목록 실패: "+ e);
		}finally {
			DBclose.close(con, pstmt, rs);
		}
		
		return list;
	}
	
	public ArrayList<ContlistDTO> pointcontent(String mem_id) {
		ContlistDTO dto = null;
		ArrayList<ContlistDTO> list = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT contlist.mcode, mthum, mtitle, netflix, watcha, tving, disney, mdate, mrate  ");
			sql.append(" FROM contlist JOIN content_critic ");
			sql.append(" ON contlist.mcode = content_critic.mcode ");
			sql.append(" WHERE content_critic.cri_point = 1 AND mem_id = ? ");
			sql.append(" ORDER BY contlist.mcode ASC ");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				list=new ArrayList<ContlistDTO>();
				do {
					dto = new ContlistDTO();
					dto.setMcode(rs.getInt("mcode"));
					dto.setMthum(rs.getString("mthum"));
					dto.setMtitle(rs.getString("mtitle"));
					dto.setNetflix(rs.getString("netflix"));
					dto.setWatcha(rs.getString("watcha"));
					dto.setTving(rs.getString("tving"));
					dto.setDisney(rs.getString("disney"));
					dto.setMdate(rs.getString("mdate"));
					dto.setMrate(rs.getDouble("mrate"));
					list.add(dto);
				}while(rs.next());
			}
			
		}catch(Exception e) {
			System.out.println("찜하기 목록 실패: "+ e);
		}finally {
			DBclose.close(con, pstmt, rs);
		}
		
		return list;
	}
	
	
}//class end