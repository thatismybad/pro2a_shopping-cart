package cz.uhk.fim.shoppingcart.gui;

import cz.uhk.fim.shoppingcart.model.CartItem;
import cz.uhk.fim.shoppingcart.model.ShoppingCart;

import javax.swing.table.AbstractTableModel;

/**
 * Table model for our shopping cart
 */
public class ShoppingCartTableModel extends AbstractTableModel {

    /**
     * Definition of interface for listening changes on purchase checkbox
     */
    interface CellCheckChangedListener {
        void onPurchaseUpdate();
    }

    private CellCheckChangedListener cellCheckChangedListener;

    private ShoppingCart shoppingCart;

    /**
     * Method for initial setting/update of table with shopping cart data + passing listener from main frame windows
     * @param shoppingCart
     * @param cellCheckChangedListener
     */
    public void setShoppingCart(ShoppingCart shoppingCart, CellCheckChangedListener cellCheckChangedListener) {
        this.shoppingCart = shoppingCart;
        this.cellCheckChangedListener = cellCheckChangedListener;
        // method for update of table data
        fireTableDataChanged();
    }

    /**
     * Method for getting row count of table
     * @return number of items in shopping cart
     */
    @Override
    public int getRowCount() {
        return shoppingCart.getCount();
    }

    /**
     * Method for getting number of columns
     * @return 5 columns
     */
    @Override
    public int getColumnCount() {
        return 5;
    }

    /**
     * Method for getting value of specific cell in row and column
     * @param rowIndex which row
     * @param columnIndex which column
     * @return specific data (and data type)
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CartItem item = shoppingCart.getItem(rowIndex);
        switch (columnIndex) {
            case 0:
                return item.getTitle();
            case 1:
                return item.getPricePerPiece();
            case 2:
                return item.getNumberOfPieces();
            case 3:
                return item.getNumberOfPieces() * item.getPricePerPiece();
            case 4:
                return item.isPurchased();
            default:
                return "?";
        }
    }

    /**
     * Method for setting value of specific cell in row and column
     * @param aValue new updated value
     * @param rowIndex which row
     * @param columnIndex which column
     */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        CartItem item = shoppingCart.getItem(rowIndex);
        switch (columnIndex) {
            case 0:
                item.setTitle((String) aValue);
                break;
            case 1:
                item.setPricePerPiece((Double) aValue);
                break;
            case 2:
                item.setNumberOfPieces((Integer) aValue);
                break;
            case 4:
                item.setPurchased((Boolean) aValue);
                // when if checkbox updated - we call onPurchaseUpdate method for update of label in another class
                cellCheckChangedListener.onPurchaseUpdate();
                break;
            default:
                break;
        }
    }

    /**
     * Method for getting name of columns
     * @param column which column
     * @return name of column
     */
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Název";
            case 1:
                return "Cena za kus";
            case 2:
                return "Počet kusů";
            case 3:
                return "Mezisoučet";
            case 4:
                return "Zakoupeno?";
            default:
                return "?";
        }
    }

    /**
     * Method for getting data type of specific column
     * @param columnIndex which column
     * @return data type of column
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
            case 3:
                return Double.class;
            case 2:
                return Integer.class;
            case 4:
                return Boolean.class;
            default:
                return Object.class;
        }
    }

    /**
     * Method for setting which cell/row/column should be editable
     * @param rowIndex which row
     * @param columnIndex which column
     * @return should be editable?
     * - we don't want to allow editing of "subtotal" column
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != 3;
    }
}
