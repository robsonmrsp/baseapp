package br.com.mr.app.rs;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.common.util.Base64Utility;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import br.com.mr.app.utils.ApplicationConfig;

@Path("/crud/uploads")
public class UploadResources {

	public Response uploadFile_(List<Attachment> attachments, @Context HttpServletRequest httpServletRequest) {
		String encode64 = "";
		DataUpload dataUpload = null;
		// String uploadFolder =
		// httpServletRequest.getServletContext().getRealPath("/") + "uploads/";
		String uploadFolder = ApplicationConfig.UPLOADS_FOLDER_LOCATION;
		for (Attachment attr : attachments) {
			DataHandler handler = attr.getDataHandler();
			try {
				InputStream stream = handler.getInputStream();

				String contentType = handler.getContentType();
				String nameFile = handler.getName();

				System.out.println("UploadResources.uploadFile()" + contentType);

				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				int read = 0;
				byte[] bytes = new byte[1024];
				while ((read = stream.read(bytes)) != -1) {
					bos.write(bytes);
				}

				if (contentType.contains("image")) {
					encode64 = Base64Utility.encode(bos.toByteArray());
					dataUpload = getDataUpload("IMG", getImageHtml(encode64));
				} else {
					// "uploads/"nameFile;
					FileOutputStream fileOutputStream = new FileOutputStream(new File(uploadFolder + File.separator + nameFile));
					fileOutputStream.write(bos.toByteArray());
					fileOutputStream.close();
					dataUpload = new DataUpload(contentType, "uploads/" + nameFile);
				}

				bos.close();
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return Response.ok(dataUpload).build();
	}

	@POST
	@Path("/file")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response uploadFile(List<Attachment> attachments, @Context HttpServletRequest httpServletRequest) {
		DataUpload dataUpload = null;
		// String uploadFolder =
		// httpServletRequest.getServletContext().getRealPath("/") + "uploads/";
		String uploadFolder = ApplicationConfig.UPLOADS_FOLDER_LOCATION;
		for (Attachment attr : attachments) {
			DataHandler handler = attr.getDataHandler();
			try {
				InputStream stream = handler.getInputStream();

				String contentType = handler.getContentType();
				String nameFile = System.currentTimeMillis() + "_" + handler.getName();
				ByteArrayOutputStream bos = null;
				if (contentType != null) {
					bos = new ByteArrayOutputStream();
					int read = 0;
					byte[] bytes = new byte[1024];
					while ((read = stream.read(bytes)) != -1) {
						bos.write(bytes);
					}
					FileOutputStream fileOutputStream = new FileOutputStream(new File(uploadFolder + File.separator + nameFile));
					fileOutputStream.write(bos.toByteArray());
					fileOutputStream.close();
					dataUpload = new DataUpload(contentType, "uploads/" + nameFile);
				}
				bos.close();
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return Response.ok(dataUpload).build();
	}

	private String getImageHtml(String encode64) {
		return "data:image/png;base64," + encode64;
	}

	private DataUpload getDataUpload(String type, String data) {
		return new DataUpload(type, data);
	}
}
