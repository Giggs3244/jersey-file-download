package com.giggs.provider;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.giggs.util.GenericResponse;

@Provider
public class MissingFileException extends Exception implements ExceptionMapper<MissingFileException> {

	private static final long serialVersionUID = 1L;

	public MissingFileException() {
		super("No file found with given name!");
	}

	public MissingFileException(String message) {
		super(message);
	}

	@Override
	public Response toResponse(MissingFileException exception) {
		return Response.status(Status.NOT_FOUND).entity(new GenericResponse(404, exception.getMessage()))
				.type(MediaType.APPLICATION_JSON).build();
	}

}
