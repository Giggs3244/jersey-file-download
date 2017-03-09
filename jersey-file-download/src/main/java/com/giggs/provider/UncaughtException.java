package com.giggs.provider;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.giggs.util.GenericResponse;

@Provider
public class UncaughtException extends Throwable implements ExceptionMapper<Throwable> {

	private static final long serialVersionUID = 1L;

	@Override
	public Response toResponse(Throwable exception) {
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(new GenericResponse(500, "Something bad happened. Please try again."))
				.type(MediaType.APPLICATION_JSON).build();
	}

}
