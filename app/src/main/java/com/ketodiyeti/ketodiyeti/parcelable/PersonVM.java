package com.ketodiyeti.ketodiyeti.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

public class PersonVM implements Parcelable {

    public static final Creator<PersonVM> CREATOR = new Creator<PersonVM>() {
        @Override
        public PersonVM createFromParcel(Parcel in) {
            return new PersonVM(in);
        }

        @Override
        public PersonVM[] newArray(int size) {
            return new PersonVM[size];
        }
    };
    private String isim;
    private String mail;
    private String yas, kilo, boy;
    private String cinsiyet;
    private String kisiId;
    private String fotoUrl;

    public PersonVM(String isim, String mail, String yas, String kilo, String boy, String cinsiyet, String kisiId, String fotoUrl) {
        this.isim = isim;
        this.mail = mail;
        this.yas = yas;
        this.kilo = kilo;
        this.boy = boy;
        this.cinsiyet = cinsiyet;
        this.kisiId = kisiId;
        this.fotoUrl = fotoUrl;
    }

    protected PersonVM(Parcel in) {
        isim = in.readString();
        mail = in.readString();
        yas = in.readString();
        kilo = in.readString();
        boy = in.readString();
        cinsiyet = in.readString();
        kisiId = in.readString();
        fotoUrl = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(isim);
        dest.writeString(mail);
        dest.writeString(yas);
        dest.writeString(kilo);
        dest.writeString(boy);
        dest.writeString(cinsiyet);
        dest.writeString(kisiId);
        dest.writeString(fotoUrl);
    }
}
