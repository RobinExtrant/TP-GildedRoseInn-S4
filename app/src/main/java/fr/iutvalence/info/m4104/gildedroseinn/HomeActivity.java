package fr.iutvalence.info.m4104.gildedroseinn;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public class HomeActivity extends Activity {
    public final static String nameFilePrefs = "MyPrefsGR";
    public final static String prefMoney = "money";
    public final static String prefNbJours = "nbJours";

    Handler mHandler; //Utilisé pour faire la boucle d'incrémentation automatique des jours.
    GildedRose application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        application = (GildedRose) getApplication();

        //Système de préférence pour les jours et la monnaie
        SharedPreferences settings = getSharedPreferences(nameFilePrefs, 0);
        application.setMoney(settings.getInt(prefMoney, 50)); //constante et valeur par defaut
        application.setNbJours(settings.getInt(prefNbJours, 1));

        mHandler = new Handler();
        mHandler.postDelayed(mRunnable, GildedRose.LONGOFONEDAY*1000);
    }

    private Runnable mRunnable = new Runnable() {

        @Override
        public void run() {
            nextDay();
            mHandler.postDelayed(mRunnable, GildedRose.LONGOFONEDAY*1000); //Pour boucler
        }
    };

    public void homeActivityClickListener(View view)
    {
        switch (view.getId())
        {
            case R.id.shop_button :
                startShopActivity();
                break;
            case R.id.inventory_button :
                startInventoryActivity();
                break;
            case R.id.next_button :
                nextDay();
                break;
            case R.id.moreMoney_button:
                moreMoney();
                break;
            default :
        }
    }

    @Override
    public void onResume() //Lorsque l'on retourne sur cette fenêtre, on met à jour ce qu'il faut.
    {
        super.onResume();
        TextView jourX = (TextView) this.findViewById(R.id.day_text);
        jourX.setText("Day "+application.getNbJours());
        TextView moneyX = (TextView) this.findViewById(R.id.money_text);
        moneyX.setText("Money: " + application.getMoney());
    }

    // On sauvegarde les préférences à l'arrêt de l'application
    @Override
    public void onStop()
    {
        super.onStop();
        SharedPreferences settings = getSharedPreferences(nameFilePrefs, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(prefMoney, application.getMoney());
        editor.putInt(prefNbJours, application.getNbJours());
    }

    public void nextDay()
    {
        application.incrementDay();
        for (Item item : application.getItems())
            application.updateItem(item);
        for (Item item : application.getItemsBought())
            application.updateItem(item);
        TextView jourX = (TextView) this.findViewById(R.id.day_text);
        onResume();
    }

    private void moreMoney()
    {
        application.incrementMoney(10);
        TextView moneyX = (TextView) this.findViewById(R.id.money_text);
        onResume();
//        moneyX.setText("Money: " + application.getMoney());
    }

    private void startInventoryActivity()
    {
        this.startActivity(new Intent(this, InventoryActivity.class));
        Intent test = new Intent(this, InventoryActivity.class);
    }

    private void startShopActivity()
    {
        this.startActivity( new Intent(this, ShopActivity.class));
    }
}
