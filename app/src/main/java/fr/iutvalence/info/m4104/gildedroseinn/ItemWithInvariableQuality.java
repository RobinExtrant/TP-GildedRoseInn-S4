package fr.iutvalence.info.m4104.gildedroseinn;

/**
 * Created by RO on 23/02/2016.
 */
class ItemWithInvariableQuality extends Item {
    public ItemWithInvariableQuality(String name, int sellIn, int quality, int initPrice) {
        super(name, sellIn, quality, initPrice);
    }

    public void updateQuality()
    {
        //The quality is invariable
    }
}
