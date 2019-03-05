package com.example.agent48.termproject.Menu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agent48.termproject.Activity.BaseActivity;
import com.example.agent48.termproject.Object.Msg;
import com.example.agent48.termproject.R;
import com.example.agent48.termproject.RetrofitHttp.ApiClient;
import com.example.agent48.termproject.RetrofitHttp.ApiService;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Agent48 on 20.04.2018.
 */

public class MainMenu extends BaseActivity {
    //static final String TAG = "MainMenu";
    @Bind(R.id.buttonLogin) Button loginButton;
    @Bind(R.id.buttonExit) Button exitButton;
    @Bind(R.id.usernameTxt) EditText userNameText;
    @Bind(R.id.passwordTxt) EditText passWordText;
    public ProgressDialog pDialog;
    //public Integer userController=0;
    boolean doubleBackToExitPressedOnce = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate() == true){
                    loginByServer();

                }else if(validate() == false){
                    onLoginFailed();
                }
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    public void loginByServer(){

        pDialog = new ProgressDialog(MainMenu.this,R.style.Theme_AppCompat_DayNight_Dialog);
        pDialog.setIndeterminate(true);
        pDialog.setMessage("Bağlanıyor...");
        pDialog.setCancelable(false);

        showDialog();

        final String username = userNameText.getText().toString();
        String password = passWordText.getText().toString();


        ApiService service = ApiClient.getClient().create(ApiService.class);

        Call<Msg> userCall = service.userLogin(username,password);

        userCall.enqueue(new Callback<Msg>() {
            @Override
            public void onResponse(Call<Msg> call, Response<Msg> response) {
                hideDialog();

                Log.i("onResponse"," "+ response.body().getMessage());

                if(response.body().getSuccess() == 1){


                    startActivity(new Intent(MainMenu.this,DatabaseMenu.class));
                    overridePendingTransition(R.animator.right_in, R.animator.left_out);



                    Toast.makeText(MainMenu.this,""+response.body().getMessage(),Toast.LENGTH_LONG).show();

                    finish();
                }else{

                    Toast.makeText(MainMenu.this,""+response.body().getMessage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Msg> call, Throwable t) {
                hideDialog();
                Toast.makeText(getBaseContext(),"Bağlantı hatası",Toast.LENGTH_LONG).show();
                Log.i("onFailure", t.toString());
            }
        });


    }

    public void showDialog(){
        if(!pDialog.isShowing()){
            pDialog.show();
        }
    }

    public void hideDialog(){
        if(pDialog.isShowing()){
            pDialog.dismiss();
        }
    }



    public void onLoginFailed(){
        Toast.makeText(MainMenu.this,"Giriş yapılamadı!",Toast.LENGTH_LONG).show();
        loginButton.setEnabled(true);
    }


    public void requestFocus(View view){

        if(view.requestFocus()){
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public boolean validate(){

        boolean valid = true;

        String username = userNameText.getText().toString();
        String password = passWordText.getText().toString();

        if(username.isEmpty()){
            userNameText.setError("Geçersiz kullanıcı adı!");
            requestFocus(userNameText);
            valid = false;
        }else{
            userNameText.setError(null);
        }

        if(password.isEmpty()){
            passWordText.setError("Geçersiz şifre!");
            requestFocus(passWordText);
            valid = false;
        }else{
            passWordText.setError(null);
        }

        return valid;

    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Çıkmak için tekrar tıklayın", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 1500);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.main_menu_layout;
    }
}
