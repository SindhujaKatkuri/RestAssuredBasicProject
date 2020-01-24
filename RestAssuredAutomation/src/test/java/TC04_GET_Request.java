import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC04_GET_Request {
	
	@Test
	public void getGoogleMapAPIPrintingAllHeaders() {
		
				//Specify Base URI
				RestAssured.baseURI = "https://maps.googleapis.com";
				
				//Request Object - For Sending Request to the server
				RequestSpecification httpRequest = RestAssured.given();
				
				//Response Object (response) - To store the Response
				//response object contains response body, header, status code etc
				Response response = httpRequest.request(Method.GET,"/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AlzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jy1s");
				
				//Print Response in Console Window
				//responseBody will have response body of the response
				//asString converts json into string and stores in responseBody object
				String responseBody = response.getBody().asString();
				System.out.println("Response Body is:" +responseBody); //Can't get response coz key value is invalid
				
				//Capture details of header from response
				//Printing All Headers
				Headers allHeaders = response.headers(); //captures all the headers from response
				for(Header header:allHeaders)
				{
					System.out.println(header.getName() + "		" +header.getValue());
				}		
		
	}

}
