package kr.co.finalproject.contlist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import net.utility.DBclose;
import net.utility.DBopen;

public class ContlistDAO {

	private DBopen dbopen=null;
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private StringBuilder sql=null;
	
	public ContlistDAO() {
		dbopen=new DBopen();
	}
	
	
	public ArrayList<ContlistDTO> contlistAll() {
		ContlistDTO dto=null;
		
		ArrayList<ContlistDTO> list=null;
		
		try {
			con=dbopen.getConnection();//DB연결
			sql=new StringBuilder();
			sql.append(" SELECT mtitle, mthum, mrate, netflix, watcha, tving, disney, mdate, cri_like, key_code, mcode ");
			sql.append(" FROM contlist ");
			sql.append(" ORDER BY RAND() ");
			pstmt = con.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				list=new ArrayList<ContlistDTO>();				
				do {
					dto = new ContlistDTO();//커서가 가리키는 한 줄 저장
					dto.setMtitle(rs.getString("mtitle"));
					dto.setMthum(rs.getString("mthum"));
					dto.setMrate(rs.getDouble("mrate"));
					dto.setNetflix(rs.getString("netflix"));
					dto.setWatcha(rs.getString("watcha"));
					dto.setTving(rs.getString("tving"));
					dto.setDisney(rs.getString("disney"));
					dto.setMdate(rs.getString("mdate"));
					dto.setCri_like(rs.getInt("cri_like"));
					dto.setKey_code(rs.getString("key_code"));
					dto.setMcode(rs.getInt("mcode"));
					list.add(dto);
				}while(rs.next());
			}//if end
			
		}catch (Exception e) {
			System.out.println("컨텐츠리스트 불러오기 실패: " + e);
		}finally{
			DBclose.close(con, pstmt, rs);
		}//try end
		
