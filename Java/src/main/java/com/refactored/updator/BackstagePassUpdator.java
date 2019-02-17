package com.refactored.updator;

import com.gildedrose.Item;

import java.util.function.Predicate;

/**
 * Backstage passes, like aged brie, increases in Quality as its SellIn value approaches:
 * <ol>
 * <li>Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but</li>
 * <li>Quality drops to 0 after the concert</li>
 * </ol>
 */
public class BackstagePassUpdator extends ItemUpdator {


    private static final Predicate<Item> SELLIN_BETWEEN_5_AND_0 =
            item -> item.sellIn <= 5 && item.sellIn > 0;

    private static final Predicate<Item> SELLIN_BETWEEN_10_AND_5 =
            item -> item.sellIn <= 10 && item.sellIn > 5;

    @Override
    public Boolean isApplicable(final Item item) {
        return item.name.equals(BACKSTAGE_PASSES_ITEM);
    }

    @Override
    public void updateItem(final Item item) {

        if (sellByDateHasPassed(item)) {
            item.quality = LOWEST_QUALITY;
        } else {
            if (!isHighestQualityValueReached(item)) {

                if (SELLIN_BETWEEN_10_AND_5.test(item)) {
                    increaseQualityInValue(item, 2);
                } else if (SELLIN_BETWEEN_5_AND_0.test(item)) {
                    increaseQualityInValue(item, 3);
                }
            }
        }

        decreaseSellInValue(item);
    }
}
