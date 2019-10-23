package cz.uhk.fim.shoppingcart.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents shopping cart - it's our enhanced ArrayList
 */
public class ShoppingCart {
    private List<CartItem> itemList = new ArrayList<>();

    /**
     * Method returns specific item from list
     * @param index position of item
     * @return item of list
     */
    public CartItem getItem(int index) {
        return itemList.get(index);
    }

    /**
     * Method for adding new item to list
     * @param item shopping cart item
     */
    public void addItem(CartItem item) {
        itemList.add(item);
    }

    /**
     * Method for removing item from specific position
     * @param index position of item
     */
    public void removeItem(int index) {
        itemList.remove(index);
    }

    /**
     * Method for removing specific item
     * @param item which item
     */
    public void removeItem(CartItem item) {
        itemList.remove(item);
    }

    /**
     * Method return number of items in list
     * @return count of items
     */
    public int getCount() {
        return itemList.size();
    }

    /**
     * Method return all items from list
     * @return list of items
     */
    public List<CartItem> getAllItems() {
        return itemList;
    }

    /**
     * Method for counting total price of whole list
     * @return sum of price
     */
    public double getTotalPrice() {
        double sum = 0;
        for(int i = 0; i < itemList.size(); i++) {
            sum += itemList.get(i).getPricePerPiece() * itemList.get(i).getNumberOfPieces();
        }
        return sum;
    }

    /**
     * Method for counting purchased item price of whole list
     * @return sum of price
     */
    public double getBoughtPrice() {
        double sum = 0;
        for(CartItem item : itemList) {
            if (item.isPurchased()) {
                sum += item.getPricePerPiece() * item.getNumberOfPieces();
            }
        }
        return sum;
    }
}
