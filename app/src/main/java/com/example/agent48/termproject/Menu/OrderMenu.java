package com.example.agent48.termproject.Menu;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
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

public class OrderMenu extends BaseActivity {
    @Bind(R.id.orderListSwipe) SwipeMenuListView orderListSwipe;
    @Bind(R.id.refreshButton) Button buttonRefresh;
    private ArrayList<String> ID = new ArrayList<String>();
    private ArrayList<String> URUN_KODU = new ArrayList<String>();
    private ArrayList<String> URUN_ADI = new ArrayList<String>();
    private ArrayList<String> ADET = new ArrayList<String>();
    private ArrayList<String> BIRIM_FIYAT = new ArrayList<String>();
    private ArrayAdapter<String> adapterProduct;
    public ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("SİPARİŞLER");
        ButterKnife.bind(this);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        adapterProduct = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1);
        getOrders();

        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDialog = new ProgressDialog(OrderMenu.this,R.style.Theme_AppCompat_DayNight_Dialog);
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

        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem decineOrder = new SwipeMenuItem(getApplicationContext());
                decineOrder.setBackground(new ColorDrawable(Color.RED));
                decineOrder.setTitle("Sipariş İptali");
                decineOrder.setTitleColor(Color.BLACK);
                decineOrder.setTitleSize(16);
                decineOrder.setWidth(300);

                menu.addMenuItem(decineOrder);


            }
        };

        orderListSwipe.setMenuCreator(creator);

        orderListSwipe.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);

        orderListSwipe.setAdapter(adapterProduct);

        orderListSwipe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                alertDialogBuilder.setTitle("Ürün Id : " + ID.get(position) + " => " + "Ürün Kodu : " + URUN_KODU.get(position))
                        .setMessage("Ürün Kodu : " + URUN_KODU.get(position) + "\n" +
                                "Ürün Adı : " + URUN_ADI.get(position) + "\n" +
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

        orderListSwipe.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {

                deleteOrder(ID.get(position));

                return false;
            }
        });
    }

    public void getOrders(){
        pDialog = new ProgressDialog(OrderMenu.this,R.style.Theme_AppCompat_DayNight_Dialog);
        pDialog.setIndeterminate(true);
        pDialog.setMessage("Veri alınıyor...");
        pDialog.setCancelable(false);

        pDialog.show();

        ApiService service = ApiClient.getClient().create(ApiService.class);

        Call<List<Product>> orderCall = service.getOrders("");

        orderCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> productList = response.body();

                for(Product product : productList){
                    ID.add(product.getId());
                    URUN_KODU.add(product.getProductCode());
                    URUN_ADI.add(product.getProductName());
                    ADET.add(product.getQuantity());
                    BIRIM_FIYAT.add(product.getPrice());

                }

                adapterProduct.addAll(URUN_KODU);

                pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(OrderMenu.this,"Bağlantı Hatası!",Toast.LENGTH_LONG).show();
                pDialog.dismiss();
            }
        });
    }

    public void deleteOrder(String id){
        pDialog = new ProgressDialog(OrderMenu.this,R.style.Theme_AppCompat_DayNight_Dialog);
        pDialog.setIndeterminate(true);
        pDialog.setMessage("İptal Ediliyor...");
        pDialog.setCancelable(false);

        pDialog.show();

        ApiService service = ApiClient.getClient().create(ApiService.class);

        Call<Msg> orderDeleteCall = service.deleteOrder(id);

        orderDeleteCall.enqueue(new Callback<Msg>() {
            @Override
            public void onResponse(Call<Msg> call, Response<Msg> response) {
                pDialog.dismiss();

                Toast.makeText(OrderMenu.this,response.body().getMessage(),Toast.LENGTH_LONG).show();
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Msg> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(OrderMenu.this,"Bağlantı Hatası!",Toast.LENGTH_LONG).show();
                pDialog.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(OrderMenu.this,DatabaseMenu.class));
        overridePendingTransition(R.animator.left_in,R.animator.right_out);
        finish();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.order_view_layout;
    }
}
