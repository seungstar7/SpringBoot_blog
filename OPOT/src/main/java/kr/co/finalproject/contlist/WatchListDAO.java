package kr.co.finalproject.contlist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.StringTokenizer;

import net.utility.DBclose;
import net.utility.DBopen;

public class WatchListDAO {

	private DBopen dbopen=null;
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private StringBuilder sql=null;
	
	public WatchListDAO() {
		dbopen=new DBopen();
	}
	
	
	public ArrayList<WatchListDTO> watchread(String mem_id) {
		
		ArrayList<WatchListDTO> watchlist=null;
		WatchListDTO dto=null;
		
		try {
			con=dbopen.getConnection();//DB연결
			sql=new StringBuilder();
			sql.append(" SELECT mem_id, A.mcode, watch_time, watch_reg, mtitle, mthum ");
			sql.append(" FROM watch_list A JOIN contlist B ");
			sql.append(" ON A.mcode=B.mcode ");
			sql.append(" WHERE mem_id=? ");
			sql.append(" ORDER BY watch_time DESC ");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, mem_id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				watchlist= new ArrayList<WatchListDTO>();
				do {
					dto = new WatchListDTO();
					dto.setMem_id(rs.getString("mem_id"));
					dto.setMcode(rs.getInt("mcode"));
					dto.setWatch_time(rs.getString("watch_time"));
					dto.setWatch_reg(rs.getString("watch_reg"));
					dto.setMtitle(rs.getString("mtitle"));
					dto.setMthum(rs.getString("mthum"));
					watchlist.add(dto);
				}while(rs.next());
			}//if end
			
		}catch (Exception e) {
			System.out.println("시청목록 상세보기 실패: " + e);
		}finally{
			DBclose.close(con, pstmt, rs);
		}//try end
		
		return watchlist;
		
	}
	
	
	
	public ArrayList<WatchListDTO> rankRead() {
		WatchListDTO dto=null;
		ArrayList<WatchListDTO> rank=null;

		try {
			con=dbopen.getConnection();//DB연결
			sql=new StringBuilder();
			sql.append(" SELECT D.mcode, watch_cnt, mtitle, mthum ");
			sql.append(" FROM(SELECT mcode, watch_cnt ");
			sql.append(" 	  FROM( ");
			sql.append(" 		     SELECT mcode, watch_cnt, @RNO := @RNO + 1 AS r ");
			sql.append(" 		     FROM( ");
			sql.append(" 		          SELECT mcode, COUNT(*) AS watch_cnt ");
			sql.append(" 		          FROM watch_list GROUP BY mcode ORDER BY watch_cnt DESC)A, (SELECT  @RNO := 0)B ");
			sql.append(" 		     )C ");
			sql.append(" 		WHERE r>=1 AND r<=3)D JOIN contlist E ");
			sql.append(" ON D.mcode=E.mcode ");
			sql.append(" ORDER BY watch_cnt DESC ");
			
			pstmt = con.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				rank=new ArrayList<WatchListDTO>();				
				do {
					dto = new WatchListDTO();//커서가 가리키는 한 줄 저장
					dto.setMtitle(rs.getString("mtitle"));
					dto.setMcode(rs.getInt("D.mcode"));
					dto.setMthum(rs.getString("mthum"));
					rank.add(dto);
				}while(rs.next());
			}//if end
			
		}catch (Exception e) {
			System.out.println("랭킹 불러오기 실패: " + e);
		}finally{
			DBclose.close(con, pstmt, rs);
		}//try end
		return rank;		
	}

	
	
	public int create(WatchListDTO dto) {
		int cnt=0;
		try {
			con=dbopen.getConnection();
			
			sql=new StringBuilder();
			sql.append(" INSERT INTO watch_list(mem_id, mcode, watch_time ,watch_reg ) ");
			sql.append(" values( ?, ?, now(), ?) ");
			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getMem_id());	
			pstmt.setInt(2, dto.getMcode());	
			pstmt.setString(3, dto.getWatch_reg());
			
			cnt=pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("시청 목록 추가 실패 : " + e);
		}finally {
			DBclose.close(con, pstmt);
		}//end
		return cnt;
	}//create end
	
	
	
	public String watchregCreate(String dateToStr){
		
		String watch_reg="";
		String lastWatch_reg="";
		
		try {
			con=dbopen.getConnection();//DB연결
			sql=new StringBuilder();
			sql.append(" SELECT MAX(watch_reg) ");
			sql.append(" FROM watch_list ");
			sql.append(" WHERE watch_reg LIKE '" + dateToStr + "%' ");

			pstmt = con.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				lastWatch_reg=rs.getString("MAX(watch_reg)");
			}//if end
			
			if(lastWatch_reg==null) {//현재 만들어진 시청번호가 없음

				watch_reg += dateToStr + "_" + "101";
				
			}else {
				int underbar=lastWatch_reg.lastIndexOf("_");				
				int lastidxWatch_reg = Integer.parseInt(lastWatch_reg.substring(underbar+1));
				lastidxWatch_reg++;
				
				watch_reg += dateToStr + "_" + lastidxWatch_reg;
				
			}
			
		}catch (Exception e) {
			System.out.println("시청목록번호 불러오기 실패: " + e);
		}finally{
			DBclose.close(con, pstmt, rs);
		}//try end		
		
		
		return watch_reg;
	}//subnoCreate() end

	
	public ArrayList<String> genreRead(String mem_id) {
		
		ArrayList<String> genrelist=null;
		ArrayList<String> keycodelist=null;
		
		try {
			con=dbopen.getConnection();//DB연결
			sql=new StringBuilder();
			sql.append(" SELECT key_code ");
			sql.append(" FROM watch_list A JOIN contlist B ");
			sql.append(" ON A.mcode=B.mcode ");
			sql.append(" WHERE mem_id=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, mem_id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				keycodelist= new ArrayList<String>();
				do {
					keycodelist.add(rs.getString("key_code"));
					
				}while(rs.next());
			}//if end
			
			genrelist=new ArrayList<String>();

			for(int i=0; i<keycodelist.size(); i++) {
			      StringTokenizer st = new StringTokenizer(keycodelist.get(i), " || ");
			      while(st.hasMoreTokens()) { //토큰할 문자가 있는지?
			    	  String key_code=st.nextToken();
			    	  if(key_code.startsWith("G")) {
			    		  genrelist.add(key_code);
			    	  }
			      }				
			}
			
		}catch (Exception e) {
			System.out.println("선호장르 불러오기 실패: " + e);
		}finally{
			DBclose.close(con, pstmt, rs);
		}//try end
		
		return genrelist;
		
	}
	
	
	
	
	
	
	
}//class end
