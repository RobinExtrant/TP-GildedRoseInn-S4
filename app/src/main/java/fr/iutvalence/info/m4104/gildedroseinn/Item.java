package fr.iutvalence.info.m4104.gildedroseinn;

public class Item {
    private final String name;

	private int sellIn;

	private int quality;

	private int initPrice;

	private int price;

	public Item(String name, int sellIn, int quality, int initPrice)
	{
		this.name = name;
		this.sellIn = sellIn;
		this.setQuality(quality);
		this.initPrice=initPrice;
		this.price=this.initPrice+(this.quality*2/3);
	}

	public String getName()
	{
		return this.name;
	}

	public void setSellIn(int sellIn)
	{
		this.sellIn = sellIn;
	}

	public int getSellIn()
	{
		return this.sellIn;
	}

	public int getQuality()
	{
		return quality;
	}

	public void setQuality(int quality)
	{
		this.quality = quality;
	}

    public int getPrice()
    {
        return price;
    }

	public void update()
	{
		updateQuality();
		updateSellIn();
		updatePrice();
	}

	public void updateSellIn()
	{
		if (this.getName().equals("Sulfuras, Hand of Ragnaros"))
			return;
		decrementItemSellIn();
	}

	public void updateQuality ()
	{
		decrementItemQualityIfNotZero();
		if (hasItemSellInDatePassed())
			decrementItemQualityIfNotZero();
	}

	public void updatePrice ()
	{
		this.price=this.initPrice+this.quality;
	}

	public void incrementItemQualityIfNotFifty() {
		if (this.getQuality() < 50)
			incrementItemQuality();
	}

	public void incrementItemQuality()
	{
		this.setQuality(this.getQuality() + 1);
	}

	public void decrementItemQualityIfNotZero()
	{
		if (this.getQuality() > 0)
			decrementItemQuality();
	}

	public boolean hasItemSellInDatePassed()
	{
		return this.getSellIn() <= 0;
	}

	public void decrementItemSellIn()
	{
		this.setSellIn(this.getSellIn() - 1);
	}

	public void decrementItemQuality()
	{
		this.setQuality(this.getQuality() - 1);
	}
}

