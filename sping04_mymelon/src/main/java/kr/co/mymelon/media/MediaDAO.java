package kr.co.mymelon.media;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import net.utility.DBClose;
import net.utility.DBOpen;

public class MediaDAO {

    private DBOpen dbopen=null;
    private Connection con=null;
    private PreparedStatement pstmt=null;
    private ResultSet rs=null;
    private StringBuilder sql=null;
    
    public MediaDAO() {
    	dbopen=new DBOpen();
    }//end
    
    public int create(MediaDTO dto) {
        int cnt = 0;
        try {
            con = dbopen.getConnection();
            sql = new StringBuilder();
            sql.append(" INSERT INTO media(mediano, title, poster, filename, filesize, mediagroupno, rdate) ");
            sql.append(" VALUES( media_seq.nextval, ?, ?, ?, ?, ?, sysdate) "); 
            pstmt = con.prepareStatement(sql.toString());
            pstmt.setString(1, dto.getTitle());
            pstmt.setString(2, dto.getPoster());
            pstmt.setString(3, dto.getFilename());
            pstmt.setLong(4, dto.getFilesize());
            pstmt.setInt(5, dto.getMediagroupno());
            cnt = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("음원등록실패"+e);
        } finally {
            DBClose.close(con, pstmt);
        }//end
        return cnt;
    	
    }//create() end
    
    
    public ArrayList<MediaDTO> list(int mediagroupno) {
        ArrayList<MediaDTO> list=null;
        try {
            con=dbopen.getConnection();
            sql=new StringBuilder();
            sql.append(" SELECT mediano, title, rdate, poster, filename, filesize, mview, mediagroupno ");
            sql.append(" FROM media ");
            sql.append(" WHERE mview='Y' AND mediagroupno=? ");
            sql.append(" ORDER BY mediano DESC ");
            pstmt=con.prepareStatement(sql.toString());
            pstmt.setInt(1, mediagroupno);
            rs=pstmt.executeQuery();
            if(rs.next()) {
                list=new ArrayList<>();
                do {
                    MediaDTO dto=new MediaDTO();
                    dto.setMediano(rs.getInt("mediano"));
                    dto.setTitle(rs.getString("title"));
                    dto.setRdate(rs.getString("rdate"));
                    dto.setPoster(rs.getString("poster"));
                    dto.setFilename(rs.getString("filename"));
                    dto.setFilesize(rs.getLong("filesize"));
                    dto.setMview(rs.getString("mview"));
                    dto.setMediagroupno(rs.getInt("mediagroupno"));
                    list.add(dto);
                }while(rs.next());
            }//if end
        }catch(Exception e){
            System.out.println("media목록 실패:"+e);
         }finally{
            DBClose.close(con, pstmt, rs);
         }//end
         return list;
    	
    }//list() end
    
    
    public MediaDTO read(int mediano) {
        MediaDTO dto = null;
        try {
          con = dbopen.getConnection();
          sql = new StringBuilder();
          sql.append(" SELECT mediano, title, rdate, poster, filename, filesize, mview, mediagroupno ");
          sql.append(" FROM media ");
          sql.append(" WHERE mediano = ? "); 
          pstmt = con.prepareStatement(sql.toString());
          pstmt.setInt(1, mediano);
          rs = pstmt.executeQuery();
          if(rs.next()) {
            dto = new MediaDTO();
            dto.setMediano(rs.getInt("mediano"));
            dto.setTitle(rs.getString("title"));
            dto.setRdate(rs.getString("rdate"));
            dto.setPoster(rs.getString("poster"));
            dto.setFilename(rs.getString("filename"));
            dto.setFilesize(rs.getLong("filesize"));
            dto.setMview(rs.getString("mview"));
            dto.setMediagroupno(rs.getInt("mediagroupno"));
          }//if end

        } catch (Exception e) {
            System.out.println("상세보기실패"+e);
        } finally {
            DBClose.close(con, pstmt, rs);
        }//end
        return dto;
    }//read() end
    
    
    public int delete(int mediano) {
        int cnt = 0;
        try {
          con = dbopen.getConnection();
          sql = new StringBuilder();
          sql.append(" DELETE FROM media" );
          sql.append(" WHERE mediano=? ");  
          pstmt = con.prepareStatement(sql.toString());
          pstmt.setInt(1, mediano);
          cnt = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("삭제실패"+e);
        } finally {
            DBClose.close(con, pstmt);
        }//end
        return cnt;
    }//delete() end
    
    
    public int update(MediaDTO dto) {
        int cnt = 0;
        try {
          con = dbopen.getConnection();
          sql = new StringBuilder();
          sql.append(" UPDATE media ");
          sql.append(" SET title=?, poster=?, filename=?, filesize=? ");
          sql.append(" WHERE mediano=? "); 
          pstmt = con.prepareStatement(sql.toString());
          pstmt.setString(1, dto.getTitle());
          pstmt.setString(2, dto.getPoster());
          pstmt.setString(3, dto.getFilename());
          pstmt.setLong(4, dto.getFilesize());
          pstmt.setInt(5, dto.getMediano());
          cnt = pstmt.executeUpdate();
        } catch (Exception e) {
           System.out.println("수정실패"+e);
        } finally {
            DBClose.close(con, pstmt);
        }//end
        return cnt;
    }//update end
    
  
    
    
    
}//class end
