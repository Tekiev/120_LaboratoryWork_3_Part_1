package DEV120_3_1_Tekiev;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Calculator {
    private JFrame frame;
    private JTextField field;
    private String number1 = "", number2 = "", operation = "";
    public Calculator() {
        frame = new JFrame();
    }
    public void init() {

        frame.setTitle("Simple Calculator");
        frame.setSize(500, 500);
        frame.add(getTextField(), BorderLayout.NORTH);
        frame.add(getButtonsGroup(), BorderLayout.CENTER);
        frame.add(getResult(), BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    private Container getTextField() {
        JPanel panelTextField = new JPanel();
        GridLayout layout = new GridLayout();
        panelTextField.setLayout(layout);
        field = new JTextField();
        field.setHorizontalAlignment(JTextField.RIGHT);
        field.setFont(new Font("CalcFontTextField", Font.PLAIN, 45));
        field.setEditable(false);
        panelTextField.add(field);
        field.setText("0");
        return panelTextField;
    }
    private Container getButtonsGroup() {
        JPanel panel = new JPanel();
        GridLayout layout = new GridLayout(4, 4);
        panel.setLayout(layout);
        ArrayList<JButton> jButtons = new ArrayList<>() {{
            add(new JButton("7"));
            add(new JButton("8"));
            add(new JButton("9"));
            add(new JButton("+"));
            add(new JButton("4"));
            add(new JButton("5"));
            add(new JButton("6"));
            add(new JButton("-"));
            add(new JButton("1"));
            add(new JButton("2"));
            add(new JButton("3"));
            add(new JButton("*"));
            add(new JButton("0"));
            add(new JButton("."));
            add(new JButton("C"));
            add(new JButton("/"));
        }};
        for (JButton jButton : jButtons) {
            jButton.setFont(new Font("CalcFontButton", Font.PLAIN, 25));
            jButton.addActionListener(new Listener());
        }
        jButtons.forEach(panel::add);
        return panel;
    }
    private Container getResult() {

        JPanel panelResult = new JPanel();
        GridLayout layout = new GridLayout();
        panelResult.setLayout(layout);
        JButton result = new JButton("=");
        result.setFont(new Font("CalcFontResult", Font.PLAIN, 45));
        result.addActionListener(new Listener());
        panelResult.add(result);
        return panelResult;
    }
    private class Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String s = e.getActionCommand();
            if ((s.charAt(0) >= '0' && s.charAt(0) <= '9') || s.charAt(0) == '.') {
                if (operation.equals("")) {
                    number1 = number1 + s;
                } else {
                    number2 = number2 + s;
                }
                field.setText(number1 + operation + number2);
            } else if (s.charAt(0) == 'C') {
                number1 = number2 = operation = "";
                field.setText("0");
            } else if (s.charAt(0) == '=') {
                if (!(number1.isEmpty() || number2.isEmpty())) {

                    Double res = 0.0;
                    switch (operation) {
                        case "+": res = Double.parseDouble(number1) + Double.parseDouble(number2);
                        break;
                        case "-": res = Double.parseDouble(number1) - Double.parseDouble(number2);
                            break;
                        case "/": res = Double.parseDouble(number1) / Double.parseDouble(number2);
                            break;
                        case "*": res = Double.parseDouble(number1) * Double.parseDouble(number2);
                            break;
                        default:
                            break;
                    }
                    if (res % 1 == 0) {
                        number1 =String.valueOf(res.intValue());
                    } else {
                        number1 = String.valueOf(res);
                    }
                    field.setText(number1);
                    operation = number2 = "";
                }
            } else {
                if (operation.equals("")) {
                    operation = s;
                }
                field.setText(number1 + operation + number2);
            }
        }
    }
}
