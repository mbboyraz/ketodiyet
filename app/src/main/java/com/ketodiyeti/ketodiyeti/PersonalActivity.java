package com.ketodiyeti.ketodiyeti;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class PersonalActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edt_kilo, edt_yas, edt_boy;
    RadioButton radio_erkek, radio_kadin;
    Person person;
    Bundle extras;
    String cinsiyet;
    Button btn_kaydet;
    TextView txt_kilo, txt_yas, txt_boy, txt_uyari;
    String mUsername, yasPref, boy, kilo, yas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        init();
        extras = getIntent().getExtras();
        btn_kaydet.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        setupUsername();
        super.onStart();


    }

    private void init() {
        edt_kilo = findViewById(R.id.edt_kilo);
        edt_boy = findViewById(R.id.edt_boy);
        edt_yas = findViewById(R.id.edt_yas);
        radio_erkek = findViewById(R.id.radio_erkek);
        radio_kadin = findViewById(R.id.radio_kadin);
        btn_kaydet = findViewById(R.id.kaydet_button);
        txt_boy = findViewById(R.id.txtview_boy);
        txt_kilo = findViewById(R.id.txtview_kilo);
        txt_yas = findViewById(R.id.txtview_yas);
        txt_uyari = findViewById(R.id.txt_uyari);
    }

    private void initView() {

        yas = edt_yas.getText().toString();
        boy = edt_boy.getText().toString();
        kilo = edt_kilo.getText().toString();

        if (radio_erkek.isChecked()) {
            cinsiyet = "Erkek";
        } else if (radio_kadin.isChecked()) {
            cinsiyet = "Kadın";
        }

        if (yas.matches("") || boy.matches("") || kilo.matches("")) {
            txt_uyari.setVisibility(View.VISIBLE);
        } else {

            person = new Person(extras.getString("name"), extras.getString("email"), edt_yas.getText().toString(), edt_kilo.getText().toString(), edt_boy.getText().toString(),
                    cinsiyet, extras.getString("usersid"), extras.getString("photourl"));
            setupUsername();
        }
    }

    private void setupUsername() {
        SharedPreferences prefs = getApplication().getSharedPreferences("PersonPrefs", 0);
        mUsername = prefs.getString("username", null);
        yasPref = prefs.getString("yas", null);
        // kullaniciAd = prefs.getString("KullaniciAdi", null);


        if (yasPref == null) {
            //  Random r = new Random();
            // Assign a random user name if we don't have one saved.
            // mUsername = "JavaUser" + r.nextInt(100000);

            mUsername = extras.getString("name");
            prefs.edit().putString("kisiAdi", mUsername).apply();
            prefs.edit().putString("kisiEmail", extras.getString("email")).apply();
            prefs.edit().putString("yas", edt_yas.getText().toString()).apply();
            prefs.edit().putString("kilo", edt_kilo.getText().toString()).apply();
            prefs.edit().putString("boy", edt_boy.getText().toString()).apply();

            //  prefs.edit().putString("KullaniciAdi", kullaniciAd).commit();

        } else {
            Intent i = new Intent(this, DashboardActivity.class);
            i.putExtra("username", mUsername);
            startActivity(i);
        }


        //  i.putExtra("KullaniciAdi", kullaniciAd);


    }

    @Override
    public void onClick(View v) {
        initView();

    }
}
