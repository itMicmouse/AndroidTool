package com.yangyakun.androidtool.db.wcdb.dbmanage;

import com.tencent.wcdb.database.SQLiteDatabase;

import com.yangyakun.androidtool.db.wcdb.muldb.DBHelper;

import java.util.concurrent.atomic.AtomicInteger;

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
