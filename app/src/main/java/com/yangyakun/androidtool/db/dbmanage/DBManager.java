package com.yangyakun.androidtool.db.dbmanage;

import java.util.concurrent.atomic.AtomicInteger;

import android.database.sqlite.SQLiteDatabase;

import com.yangyakun.androidtool.db.muldb.DBHelper;

public class DBManager {
	
	private AtomicInteger mOpenCounter = new AtomicInteger();
	
	private static DBManager instance;
	private static DBHelper mdbHelper;
	private SQLiteDatabase mdb;
	 
	public static synchronized DBManager initializeInstance(DBHelper helper) {
		if (instance == null) {
			instance = new DBManager();
			mdbHelper = helper;
		}
		
		return instance;
	}
	 

	
	public static synchronized DBManager getInstance() {
		if (instance == null) {
			throw new IllegalStateException(DBManager.class.getSimpleName() +
				" is not initialized, call initializeInstance(..) method first.");
		}
	 
		return instance;
	}
	 
	public synchronized SQLiteDatabase openDatabase() {
		if(mOpenCounter.incrementAndGet() == 1) {
			mdb = mdbHelper.getWritableDatabase();
		}
		return mdb;
	}
	 
	public synchronized void closeDatabase() {
		// Closing database
		if(mOpenCounter.decrementAndGet() == 0) {
			mdb.close();
		}
	}
}
