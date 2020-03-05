package com.dokyuportfolio.admin.serviceImpl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dokyuportfolio.admin.mapper.AdminMapper;
import com.dokyuportfolio.admin.service.AdminService;
import com.dokyuportfolio.admin.vo.BoardVO;
import com.dokyuportfolio.admin.vo.CategoryVO;
import com.dokyuportfolio.admin.vo.FileVO;
import com.dokyuportfolio.admin.vo.MenuVO;
import com.dokyuportfolio.main.vo.UserVO;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminMapper mapper;
	
	@Override
	public Map<String, Object> menuList(MenuVO vo) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		vo.setTotCnt(mapper.selectMenuCount());
		List<MenuVO> list = mapper.selectMenuList(vo);
		result.put("list", list);
		result.put("paging",vo);
		return result;
	}
	
	@Override
	public Map<String, Object> categoryAdd(CategoryVO vo) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("state","fail");
		
		String categoryId = mapper.selectCategoryByCategoryId(vo);
		
		if(categoryId == null) {
			
			vo.setCategorySeq(UUID.randomUUID().toString());
			int res = mapper.insertCategory(vo);
			if(res > 0) {
				result.put("state", "success");
			}
		}
		
		return result;
	}

	@Override
	public Map<String, Object> menuAdd(MenuVO vo) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("state", "fail");
		
		String menuId = mapper.selectMenuByMenuId(vo);
		
		if(menuId == null) {
			
			vo.setMenuSeq(UUID.randomUUID().toString());
			int res = mapper.insertMenu(vo);
			if(res > 0) {
				result.put("state", "success");
			}
		}
		
		return result;
	}
	
	@Override
	public Map<String, Object> menuModify(MenuVO vo) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("state", "fail");
		
		int res = mapper.updateMenu(vo);
		if(res > 0) {
			result.put("state", "success");
		}
		
		return result;
	}
	
	@Override
	public Map<String, Object> categoryModify(CategoryVO vo) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("state", "fail");
		
		int res = mapper.updateCategory(vo);
		if(res > 0) {
			result.put("state", "success");
		}
		
		return result;
	}
	
	@Override
	public Map<String, Object> menuDelete(String[] menuSeqList) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("state", "fail");
		
		int res = mapper.deleteMenu(menuSeqList);
		if(res > 0) {
			result.put("state", "success");
		}
		
		return result;
	}
	
	@Override
	public Map<String, Object> categoryDelete(String[] categorySeqList) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("state", "fail");
		
		int res = mapper.deleteCategory(categorySeqList);
		if(res > 0) {
			result.put("state", "success");
		}
		
		return result;
	}
	
	@Override
	public MenuVO loadMenuInfo(MenuVO vo) throws Exception {
		return mapper.selectMenuInfo(vo);
	}

	@Override
	public Map<String, Object> userList(UserVO vo) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		vo.setTotCnt(mapper.selectUserCount());
		List<UserVO> list = mapper.selectUserList(vo);
		result.put("list", list);
		result.put("paging", vo);
		return result;
	}
	
	@Override
	public UserVO loadUserInfo(UserVO vo) throws Exception{
		return mapper.selectUserInfo(vo);
	}

	@Override
	public Map<String, Object> userModify(UserVO vo) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("state", "fail");
		
		int res = mapper.updateUser(vo);
		if(res > 0) {
			result.put("state", "success");
		}
		return result;
	}

	@Override
	public Map<String, Object> boardList(BoardVO vo) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		vo.setTotCnt(mapper.selectBoardCount(vo));
		List<BoardVO> list = mapper.selectBoardList(vo);
		result.put("list", list);
		result.put("paging", vo);
		return result;
	}
	
	@Override
	public List<CategoryVO> categoryListForSelectBox() throws Exception{
		return mapper.selectCategoryListForSelectBox();
	}
	
	@Override
	public Map<String, Object> categoryList(CategoryVO vo) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		vo.setTotCnt(mapper.selectCategoryCount());
		List<CategoryVO> list = mapper.selectCategoryList(vo);
		result.put("list", list);
		result.put("paging", vo);
		return result;
	}

	@Override
	public CategoryVO loadCategoryInfo(CategoryVO vo) throws Exception {
		return mapper.selectCategoryInfo(vo);
	}
	
	@Override
	public Map<String, Object> boardImageUpload(MultipartFile upload , String imageUploadPath , String baseUrl) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		String imageFileName = upload.getOriginalFilename();
		String imageFileExt  = imageFileName.substring(imageFileName.lastIndexOf(".") + 1);
		String uploadImageFileName = UUID.randomUUID().toString() + "." + imageFileExt;
		
		SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd");
		Date today = new Date();
		
		String uploadPath = imageUploadPath + format.format(today) + File.separator + uploadImageFileName;
		
		File imageFile = new File(uploadPath);
		if(!imageFile.exists()) {
			imageFile.mkdirs();
		}
		upload.transferTo(imageFile);
		
		result.put("fileName", uploadPath);
		result.put("uploaded", 1);
		result.put("url", baseUrl + "/static/uploadFile/images/"+format.format(today)+"/"+uploadImageFileName);
		return result;
	}
	
	@Override
	@Transactional
	public Map<String, Object> boardModify(BoardVO vo) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("state", "fail");
		
		/******** 게시글 UPDATE 전 메인최상위글 여부 N 으로 UPDATE START **********/
		if("Y".equals(vo.getMainTopView())) {
			mapper.updateMainTopViewAll();
		}
		/********* 게시글 UPDATE 전 메인최상위글 여부 N 으로 UPDATE END ***********/
		
		/********************* 게시글 UPDATE START ************************/
		int res = mapper.updateBoard(vo);
		/********************** 게시글 UPDATE END *************************/
		
		if(res > 0) {
			
			if(vo.getThumbnail() != null) {
				
				/********************* 썸네일 원본 저장 START ************************/
				String thumbOriginFileName = vo.getThumbnail().getOriginalFilename();
				String thumbOriginFileExt = thumbOriginFileName.substring(thumbOriginFileName.lastIndexOf(".") + 1);
				String thumbOriginSaveFileName = UUID.randomUUID().toString() + "." + thumbOriginFileExt;
				String thumbOriginFilePath = vo.getThumbnailUploadPath() + thumbOriginSaveFileName;
				File thumbOriginFile = new File(thumbOriginFilePath);
				if(!thumbOriginFile.exists()) {
					thumbOriginFile.mkdirs();
				}
				vo.getThumbnail().transferTo(thumbOriginFile);
				/********************** 썸네일 원본 저장 END *************************/
				
				/******************** 썸네일 생성 및 저장 START ***********************/
				BufferedImage srcImg = ImageIO.read(new File(thumbOriginFilePath));
				
				int thumbWidth = 644;
				int thumbHeight = 483;
				
				int originWidth = srcImg.getWidth();
				int originHeight = srcImg.getHeight();
				
				int newWidth = originWidth;
				int newHeight = (originWidth * thumbHeight) / thumbWidth;
				
				if(newHeight > originHeight) {
					newWidth = (originHeight * thumbWidth) / thumbHeight;
					newHeight = originHeight;
				}
				
				BufferedImage cropImg = Scalr.crop(srcImg, (originWidth-newWidth)/2, (originHeight-newHeight)/2, newWidth, newHeight);
				BufferedImage destImg = Scalr.resize(cropImg, thumbWidth, thumbHeight);
				
				String thumbFileName = UUID.randomUUID().toString() + "." + thumbOriginFileExt;
				String thumbFilePath = vo.getThumbnailUploadPath() + thumbFileName;
				File thumbFile = new File(thumbFilePath);
				if(ImageIO.write(destImg, thumbOriginFileExt, thumbFile)) {
					if(thumbOriginFile.delete()) {
						
						FileVO fileVO = new FileVO();
						fileVO.setFileSeq(UUID.randomUUID().toString());
						fileVO.setBoardSeq(vo.getBoardSeq());
						fileVO.setOriginFileName(thumbOriginFileName);
						fileVO.setExtensionName(thumbOriginFileExt);
						fileVO.setFileSize(String.valueOf(destImg.getData().getDataBuffer().getSize()));
						fileVO.setSaveFileName(thumbFileName);
						fileVO.setSaveFilePath(thumbFilePath);
						fileVO.setFileUrl(vo.getBaseUrl() + "/static/uploadFile/thumbnail/" + thumbFileName);
						fileVO.setFileKind("thumbnail");
						fileVO.setInstId(vo.getUpdtId());
						
						mapper.insertFile(fileVO);
					}
				}
				/********************* 썸네일 생성 및 저장 END ************************/
			}
			
			if(vo.getFiles() != null) {
				
				for(MultipartFile file : vo.getFiles()) {
					
					/********************* 파일 저장 START ************************/
					String originFileName = file.getOriginalFilename();
					String originFileExt = originFileName.substring(originFileName.lastIndexOf(".") + 1);
					String originSaveFileName = UUID.randomUUID().toString() + "." + originFileExt;
					String originFilePath = vo.getFileUploadPath() + originSaveFileName;
					File originFile = new File(originFilePath);
					if(!originFile.exists()) {
						originFile.mkdirs();
					}
					file.transferTo(originFile);
					
					FileVO fileVO = new FileVO();
					fileVO.setFileSeq(UUID.randomUUID().toString());
					fileVO.setBoardSeq(vo.getBoardSeq());
					fileVO.setOriginFileName(originFileName);
					fileVO.setExtensionName(originFileExt);
					fileVO.setFileSize(String.valueOf(file.getSize()));
					fileVO.setSaveFileName(originSaveFileName);
					fileVO.setSaveFilePath(originFilePath);
					fileVO.setFileUrl(vo.getBaseUrl() + "/static/uploadFile/file/" + originSaveFileName);
					fileVO.setFileKind("file");
					fileVO.setInstId(vo.getUpdtId());
					
					mapper.insertFile(fileVO);
					/********************** 파일 저장 END *************************/
					
				}
			}
			result.put("state", "success");
		}
		return result;
	}
	
	@Override
	@Transactional
	public Map<String, Object> boardWrite(BoardVO vo) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("state", "fail");
		
		/********************* 게시글 번호 생성 START ************************/
		vo.setBoardSeq(UUID.randomUUID().toString());
		/********************** 게시글 번호 생성 END *************************/
		
		/******** 게시글 INSERT 전 메인최상위글 여부 N 으로 UPDATE START **********/
		if("Y".equals(vo.getMainTopView())) {
			mapper.updateMainTopViewAll();
		}
		/********* 게시글 INSERT 전 메인최상위글 여부 N 으로 UPDATE END ***********/
		
		/********************* 게시글 INSERT START ************************/
		int res = mapper.insertBoard(vo);
		/********************** 게시글 INSERT END *************************/
		
		if(res > 0) {
			
			if(vo.getThumbnail() != null) {
				
				/********************* 썸네일 원본 저장 START ************************/
				String thumbOriginFileName = vo.getThumbnail().getOriginalFilename();
				String thumbOriginFileExt = thumbOriginFileName.substring(thumbOriginFileName.lastIndexOf(".") + 1);
				String thumbOriginSaveFileName = UUID.randomUUID().toString() + "." + thumbOriginFileExt;
				String thumbOriginFilePath = vo.getThumbnailUploadPath() + thumbOriginSaveFileName;
				File thumbOriginFile = new File(thumbOriginFilePath);
				if(!thumbOriginFile.exists()) {
					thumbOriginFile.mkdirs();
				}
				vo.getThumbnail().transferTo(thumbOriginFile);
				/********************** 썸네일 원본 저장 END *************************/
				
				/******************** 썸네일 생성 및 저장 START ***********************/
				BufferedImage srcImg = ImageIO.read(new File(thumbOriginFilePath));
				
				int thumbWidth = 644;
				int thumbHeight = 483;
				
				int originWidth = srcImg.getWidth();
				int originHeight = srcImg.getHeight();
				
				int newWidth = originWidth;
				int newHeight = (originWidth * thumbHeight) / thumbWidth;
				
				if(newHeight > originHeight) {
					newWidth = (originHeight * thumbWidth) / thumbHeight;
					newHeight = originHeight;
				}
				
				BufferedImage cropImg = Scalr.crop(srcImg, (originWidth-newWidth)/2, (originHeight-newHeight)/2, newWidth, newHeight);
				BufferedImage destImg = Scalr.resize(cropImg, thumbWidth, thumbHeight);
				
				String thumbFileName = UUID.randomUUID().toString() + "." + thumbOriginFileExt;
				String thumbFilePath = vo.getThumbnailUploadPath() + thumbFileName;
				File thumbFile = new File(thumbFilePath);
				if(ImageIO.write(destImg, thumbOriginFileExt, thumbFile)) {
					if(thumbOriginFile.delete()) {
						
						FileVO fileVO = new FileVO();
						fileVO.setFileSeq(UUID.randomUUID().toString());
						fileVO.setBoardSeq(vo.getBoardSeq());
						fileVO.setOriginFileName(thumbOriginFileName);
						fileVO.setExtensionName(thumbOriginFileExt);
						fileVO.setFileSize(String.valueOf(destImg.getData().getDataBuffer().getSize()));
						fileVO.setSaveFileName(thumbFileName);
						fileVO.setSaveFilePath(thumbFilePath);
						fileVO.setFileUrl(vo.getBaseUrl() + "/static/uploadFile/thumbnail/" + thumbFileName);
						fileVO.setFileKind("thumbnail");
						fileVO.setInstId(vo.getInstId());
						
						mapper.insertFile(fileVO);
					}
				}
				/********************* 썸네일 생성 및 저장 END ************************/
			}
			
			if(vo.getFiles() != null) {
				
				for(MultipartFile file : vo.getFiles()) {
					
					/********************* 파일 저장 START ************************/
					String originFileName = file.getOriginalFilename();
					String originFileExt = originFileName.substring(originFileName.lastIndexOf(".") + 1);
					String originSaveFileName = UUID.randomUUID().toString() + "." + originFileExt;
					String originFilePath = vo.getFileUploadPath() + originSaveFileName;
					File originFile = new File(originFilePath);
					if(!originFile.exists()) {
						originFile.mkdirs();
					}
					file.transferTo(originFile);
					
					FileVO fileVO = new FileVO();
					fileVO.setFileSeq(UUID.randomUUID().toString());
					fileVO.setBoardSeq(vo.getBoardSeq());
					fileVO.setOriginFileName(originFileName);
					fileVO.setExtensionName(originFileExt);
					fileVO.setFileSize(String.valueOf(file.getSize()));
					fileVO.setSaveFileName(originSaveFileName);
					fileVO.setSaveFilePath(originFilePath);
					fileVO.setFileUrl(vo.getBaseUrl() + "/static/uploadFile/file/" + originSaveFileName);
					fileVO.setFileKind("file");
					fileVO.setInstId(vo.getInstId());
					
					mapper.insertFile(fileVO);
					/********************** 파일 저장 END *************************/
					
				}
				
			}
			
			result.put("state", "success");
		}
		
		return result;
	}
	
	@Override
	public Map<String, Object> loadBoardInfo(BoardVO vo) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("boardInfo", null);
		result.put("thumbnailFileInfo", null);
		result.put("fileInfoList", null);
		
		BoardVO boardInfo = mapper.selectBoardInfoByBoardSeq(vo);
		FileVO thumbnailFileInfo = mapper.selectThumbnailFileByBoardSeq(vo);
		List<FileVO> fileInfoList = mapper.selectFileListByBoardSeq(vo);
		
		result.put("boardInfo", boardInfo);
		result.put("thumbnailFileInfo", thumbnailFileInfo);
		result.put("fileInfoList", fileInfoList);
		
		return result;
	}

	@Override
	@Transactional
	public Map<String, Object> fileDel(FileVO vo) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("state", "fail");
		
		FileVO fileInfo = mapper.selectFileInfo(vo);
		int res = mapper.deleteFile(vo);
		
		if(res > 0) {
			File thumbnailFile = new File(fileInfo.getSaveFilePath());
			thumbnailFile.delete();
			result.put("state", "success");
		}
		return result;
	}

	@Override
	@Transactional
	public Map<String, Object> boardDel(BoardVO vo) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("state", "fail");
		
		FileVO thumbFileInfo = mapper.selectThumbnailFileByBoardSeq(vo);
		List<FileVO> fileInfoList = mapper.selectFileListByBoardSeq(vo);
		
		if(thumbFileInfo != null) {
			int res = mapper.deleteFile(thumbFileInfo);
			if(res > 0) {
				File thumbFile = new File(thumbFileInfo.getSaveFilePath());
				thumbFile.delete();
			}
		}
		
		for(FileVO fileVO : fileInfoList) {
			int res = mapper.deleteFile(fileVO);
			if(res > 0) {
				File file = new File(fileVO.getSaveFilePath());
				file.delete();
			}
		}
		
		int res = mapper.deleteBoard(vo);
		if(res > 0) {
			result.put("state", "success");
		}
		return result;
	}
}
