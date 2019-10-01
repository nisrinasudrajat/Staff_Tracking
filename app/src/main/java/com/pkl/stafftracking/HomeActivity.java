package com.pkl.stafftracking;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    CardView cardView_presensi, cardView_kegiatan, cardView_laporan, cardView_lokasi;
    Button btnLogout;

    Dialog logout_dialog;
    Button logout_ok, logout_cancel;
    TextView logout_text, logout_text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cardView_presensi = findViewById(R.id.cardView_presensi);
        cardView_kegiatan = findViewById(R.id.cardView_kegiatan);
        cardView_laporan = findViewById(R.id.cardView_laporan);
        cardView_lokasi = findViewById(R.id.cardView_lokasi);
        btnLogout = findViewById(R.id.home_logout);

        logout_dialog = new Dialog(HomeActivity.this);

        cardView_presensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PresensiActivity.class);
                startActivity(intent);
            }
        });


        cardView_laporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ReportActivity.class);
                startActivity(intent);
            }
        });

        cardView_kegiatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), KegiatanActivity.class);
                startActivity(intent);
            }
        });

        cardView_lokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutDialog();
            }
        });

    }

    private void showLogoutDialog() {

        logout_dialog.setContentView(R.layout.activity_logout_popup);
        logout_ok = (Button)logout_dialog.findViewById(R.id.logout_ok);
        logout_cancel = (Button)logout_dialog.findViewById(R.id.logout_cancel);
        logout_text = (TextView)logout_dialog.findViewById(R.id.logout_text);
        logout_text2 = (TextView)logout_dialog.findViewById(R.id.logout_text2);

        logout_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        logout_dialog.show();

        logout_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout_dialog.dismiss();
            }
        });

        logout_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });


    }
}
