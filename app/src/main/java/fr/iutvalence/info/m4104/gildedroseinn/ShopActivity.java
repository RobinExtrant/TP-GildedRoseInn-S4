package fr.iutvalence.info.m4104.gildedroseinn;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;


public class ShopActivity extends ActionBarActivity {

    Handler mHandler; //Utilisé pour faire la boucle de mise à jour
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_layout);
        GildedRose application = (GildedRose) getApplication();

        list = (ListView) findViewById(R.id.listView);
        list.setAdapter(new ListItemsAdapter(getBaseContext(), application, ListItemsAdapter.shopItem));

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