		return list;
		
	}
	
	
	public ContlistDTO contlist(int mcode) {
		ContlistDTO dto=null;
		
		try {
			con=dbopen.getConnection();//DB연결
			sql=new StringBuilder();
			sql.append(" SELECT mtitle, mthum, mrate, netflix, watcha, tving, disney, mdate, cri_like, key_code, mcode, maudio, director, actor, mtitle_eng ");
			sql.append(" FROM contlist ");
			sql.append(" WHERE mcode=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, mcode);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
					dto = new ContlistDTO();//커서가 가리키는 한 줄 저장
					dto.setMtitle(rs.getString("mtitle"));
					dto.setMthum(rs.getString("mthum"));
					dto.setMrate(rs.getDouble("mrate"));
					dto.setNetflix(rs.getString("netflix"));
					dto.setWatcha(rs.getString("watcha"));
					dto.setTving(rs.getString("tving"));
					dto.setDisney(rs.getString("disney"));
					dto.setMdate(rs.getString("mdate"));
					dto.setCri_like(rs.getInt("cri_like"));
					dto.setKey_code(rs.getString("key_code"));
					dto.setMcode(rs.getInt("mcode"));
					dto.setMaudio(rs.getString("maudio"));
					dto.setDirector(rs.getString("director"));
					dto.setActor(rs.getString("actor"));
					dto.setMtitle_eng(rs.getString("mtitle_eng"));
			}//if end
			
		}catch (Exception e) {
			System.out.println("컨텐츠리스트 불러오기 실패: " + e);
		}finally{
			DBclose.close(con, pstmt, rs);
		}//try end
		return dto;
	}
	
	
	   public ContlistDTO contlistread(int mcode) {
	    	ContlistDTO dto=null;
	       try {
	           con=dbopen.getConnection();
	           
	           sql=new StringBuilder();
	           sql.append(" SELECT review.*, contlist.*");
	           sql.append(" FROM contlist ");
	           sql.append(" LEFT OUTER JOIN review ");
	           sql.append("  ON review.mcode = contlist.mcode ");
	           sql.append("  WHERE contlist.mcode=? ");
	           sql.append(" ORDER BY contlist.mcode; ");
	           
	           pstmt=con.prepareStatement(sql.toString());
	           pstmt.setInt(1, mcode);
	           
	           rs=pstmt.executeQuery();
	           if(rs.next()) {
	        	   dto=new ContlistDTO(); // dto 생성 후  값 넣어주기.
	        	   dto.setMcode(mcode); // mcode는 그대로 받아줌.
	        	   dto.setMtitle(rs.getString("mtitle"));
	        	   dto.setMthum(rs.getString("mthum"));
	        	   dto.setMrate(rs.getDouble("mrate"));
	        	   dto.setNetflix(rs.getString("netflix"));
	        	   dto.setWatcha(rs.getString("watcha"));
	        	   dto.setTving(rs.getString("tving"));
				   dto.setDisney(rs.getString("disney"));
	        	   dto.setMdate(rs.getString("mdate"));
				   dto.setCri_like(rs.getInt("cri_like"));
	        	   dto.setKey_code(rs.getString("key_code"));
	        	   dto.setDirector(rs.getString("director"));
	        	   dto.setActor(rs.getString("actor"));
	        	   
	           }//end
	           
	       }catch (Exception e) {
	           System.out.println("컨텐츠 상세보기실패:"+e);
	       }finally {
	           DBclose.close(con, pstmt, rs);
	       }//end
	       return dto;
	   }// read() end

	   
	    public ContlistDTO KeywordRead(String key_code) {
	        ContlistDTO dto=null;
	        try {
	            con=dbopen.getConnection();
	            
	            sql=new StringBuilder();
	            sql.append(" SELECT key_name ");
	            sql.append(" FROM search_key ");
	            sql.append(" WHERE key_code=? ");
	            
	            pstmt=con.prepareStatement(sql.toString());
	            pstmt.setString(1, key_code);
	            
	            rs=pstmt.executeQuery();
	            if(rs.next()) {
	               dto=new ContlistDTO(); // dto 생성 후  값 넣어주기.
	               dto.setKey_code(rs.getString("key_code"));


	            }//end
	            
	        }catch (Exception e) {
	            System.out.println("컨텐츠 키워드 불러오기 실패:"+e);
	        }finally {
	            DBclose.close(con, pstmt, rs);
	        }//end
	        return dto;
	    }// read() end
	   
	   
	   
		public int insert(ContlistDTO dto) {
			int cnt=0;
			
			try {
				con=dbopen.getConnection();//DB연결
				sql=new StringBuilder();
				sql.append(" INSERT INTO contlist (mtitle, mthum, netflix, watcha, tving, disney, mdate, key_code, maudio, director, actor, mtitle_eng) ");
				sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, dto.getMtitle());
				pstmt.setString(2, dto.getMthum());
				pstmt.setString(3, dto.getNetflix());
				pstmt.setString(4, dto.getWatcha());
				pstmt.setString(5, dto.getTving());
				pstmt.setString(6, dto.getDisney());
				pstmt.setString(7, dto.getMdate());
				pstmt.setString(8, dto.getKey_code());
				pstmt.setString(9, dto.getMaudio());
				pstmt.setString(10, dto.getDirector());
				pstmt.setString(11, dto.getActor());
				pstmt.setString(12, dto.getMtitle_eng());
				
				cnt=pstmt.executeUpdate();
				
			} catch (Exception e) {
				System.out.println("컨텐츠 추가 실패: " + e);
			} finally {
				DBclose.close(con, pstmt);
			}//try end
			
			
			return cnt;
			
		}//insert() end
	   
		
		
		public String readDirector(String pname) {
			String pno = null;
			try {
				con = dbopen.getConnection();
				sql = new StringBuilder();
				sql.append(" SELECT pno ");
				sql.append(" FROM people ");
				sql.append(" WHERE pname=? AND pno LIKE 'D_%' ");

				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, pname);

				rs = pstmt.executeQuery();
				if (rs.next()) {
					pno = rs.getString("pno");
				}

			} catch (Exception e) {
				System.out.println("감독 불러오기 실패 : " + e);
			} finally {
				DBclose.close(con, pstmt, rs);
			}
			return pno;

		}
	
		
		public String readActor(String pname) {
			String pno = null;
			try {
				con = dbopen.getConnection();
				sql = new StringBuilder();
				sql.append(" SELECT pno ");
				sql.append(" FROM people ");
				sql.append(" WHERE pname=? AND pno LIKE 'A_%' ");

				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, pname);

				rs = pstmt.executeQuery();
				if (rs.next()) {
					pno = rs.getString("pno");
				}

			} catch (Exception e) {
				System.out.println("배우 불러오기 실패 : " + e);
			} finally {
				DBclose.close(con, pstmt, rs);
			}
			return pno;
		}
		
		
		public String readPno(String searchname) {
			String pno = null;
			try {
				con = dbopen.getConnection();
				sql = new StringBuilder();
				sql.append(" SELECT pno ");
				sql.append(" FROM people ");
				sql.append(" WHERE pname LIKE '%"+searchname+"%' ");
				sql.append("    OR pname_eng LIKE '%"+searchname+"%' ");
				
				pstmt = con.prepareStatement(sql.toString());

				rs = pstmt.executeQuery();
				if (rs.next()) {
					pno = rs.getString("pno");
				}

			} catch (Exception e) {
				System.out.println("인물 불러오기 실패 : " + e);
			} finally {
				DBclose.close(con, pstmt, rs);
			}
			return pno;
		}
		
		
		
		public String readPname(String pno) {
			String pname = null;
			try {
				con = dbopen.getConnection();
				sql = new StringBuilder();
				sql.append(" SELECT pname ");
				sql.append(" FROM people ");
				sql.append(" WHERE pno=? ");

				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, pno);

				rs = pstmt.executeQuery();
				if (rs.next()) {
					pname = rs.getString("pname");
				}

			} catch (Exception e) {
				System.out.println("이름 불러오기 실패 : " + e);
			} finally {
				DBclose.close(con, pstmt, rs);
			}
			return pname;
		}

		
		
		public int contUpdate(ContlistDTO dto) {
			int cnt=0;
			
			try {
				con=dbopen.getConnection();//DB연결
				sql=new StringBuilder();
				sql.append(" UPDATE contlist ");
				sql.append(" SET mtitle=?, mthum=?, netflix=?, watcha=?, tving=?, disney=?, ");
				sql.append(" 	 mdate=?, key_code=?, maudio=?, director=?, actor=?, mtitle_eng=? ");
				sql.append(" WHERE mcode=?  ");

				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, dto.getMtitle());
				pstmt.setString(2, dto.getMthum());
				pstmt.setString(3, dto.getNetflix());
				pstmt.setString(4, dto.getWatcha());
				pstmt.setString(5, dto.getTving());
				pstmt.setString(6, dto.getDisney());
				pstmt.setString(7, dto.getMdate());
				pstmt.setString(8, dto.getKey_code());
				pstmt.setString(9, dto.getMaudio());
				pstmt.setString(10, dto.getDirector());
				pstmt.setString(11, dto.getActor());
				pstmt.setString(12, dto.getMtitle_eng());
				pstmt.setInt(13, dto.getMcode());

				
				cnt=pstmt.executeUpdate();
				
			} catch (Exception e) {
				System.out.println("컨텐츠 수정 실패: " + e);
			} finally {
				DBclose.close(con, pstmt);
			}//try end
			
			
			return cnt;
			
		}//update() end
		
		
		
		public ArrayList<ContlistDTO> mainsearch(String searchkey, String pno, int nowPage, int recordPerPage) {
			
			ArrayList<ContlistDTO> list=null;
			ContlistDTO dto=null;
	        int startRow = ((nowPage-1) * recordPerPage);
	        
			try {
				con=dbopen.getConnection();//DB연결
				sql=new StringBuilder();
				sql.append(" SELECT mtitle, mtitle_eng, mthum, mrate, netflix, watcha, tving, disney, mdate, key_code, cri_like, mcode, director, actor ");
				sql.append(" FROM contlist ");
				sql.append(" WHERE mtitle LIKE '%"+searchkey+"%' ");
				sql.append(" 	OR mtitle_eng LIKE '%"+searchkey+"%' ");
				sql.append(" 	OR director LIKE '%"+pno+"%' ");
				sql.append(" 	OR actor LIKE '%"+pno+"%' ");
				sql.append(" ORDER BY mcode DESC ");
                sql.append(" LIMIT " + startRow + ", " + recordPerPage);
				
				pstmt = con.prepareStatement(sql.toString());

				rs = pstmt.executeQuery();
				if(rs.next()) {
					list=new ArrayList<ContlistDTO>();				
					do {
						dto = new ContlistDTO();//커서가 가리키는 한 줄 저장
						dto.setMtitle(rs.getString("mtitle"));
						dto.setMthum(rs.getString("mthum"));
						dto.setMrate(rs.getDouble("mrate"));
						dto.setNetflix(rs.getString("netflix"));
						dto.setWatcha(rs.getString("watcha"));
						dto.setTving(rs.getString("tving"));
						dto.setDisney(rs.getString("disney"));
						dto.setMdate(rs.getString("mdate"));
						dto.setCri_like(rs.getInt("cri_like"));
						dto.setKey_code(rs.getString("key_code"));
						dto.setMcode(rs.getInt("mcode"));
						dto.setActor(rs.getString("actor"));
						dto.setDirector(rs.getString("director"));
						
						list.add(dto);
					}while(rs.next());
				}//if end
				
			} catch (Exception e) {
				System.out.println("컨텐츠 검색 실패: " + e);
			} finally {
				DBclose.close(con, pstmt);
			}//try end
			
			
			return list;
			
		}//mainsearch() end
		
		
		public ArrayList<ContlistDTO> mainottsearch(String ott, String searchkey, String pno, int nowPage, int recordPerPage) {
			
			ArrayList<ContlistDTO> list=null;
			ContlistDTO dto=null;
	        int startRow = ((nowPage-1) * recordPerPage);
	        
			try {
				con=dbopen.getConnection();//DB연결
				sql=new StringBuilder();
				sql.append(" SELECT mtitle, mtitle_eng, mthum, mrate, netflix, watcha, tving, disney, mdate, key_code, cri_like, mcode, director, actor ");
				sql.append(" FROM contlist ");
				sql.append(" WHERE (mtitle LIKE '%"+searchkey+"%' ");
				sql.append(" 	OR mtitle_eng LIKE '%"+searchkey+"%' ");
				sql.append(" 	OR director LIKE '%"+pno+"%' ");
				sql.append(" 	OR actor LIKE '%"+pno+"%') ");
				
				String search="";
				if(ott.equals("netflix")) {
					search+=" AND netflix='O' ";
				}else if(ott.equals("watcha")) {
					search+=" AND watcha='O' ";
				}else if(ott.equals("tving")) {
					search+=" AND tving='O' ";
				}else if(ott.equals("disney")) {
					search+=" AND disney='O' ";
				}else {
					search+=" AND (netflix='O' OR watcha='O' OR disney='O' OR tving='O') ";
				}
				sql.append(search);
				
				sql.append(" ORDER BY mcode DESC ");
                sql.append(" LIMIT " + startRow + ", " + recordPerPage);
				
				pstmt = con.prepareStatement(sql.toString());

				rs = pstmt.executeQuery();
				if(rs.next()) {
					list=new ArrayList<ContlistDTO>();				
					do {
						dto = new ContlistDTO();//커서가 가리키는 한 줄 저장
						dto.setMtitle(rs.getString("mtitle"));
						dto.setMthum(rs.getString("mthum"));
						dto.setMrate(rs.getDouble("mrate"));
						dto.setNetflix(rs.getString("netflix"));
						dto.setWatcha(rs.getString("watcha"));
						dto.setTving(rs.getString("tving"));
						dto.setDisney(rs.getString("disney"));
						dto.setMdate(rs.getString("mdate"));
						dto.setCri_like(rs.getInt("cri_like"));
						dto.setKey_code(rs.getString("key_code"));
						dto.setMcode(rs.getInt("mcode"));
						dto.setActor(rs.getString("actor"));
						dto.setDirector(rs.getString("director"));
						
						list.add(dto);
					}while(rs.next());
				}//if end
				
			} catch (Exception e) {
				System.out.println("컨텐츠 검색 실패: " + e);
			} finally {
				DBclose.close(con, pstmt);
			}//try end
			
			
			return list;
			
		}//mainsearch() end
		
		
		
		public ArrayList<ContlistDTO> mainottsearch(String ott, String searchkey, int nowPage, int recordPerPage) {
			
			ArrayList<ContlistDTO> list=null;
			ContlistDTO dto=null;
	        int startRow = ((nowPage-1) * recordPerPage);
	        
			try {
				con=dbopen.getConnection();//DB연결
				sql=new StringBuilder();
				sql.append(" SELECT mtitle, mtitle_eng, mthum, mrate, netflix, watcha, tving, disney, mdate, key_code, cri_like, mcode, director, actor ");
				sql.append(" FROM contlist ");
				sql.append(" WHERE (mtitle LIKE '%"+searchkey+"%' ");
				sql.append(" 	OR mtitle_eng LIKE '%"+searchkey+"%') ");
				
				String search="";
				if(ott.equals("netflix")) {
					search+=" AND netflix='O' ";
				}else if(ott.equals("watcha")) {
					search+=" AND watcha='O' ";
				}else if(ott.equals("tving")) {
					search+=" AND tving='O' ";
				}else if(ott.equals("disney")) {
					search+=" AND disney='O' ";
				}else {
					search+=" AND (netflix='O' OR watcha='O' OR disney='O' OR tving='O') ";
				}
				sql.append(search);
				
				sql.append(" ORDER BY mcode DESC ");
                sql.append(" LIMIT " + startRow + ", " + recordPerPage);
				
				pstmt = con.prepareStatement(sql.toString());

				rs = pstmt.executeQuery();
				if(rs.next()) {
					list=new ArrayList<ContlistDTO>();				
					do {
						dto = new ContlistDTO();//커서가 가리키는 한 줄 저장
						dto.setMtitle(rs.getString("mtitle"));
						dto.setMthum(rs.getString("mthum"));
						dto.setMrate(rs.getDouble("mrate"));
						dto.setNetflix(rs.getString("netflix"));
						dto.setWatcha(rs.getString("watcha"));
						dto.setTving(rs.getString("tving"));
						dto.setDisney(rs.getString("disney"));
						dto.setMdate(rs.getString("mdate"));
						dto.setCri_like(rs.getInt("cri_like"));
						dto.setKey_code(rs.getString("key_code"));
						dto.setMcode(rs.getInt("mcode"));
						dto.setActor(rs.getString("actor"));
						dto.setDirector(rs.getString("director"));
						
						list.add(dto);
					}while(rs.next());
				}//if end
				
			} catch (Exception e) {
				System.out.println("컨텐츠 검색 실패: " + e);
			} finally {
				DBclose.close(con, pstmt);
			}//try end
			
			
			return list;
			
		}//mainsearch() end
		
		
		
		public ArrayList<ContlistDTO> ottRead(String ott, String col, String word, String sort,  int nowPage, int recordPerPage) {
			ContlistDTO dto=null;			
			ArrayList<ContlistDTO> list=null;
	        int startRow = ((nowPage-1) * recordPerPage);			
			try {
				con=dbopen.getConnection();//DB연결
				sql=new StringBuilder();
				sql.append(" SELECT mtitle, mthum, mrate, netflix, watcha, tving, disney, mdate, cri_like, key_code, mcode ");
				sql.append(" FROM contlist ");
				
				String search="";
				if(ott.equals("netflix")) {
					search+=" WHERE netflix='O' ";
				}else if(ott.equals("watcha")) {
					search+=" WHERE watcha='O' ";
				}else if(ott.equals("tving")) {
					search+=" WHERE tving='O' ";
				}else if(ott.equals("disney")) {
					search+=" WHERE disney='O' ";
				}else {
					search+=" WHERE (netflix='O' OR watcha='O' OR disney='O' OR tving='O') ";
				}
				
	            if(word.length()!=0) { 
					if(col.equals("key_code")) {
						search+= " AND key_code LIKE '%"+word+"%' ";						
					}else if(col.equals("pno")) {						
						search+= " AND (actor LIKE '%" + word + "%' ";
						search+= " OR director LIKE '%" + word + "%') ";
					}else if(col.equals("mdate")) {						
                        int mdate= Integer.parseInt(word);
						search+= " AND (mdate>=" + mdate + " AND mdate<="+ ( mdate+10 )+") ";
					}else if(col.equals("mrate")) {						
                        int mrate= Integer.parseInt(word);
						search+= " AND (mrate>" + (mrate-1) + " AND mrate<="+ mrate+") ";
					}					
	            }	
				
	            if(sort.length()!=0) { 
					if(sort.equals("cri_like_high")) {//좋아요 많은 순
						search+= " ORDER BY cri_like DESC ";						
					}else if(sort.equals("cri_like_low")) {//좋아요 적은 순
						search+= " ORDER BY cri_like ASC ";						
					}else if(sort.equals("mdate_high")) {//최신 개봉 순
						search+= " ORDER BY mdate DESC ";						
					}else if(sort.equals("mdate_low")) {//오래된 개봉 순
						search+= " ORDER BY mdate ASC ";						
					}
	            }	
	            
				sql.append(search);
                sql.append(" LIMIT " + startRow + ", " + recordPerPage);
				
				pstmt = con.prepareStatement(sql.toString());
				
				rs = pstmt.executeQuery();
				if(rs.next()) {
					list=new ArrayList<ContlistDTO>();				
					do {
						dto = new ContlistDTO();//커서가 가리키는 한 줄 저장
						dto.setMtitle(rs.getString("mtitle"));
						dto.setMthum(rs.getString("mthum"));
						dto.setMrate(rs.getDouble("mrate"));
						dto.setNetflix(rs.getString("netflix"));
						dto.setWatcha(rs.getString("watcha"));
						dto.setTving(rs.getString("tving"));
						dto.setDisney(rs.getString("disney"));
						dto.setMdate(rs.getString("mdate"));
						dto.setCri_like(rs.getInt("cri_like"));
						dto.setKey_code(rs.getString("key_code"));
						dto.setMcode(rs.getInt("mcode"));
						list.add(dto);
					}while(rs.next());
				}//if end
				
			}catch (Exception e) {
				System.out.println("OTT 검색 리스트 불러오기 실패: " + e);
			}finally{
				DBclose.close(con, pstmt, rs);
			}//try end
			
			return list;
			
		}

		
		public int count(String col, String word) {
	    	int cnt=0;
	    	try {
				con=dbopen.getConnection();
				
				sql=new StringBuilder();
				sql.append(" SELECT COUNT(*) as cnt ");
				sql.append(" FROM contlist ");
				
				if(word.length()>=1) {
					String search="";
					if(col.equals("mcode")) {
						search+= " WHERE mcode=" + word;
					}else if(col.equals("mtitle")) {
						search+= " WHERE mtitle LIKE '%" + word + "%' ";
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
		
		public ArrayList<ContlistDTO> list(String col, String word, int nowPage, int recordPerPage){
	    	ArrayList<ContlistDTO> list=null;
	    	
	    	int startRow = ((nowPage-1) * recordPerPage) ;
	    	try {
	    		con=dbopen.getConnection(); 
				sql=new StringBuilder();

				sql.append(" SELECT mtitle, mthum, mrate, netflix, watcha, tving, disney, mdate, key_code, cri_like, mcode, director, actor ");
	            sql.append(" FROM contlist ");
					            
	            if(word.length()!=0) { 
			        String search="";
					if(col.equals("key_code")) {
						search+= " WHERE key_code LIKE '%"+word+"%' ";						
					}else if(col.equals("pno")) {						
						search+= " WHERE actor LIKE '%" + word + "%' ";
						search+= " OR director LIKE '%" + word + "%' ";
					}else if(col.equals("mdate")) {						
                        int mdate= Integer.parseInt(word);
						search+= " WHERE mdate>=" + mdate + " AND mdate<="+ ( mdate+10 );
					}else if(col.equals("mrate")) {						
                        int mrate= Integer.parseInt(word);
						search+= " WHERE mrate>" + (mrate-1) + " AND mrate<="+ mrate;
					}
					
					sql.append(search);	            
	            }	

	            sql.append(" ORDER BY mcode DESC ");
	            sql.append(" LIMIT " + startRow + ", " + recordPerPage);
				
			pstmt=con.prepareStatement(sql.toString());
		    rs=pstmt.executeQuery();
		    if(rs.next()) {
		    	list=new ArrayList<>();
		    	do {
		    		ContlistDTO dto = new ContlistDTO();//커서가 가리키는 한 줄 저장
					dto.setMtitle(rs.getString("mtitle"));
					dto.setMthum(rs.getString("mthum"));
					dto.setMrate(rs.getDouble("mrate"));
					dto.setNetflix(rs.getString("netflix"));
					dto.setWatcha(rs.getString("watcha"));
					dto.setTving(rs.getString("tving"));
					dto.setDisney(rs.getString("disney"));
					dto.setMdate(rs.getString("mdate"));
					dto.setCri_like(rs.getInt("cri_like"));
					dto.setKey_code(rs.getString("key_code"));
					dto.setMcode(rs.getInt("mcode"));
					dto.setActor(rs.getString("actor"));
					dto.setDirector(rs.getString("director"));
					
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
		
		public ArrayList<ContlistDTO> list(String col, String word, String sort, int nowPage, int recordPerPage){
	    	ArrayList<ContlistDTO> list=null;
	    	
	    	int startRow = ((nowPage-1) * recordPerPage) ;
	    	try {
	    		con=dbopen.getConnection(); 
				sql=new StringBuilder();

				sql.append(" SELECT mtitle, mthum, mrate, netflix, watcha, tving, disney, mdate, key_code, cri_like, mcode, director, actor ");
	            sql.append(" FROM contlist ");
					            
	            if(word.length()!=0) { 
			        String search="";
					if(col.equals("key_code")) {
						search+= " WHERE key_code LIKE '%"+word+"%' ";						
					}else if(col.equals("pno")) {						
						search+= " WHERE actor LIKE '%" + word + "%' ";
						search+= " OR director LIKE '%" + word + "%' ";
					}else if(col.equals("mdate")) {						
                        int mdate= Integer.parseInt(word);
						search+= " WHERE mdate>=" + mdate + " AND mdate<="+ ( mdate+10 );
					}else if(col.equals("mrate")) {						
                        int mrate= Integer.parseInt(word);
                        if(mrate==5) {
                        	search+= " WHERE mrate=" + mrate;
                        }else{
                        	search+= " WHERE mrate>=" + mrate + " AND mrate<"+ (mrate+1);
                        }
					}					
					sql.append(search);	            
	            }	

	            if(sort.length()!=0) { 
			        String search="";
					if(sort.equals("cri_like_high")) {//좋아요 많은 순
						search+= " ORDER BY cri_like DESC ";						
					}else if(sort.equals("cri_like_low")) {//좋아요 적은 순
						search+= " ORDER BY cri_like ASC ";						
					}else if(sort.equals("mdate_high")) {//최신 개봉 순
						search+= " ORDER BY mdate DESC ";						
					}else if(sort.equals("mdate_low")) {//오래된 개봉 순
						search+= " ORDER BY mdate ASC ";						
					}
					
					sql.append(search);	            
	            }		            

	            sql.append(" LIMIT " + startRow + ", " + recordPerPage);
					            
			pstmt=con.prepareStatement(sql.toString());
		    rs=pstmt.executeQuery();
		    if(rs.next()) {
		    	list=new ArrayList<>();
		    	do {
		    		ContlistDTO dto = new ContlistDTO();//커서가 가리키는 한 줄 저장
					dto.setMtitle(rs.getString("mtitle"));
					dto.setMthum(rs.getString("mthum"));
					dto.setMrate(rs.getDouble("mrate"));
					dto.setNetflix(rs.getString("netflix"));
					dto.setWatcha(rs.getString("watcha"));
					dto.setTving(rs.getString("tving"));
					dto.setDisney(rs.getString("disney"));
					dto.setMdate(rs.getString("mdate"));
					dto.setCri_like(rs.getInt("cri_like"));
					dto.setKey_code(rs.getString("key_code"));
					dto.setMcode(rs.getInt("mcode"));
					dto.setActor(rs.getString("actor"));
					dto.setDirector(rs.getString("director"));
					
					list.add(dto);
		    	}while(rs.next());
		    }
	    		
	    	}catch (Exception e) {
				System.out.println("목록 정렬 불러오기 실패 : " + e);
			}finally {
				DBclose.close(con,pstmt,rs);
			}
	    	
	    	return list;
	    }//list() end
		
		
		
		public int count() {
	    	int cnt=0;
	    	try {
				con=dbopen.getConnection();
				
				sql=new StringBuilder();
				sql.append(" SELECT COUNT(*) as cnt ");
				sql.append(" FROM contlist ");
				
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

		
		public void mrateUpdate(double mrate, int mcode) {
	         
	         try {
	            con=dbopen.getConnection();
	            
	            sql=new StringBuilder();
	            sql.append(" update contlist ");
	            sql.append(" set mrate=?  ");
	            sql.append(" where mcode=? ");
	            
	            pstmt=con.prepareStatement(sql.toString());
	            pstmt.setDouble(1, mrate);
	            pstmt.setInt(2, mcode);
	            
	            pstmt.executeUpdate();
	         } catch (Exception e) {
	            System.out.println("평점 평균 내기 실패 : " + e);
	         }finally {
	            DBclose.close(con,pstmt);
	            
	         }//end
	      }

		
		public ArrayList<String> contentSearch(String keyword) {
			
			ArrayList<String> list=new ArrayList<String>();
			
			try {
				con=dbopen.getConnection();//DB연결
				sql=new StringBuilder();
				sql.append(" SELECT mtitle ");
				sql.append(" FROM contlist ");
				sql.append(" WHERE mtitle LIKE '%"+keyword+"%' ");
				sql.append(" ORDER BY mcode DESC ");
				
				pstmt = con.prepareStatement(sql.toString());

				rs = pstmt.executeQuery();
				if(rs.next()) {
					do {
						list.add(rs.getString("mtitle"));
					}while(rs.next());
				}//if end				
			
				sql=new StringBuilder();
				sql.append(" SELECT mtitle_eng ");
				sql.append(" FROM contlist ");
				sql.append(" WHERE mtitle_eng LIKE '%"+keyword+"%' ");
				sql.append(" ORDER BY mcode DESC ");
				
				pstmt = con.prepareStatement(sql.toString());

				rs = pstmt.executeQuery();
				if(rs.next()) {
					do {
						list.add(rs.getString("mtitle_eng"));						
					}while(rs.next());
				}//if end
				
				
			} catch (Exception e) {
				System.out.println("영화 검색 추천 목록 실패: " + e);
			} finally {
				DBclose.close(con, pstmt);
			}//try end
			
			
			return list;
			
		}//contentSearch() end

		
		
		public ArrayList<String> mainSuggest(String keyword) {
			
			ArrayList<String> list=new ArrayList<String>();
			
			try {
				con=dbopen.getConnection();//DB연결
				sql=new StringBuilder();
				sql.append(" SELECT mtitle ");
				sql.append(" FROM contlist ");
				sql.append(" WHERE mtitle LIKE '%"+keyword+"%' ");
				sql.append(" ORDER BY mcode DESC ");
				pstmt = con.prepareStatement(sql.toString());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					do {
						list.add(rs.getString("mtitle"));
					}while(rs.next());
				}//if end				
			
				sql=new StringBuilder();
				sql.append(" SELECT mtitle_eng ");
				sql.append(" FROM contlist ");
				sql.append(" WHERE mtitle_eng LIKE '%"+keyword+"%' ");
				sql.append(" ORDER BY mcode DESC ");				
				pstmt = con.prepareStatement(sql.toString());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					do {
						list.add(rs.getString("mtitle_eng"));						
					}while(rs.next());
				}//if end
				
				
				sql=new StringBuilder();
				sql.append(" SELECT pname ");
				sql.append(" FROM people ");
				sql.append(" WHERE pname LIKE '%"+keyword+"%' ");
				sql.append(" ORDER BY pname ");				
				pstmt = con.prepareStatement(sql.toString());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					do {
						list.add(rs.getString("pname"));						
					}while(rs.next());
				}//if end
				
				
				sql=new StringBuilder();
				sql.append(" SELECT pname_eng ");
				sql.append(" FROM people ");
				sql.append(" WHERE pname_eng LIKE '%"+keyword+"%' ");
				sql.append(" ORDER BY pname_eng ");				
				pstmt = con.prepareStatement(sql.toString());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					do {
						list.add(rs.getString("pname_eng"));						
					}while(rs.next());
				}//if end				
				
				
			} catch (Exception e) {
				System.out.println("메인페이지 검색 추천 목록 실패: " + e);
			} finally {
				DBclose.close(con, pstmt);
			}//try end
			
			
			return list;
			
		}//contentSearch() end
		
		
		
	    public int mcodeRead(String title) {
	        int mcode=0;
	        try {
	            con=dbopen.getConnection();
	            
	            sql=new StringBuilder();
	            sql.append(" SELECT mcode, mtitle, mtitle_eng ");
	            sql.append(" FROM contlist ");
	            sql.append(" WHERE mtitle=? OR mtitle_eng=? ");
	            
	            pstmt=con.prepareStatement(sql.toString());
	            pstmt.setString(1, title);
	            pstmt.setString(2, title);
	            
	            rs=pstmt.executeQuery();
	            if(rs.next()) {
	            	
	            	mcode=rs.getInt("mcode");

	            }//end
	            
	        }catch (Exception e) {
	            System.out.println("영화코드 불러오기 실패:"+e);
	        }finally {
	            DBclose.close(con, pstmt, rs);
	        }//end
	        return mcode;
	    }// mcodeRead() end




}//class end
