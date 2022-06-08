package kr.co.mymelon.mediagroup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import net.utility.DBClose;
import net.utility.DBOpen;

public class MediagroupDAO {
    
	private DBOpen dbopen=null;
    private Connection con=null;
    private PreparedStatement pstmt=null;
    private ResultSet rs=null;
    private StringBuilder sql=null;
    
    public MediagroupDAO() {
    	dbopen=new DBOpen();
    }//end
    
    
    public int create(MediagroupDTO dto) {
        int cnt=0;
        try {
            con=dbopen.getConnection();
            sql=new StringBuilder();
            sql.append(" INSERT INTO mediagroup(mediagroupno, title) ");
            sql.append(" VALUES( mediagroup_seq.nextval, ? ) ");
            pstmt=con.prepareStatement(sql.toString());
            pstmt.setString(1, dto.getTitle());
            cnt=pstmt.executeUpdate();            
        }catch(Exception e){
            System.out.println("미디어그룹등록실패:" + e);
        }finally{
            DBClose.close(con, pstmt);
        }
        return cnt;
    }//create() end
    
    
    public ArrayList<MediagroupDTO> list(){
        ArrayList<MediagroupDTO> list=null;
        try {
            con=dbopen.getConnection();
            sql=new StringBuilder();
            sql.append(" SELECT mediagroupno, title ");
            sql.append(" FROM mediagroup ");
            sql.append(" ORDER BY mediagroupno DESC ");
            pstmt=con.prepareStatement(sql.toString());
            rs=pstmt.executeQuery();
            if(rs.next()) {
                list=new ArrayList<MediagroupDTO>();
                do{
                    MediagroupDTO dto=new MediagroupDTO();
                    dto.setMediagroupno(rs.getInt("mediagroupno"));
                    dto.setTitle(rs.getString("title"));
                    list.add(dto);
                }while(rs.next());
            }//if end
            
        }catch(Exception e) {
            System.out.println("미디어그룹목록실패: "+e);
        }finally{
            DBClose.close(con, pstmt, rs);
        }//end    
        return list;
    }//list() end
    
    
    public MediagroupDTO read(int mediagroupno) {
        MediagroupDTO dto=null;
        try {
            con=dbopen.getConnection();
            sql=new StringBuilder();
            sql.append(" SELECT mediagroupno, title ");
            sql.append(" FROM mediagroup ");
            sql.append(" WHERE mediagroupno = ? ");
            pstmt = con.prepareStatement(sql.toString());
            pstmt.setInt(1, mediagroupno);
            rs = pstmt.executeQuery();
            if(rs.next()){
                dto=new MediagroupDTO();
                dto.setMediagroupno(rs.getInt("mediagroupno"));
                dto.setTitle(rs.getString("title"));
            }//if end            
        }catch(Exception e) {
            System.out.println("미디어그룹 상세보기 실패: "+e);
        }finally{
            DBClose.close(con, pstmt, rs);
        }//end    
        return dto;
    }//read() end
    
    
    public int update(MediagroupDTO dto) {
        int cnt=0;
        try{
            con = dbopen.getConnection();
            sql = new StringBuilder();
            sql.append(" UPDATE mediagroup ");
            sql.append(" SET title = ? ");
            sql.append(" WHERE mediagroupno = ?");
            pstmt = con.prepareStatement(sql.toString());
            pstmt.setString(1, dto.getTitle());
            pstmt.setInt(2, dto.getMediagroupno());
            cnt = pstmt.executeUpdate();
        }catch(Exception e){
            System.out.println("미디어그룹 수정실패: "+e);
        }finally{
            DBClose.close(con, pstmt);
        }//end
        return cnt;
    }//update() end
    
    
    public int delete(int mediagroupno) {
        int cnt=0;
        try {
            con = dbopen.getConnection();
            sql = new StringBuilder();
            sql.append(" DELETE FROM mediagroup ");
            sql.append(" WHERE mediagroupno = ?");
            pstmt = con.prepareStatement(sql.toString());
            pstmt.setInt(1, mediagroupno);
            cnt = pstmt.executeUpdate();
        }catch(Exception e){
            System.out.println("미디어그룹 삭제 실패: "+e);
        }finally{
            DBClose.close(con, pstmt);
        }//end
        return cnt;
    }//delete() end
    
        
    
}//class end
