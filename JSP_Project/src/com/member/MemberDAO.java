package com.member;

import java.util.ArrayList;

public interface MemberDAO {
//CRUD(create read update delete)��� ��
	//�߰� (MemberVO ���� vo ��ü�� �߰�)
	public void memberInsert(MemberVO vo);
	//��ü���� (��ü����Ʈ�� ������? select�� ���� �� MemberVO ���� ArrayList�� ����.)
	public ArrayList<MemberVO>memberList();
	//���� 
	public int memberUpdate(MemberVO vo);
	//�󼼺��� (�ϳ��� ������? return���� �ִٴ� ��. MemberVO��)
	public MemberVO memberView(String userid);
	//����
	public void memberDel(String userid);
	//���̵� �ߺ� üũ (���ϰ��� userid) 
	public String idCheck(String userid);
	//�α��� üũ (���ϰ��� flag)
	public int loginCheck(String userid, String pwd);
	//��üȸ�� ��
	public int memberCount();
}
