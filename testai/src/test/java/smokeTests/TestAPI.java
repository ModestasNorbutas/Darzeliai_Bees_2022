package smokeTests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestAPI {

    static SessionFilter sessionFilter = new SessionFilter();

    RequestSpecification reqSpec = new RequestSpecBuilder().
        setBaseUri("https://bees.akademijait.vtmc.lt/darzelis").
            setContentType(ContentType.JSON).
            addFilters(Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter())).
            build();

    //log in and rerutn session ID
    public static SessionFilter logInApi(String username, String pwd, RequestSpecification reqSpec) {
        given().
                spec(reqSpec).
                contentType("application/x-www-form-urlencoded; charset=utf-8").
                formParam("username", username).
                formParam("password", pwd).
                filter(sessionFilter).
                when().
                post("login");
        return sessionFilter;
    }

    public static void logOutApi(RequestSpecification reqSpec) {
        given().
                spec(reqSpec).
                filter(sessionFilter).
                when().
                post("logout");
    }


    @Test(groups = "smoke", dataProvider = "parameters")
    public void api_shouldLogInAndOut(String username, String pwd, String role) {

        SessionFilter sessionFilter =
                logInApi(username, pwd, reqSpec);
        given().
                spec(reqSpec).
                filter(sessionFilter).
                when().
                get("api/users/user").
                then().
                body("role", equalTo(role)).
                body("username", equalTo(username));
        logOutApi(reqSpec);

        // assert that logout was successful
        given().
                spec(reqSpec).
                filter(sessionFilter).
                when().
                get("api/users/user").
                then().
                statusCode(401);
    }

    @DataProvider
    public Object[][] parameters() {

        return new Object[][]{
                {"user@user.lt", "user@user.lt", "USER"},
                {"admin@admin.lt", "admin@admin.lt", "ADMIN"},
                {"manager@manager.lt", "manager@manager.lt", "MANAGER"}
        };
    }

}
