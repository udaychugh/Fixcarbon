package com.udaychugh.fixcarbon

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.google.android.material.bottomsheet.BottomSheetDialog

class home_screen : AppCompatActivity() {

    lateinit var addnew : CardView
    lateinit var saved : CardView
    lateinit var helpbox : CardView

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        addnew=findViewById(R.id.secondcard)
        saved=findViewById(R.id.firstcard)
        helpbox=findViewById(R.id.thirdcard)

        addnew.setOnClickListener {
            val intent = Intent(this, com.udaychugh.fixcarbon.addnew::class.java)
            startActivity(intent)
        }

        saved.setOnClickListener {
            val intent = Intent(this, savedtrees::class.java)
            startActivity(intent)
        }



        helpbox.setOnClickListener {

            val dialog = BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.bottom_sheet_layout, null)

            dialog.setCancelable(true)
            dialog.setContentView(view)
            dialog.show()

        }



    }

}