package com.qf.jerseytest

import javax.ws.rs.{GET, Path}
import org.springframework.stereotype.Component

@Component
@Path("/api")
class JerseyController {

  @GET
  @Path("/test")
  def apitest(): String = {
    "bcbbbb"
  }
}
