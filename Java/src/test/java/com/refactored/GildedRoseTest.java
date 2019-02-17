package com.refactored;

import com.gildedrose.Item;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GildedRoseTest {

    @Test
    public void testDefaultItem() {
        Item[] items = new Item[]{
                new Item("+5 Dexterity Vest", 10, 20), //
                new Item("+5 Dexterity Vest", 0, 20), //
                new Item("+5 Dexterity Vest", 1, 50), //
                new Item("+5 Dexterity Vest", 0, 0),
                new Item("+5 Dexterity Vest", 0, 1)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("+5 Dexterity Vest, 9, 19", app.items[0].toString());
        assertEquals("+5 Dexterity Vest, -1, 18", app.items[1].toString());
        assertEquals("+5 Dexterity Vest, 0, 49", app.items[2].toString());
        assertEquals("+5 Dexterity Vest, -1, 0", app.items[3].toString());
        assertEquals("+5 Dexterity Vest, -1, 0", app.items[4].toString());
    }

    @Test
    public void testBrieItem() {
        Item[] items = new Item[]{
                new Item("Aged Brie", 5, 1), //
                new Item("Aged Brie", 0, 0), //
                new Item("Aged Brie", 5, 50), //
                new Item("Aged Brie", -1, 2), //
                new Item("Aged Brie", -3, 5), //
                new Item("Aged Brie", -5, 10), //
                new Item("Aged Brie", -5, 50), //
                new Item("Aged Brie", -5, 49), //
                new Item("Aged Brie", 0, 49) //
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Aged Brie, 4, 2", app.items[0].toString());
        assertEquals("Aged Brie, -1, 2", app.items[1].toString());
        assertEquals("Aged Brie, 4, 50", app.items[2].toString());
        assertEquals("Aged Brie, -2, 4", app.items[3].toString());
        assertEquals("Aged Brie, -4, 7", app.items[4].toString());
        assertEquals("Aged Brie, -6, 12", app.items[5].toString());
        assertEquals("Aged Brie, -6, 50", app.items[6].toString());
        assertEquals("Aged Brie, -6, 50", app.items[7].toString());
        assertEquals("Aged Brie, -1, 50", app.items[8].toString());
    }

    @Test
    public void testBackstageItem() {
        Item[] items = new Item[]{
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 1), //
                new Item("Backstage passes to a TAFKAL80ETC concert", 0, 0), //
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 50), //
                new Item("Backstage passes to a TAFKAL80ETC concert", -1, 2), //
                new Item("Backstage passes to a TAFKAL80ETC concert", -3, 5), //
                new Item("Backstage passes to a TAFKAL80ETC concert", -5, 10), //
                new Item("Backstage passes to a TAFKAL80ETC concert", -5, 50), //
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49) //
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert, 4, 4", app.items[0].toString());
        assertEquals("Backstage passes to a TAFKAL80ETC concert, -1, 0", app.items[1].toString());
        assertEquals("Backstage passes to a TAFKAL80ETC concert, 4, 50", app.items[2].toString());
        assertEquals("Backstage passes to a TAFKAL80ETC concert, -2, 0", app.items[3].toString());
        assertEquals("Backstage passes to a TAFKAL80ETC concert, -4, 0", app.items[4].toString());
        assertEquals("Backstage passes to a TAFKAL80ETC concert, -6, 0", app.items[5].toString());
        assertEquals("Backstage passes to a TAFKAL80ETC concert, -6, 0", app.items[6].toString());
        assertEquals("Backstage passes to a TAFKAL80ETC concert, 4, 50", app.items[7].toString());
    }

    @Test
    public void testSulfurItem() {
        Item[] items = new Item[]{
                new Item("Sulfuras, Hand of Ragnaros", 10, 20), //
                new Item("Sulfuras, Hand of Ragnaros", 0, 20), //
                new Item("Sulfuras, Hand of Ragnaros", 1, 50), //
                new Item("Sulfuras, Hand of Ragnaros", 0, 0),
                new Item("Sulfuras, Hand of Ragnaros", 1, 49), //
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Sulfuras, Hand of Ragnaros, 10, 20", app.items[0].toString());
        assertEquals("Sulfuras, Hand of Ragnaros, 0, 20", app.items[1].toString());
        assertEquals("Sulfuras, Hand of Ragnaros, 1, 50", app.items[2].toString());
        assertEquals("Sulfuras, Hand of Ragnaros, 0, 0", app.items[3].toString());
        assertEquals("Sulfuras, Hand of Ragnaros, 1, 49", app.items[4].toString());
    }

    @Test
    public void testConjuredItem() {
        Item[] items = new Item[]{
                new Item("Conjured Mana Cake", 10, 20), //
                new Item("Conjured Mana Cake", 0, 20), //
                new Item("Conjured Mana Cake", 1, 50), //
                new Item("Conjured Mana Cake", 0, 0),
                new Item("Conjured Mana Cake", 1, 1)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Conjured Mana Cake, 9, 18", app.items[0].toString());
        assertEquals("Conjured Mana Cake, -1, 16", app.items[1].toString());
        assertEquals("Conjured Mana Cake, 0, 48", app.items[2].toString());
        assertEquals("Conjured Mana Cake, -1, 0", app.items[3].toString());
        assertEquals("Conjured Mana Cake, 0, 0", app.items[4].toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidItem() {
        Item[] items = new Item[]{
                new Item("+5 Dexterity Vest", 1, 56)
        };

        GildedRose app = new GildedRose(items);

        try {
            app.updateQuality();

        } catch (IllegalArgumentException exception) {
            assertEquals("Item Quality property is invalid: +5 Dexterity Vest, 1, 56", exception.getMessage());
            throw exception;
        }
    }
}