package com.board.model;

import java.util.ArrayList;

public interface BoardDAO {
	//추가
	public void boardSave(BoardDTO board);
	//전체보기
	public ArrayList<BoardDTO> boardList();
	//상세보기
	public BoardDTO boardfindById(int num);
	//수정
	public BoardDTO boardUpdate(BoardDTO board);
	//삭제
	public void boardDelete(int num);
}
