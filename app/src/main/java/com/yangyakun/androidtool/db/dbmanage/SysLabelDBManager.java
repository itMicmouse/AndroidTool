package com.yangyakun.androidtool.db.dbmanage;

import android.database.sqlite.SQLiteDatabase;

import com.yangyakun.androidtool.db.muldb.DBHelper;
import com.yangyakun.androidtool.db.muldb.SysLabel;

import java.util.concurrent.atomic.AtomicInteger;

public class SysLabelDBManager {
	
	private AtomicInteger mOpenCounter = new AtomicInteger();
	
	private static SysLabelDBManager instance;
	private static SysLabel mdbHelper;
	private SQLiteDatabase mdb;
	 
	public static synchronized SysLabelDBManager initializeInstance(SysLabel helper) {
		if (instance == null) {
			instance = new SysLabelDBManager();
			mdbHelper = helper;
		}
		
		return instance;
	}
	 

	
	public static synchronized SysLabelDBManager getInstance() {
		if (instance == null) {
			throw new IllegalStateException(SysLabelDBManager.class.getSimpleName() +
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
