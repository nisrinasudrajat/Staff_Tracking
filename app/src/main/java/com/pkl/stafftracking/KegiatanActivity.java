package com.pkl.stafftracking;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.pkl.stafftracking.Model.UploadInfo;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.pkl.stafftracking.PresensiActivity.getDayName;

public class KegiatanActivity extends AppCompatActivity {

    private Button selectfile,submit;
    private EditText keterangan;
    private TextView date, notif;
    Uri fileurl;
    private FirebaseAuth auth;

    FirebaseStorage storage; // untuk mengupload file
    FirebaseDatabase database; // untuk menyimpan file
    ProgressDialog progressDialog; // untuk membuat progressbar
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kegiatan);

        getSupportActionBar().setTitle("Kegiatan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // inisialisasi fields EditText dan Button
        notif = (TextView) findViewById(R.id.file);
        keterangan = findViewById(R.id.keterangan);
        date = (TextView) findViewById(R.id.mydate);
        selectfile = (Button) findViewById(R.id.Button);
        submit = (Button) findViewById(R.id.Btn);

        //menampilkan tanggal
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        SimpleDateFormat month_date = new SimpleDateFormat("MMM");
        String mMonth = month_date.format(c.getTime());
        //int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        String tanggal = mDay + " " + mMonth + " " + mYear;
        date.setText(tanggal);

        //return
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();

        //button select
        selectfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(KegiatanActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
                {
                    SelectPdf();
                }else
                    ActivityCompat.requestPermissions(KegiatanActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fileurl!=null){ //user telah memilih file
                    submitfile(fileurl);
                }
                else
                    Toast.makeText(KegiatanActivity.this, "Pilih File", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void submitfile(Uri fileurl) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Mengirim File....");
        progressDialog.setProgress(0);
        progressDialog.show();

        final String fileName = System.currentTimeMillis()+"";
        auth = FirebaseAuth.getInstance();
        // mengambil referensi ke Firebase Database
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userid = currentFirebaseUser.getUid();
        StorageReference storageReference = storage.getReference(); // return path
        storageReference.child(userid).child("Files").child(fileName).putFile(fileurl)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        String url= taskSnapshot.getDownloadUrl().toString(); //return url
                        String ket = keterangan.getText().toString();
                        Calendar cal = Calendar.getInstance();
                        int second = cal.get(Calendar.SECOND);
                        int minute = cal.get(Calendar.MINUTE);
                        int hourofday = cal.get(Calendar.HOUR_OF_DAY); //24 hour format
                        String jam = hourofday + ":" + minute + ":" + second;
                        String tanggal = date.getText().toString();
                        int day = cal.get(Calendar.DAY_OF_WEEK);
                        String hari = getDayName(day);
                        @SuppressWarnings("VisibleForTests")
                        UploadInfo fileUploadInfo = new UploadInfo(hari,tanggal,jam,ket, url);
                        //store url in realtime db
                        auth = FirebaseAuth.getInstance();
                        // mengambil referensi ke Firebase Database
                        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                        String userid = currentFirebaseUser.getUid();
                        // Getting image upload ID.
                        DatabaseReference reference = database.getReference(); //
                        String fileUploadId = reference.push().getKey();
                        reference.child("User").child(userid).child("Kegiatan").child(fileUploadId).setValue(fileUploadInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(KegiatanActivity.this, "File berhasil di upload", Toast.LENGTH_SHORT).show();

                                    progressDialog.dismiss(); // Hiding the progressDialog after done uploading.
                                    keterangan.setText(""); //set ke awal lagi
                                    notif.setText("No File");
                                }
                                else
                                    Toast.makeText(KegiatanActivity.this, "File tidak berhasil di upload", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }) .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(KegiatanActivity.this, "File tidak berhasil di upload", Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    // track the progress
                int currentProgress = (int) (100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                progressDialog.setProgress(currentProgress);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 9 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            SelectPdf();
        }else
            Toast.makeText(KegiatanActivity.this, "Please provide permission..",Toast.LENGTH_SHORT).show();
    }

    private void SelectPdf() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);// untuk mengambil file
        startActivityForResult(intent,86);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==86 && resultCode==RESULT_OK && data!=null)
        {
            fileurl=data.getData(); // return url file yang dipilih
            notif.setText("File dipilih adalah :"+ data.getData().getLastPathSegment());
        }
        else {
            Toast.makeText(KegiatanActivity.this, "Silahkan pilih file", Toast.LENGTH_SHORT).show();
        }
    }
}
