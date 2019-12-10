package com.test.automation.testautomation.spring.restcontrollers;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import com.test.automation.testautomation.TestAutomationApplication;
import com.test.automation.testautomation.domain.Page;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jayway.restassured.RestAssured.baseURI;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAutomationApplication.class)
@TestPropertySource(value = {"classpath:application.properties"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PageControllerTest {

    private static final String BASE_URI = "http://localhost";
    private static Long newPageId;

    @Value("${server.port}")
    private int port;

    @Before
    public void setBaseUri() {
        RestAssured.port = this.port;
        baseURI = BASE_URI;
    }

    @Test
    public void postNewValidPageTest() {
        ResponseBody test = given().contentType(ContentType.JSON)
                .body(createPageObject("TestPage", "http://testpage.com"))
                .post("/api/page")
                .then().statusCode(HttpStatus.CREATED.value()).extract().response();
        newPageId = test.as(Page.class).getId();
    }

    @Test
    public void postNewPageWithEmptyNameTest() {
        given().contentType(ContentType.JSON)
                .body(createPageObject("", "http://testwithoutname.com"))
                .post("/api/page")
                .then().statusCode(400);
    }

    @Test
    public void postNewPageWithEmptyUrlTest() {
        given().contentType(ContentType.JSON)
                .body(createPageObject("TestEmptyUrl", ""))
                .post("/api/page")
                .then().statusCode(400);
    }

    @Test
    public void postNewPageWithEmptyNameAndUrlTest() {
        given().contentType(ContentType.JSON)
                .body(createPageObject("", ""))
                .post("/api/page")
                .then().statusCode(400);
    }

    @Test
    public void checkNewlyCreatedPageDataTest() {
        given().pathParam("id", newPageId).
                get("/api/pages/id/{id}").then().assertThat().body("name", hasItem("TestPage")).and()
                .assertThat().body("url", hasItem("http://testpage.com"));
    }

    @Test
    public void deleteNewlyCreatedPageDataTest() {
        given().pathParam("id", newPageId)
                .delete("/api/page/id/{id}")
                .then().statusCode(HttpStatus.NO_CONTENT.value());
    }

    private String createPageObject(String name, String url) {

        JSONObject page = new JSONObject();

        try {
            page.put("name", name);
            page.put("url", url);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return page.toString();
    }
}
