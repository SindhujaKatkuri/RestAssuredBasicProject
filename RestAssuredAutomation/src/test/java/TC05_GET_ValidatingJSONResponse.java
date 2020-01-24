import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC05_GET_ValidatingJSONResponse {
	
	@Test
	public void getWeatherAPIValidatingJSONResponse() {
		
				//Specify Base URI
				RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
				
				//Request Object - For Sending Request to the server
				RequestSpecification httpRequest = RestAssured.given();
				
				//Response Object (response) - To store the Response
				//response object contains response body, header, status code etc
				Response response = httpRequest.request(Method.GET,"/Delhi");
				
				//Print Response in Console Window
				//responseBody will have response body of the response
				//asString converts json into string and stores in responseBody object
				String responseBody = response.getBody().asString();
				System.out.println("Response Body is:" +responseBody);  
				Assert.assertEquals(responseBody.contains("Delhi"),true);			
		
	}

}
