package com.tenniscourts.config;

import java.net.URI;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
//@Api(tags = { "Tennis Court challenge" })
//@RestController
public class BaseRestController {

  protected URI locationByEntity(Long entityId){
        return ServletUriComponentsBuilder.fromCurrentRequest().path(
                "/{id}").buildAndExpand(entityId).toUri();
    }
}
