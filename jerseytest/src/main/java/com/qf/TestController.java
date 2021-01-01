package com.qf;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Component
@Path("/test")
public class TestController {

    @GET
    @Path("/mm")
    public  String main() {
        return "asas";
    }
}
