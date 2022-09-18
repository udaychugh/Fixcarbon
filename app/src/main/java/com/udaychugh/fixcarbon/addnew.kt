package com.udaychugh.fixcarbon

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.*


class addnew : AppCompatActivity(), LocationListener {

    //initializing inputs
    lateinit var species : EditText
    lateinit var height : EditText
    lateinit var date : EditText
    lateinit var diameter : EditText

    //initializing buttons
    lateinit var clear : AppCompatButton
    lateinit var save : AppCompatButton

    //location and camera button
    lateinit var cam : AppCompatButton
    lateinit var glocation : AppCompatButton

    //for getting location variables
    private lateinit var locationManager : LocationManager
    private val locationPermissionCode = 2

    //for clicking images additionals things
    lateinit var resultlayout : LinearLayout
    lateinit var deletebtn : AppCompatButton
    lateinit var showingimage : ImageView

    val REQUEST_CODE = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addnew)


        //setting ids of inputs
        species=findViewById(R.id.speciestext)
        height=findViewById(R.id.heighttext)
        date=findViewById(R.id.datetext)
        diameter=findViewById(R.id.diametertext)

        //setting ids of buttons
        clear=findViewById(R.id.cleardata)
        save=findViewById(R.id.savedata)
        cam=findViewById(R.id.getphoto)
        glocation=findViewById(R.id.getlocation)

        //setting ids of additional things
        resultlayout=findViewById(R.id.showingresults)
        deletebtn=findViewById(R.id.deletephoto)
        showingimage=findViewById(R.id.showingresultimage)


        clear.setOnClickListener {
            species.text.clear()
            height.text.clear()
            date.text.clear()
            diameter.text.clear()
            glocation.text = "Get Current Location"
        }

        save.setOnClickListener {
            val especies = species.text.toString()
            val eheight = height.text.toString()
            val edate = date.text.toString()
            val ediameter = diameter.text.toString()
            val elocation = glocation.text.toString().lowercase()

            if (especies == "" || eheight == "" || edate == "" || ediameter == "" || elocation == "get current location")
            {
                Toast.makeText(applicationContext, "Please Enter Data in Valid form", Toast.LENGTH_SHORT).show()
            }
            else{
                val db = DBHelper(this, null)
                db.addname(especies, eheight, edate, ediameter, elocation)
                Toast.makeText(this, especies + " added to database", Toast.LENGTH_SHORT).show()
                species.text.clear()
                height.text.clear()
                date.text.clear()
                diameter.text.clear()
                glocation.text = "Get Current Location"
            }
        }

        glocation.setOnClickListener {
            getLocation()
        }

        cam.setOnClickListener {
            capturePhoto()
        }

        deletebtn.setOnClickListener {
            resultlayout.visibility = View.GONE
            cam.visibility = View.VISIBLE
        }

    }

    private fun capturePhoto() {

        val cameraintent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraintent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE && data != null){
            resultlayout.visibility = View.VISIBLE
            cam.visibility = View.GONE
            showingimage.setImageBitmap(data.extras?.get("data") as Bitmap)
        }
    }

    private fun getLocation() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), locationPermissionCode)
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
    }

    @SuppressLint("SetTextI18n")
    override fun onLocationChanged(location: Location) {
        val longi = location.longitude
        val lati = location.latitude
        glocation.text = "$lati , $longi"
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == locationPermissionCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}