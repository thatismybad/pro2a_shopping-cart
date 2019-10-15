package cz.uhk.fim.shoppingcart.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<CartItem> itemList = new ArrayList<>();

    public CartItem getItem(int index) {
        return itemList.get(index);
    }

    public void addItem(CartItem item) {
        itemList.add(item);
    }

    public void removeItem(int index) {
        itemList.remove(index);
    }

    public void removeItem(CartItem item) {
        itemList.remove(item);
    }

    public int getCount() {
        return itemList.size();
    }

    public List<CartItem> getAllItems() {
        return itemList;
    }

    public double getTotalPrice() {
        double sum = 0;
        for(int i = 0; i < itemList.size(); i++) {
            sum += itemList.get(i).getPricePerPiece();
        }
        return sum;
//        for(CartItem item : itemList) {
//            sum += item.getPricePerPiece();
//        }
//        return sum;
    }
}
