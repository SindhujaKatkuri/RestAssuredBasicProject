import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC02_POST_Request {

	@Test
	void postRegistrationCustomerAPI(){
		
		//Specify Base URI
		RestAssured.baseURI = "http://restapi.demoqa.com/customer";
		
		//Request Object - For Sending Request to the server
		RequestSpecification httpRequest = RestAssured.given();
		
		//Response Object (response) - To store the Response
		//Request Payload sending along with POST Request
		//Along with POST Request, we have to send parameters - request parameters and they are in JSON format
		//response object contains response body, header, status code etc
		JSONObject requestParams = new JSONObject();
		
		requestParams.put("FirstName", "Sindhu");
		requestParams.put("LastName", "Katkuri");
		requestParams.put("UserName", "SindhuKatkuri");
		requestParams.put("Password", "CnduAxe@2413");
		requestParams.put("Email", "SindhuKatkuri@gmail.com");
		
		httpRequest.header("Conntent-Type","application/json");
		
		//request params are converted to JSON and sent in request body
		//attach above data to the request
		httpRequest.body(requestParams.toJSONString());
		
		//Response Object (response) - To store the Response
		//response object contains response body, header, status code etc
		//Request goes to the server and creates new record
		Response response = httpRequest.request(Method.POST,"/register");
		
		//Print Response in Console Window
		//responseBody will have response body of the response
		//asString converts json into string and stores in responseBody object
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is:" +responseBody);
		
		//Status Code Validation
		int statusCode = response.getStatusCode();
		System.out.println("Status Code is:" +statusCode);
		Assert.assertEquals(statusCode, 201);
		
		//Success Code Verification
		//data is available in json path
		String successCode = response.jsonPath().get("SuccessCode");
		System.out.println("Success Code is:" +successCode);
		Assert.assertEquals(successCode, "OPERATION_SUCCESS");
		
	}
	
}
