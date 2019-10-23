package cz.uhk.fim.shoppingcart.gui;

import cz.uhk.fim.shoppingcart.model.CartItem;
import cz.uhk.fim.shoppingcart.model.ShoppingCart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Class represents main window frame + implements listener for updates when checkbox is clicked
 */
public class MainFrame extends JFrame implements ShoppingCartTableModel.CellCheckChangedListener {

    private ShoppingCart shoppingCart = new ShoppingCart();
    private ShoppingCartTableModel model;
    private ShoppingCartTableModel.CellCheckChangedListener cellCheckChangedListener = this;
    private JLabel lblBoughtPriceValue;

    /**
     * Constructor with initialization method calls
     */
    public MainFrame() {
        initFrame();
        initUi();
    }

    /**
     * Initialization method for User Interface (UI)
     */
    private void initUi() {
        // panel container for panel with inputs and label for error messages
        JPanel pnlInput = new JPanel(new BorderLayout());

        // part with input initialization - texts, sizes, tooltips
        JPanel pnlInputControl = new JPanel(new FlowLayout());
        JLabel lblInputTitle = new JLabel("Název: ");
        JTextField txtInputItem = new JTextField("", 15);
        txtInputItem.setToolTipText("Zadej název položky...");
        JButton btnInputAdd = new JButton("Přidat");
        JLabel lblPrice = new JLabel("Cena/ks: ");
        JTextField txtPrice = new JTextField("", 5);
        txtPrice.setToolTipText("Zadejte cenu za kus...");
        JLabel lblPieces = new JLabel("Počet kusů: ");
        JSpinner spPieces = new JSpinner(new SpinnerNumberModel(1, 1, 999, 1));
        Dimension prefSize = spPieces.getEditor().getPreferredSize();
        prefSize = new Dimension(50, prefSize.height);
        spPieces.getEditor().setPreferredSize(prefSize);

        // adding item from this part into panel
        addComponents(pnlInputControl, lblInputTitle, txtInputItem, lblPrice, txtPrice, lblPieces, spPieces, btnInputAdd);

        // initialization of error label - it should be red and not visible
        JLabel lblError = new JLabel();
        lblError.setForeground(Color.RED);
        lblError.setVisible(false);
        lblError.setHorizontalAlignment(SwingConstants.CENTER);

        pnlInput.add(pnlInputControl, BorderLayout.NORTH);
        pnlInput.add(lblError, BorderLayout.SOUTH);

        // adding table with model to UI
        JTable table = new JTable();
        model = new ShoppingCartTableModel();
        model.setShoppingCart(shoppingCart, cellCheckChangedListener);
        table.setModel(model);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(new JScrollPane(table)); // setting scrolling for table

        // bottom part of window with total and bought prices
        JPanel pnlOverview = new JPanel(new BorderLayout());
        JPanel pnlTotalPrice = new JPanel(new FlowLayout());
        JLabel lblTotalPrice = new JLabel("Celková cena: ");
        JLabel lblTotalPriceValue = new JLabel("0.00");
        addComponents(pnlTotalPrice, lblTotalPrice, lblTotalPriceValue);

        JPanel pnlBoughtPrice = new JPanel(new FlowLayout());
        JLabel lblBoughtPrice = new JLabel("Cena vybraných: ");
        lblBoughtPriceValue = new JLabel("0.00");
        addComponents(pnlBoughtPrice, lblBoughtPrice, lblBoughtPriceValue);

        pnlOverview.add(pnlTotalPrice, BorderLayout.WEST);
        pnlOverview.add(pnlBoughtPrice, BorderLayout.EAST);

        // adding all main parts into window
        add(pnlInput, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        add(pnlOverview, BorderLayout.SOUTH);

        // handling on button "Přidat" click
        btnInputAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String input = txtInputItem.getText().trim();
                // if input is not empty - do this
                if (!input.isEmpty()) {
                    try {
                        double pricePerPiece = Double.parseDouble(txtPrice.getText());
                        CartItem item = new CartItem(input, pricePerPiece, (int) spPieces.getValue());
                        shoppingCart.addItem(item);
                        model.setShoppingCart(shoppingCart, cellCheckChangedListener);
                        lblTotalPriceValue.setText(String.format("%.2f", shoppingCart.getTotalPrice()));
                        txtInputItem.setText("");
                        txtPrice.setText("");
                        spPieces.setValue(1);
                        lblError.setVisible(false);
                    } catch (NumberFormatException ex) {
                        // if user writes something else than number in price text field
                        lblError.setText("Cena má být číslo!");
                        lblError.setVisible(true);
                    }
                } else {
                    // in case of empty input and trying to add it to shopping cart
                    lblError.setText("Zkus nejdřív něco zadat!");
                    lblError.setVisible(true);
                }
            }
        });
    }

    /**
     * Method for adding multiple components into one container (for FlowLayout)
     * @param container into which container
     * @param components list of items
     */
    private void addComponents(JComponent container, JComponent... components) {
        for(JComponent component : components) {
            container.add(component);
        }
    }

    /**
     * Initialization of frame window
     */
    private void initFrame() {
        setTitle("Shopping Cart [A]");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640, 480);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Implementation of interface method - it's for update of bought price label when checkbox is clicked
     */
    @Override
    public void onPurchaseUpdate() {
        lblBoughtPriceValue.setText(String.format("%.2f", shoppingCart.getBoughtPrice()));
    }
}
