package com.dokyuportfolio.board.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dokyuportfolio.admin.vo.BoardVO;
import com.dokyuportfolio.admin.vo.CategoryVO;
import com.dokyuportfolio.admin.vo.FileVO;
import com.dokyuportfolio.board.mapper.BoardMapper;
import com.dokyuportfolio.board.service.BoardService;
import com.dokyuportfolio.board.vo.CommentVO;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMapper mapper;
	
	@Override
	public FileVO loadFileInfo(FileVO vo) throws Exception{
		return mapper.selectFileInfo(vo);
	}
	
	@Override
	public List<FileVO> loadFileList(BoardVO vo) throws Exception{
		return mapper.selectFileList(vo);
	}
	
	@Override
	public Map<String, Object> loadBoardDetail(BoardVO vo) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		BoardVO boardInfo = mapper.selectBoardDetail(vo);
		List<CommentVO> loadCommentList = mapper.loadCommentList(vo);
		
		result.put("category", mapper.selectCategory(boardInfo));
		result.put("boardInfo", boardInfo);
		result.put("commentList",loadCommentList);
		
		return result;
	}
	
	@Override
	public Map<String, Object> loadBoard(BoardVO vo) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		vo.setTotCnt(mapper.selectBoardCount(vo));
		
		result.put("category", mapper.selectCategory(vo));
		result.put("list", mapper.selectBoardList(vo));
		result.put("paging", vo);
		return result;
	}
	
	@Override
	public Map<String, Object> loadMainTopView() throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		BoardVO mainTopView = mapper.selectMainTopView();
		
		FileVO mainTopViewThumbnail = mapper.selectMainTopViewThumbnail(mainTopView);
		
		result.put("mainTopViewThumbnail",mainTopViewThumbnail);
		result.put("mainTopView", mainTopView);
		return result;
	}

	@Override
	public Map<String, Object> loadView(CategoryVO vo) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> articleList = new ArrayList<Map<String,Object>>();
		
		List<BoardVO> mainViewList = mapper.selectMainView(vo);
		
		for(BoardVO boardInfo : mainViewList) {
			
			Map<String, Object> article = new HashMap<String, Object>();
			FileVO mainBodyViewThumbnail = mapper.selectMainTopViewThumbnail(boardInfo);
			
			article.put("viewThumbnail", mainBodyViewThumbnail);
			article.put("view", boardInfo);
			
			articleList.add(article);
		}
		
		result.put("subject", mapper.selectMainViewSubject(vo));
		result.put("list", articleList);
		
		return result;
	}
	
	@Override
	public List<CommentVO> loadCommentList(BoardVO vo) throws Exception{
		
		 List<CommentVO> loadCommentList = mapper.loadCommentList(vo);
		 
		return loadCommentList;
	}
	
	@Override
	public int Commentwrite(CommentVO vo) throws Exception{
		int Commentwrite = mapper.Commentwrite(vo);
		return Commentwrite;
	}
	
	@Override
	public int Commentdelete(CommentVO vo)throws Exception{
		int Commentdelete = mapper.Commentdelete(vo);
		return Commentdelete;
	}
	
	@Override
	public Map<String, Object> loadSearchlist(BoardVO vo) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		vo.setTotCnt(mapper.selectBoardSearchCount(vo));
		
		result.put("list", mapper.selectSearchBoardList(vo));
		result.put("paging", vo);
		
		return result;
	}
	
}
