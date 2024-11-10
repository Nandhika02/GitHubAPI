package org.techArk.tests.CRUD;

import org.techArk.requestPOJO.UpdateRepoRequest;
import org.techArk.utils.TestDataUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.tekarch.base.APIHelper;
import com.tekarch.base.BaseTest;

import io.restassured.response.Response;

public class UpdateRepoTest extends BaseTest {

	 @Test(description = "Update repository name")
	    public void testUpdateRepository() {
	        String owner = TestDataUtils.getProperty("repoOwner");
	        String repo = "Hello-World";
	        UpdateRepoRequest updateRepoRequest = new UpdateRepoRequest("Updated-Repo-Name", "my repository created using APIs after update", false);
	        Response response = APIHelper.updateRepository(owner, repo, updateRepoRequest);
	        Assert.assertEquals(response.getStatusCode(), 200);
	        Assert.assertEquals(response.jsonPath().getString("name"), updateRepoRequest.getName());
	    }
}
