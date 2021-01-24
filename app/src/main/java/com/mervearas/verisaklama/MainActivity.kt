package com.mervearas.verisaklama

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var sharedPreferences :SharedPreferences  //Heryerden ulaşılabilmesi için class'dan hemen sonra lateinit şeklinde sonradan initialize edilecek diye tanımlandı
    var ageFromPreferences : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //SharedPreferences Initialize
        //Veri saklama isleminde SharedPreferences kullanılır. Bunun kullanım şekli this.getSharedPreferences(name:"package adı yapıştırılır",MODE...)
        sharedPreferences = this.getSharedPreferences("com.mervearas.verisaklama", MODE_PRIVATE) //VERİ KAYDETME KODU

        ageFromPreferences = sharedPreferences.getInt("age",0) //Uygulama ilk açıldığında kullanıcının önceden kaydettiği kodu okuma yapmasını sağlar. VERİ OKUMA
        if (ageFromPreferences == 0){
            textView.text = "Your Age: "
        } else{
            textView.text = "Your Age: $ageFromPreferences"
        }
    }

    fun save (view: View){
        val myAge = editText.text.toString().toIntOrNull()
        if (myAge != null){
            textView.text = "Your Age: " + myAge
            sharedPreferences.edit().putInt("age",myAge).apply() //VERİYİ INT TÜRÜNDEN ALIR.
        }
    }

    fun delete (view:View){
        ageFromPreferences = sharedPreferences.getInt("age",0) //VERİ OKUMA KODU
        if(ageFromPreferences != 0){
            sharedPreferences.edit().remove("age") .apply() //VERİ SİLME KODU
            textView.text = "Your Age: "
        }
    }
}