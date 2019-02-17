package com.refactored.updator;

import com.gildedrose.Item;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Abstract updator. Een speciale updator hoort deze class te extenden om speciale regels toe te kunnen passen.
 */
public abstract class ItemUpdator {

    // TODO. Deze kunnen naar een external persistence layer worden verplaatst
    static final String SULFURAS_ITEM = "Sulfuras, Hand of Ragnaros";
    static final String BRIE_ITEM = "Aged Brie";
    static final String BACKSTAGE_PASSES_ITEM = "Backstage passes to a TAFKAL80ETC concert";
    static final String CONJURED_ITEM = "Conjured Mana Cake";

    private static final List<String> SPECIAL_ITEMS =
            Arrays.asList(BRIE_ITEM, SULFURAS_ITEM, BACKSTAGE_PASSES_ITEM, CONJURED_ITEM);

    private static final Predicate<Item> IS_SPECIAL_ITEM = item -> SPECIAL_ITEMS.contains(item.name);

    private static final int HIGHEST_QUALITY = 50;
    static final int LOWEST_QUALITY = 0;

    private static final Predicate<Item> IS_VALID_ITEM =
            item -> item.quality >= LOWEST_QUALITY && item.quality <= HIGHEST_QUALITY;

    private static final Predicate<Item> SELL_BY_DATE_EXPIRED = item -> item.sellIn <= 0;

    public abstract Boolean isApplicable(final Item item);

    public abstract void updateItem(final Item item);

    void decreaseSellInValue(Item item) {
        item.sellIn = item.sellIn - 1;
    }

    void decreaseQualityInValue(final Item item, int decreaseBy) {

        item.quality = Math.max(item.quality - decreaseBy, LOWEST_QUALITY);
    }

    void increaseQualityInValue(final Item item, int increaseBy) {

        item.quality = Math.min(item.quality + increaseBy, HIGHEST_QUALITY);
    }

    /**
     * Basisvalidatie:
     * <ol>
     * <li>The Quality of an item is never negative</li>
     * <li>The Quality of an item is never more than 50</li>
     * </ol>
     *
     * @param item
     */
    public void validateItem(final Item item) {

        if (!IS_VALID_ITEM.test(item)) {
            throw new IllegalArgumentException("Item Quality property is invalid: " + item);
        }
    }

    static final boolean isHighestQualityValueReached(final Item item) {
        return item.quality >= HIGHEST_QUALITY;
    }

    static final boolean isLowestQualityValueReached(final Item item) {
        return item.quality == LOWEST_QUALITY;
    }

    static final boolean sellByDateHasPassed(final Item item) {
        return SELL_BY_DATE_EXPIRED.test(item);
    }

    static final boolean isSpecialItem(Item item) {
        return IS_SPECIAL_ITEM.test(item);
    }
}