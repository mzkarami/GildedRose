package com.refactored.updator;

import com.gildedrose.Item;

/**
 * Aged Brie actually increases in Quality the older it gets
 */
public class BrieUpdator extends ItemUpdator {


    @Override
    public Boolean isApplicable(final Item item) {
        return item.name.equals(BRIE_ITEM);
    }

    @Override
    public void updateItem(final Item item) {

        if (!isHighestQualityValueReached(item)) {

            if (sellByDateHasPassed(item)) {
                increaseQualityInValue(item, 2);
            } else {
                increaseQualityInValue(item, 1);
            }
        }

        decreaseSellInValue(item);
    }
}