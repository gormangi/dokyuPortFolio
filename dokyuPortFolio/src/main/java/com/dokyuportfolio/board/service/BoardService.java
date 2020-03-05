package com.dokyuportfolio.board.service;

import java.util.List;
import java.util.Map;

import com.dokyuportfolio.admin.vo.BoardVO;
import com.dokyuportfolio.admin.vo.CategoryVO;
import com.dokyuportfolio.admin.vo.FileVO;
import com.dokyuportfolio.board.vo.CommentVO;

public interface BoardService {
	
	FileVO loadFileInfo(FileVO vo) throws Exception;
	
	List<FileVO> loadFileList(BoardVO vo) throws Exception;
	
	Map<String, Object> loadBoardDetail(BoardVO vo) throws Exception;
	
	Map<String, Object> loadBoard(BoardVO vo) throws Exception;

	Map<String, Object> loadMainTopView() throws Exception;
	
	Map<String, Object> loadView(CategoryVO vo) throws Exception;
	
	List<CommentVO> loadCommentList (BoardVO vo)throws Exception;
	
	int Commentwrite (CommentVO vo)throws Exception;
	
	int Commentdelete (CommentVO vo)throws Exception;
	
	Map<String, Object> loadSearchlist(BoardVO vo) throws Exception;
}
