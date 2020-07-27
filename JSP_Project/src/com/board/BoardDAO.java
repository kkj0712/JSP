package com.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	
	//디비셋팅
	private static BoardDAO instance=new BoardDAO();
	
	public static BoardDAO getInstance() {
		return instance;
	}
	
	private Connection getConnection() throws Exception{
		Context initCtx=new InitialContext();
		Context envCtx=(Context) initCtx.lookup("java:comp/env");
		DataSource ds=(DataSource) envCtx.lookup("jdbc/member"); 
		return ds.getConnection();
	}
	
	//추가
	public void boardInsert(BoardVO board) { //새글 답글 구분
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		//부모글
		int num=board.getNum();
		int ref=board.getRef(); //게시글 순서
		int re_step=board.getRe_step(); //답글 순서
		int re_level=board.getRe_level(); //답글의 답글 순서
		
		int number=0;
		String sql=null;
		try {
			con=getConnection();
			ps=con.prepareStatement("select max(num) from board");
			rs=ps.executeQuery();
			if(rs.next()) { //기존 데이터가 있을때 ref를 최대값 +1 로 결정
				number=rs.getInt(1)+1;
			}else { //기존 데이터가 없을때 ref를 1로 결정
				number=1;
			}
			if(num!=0) { //답글
				sql="update board set re_step=re_step+1 where ref=? and re_step>?";
				ps=con.prepareStatement(sql);
				ps.setInt(1, ref);
				ps.setInt(2, re_step);
				ps.executeUpdate();
				re_step=re_step+1;
				re_level=re_level+1;
			}else { //새글
				ref=number;
				re_step=0;
				re_level=0;
			}
			//num, writer, subject, email, content, passwd, ip, readcount, ref, re_step, re_level
			sql="INSERT INTO BOARD(num,writer,subject,email,content,passwd,ip,readcount,ref,re_step,re_level)"
					+ "VALUES (board_seq.nextval,?,?,?,?,?,?,0,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, board.getWriter());
			ps.setString(2, board.getSubject());
			ps.setString(3, board.getEmail());
			ps.setString(4, board.getContent());
			ps.setString(5, board.getPasswd());
			ps.setString(6, board.getIp());
			ps.setInt(7, ref);
			ps.setInt(8, re_step);
			ps.setInt(9, re_level);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con,ps,rs);
		}
	}
	
	//전체보기
	public ArrayList<BoardVO> boardList(int startRow, int endRow){
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<BoardVO> arr=new ArrayList<BoardVO>();
		try {
			con=getConnection(); //게시글은 최신순. 답글은 오래된 순.
			String sql="select * from ("
						+ "select rownum rn, aa.* from ("
						+ "select * from board order by ref desc, re_step asc) aa " 
						+ ") where rn <=? and rn >=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, endRow);
			ps.setInt(2, startRow);
			rs=ps.executeQuery();
			while(rs.next()) {
				BoardVO board=new BoardVO();
				board.setNum(rs.getInt("num"));
				board.setEmail(rs.getString("email"));
				board.setIp(rs.getString("ip"));
				board.setReadcount(rs.getInt("readcount"));
				board.setSubject(rs.getString("subject"));
				board.setWriter(rs.getString("writer"));
				board.setReg_date(rs.getString("reg_date"));
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
	public ArrayList<BoardVO> boardList(String field, String word, int startRow, int endRow){
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<BoardVO> arr=new ArrayList<BoardVO>();
		try {
			con=getConnection();
			String sql="select * from ("
					+ "select rownum rn, aa.* from ("
					+ "select * from board where "
					+field+" like '%"+word+"%' order by ref desc, re_step asc) aa "
					+ ") where rn <=? and rn >=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, endRow);
			ps.setInt(2, startRow);
			rs=ps.executeQuery();
			while(rs.next()) {
				BoardVO board=new BoardVO();
				board.setNum(rs.getInt("num"));
				board.setEmail(rs.getString("email"));
				board.setIp(rs.getString("ip"));
				board.setReadcount(rs.getInt("readcount"));
				board.setSubject(rs.getString("subject"));
				board.setWriter(rs.getString("writer"));
				board.setReg_date(rs.getString("reg_date"));
				arr.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps, rs);
		}
		return arr;
	}
	
	//상세보기
	public BoardVO boardView(int num) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		BoardVO board=null;
		
		try {
			con=getConnection();
			st=con.createStatement();
			st.executeUpdate("update board set readcount=readcount+1 where num="+num);
			String sql="select * from board where num="+num;
			st=con.createStatement();
			rs=st.executeQuery(sql);
			if(rs.next()) {
				board=new BoardVO();
				board.setNum(rs.getInt("num"));
				board.setContent(rs.getString("content"));
				board.setEmail(rs.getString("email"));
				board.setSubject(rs.getString("subject"));
				board.setIp(rs.getString("ip"));
				board.setPasswd(rs.getString("passwd"));
				board.setRe_level(rs.getInt("re_level"));
				board.setRe_step(rs.getInt("re_step"));
				board.setRef(rs.getInt("ref"));
				board.setReg_date(rs.getString("reg_date"));
				board.setReadcount(rs.getInt("readcount"));
				board.setWriter(rs.getString("writer"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, st, rs);
		}
		return board;
	}
	
	//수정
	public int boardUpdate(BoardVO board) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		int flag=0;
		String sql="";
		try {
			con=getConnection();
			sql="select passwd from board where num="+board.getNum();
			ps=con.prepareStatement(sql); //passwd 검색
			rs=ps.executeQuery();
			if(rs.next()) {
				if(rs.getString("passwd").equals(board.getPasswd())) { //비번 일치
					sql="UPDATE BOARD SET subject=?, email=?, content=?, reg_date=sysdate where num=?";
					ps=con.prepareStatement(sql);
					ps.setString(1, board.getSubject());
					ps.setString(2, board.getEmail());
					ps.setString(3, board.getContent());
					ps.setInt(4, board.getNum());
					flag=ps.executeUpdate(); //업데이트 된 수를 flag에 담는다.
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, ps);
		}
		return flag;
	}
	
	//삭제
	public int boardDelete(int num) {
		int flag=0;
		Connection con=null;
		Statement st=null;
		try {
			con=getConnection();
			String sql="DELETE FROM board WHERE num="+num;
			st=con.createStatement();
			flag=st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, null);
		}
		return flag;
	}
	
	//개수
	public int boardCount() {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int count=0;
		try {
			con=getConnection();
			st=con.createStatement();
			String sql="select count(*) from board";
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
	
	//검색 개수
		public int boardCount(String field, String word) {
			Connection con=null;
			Statement st=null;
			ResultSet rs=null;
			int count=0;
			try {
				con=getConnection();
				st=con.createStatement();
				String sql="select count(*) from board where "+field+" like '%"+word+"%'";
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
		
	//comment
		
	//commentInsert
	public void commentInsert(CommentVO cvo) {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=getConnection();
			String sql="INSERT INTO commentboard(cnum, userid, msg, bnum)"
					+ "VALUES(comment_seq.nextval,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, cvo.getUserid());
			ps.setString(2, cvo.getMsg());
			ps.setInt(3, cvo.getBnum());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}
	}
	
	//commentList
	public ArrayList<CommentVO> commentList(int num){
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		ArrayList<CommentVO> arr=new ArrayList<CommentVO>();
		
		try {
			con=getConnection();
			String sql="SELECT * FROM COMMENTBOARD where bnum="+num+" ORDER BY cnum DESC";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				CommentVO comment=new CommentVO();
				comment.setBnum(rs.getInt("bnum"));
				comment.setCnum(rs.getInt("cnum"));
				comment.setMsg(rs.getString("msg"));
				comment.setReg_date(rs.getString("reg_date"));
				comment.setUserid(rs.getString("userid"));
				arr.add(comment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return arr;
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
