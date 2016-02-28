package fr.iutvalence.info.m4104.gildedroseinn;

import android.app.Application;

import java.util.ArrayList;

public class GildedRose extends Application
{
    public final static int LONGOFONEDAY = 10;
    public final static int SELL_IN_DATE_THRESHOLD_FOR_BACKSTAGE_QUALITY_BEING_DECREMENTED_THREE_TIMES = 5;
    public final static int SELL_IN_DATE_THRESHOLD_FOR_BACKSTAGE_QUALITY_BEING_DECREMENTED_TWICE = 10;
    private int nbJours = 1;
    private int money = 50;

    Item[] items = new Item[] {
             new Item("+5 Dexterity Vest", 10, 20, 30),
             new ItemWithMaxQuality50("Aged Brie", 2, 0, 3),
             new Item("Elixir of the Mongoose", 5, 7, 15),
             new ItemWithInvariableQuality("Sulfuras, Hand of Ragnaros", 0, 80, 10),
             new ItemWithInvariableQuality("Sulfuras, Hand of Ragnaros", -1, 80, 10),
             new ItemWithQualityNullWhenSellInNegative("Backstage passes to a TAFKAL80ETC concert", 15, 20, 15),
             new ItemWithQualityNullWhenSellInNegative("Backstage passes to a TAFKAL80ETC concert", 10, 49, 30),
             new ItemWithQualityNullWhenSellInNegative("Backstage passes to a TAFKAL80ETC concert", 5, 49, 30),
             new Item("Conjured Mana Cake", 3, 6, 9) };

    ArrayList<Item> itemsBought = new ArrayList<Item>();

    public Item[] getItems()
    {
        return items;
    }

    public ArrayList<Item> getItemsBought()
    {
        return itemsBought;
    }

    public void incrementDay()
    {
        this.nbJours +=1;
    }

    public int getNbJours()
    {
        return this.nbJours;
    }

    public void setNbJours(int nbJours)
    {
        this.nbJours= nbJours;
    }

    public int getMoney()
    {
        return this.money;
    }

    public void setMoney(int money)
    {
        this.money= money;
    }

    public void decrementMoney(int cost)
    {
        this.money-=cost;
    }
    public void incrementMoney(int cost)
    {
        this.money+=cost;
    }

    public static void updateItem(Item item)
    {
        item.update();
    }
}

