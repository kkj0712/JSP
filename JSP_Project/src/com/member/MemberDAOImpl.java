package com.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAOImpl implements MemberDAO{
	private static MemberDAOImpl instance=new MemberDAOImpl();
	public static MemberDAOImpl getInstance() {
		return instance;
	}
	private Connection getConnection() throws Exception{
		Context initCtx=new InitialContext();
		Context envCtx=(Context) initCtx.lookup("java:comp/env");
		DataSource ds=(DataSource) envCtx.lookup("jdbc/member"); //context.xml의 name을 jdbc/member로 바꾸기
		return ds.getConnection();
	}
	
	//추가
	public void memberInsert(MemberVO vo) {
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			con=getConnection();
			String sql="INSERT INTO JMEMBER VALUES (?,?,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, vo.getUserid());
			ps.setString(2, vo.getName());
			ps.setString(3, vo.getPwd());
			ps.setString(4, vo.getEmail());
			ps.setString(5, vo.getPhone());
			ps.setInt(6, vo.getAdmin());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con,ps);
		}
	}

	//전체보기 List
	public ArrayList<MemberVO> memberList() {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null; //결과가 있으면 ResultSet씀
		ArrayList<MemberVO> arr=new ArrayList<>();
		try {
			con=getConnection();
			String sql="SELECT * FROM JMEMBER";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				MemberVO member=new MemberVO();
				member.setName(rs.getString("name"));
				member.setUserid(rs.getString("userid"));
				member.setPwd(rs.getString("pwd"));
				member.setEmail(rs.getString("email"));
				member.setPhone(rs.getString("phone"));
				member.setAdmin(rs.getInt("admin"));
				arr.add(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return arr;
	}

	//일반회원 회원정보 수정
	public int memberUpdate(MemberVO vo) {
		Connection con=null;
		PreparedStatement ps=null;
		int flag=0;
		try {
			con=getConnection();
			String sql="update JMEMBER set name=?, pwd=?, email=?, phone=?, admin=? where userid=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getPwd());
			ps.setString(3, vo.getEmail());
			ps.setString(4, vo.getPhone());
			ps.setInt(5, vo.getAdmin());
			ps.setString(6, vo.getUserid());
			flag=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}
		return flag;
	}

	//일반회원 회원정보 상세보기
	public MemberVO memberView(String userid) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		MemberVO m=null;
		try {
			con=getConnection();
			String sql="select * from JMEMBER where userid='"+userid+"'";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			if(rs.next()) {
				m=new MemberVO();
				m.setUserid(rs.getString("userid"));
				m.setPwd(rs.getString("pwd"));
				m.setPhone(rs.getString("phone"));
				m.setName(rs.getString("name"));
				m.setEmail(rs.getString("email"));
				m.setAdmin(rs.getInt("admin"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return m;
	}

	//삭제
	public void memberDel(String userid) {
		Connection con=null;
		Statement st=null;
		try {
			con=getConnection();
			String sql="delete from JMEMBER where userid='"+userid+"'";
			st=con.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, null);
		}
	}

	//아이디 중복체크
	public String idCheck(String userid) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		String flag="yes"; //사용가능
		try {
			con=getConnection();
			String sql="select * from JMEMBER where userid='"+userid+"'";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			if(rs.next()) {
				flag="no";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return flag;
	}
	
	//로그인체크 (비밀번호 오류: 2, 회원아님: -1, 관리자: 1, 일반회원: 0)
	public int loginCheck(String userid, String pwd) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int flag=-1;
		try {
			con=getConnection();
			String sql="select pwd, admin from JMEMBER where userid='"+userid+"'";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			if(rs.next()) { //id는 맞음
				if(rs.getString("pwd").equals(pwd)) { //비번 맞다
					flag=rs.getInt("admin");
				}else { //비번 틀림
					flag=2;
				}				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return flag;
	}
	
	//전체회원 수
	public int memberCount() {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int count=0;
		try {
			con=getConnection();
			st=con.createStatement();
			String sql="select count(*) from JMEMBER";
			rs=st.executeQuery(sql);
			if(rs.next()) {
				count=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return count;
	}
	
	//닫기 closeConnection
	private void closeConnection(Connection con, PreparedStatement ps) {
		try {
			if(ps!=null) ps.close();
			if(con!=null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	private void closeConnection(Connection con, Statement st, ResultSet rs) {
		try {
			if(st!=null) st.close();
			if(con!=null) con.close();
			if(rs!=null) rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
}
