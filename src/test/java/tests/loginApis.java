package tests;

import com.shaft.api.RestActions;
import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class loginApis {


    SHAFT.API api;

    String baseurl = System.getProperty("BaseUrl");

    String Url = "/api/createAccount";

    String LoginUrl= "/api/verifyLogin";

    String mail = "marina"+System.currentTimeMillis()+"@gizasystem.com";
    String pass = "headway2023";


    @Test
    public void apiRegsiterUserTest()
    {
        apiRegisterUser(mail,pass);
        validateThatAccountCreated();
    }
    @Test(dependsOnMethods = "apiRegsiterUserTest")
    public void apiLoginUserTest()
    {
        apiLoginUser(mail,pass);
        validateThatUserlogged();
    }




    @BeforeClass
    public void beforeClass() {
        api = new SHAFT.API(baseurl);

    }

    public void apiRegisterUser(String Mail, String PassWord) {
        List <List<Object>> formInputs = Arrays.asList(
                Arrays.asList("name", "Marina"),
                Arrays.asList("email", Mail),
                Arrays.asList("password", PassWord),
                Arrays.asList("title", "Mrs"),
                Arrays.asList("birth_date", "22"),
                Arrays.asList("birth_month", "March"),
                Arrays.asList("birth_year", "2002"),
                Arrays.asList("firstname", "Marina"),
                Arrays.asList("lastname", "Nabil"),
                Arrays.asList("company", "gizasystems"),
                Arrays.asList("address1", "shoubra masr"),
                Arrays.asList("address2", "none"),
                Arrays.asList("country", "United States"),
                Arrays.asList("zipcode", "123456"),
                Arrays.asList("state", "la"),
                Arrays.asList("city", "california"),
                Arrays.asList("mobile_number", "01273226641")
        );
        api.post(Url)
                .setParameters(formInputs, RestActions.ParametersType.FORM)
                .setContentType(ContentType.URLENC)
                .setTargetStatusCode(200)
                .perform();

    }

    public void apiLoginUser(String Mail,String PassWord)
    {
        List <List<Object>> formInputs = Arrays.asList(
                Arrays.asList("email", Mail),
                Arrays.asList("password", PassWord)
        );

                api.post(LoginUrl)
                .setParameters(formInputs, RestActions.ParametersType.FORM)
                .setContentType(ContentType.URLENC)
                .setTargetStatusCode(200)
                .perform();
    }


        public void validateThatAccountCreated() {
        api.verifyThatResponse().body().contains("User created!").perform();

    }

    public void validateThatUserlogged() {
        api.verifyThatResponse().body().contains("User exists!").perform();

    }




}
