package com.address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class AddressDAO {
	//싱글톤 패턴. 최초 한번만 메모리 할당 (static)
	//AddressDAO클래스에 인스턴스를 만들어 사용 
	private static AddressDAO instance = new AddressDAO(); 
	public static AddressDAO getInstance() {
		return instance;
	}
	//디비연결 (scott의 address 테이블)
	private Connection getConnection() throws Exception{
		Context initCtx=new InitialContext();
		Context envCtx=(Context)initCtx.lookup("java:comp/env");
		DataSource ds=(DataSource)envCtx.lookup("jdbc/jsp"); 
		//톰캣에서 jdbc/jsp라는 이름을 찾아서 커넥션 시킨다.
		return ds.getConnection();
	}
	
	//추가
	public void addrInsert(Address ad) {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=getConnection();
			String sql="INSERT INTO address "
					+ "VALUES (address_seq.nextval,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, ad.getName());
			ps.setString(2, ad.getZipcode());
			ps.setString(3, ad.getAddr());
			ps.setString(4, ad.getTel());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con,ps);
		}
	}
	
	//수정
	public void addrUpdate(Address ad) {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=getConnection();
			String sql="UPDATE address SET name=?, zipcode=?, "
					+ "addr=?, tel=? WHERE num=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, ad.getName());
			ps.setString(2, ad.getZipcode());
			ps.setString(3, ad.getAddr());
			ps.setString(4, ad.getTel());
			ps.setLong(5, ad.getNum());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}
	}
	
	//전체보기
	public ArrayList<Address> addrList(String field, String word){
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		String sql="";
		ArrayList<Address> arr=new ArrayList<Address>();
		try {
			con=getConnection();
			if(word.equals("")) { //검색이 아닐떄
				sql="SELECT * FROM address";
			}else { //검색일때
				sql="SELECT * FROM address WHERE "+field+" LIKE '%"+word+"%'";
				//sql="select * from address where tel like '%010%'";
			}
			st=con.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				Address ad=new Address();
				ad.setNum(rs.getInt("num"));
				ad.setName(rs.getString("name"));
				ad.setAddr(rs.getString("addr"));
				ad.setZipcode(rs.getString("zipcode"));
				ad.setTel(rs.getString("tel"));
				arr.add(ad);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con,st,rs);
		}
		return arr;
	}
	
	//개수출력
	public int getCount(String field, String word) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		String sql="";
		int count=0;
		try {
			con=getConnection();
			if(word.equals("")) { //검색아니고 전체
				sql="SELECT COUNT(*) FROM address";
			}else { //검색일때
				sql="SELECT COUNT(*) FROM address WHERE "+field+" LIKE '%"+word+"%'";
			}
			st=con.createStatement();
			rs=st.executeQuery(sql);
			if(rs.next()) {
				count=rs.getInt(1); //address테이블의 첫번째 컬럼의 개수
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return count;
	}
	
	//상세보기
	public Address addrDetail(int num) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		Address ad=null;
		try {
			con=getConnection();
			String sql="SELECT * FROM address where num="+num;
			st=con.createStatement();
			rs=st.executeQuery(sql);
			if(rs.next()) {
				ad=new Address();
				ad.setAddr(rs.getString("addr"));
				ad.setName(rs.getString("name"));
				ad.setNum(rs.getLong("num"));
				ad.setTel(rs.getString("tel"));
				ad.setZipcode(rs.getString("zipcode"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con,st,rs);
		}
		return ad;
	}
	
	//삭제
	public void addrDelete(int num) {
		Connection con=null;
		Statement st=null;
		try {
			con=getConnection();
			String sql="DELETE FROM address WHERE num="+num;
			st=con.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, null);
		}
	}
	
	
	//우편번호 검색
	public ArrayList<ZipCodeBean> zipcodeRead(String dong) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		ArrayList<ZipCodeBean> zipArr=new ArrayList<>(); 
		//결과값이 여러개라서 ArrayList사용
		
		try {
			con=getConnection();
			//select * from zipcode where dong like '%서면%';
			String sql="SELECT * FROM zipcode WHERE dong like '%"+dong+"%'";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				ZipCodeBean zip=new ZipCodeBean();
				zip.setBunji(rs.getString("bunji"));
				zip.setDong(rs.getString("dong"));
				zip.setGugun(rs.getString("gugun"));
				zip.setSeq(rs.getInt("seq"));
				zip.setSido(rs.getString("sido"));
				zip.setZipcode(rs.getString("zipcode"));
				zipArr.add(zip);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return zipArr;
	}
	
	
	//closeConnection
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
