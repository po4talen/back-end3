package com.brainacad;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;


public class ReqSpec {
   public static final RequestSpecification REQUEST_SPEC = new RequestSpecBuilder()
            .setBaseUri("https://reqres.in")
            .setContentType(ContentType.JSON)
            .setBasePath("/api/{page}/{id}")
            .build();
}
