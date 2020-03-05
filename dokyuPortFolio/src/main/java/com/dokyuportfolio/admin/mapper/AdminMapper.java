package com.dokyuportfolio.admin.mapper;

import java.util.List;

import com.dokyuportfolio.admin.vo.BoardVO;
import com.dokyuportfolio.admin.vo.CategoryVO;
import com.dokyuportfolio.admin.vo.FileVO;
import com.dokyuportfolio.admin.vo.MenuVO;
import com.dokyuportfolio.main.vo.UserVO;

public interface AdminMapper {

	int selectMenuCount() throws Exception;
	
	List<MenuVO> selectMenuList(MenuVO vo) throws Exception;
	
	String selectMenuByMenuId(MenuVO vo) throws Exception;
	
	int insertMenu(MenuVO vo) throws Exception;
	
	int updateMenu(MenuVO vo) throws Exception;
	
	int deleteMenu(String[] menuSeqList) throws Exception;
	
	MenuVO selectMenuInfo(MenuVO vo) throws Exception;
	
	int selectUserCount() throws Exception;
	
	List<UserVO> selectUserList(UserVO vo) throws Exception;
	
	UserVO selectUserInfo(UserVO vo) throws Exception;
	
	int updateUser(UserVO vo) throws Exception;
	
	int selectBoardCount(BoardVO vo) throws Exception;
	
	List<BoardVO> selectBoardList(BoardVO vo) throws Exception;
	
	List<CategoryVO> selectCategoryListForSelectBox() throws Exception;
	
	int selectCategoryCount() throws Exception;
	
	List<CategoryVO> selectCategoryList(CategoryVO vo) throws Exception;
	
	String selectCategoryByCategoryId(CategoryVO vo) throws Exception;
	
	int insertCategory(CategoryVO vo) throws Exception;
	
	CategoryVO selectCategoryInfo(CategoryVO vo) throws Exception;
	
	int updateCategory(CategoryVO vo) throws Exception;
	
	int deleteCategory(String[] categorySeqList) throws Exception;
	
	int insertBoard(BoardVO vo) throws Exception;
	
	int updateBoard(BoardVO vo) throws Exception;
	
	int insertFile(FileVO vo) throws Exception;
	
	BoardVO selectBoardInfoByBoardSeq(BoardVO vo) throws Exception;
	
	FileVO selectThumbnailFileByBoardSeq(BoardVO vo) throws Exception;
	
	List<FileVO> selectFileListByBoardSeq(BoardVO vo) throws Exception;
	
	FileVO selectFileInfo(FileVO vo) throws Exception;
	
	int deleteFile(FileVO vo) throws Exception;
	
	void updateMainTopViewAll() throws Exception;
	
	int deleteBoard(BoardVO vo) throws Exception;
	
}
