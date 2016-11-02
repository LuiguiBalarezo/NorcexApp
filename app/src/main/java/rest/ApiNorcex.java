package rest;

import model.LoginResponse;
import model.TokenRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by BALAREZO on 26/05/2016.
 */
public interface ApiNorcex {
//    @POST("authorization/signin")
//    Call<LoginModel> signin(@Query("grant_type") String grant_type,
//                            @Query("client_id") String client_id,
//                            @Query("client_secret") String client_secret,
//                            @Query("username") String username,
//                            @Query("password") String password
//    );

    @POST("authorization/signin")
    Call<LoginResponse> signin(@Body TokenRequest tokenRequest);

}
