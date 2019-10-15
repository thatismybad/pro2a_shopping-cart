package cz.uhk.fim.shoppingcart.gui;

import cz.uhk.fim.shoppingcart.model.CartItem;
import cz.uhk.fim.shoppingcart.model.ShoppingCart;

import javax.swing.table.AbstractTableModel;

public class ShoppingCartTableModel extends AbstractTableModel {

    private ShoppingCart shoppingCart;

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return shoppingCart.getCount();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

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
                break;
            default:
                break;
        }
    }

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

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
}
