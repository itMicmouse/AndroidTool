package com.yangyakun.androidtool.db.wcdb.dbmanage;

import com.tencent.wcdb.database.SQLiteDatabase;

import com.yangyakun.androidtool.db.wcdb.muldb.PbPrescription;

import java.util.concurrent.atomic.AtomicInteger;

public class PbPrescriptionDBManager {
	
	private AtomicInteger mOpenCounter = new AtomicInteger();
	
	private static PbPrescriptionDBManager instance;
	private static PbPrescription mdbHelper;
	private SQLiteDatabase mdb;
	 
	public static synchronized PbPrescriptionDBManager initializeInstance(PbPrescription helper) {
		if (instance == null) {
			instance = new PbPrescriptionDBManager();
			mdbHelper = helper;
		}
		
		return instance;
	}
	 

	
	public static synchronized PbPrescriptionDBManager getInstance() {
		if (instance == null) {
			throw new IllegalStateException(PbPrescriptionDBManager.class.getSimpleName() +
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
