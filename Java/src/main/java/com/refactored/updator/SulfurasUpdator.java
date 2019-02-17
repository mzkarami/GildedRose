package com.refactored.updator;

import com.gildedrose.Item;

/**
 * Sulfuras, being a legendary item, never has to be sold or decreases in Quality
 */
public class SulfurasUpdator extends ItemUpdator {


    @Override
    public Boolean isApplicable(final Item item) {

        return item.name.equals(SULFURAS_ITEM);
    }

    @Override
    public void updateItem(final Item item) {

        // No need to do anyhting here, as being a legendary item, never has to be sold or decreases in Quality
    }
}
