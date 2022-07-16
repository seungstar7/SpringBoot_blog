package kr.co.finalproject.search;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import net.utility.DBclose;
import net.utility.DBopen;

public class SearchKeyDAO {

	private DBopen dbopen=null;
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private StringBuilder sql=null;
	
	public SearchKeyDAO() {
		dbopen=new DBopen();
	}
	
	
	public ArrayList<SearchKeyDTO> readall() {
		
		SearchKeyDTO dto=null;
		ArrayList<SearchKeyDTO> list=null;
		
		try {
			con=dbopen.getConnection();//DB연결
			sql=new StringBuilder();
			sql.append(" SELECT key_name, key_code ");
			sql.append(" FROM search_key ");
			pstmt = con.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				list = new ArrayList<SearchKeyDTO>();
				do {
					dto = new SearchKeyDTO();//커서가 가리키는 한 줄 저장
					dto.setKey_code(rs.getString("key_code"));
					dto.setKey_name(rs.getString("key_name"));
					list.add(dto);
				}while(rs.next());
			}//if end
		}catch (Exception e) {
			System.out.println("키워드 불러오기 실패: " + e);
		}finally{
			DBclose.close(con, pstmt, rs);
		}//try end
		
		return list;
	}

	
	
	public ArrayList<SearchKeyDTO> readRandom() {
		
		SearchKeyDTO dto=null;
		ArrayList<SearchKeyDTO> list=null;
		
		try {
			con=dbopen.getConnection();//DB연결
			sql=new StringBuilder();
			sql.append(" SELECT key_name, key_code ");
			sql.append(" FROM search_key ");
			sql.append(" ORDER BY RAND() ");			
			pstmt = con.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				list = new ArrayList<SearchKeyDTO>();
				do {
					dto = new SearchKeyDTO();//커서가 가리키는 한 줄 저장
					dto.setKey_code(rs.getString("key_code"));
					dto.setKey_name(rs.getString("key_name"));
					list.add(dto);
				}while(rs.next());
			}//if end
		}catch (Exception e) {
			System.out.println("키워드 불러오기 실패: " + e);
		}finally{
			DBclose.close(con, pstmt, rs);
		}//try end
		
		return list;
	}


	public String SearchKeyAll(String key_code) {
		String key_name = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT key_name ");
			sql.append(" FROM search_key ");
			sql.append(" WHERE key_code=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, key_code);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				key_name = rs.getString("key_name");
			}

		} catch (Exception e) {
			System.out.println("키워드 불러오기 실패 : " + e);
		} finally {
			DBclose.close(con, pstmt, rs);
		} // end
		return key_name;
	} // SearchKeyAll() 끝
	
	
	
	public SearchKeyDTO SearchKey(String key_code) {
		SearchKeyDTO dto = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT key_name, key_code ");
			sql.append(" FROM search_key ");
			sql.append(" WHERE key_code=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, key_code);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto=new SearchKeyDTO();
				dto.setKey_name(rs.getString("key_name"));
				dto.setKey_code(rs.getString("key_code"));
			}

		} catch (Exception e) {
			System.out.println("키워드 불러오기 실패 : " + e);
		} finally {
			DBclose.close(con, pstmt, rs);
		} // end
		return dto;
	} // SearchKeyAll() 끝

	
	
	public String SearchKeyCode(String key_name) {
		String key_code = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT key_code ");
			sql.append(" FROM search_key ");
			sql.append(" WHERE key_name=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, key_name);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				key_code = rs.getString("key_code");
			}

		} catch (Exception e) {
			System.out.println("키워드 불러오기 실패 : " + e);
		} finally {
			DBclose.close(con, pstmt, rs);
		} // end
		return key_code;
	} // SearchKeyAll() 끝
	
	
	
	public ArrayList<SearchKeyDTO> readGene() {
		
		SearchKeyDTO dto=null;
		ArrayList<SearchKeyDTO> list=null;
		
		try {
			con=dbopen.getConnection();//DB연결
			sql=new StringBuilder();
			sql.append(" SELECT key_name, key_code ");
			sql.append(" FROM search_key ");
			sql.append(" WHERE key_code LIKE 'G%' ");
			pstmt = con.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				list = new ArrayList<SearchKeyDTO>();
				do {
					dto = new SearchKeyDTO();//커서가 가리키는 한 줄 저장
					dto.setKey_code(rs.getString("key_code"));
					dto.setKey_name(rs.getString("key_name"));
					list.add(dto);
				}while(rs.next());
			}//if end
		}catch (Exception e) {
			System.out.println("장르 불러오기 실패: " + e);
		}finally{
			DBclose.close(con, pstmt, rs);
		}//try end
		
		return list;
	}	
	
	
	
	
	
}//class end
