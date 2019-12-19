package com.ge.exercise5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Item {

    private static final Logger logger = LogManager.getLogger(Item.class);

    public ItemType type;
    public int value;
    public int sellBy;

    public Item(ItemType itemType, int value, int sellBy) {
        this.type = itemType;
        this.value = value;
        this.sellBy = sellBy;
    }

    public void setType(ItemType type) {
		this.type = type;
	}

	public ItemType getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    //MODIFICATION
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + sellBy;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + value;
		return result;
	}

    //MODIFICATION
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (sellBy != other.sellBy)
			return false;
		if (type != other.type)
			return false;
		if (value != other.value)
			return false;
		return true;
	}

	public int getSellBy() {
        return sellBy;
    }

    public void setSellBy(int sellBy) {
        this.sellBy = sellBy;
    }
}
