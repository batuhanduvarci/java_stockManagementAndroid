package com.example.agent48.termproject.Menu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
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
 * Created by Agent48 on 28.04.2018.
 */

public class AddProduct extends BaseActivity {
    @Bind(R.id.buttonAdd) Button addProductButton;
    @Bind(R.id.productCodeTxt) EditText productCode;
    @Bind(R.id.productNameTxt) EditText productName;
    @Bind(R.id.productionDateTxt) EditText productionDate;
    @Bind(R.id.consumeDateTxt) EditText consumeDate;
    @Bind(R.id.quantityTxt) EditText quantity;
    @Bind(R.id.priceTxt) EditText price;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("ÜRÜN EKLE");
        ButterKnife.bind(this);

        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!productCode.getText().toString().isEmpty() && !productName.getText().toString().isEmpty() && !productionDate.getText().toString().isEmpty() && !consumeDate.getText().toString().isEmpty() &&  !quantity.getText().toString().isEmpty() && !price.getText().toString().isEmpty()){
                    addProduct();
                }
                else{
                    Toast.makeText(AddProduct.this,"Bütün alanlar dolu olmalı!",Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    public void addProduct(){
        pDialog = new ProgressDialog(AddProduct.this,R.style.Theme_AppCompat_DayNight_Dialog);
        pDialog.setIndeterminate(true);
        pDialog.setMessage("Ekleniyor...");
        pDialog.setCancelable(false);
        pDialog.show();

        String pCode = productCode.getText().toString();
        String pName = productName.getText().toString();
        String prodDate = productionDate.getText().toString();
        String consDate = consumeDate.getText().toString();
        String quan = quantity.getText().toString();
        String pri = price.getText().toString();

        ApiService service = ApiClient.getClient().create(ApiService.class);

        Call<Msg> productCall = service.addProduct(pCode,pName,prodDate,consDate,quan,pri);

        productCall.enqueue(new Callback<Msg>() {
            @Override
            public void onResponse(Call<Msg> call, Response<Msg> response) {
                pDialog.hide();

                if(response.body().getSuccess() == 1){

                    Toast.makeText(AddProduct.this,""+response.body().getMessage(),Toast.LENGTH_LONG).show();


                }else{
                    Toast.makeText(AddProduct.this,""+response.body().getMessage(),Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<Msg> call, Throwable t) {
                pDialog.hide();
                Toast.makeText(AddProduct.this,""+"Bağlantı Hatası!",Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AddProduct.this,StockMenu.class));
        overridePendingTransition(R.animator.left_in,R.animator.right_out);
        finish();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.add_product_view;
    }
}
