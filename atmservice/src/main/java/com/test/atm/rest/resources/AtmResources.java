package com.test.atm.rest.resources;

import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.test.atm.rest.model.CustomerAccount;
import com.test.atm.rest.service.AtmService;

@Path("/customeraccounts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AtmResources {
	AtmService atmService = new AtmService();
	
	 	@POST
	 	@Path("/{name}")
	    public Response createAccount(@PathParam("name") String customerName) {
	 		CustomerAccount newCustomerAccount = atmService.createAccount(customerName);
	        return Response.ok().entity(newCustomerAccount).build();
	    }
	 	
	 	@POST
	 	@Path("/deposits/{id}/{amount}")
	 	public Response depositAmount(@PathParam("id") UUID id, @PathParam("amount") Double depositAmount){
	 		CustomerAccount customerAccount = atmService.depositAmount(id,depositAmount);
	 		return Response.ok().entity(customerAccount).build();
	 	}
	 	
	 	@POST
	 	@Path("/withdraws/{id}/{amount}")
	 	public Response withdrawAmount(@PathParam("id") UUID id, @PathParam("amount") Double withDrawAmount){
	 		CustomerAccount customerAccount = atmService.withdrawAmount(id,withDrawAmount);
	 		return Response.ok().entity(customerAccount).build();
	 	}
	 	
	 	@GET
	 	@Path("/balances/{id}")
	 	public Response displayBalance(@PathParam("id") UUID id){
	 		CustomerAccount customerAccount = atmService.displayBalance(id);
	 		return Response.ok().entity(customerAccount).build();
	 	}

}
