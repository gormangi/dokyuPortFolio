package com.dokyuportfolio.admin.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dokyuportfolio.admin.service.AdminService;
import com.dokyuportfolio.admin.vo.BoardVO;
import com.dokyuportfolio.admin.vo.CategoryVO;
import com.dokyuportfolio.admin.vo.FileVO;
import com.dokyuportfolio.admin.vo.MenuVO;
import com.dokyuportfolio.main.vo.UserVO;

@Controller
public class AdminController {
	
	@Autowired
	private AdminService service;
	
	/**
	 * 게시글 삭제
	 * 
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "admin/boardDel.do")
	public Map<String, Object> boardDel(HttpServletRequest request , BoardVO vo) throws Exception{
		return service.boardDel(vo);
	}
	
	/**
	 * ckeditor 이미지 업로드
	 * 
	 * @param request
	 * @param upload
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "admin/boardImageUpload.do")
	public Map<String, Object> boardImageUpload(HttpServletRequest request , @RequestParam MultipartFile upload) throws Exception{
		String imageUploadPath = request.getSession().getServletContext().getRealPath("/") + "static"+ File.separator +"uploadFile"+ File.separator + "images" + File.separator;
		String baseUrl = request.getRequestURL().toString().replace(request.getRequestURI().toString(),"");
		return service.boardImageUpload(upload , imageUploadPath , baseUrl);
	}
	
	/**
	 * 썸네일 삭제
	 * 
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "admin/fileDel.do")
	public Map<String, Object> fileDel(HttpServletRequest request , FileVO vo) throws Exception{
		return service.fileDel(vo);
	}
	
	/**
	 * 게시판 글 수정
	 * 
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "admin/boardModify.do")
	public Map<String, Object> boardModify(HttpServletRequest request , BoardVO vo) throws Exception{
		HttpSession session = request.getSession();
		UserVO userInfo = (UserVO)session.getAttribute("userInfo");
		vo.setUpdtId(userInfo.getUserId());
		vo.setThumbnailUploadPath(request.getSession().getServletContext().getRealPath("/") + "static" + File.separator + "uploadFile" + File.separator + "thumbnail" + File.separator);
		vo.setFileUploadPath(request.getSession().getServletContext().getRealPath("/") + "static" + File.separator + "uploadFile" + File.separator + "file" + File.separator);
		vo.setBaseUrl(request.getRequestURL().toString().replace(request.getRequestURI().toString(),""));
		return service.boardModify(vo);
	}
	
	/**
	 * 게시판 글 작성
	 * 
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "admin/boardWrite.do")
	public Map<String, Object> boardWrite(HttpServletRequest request , BoardVO vo) throws Exception{
		HttpSession session = request.getSession();
		UserVO userInfo = (UserVO)session.getAttribute("userInfo");
		vo.setInstId(userInfo.getUserId());
		vo.setThumbnailUploadPath(request.getSession().getServletContext().getRealPath("/") + "static" + File.separator + "uploadFile" + File.separator + "thumbnail" + File.separator);
		vo.setFileUploadPath(request.getSession().getServletContext().getRealPath("/") + "static" + File.separator + "uploadFile" + File.separator + "file" + File.separator);
		vo.setBaseUrl(request.getRequestURL().toString().replace(request.getRequestURI().toString(),""));
		return service.boardWrite(vo);
	}
	
	/**
	 * 관리자 - 카테고리관리
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "admin/categoryManagement.do")
	public String categoryManagement(HttpServletRequest request , Model model) throws Exception{
		model.addAttribute("adminMenuName","categoryManagement");
		return "admin/admin/categoryManagement";
	}
	
	/**
	 * 카테고리 목록 조회
	 * 
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "admin/categoryList.do")
	public Map<String, Object> categoryList(HttpServletRequest request , CategoryVO vo) throws Exception{
		return service.categoryList(vo);
	}
	
	/**
	 * 카테고리 추가
	 * 
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "admin/categoryAdd.do")
	public Map<String, Object> categoryAdd(HttpServletRequest request , CategoryVO vo) throws Exception{
		HttpSession session = request.getSession();
		UserVO userInfo = (UserVO)session.getAttribute("userInfo");
		vo.setInstId(userInfo.getUserId());
		return service.categoryAdd(vo);
	}
	
	/**
	 * 카테고리 수정
	 * 
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "admin/categoryModify.do")
	public Map<String, Object> categoryModify(HttpServletRequest request , CategoryVO vo) throws Exception{
		return service.categoryModify(vo);
	}
	
	/**
	 * 카테고리 조회
	 * 
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "admin/loadCategoryInfo.do")
	public CategoryVO loadCategoryInfo(HttpServletRequest request , CategoryVO vo) throws Exception{
		return service.loadCategoryInfo(vo);
	}
	
	/**
	 * 관리자 - 게시판관리
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "admin/boardManagement.do")
	public String boardManagement(HttpServletRequest request , Model model) throws Exception{
		model.addAttribute("adminMenuName","boardManagement");
		return "admin/admin/boardManagement";
	}
	
	/**
	 * 게시판 글작성 화면
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "admin/boardWriteView.do")
	public String boardWriteView(HttpServletRequest request , Model model , @RequestParam("wMod")String writeMod , @RequestParam(value="bs",required = false)String boardSeq) throws Exception{
		model.addAttribute("adminMenuName","boardManagement");
		model.addAttribute("writeMod",writeMod);
		model.addAttribute("boardSeq",boardSeq);
		return "admin/admin/boardWriteView";
	}
	
	/**
	 * 카테고리 조회 (selectBox)
	 * 
	 * @param requeset
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "admin/categoryListForSelectBox.do")
	public List<CategoryVO> categoryListForSelectBox(HttpServletRequest requeset) throws Exception{
		return service.categoryListForSelectBox();
	}
	
	/**
	 * 카테고리 삭제
	 * 
	 * @param request
	 * @param categorySeqList
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "admin/categoryDelete.do")
	public Map<String, Object> categoryDelete(HttpServletRequest request , @RequestParam("categorySeqList[]") String[] categorySeqList) throws Exception{
		return service.categoryDelete(categorySeqList);
	}
	
	/**
	 * 게시물 목록 조회
	 * 
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "admin/boardList.do")
	public Map<String, Object> boardList(HttpServletRequest request , BoardVO vo) throws Exception{
		return service.boardList(vo);
	}
	
	/**
	 * 게시물 조회
	 * 
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "admin/loadBoardInfo.do")
	public Map<String, Object> loadBoardInfo(HttpServletRequest request , BoardVO vo) throws Exception{
		return service.loadBoardInfo(vo);
	}
	
	/**
	 * 관리자 - 사용자관리
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "admin/userManagement.do")
	public String userManagement(HttpServletRequest request , Model model) throws Exception{
		model.addAttribute("adminMenuName","userManagement");
		return "admin/admin/userManagement";
	}
	
	/**
	 * 유저리스트 조회
	 * 
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "admin/userList.do")
	public Map<String, Object> userList(HttpServletRequest request , UserVO vo) throws Exception{
		return service.userList(vo);
	}
	
	/**
	 * 유저 조회
	 * 
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "admin/loadUserInfo.do")
	public UserVO loadUserInfo(HttpServletRequest request , UserVO vo) throws Exception{
		return service.loadUserInfo(vo);
	}
	
	/**
	 * 유저 수정
	 * 
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "admin/userModify.do")
	public Map<String, Object> userModify(HttpServletRequest request , UserVO vo) throws Exception{
		return service.userModify(vo);
	}
	
	/**
	 * 관리자 - 메뉴관리
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "admin/menuManagement.do")
	public String menuManagement(HttpServletRequest request , Model model) throws Exception{
		model.addAttribute("adminMenuName","menuManagement");
		return "admin/admin/menuManagement";
	}
	
	/**
	 * 메뉴 리스트 조회
	 * 
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "admin/menuList.do")
	public Map<String, Object> menuList(HttpServletRequest request , MenuVO vo) throws Exception{
		return service.menuList(vo);
	}
	
	/**
	 * 메뉴 추가
	 * 
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "admin/menuAdd.do")
	public Map<String, Object> menuAdd(HttpServletRequest request , MenuVO vo) throws Exception{
		HttpSession session = request.getSession();
		UserVO userInfo = (UserVO)session.getAttribute("userInfo");
		vo.setInstId(userInfo.getUserId());
		return service.menuAdd(vo);
	}
	
	/**
	 * 메뉴 수정
	 * 
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "admin/menuModify.do")
	public Map<String, Object> menuModify(HttpServletRequest request , MenuVO vo) throws Exception{
		HttpSession session = request.getSession();
		UserVO userInfo = (UserVO)session.getAttribute("userInfo");
		vo.setUpdtId(userInfo.getUserId());
		return service.menuModify(vo);
	}
	
	/**
	 * 메뉴 삭제
	 * 
	 * @param request
	 * @param menuSeqList
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "admin/menuDelete.do")
	public Map<String, Object> menuDelete(HttpServletRequest request , @RequestParam("menuSeqList[]") String[] menuSeqList) throws Exception{
		return service.menuDelete(menuSeqList);
	}
	
	/**
	 * 메뉴 조회
	 * 
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "admin/loadMenuInfo.do")
	public MenuVO loadMenuInfo(HttpServletRequest request , MenuVO vo) throws Exception{
		return service.loadMenuInfo(vo);
	}
}
