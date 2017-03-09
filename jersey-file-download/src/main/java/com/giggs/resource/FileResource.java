package com.giggs.resource;

import java.io.File;
import java.net.URL;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.giggs.provider.MissingFileException;

@Path("/download")
public class FileResource {

	final private static String DIRECTORY_FILES = "files/";
	final private static String EXTENSION_FILES = ".pdf";

	public FileResource() {
		System.out.println("FileResource here!");
	}

	@GET
	@Path("/saludo")
	public String saludar() {
		return "Hola";
	}

	@GET
	@Path("/directDownloadFile")
	public Response getFile() throws MissingFileException {

		ClassLoader classLoader = getClass().getClassLoader();
		URL locationFile = classLoader.getResource("files/tarea_estadistica.pdf");
		File file = null;

		if (locationFile != null) {
			file = new File(locationFile.getFile());
		} else {
			throw new MissingFileException("File not found.");
		}

		return Response.ok(file, MediaType.APPLICATION_OCTET_STREAM)
				.header("Content-Disposition", "attachment; filename=" + file.getName()).build();
	}

	@GET
	@Path("/{fileName}")
	public Response getFile(@PathParam("fileName") String fileName) throws MissingFileException {

		String evalLocationFile = DIRECTORY_FILES + fileName + EXTENSION_FILES;
		String fileNameExt = fileName + EXTENSION_FILES;
		File file = null;

		ClassLoader classLoader = this.getClass().getClassLoader();
		URL locationFile = classLoader.getResource(evalLocationFile);

		if (locationFile != null) {
			file = new File(locationFile.getFile());
		} else {
			throw new MissingFileException(fileNameExt + " File not found.");
		}

		return Response.ok(file, MediaType.APPLICATION_OCTET_STREAM)
				.header("Content-Disposition", "attachment; filename=" + file.getName()).build();

	}

}
