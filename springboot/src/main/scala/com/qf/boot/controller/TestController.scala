package com.qf.boot.controller

import org.springframework.web.bind.annotation.{GetMapping, RestController}

@RestController
class TestController {

  @GetMapping(Array("/test"))
  def test( mm:String): String = {
    mm
  }

}
