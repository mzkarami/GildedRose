package com.refactored.updator;

import com.gildedrose.Item;

/**
 * <ol>
 * <li>At the end of each day our system lowers both values for every item</li>
 * <li>Once the sell by date has passed, Quality degrades twice as fast</li>
 * </ol>
 */
public class DefaultUpdator extends ItemUpdator {


    @Override
    public Boolean isApplicable(final Item item) {
        return !isSpecialItem(item);
    }

    @Override
    public void updateItem(final Item item) {

        if (!isLowestQualityValueReached(item)) {

            if (sellByDateHasPassed(item)) {
                decreaseQualityInValue(item, 2);
            } else {
                decreaseQualityInValue(item, 1);
            }
        }

        decreaseSellInValue(item);
    }
}
