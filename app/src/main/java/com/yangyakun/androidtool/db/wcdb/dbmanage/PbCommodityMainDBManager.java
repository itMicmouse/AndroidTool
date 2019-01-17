package com.yangyakun.androidtool.db.wcdb.dbmanage;

import com.tencent.wcdb.database.SQLiteDatabase;

import com.yangyakun.androidtool.db.wcdb.muldb.PbCommodityMain;

import java.util.concurrent.atomic.AtomicInteger;

public class PbCommodityMainDBManager {
	
	private AtomicInteger mOpenCounter = new AtomicInteger();
	
	private static PbCommodityMainDBManager instance;
	private static PbCommodityMain mdbHelper;
	private SQLiteDatabase mdb;
	 
	public static synchronized PbCommodityMainDBManager initializeInstance(PbCommodityMain helper) {
		if (instance == null) {
			instance = new PbCommodityMainDBManager();
			mdbHelper = helper;
		}
		
		return instance;
	}
	 

	
	public static synchronized PbCommodityMainDBManager getInstance() {
		if (instance == null) {
			throw new IllegalStateException(PbCommodityMainDBManager.class.getSimpleName() +
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
