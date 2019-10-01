package cz.uhk.fim.shoppingcart.gui;

import cz.uhk.fim.shoppingcart.model.CartItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {

    private List<CartItem> itemList = new ArrayList<>();
    private JTextArea areaContent;

    public MainFrame() {
        initFrame();
        initUi();
    }

    private void initUi() {
        JPanel pnlInput = new JPanel(new BorderLayout());

        JLabel lblInputTitle = new JLabel("Název:");
        JTextField txtInputItem = new JTextField();
        txtInputItem.setToolTipText("Zadej název položky...");
        JButton btnInputAdd = new JButton("Přidat");

//        JTextArea areaContent = new JTextArea();
        areaContent = new JTextArea();
        areaContent.setEditable(false);

        pnlInput.add(lblInputTitle, BorderLayout.WEST);
        pnlInput.add(txtInputItem, BorderLayout.CENTER);
        pnlInput.add(btnInputAdd, BorderLayout.EAST);

        add(pnlInput, BorderLayout.NORTH);
        add(areaContent, BorderLayout.CENTER);

        btnInputAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                areaContent.setText(txtInputItem.getText().trim());
                itemList.add(new CartItem(txtInputItem.getText().trim()));
                areaContent.setText(prepareContent(itemList));
                prepareContent(itemList, areaContent);
                txtInputItem.setText("");
            }
        });
    }

    private void initFrame() {
        setTitle("Shopping Cart [A]");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640, 480);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private String prepareContent(List<CartItem> list) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).getTitle());
            if (i != list.size() - 1) {
                sb.append(", \n");
            }
        }
        return sb.toString();
    }

    private void prepareContent(List<CartItem> list, JTextArea area) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).getTitle());
            if (i != list.size() - 1) {
                sb.append(", \n");
            }
        }
        area.setText(sb.toString());
    }

    private void prepareContentGlobal(List<CartItem> list) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).getTitle());
            if (i != list.size() - 1) {
                sb.append(", \n");
            }
        }
        areaContent.setText(sb.toString());
    }

}
