package com.dokyuportfolio.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.dokyuportfolio.admin.vo.BoardVO;
import com.dokyuportfolio.admin.vo.CategoryVO;
import com.dokyuportfolio.admin.vo.FileVO;
import com.dokyuportfolio.admin.vo.MenuVO;
import com.dokyuportfolio.main.vo.UserVO;

public interface AdminService {
	
	Map<String, Object> menuList(MenuVO vo) throws Exception;
	
	Map<String, Object> menuAdd(MenuVO vo) throws Exception;
	
	Map<String, Object> menuModify(MenuVO vo) throws Exception;
	
	Map<String, Object> menuDelete(String[] menuSeqList) throws Exception;
	
	MenuVO loadMenuInfo(MenuVO vo) throws Exception;
	
	Map<String, Object> userList(UserVO vo) throws Exception;
	
	UserVO loadUserInfo(UserVO vo) throws Exception;
	
	Map<String, Object> userModify(UserVO vo) throws Exception;
	
	Map<String, Object> boardList(BoardVO vo) throws Exception;
	
	List<CategoryVO> categoryListForSelectBox() throws Exception;
	
	Map<String, Object> categoryList(CategoryVO vo) throws Exception;
	
	Map<String, Object> categoryAdd(CategoryVO vo) throws Exception;
	
	CategoryVO loadCategoryInfo(CategoryVO vo) throws Exception;
	
	Map<String, Object> categoryModify(CategoryVO vo) throws Exception;
	
	Map<String, Object> categoryDelete(String[] categorySeqList) throws Exception;
	
	Map<String, Object> boardWrite(BoardVO vo) throws Exception;
	
	Map<String, Object> loadBoardInfo(BoardVO vo) throws Exception;
	
	Map<String, Object> fileDel(FileVO vo) throws Exception;
	
	Map<String, Object> boardModify(BoardVO vo) throws Exception;
	
	Map<String, Object> boardImageUpload(MultipartFile upload , String imageUploadPath , String baseUrl) throws Exception;
	
	Map<String, Object> boardDel(BoardVO vo) throws Exception;

}
