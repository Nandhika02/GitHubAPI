package org.techArk.tests.CRUD;

import org.techArk.utils.TestDataUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.tekarch.base.APIHelper;
import com.tekarch.base.BaseTest;

import io.restassured.response.Response;

public class DeleteRepoTest extends BaseTest {

	@Test(description = "Delete an existing repository")
    public void testDeleteRepository() {
        String owner = TestDataUtils.getProperty("repoOwner");
        String repo = "Hello-";
        Response response = APIHelper.deleteRepository(owner, repo);
        Assert.assertEquals(response.getStatusCode(), 204);
        Assert.assertTrue(response.getBody().asString().isEmpty(), "Expected empty response body for 204 status");
	
}
	
	
	@Test(description = "Delete a repository with non-existing name")
    public void testDeleteRepositorywithnonexistingname() {
        String owner = TestDataUtils.getProperty("repoOwner");
        String repo = "Welcome";
        Response response = APIHelper.deleteRepository(owner, repo);
        Assert.assertEquals(response.getStatusCode(), 404, "Expected status code 404 for non-existing repository");

     
        String actualMessage = response.jsonPath().getString("message");
        Assert.assertEquals(actualMessage, "Not Found", "Expected error message 'Not Found' for non-existing repository deletion attempt");
    }
}