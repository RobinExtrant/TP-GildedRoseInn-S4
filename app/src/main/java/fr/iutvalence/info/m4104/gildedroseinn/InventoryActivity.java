package fr.iutvalence.info.m4104.gildedroseinn;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;


public class InventoryActivity extends Activity {

    Handler mHandler; //Utilisé pour faire la boucle de mise à jour
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_layout);
        GildedRose application = (GildedRose) getApplication();

        list = (ListView) findViewById(R.id.listView2);
        list.setAdapter(new ListItemsAdapter(getBaseContext(), application, ListItemsAdapter.inventoryItem));

        mHandler = new Handler();
        mHandler.postDelayed(mRunnable, GildedRose.LONGOFONEDAY * 1000);
    }

    private Runnable mRunnable = new Runnable() {

        @Override
        public void run() {
            list.invalidateViews();
            mHandler.postDelayed(mRunnable, GildedRose.LONGOFONEDAY*1000);
        }
    };
}
