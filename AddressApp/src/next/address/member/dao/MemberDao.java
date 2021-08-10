package next.address.member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import next.address.member.model.MemberVO;

public class MemberDao {
	
	public boolean insertMember(MemberVO member) {		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet  rs = null;
		StringBuilder sb = new StringBuilder();
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "java", "oracle");

			sb.append("INSERT INTO member2 (               " ) ;
			sb.append("		    	mem_id  , mem_pass , mem_name " );
			sb.append("		     , mem_bir , mem_hp , mem_mail ");
			sb.append("		     , mem_job , mem_mileage ");
			sb.append("	) VALUES ( ?, ? , ? ");
			sb.append("	          , ?, ?, ? ");
			sb.append("	          , ?, ?) ");
			System.out.println(sb.toString().replaceAll("\s{2,}",""));
			pstmt = conn.prepareStatement(sb.toString());
			
			// 4. 구문객체 실행 밑 결과 
			// 바인드변수 설정
			int idx = 1;
			pstmt.setString(idx++, member.getMemId());
			pstmt.setString(idx++, member.getMemPass());
			pstmt.setString(idx++, member.getMemName());
			pstmt.setString(idx++, member.getMemBir());
			pstmt.setString(idx++, member.getMemHp());
			pstmt.setString(idx++, member.getMemMail());
			pstmt.setString(idx++, member.getMemJob());
			pstmt.setInt(idx++, member.getMemMileage());
			int cnt = pstmt.executeUpdate();
			if(cnt>0) {
				return true;
			}
			return false;
		}catch (SQLException e) {
			System.out.println("작업 중 오류 발생 : " + e.getMessage());
			e.printStackTrace();
		}finally {
			if(rs != null)try {rs.close();}catch (SQLException e) {}
			if(pstmt != null)try {pstmt.close();}catch (SQLException e) {}
			if(conn != null)try {conn.close();}catch (SQLException e) {}
		}
		return false;
	}
	
	public boolean updateMember(MemberVO member) {		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet  rs = null;
		StringBuilder sb = new StringBuilder();
		try {			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "java", "oracle"); 
			sb.append("UPDATE member2 ");
			sb.append("	    SET mem_pass = ?  , mem_hp = ? ");
			sb.append("           , mem_mail = ? , mem_job = ?");
			sb.append("      WHERE mem_id = ?                          ");
			System.out.println(sb.toString().replaceAll("\s{2,}",""));
			pstmt = conn.prepareStatement(sb.toString());
			int idx = 1;
			pstmt.setString(idx++, member.getMemPass());
			pstmt.setString(idx++, member.getMemHp());
			pstmt.setString(idx++, member.getMemMail());
			pstmt.setString(idx++, member.getMemJob());
			pstmt.setString(idx++, member.getMemId());
			return true;
		}catch (SQLException e) {
			System.out.println("작업 중 오류 발생 : " + e.getMessage());
			e.printStackTrace();
		}finally {
			if(rs != null)try {rs.close();}catch (SQLException e) {}
			if(pstmt != null)try {pstmt.close();}catch (SQLException e) {}
			if(conn != null)try {conn.close();}catch (SQLException e) {}
		}
		return false;
	}
	
	/**
	 * 회원 조회 
	 * <b>해당 회원이 존재하지 않는 경우 null 리턴</b>
	 * @param id
	 * @return
	 */
	public MemberVO getMember(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet  rs = null;
		StringBuilder sb = new StringBuilder();
		MemberVO member = null;
		try {			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "java", "oracle");
			sb.append("SELECT mem_id , mem_name" );
			sb.append("     , TO_CHAR(mem_bir,'YYYY.MM.DD') as mem_bir ");
			sb.append("     , mem_hp , mem_mail  " );
			sb.append("     , mem_job, mem_mileage        " );
			sb.append("FROM member2                       " );
			sb.append("WHERE mem_id = ?                   " );
			System.out.println(sb.toString().replaceAll("\s{2,}",""));
			
			pstmt = conn.prepareStatement(sb.toString()); 
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member = new MemberVO();
				// Table -> VO (POJO) 객체
				member.setMemId(rs.getString("mem_id"));
				//member.setMemPass(rs.getString("mem_pass"));
				member.setMemName(rs.getString("mem_name"));
				member.setMemBir(rs.getString("mem_bir"));
				member.setMemHp(rs.getString("mem_hp"));
				member.setMemMail(rs.getString("mem_mail"));
				member.setMemJob(rs.getString("mem_job"));
				member.setMemMileage(rs.getInt("mem_mileage"));
			}
			return member;
		} catch (SQLException e) {
			System.out.println("작업 중 오류 발생 : " + e.getMessage());
			e.printStackTrace();
			return null;
		}finally {
			if(rs != null)try {rs.close();}catch (SQLException e) {}
			if(pstmt != null)try {pstmt.close();}catch (SQLException e) {}
			if(conn != null)try {conn.close();}catch (SQLException e) {}
		}
	}
	
	/**
	 * 전체 회원 목록 리턴 
	 * @return
	 */
	public List getMemberList() {		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet  rs = null;
		StringBuilder sb = new StringBuilder();
		List<MemberVO> list = new ArrayList<MemberVO>();		
		try {			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "java", "oracle");
			sb.append("SELECT mem_id, mem_name" );
			sb.append("     , TO_CHAR(mem_bir,'YYYY.MM.DD') as mem_bir ");
			sb.append("     , mem_hp , mem_mail  " );
			sb.append("     , mem_job, mem_mileage        " );
			sb.append("FROM member2                       " );
			sb.append(" ORDER BY mem_id ASC           " );
			System.out.println(sb.toString().replaceAll("\s{2,}",""));
			pstmt = conn.prepareStatement(sb.toString()); 
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO member = new MemberVO();
				// Table -> VO (POJO) 객체
				member.setMemId(rs.getString("mem_id"));
				//member.setMemPass(rs.getString("mem_pass"));
				member.setMemName(rs.getString("mem_name"));
				member.setMemBir(rs.getString("mem_bir"));
				member.setMemHp(rs.getString("mem_hp"));
				member.setMemMail(rs.getString("mem_mail"));
				member.setMemJob(rs.getString("mem_job"));
				member.setMemMileage(rs.getInt("mem_mileage"));
				list.add(member);
			}
			return list;

		}catch (SQLException e) {
			System.out.println("작업 중 오류 발생 : " + e.getMessage());
			e.printStackTrace();;
		}finally {
			if(rs != null)try {rs.close();}catch (SQLException e) {}
			if(pstmt != null)try {pstmt.close();}catch (SQLException e) {}
			if(conn != null)try {conn.close();}catch (SQLException e) {}
		}
		return Collections.EMPTY_LIST; //  null 보다는 Collections.EMPTY_LIST 
	} 
	
}
