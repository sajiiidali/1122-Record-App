package com.a1122recordapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class Database(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    lateinit var db: SQLiteDatabase
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table $TABLE_NAME(Type_of_EC TEXT, EC_Number TEXT, C_Date TEXT, Month_No TEXT, Date_No TEXT, Year_No TEXT, Row_string TEXT )")
    }

    override fun onUpgrade(db: SQLiteDatabase, i: Int, i1: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
    fun deleteData(ecNumber: String?, ecType: String?): Boolean {
        db = this.writableDatabase
        val result = db.delete(TABLE_NAME, " EC_Number=? AND Type_of_EC=?", arrayOf(ecNumber,ecType)).toLong()
        return result != -1L
    }
    fun checkDataByMonthAndYear(
        date: Int,
        month: Int,
        year: Int): Cursor? {

        db = this.readableDatabase

        return db.rawQuery(
            "SELECT * FROM EC_Table WHERE Date_No = ? AND Month_No = ? AND Year_No = ?",
            arrayOf(date.toString(), month.toString(), year.toString())
        )
    }
    fun checkByYear(year: Int): Cursor? {

        db = this.readableDatabase

        return db.rawQuery(
            "SELECT * FROM EC_Table WHERE Year_No = ?", arrayOf(year.toString())
        )
    }

    fun insertData(
        ECT: String?,
        ECN: String?,
        CD: String?,
        month: String?,
        date: String?,
        year: String?,
        rowString: String?
    ): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_1, ECT)
        contentValues.put(COLUMN_2, ECN)
        contentValues.put(COLUMN_3, CD)
        contentValues.put(COLUMN_4, month)
        contentValues.put(COLUMN_5, date)
        contentValues.put(COLUMN_6, year)
        contentValues.put(COLUMN_7, rowString)
        val result = db.insert(TABLE_NAME, null, contentValues)
        return result != -1L
    }

    fun deleteAll() {
        db = this.writableDatabase
        db.delete(TABLE_NAME, null, null)
    }

    fun checkData(m: Int, year: Int): Cursor {
        db = this.readableDatabase
        return db.rawQuery(
            "SELECT * FROM EC_Table WHERE Month_No = ? AND Year_No = ?",
            arrayOf(m.toString(), year.toString())
        )
    }
    fun updateRowData(
        EC_Number: String,
        EcType: String?,
        checkEcNumber:String?,
        checkEcType :String?
    ): Boolean {

        db = this.writableDatabase
        val contentValues = ContentValues(2)
        contentValues.put(COLUMN_1, EcType)
        contentValues.put(COLUMN_2, EC_Number)
        val result = db.update(TABLE_NAME, contentValues, " EC_Number=? AND Type_of_EC=?", arrayOf(checkEcNumber,checkEcType)).toLong()
        return result != -1L
    }

    fun checkDayOffLeave(ecNumber: String?, ecType: String?): Cursor {
        db = this.readableDatabase
        return db.rawQuery(
            "SELECT * FROM EC_Table WHERE C_Date = ? AND Type_of_EC = ?", arrayOf(ecNumber, ecType))
    }

    fun deleteDayOffLeave(dateofDayOff : String?, dayOff: String?): Boolean {
        db = this.writableDatabase
        val result = db.delete(TABLE_NAME, " C_Date=? AND Type_of_EC=?", arrayOf(dateofDayOff,dayOff)).toLong()
        return result != -1L
    }

    fun checkByEcNumber(ecNumber: String?, ecType: String?): Cursor {
        db = this.readableDatabase
        return db.rawQuery(
            "SELECT * FROM EC_Table WHERE EC_Number = ? AND Type_of_EC = ?", arrayOf(ecNumber, ecType))    }

    fun updateDayOff(
        updateDate: String,
        updateDayOff: String,
        date: String?,
        dayOff: String?
    ): Boolean {
        db = this.writableDatabase
        val contentValues = ContentValues(2)
        contentValues.put(COLUMN_1, updateDayOff)
        contentValues.put(COLUMN_3, updateDate)
        val result = db.update(TABLE_NAME, contentValues, " C_Date=? AND Type_of_EC=?", arrayOf(date,dayOff)).toLong()
        return result != -1L
    }

    companion object {

        private const val DATABASE_NAME = "EC_DataBase"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "EC_Table"
        const val COLUMN_1 = "Type_of_EC"
        const val COLUMN_2 = "EC_Number"
        const val COLUMN_3 = "C_Date"
        const val COLUMN_4 = "Month_No"
        const val COLUMN_5 = "Date_No"
        const val COLUMN_6 = "Year_No"
        const val COLUMN_7 = "Row_string"
    }
}
