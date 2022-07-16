package kr.co.finalproject.contlist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import net.utility.DBclose;
import net.utility.DBopen;

public class PeopleDAO {

	private DBopen dbopen=null;
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private StringBuilder sql=null;
	
	public PeopleDAO() {
		dbopen=new DBopen();
	}
	

	
	public int insertPeople(PeopleDTO dto, String pno) {
		int cnt=0;
		
		try {
			con=dbopen.getConnection();//DB연결
			sql=new StringBuilder();
			sql.append(" INSERT INTO people (pno, pname, pphoto) ");
			sql.append(" VALUES (?, ?, ?) ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, pno);
			pstmt.setString(2, dto.getPname());
			pstmt.setString(3, dto.getPphoto());
			
			cnt=pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("인물 추가 실패: " + e);
		} finally {
			DBclose.close(con, pstmt);
		}//try end		
		
		return cnt;
		
	}//insert() end
	
	
	public String createPno(String pcode) {
		String pno="";
		String lastpno="";

		try {
			con=dbopen.getConnection();//DB연결
			sql=new StringBuilder();
			sql.append(" SELECT MAX(pno) ");
			sql.append(" FROM people ");
			sql.append(" WHERE pno LIKE '"+pcode+"%' ");

			pstmt = con.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				lastpno=rs.getString("MAX(pno)");
			}//if end
			
			int underbar=lastpno.lastIndexOf("_");			
			int lastidxpno=Integer.parseInt(lastpno.substring(underbar+1));
			lastidxpno++;
			
			if(lastidxpno<10) {
				pno+=pcode+"_00"+lastidxpno;
			}else if (lastidxpno<100) {
				pno+=pcode+"_0"+lastidxpno;
			}else {
				pno+=pcode+"_"+lastidxpno;
			}
			
		} catch (Exception e) {
			System.out.println("인물 번호 생성 실패: " + e);
		} finally {
			DBclose.close(con, pstmt, rs);
		}//try end		
		
		return pno;
		
	}//insert() end
	
	
	
	public ArrayList<String> readPno(String searchname) {
		ArrayList<String> pnolist=null;		
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT pno ");
			sql.append(" FROM people ");
			sql.append(" WHERE pname LIKE '%"+searchname+"%' ");
			
			pstmt = con.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				pnolist=new ArrayList<String>();		
				do {
					pnolist.add(rs.getString("pno"));					
				}while(rs.next());
			}

		} catch (Exception e) {
			System.out.println("인물 목록 불러오기 실패 : " + e);
		} finally {
			DBclose.close(con, pstmt, rs);
		}
		return pnolist;
	}
	
	
	
	public PeopleDTO readPeople(String pno) {

		PeopleDTO dto=null;
		
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT pno, pname, pname_eng, pphoto ");
			sql.append(" FROM people ");
			sql.append(" WHERE pno=? ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, pno);

			rs = pstmt.executeQuery();
			if (rs.next()) {

					dto = new PeopleDTO();
					dto.setPname(rs.getString("pname"));
					dto.setPname_eng(rs.getString("pname_eng"));
					dto.setPno(rs.getString("pno"));
					dto.setPphoto(rs.getString("pphoto"));
			}

		} catch (Exception e) {
			System.out.println("감독/배우 불러오기 실패 : " + e);
		} finally {
			DBclose.close(con, pstmt, rs);
		}
		return dto;

	}

	
	
	public ArrayList<PeopleDTO> list(String pcode) {
		
		ArrayList<PeopleDTO> list=null;
		PeopleDTO dto=null;
		
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT pno, pname, pname_eng, pphoto ");
			sql.append(" FROM people ");
			sql.append(" WHERE pno LIKE '%"+pcode+"%' ");

			pstmt = con.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				list=new ArrayList<PeopleDTO>();
					
				do {
					dto = new PeopleDTO();
					dto.setPname(rs.getString("pname"));
					dto.setPname_eng(rs.getString("pname_eng"));
					dto.setPno(rs.getString("pno"));
					dto.setPphoto(rs.getString("pphoto"));
					list.add(dto);
				}while(rs.next());
			}

		} catch (Exception e) {
			System.out.println("배우/감독 목록 불러오기 실패 : " + e);
		} finally {
			DBclose.close(con, pstmt, rs);
		}
		return list;
	}
	
	
	
	public ArrayList<PeopleDTO> list(String pcode, int nowPage, int recordPerPage) {
		
		ArrayList<PeopleDTO> list=null;
		PeopleDTO dto=null;
    	int startRow = ((nowPage-1) * recordPerPage) ;

		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT pno, pname, pname_eng, pphoto ");
			sql.append(" FROM people ");
			sql.append(" WHERE pno LIKE '%"+pcode+"%' ");
            sql.append(" LIMIT " + startRow + ", " + recordPerPage);

			pstmt = con.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				list=new ArrayList<PeopleDTO>();
					
				do {
					dto = new PeopleDTO();
					dto.setPname(rs.getString("pname"));
					dto.setPname_eng(rs.getString("pname_eng"));
					dto.setPno(rs.getString("pno"));
					dto.setPphoto(rs.getString("pphoto"));
					list.add(dto);
				}while(rs.next());
			}

		} catch (Exception e) {
			System.out.println("배우/감독 목록 불러오기 실패 : " + e);
		} finally {
			DBclose.close(con, pstmt, rs);
		}
		return list;
	}
	
	
	public int count(String pcode) {
		
		int totalRecord=0;

		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT count(*) as cnt ");
			sql.append(" FROM people ");
			sql.append(" WHERE pno LIKE '%"+pcode+"%' ");

			pstmt = con.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				totalRecord=rs.getInt("cnt");
			}

		} catch (Exception e) {
			System.out.println("배우/감독 카운팅 실패 : " + e);
		} finally {
			DBclose.close(con, pstmt, rs);
		}
		return totalRecord;
	}
	
	
	public int update(PeopleDTO dto) {
		
		int cnt=0;
		
		try {
			con=dbopen.getConnection();//DB연결
			sql=new StringBuilder();
			sql.append(" UPDATE people ");
			sql.append(" SET pname=?, pname_eng=?, pphoto=? ");
			sql.append(" WHERE pno=?  ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getPname());
			pstmt.setString(2, dto.getPname_eng());
			pstmt.setString(3, dto.getPphoto());
			pstmt.setString(4, dto.getPno());
			
			cnt=pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("배우/감독 수정 실패: " + e);
		} finally {
			DBclose.close(con, pstmt);
		}//try end
		
		return cnt;
	}
	
	
	
}//class end