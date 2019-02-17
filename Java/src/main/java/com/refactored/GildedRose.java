package com.refactored;

import com.gildedrose.Item;
import com.refactored.updator.*;

import java.util.Arrays;
import java.util.List;


/**
 * Basic rules of handling inventroy and items:
 * <ol>
 * <li>All items have a SellIn value which denotes the number of days we have to sell the item</li>
 * <li>All items have a Quality value which denotes how valuable the item is</li>
 * <li>At the end of each day our system lowers both values for every item</li>
 * <li>The Quality of an item is never negative</li>
 * <li>The Quality of an item is never more than 50</li>
 * </ol>
 * <p>
 * Bij sommige items zijn er speciale regels van toepassing wat betreft sellIn en Quality afname. Om dit makkelijker
 * te kunnen uitbereiden is er naast een DefaultUpdater speciale Updaters geimplementeerd. Een nieuwe speciale
 * item kan als volgt worden toegevoegd aan het systeem : <p>
 * <ol>
 * <li>Implementeer de nieuwe speciale Updater.</li>
 * <li>Voeg deze toe aan de lijst van supported Updaters</li>
 * <li>Implmenteer de nodige unit tests</li>
 * </ol>
 * </p>
 */
class GildedRose {
    Item[] items;

    private static final List<ItemUpdator> ITEM_UPDATORS = Arrays.asList(
            new DefaultUpdator(),
            new BrieUpdator(),
            new SulfurasUpdator(),
            new BackstagePassUpdator(),
            new ConjuredUpdator()
    );

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {

            final ItemUpdator updator = findItemUpdator(item);
            updator.validateItem(item);
            updator.updateItem(item);
        }
    }

    private ItemUpdator findItemUpdator(final Item item) {
        return ITEM_UPDATORS.stream()
                .filter(g -> g.isApplicable(item))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }
}