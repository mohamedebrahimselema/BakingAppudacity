package com.example.bakingappudacity.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.widget.RemoteViews;

import com.example.bakingappudacity.R;
import com.example.bakingappudacity.util.Constants;


public class IngredientWidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {


        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_ingredient_widget);

        SharedPreferences sharedPreferences=context.getSharedPreferences("com.kehinde.bakingapp", Context.MODE_PRIVATE);
        String recipe= sharedPreferences.getString(Constants.S_PREF_RECIPE,"");

        Intent intent = new Intent(context, WidgetIntentService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

        views.setTextViewText(R.id.widget_recipe_name,recipe + " Ingredients");


        // Set up the RemoteViews object to use a RemoteViews adapter.
        views.setRemoteAdapter(appWidgetId, R.id.ing_widget_list, intent);



        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {

            updateAppWidget(context, appWidgetManager, appWidgetId);
        }

        super.onUpdate(context,appWidgetManager,appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

