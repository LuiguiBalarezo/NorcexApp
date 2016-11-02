package com.toquescript.norcexapp;

import android.accounts.AccountAuthenticatorActivity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import model.LoginResponse;
import model.TokenRequest;
import model.TokenResponseResult;
import rest.ApiClient;
import rest.ApiNorcex;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AccountAuthenticatorActivity {

    private final String TAG = this.getClass().getSimpleName();

    /**
     * ACCOUNT MANAGER
     */

    /**
     * INJECION DE VISTAS BUTTERNIFE
     */
    @BindView(R.id.edt_user)
    EditText edt_user;
    @BindView(R.id.edt_password)
    EditText edt_password;
    @BindView(R.id.btn_login)
    Button btn_login;
    private Unbinder unbinder;

    /**
     * CLASES PARA LA EXTRACCION DE DATOS CON RETROFIT
     */
    ApiNorcex apiInterfaceNorcex = null;
    Call<LoginResponse> login = null;
    TokenRequest tokenRequest = null;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        unbinder = ButterKnife.bind(this);
        apiInterfaceNorcex = ApiClient.getClient().create(ApiNorcex.class);
        tokenRequest = new TokenRequest();

        /**
         * ACCOUNTMANAGER
         * */


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @OnClick(R.id.btn_login)
    public void onClick() {

        tokenRequest.setGrant_type(getString(R.string.grant_type_password));
        tokenRequest.setClient_id(getString(R.string.client_id));
        tokenRequest.setClient_secret(getString(R.string.client_secret));
        tokenRequest.setUsername(edt_user.getText().toString());
        tokenRequest.setPassword(edt_password.getText().toString());

        login = apiInterfaceNorcex.signin(tokenRequest);
        login.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {

                    LoginResponse mLoginObject = response.body();
                    String returnedResponse = mLoginObject.getMessage();
                    TokenResponseResult result = mLoginObject.getResult();
                    Toast.makeText(Login.this, "Returned " + returnedResponse + " " + result.getAccessToken(), Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(Login.this, "error " + response.code() + " ", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(Login.this, "Throwable" + t.getMessage().toString() + " ", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Login Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.toquescript.norcexapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Login Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.toquescript.norcexapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
