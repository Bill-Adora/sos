package com.example.sos.data.table;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sos.data.DbSOS;
import com.example.sos.data.Table;
import com.example.sos.data.bean.Contact;

public class TbContact extends Table {
	public final static String COLUMN_NAME_ID = "contact_id";
	public final static String COLUMN_NAME_FULLNAME = "fullname";
	public final static String COLUMN_NAME_PHONE = "phone";
	public final static String COLUMN_NAME_EMAIL = "email";
	public final static String COLUMN_NAME_RELATION = "relation";
	
	private final String RECORD_ID = "1";

	public TbContact(String tbName, DbSOS db) {
		super(tbName, db);
	}

	@Override
	public void create(SQLiteDatabase db) {

		String sql = "CREATE TABLE " + TB_NAME + "( " + COLUMN_NAME_ID
				+ " PRIMARY KEY " + COMMA + COLUMN_NAME_FULLNAME + TYPE_TEXT
				+ COMMA + COLUMN_NAME_PHONE + TYPE_TEXT + COMMA
				+ COLUMN_NAME_EMAIL + TYPE_TEXT + COMMA + COLUMN_NAME_RELATION
				+ TYPE_TEXT + " )";

		db.execSQL(sql);
	}

	public void add(Contact data) {

		ContentValues values = new ContentValues();
		values.put(COLUMN_NAME_ID, data.getId());
		values.put(COLUMN_NAME_FULLNAME, data.getFullName());
		values.put(COLUMN_NAME_PHONE, data.getPhone());
		values.put(COLUMN_NAME_EMAIL, data.getEmail());
		values.put(COLUMN_NAME_RELATION, data.getRelation());

		SQLiteDatabase db = this.db.getWritableDatabase();
		db.insert(TB_NAME, null, values);
		db.close();
	}

	public void set(Contact data){
		data.setId(RECORD_ID);
		if(get() == null)
			add(data);
		else
			update(data);
	}
	
	public int update(Contact data) {

		ContentValues values = new ContentValues();
		values.put(COLUMN_NAME_ID, RECORD_ID);
		values.put(COLUMN_NAME_FULLNAME, data.getFullName());
		values.put(COLUMN_NAME_PHONE, data.getPhone());
		values.put(COLUMN_NAME_EMAIL, data.getEmail());
		values.put(COLUMN_NAME_RELATION, data.getRelation());

		SQLiteDatabase db = this.db.getWritableDatabase();

		// updating row
		return db.update(TB_NAME, values, COLUMN_NAME_ID + " = ?",
				new String[] { data.getId() });

	}

	public Contact get() {
		SQLiteDatabase db = this.db.getWritableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM " + TB_NAME
				+ " WHERE contact_id = ?", new String[] { RECORD_ID });

		Contact data = null;

		if (cursor.getCount() > 0) {
			cursor.moveToFirst();

			data = new Contact();

			data.setId(cursor.getString(0));
			data.setFullName(cursor.getString(1));
			data.setPhone(cursor.getString(2));
			data.setEmail(cursor.getString(3));
			data.setRelation(cursor.getString(4));

		}

		return data;
	}

	public ArrayList<Contact> getAll() {
		ArrayList<Contact> list = new ArrayList<Contact>();

		String selectQuery = "SELECT  * FROM " + TB_NAME;

		SQLiteDatabase db = this.db.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {

				Contact data = new Contact();

				data.setId(cursor.getString(0));
				data.setFullName(cursor.getString(1));
				data.setPhone(cursor.getString(2));
				data.setEmail(cursor.getString(3));
				data.setRelation(cursor.getString(4));

				list.add(data);
			} while (cursor.moveToNext());
		}

		return list;
	}

	public void delete(String id) {
		SQLiteDatabase db = this.db.getWritableDatabase();
		db.delete(TB_NAME, COLUMN_NAME_ID + " = ?", new String[] { id });
		db.close();
	}

	public boolean exists(String id) {
		SQLiteDatabase db = this.db.getWritableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM " + TB_NAME
				+ " WHERE contact_id = ?", new String[] { id });

		if (cursor.moveToFirst()) {
			return true;
		}

		return false;
	}

}
