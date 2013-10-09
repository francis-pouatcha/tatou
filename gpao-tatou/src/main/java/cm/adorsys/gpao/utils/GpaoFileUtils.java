package cm.adorsys.gpao.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author clovisgakam
 *
 */
public class GpaoFileUtils {

	public static String  saveFile(String filePath, MultipartFile largeFile,String fileName)
			 {
		try {
			String originalFilename = largeFile.getOriginalFilename();
			String contentType = largeFile.getContentType();
			String fileExtension = "png";
			if (StringUtils.isBlank(contentType)) {
				if (StringUtils.isBlank(originalFilename)) {
					return null ;
				}
				fileExtension = FilenameUtils.getExtension(originalFilename);

			}
			String fn = fileName +"."+ fileExtension;
			System.out.println(fn);
			File metaDir = new File(GpaoDocumentDirectories.ROOT_PATH+filePath);
			metaDir.mkdirs();
			File diskFile = new File(metaDir, fn);
			FileOutputStream fileOutputStream = new FileOutputStream(diskFile);
			IOUtils.copy(largeFile.getInputStream(), fileOutputStream);
			IOUtils.closeQuietly(fileOutputStream);
			return filePath+fn ;
		} catch(IOException e){
			return null ;
		}
	}


	public static void deleteFile(String filePath,  String fileName){
		File lastDir = new File(filePath);
		if(fileName!=null){
			File file = new File(lastDir, fileName);
			if(file.exists())file.delete();
		}
	}

	public static boolean fileExist(String filePath,  String fileName){
		File lastDir = new File(filePath);
		if(fileName!=null){
			return fileExist(filePath, fileName)	;
		}
		return false ;
	}

	@SuppressWarnings("unused")
	public static void deleteFiles(String filePath) {
		File fileDIr = new File(filePath);
		try {
			FileUtils.forceDelete(fileDIr);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}




} 

