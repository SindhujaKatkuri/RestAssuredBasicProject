import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC01_GET_Request {

	@Test
	void getWeatherDetails(){
		
		//Specify Base URI
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		
		//Request Object - For Sending Request to the server
		RequestSpecification httpRequest = RestAssured.given();
		
		//Response Object (response) - To store the Response
		//response object contains response body, header, status code etc
		Response response = httpRequest.request(Method.GET,"/Hyderabad");
		
		//Print Response in Console Window
		//responseBody will have response body of the response
		//asString converts json into string and stores in responseBody object
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is:" +responseBody);
		
		//Status Code Validation
		int statusCode = response.getStatusCode();
		System.out.println("Status Code is:" +statusCode);
		Assert.assertEquals(statusCode, 200);
		
		//Status Line Verification
		String statusLine = response.getStatusLine();
		System.out.println("Status Line is:" +statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
	}
	
}
	