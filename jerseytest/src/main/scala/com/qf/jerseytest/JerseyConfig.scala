package com.qf.jerseytest

import org.glassfish.jersey.server.ResourceConfig
import org.springframework.context.annotation.Configuration

@Configuration
class JerseyConfig  extends  ResourceConfig{
   register(classOf[JerseyController])
}
