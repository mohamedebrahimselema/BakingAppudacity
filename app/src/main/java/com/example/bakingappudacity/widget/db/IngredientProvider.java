package com.example.bakingappudacity.widget.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class IngredientProvider extends ContentProvider{

    private SQLiteDatabase db;
    private IngredientDBHelper ingredientDBHelper;
    public static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(IngredientContract.AUTHORITY, IngredientContract.TABLE, IngredientContract.INGREDIENT_LIST);
        uriMatcher.addURI(IngredientContract.AUTHORITY, IngredientContract.TABLE+"/#", IngredientContract.INGREDIENT_ITEM);
    }

    @Override
    public boolean onCreate() {
        boolean ret = true;
        ingredientDBHelper = new IngredientDBHelper(getContext());
        db = ingredientDBHelper.getWritableDatabase();

        if (db == null) {
            ret = false;
        }

        if (db.isReadOnly()) {
            db.close();
            db = null;
            ret = false;
        }

        return ret;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(IngredientContract.TABLE);

        switch (uriMatcher.match(uri)) {
            case IngredientContract.INGREDIENT_LIST:
                break;

            case IngredientContract.INGREDIENT_ITEM:
                qb.appendWhere(IngredientContract.Columns._ID + " = "+ uri.getLastPathSegment());
                break;

            default:
                throw new IllegalArgumentException("Invalid URI: " + uri);
        }

        Cursor cursor = qb.query(db,projection,selection,selectionArgs,null,null,null);

        return cursor;
    }

    @Override
    public String getType(Uri uri) {

        switch (uriMatcher.match(uri)) {
            case IngredientContract.INGREDIENT_LIST:
                return IngredientContract.CONTENT_TYPE;

            case IngredientContract.INGREDIENT_ITEM:
                return IngredientContract.CONTENT_ITEM_TYPE;

            default:
                throw new IllegalArgumentException("Invalid URI: "+uri);
        }

    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {

        if (uriMatcher.match(uri) != IngredientContract.INGREDIENT_LIST) {
            throw new IllegalArgumentException("Invalid URI: "+uri);
        }

        long id = db.insert(IngredientContract.TABLE,null,contentValues);

        if (id>0) {
            return ContentUris.withAppendedId(uri, id);
        }
        throw new SQLException("Error inserting into table: "+ IngredientContract.TABLE);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        int deleted = 0;

        switch (uriMatcher.match(uri)) {
            case IngredientContract.INGREDIENT_LIST:
                db.delete(IngredientContract.TABLE,selection,selectionArgs);
                break;

            case IngredientContract.INGREDIENT_ITEM:
                String where = IngredientContract.Columns._ID + " = " + uri.getLastPathSegment();
                if (!selection.isEmpty()) {
                    where += " AND "+selection;
                }

                deleted = db.delete(IngredientContract.TABLE,where,selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Invalid URI: "+uri);
        }

        return deleted;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {

        int updated = 0;

        switch (uriMatcher.match(uri)) {
            case IngredientContract.INGREDIENT_LIST:
                db.update(IngredientContract.TABLE,contentValues,s,strings);
                break;

            case IngredientContract.INGREDIENT_ITEM:
                String where = IngredientContract.Columns._ID + " = " + uri.getLastPathSegment();
                if (!s.isEmpty()) {
                    where += " AND "+s;
                }
                updated = db.update(IngredientContract.TABLE,contentValues,where,strings);
                break;

            default:
                throw new IllegalArgumentException("Invalid URI: "+uri);
        }

        return updated;
    }
}
