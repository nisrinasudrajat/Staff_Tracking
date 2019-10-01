package com.pkl.stafftracking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.pkl.stafftracking.Model.Presensi;

public class LaporanActivity extends AppCompatActivity {

    ListView listView;

    FirebaseListAdapter adapter;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan);

        getSupportActionBar().setTitle("Laporan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth = FirebaseAuth.getInstance();
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final String userid = currentFirebaseUser.getUid();

        listView = findViewById(R.id.listView_laporan);

        Query query = FirebaseDatabase.getInstance().getReference().child("User").child(userid).child("presensi").child("Hadir");

        FirebaseListOptions<Presensi> options = new FirebaseListOptions.Builder<Presensi>()
                .setLayout(R.layout.list_item_laporan)
                .setQuery(query, Presensi.class)
                .build();

        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(View v, Object model, int position) {
                TextView hari = v.findViewById(R.id.laporan_hari_masuk);
                TextView tanggal = v.findViewById(R.id.laporan_tanggal_masuk);
                TextView jam = v.findViewById(R.id.laporan_waktu_masuk);

                Presensi presensi = (Presensi) model;
                hari.setText(presensi.getHari());
                tanggal.setText("   "+presensi.getTanggal());
                jam.setText("Anda masuk pukul "+presensi.getJam());

            }
        };

        listView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }



}
