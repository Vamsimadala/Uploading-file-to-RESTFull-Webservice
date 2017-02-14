package RESTFullSERVICE;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileOutputStream;

import org.omg.CORBA.portable.OutputStream;

import com.sun.jersey.core.header.FormDataContentDisposition;

@Path("/university")
public class Uploadfile {

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String uploadfile(
			@FormParam("file") InputStream uploadedInputStream,
			@FormParam("file") FormDataContentDisposition fileDetail){
		
		saveToDisk(uploadedInputStream,fileDetail);
		
		return "File upload succesfull";
		}
	
	private void saveToDisk(InputStream uploadedInputStream,
			FormDataContentDisposition fileDetail ){
		String uploadedFileLocation = "e://upload/" + fileDetail.getFileName();
		
		try{
			FileOutputStream out = new FileOutputStream(new File(
					uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];
			
			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes))!= -1 ){
				out.write(bytes,0,read);
			}
		out.flush();
		out.close();
		}catch (IOException e){
			e.printStackTrace();
		
		}
	}
	
	
}
