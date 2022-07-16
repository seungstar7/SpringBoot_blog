package kr.co.finalproject.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import net.utility.DBclose;
import net.utility.DBopen;
import net.utility.MyAuthenticator;

public class MemberDAO {

	private DBopen dbopen=null;
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
    private StringBuilder sql=null;

	public MemberDAO() {
		dbopen = new DBopen();
	}

	public int insert(MemberDTO dto) {
		int cnt=0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" INSERT INTO member_info(mem_id, mem_pw, mem_phone, mem_email, mem_reg, mem_birth) ");
			sql.append(" VALUES(?, ?, ?, ?, now(), ?) ");
			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getMem_id());
			pstmt.setString(2, dto.getMem_pw());
			pstmt.setString(3, dto.getMem_phone());
			pstmt.setString(4, dto.getMem_email());
			pstmt.setString(5, dto.getMem_birth());
			
			cnt = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("회원 가입 실패: "+ e);
		}finally {
			DBclose.close(con, pstmt);
		}
		return cnt;
	}
	
	public MemberDTO read(String mem_id){
		MemberDTO dto = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT mem_id, mem_pw, mem_phone, mem_email, mem_lv, mem_reg, mem_birth " );
			sql.append(" FROM member_info ");
			sql.append(" WHERE mem_id=? ");
			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, mem_id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new MemberDTO();
				dto.setMem_id(rs.getString("mem_id"));
				dto.setMem_pw(rs.getString("mem_pw"));
				dto.setMem_phone(rs.getString("mem_phone"));
				dto.setMem_email(rs.getString("mem_email"));
				dto.setMem_lv(rs.getString("mem_lv"));
				dto.setMem_reg(rs.getString("mem_reg"));
				dto.setMem_birth(rs.getString("mem_birth"));
			}
			
		}catch(Exception e) {
			System.out.println("회원 조회 실패: "+ e);
		}finally {
			DBclose.close(con, pstmt, rs);
		}
		return dto;
	}
	
	
	
	public MemberDTO readone(String mem_id){
		MemberDTO dto = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT mem_id, mem_pw, mem_phone, mem_email, mem_lv, mem_reg, mem_birth " );
			sql.append(" FROM member_info ");
			sql.append(" WHERE mem_id=? ");
			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, mem_id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new MemberDTO();
				dto.setMem_id(rs.getString("mem_id"));
				dto.setMem_pw(rs.getString("mem_pw"));
				dto.setMem_phone(rs.getString("mem_phone"));
				dto.setMem_email(rs.getString("mem_email"));
				dto.setMem_lv(rs.getString("mem_lv"));
				dto.setMem_reg(rs.getString("mem_reg"));
				dto.setMem_birth(rs.getString("mem_birth"));
			}
			
		}catch(Exception e) {
			System.out.println("회원 조회 실패: "+ e);
		}finally {
			DBclose.close(con, pstmt, rs);
		}
		return dto;
	}
	
	
	public int update(MemberDTO dto) {
		int cnt=0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" UPDATE member_info" );
			sql.append(" SET mem_pw=?, mem_phone=?, mem_email=? ");
			sql.append(" WHERE mem_pw = ? AND mem_id=? ");	
			pstmt = con.prepareStatement(sql.toString());
			
			if(!(dto.getNew_pw().equals(""))) {
            	pstmt.setString(1, dto.getNew_pw());
            } else {
            	pstmt.setString(1, dto.getMem_pw());
            }
            pstmt.setString(2, dto.getMem_phone());
            pstmt.setString(3, dto.getMem_email());
            pstmt.setString(4, dto.getMem_pw());
            pstmt.setString(5, dto.getMem_id());
            
            cnt = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("회원정보 수정 실패: "+ e);
		}finally {
			DBclose.close(con, pstmt);
		}
		return cnt;
	}
	
	
	
	public int delete(String mem_id, String mem_pw) {
		int cnt=0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" UPDATE member_info ");
			sql.append(" SET mem_lv = 'F' ");
			sql.append(" WHERE mem_id = ? AND mem_pw=? ");
			
			pstmt = con.prepareStatement(sql.toString());
			
			pstmt.setString(1, mem_id);
            pstmt.setString(2, mem_pw);
            
            cnt = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("회원 탈퇴 실패: " + e);
		}finally {
			DBclose.close(con, pstmt);
		}
		
		return cnt;
	}
	
	
	public String loginRead(String mem_id, String mem_pw) {
		String mem_lv=null;
		try {
			con=dbopen.getConnection();//DB연결

			sql=new StringBuilder();
			sql.append(" SELECT mem_lv ");
			sql.append(" FROM member_info ");
			sql.append(" WHERE mem_id=? AND mem_pw=? ");
			sql.append(" AND mem_lv IN ('A', 'B') ");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, mem_id);
			pstmt.setString(2, mem_pw);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mem_lv=rs.getString("mem_lv");
			}//if end
			
		} catch (Exception e) {
			System.out.println("로그인 실패: " + e);
		}finally{
			DBclose.close(con, pstmt, rs);;
		}//try end
		return mem_lv;
	}//loginProc() end
	
	
	
	public ArrayList<MemberDTO> memberlist(){
		ArrayList<MemberDTO> list=null;
		MemberDTO dto = null;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT mem_id, mem_pw, mem_phone, mem_email, mem_lv, mem_reg, mem_birth " );
			sql.append(" FROM member_info ");
			sql.append(" ORDER BY mem_lv, mem_id  ");

			
			pstmt=con.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				list=new ArrayList<MemberDTO>();
				do {
					dto = new MemberDTO();
					dto.setMem_id(rs.getString("mem_id"));
					dto.setMem_pw(rs.getString("mem_pw"));
					dto.setMem_phone(rs.getString("mem_phone"));
					dto.setMem_email(rs.getString("mem_email"));
					dto.setMem_lv(rs.getString("mem_lv"));
					dto.setMem_reg(rs.getString("mem_reg"));
					dto.setMem_birth(rs.getString("mem_birth"));
					list.add(dto);
				}while(rs.next());
			}
			
		}catch(Exception e) {
			System.out.println("회원 목록 조회 실패: "+ e);
		}finally {
			DBclose.close(con, pstmt, rs);
		}
		return list;
	}

	
	public int count(String col, String word) {
    	int cnt=0;
    	try {
			con=dbopen.getConnection();
			
			sql=new StringBuilder();
			sql.append(" SELECT COUNT(*) as cnt ");
			sql.append(" FROM member_info ");
			
			if(word.length()>=1) {
				String search="";
				if(col.equals("mem_id")) {
					search+= " WHERE mem_id LIKE '%" + word + "%' ";
				}else if(col.equals("mem_phone")) {
					search+= " WHERE mem_phone LIKE '%" + word + "%' ";
				}else if(col.equals("mem_email")) {
					search+= " WHERE mem_email LIKE '%" + word + "%' ";
				}else if(col.equals("mem_lv")) {
					search+= " WHERE mem_lv LIKE '%" + word + "%' ";
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
	
	public ArrayList<MemberDTO> list(String col,String word,int nowPage, int recordPerPage){
    	ArrayList<MemberDTO> list=null;
    	
    	int startRow = ((nowPage-1) * recordPerPage) + 1 ;
        int endRow   = nowPage * recordPerPage;
    	try {
    		con=dbopen.getConnection(); 
			sql=new StringBuilder();
			if(word.length()==0) { 
				sql.append(" SELECT mem_id, mem_pw, mem_phone, mem_email, mem_lv, mem_reg, mem_birth ");
		        sql.append(" FROM( SELECT mem_id, mem_pw, mem_phone, mem_email, mem_lv, mem_reg, mem_birth, @RNO := @RNO + 1 AS r ");
		        sql.append("       FROM ( ");
		        sql.append("              SELECT mem_id, mem_pw, mem_phone, mem_email, mem_lv, mem_reg, mem_birth ");
		        sql.append("              FROM member_info ORDER BY mem_lv, mem_id  ASC)A, (SELECT @RNO := 0) B ");
		        sql.append("           )C");
		        sql.append(" WHERE r>=" + startRow + " AND r<=" + endRow) ;
			}else {
				sql.append(" SELECT mem_id, mem_pw, mem_phone, mem_email, mem_lv, mem_reg, mem_birth ");
		        sql.append(" FROM( SELECT mem_id, mem_pw, mem_phone, mem_email, mem_lv, mem_reg, mem_birth, @RNO := @RNO + 1 AS r ");
		        sql.append("       FROM ( ");
		        sql.append("              SELECT mem_id, mem_pw, mem_phone, mem_email, mem_lv, mem_reg, mem_birth ");
		        sql.append("              FROM member_info  ");
		        String search="";
				if(col.equals("mem_id")) {
					search+= " WHERE mem_id LIKE '%" + word + "%' ";
				}else if(col.equals("mem_phone")) {
					search+= " WHERE mem_phone LIKE '%" + word + "%' ";
				}else if(col.equals("mem_email")) {
					search+= " WHERE mem_email LIKE '%" + word + "%' ";
				}else if(col.equals("mem_lv")) {
					search+= " WHERE mem_lv LIKE '%" + word + "%' ";
				}//if end
				sql.append(search);
		        sql.append("              				   ORDER BY mem_lv, mem_id  ASC)A, (SELECT @RNO := 0) B ");
		        sql.append("           )C");
		        sql.append(" WHERE r>=" + startRow + " AND r<=" + endRow) ;
			}//if end
		pstmt=con.prepareStatement(sql.toString());
	    rs=pstmt.executeQuery();
	    if(rs.next()) {
	    	list=new ArrayList<>();
	    	do {
	    		MemberDTO dto = new MemberDTO();
				dto.setMem_id(rs.getString("mem_id"));
				dto.setMem_pw(rs.getString("mem_pw"));
				dto.setMem_phone(rs.getString("mem_phone"));
				dto.setMem_email(rs.getString("mem_email"));
				dto.setMem_lv(rs.getString("mem_lv"));
				dto.setMem_reg(rs.getString("mem_reg"));
				dto.setMem_birth(rs.getString("mem_birth"));
				list.add(dto);
	    	}while(rs.next());
	    }
    		
    	}catch (Exception e) {
			System.out.println("목록 불러오기 실패 : " + e);
		}finally {
			DBclose.close(con,pstmt,rs);
		}
    	return list;
    }
	
	
	public int ckId(String mem_id) {
		int result = -1;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT mem_id ");
			sql.append(" FROM member_info ");
			sql.append(" WHERE mem_id=? ");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, mem_id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = 0;
			}else {
				result = 1;
			}
			
		}catch (Exception e) {
			System.out.println("아이디 체크 실패: "+e);
		}
		
		return result;
	}

	
	public int ckEmail(String mem_email) {
		int result = -1;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT mem_email ");
			sql.append(" FROM member_info ");
			sql.append(" WHERE mem_email=? ");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, mem_email);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = 0;
			}else {
				result = 1;
			}
			
		}catch (Exception e) {
			System.out.println("이메일 체크 실패: "+e);
		}
		
		return result;
	}

	
	public int ckPhone(String mem_phone) {
		int result = -1;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT mem_phone ");
			sql.append(" FROM member_info ");
			sql.append(" WHERE mem_phone=? ");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, mem_phone);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = 0;
			}else {
				result = 1;
			}
			
		}catch (Exception e) {
			System.out.println("전화번호 체크 실패: "+e);
		}
		
		return result;
	}
	
	
	public boolean findId(MemberDTO dto) {
		boolean flag = false;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" SELECT mem_id ");
			sql.append(" FROM member_info ");
			sql.append(" WHERE mem_phone=? AND mem_email=? ");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getMem_phone());
			pstmt.setString(2, dto.getMem_email());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String mem_id = rs.getString("mem_id");
				String content="※ 임시 비밀번호로 로그인 한후, 회원 정보 수정에서 비밀번호를 변경하시기 바랍니다";
                content += "<hr>";
                content += "<table border='1'>";
                content += "<tr>";
                content += "    <th>아이디</th>";
                content += "    <td>" + mem_id + "</td>";
                content += "</tr>";                    
                content += "</table>";
                
                String mailServer="mw-002.cafe24.com"; //카페24 메일서버
                Properties props=new Properties();  
                props.put("mail.smtp.host", mailServer);
                props.put("mail.smtp.auth", true);
                Authenticator myAuth=new MyAuthenticator(); //다형성
                Session sess=Session.getInstance(props, myAuth);
                
                InternetAddress[] address={ new InternetAddress(dto.getMem_email()) };
                Message msg=new MimeMessage(sess);
                msg.setRecipients(Message.RecipientType.TO, address);
                msg.setFrom(new InternetAddress("pretyimo@studydesk.co.kr"));
                msg.setSubject("MyWeb 아이디/비번 입니다");
                msg.setContent(content, "text/html; charset=UTF-8");
                msg.setSentDate(new Date());
                Transport.send(msg);
				flag= true;
			}else {
				flag = false;
			}
			
		}catch (Exception e) {
			System.out.println("아이디 찾기 실패: "+e);
		}finally {
			DBclose.close(con, pstmt, rs);
		}
		
		return flag;
	}

	public boolean findPw(MemberDTO dto) {
		boolean flag = false;
		try {
			con = dbopen.getConnection();
			String[] ch= {
                    "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
                    "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
                    "0","1","2","3","4","5","6","7","8","9"
            };//0~61 인덱스
            
            //ch배열에서 랜덤하게 10글자 발생
            StringBuilder imsiPW=new StringBuilder(); //2)임시 비밀번호
            for(int i=0; i<10; i++) {
                int num=(int)(Math.random()*ch.length); //ch[0]~ch[61]까지 존재
                imsiPW.append(ch[num]);
            }//for end
            
            //임시비밀번호로 업데이트 하기
            sql=new StringBuilder();
            sql.append(" UPDATE member_info ");
            sql.append(" SET mem_pw=? ");
            sql.append(" WHERE mem_id=? AND mem_phone=? AND mem_email=? ");
            pstmt=con.prepareStatement(sql.toString());
            pstmt.setString(1, imsiPW.toString()); //임시비밀번호
            pstmt.setString(2, dto.getMem_id());
            pstmt.setString(3, dto.getMem_phone());
            pstmt.setString(4, dto.getMem_email());
             
            int cnt=pstmt.executeUpdate();
            if(cnt==1) { //임시 비밀번호로 업데이트 되었다면, 아이디와 임시비밀번호를 이메일 전송하기
                String content="※ 임시 비밀번호로 로그인 한후, 회원 정보 수정에서 비밀번호를 변경하시기 바랍니다";
                content += "<hr>";
                content += "<table border='1'>";
                content += "<tr>";
                content += "    <th>임시비밀번호</th>";
                content += "    <td>" + imsiPW.toString() + "</td>";
                content += "</tr>";                    
                content += "</table>";
                
                String mailServer="mw-002.cafe24.com"; //카페24 메일서버
                Properties props=new Properties();  
                props.put("mail.smtp.host", mailServer);
                props.put("mail.smtp.auth", true);
                Authenticator myAuth=new MyAuthenticator(); //다형성
                Session sess=Session.getInstance(props, myAuth);
                
                InternetAddress[] address={ new InternetAddress(dto.getMem_email()) };
                Message msg=new MimeMessage(sess);
                msg.setRecipients(Message.RecipientType.TO, address);
                msg.setFrom(new InternetAddress("pretyimo@studydesk.co.kr"));
                msg.setSubject("MyWeb 아이디/비번 입니다");
                msg.setContent(content, "text/html; charset=UTF-8");
                msg.setSentDate(new Date());
                Transport.send(msg);
            }
		}catch(Exception e) {
			System.out.println("비밀번호 찾기 실패: " + e);
		}finally {
			DBclose.close(con, pstmt);
		}
		return flag;
	}

	
	public int updateLv(MemberDTO dto) {
		int cnt=0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuilder();
			sql.append(" UPDATE member_info" );
			sql.append(" SET mem_lv=? ");
			sql.append(" WHERE mem_id=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getMem_lv());
            pstmt.setString(2, dto.getMem_id());
            
            cnt = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("회원등급 수정 실패: "+ e);
		}finally {
			DBclose.close(con, pstmt);
		}
		return cnt;
	}
	
	
	
}//class end