package com.example.bakingappudacity.widget.db;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class IngredientContract {
	public static final String DB_NAME = "com.kehinde.bakingapp.widget.db.ingredients";
	public static final int DB_VERSION = 1;
	public static final String TABLE = "ingredients";
    public static final String AUTHORITY = "com.kehinde.bakingapp.widget";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLE);
    public static final int INGREDIENT_LIST = 1;
    public static final int INGREDIENT_ITEM = 2;
    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/kehinde.ingredientDB/"+TABLE;
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/kehinde/ingredientDB" + TABLE;

	public class Columns {
		public static final String QUANTITY = "quantity";
		public static final String MEASURE = "measure";
		public static final String INGREDIENT = "ingredient";
		public static final String _ID = BaseColumns._ID;
	}
}
