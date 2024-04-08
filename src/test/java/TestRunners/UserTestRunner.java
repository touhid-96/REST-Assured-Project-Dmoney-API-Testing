package TestRunners;

import Config.Setup;
import Config.TransModel;
import Config.UserModel;
import Controllers.UserController;
import Utils.Utils;
import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserTestRunner extends Setup {
    UserController userController;

    @Test(priority = 1, description = "Admin can login successfully")
    public void adminLogin() throws ConfigurationException {
        userController = new UserController(properties.getProperty("baseURL"));

        UserModel userModel = new UserModel();
        userModel.setEmail("salman@roadtocareer.net");
        userModel.setPassword("1234");

        Response response = userController.login(userModel);

        //assertion
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getString("message"), "Login successfully");

        //save token
        Utils.setEnvVariable("token", jsonPath.getString("token"));
    }

    @Test(priority = 2, description = "Admin can create customer 1")
    public void createCustomer1() throws ConfigurationException {

        userController = new UserController(
                properties.getProperty("baseURL"),
                properties.getProperty("token"),
                properties.getProperty("secretKey")
        );

        Faker faker = new Faker();
        UserModel userModel = new UserModel();
        userModel.setName(faker.name().fullName());
        userModel.setEmail(faker.internet().emailAddress());
        userModel.setPassword("1234");
        userModel.setPhone_number("019"+Utils.generateRandomDigits(8));
        userModel.setNid("6582"+Utils.generateRandomDigits(6));
        userModel.setRole("Customer");

        Response response = userController.createUser(userModel);

        //assertion
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("message"), "User created");

        //save customer info
        Utils.setEnvVariable("customer1_phone", jsonPath.getString("user.phone_number"));
    }

    @Test(priority = 3, description = "Admin can create customer 2")
    public void createCustomer2() throws ConfigurationException {

        userController = new UserController(
                properties.getProperty("baseURL"),
                properties.getProperty("token"),
                properties.getProperty("secretKey")
        );

        Faker faker = new Faker();
        UserModel userModel = new UserModel();
        userModel.setName(faker.name().fullName());
        userModel.setEmail(faker.internet().emailAddress());
        userModel.setPassword("1234");
        userModel.setPhone_number("019"+Utils.generateRandomDigits(8));
        userModel.setNid("6582"+Utils.generateRandomDigits(6));
        userModel.setRole("Customer");

        Response response = userController.createUser(userModel);

        //assertion
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("message"), "User created");

        //save customer info
        Utils.setEnvVariable("customer2_phone", jsonPath.getString("user.phone_number"));
    }

    @Test(priority = 4, description = "Admin can create an agent")
    public void createAgent() throws ConfigurationException, InterruptedException {

        userController = new UserController(
                properties.getProperty("baseURL"),
                properties.getProperty("token"),
                properties.getProperty("secretKey")
        );

        Faker faker = new Faker();
        UserModel userModel = new UserModel();
        userModel.setName(faker.name().fullName());
        userModel.setEmail(faker.internet().emailAddress());
        userModel.setPassword("1234");
        userModel.setPhone_number("019"+Utils.generateRandomDigits(8));
        userModel.setNid("6582"+Utils.generateRandomDigits(6));
        userModel.setRole("Agent");

        Response response = userController.createUser(userModel);

        //assertion
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("message"), "User created");

        //save agent info
        Utils.setEnvVariable("agent_phone", jsonPath.getString("user.phone_number"));
    }

    @Test(priority = 5, description = "Admin can deposit to agent account")
    public void depositToAgent() {

        userController = new UserController(
                properties.getProperty("baseURL"),
                properties.getProperty("token"),
                properties.getProperty("secretKey")
        );

        TransModel transModel = new TransModel();
        transModel.setFrom_account("SYSTEM");
        transModel.setTo_account(properties.getProperty("agent_phone"));
        transModel.setAmount(2000);

        Response response = userController.deposit(transModel);

        //assertion
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("message"), "Deposit successful");
    }

    @Test(priority = 6, description = "Agent can deposit to customer account")
    public void depositToCustomer() {
        userController = new UserController(
                properties.getProperty("baseURL"),
                properties.getProperty("token"),
                properties.getProperty("secretKey")
        );

        TransModel transModel = new TransModel();
        transModel.setFrom_account(properties.getProperty("agent_phone"));
        transModel.setTo_account(properties.getProperty("customer1_phone"));
        transModel.setAmount(1500);

        Response response = userController.deposit(transModel);

        //assertion
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("message"), "Deposit successful");
    }

    @Test(priority =7, description = "Customer-1 can withdraw money from agent")
    public void withdrawFromAgent() {
        userController = new UserController(
                properties.getProperty("baseURL"),
                properties.getProperty("token"),
                properties.getProperty("secretKey")
        );

        TransModel transModel = new TransModel();
        transModel.setFrom_account(properties.getProperty("customer1_phone"));
        transModel.setTo_account(properties.getProperty("agent_phone"));
        transModel.setAmount(500);

        Response response = userController.withdraw(transModel);

        //assertion
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("message"), "Withdraw successful");
    }

    @Test(priority = 8, description = "Customer-1 can send money to other customer-2")
    public void sendMoney() {
        userController = new UserController(
                properties.getProperty("baseURL"),
                properties.getProperty("token"),
                properties.getProperty("secretKey")
        );

        TransModel transModel = new TransModel();
        transModel.setFrom_account(properties.getProperty("customer1_phone"));
        transModel.setTo_account(properties.getProperty("customer2_phone"));
        transModel.setAmount(500);

        Response response = userController.sendMoney(transModel);

        //assertion
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("message"), "Send money successful");
    }

    @Test(priority = 9, description = "Customer-2 can pay to merchant")
    public void payToMerchant() {
        userController = new UserController(
                properties.getProperty("baseURL"),
                properties.getProperty("token"),
                properties.getProperty("secretKey")
        );

        TransModel transModel = new TransModel();
        transModel.setFrom_account(properties.getProperty("customer2_phone"));
        transModel.setTo_account(properties.getProperty("merchant_phone"));
        transModel.setAmount(100);

        Response response = userController.payment(transModel);

        //assertion
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("message"), "Payment successful");
    }

    @Test(priority = 10, description = "Customer-2 can check remaining balance")
    public void checkBalance() {
        userController = new UserController(
                properties.getProperty("baseURL"),
                properties.getProperty("token"),
                properties.getProperty("secretKey")
        );

        Response response = userController.checkBalance(properties.getProperty("customer2_phone"));

        //assertion
        JsonPath jsonPath = response.jsonPath();
        Assert.assertTrue(jsonPath.getString("message").contains("User balance"));
    }
}
