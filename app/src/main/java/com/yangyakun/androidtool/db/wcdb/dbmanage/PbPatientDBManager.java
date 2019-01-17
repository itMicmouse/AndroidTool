package com.yangyakun.androidtool.db.wcdb.dbmanage;

import com.tencent.wcdb.database.SQLiteDatabase;

import com.yangyakun.androidtool.db.wcdb.muldb.PbPatient;

import java.util.concurrent.atomic.AtomicInteger;

public class PbPatientDBManager {
	
	private AtomicInteger mOpenCounter = new AtomicInteger();
	
	private static PbPatientDBManager instance;
	private static PbPatient mdbHelper;
	private SQLiteDatabase mdb;
	 
	public static synchronized PbPatientDBManager initializeInstance(PbPatient helper) {
		if (instance == null) {
			instance = new PbPatientDBManager();
			mdbHelper = helper;
		}
		
		return instance;
	}
	 

	
	public static synchronized PbPatientDBManager getInstance() {
		if (instance == null) {
			throw new IllegalStateException(PbPatientDBManager.class.getSimpleName() +
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
