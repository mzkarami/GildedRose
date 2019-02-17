package com.refactored.updator;

import com.gildedrose.Item;

/**
 * Conjured items degrade in Quality twice as fast as normal items
 */
public class ConjuredUpdator extends ItemUpdator {


    @Override
    public Boolean isApplicable(final Item item) {

        return item.name.equals(CONJURED_ITEM);
    }

    @Override
    public void updateItem(final Item item) {

        if (!isLowestQualityValueReached(item)) {

            if (sellByDateHasPassed(item)) {
                decreaseQualityInValue(item, 4);
            } else {
                decreaseQualityInValue(item, 2);
            }
        }

        decreaseSellInValue(item);
    }
}
