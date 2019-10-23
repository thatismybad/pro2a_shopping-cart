package cz.uhk.fim.shoppingcart.model;

/**
 * Class represents item in shopping cart
 */
public class CartItem {

    private String title; // name of item
    private double pricePerPiece; // price per piece
    private int numberOfPieces; // number of pieces
    private boolean isPurchased; // is item purchased?

    public CartItem() {
    }

    public CartItem(String title, double pricePerPiece, int numberOfPieces) {
        this.title = title;
        this.pricePerPiece = pricePerPiece;
        this.numberOfPieces = numberOfPieces;
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

    public int getNumberOfPieces() {
        return numberOfPieces;
    }

    public void setNumberOfPieces(int numberOfPieces) {
        this.numberOfPieces = numberOfPieces;
    }

    public boolean isPurchased() {
        return isPurchased;
    }

    public void setPurchased(boolean purchased) {
        isPurchased = purchased;
    }
}
