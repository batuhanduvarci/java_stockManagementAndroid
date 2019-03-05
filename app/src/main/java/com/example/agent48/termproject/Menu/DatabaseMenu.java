package com.example.agent48.termproject.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.agent48.termproject.Activity.BaseActivity;
import com.example.agent48.termproject.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Agent48 on 23.04.2018.
 */

public class DatabaseMenu extends BaseActivity {
    @Bind(R.id.buttonStock) Button stocksButton;
    @Bind(R.id.buttonDecreasing) Button decreasingButton;
    @Bind(R.id.buttonRequests) Button requestsButton;
    @Bind(R.id.buttonOrders) Button ordersButton;
    @Bind(R.id.buttonLogs) Button logsButton;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("ANA MENU");
        ButterKnife.bind(this);

        stocksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DatabaseMenu.this,StockMenu.class));
                overridePendingTransition(R.animator.right_in, R.animator.left_out);
            }
        });

        decreasingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DatabaseMenu.this,DecreasingMenu.class));
                overridePendingTransition(R.animator.right_in, R.animator.left_out);
            }
        });

        requestsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DatabaseMenu.this,RequestMenu.class));
                overridePendingTransition(R.animator.right_in, R.animator.left_out);

            }
        });

        ordersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DatabaseMenu.this,OrderMenu.class));
                overridePendingTransition(R.animator.right_in, R.animator.left_out);

            }
        });

        logsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DatabaseMenu.this,LogMenu.class));
                overridePendingTransition(R.animator.right_in, R.animator.left_out);

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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
        return R.layout.database_menu_layout;
    }
}
