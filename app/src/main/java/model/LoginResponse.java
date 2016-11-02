package model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by BALAREZO on 26/05/2016.
 */
public class LoginResponse {

    @SerializedName("error")
    private String error;
    @SerializedName("result")
    private TokenResponseResult result;
    @SerializedName("message")
    private String message;

    public LoginResponse(String error, String message) {
        this.error = error;
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public TokenResponseResult getResult() {
        return result;
    }

    public void setResult(TokenResponseResult result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
