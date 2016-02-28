package fr.iutvalence.info.m4104.gildedroseinn;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by extrantr on 08/02/16.
 * On utilise cette classe deux fois (Pour la shopActivity et l'inventoryActivity
 */
public class ListItemsAdapter implements android.widget.ListAdapter
{
    public final static boolean shopItem = true;
    public final static boolean inventoryItem = false;

    private final Context context;
    private final GildedRose application;
    private final boolean typeListItemsAdapter;//true pour shop et false pour inventory

    public ListItemsAdapter(Context context, GildedRose application, boolean typeListItemsAdapter)
    {
        this.context=context;
        this.application=application;
        this.typeListItemsAdapter=typeListItemsAdapter;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        if(typeListItemsAdapter)
            return this.application.getItems().length;
        else
            return this.application.getItemsBought().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
            convertView = LayoutInflater.from(this.context).inflate(R.layout.item_layout, parent, false);

        final Item item;

        if(typeListItemsAdapter) //On utilise ce booléen pour différencier le cas des deux activités
        {
            item = this.application.getItems()[position];
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(application.getMoney()>=item.getPrice())
                    {
                        application.decrementMoney(item.getPrice());
                        application.itemsBought.add(item);
                        Toast.makeText(context, "Item bought", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(context, "No money", Toast.LENGTH_SHORT).show();
                }

            });

        }
        else
        {
            item = this.application.getItemsBought().get(position);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    application.itemsBought.remove(item);
                    Toast.makeText(context, "Item used", Toast.LENGTH_SHORT).show();
                    ((ListView)v.getParent()).invalidateViews();
                }
            });
        }

        TextView textName = (TextView) convertView.findViewById(R.id.name);
        textName.setText(item.getName());

        TextView textPrice = (TextView) convertView.findViewById(R.id.price);
        textPrice.setText(String.valueOf(item.getPrice()));

        TextView textQuality = (TextView) convertView.findViewById(R.id.quality);
        textQuality.setText(String.valueOf(item.getQuality()));

        return convertView; /*On renvoie la nouvelle vue, sur laquelle nous avons modifié les
                            sous view qui correspond à l'item à la position indiquée automatiquement
                            par le listView*/
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
