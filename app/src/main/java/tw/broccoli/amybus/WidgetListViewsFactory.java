package tw.broccoli.amybus;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by broccoli on 15/9/30.
 */
public class WidgetListViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    public static final String KEY_TEXT = "text";
    public static final String KEY_COLOR = "color";

    private Context mContext = null;
    private int appWidgetId;
    private List<Map<String, String>> list_text = new ArrayList();


    public WidgetListViewsFactory(Context context, Intent intent) {
        this.mContext = context;
        appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    public void addText(String text){
        if(list_text == null){
            list_text = new ArrayList();
            return;
        }

        Bus bus = Bus.getBus(text);
        if(bus == null)return;

        Map tempMap = new HashMap();
        tempMap.put(KEY_TEXT, text);
        Alarm tempAlarm = BusDBHelper.getAlarm(bus);

        if(tempAlarm==null||("".equals(tempAlarm.getRing()) && !tempAlarm.getVibrate())){
            tempMap.put(KEY_COLOR, String.valueOf(android.R.color.black));
        }else{
            tempMap.put(KEY_COLOR, String.valueOf(R.color.red));
        }
        list_text.add(tempMap);
    }

    public List<Map<String, String>> getListText(){
        return list_text;
    }

    public void setTextColor(String text, int color){
        for(int temp=0;temp<list_text.size();temp++){
            if(text.equals(list_text.get(temp).get(KEY_TEXT))){
                list_text.get(temp).put(KEY_COLOR, String.valueOf(color));
                break;
            }
        }
    }

    public int getTextColor(int index){
        return Integer.valueOf(list_text.get(index).get(KEY_COLOR));
    }

    public void cleanText(){
        list_text = new ArrayList();
    }

    @Override
    public int getCount() {
        return list_text.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews row = new RemoteViews(mContext.getPackageName(), R.layout.widget_list_row);

        row.setTextViewText(R.id.textview_widget_list_row, list_text.get(position).get(KEY_TEXT));

        if(position == 1){
            row.setTextColor(R.id.textview_widget_list_row, mContext.getResources().getColor(Integer.valueOf(list_text.get(position).get(KEY_COLOR))));
        }else{
            row.setTextColor(R.id.textview_widget_list_row, mContext.getResources().getColor(Integer.valueOf(list_text.get(position).get(KEY_COLOR))));
        }

        Bundle extras = new Bundle();
        extras.putInt(MyWidgetProvider.WIDGET_ITEM_INDEX, position);
        extras.putString(MyWidgetProvider.WIDGET_ITEM_TEXT, list_text.get(position).get(KEY_TEXT));
        Intent i = new Intent();
        i.putExtras(extras);
        row.setOnClickFillInIntent(R.id.textview_widget_list_row, i);

        return row;
    }


    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public boolean hasStableIds() {
        return true;
    }


    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }
}