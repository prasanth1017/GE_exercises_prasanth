package com.ge.exercise5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.ge.exercise5.ItemType.*;

public class Warehouse {
    private static final Logger logger = LogManager.getLogger(Warehouse.class);

    List<Item> items;

    public Warehouse() {
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void updateItems() {
        for (Item item : items) {
            if (item.getType() != AGEABLE && item.getType() != RARE) {
                if (item.getValue() > 0) {
                    if (item.getType() == NORMAL) {
                        item.setValue(item.getValue() - 1);
                    } else if (item.getType() == PERISHABLE) {
                        item.setValue(item.getValue() - 2);
                    }
                    // If value goes below 0, reset to 0
                    if (item.getValue() < 0) {
                        item.setValue(0);
                    }
                }
            } else if (item.getType() != NORMAL && item.getType() != AGEABLE && item.getType() != RARE) {
                if (item.getValue() > 0) {
                    item.setValue(item.getValue() - 1);
                }
            } else if (item.getValue() < 50) {
                item.setValue(item.getValue() + 1);
                // RARE items increase in value over time
                if (item.getType() == RARE) {
                    if (item.getSellBy() <= 14) {
                        if (item.getValue() < 50)
                            item.setValue(item.getValue() + 1);
                    }
                    if (item.getSellBy() <= 7) {
                        if (item.getValue() < 50)
                            item.setValue(item.getValue() + 1);
                    }
                }
            }
            // Precious items never to be sold
            if (item.getType() != PRECIOUS) {
                item.setSellBy(item.getSellBy() - 1);
            }
            if (item.getSellBy() < 0) {
                if (item.getType() != AGEABLE) {
                    if (item.getType() != RARE) {
                        if (item.getValue() > 0) {
                            // Precious items never decrease in value
                            if(item.getType() != PRECIOUS) {
                                // Perishable items decrease twice the normal rate
                                if(item.getType() != NORMAL) {
                                    item.setValue(item.getValue() - 2);
                                    // If value goes below 0, reset to 0
                                    if (item.getValue() < 0) {
                                        item.setValue(0);
                                    }
                                // Normal items decrease by one
                                } else {
                                    item.setValue(item.getValue() - 1);
                                }
                            }
                        }
                    // Rare items value drop to 0 when sellby is 0
                    } else {
                        item.setValue(item.getValue() - item.getValue());
                    }
                // Ageable items increase in value by 1
                } else {
                    if (item.getValue() < 50) {
                        item.setValue(item.getValue() + 1);
                    }
                }
            }
        }
    }
}
