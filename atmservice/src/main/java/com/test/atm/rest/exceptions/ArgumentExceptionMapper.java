package com.test.atm.rest.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.test.atm.rest.model.ErrorMessage;

@Provider
public class ArgumentExceptionMapper implements ExceptionMapper<ArgumentException>{

	@Override
	public Response toResponse(ArgumentException ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 404);
		return Response.status(Status.BAD_REQUEST)
				.entity(errorMessage)
				.build();
	}

}
