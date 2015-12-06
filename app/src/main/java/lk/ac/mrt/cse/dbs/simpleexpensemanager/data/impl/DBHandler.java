package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 12/5/2015.
 */
public class DBHandler extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME1 = "Account";
    public static final String TABLE_NAME2 = "Transaction";

    // Database Information
    static final String DATABASE_NAME = "Expense Manager";

    // database version
    static final int DATABASE_VERSION = 1;


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE1 = "CREATE TABLE IF NOT EXISTS Account(" +
                "accountNo VARCHAR(20) PRIMARY KEY NOT NULL," +
                "bankName  VARCHAR(30)NOT NULL," +
                "accountHolderName  VARCHAR(50) NOT NULL," +
                "balance   FLOAT NOT NULL" +
                ")";

        String CREATE_TABLE2 = "CREATE TABLE IF NOT EXISTS Transaction (" +
                "date   DATE NOT NULL, " +
                "accountNo  VARCHAR(20) NOT NULL," +
                "expenseType    VARCHAR(7)," +
                "amount     FLOAT," +
                "FOREIGN KEY (accountNo) REFERENCES Account(accountNo)" +
                ");";
        db.execSQL(CREATE_TABLE1);
        db.execSQL(CREATE_TABLE2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);
    }
}
