package com.tekarch.base;

//import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.techArk.requestPOJO.CreateRepoRequest;
import org.techArk.requestPOJO.UpdateRepoRequest;
import org.techArk.utils.EnvironmentDetails;
import org.testng.Assert;
import java.util.HashMap;



public class APIHelper {
	   private static RequestSpecification reqSpec;
	   private static final ObjectMapper objectMapper = new ObjectMapper();

	   static {
	        RestAssured.baseURI = EnvironmentDetails.getProperty("baseURL");
	        reqSpec = RestAssured.given().headers(getHeaders());
	    }

    private static HashMap<String, String> getHeaders() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + EnvironmentDetails.getProperty("token"));
        return headers;
    }
    
    
    
    public static Response getSingleRepository(String owner, String repo) {
        try {
            Response response = reqSpec.pathParam("owner", owner)
                                        .pathParam("repo", repo)
                                        .get("/repos/{owner}/{repo}");
            response.then().log().all();
            return response;
        } catch (Exception e) {
            Assert.fail("Get repository failed: " + e.getMessage());
            return null;
        }
    }

    public static Response getAllRepositories() {
        try {
            Response response = reqSpec.get("/user/repos");
            response.then().log().all();
            return response;
        } catch (Exception e) {
            Assert.fail("Get all repositories failed: " + e.getMessage());
            return null;
        }
    }

    public static Response createRepository(CreateRepoRequest createRepoRequest) {
        try {
            Response response = reqSpec.body(createRepoRequest).post("/user/repos");
            response.then().log().all();
            return response;
        } catch (Exception e) {
            Assert.fail("Create repository failed: " + e.getMessage());
            return null;
        }
    }

    public static Response updateRepository(String owner, String repo, UpdateRepoRequest updateRepoRequest) {
        try {
            Response response = reqSpec
            							.pathParam("owner", owner)
                                        .pathParam("repo", repo)
                                        .body(updateRepoRequest)
                                        .patch("/repos/{owner}/{repo}");
            response.then().log().all();
            return response;
        } catch (Exception e) {
            Assert.fail("Update repository failed: " + e.getMessage());
            return null;
        }
    }

    public static Response deleteRepository(String owner, String repo) {
        try {
            Response response = reqSpec
                                    .pathParam("owner", owner)
                                    .pathParam("repo", repo)
                                    .delete("/repos/{owner}/{repo}");

            // Log the response details
            response.then().log().all();
            
            return response;  // Return the response to be validated in the test
        } catch (Exception e) {
            Assert.fail("Delete repository failed: " + e.getMessage());
            return null;
        }
    }
}