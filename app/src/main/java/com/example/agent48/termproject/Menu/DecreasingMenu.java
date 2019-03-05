package com.example.agent48.termproject.Menu;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

public class DecreasingMenu extends BaseActivity{
    @Bind(R.id.decreasingListSwipe) SwipeMenuListView stockListSwipe;
    @Bind(R.id.refreshTableButton) Button refreshTableButton;
    private ArrayList<String> ID = new ArrayList<String>();
    private ArrayList<String> URUN_KODU = new ArrayList<String>();
    private ArrayList<String> URUN_ADI = new ArrayList<String>();
    private ArrayList<String> URETIM_TARIHI = new ArrayList<String>();
    private ArrayList<String> SON_TUKETIM_TARIHI = new ArrayList<String>();
    private ArrayList<String> ADET = new ArrayList<String>();
    private ArrayList<String> BIRIM_FIYAT = new ArrayList<String>();
    private ArrayAdapter<String> adapterProduct;
    public ProgressDialog pDialog;
    EditText quant;
    public String orderQuantity;

    @SerializedName("ID")
    private String id;
    @SerializedName("URUN_KODU")
    private String pCode;
    @SerializedName("URUN_ADI")
    private String pName;
    @SerializedName("ADET")
    private String pQuantity;
    @SerializedName("BIRIM_FIYAT")
    private String pPrice;
    @SerializedName("DURUM")
    private String pState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("AZALAN ÜRÜNLER");
        ButterKnife.bind(this);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        final AlertDialog.Builder alertDialogBuilder2 = new AlertDialog.Builder(this);
        quant = new EditText(DecreasingMenu.this);
        adapterProduct = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1);
        getDecreasing();

        refreshTableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDialog = new ProgressDialog(DecreasingMenu.this,R.style.Theme_AppCompat_DayNight_Dialog);
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
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(Color.GREEN));
                deleteItem.setTitle("Sipariş Ver");
                deleteItem.setTitleColor(Color.BLACK);
                deleteItem.setTitleSize(18);
                deleteItem.setWidth(250);

                menu.addMenuItem(deleteItem);

            }
        };

        stockListSwipe.setMenuCreator(creator);

        stockListSwipe.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);

        stockListSwipe.setAdapter(adapterProduct);

        stockListSwipe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                alertDialogBuilder.setTitle("Ürün Id : " + ID.get(position))
                        .setMessage("Ürün Kodu : " + URUN_KODU.get(position) + "\n" +
                                "Ürün Adı : " + URUN_ADI.get(position) + "\n" +
                                "Üretim Tarihi : "+URETIM_TARIHI.get(position) + "\n"+
                                "Son Tüketim Tarihi : "+SON_TUKETIM_TARIHI.get(position) + "\n"+
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

        stockListSwipe.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                quant.setLayoutParams(lp);
                quant.setInputType(InputType.TYPE_CLASS_NUMBER);
                pCode = URUN_KODU.get(position);
                pName = URUN_ADI.get(position);
                pPrice = BIRIM_FIYAT.get(position);
                pState = "Siparis Verildi";

                alertDialogBuilder2.setView(quant);
                alertDialogBuilder2.setTitle("Sipariş Ver => "+ "Ürün kodu : "+URUN_KODU.get(position))
                        .setCancelable(true)
                        .setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                orderQuantity = quant.getText().toString();
                                pQuantity = orderQuantity;
                                orderProduct(pCode,pName,pQuantity,pPrice,pState);
                            }
                        }).show();


                return false;
            }
        });
    }

    public void getDecreasing(){
        pDialog = new ProgressDialog(DecreasingMenu.this,R.style.Theme_AppCompat_DayNight_Dialog);
        pDialog.setIndeterminate(true);
        pDialog.setMessage("Veri alınıyor...");
        pDialog.setCancelable(false);

        pDialog.show();

        ApiService service = ApiClient.getClient().create(ApiService.class);

        Call<List<Product>> decreasingCall = service.getDecreasing("");

        decreasingCall.enqueue(new Callback<List<Product>>() {
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

                }

                adapterProduct.addAll(URUN_KODU);

                pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(DecreasingMenu.this,"Bağlantı Hatası!",Toast.LENGTH_LONG).show();
                pDialog.dismiss();
            }
        });
    }

    public void orderProduct(String pCode, String pName, String pQuantity, String pPrice, String pState){
        pDialog = new ProgressDialog(DecreasingMenu.this,R.style.Theme_AppCompat_DayNight_Dialog);
        pDialog.setIndeterminate(true);
        pDialog.setMessage("Sipariş Veriliyor...");
        pDialog.setCancelable(false);

        pDialog.show();

        ApiService service = ApiClient.getClient().create(ApiService.class);

        Call<Msg> orderCall = service.orderProduct(pCode,pName,pQuantity,pPrice,pState);

        orderCall.enqueue(new Callback<Msg>() {
            @Override
            public void onResponse(Call<Msg> call, Response<Msg> response) {
                pDialog.hide();

                Toast.makeText(DecreasingMenu.this,response.body().getMessage(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Msg> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(DecreasingMenu.this,"Bağlantı Hatası!",Toast.LENGTH_LONG).show();
                pDialog.dismiss();
            }
        });
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DecreasingMenu.this,DatabaseMenu.class));
        overridePendingTransition(R.animator.left_in,R.animator.right_out);
        finish();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.decreasing_view_layout;
    }
}
