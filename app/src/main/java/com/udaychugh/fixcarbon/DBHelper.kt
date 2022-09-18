package com.udaychugh.fixcarbon

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION){
    override fun onCreate(db: SQLiteDatabase?) {
        val query = ("CREATE TABLE " + TABLE_NAME + " (" + ID_COL + " INTEGER PRIMARY KEY, " + SPECIES_COL + " TEXT," + HEIGHT_COL + " TEXT," + DATE_COL + " TEXT," + DIAMETER_COL + " TEXT," + LOCATION_COL + " TEXT" + ")")
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME)
        onCreate(db)
    }

    fun addname(species: String, height: String, pdate: String, diam: String, loc : String)
    {
        val values = ContentValues()

        //putting values in contentvalues
        values.put(SPECIES_COL, species)
        values.put(HEIGHT_COL, height)
        values.put(DATE_COL, pdate)
        values.put(DIAMETER_COL, diam)
        values.put(LOCATION_COL, loc)

        //insert value in database
        val db = this.writableDatabase

        db.insert(TABLE_NAME, null, values)

        //closing database
        db.close()
    }

    fun getName(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)
    }

    companion object{
        private val DATABASE_NAME = "Plants_Database"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "plant_db"
        val ID_COL = "id"
        val SPECIES_COL = "Species"
        val HEIGHT_COL = "Height"
        val DATE_COL = "Date"
        val DIAMETER_COL = "Diameter"
        val LOCATION_COL = "Location"
    }
}
