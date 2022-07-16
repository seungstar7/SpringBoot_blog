package kr.co.finalproject.webmaster;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import net.utility.DBclose;
import net.utility.DBopen;

public class RecommendDAO {
	
	private DBopen dbopen=null;
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private StringBuilder sql=null;
	
	public RecommendDAO() {
		dbopen=new DBopen();
	}
	
	
	public ArrayList<ThemesDTO> readThemes() {

		ArrayList<ThemesDTO> list=null;
		
		ThemesDTO dto=null;
		
		try {
			con=dbopen.getConnection();//DB연결
			sql=new StringBuilder();
			sql.append(" SELECT t_num, t_title, t_photo ");
			sql.append(" FROM themes ");
			pstmt = con.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
					list=new ArrayList<ThemesDTO>();
				do {
					
					dto = new ThemesDTO();//커서가 가리키는 한 줄 저장					
					dto.setT_num(rs.getInt("t_num"));
					dto.setT_title(rs.getString("t_title"));
					dto.setT_photo(rs.getString("t_photo"));
					list.add(dto);
				}while(rs.next());
				
			}//if end
		}catch (Exception e) {
			System.out.println("테마 불러오기 실패: " + e);
		}finally{
			DBclose.close(con, pstmt, rs);
		}//try end
		
		return list;
	}

	
	
	public ArrayList<RecommendDTO> readRecThemes(int t_num) {

		ArrayList<RecommendDTO> list=null;
		
		RecommendDTO dto=null;
		
		try {
			con=dbopen.getConnection();//DB연결
			sql=new StringBuilder();
			sql.append(" SELECT t_num, r_num, r_title, r_date, r_content, r_photo, mcodes ");
			sql.append(" FROM rec_theme ");
			sql.append(" WHERE t_num=? ");			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, t_num);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
					list=new ArrayList<RecommendDTO>();
				do {
					
					dto = new RecommendDTO();//커서가 가리키는 한 줄 저장					
					dto.setT_num(rs.getInt("t_num"));
					dto.setR_num(rs.getInt("r_num"));
					dto.setR_title(rs.getString("r_title"));
					dto.setR_date(rs.getString("r_date"));
					dto.setR_content(rs.getString("r_content"));
					dto.setR_photo(rs.getString("r_photo"));
					dto.setMcodes(rs.getString("mcodes"));
					
					list.add(dto);
				}while(rs.next());
				
			}//if end
		}catch (Exception e) {
			System.out.println("테마별 추천글 불러오기 실패: " + e);
		}finally{
			DBclose.close(con, pstmt, rs);
		}//try end
		
		return list;
	}

	
	public ArrayList<RecommendDTO> readRecThemes() {

		ArrayList<RecommendDTO> list=null;		
		RecommendDTO dto=null;
		
		try {
			con=dbopen.getConnection();//DB연결
			sql=new StringBuilder();
			sql.append(" SELECT t_num, r_num, r_title, r_date, r_content, r_photo, mcodes ");
			sql.append(" FROM rec_theme ");
			sql.append(" ORDER BY RAND() ");			
			sql.append(" LIMIT 3 ");			
			pstmt = con.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
					list=new ArrayList<RecommendDTO>();
				do {
					
					dto = new RecommendDTO();//커서가 가리키는 한 줄 저장					
					dto.setT_num(rs.getInt("t_num"));
					dto.setR_num(rs.getInt("r_num"));
					dto.setR_title(rs.getString("r_title"));
					dto.setR_date(rs.getString("r_date"));
					dto.setR_content(rs.getString("r_content"));
					dto.setR_photo(rs.getString("r_photo"));
					dto.setMcodes(rs.getString("mcodes"));
					
					list.add(dto);
				}while(rs.next());
				
			}//if end
		}catch (Exception e) {
			System.out.println("테마별 추천글 인덱스용 불러오기 실패: " + e);
		}finally{
			DBclose.close(con, pstmt, rs);
		}//try end
		
		return list;
	}
	
	
	
	public int insert(ThemesDTO dto) {
		int cnt=0;
		
		try {
			con=dbopen.getConnection();//DB연결
			sql=new StringBuilder();
			sql.append(" INSERT INTO themes (t_title, t_photo) ");
			sql.append(" VALUES (?, ?) ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getT_title());
			pstmt.setString(2, dto.getT_photo());
			
			cnt=pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("테마 추가 실패: " + e);
		} finally {
			DBclose.close(con, pstmt);
		}//try end
		
		return cnt;
		
	}//insert() end

	
	public ThemesDTO readTheme(int t_num) {
	
		ThemesDTO dto=null;
		
		try {
			con=dbopen.getConnection();//DB연결
			sql=new StringBuilder();
			sql.append(" SELECT t_num, t_title, t_photo ");
			sql.append(" FROM themes ");
			sql.append(" WHERE t_num=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, t_num);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {					
					dto = new ThemesDTO();				
					dto.setT_num(rs.getInt("t_num"));
					dto.setT_title(rs.getString("t_title"));
					dto.setT_photo(rs.getString("t_photo"));				
			}//if end
		}catch (Exception e) {
			System.out.println("테마 1개 불러오기 실패: " + e);
		}finally{
			DBclose.close(con, pstmt, rs);
		}//try end
		
		return dto;
	}

	
	
	public RecommendDTO readRecTheme(int r_num) {
		
		RecommendDTO dto=null;
		
		try {
			con=dbopen.getConnection();//DB연결
			sql=new StringBuilder();
			sql.append(" SELECT t_num, r_num, r_title, r_date, r_content, r_photo, mcodes ");
			sql.append(" FROM rec_theme ");
			sql.append(" WHERE r_num=? ");			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, r_num);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
					dto = new RecommendDTO();//커서가 가리키는 한 줄 저장					
					dto.setT_num(rs.getInt("t_num"));
					dto.setR_num(rs.getInt("r_num"));
					dto.setR_title(rs.getString("r_title"));
					dto.setR_date(rs.getString("r_date"));
					dto.setR_content(rs.getString("r_content"));
					dto.setR_photo(rs.getString("r_photo"));
					dto.setMcodes(rs.getString("mcodes"));
			}//if end
		}catch (Exception e) {
			System.out.println("테마별 추천글 1개 불러오기 실패: " + e);
		}finally{
			DBclose.close(con, pstmt, rs);
		}//try end
		
		return dto;
	}
	
	
	public int insert(RecommendDTO dto) {
		int cnt=0;
		
		try {
			con=dbopen.getConnection();//DB연결
			sql=new StringBuilder();
			sql.append(" INSERT INTO rec_theme (r_title, r_photo, mcodes, r_content, r_date, t_num ) ");
			sql.append(" VALUES (?, ?, ?, ?, now(), ?) ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getR_title());
			pstmt.setString(2, dto.getR_photo());
			pstmt.setString(3, dto.getMcodes());
			pstmt.setString(4, dto.getR_content());
			pstmt.setInt(5, dto.getT_num());			
		
			cnt=pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("추천글 추가 실패: " + e);
		} finally {
			DBclose.close(con, pstmt);
		}//try end
		
		return cnt;
		
	}//insert() end
	
	
	public int recDelete(int r_num) {
		int cnt=0;
		
		try {
			con=dbopen.getConnection();//DB연결
			sql=new StringBuilder();
			sql.append(" DELETE FROM rec_theme ");
			sql.append(" WHERE r_num=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, r_num);			
		
			cnt=pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("추천글 삭제 실패: " + e);
		} finally {
			DBclose.close(con, pstmt);
		}//try end		
		
		return cnt;
	}
	
	
	public int readTnum(String t_title) {

		int t_num=0;
				
		try {
			con=dbopen.getConnection();//DB연결
			sql=new StringBuilder();
			sql.append(" SELECT t_num ");
			sql.append(" FROM themes ");
			sql.append(" WHERE t_title=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, t_title);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				t_num=rs.getInt("t_num");				
			}//if end
			
		}catch (Exception e) {
			System.out.println("테마 번호 불러오기 실패: " + e);
		}finally{
			DBclose.close(con, pstmt, rs);
		}//try end
		
		return t_num;
	}
	
	
	
	public int recUpdate(RecommendDTO dto) {
		int cnt=0;
		
		try {
			con=dbopen.getConnection();//DB연결
			sql=new StringBuilder();
			sql.append(" UPDATE rec_theme ");
			sql.append(" SET r_title=?, r_photo=?, mcodes=?, r_content=?, t_num=? ");
			sql.append(" WHERE r_num=?  ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getR_title());
			pstmt.setString(2, dto.getR_photo());
			pstmt.setString(3, dto.getMcodes());
			pstmt.setString(4, dto.getR_content());
			pstmt.setInt(5, dto.getT_num());
			pstmt.setInt(6, dto.getR_num());
			
			cnt=pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("추천글 수정 실패: " + e);
		} finally {
			DBclose.close(con, pstmt);
		}//try end
		
		
		return cnt;
		
	}//update() end
	
	
	
}//class end
