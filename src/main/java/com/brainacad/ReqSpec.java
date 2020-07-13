package com.brainacad;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;


public class ReqSpec {
   public static final RequestSpecification REQUEST_SPEC = new RequestSpecBuilder()
           .setConfig(RestAssured.config()
                   .objectMapperConfig(new ObjectMapperConfig(ObjectMapperType.GSON)))
            .setBaseUri("https://reqres.in")
            .setContentType(ContentType.JSON)
            .setBasePath("/api/{page}/{id}")
            .build();
}
