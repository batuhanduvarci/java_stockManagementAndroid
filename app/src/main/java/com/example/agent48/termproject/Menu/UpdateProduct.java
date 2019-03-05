package com.example.agent48.termproject.Menu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agent48.termproject.Activity.BaseActivity;
import com.example.agent48.termproject.Object.Msg;
import com.example.agent48.termproject.Object.Product;
import com.example.agent48.termproject.R;
import com.example.agent48.termproject.RetrofitHttp.ApiClient;
import com.example.agent48.termproject.RetrofitHttp.ApiService;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Agent48 on 30.04.2018.
 */

public class UpdateProduct extends BaseActivity {
    @SerializedName("URUN_KODU")
    public String pCode;

    @Bind(R.id.buttonUpdate) Button updateButton;
    @Bind(R.id.productCodeTxtUpd) EditText productCode;
    @Bind(R.id.productNameTxtUpd) EditText productName;
    @Bind(R.id.productionDateTxtUpd) EditText productionDate;
    @Bind(R.id.consumeDateTxtUpd) EditText consumeDate;
    @Bind(R.id.quantityTxtUpd) EditText quantity;
    @Bind(R.id.priceTxtUpd) EditText price;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("ÜRÜN GÜNCELLE");
        ButterKnife.bind(this);

        Bundle extra = getIntent().getExtras();
        pCode = extra.getString("productCode");

        Log.i("onCreate", "+++++++++++++++++"+pCode);
        productCode.setFocusable(false);
        getPorductInfo();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProduct();
            }
        });
    }

    public void getPorductInfo(){
        pDialog = new ProgressDialog(UpdateProduct.this,R.style.Theme_AppCompat_DayNight_Dialog);
        pDialog.setIndeterminate(true);
        pDialog.setMessage("Ürün Bilgileri Alınıyor...");
        pDialog.setCancelable(false);

        pDialog.show();


        ApiService service = ApiClient.getClient().create(ApiService.class);

        Call<List<Product>> productInfoCall = service.getUpdateProductInfo(pCode);
        Log.i("getProductInfo","*******************"+pCode);

        productInfoCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                productCode.setText(response.body().get(0).getProductCode());
                productName.setText(response.body().get(0).getProductName());
                productionDate.setText(response.body().get(0).getProductionDate());
                consumeDate.setText(response.body().get(0).getConsumeDate());
                quantity.setText(response.body().get(0).getQuantity());
                price.setText(response.body().get(0).getPrice());

                pDialog.hide();
                Toast.makeText(UpdateProduct.this,"Ürün Bilgileri Başarıyla Alındı",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                pDialog.dismiss();
                Toast.makeText(UpdateProduct.this,"Bağlantı Hatası!",Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });

    }

    public void updateProduct(){
        pDialog = new ProgressDialog(UpdateProduct.this,R.style.Theme_AppCompat_DayNight_Dialog);
        pDialog.setIndeterminate(true);
        pDialog.setMessage("Ürün Güncelleniyor...");
        pDialog.setCancelable(false);

        pDialog.show();

        ApiService service = ApiClient.getClient().create(ApiService.class);

        Call<Msg> productUpdateCall = service.updateProduct(productCode.getText().toString(),productName.getText().toString(),productionDate.getText().toString(),consumeDate.getText().toString(),quantity.getText().toString(),price.getText().toString());

        productUpdateCall.enqueue(new Callback<Msg>() {
            @Override
            public void onResponse(Call<Msg> call, Response<Msg> response) {
                pDialog.hide();
                Toast.makeText(UpdateProduct.this,"Güncelleme Başarılı",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Msg> call, Throwable t) {
                pDialog.dismiss();
                Toast.makeText(UpdateProduct.this,"Güncelleme Başarısız!",Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(UpdateProduct.this,StockMenu.class));
        overridePendingTransition(R.animator.left_in,R.animator.right_out);
        finish();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.update_product_view;
    }
}
