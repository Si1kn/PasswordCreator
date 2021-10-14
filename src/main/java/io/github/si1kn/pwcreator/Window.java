package io.github.si1kn.pwcreator;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Window extends JFrame {


    private final JTextArea createdPasswordHere;
    private final JComboBox jcomp3;
    private final JCheckBox numbers;
    private final JCheckBox captial;

    public Window(String title) {

        setTitle(title);
        //construct preComponents
        String[] jcomp3Items = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};

        //construct components
        JButton createpassword = new JButton("Create password");
        createdPasswordHere = new JTextArea(5, 5);
        jcomp3 = new JComboBox(jcomp3Items);
        JLabel jcomp4 = new JLabel(" Pick number for password size:");
        numbers = new JCheckBox("Do you want numbers in password?");
        captial = new JCheckBox("Do you want Captial letters in your password?");

        //adjust size and set layout
        setPreferredSize(new Dimension(944, 574));
        setLayout(null);


        createpassword.addActionListener(m -> {
            createdPasswordHere.setText(createPassword(captial.isSelected(), numbers.isSelected(), jcomp3.getSelectedIndex()));
        });
        //add components
        add(createpassword);
        add(createdPasswordHere);
        add(jcomp3);
        add(jcomp4);
        add(numbers);
        add(captial);

        //set component bounds (only needed by Absolute Positioning)
        createpassword.setBounds(360, 470, 230, 20);
        createdPasswordHere.setBounds(105, 500, 770, 35);
        jcomp3.setBounds(430, 415, 100, 25);
        jcomp4.setBounds(235, 415, 195, 25);
        numbers.setBounds(365, 380, 230, 20);
        captial.setBounds(335, 350, 290, 25);
    }


    public static void main(String[] args) {
        JFrame frame = new Window("Title");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.pack();
        frame.setVisible(true);
    }


    private String createPassword(boolean capitals, boolean numbers, int length) {
        final StringBuilder builder = new StringBuilder();

        final Random r = new Random();

        for (int i = 1; i < length; i++) {
            char c = (char) (r.nextInt(26) + 'a');

            String s = ThreadLocalRandom.current().nextBoolean() && ThreadLocalRandom.current().nextBoolean() && numbers ? "" + ThreadLocalRandom.current().nextInt(0, 10 + 1) : "";
            if (ThreadLocalRandom.current().nextBoolean() && ThreadLocalRandom.current().nextBoolean() && capitals) {
                builder.append(String.valueOf(c).toUpperCase(Locale.ROOT) + s);
            } else {
                builder.append(c + s.trim());
            }
        }

        return builder.toString();
    }
}
