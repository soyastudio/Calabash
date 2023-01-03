package com.albertsons.application.rest;

import com.albertsons.application.actions.ActionExecutor;
import com.albertsons.application.actions.CmmAction;
import com.albertsons.application.actions.MappingAction;
import io.swagger.annotations.Api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/albertsons")
@Api(value = "Albertsons")
public class Albertsons {

    @GET
    @Path("/cmm")
    @Produces(MediaType.TEXT_PLAIN)
    public Response cmm(@HeaderParam("bod") String bod) {
        try {
            String result = ActionExecutor.newInstance(CmmAction.class)
                    .setProperty("bod", bod)
                    .execute();

            return Response.ok(result).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/mapping")
    @Produces(MediaType.TEXT_PLAIN)
    public Response mapping(@HeaderParam("xlsx") String xlsx, @HeaderParam("sheet") String sheet) {
        try {
            String result = ActionExecutor.newInstance(MappingAction.class)
                    .setProperty("file", xlsx)
                    .setProperty("sheet", sheet)
                    .execute();

            return Response.ok(result).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
