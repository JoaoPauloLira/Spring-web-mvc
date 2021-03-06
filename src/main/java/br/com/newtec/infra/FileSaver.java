package br.com.newtec.infra;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {

	@Autowired
	private HttpServletRequest httpServlet;
	
	public String SaveFale(String baseFolder, MultipartFile file) {

		try {
			String realPath = httpServlet.getServletContext().getRealPath("/"+baseFolder);
			String pathname = realPath + "/" + file.getOriginalFilename();
			file.transferTo(new File(pathname));
			return baseFolder + "/" + file.getOriginalFilename();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
