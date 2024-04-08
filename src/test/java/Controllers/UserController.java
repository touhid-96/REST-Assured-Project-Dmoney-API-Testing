package Controllers;

import Config.TransModel;
import Config.UserModel;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserController {
    private String baseURL;
    private String token;
    private String secretKey;

    public UserController(String baseURL) {
        this.baseURL = baseURL;
    }

    public UserController(String baseURL, String token, String secretKey) {
        this.baseURL = baseURL;
        this.token = token;
        this.secretKey = secretKey;
    }

    public Response login(UserModel userModel) {
        RestAssured.baseURI = baseURL;
        Response response = given()
                .contentType("application/json")
                .body(userModel)
                .when()
                .post("/user/login");

        return response;
    }

    public Response createUser(UserModel userModel) {
        RestAssured.baseURI = baseURL;
        Response response = given()
                .contentType("application/json")
                .header("Authorization", token)
                .header("X-AUTH-SECRET-KEY", secretKey)
                .body(userModel)
                .when()
                .post("/user/create");

        return response;
    }

    public Response deposit(TransModel transModel) {
        RestAssured.baseURI = baseURL;
        Response response = given()
                .contentType("application/json")
                .header("Authorization", token)
                .header("X-AUTH-SECRET-KEY", secretKey)
                .body(transModel)
                .when()
                .post("/transaction/deposit");

        return response;
    }

    public Response withdraw(TransModel transModel) {
        RestAssured.baseURI = baseURL;
        Response response = given()
                .contentType("application/json")
                .header("Authorization", token)
                .header("X-AUTH-SECRET-KEY", secretKey)
                .body(transModel)
                .when()
                .post("/transaction/withdraw");

        return response;
    }

    public Response sendMoney(TransModel transModel) {
        RestAssured.baseURI = baseURL;
        Response response = given()
                .contentType("application/json")
                .header("Authorization", token)
                .header("X-AUTH-SECRET-KEY", secretKey)
                .body(transModel)
                .when()
                .post("/transaction/sendmoney");

        return response;
    }

    public Response payment(TransModel transModel) {
        RestAssured.baseURI = baseURL;
        Response response = given()
                .contentType("application/json")
                .header("Authorization", token)
                .header("X-AUTH-SECRET-KEY", secretKey)
                .body(transModel)
                .when()
                .post("/transaction/payment");

        return response;
    }

    public Response checkBalance(String userPhone) {
        RestAssured.baseURI = baseURL;
        Response response = given()
                .contentType("application/json")
                .header("Authorization", token)
                .header("X-AUTH-SECRET-KEY", secretKey)
                .when()
                .get("/transaction/balance/"+userPhone);

        return response;
    }
}
