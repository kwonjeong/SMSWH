package com.example.jeong;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

public class DataAdapter {

    protected static final String TAG = "DataAdapter";

    protected static final String TABLE_NAME = "toy_info";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DataBaseHelper mDbHelper;

    public DataAdapter(Context context)
    {
        this.mContext = context;
        mDbHelper = new DataBaseHelper(mContext);
    }

    public DataAdapter createDatabase() throws SQLException
    {
        try
        {
            mDbHelper.createDataBase();
        }
        catch (IOException mIOException)
        {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public DataAdapter open() throws SQLException
    {
        try
        {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "open >>"+ mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public void close()
    {
        mDbHelper.close();
    }

    public ArrayList getTableData()
    {
        try
        {
            String sql ="SELECT * FROM " + TABLE_NAME;

            ArrayList toyList = new ArrayList();

            ttoy info = null;

            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur!=null)
            {
                // 칼럼의 마지막까지
                while( mCur.moveToNext() ) {

                    info = new ttoy();

                    info.setTtoyname(mCur.getString(0));
                    info.setTtoymade(mCur.getInt(1));
                    info.setTtoydetail(mCur.getString(2));
                    info.setTtoyquality(mCur.getString(3));
                    info.setTtoyage(mCur.getInt(4));
                    info.setTtoyimgid(mCur.getString(5));

                    // 리스트에 넣기
                    toyList.add(info);
                }

            }
            return toyList;
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }
}
