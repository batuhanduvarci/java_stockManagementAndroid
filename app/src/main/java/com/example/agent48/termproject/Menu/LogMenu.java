package com.example.agent48.termproject.Menu;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.agent48.termproject.Activity.BaseActivity;
import com.example.agent48.termproject.Object.Msg;
import com.example.agent48.termproject.Object.Product;
import com.example.agent48.termproject.R;
import com.example.agent48.termproject.RetrofitHttp.ApiClient;
import com.example.agent48.termproject.RetrofitHttp.ApiService;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Agent48 on 23.04.2018.
 */

public class LogMenu extends BaseActivity {
    @Bind(R.id.logListSwipe) SwipeMenuListView logListSwipe;
    @Bind(R.id.refreshButton) Button buttonRefresh;
    private ArrayList<String> ID = new ArrayList<String>();
    private ArrayList<String> URUN_KODU = new ArrayList<String>();
    private ArrayList<String> URUN_ADI = new ArrayList<String>();
    private ArrayList<String> URETIM_TARIHI = new ArrayList<String>();
    private ArrayList<String> SON_TUKETIM_TARIHI = new ArrayList<String>();
    private ArrayList<String> ADET = new ArrayList<String>();
    private ArrayList<String> BIRIM_FIYAT = new ArrayList<String>();
    private ArrayList<String> KAYIT = new ArrayList<String>();
    private ArrayList<String> TARIH = new ArrayList<String>();
    private ArrayAdapter<String> adapterProduct;
    public ProgressDialog pDialog;
    public String placeholder = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        adapterProduct = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1);
        getLogs();

        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDialog = new ProgressDialog(LogMenu.this,R.style.Theme_AppCompat_DayNight_Dialog);
                pDialog.setIndeterminate(true);
                pDialog.setMessage("Yenileniyor...");
                pDialog.setCancelable(false);
                pDialog.show();

                Intent intent = getIntent();
                finish();
                startActivity(intent);
                pDialog.dismiss();
            }
        });

        logListSwipe.setAdapter(adapterProduct);


        logListSwipe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                alertDialogBuilder.setTitle("Ürün Id : " + ID.get(position) + " " + KAYIT.get(position))
                        .setMessage("Ürün Kodu : " + URUN_KODU.get(position) + "\n" +
                                "Ürün Adı : " + URUN_ADI.get(position) + "\n" +
                                "Üretim Tarihi : "+URETIM_TARIHI.get(position) + "\n"+
                                "Son Tüketim Tarihi : "+SON_TUKETIM_TARIHI.get(position) + "\n"+
                                "Adet : " + ADET.get(position) + "\n"+
                                "Birim Fiyat : " + BIRIM_FIYAT.get(position) + "\n"+
                                "Kayıt : " + KAYIT.get(position) + "\n" +
                                "Tarih : " + TARIH.get(position) + "\n")
                        .setCancelable(true)
                        .setPositiveButton("GERI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).show();

            }
        });

    }

    public void getLogs(){
        pDialog = new ProgressDialog(LogMenu.this,R.style.Theme_AppCompat_DayNight_Dialog);
        pDialog.setIndeterminate(true);
        pDialog.setMessage("Veri alınıyor...");
        pDialog.setCancelable(false);

        pDialog.show();

        ApiService service = ApiClient.getClient().create(ApiService.class);

        Call<List<Product>> logCall = service.getLogs(placeholder);

        logCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> productList = response.body();

                for(Product product : productList){
                    ID.add(product.getId());
                    URUN_KODU.add(product.getProductCode());
                    URUN_ADI.add(product.getProductName());
                    URETIM_TARIHI.add(product.getProductionDate());
                    SON_TUKETIM_TARIHI.add(product.getConsumeDate());
                    ADET.add(product.getQuantity());
                    BIRIM_FIYAT.add(product.getPrice());
                    KAYIT.add(product.getRecord());
                    TARIH.add(product.getDate());

                }
                adapterProduct.addAll(URUN_KODU);

                pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(LogMenu.this,"Bağlantı Hatası!",Toast.LENGTH_LONG).show();
                pDialog.dismiss();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(LogMenu.this,DatabaseMenu.class));
        overridePendingTransition(R.animator.left_in,R.animator.right_out);
        finish();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.log_view_layout;
    }
}
