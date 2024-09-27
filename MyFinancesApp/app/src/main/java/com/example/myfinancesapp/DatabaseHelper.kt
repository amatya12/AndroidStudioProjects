package com.example.myfinancesapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "finances.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createCDTable = "CREATE TABLE CDs (id INTEGER PRIMARY KEY AUTOINCREMENT, accountNumber TEXT, initialBalance REAL, currentBalance REAL, interestRate REAL)"
        val createLoanTable = "CREATE TABLE Loans (id INTEGER PRIMARY KEY AUTOINCREMENT, accountNumber TEXT, initialBalance REAL, currentBalance REAL, paymentAmount REAL, interestRate REAL)"
        val createCheckingTable = "CREATE TABLE Checking (id INTEGER PRIMARY KEY AUTOINCREMENT, accountNumber TEXT, currentBalance REAL)"

        db.execSQL(createCDTable)
        db.execSQL(createLoanTable)
        db.execSQL(createCheckingTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS CDs")
        db.execSQL("DROP TABLE IF EXISTS Loans")
        db.execSQL("DROP TABLE IF EXISTS Checking")
        onCreate(db)
    }

    fun insertCD(cd: CD) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("accountNumber", cd.accountNumber)
            put("initialBalance", cd.initialBalance)
            put("currentBalance", cd.currentBalance)
            put("interestRate", cd.interestRate)
        }
        db.insert("CDs", null, values)
        db.close()
    }

    fun insertLoan(loan: Loan) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("accountNumber", loan.accountNumber)
            put("initialBalance", loan.initialBalance)
            put("currentBalance", loan.currentBalance)
            put("paymentAmount", loan.paymentAmount)
            put("interestRate", loan.interestRate)
        }
        db.insert("Loans", null, values)
        db.close()
    }

    fun insertCheckingAccount(account: CheckingAccount) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("accountNumber", account.accountNumber)
            put("currentBalance", account.currentBalance)
        }
        db.insert("Checking", null, values)
        db.close()
    }
}
