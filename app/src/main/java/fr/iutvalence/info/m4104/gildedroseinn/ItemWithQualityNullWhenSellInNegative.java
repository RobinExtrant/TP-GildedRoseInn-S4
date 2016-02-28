package fr.iutvalence.info.m4104.gildedroseinn;

/**
 * Created by RO on 23/02/2016.
 */
class ItemWithQualityNullWhenSellInNegative extends Item {
    public ItemWithQualityNullWhenSellInNegative(String name, int sellIn, int quality, int initPrice) {
        super(name, sellIn, quality, initPrice);
    }

    public void updateQuality()
    {
        if (hasItemSellInDatePassed())
        {
            setQuality(0);
            return;
        }

        decrementItemQualityIfNotZero();
        if (getSellIn() <= GildedRose.SELL_IN_DATE_THRESHOLD_FOR_BACKSTAGE_QUALITY_BEING_DECREMENTED_TWICE)
            decrementItemQualityIfNotZero();
        if (getSellIn() <= GildedRose.SELL_IN_DATE_THRESHOLD_FOR_BACKSTAGE_QUALITY_BEING_DECREMENTED_THREE_TIMES)
            decrementItemQualityIfNotZero();
    }
}
