package com.example.agent48.termproject.Menu;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.agent48.termproject.Activity.BaseActivity;
import com.example.agent48.termproject.Object.Msg;
import com.example.agent48.termproject.Object.Product;
import com.example.agent48.termproject.R;
import com.example.agent48.termproject.RetrofitHttp.ApiClient;
import com.example.agent48.termproject.RetrofitHttp.ApiService;
import com.google.gson.annotations.SerializedName;


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

public class StockMenu extends BaseActivity {
    @Bind(R.id.stockListSwipe) SwipeMenuListView stockListSwipe;
    @Bind(R.id.addProductButton) Button addProductButton;
    @Bind(R.id.refreshTableButton) Button refreshTableButton;
    public static String placeholder = "";
    private ArrayList<String> ID = new ArrayList<String>();
    private ArrayList<String> URUN_KODU = new ArrayList<String>();
    private ArrayList<String> URUN_ADI = new ArrayList<String>();
    private ArrayList<String> URETIM_TARIHI = new ArrayList<String>();
    private ArrayList<String> SON_TUKETIM_TARIHI = new ArrayList<String>();
    private ArrayList<String> ADET = new ArrayList<String>();
    private ArrayList<String> BIRIM_FIYAT = new ArrayList<String>();
    private ArrayAdapter<String> adapterProduct;
    public String pCode;
    public ProgressDialog pDialog;
    public ProgressDialog pDialog2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("STOKLAR");
        ButterKnife.bind(this);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        adapterProduct = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1);
        getStock();

        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StockMenu.this,AddProduct.class));
                overridePendingTransition(R.animator.right_in, R.animator.left_out);
            }
        });

        refreshTableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDialog2 = new ProgressDialog(StockMenu.this,R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                pDialog2.setIndeterminate(true);
                pDialog2.setMessage("Yenileniyor...");
                pDialog2.setCancelable(false);
                pDialog2.show();

                Intent intent = getIntent();
                finish();
                startActivity(intent);
                pDialog2.dismiss();
            }
        });


        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(Color.RED));
                deleteItem.setTitle("Sil");
                deleteItem.setTitleColor(Color.BLACK);
                deleteItem.setTitleSize(18);
                deleteItem.setWidth(250);

                menu.addMenuItem(deleteItem);

                SwipeMenuItem updateItem = new SwipeMenuItem(getApplicationContext());
                updateItem.setBackground(new ColorDrawable(Color.YELLOW));
                updateItem.setTitle("Güncelle");
                updateItem.setTitleColor(Color.BLACK);
                updateItem.setTitleSize(18);
                updateItem.setWidth(250);

                menu.addMenuItem(updateItem);

            }
        };
        stockListSwipe.setMenuCreator(creator);

        stockListSwipe.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);

        stockListSwipe.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index){
                    case 0:
                        deleteProduct(ID.get(position));
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);

                        break;

                    case 1:
                        pCode = URUN_KODU.get(position);
                        startActivity(new Intent(StockMenu.this,UpdateProduct.class).putExtra("productCode",pCode));
                        break;
                }

                return false;
            }
        });

        stockListSwipe.setAdapter(adapterProduct);

        stockListSwipe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                alertDialogBuilder.setTitle("Ürün Id : " + ID.get(position))
                        .setMessage("Ürün Kodu : " + URUN_KODU.get(position) + "\n" +
                        "Ürün Adı : " + URUN_ADI.get(position) + "\n" +
                        "Üretim Tarihi : " + URETIM_TARIHI.get(position) + "\n" +
                        "Son Tüketim Tarihi : " + SON_TUKETIM_TARIHI.get(position) + "\n" +
                        "Adet : " + ADET.get(position) + "\n"+
                        "Birim Fiyat : " + BIRIM_FIYAT.get(position))
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


    public void getStock(){
        pDialog = new ProgressDialog(StockMenu.this,R.style.Theme_AppCompat_DayNight_Dialog);
        pDialog.setIndeterminate(true);
        pDialog.setMessage("Veri alınıyor...");
        pDialog.setCancelable(false);

        pDialog.show();

        ApiService serviceProduct = ApiClient.getClient().create(ApiService.class);

        Call<List<Product>> productCall = serviceProduct.getStock(placeholder);

        productCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                List<Product> productList = response.body();

                Log.i("onResponse","Working");

                for(Product product : productList){
                    ID.add(product.getId());
                    URUN_KODU.add(product.getProductCode());
                    URUN_ADI.add(product.getProductName());
                    URETIM_TARIHI.add(product.getProductionDate());
                    SON_TUKETIM_TARIHI.add(product.getConsumeDate());
                    ADET.add(product.getQuantity());
                    BIRIM_FIYAT.add(product.getPrice());

                }
                Log.i("onResponse","For Completed");
                adapterProduct.addAll(URUN_KODU);

                pDialog.dismiss();
                Log.i("onResponse",URUN_KODU.toString());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(StockMenu.this,"Bağlantı Hatası!",Toast.LENGTH_LONG).show();
                pDialog.dismiss();
            }
        });
    }

    public void deleteProduct(String productCode){
        pDialog = new ProgressDialog(StockMenu.this,R.style.Theme_AppCompat_DayNight_Dialog);
        pDialog.setIndeterminate(true);
        pDialog.setMessage("Ürün Siliniyor...");
        pDialog.setCancelable(false);

        pDialog.show();


        ApiService service = ApiClient.getClient().create(ApiService.class);

        Call<Msg> productDeleteCall = service.deleteProduct(productCode);

        productDeleteCall.enqueue(new Callback<Msg>() {
            @Override
            public void onResponse(Call<Msg> call, Response<Msg> response) {
                pDialog.hide();

                if(response.body().getSuccess() == 1){

                    Toast.makeText(StockMenu.this,""+response.body().getMessage(),Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(StockMenu.this,""+response.body().getMessage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Msg> call, Throwable t) {
                pDialog.hide();
                Toast.makeText(StockMenu.this,"Bağlantı Hatası!",Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(StockMenu.this,DatabaseMenu.class));
        overridePendingTransition(R.animator.left_in,R.animator.right_out);
        finish();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.stock_view_layout;
    }
}
