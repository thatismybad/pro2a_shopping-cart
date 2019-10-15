package cz.uhk.fim.shoppingcart.model;

public class CartItem {

    private String title;
    private double pricePerPiece;
    private boolean isPurchased;

    public CartItem() {
    }

    public CartItem(String title, double pricePerPiece) {
        this.title = title;
        this.pricePerPiece = pricePerPiece;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPricePerPiece() {
        return pricePerPiece;
    }

    public void setPricePerPiece(double pricePerPiece) {
        this.pricePerPiece = pricePerPiece;
    }

    public boolean isPurchased() {
        return isPurchased;
    }

    public void setPurchased(boolean purchased) {
        isPurchased = purchased;
    }
}
