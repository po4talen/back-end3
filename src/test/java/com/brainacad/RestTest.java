package com.brainacad;

import org.apache.http.HttpResponse;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static com.brainacad.JsonUtils.stringFromJSONByPath;


public class RestTest{

    private static final String URL="https://reqres.in/";

    @Test//GET LIST USERS метод
    public void checkGetResponseStatusCodeListUsers() throws IOException {
        String endpoint="/api/users";

        //Выполняем REST GET запрос с нашими параметрами
        // и сохраняем результат в переменную response.
        HttpResponse response = HttpClientHelper.get(URL+endpoint,"page=2");

        //получаем статус код из ответа
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code : " + statusCode);
        Assert.assertEquals("Response status code should be 200", 200, statusCode);
    }

    @Test//GET LIST USERS метод
    public void checkGetResponseBodyNotNullListUsers() throws IOException {
        String endpoint="/api/users";

         //Выполняем REST GET запрос с нашими параметрами
        // и сохраняем результат в переменную response.
        HttpResponse response = HttpClientHelper.get(URL+endpoint,"page=2");

        //Конвертируем входящий поток тела ответа в строку
        String body=HttpClientHelper.getBodyFromResponse(response);
        System.out.println(body);
        Assert.assertNotEquals("Body shouldn't be null", null, body);
    }

    @Test//POST метод
    public void checkPostResponseStatusCodeCreate() throws IOException {
        String endpoint="/api/users";

        //создаём тело запроса
        String requestBody="{\"name\": \"morpheus\",\"job\": \"leader\"}";

        //Выполняем REST POST запрос с нашими параметрами
        // и сохраняем результат в переменную response.
        HttpResponse response = HttpClientHelper.post(URL+endpoint,requestBody);

        //получаем статус код из ответа
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code : " + statusCode);
        Assert.assertEquals("Response status code should be 201", 201, statusCode);
    }

    @Test//POST метод
    public void checkPostResponseBodyNotNullCreate() throws IOException {
        String endpoint="/api/users";

        //создаём тело запроса
        String requestBody="{\"name\": \"morpheus\",\"job\": \"leader\"}";

        //Выполняем REST POST запрос с нашими параметрами
        // и сохраняем результат в переменную response.
        HttpResponse response = HttpClientHelper.post(URL+endpoint,requestBody);

        //Конвертируем входящий поток тела ответа в строку
        String body=HttpClientHelper.getBodyFromResponse(response);
        System.out.println(body);
        Assert.assertNotEquals("Body shouldn't be null", null, body);
    }

    //TODO: напишите по тесткейсу на каждый вариант запроса на сайте https://reqres.in
    //TODO: в тескейсах проверьте Result Code и несколько параметров из JSON ответа (если он есть)
    @Test //GET SINGLE USER NOT FOUND
    public void checkGetResponseStatusCodeSingleUserNotFound() throws IOException {
        String endpoint="/api/users/23";
        HttpResponse response = HttpClientHelper.get(URL+endpoint,"");
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code : " + statusCode);
        Assert.assertEquals("Response status code should be 404", 404, statusCode);
    }

    @Test // PUT UPDATE
    public void checkPutResponseBodyUpdate() throws IOException {
        String endpoint="/api/users/2";
        String requestBody="{\"name\": \"morpheus\",\"job\": \"zion resident\"}";
        HttpResponse response = HttpClientHelper.put(URL+endpoint,requestBody);
        String body=HttpClientHelper.getBodyFromResponse(response);
        //System.out.println(body);
        String data = stringFromJSONByPath(body, "$.name");
        //System.out.println("First name = " + data);
        Assert.assertEquals("First name should be Charles", "morpheus", data);
    }

}
