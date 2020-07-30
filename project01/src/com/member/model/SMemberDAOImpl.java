package com.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class SMemberDAOImpl implements MemberDAO{
	
	private static SMemberDAOImpl instance=new SMemberDAOImpl();
	public static SMemberDAOImpl getInstance() {
		return instance;
	}
	private Connection getConnection() throws Exception{
		Context initCtx=new InitialContext();
		Context envCtx=(Context) initCtx.lookup("java:comp/env");
		DataSource ds=(DataSource) envCtx.lookup("jdbc/member"); //context.xml�� name�� jdbc/member�� �ٲٱ�
		return ds.getConnection();
	}
	
	//ȸ������
	public void memberInsert(MemberDTO vo) {
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			con=getConnection();
			String sql="INSERT INTO jmember VALUES(?,?,?,?,?,?)";
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
			closeConnection(con, ps);
		}
	}

	//ȸ������Ʈ ����
	public ArrayList<MemberDTO> memberList() {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		ArrayList<MemberDTO>arr=new ArrayList<MemberDTO>();
		
		try {
			con=getConnection();
			String sql="select * from jmember";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				MemberDTO dto=new MemberDTO();
				dto.setAdmin(rs.getInt("admin"));
				dto.setEmail(rs.getString("email"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setPwd(rs.getString("pwd"));
				dto.setUserid(rs.getString("userid"));
				arr.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return arr;
	}
	//ȸ������ ����
	public int memberUpdate(MemberDTO vo) {
		Connection con=null;
		PreparedStatement ps=null;
		int flag=0;
		try {
			con=getConnection();
			String sql="update jmember set name=?, pwd=?, email=?, phone=?, admin=? where userid=?";
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
	
	//ȸ�� �󼼺���
	public MemberDTO memberView(String userid) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		MemberDTO dto=null;
		try {
			con=getConnection();
			String sql="select * from jmember where userid='"+userid+"'";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			if(rs.next()) {
				dto=new MemberDTO();
				dto.setAdmin(rs.getInt("admin"));
				dto.setEmail(rs.getString("email"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setPwd(rs.getString("pwd"));
				dto.setUserid(rs.getString("userid"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return dto;
	}
	
	//����
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
	
	//���̵� �ߺ�Ȯ�� ����ϱ� ��ư
	public String idCheck(String userid) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		String flag="yes"; //��밡��
		try {
			con=getConnection();
			String sql="select * from jmember where userid='"+userid+"'";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			if(rs.next()) {
				flag="no"; //���Ұ���
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return flag;
	}

	//�α���üũ (��й�ȣ ����: 2, ȸ���ƴ�: -1, ������: 1, �Ϲ�ȸ��: 0)
	public int loginCheck(String userid, String pwd) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int flag=-1;
		try {
			con=getConnection();
			String sql="select pwd, admin from jmember where userid='"+userid+"'";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			if(rs.next()) { //id ����
				if(rs.getString("pwd").equals(pwd)) { //��� ��ġ
					flag=rs.getInt("admin");
				}else { //��� ����
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
	
	//ȸ����
	public int getCount() {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int count=0;
		
		try {
			con=getConnection();
			String sql="select count(*) from jmember";
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
	
	//�ݱ� closeConnection
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
