package com.member;

import java.util.ArrayList;

public interface MemberDAO {
//CRUD(create read update delete)라고 함
	//추가 (MemberVO 형의 vo 객체를 추가)
	public void memberInsert(MemberVO vo);
	//전체보기 (전체리스트를 보려면? select에 들어가는 값 MemberVO 형의 ArrayList를 본다.)
	public ArrayList<MemberVO>memberList();
	//수정 
	public int memberUpdate(MemberVO vo);
	//상세보기 (하나를 보려면? return값이 있다는 뜻. MemberVO형)
	public MemberVO memberView(String userid);
	//삭제
	public void memberDel(String userid);
	//아이디 중복 체크 (리턴값은 userid) 
	public String idCheck(String userid);
	//로그인 체크 (리턴값은 flag)
	public int loginCheck(String userid, String pwd);
	//전체회원 수
	public int memberCount();
}
