package cz.uhk.fim.shoppingcart.model;

public class CartItem {

    private String title;

    public CartItem() {
    }

    public CartItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
