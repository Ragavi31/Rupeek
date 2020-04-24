package com.tyss.restapitesting.generic;

import static io.restassured.RestAssured.*;
import org.testng.annotations.BeforeSuite;

public class BaseLib {

	@BeforeSuite()
	public void preConfiguration() {
		baseURI = "http://13.126.80.194";
		port = 8080;

	}
}
