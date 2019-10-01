package com.pkl.stafftracking.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.pkl.stafftracking.Model.Presensi;
import com.pkl.stafftracking.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MasukFragment extends Fragment {

    ListView listView;

    FirebaseListAdapter adapter;
    FirebaseAuth auth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_masuk, container, false);
        listView = v.findViewById(R.id.listView_laporan);


        auth = FirebaseAuth.getInstance();
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final String userid = currentFirebaseUser.getUid();



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
                tanggal.setText("   " + presensi.getTanggal());
                jam.setText("Anda masuk pukul " + presensi.getJam());

            }
        };

        listView.setAdapter(adapter);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
