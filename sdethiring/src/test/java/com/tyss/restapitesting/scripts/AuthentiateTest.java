package com.tyss.restapitesting.scripts;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.tyss.restapitesting.generic.BaseLib;
import com.tyss.restapitesting.generic.IEndPoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * 
 * @author Ragavi
 *
 */
public class AuthentiateTest extends BaseLib {
	String token;
	String phoneNo;

	/**
	 * To get a jwttoken for authentication
	 */
	@Test
	public void authenticate() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("username", "rupeek");
		jsonObject.put("password", "password");
		RequestSpecification requestSpec = given();
		requestSpec.contentType(ContentType.JSON);
		requestSpec.body(jsonObject.toJSONString());
		Response response = requestSpec.post(IEndPoints.GET_TOKEN);
		token = response.jsonPath().get("token");
		response.getBody().prettyPrint();
		response.then().assertThat().statusCode(200).and().contentType(ContentType.JSON);
	}

	/**
	 * To get all customer resources from api
	 */
	@Test
	public void getAllCustomers() {

		Response response = given().auth().oauth2(token).when().get(IEndPoints.GET_ALL_RESOURCE);
		response.prettyPrint();
		response.then().assertThat().statusCode(200).and().contentType(ContentType.JSON);
		phoneNo = response.jsonPath().get("phone[0]");

	}

	/**
	 * To get user based on phone number
	 */
	@Test
	public void getUserWithPhoneNum() {

		Response response = given().auth().oauth2(token).pathParam("phone", phoneNo).when()
				.get(IEndPoints.GET_SINGLE_RESOURCE);
		response.prettyPrint();
		response.then().assertThat().statusCode(200).and().contentType(ContentType.JSON);
	}
}
