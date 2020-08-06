package com.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAOImpl implements BoardDAO{
	//디비셋팅
	private static BoardDAOImpl instance=new BoardDAOImpl();
	public static BoardDAOImpl getInstance() {
		return instance;
	}
	private Connection getConnection() throws Exception{
		Context initCtx=new InitialContext();
		Context envCtx=(Context) initCtx.lookup("java:comp/env");
		DataSource ds=(DataSource) envCtx.lookup("jdbc/board"); //context.xml의 name을 jdbc/guest로 바꾸기
		return ds.getConnection();
	}

	//추가
	public void boardSave(BoardDTO board) {
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			con=getConnection();
			String sql="insert into tbl_board values (tbl_board_seq.nextval,?,?,?,sysdate,0)";
			ps=con.prepareStatement(sql);
			ps.setString(1, board.getWriter());
			ps.setString(2, board.getContent());
			ps.setString(3, board.getSubject());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}
	}

	//검색 아닌 전체보기
	public ArrayList<BoardDTO> boardList(int startRow, int endRow) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<BoardDTO> arr=new ArrayList<BoardDTO>();
		
		try {
			con=getConnection();
			StringBuilder sb=new StringBuilder();
			sb.append("select * from");
			sb.append(" (select aa.*, rownum rn from");
			sb.append(" (select * from tbl_board order by num desc) aa");
			sb.append(" where rownum<=?) where rn>=?");
			ps=con.prepareStatement(sb.toString());
			ps.setInt(1, endRow);
			ps.setInt(2, startRow);
			rs=ps.executeQuery();
			while(rs.next()) {
				BoardDTO board=new BoardDTO();
				board.setNum(rs.getInt("num"));
				board.setWriter(rs.getString("writer"));
				board.setSubject(rs.getString("subject"));
				board.setContent(rs.getString("content"));
				board.setReg_date(rs.getString("reg_date"));
				board.setReadcount(rs.getInt("readcount"));
				arr.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps, rs);
		}
		return arr;
	}

	//검색 전체보기
	public ArrayList<BoardDTO> boardList(String field, String word, int startRow, int endRow) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<BoardDTO> arr=new ArrayList<BoardDTO>();
		
		try {
			con=getConnection();
			StringBuilder sb=new StringBuilder();
			sb.append("select * from");
			sb.append(" (select aa.*, rownum rn from");
			sb.append(" (select * from tbl_board where "+field+" like '%"+word+"%'");
			sb.append(" order by num desc) aa");
			sb.append(" where rownum<=?) where rn>=?");
			ps=con.prepareStatement(sb.toString());
			ps.setInt(1, endRow);
			ps.setInt(2, startRow);
			rs=ps.executeQuery();
			while(rs.next()) {
				BoardDTO board=new BoardDTO();
				board.setNum(rs.getInt("num"));
				board.setWriter(rs.getString("writer"));
				board.setSubject(rs.getString("subject"));
				board.setContent(rs.getString("content"));
				board.setReg_date(rs.getString("reg_date"));
				board.setReadcount(rs.getInt("readcount"));
				arr.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps, rs);
		}
		return arr;
	}
	
	//게시물 개수 출력
	public int boardCount(String field, String word) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int count=0;
		String sql="";
		try {
			con=getConnection();
			st=con.createStatement();
			if(word.equals("")) {
				sql="select count(*) from tbl_board";
			}else {
				sql="select count(*) from tbl_board where "+field+" like '%"+word+"%'";
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
	
	//상세보기
	public BoardDTO boardfindById(int num) {
		return null;
	}

	//수정
	public void boardUpdate(BoardDTO board) {
		
	}

	//삭제
	public void boardDelete(int num) {
		
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
	
	public ArrayList<BoardDTO> boardList() {
		return null;
	}
}
