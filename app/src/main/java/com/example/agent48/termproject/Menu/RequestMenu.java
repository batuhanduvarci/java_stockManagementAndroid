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
import com.example.agent48.termproject.Object.User;
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

public class RequestMenu extends BaseActivity {
    @Bind(R.id.requestListSwipe) SwipeMenuListView requestListSwipe;
    @Bind(R.id.refreshButton) Button buttonRefresh;
    private ArrayList<String> ID = new ArrayList<String>();
    private ArrayList<String> SIRKET_ADI = new ArrayList<String>();
    private ArrayList<String> AD = new ArrayList<String>();
    private ArrayList<String> SOYAD = new ArrayList<String>();
    private ArrayList<String> E_POSTA = new ArrayList<String>();
    private ArrayList<String> KULLANICI_ADI = new ArrayList<String>();
    private ArrayList<String> SIFRE = new ArrayList<String>();
    private ArrayList<String> DURUM = new ArrayList<String>();
    private ArrayAdapter<String> adapterUser;
    public ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        adapterUser = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1);
        getUsers();

        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem decline = new SwipeMenuItem(getApplicationContext());
                decline.setBackground(new ColorDrawable(Color.RED));
                decline.setTitle("Reddet");
                decline.setTitleColor(Color.BLACK);
                decline.setTitleSize(18);
                decline.setWidth(250);

                menu.addMenuItem(decline);

                SwipeMenuItem accept = new SwipeMenuItem(getApplicationContext());
                accept.setBackground(new ColorDrawable(Color.GREEN));
                accept.setTitle("Onayla");
                accept.setTitleColor(Color.BLACK);
                accept.setTitleSize(18);
                accept.setWidth(250);

                menu.addMenuItem(accept);

            }
        };
        requestListSwipe.setMenuCreator(creator);

        requestListSwipe.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);

        requestListSwipe.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index){
                    case 0:
                        declineRequest(ID.get(position));
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                        break;

                    case 1:
                        acceptRequest(SIRKET_ADI.get(position),AD.get(position),SOYAD.get(position),E_POSTA.get(position),KULLANICI_ADI.get(position),SIFRE.get(position));
                        Intent intent2 = getIntent();
                        finish();
                        startActivity(intent2);
                        break;
                }

                return false;
            }
        });

        requestListSwipe.setAdapter(adapterUser);

        requestListSwipe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                alertDialogBuilder.setTitle("Ürün Id : " + ID.get(position))
                        .setMessage("Şirket Adı : " + SIRKET_ADI.get(position) + "\n" +
                                "Ad : " + AD.get(position) + "\n" +
                                "Soyad : " + SOYAD.get(position) + "\n" +
                                "E Posta : " + E_POSTA.get(position) + "\n" +
                                "Kullanıcı Adı : " + KULLANICI_ADI.get(position) + "\n"+
                                "Şifre : " + SIFRE.get(position) + "\n" +
                                "Durum : " + DURUM.get(position))
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

    public void getUsers(){
        pDialog = new ProgressDialog(RequestMenu.this,R.style.Theme_AppCompat_DayNight_Dialog);
        pDialog.setIndeterminate(true);
        pDialog.setMessage("Veri alınıyor...");
        pDialog.setCancelable(false);

        pDialog.show();

        ApiService serviceUsers = ApiClient.getClient().create(ApiService.class);

        Call<List<User>> userCall = serviceUsers.getUsers("");

        userCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> userList = response.body();

                for(User user : userList){
                    ID.add(user.getId());
                    SIRKET_ADI.add(user.getCompanyName());
                    AD.add(user.getName());
                    SOYAD.add(user.getSurname());
                    E_POSTA.add(user.getEmail());
                    KULLANICI_ADI.add(user.getUsername());
                    SIFRE.add(user.getPassword());
                    DURUM.add(user.getState());

                }

                adapterUser.addAll(SIRKET_ADI);

                pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(RequestMenu.this,"Bağlantı Hatası!",Toast.LENGTH_LONG).show();
                pDialog.dismiss();
            }
        });
    }

    public void acceptRequest(String companyName, String name, String surname, String email, String username, String password){
        pDialog = new ProgressDialog(RequestMenu.this,R.style.Theme_AppCompat_DayNight_Dialog);
        pDialog.setIndeterminate(true);
        pDialog.setMessage("Onaylanıyor...");
        pDialog.setCancelable(false);

        pDialog.show();

        ApiService serviceUsers = ApiClient.getClient().create(ApiService.class);

        Call<Msg> acceptCall = serviceUsers.acceptRequest(companyName,name,surname,email,username,password);

        acceptCall.enqueue(new Callback<Msg>() {
            @Override
            public void onResponse(Call<Msg> call, Response<Msg> response) {
                pDialog.dismiss();

                Toast.makeText(RequestMenu.this,""+response.body().getMessage(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Msg> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(RequestMenu.this,"Bağlantı Hatası!",Toast.LENGTH_LONG).show();
                pDialog.dismiss();
            }
        });
    }

    public void declineRequest(String id){
        pDialog = new ProgressDialog(RequestMenu.this,R.style.Theme_AppCompat_DayNight_Dialog);
        pDialog.setIndeterminate(true);
        pDialog.setMessage("Reddediliyor...");
        pDialog.setCancelable(false);

        pDialog.show();

        ApiService serviceUsers = ApiClient.getClient().create(ApiService.class);

        Call<Msg> declineCall = serviceUsers.declineRequest(id);

        declineCall.enqueue(new Callback<Msg>() {
            @Override
            public void onResponse(Call<Msg> call, Response<Msg> response) {
                pDialog.dismiss();

                Toast.makeText(RequestMenu.this,""+response.body().getMessage(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Msg> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(RequestMenu.this,"Bağlantı Hatası!",Toast.LENGTH_LONG).show();
                pDialog.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(RequestMenu.this,DatabaseMenu.class));
        overridePendingTransition(R.animator.left_in,R.animator.right_out);
        finish();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.request_view_layout;
    }
}
