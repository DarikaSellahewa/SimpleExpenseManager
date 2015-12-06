package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;



import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.Constants;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Transaction;

/**
 * Created by User on 12/5/2015.
 */
public class PersistentTransactionDAO implements TransactionDAO {

    // Database Information
    static final String DATABASE_NAME = "130554E";

    // database version
    static final int DATABASE_VERSION = 1;


    SQLiteDatabase db;
    java.io.File filename = Constants.CONTEXT.getFilesDir();
    public PersistentTransactionDAO()
    {
        db = SQLiteDatabase.openOrCreateDatabase(filename.getAbsolutePath() + "/130554E.sqlite", null);
        // db.execSQL("CREATE TABLE IF NOT EXISTS Trans(accountNo VARCHAR(50),expenseType VARCHAR(50));");
        db.execSQL("CREATE TABLE IF NOT EXISTS Transactions(accountNo VARCHAR(50),expenseType VARCHAR(50),amount NUMERIC(10,2), date_value Date);");
    }





    @Override
    public void logTransaction(Date date, String accountNo, ExpenseType expenseType, double amount) {
        db.execSQL("INSERT INTO Transactions VALUES('"+accountNo+"','"+((expenseType==ExpenseType.INCOME)?"INCOME":"EXPENSE")+"','"+amount+"','"+date.toString()+"');");
    }

    @Override
    public List<Transaction> getAllTransactionLogs() {
        Cursor resultSet = db.rawQuery("Select * from Transactions", null);
        resultSet.moveToFirst();
        List<Transaction> result = new ArrayList<Transaction>();
        while(!resultSet.isAfterLast())
        {
            result.add( new Transaction(new Date(resultSet.getString(3)),resultSet.getString(0),((resultSet.getString(1)=="INCOME")?ExpenseType.INCOME:ExpenseType.EXPENSE), Double.parseDouble(resultSet.getString(2) ) ));
            resultSet.moveToNext();
        }
        return result;
    }

    @Override
    public List<Transaction> getPaginatedTransactionLogs(int limit) {
        Cursor resultSet = db.rawQuery("Select * from Transactions ORDER BY date_value LIMIT " + limit, null);
        resultSet.moveToFirst();
        List<Transaction> result = new ArrayList<Transaction>();
        while(!resultSet.isAfterLast())
        {
            result.add( new Transaction(new Date(resultSet.getString(3)),resultSet.getString(0),((resultSet.getString(1)=="INCOME")?ExpenseType.INCOME:ExpenseType.EXPENSE), Double.parseDouble(resultSet.getString(2) ) ));
            resultSet.moveToNext();
        }
        return result;
    }

}
