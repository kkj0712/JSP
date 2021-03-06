package com.guest.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class GuestDAO {
	//디비셋팅
	private static GuestDAO instance=new GuestDAO();
	public static GuestDAO getInstance() {
		return instance;
	}
	private Connection getConnection() throws Exception{
		Context initCtx=new InitialContext();
		Context envCtx=(Context) initCtx.lookup("java:comp/env");
		DataSource ds=(DataSource) envCtx.lookup("jdbc/guest"); //context.xml의 name을 jdbc/guest로 바꾸기
		return ds.getConnection();
	}
	
	//추가
	public void guestInsert(GuestDTO guest) {
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			con=getConnection();
			String sql="insert into guestbook(num, name, content, grade, created, ipaddr)"
					+ "values (guestbook_seq.nextval,?,?,?,sysdate,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, guest.getName());
			ps.setString(2, guest.getContent());
			ps.setString(3, guest.getGrade());
			ps.setString(4, guest.getIpaddr());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con,ps);
		}
	}
	
	//전체보기 페이징
	public ArrayList<GuestDTO> guestList(int startRow, int endRow){
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<GuestDTO> arr=new ArrayList<GuestDTO>();
		
		try {
			con=getConnection();
			StringBuilder sb=new StringBuilder();
			sb.append("select * from");
			sb.append(" (select aa.*, rownum rn from");
			sb.append(" (select * from guestbook order by num desc) aa");
			sb.append(" where rownum<=?) where rn>=?");
			ps=con.prepareStatement(sb.toString());
			ps.setInt(1, endRow);
			ps.setInt(2, startRow);
			rs=ps.executeQuery();
			while(rs.next()) {
				GuestDTO dto=new GuestDTO();
				dto.setNum(rs.getInt("num"));
				dto.setContent(rs.getString("content"));
				dto.setCreated(rs.getString("created"));
				dto.setGrade(rs.getString("grade"));
				dto.setIpaddr(rs.getString("ipaddr"));
				dto.setName(rs.getString("name"));
				arr.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps, rs);
		}
		return arr;
	}
	
	//검색 전체보기 페이징
	public ArrayList<GuestDTO> guestList(String field, String word, int startRow, int endRow){
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<GuestDTO> arr=new ArrayList<GuestDTO>();
		
		try {
			con=getConnection();
			StringBuilder sb=new StringBuilder();
			sb.append("select * from");
			sb.append(" (select aa.*, rownum rn from");
			sb.append(" (select * from guestbook where "+field+" like '%"+word+"%'");
			sb.append(" order by num desc) aa");
			sb.append(" where rownum<=?) where rn>=?");
			ps=con.prepareStatement(sb.toString());
			ps.setInt(1, endRow);
			ps.setInt(2, startRow);
			rs=ps.executeQuery();
			while(rs.next()) {
				GuestDTO dto=new GuestDTO();
				dto.setNum(rs.getInt("num"));
				dto.setContent(rs.getString("content"));
				dto.setCreated(rs.getString("created"));
				dto.setGrade(rs.getString("grade"));
				dto.setIpaddr(rs.getString("ipaddr"));
				dto.setName(rs.getString("name"));
				arr.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps, rs);
		}
		return arr;
	}
	
	
	//전체보기
	public ArrayList<GuestDTO> guestList(){
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		ArrayList<GuestDTO> arr=new ArrayList<GuestDTO>();
		
		try {
			con=getConnection();
			st=con.createStatement();
			String sql="select * from guestbook order by num desc";
			rs=st.executeQuery(sql);
			while(rs.next()) {
				GuestDTO dto=new GuestDTO();
				dto.setNum(rs.getInt("num"));
				dto.setContent(rs.getString("content"));
				dto.setCreated(rs.getString("created"));
				dto.setGrade(rs.getString("grade"));
				dto.setIpaddr(rs.getString("ipaddr"));
				dto.setName(rs.getString("name"));
				arr.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return arr;
	}
	
	//상세보기
	public GuestDTO guestView(int num) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		GuestDTO guest=null;
		try {
			con=getConnection();
			st=con.createStatement();
			String sql="select * from guestbook where num="+num;
			rs=st.executeQuery(sql);
			if(rs.next()) {
				guest=new GuestDTO();
				guest.setNum(rs.getInt("num"));
				guest.setContent(rs.getString("content"));
				guest.setCreated(rs.getString("created"));
				guest.setGrade(rs.getString("grade"));
				guest.setIpaddr(rs.getString("ipaddr"));
				guest.setName(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return guest;
	}
	
	//평가 리스트 개수 출력
	public int guestCount() {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int count=0;
		
		try {
			con=getConnection();
			String sql="select count(*) from guestbook";
			st=con.createStatement();
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
	
	//검색 평가 리스트 개수 출력
		public int guestCount(String field, String word) {
			Connection con=null;
			Statement st=null;
			ResultSet rs=null;
			int count=0;
			String sql="";
			try {
				con=getConnection();
				st=con.createStatement();
				if(word.equals("")) {
					sql="select count(*) from guestbook";
				}else {
					sql="select count(*) from guestbook where "+field+" like '%"+word+"%'";
				}
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
	
	//삭제
	public void guestDelete(int num) {
		Connection con=null;
		Statement st=null;
		try {
			con=getConnection();
			String sql="delete from guestbook where num="+num;
			st=con.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, null);
		}
	}
		
	//로그인체크 (비밀번호 오류: 2, 회원아님: -1, 관리자: 1, 일반회원: 0)
	public int guestLoginCheck(String userid, String pwd) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int flag=-1;
		
		try {
			con=getConnection();
			String sql="select pwd, admin from jmember where userid='"+userid+"'";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			if(rs.next()) { //id 맞음
				if(rs.getString("pwd").equals(pwd)) { //비번 일치
					flag=rs.getInt("admin");
				}else { //비번 오류
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
