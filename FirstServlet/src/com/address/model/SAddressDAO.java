package com.address.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class SAddressDAO {
	private static SAddressDAO instance = new SAddressDAO();

	public static SAddressDAO getInstance() {
		return instance;
	}

	//디비연결
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/member");
		return ds.getConnection();
	}

	//추가
	public void insertAddress(SAddressDTO ad) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = getConnection();
			String sql = "insert into address " 
			+ "values(address_seq.nextval,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, ad.getName());
			ps.setString(2, ad.getZipcode());
			ps.setString(3, ad.getAddr());
			ps.setString(4, ad.getTel());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}

	}

	//상세보기
	public SAddressDTO addressDetail(int num) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		SAddressDTO dto=null;
		try {
			con=getConnection();
			st=con.createStatement();
			String sql="select * from address where num="+num;
			rs=st.executeQuery(sql);
			if(rs.next()) {
				dto=new SAddressDTO();
				dto.setNum(rs.getInt("num"));
				dto.setAddr(rs.getString("addr"));
				dto.setName(rs.getString("name"));
				dto.setNum(rs.getLong("num"));
				dto.setTel(rs.getString("tel"));
				dto.setZipcode(rs.getString("zipcode"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return dto;
	}
	
	//전체보기
	public ArrayList<SAddressDTO> addressList(){
			Connection con = null;
			Statement st = null;
			ResultSet rs = null;
			ArrayList<SAddressDTO> arr = new ArrayList<>();
			try {
				con = getConnection();
				String	sql ="select * from address";
				st = con.createStatement();
				rs = st.executeQuery(sql);
				while(rs.next()) {
					SAddressDTO ad= new SAddressDTO();
					ad.setAddr(rs.getString("addr"));
					ad.setName(rs.getString("name"));
					ad.setNum(rs.getLong("num"));
					ad.setTel(rs.getString("tel"));
					ad.setZipcode(rs.getString("zipcode"));
					arr.add(ad);
				}
			} catch (Exception e) {
					e.printStackTrace();
			}finally {
				closeConnection(con, st, rs);
			}
			return arr;
		}
	
	//삭제하기
	public void addressDelete(int num) {
		Connection con=null;
		Statement st=null;
		
		try {
			con=getConnection();
			st=con.createStatement();
			String sql="delete from address where num="+num;
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, null);
		}
	}
	
	//수정하기
	public SAddressDTO addressUpdate(SAddressDTO dto) {
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			con=getConnection();
			String sql="update address set name=?, zipcode=?, tel=?, addr=? where num=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getZipcode());
			ps.setString(3, dto.getTel());
			ps.setString(4, dto.getAddr());
			ps.setLong(5, dto.getNum());
			ps.executeUpdate();
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			closeConnection(con, ps);
		}
		return dto;
	}
	
	//개수출력
	public int getCount() {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int count=0;
		
		try {
			con=getConnection();
			String sql="select count(*) from address";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			if(rs.next()) {
				count=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	//검색 개수출력
	public int searchCount(String field, String word) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int count=0;
		
		try {
			con=getConnection();
			String sql="select count(*) from address where "+field+" like '%"+word+"%'";
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
	
	//우편번호 검색
	public ArrayList<ZipcodeDTO> zipSearch(String dong){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<ZipcodeDTO> arr = new ArrayList<>();
		try {
			con = getConnection();
			String	sql ="select * from zipcode where dong like '%"+dong+"%'";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				ZipcodeDTO zip= new ZipcodeDTO();
				zip.setBunji(rs.getString("bunji"));
				zip.setDong(rs.getString("dong"));
				zip.setGugun(rs.getString("gugun"));
				zip.setSido(rs.getString("sido"));
				zip.setZipcode(rs.getString("zipcode"));
				arr.add(zip);
			}
		} catch (Exception e) {
				e.printStackTrace();
		}finally {
			closeConnection(con, st, rs);
		}
		return arr;
	}
	
	//검색
	public ArrayList<SAddressDTO > addressSearch(String field, String word) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		ArrayList<SAddressDTO> arr=new ArrayList<SAddressDTO>();
		try {
			con=getConnection();
			String sql="select * from address where "+field+" like '%"+word+"%'";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				SAddressDTO sd=new SAddressDTO();
				sd.setNum(rs.getInt("num"));
				sd.setName(rs.getString("name"));
				sd.setAddr(rs.getString("addr"));
				sd.setTel(rs.getString("tel"));
				sd.setZipcode(rs.getString("zipcode"));
				arr.add(sd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return arr;
	}
	
	//닫기
	private void closeConnection(Connection con, PreparedStatement ps) {
		try {
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void closeConnection(Connection con, Statement st, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
