package com.dokyuportfolio.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dokyuportfolio.admin.vo.BoardVO;
import com.dokyuportfolio.admin.vo.CategoryVO;
import com.dokyuportfolio.admin.vo.FileVO;
import com.dokyuportfolio.board.service.BoardService;
import com.dokyuportfolio.board.vo.CommentVO;
import com.dokyuportfolio.common.FileDownload;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	/**
	 * 파일 다운로드
	 * 
	 * @param request
	 * @param response
	 * @param vo
	 * @throws Exception
	 */
	@RequestMapping(value = "/board/fileDownload.do")
	public void fileDownload(HttpServletRequest request , HttpServletResponse response , FileVO vo) throws Exception{
		FileVO fileInfo = service.loadFileInfo(vo);
		FileDownload.download(request, response, fileInfo);
	}
	
	/**
	 * 게시판 파일목록 조회
	 * 
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/board/loadFileList.do")
	public List<FileVO> loadFileList(HttpServletRequest request , BoardVO vo) throws Exception{
		return service.loadFileList(vo);
	}
	
	/**
	 * 게시판 상세 조회
	 * 
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/board/loadBoardDetail.do")
	public Map<String, Object> loadBoardDetail(HttpServletRequest request , BoardVO vo) throws Exception{
		return service.loadBoardDetail(vo);
	}
	
	/**
	 * 게시판 상세 화면
	 * 
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/board/boardDetail.do")
	public String boardDetail(HttpServletRequest request , Model model , @RequestParam("bs") String bs) throws Exception{
		
		model.addAttribute("boardSeq" , bs);
		
		return "dokyuPortfolio/board/boardDetail";
	}
	
	
	/**
	 * 게시판 목록 조회
	 * 
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/board/loadBoard.do")
	public Map<String, Object> loadBoard(HttpServletRequest request , BoardVO vo) throws Exception{
		return service.loadBoard(vo);
	}
	
	/**
	 * 게시판
	 * 
	 * @param request
	 * @param ci
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "board/board.do")
	public String board(HttpServletRequest request , Model model , @RequestParam("ci") String ci) throws Exception{
		model.addAttribute("categoryId",ci);
		return "dokyuPortfolio/board/board";
	}
	
	/**
	 * 메인 최상위글 조회
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "board/loadMainTopView.do")
	public Map<String, Object> loadMainTopView(HttpServletRequest request) throws Exception{
		return service.loadMainTopView();
	}
	
	/**
	 * 메인글 조회
	 * 
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "board/loadView.do")
	public Map<String, Object> loadView(HttpServletRequest request, CategoryVO vo) throws Exception{
		return service.loadView(vo);
	}
	
	
	/**
	 * comment 조회
	 * 
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/board/loadBoardDetailComment.do")
	public List<CommentVO> loadCommentList(HttpServletRequest request , BoardVO vo) throws Exception{
		return service.loadCommentList(vo);
	}
	
	/**
	 * comment 입력
	 * 
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/board/loadBoardDetailCommentwrite.do")
	public int Commentwrite(HttpServletRequest request , CommentVO vo) throws Exception{
		return service.Commentwrite(vo);
	}
	
	/**
	 * comment 삭제
	 * 
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/board/loadBoardDetailCommentdelete.do")
	public int Commentdelete(HttpServletRequest request , CommentVO vo) throws Exception{
		return service.Commentdelete(vo);
	}
	
	/**
	 * 검색결과 조회
	 * 
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/board/boardSearch.do")
	public String boardSearch(HttpServletRequest request , Model model ,@RequestParam("board_search_text") String board_search_text) throws Exception{
		
		model.addAttribute("board_search_text",board_search_text);
		
		return "dokyuPortfolio/board/boardSearch";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "loadBoardSearchlist.do")
	public Map<String, Object> loadSearchlist(HttpServletRequest request, BoardVO vo) throws Exception{
		return service.loadSearchlist(vo);
	}
	
}
