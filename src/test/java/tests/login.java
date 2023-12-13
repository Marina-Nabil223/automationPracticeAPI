package tests;

import com.shaft.api.RestActions;
import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;


public class login {

    SHAFT.GUI.WebDriver driver;
    String homePageUrl = "https://automationexercise.com";

    String mail = "marina"+System.currentTimeMillis()+"@gizasystem.com";
    String pass = "headway2023";

    String userName="Marina";


    private final By homePageTitle = By.xpath("(//h2[@class='title text-center'])[1]");
    private final By selectSignUpIcon  = By.xpath("(//a//i)[4]");
    private final By signUpMsg = By.xpath("(//h2)[3]");
    private final By username =By.name("name");
    private final By userMail =By.xpath("//input[@data-qa='signup-email']");
    private final By signUPBtn =   By.xpath("//button[@data-qa='signup-button']");

    private final By accountInfoTxt = By.xpath("(//h2[@class='title text-center']//b)[1]");
    private final By gender = By.id("id_gender2");
    private final By password =  By.id("password");
    private final By days =  By.id("days");
    private final By months =  By.id("months");
    private final By years =  By.id("years");
    private final By newsletter =  By.id("newsletter");
    private final By optin =  By.id("optin");
    private final By firstName =  By.id("first_name");
    private final By LastName =  By.id("last_name");
    private final By addressOne =  By.id("address1");
    private final By addressTwo =  By.id("address2");
    private final By country =  By.id("country");
    private final By state =  By.id("state");
    private final By city = By.id("city");
    private final By zipcode =  By.id("zipcode");
    private final By mobileNumber =  By.id("mobile_number");
    private final By createAccountBtn =  By.xpath("(//button[@class='btn btn-default'])[1]");
    //private final By ad =  By.xpath("//div[@class='grippy-host']");
    private final By createAccountMsg = By.xpath("//h2[@data-qa='account-created']//b");
    private final By contiueBtn = By.xpath("//a[@class='btn btn-primary']");
    private final By logoutBtn  = By.xpath("(//a//i)[4]");
    private final By loginMail  = By.xpath("//input[@data-qa='login-email']");
    private final By loginPassword  = By.xpath("//input[@data-qa='login-password']");
    private final By loginBtn  = By.xpath("//button[@data-qa='login-button']");
    private final By assertLogin  = By.xpath("(//a//b)[1]");


    @Test
    public void test() {
        assertOnVisibilityOfHomePage("FEATURES ITEMS");
        SignUp();
        assertOnSignUpPage("New User Signup!");
        UserSignUp(userName,mail);
        assertOnAccountInfoPage("ENTER ACCOUNT INFORMATION");
        enterAccountInformation(pass,"Marina","Nabil","22","March","2002");
        enterAddressInformation("Shoubra Masr","none","United States","California","log anglos","14161","01100303107");
        assertOnAccountCreated("ACCOUNT CREATED!");
        userLogout();
        userLogin(mail,pass);
        assertLogin(userName);


    }



    @BeforeClass
    public void beforeClass() {
        driver = new SHAFT.GUI.WebDriver();
       navigateToHomePage();

    }


    public void navigateToHomePage() {

        driver.browser().navigateToURL(homePageUrl);
    }

    public void assertOnVisibilityOfHomePage(String expectedResult) {
        driver.element().getText(homePageTitle);
        driver.assertThat().element(homePageTitle).text().isEqualTo(expectedResult).perform();

    }

    public void SignUp () {

        driver.element().click(selectSignUpIcon);
    }



    public void assertOnSignUpPage(String expectedResult) {
        driver.element().getText(signUpMsg);
        driver.assertThat().element(signUpMsg).text().isEqualTo(expectedResult).perform();
    }

    public void UserSignUp(String name,String email)
    {
        driver.element().type(username,name);
        driver.element().type(userMail,email);
        driver.element().click(signUPBtn);
    }

    public void assertOnAccountInfoPage(String expectedResult)
    {
        driver.element().getText(accountInfoTxt);
        driver.assertThat().element(accountInfoTxt).text().isEqualTo(expectedResult).perform();

    }

    public void enterAccountInformation(String pass,String userFirstName,String userLastName,String day,String month,String year) {
        driver.element().click(gender);
        driver.element().type(password,pass);

        driver.element().select(days,day);
        driver.element().select(months,month);
        driver.element().select(years,year);

        driver.element().click(newsletter);
        driver.element().click(optin);

        driver.element().type(firstName,userFirstName);
        driver.element().type(LastName,userLastName);
    }
    public void enterAddressInformation(String userAddressOne,String userAddressTwo,String Country,String State, String City , String zipCode, String userMobileNumber)
    {
        driver.element().type(addressOne,userAddressOne);
        driver.element().type(addressTwo,userAddressTwo);
        driver.element().select(country,Country);
        driver.element().type(state,State);
        driver.element().type(city,City);
        driver.element().type(zipcode,zipCode);
        driver.element().type(mobileNumber,userMobileNumber);
        //driver.element().click(ad);
        driver.element().click(createAccountBtn);
    }

    public void assertOnAccountCreated(String expectedResult) {

        driver.element().getText(createAccountMsg);
        driver.assertThat().element(createAccountMsg).text().isEqualTo(expectedResult).perform();
    }

    public void userLogout(){
        driver.element().click(contiueBtn);
        driver.element().click(logoutBtn);

    }

    public void userLogin(String email, String pass)
    {
        driver.element().click(loginBtn);
        driver.element().type(loginMail,email);
        driver.element().type(loginPassword,pass);
        driver.element().click(loginBtn);

    }

    public void assertLogin(String expectedResult)
    {
        driver.assertThat().element(assertLogin).text().isEqualTo(expectedResult).perform();
    }











}
