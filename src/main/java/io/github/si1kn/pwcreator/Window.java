package io.github.si1kn.pwcreator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Window extends JFrame {

    private final JTextArea createdPasswordHere;
    private final JComboBox<?> jcomp3;
    private final JCheckBox numbers;
    private final JCheckBox captial;

    public Window(String title) {
        this.setTitle(title);
        this.setLayout(null);
        this.setPreferredSize(new Dimension(944, 574));

        //construct preComponents
        final String[] jcomp3Items = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};

        //construct components
        final JButton createpassword = new JButton("Create password");
        final JLabel jcomp4 = new JLabel(" Pick number for password size:");

        this.jcomp3 = new JComboBox<>(jcomp3Items);
        this.createdPasswordHere = new JTextArea(5, 5);
        this.numbers = new JCheckBox("Do you want numbers in password?");
        this.captial = new JCheckBox("Do you want Captial letters in your password?");

        createpassword.addActionListener(this::actionPerformed);

        //add components
        this.add(createpassword);
        this.add(this.createdPasswordHere);
        this.add(this.jcomp3);
        this.add(jcomp4);
        this.add(this.numbers);
        this.add(this.captial);

        //set component bounds (only needed by Absolute Positioning)
        createpassword.setBounds(360, 470, 230, 20);
        this.createdPasswordHere.setBounds(105, 500, 770, 35);
        this.jcomp3.setBounds(430, 415, 100, 25);
        jcomp4.setBounds(235, 415, 195, 25);
        this.numbers.setBounds(365, 380, 230, 20);
        this.captial.setBounds(335, 350, 290, 25);
    }

    public static void main(String[] args) {
        final JFrame frame = new Window("Password Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    private String createPassword(boolean capitals, boolean numbers, int length) {
        final StringBuilder builder = new StringBuilder();

        final Random random = new Random();

        for (int i = 1; i < length; i++) {
            final char randomChar = (char) (random.nextInt(26) + 'a');

            final String randomString = ThreadLocalRandom.current().nextBoolean() && ThreadLocalRandom.current().nextBoolean() && numbers ? "" + ThreadLocalRandom.current().nextInt(0, 10 + 1) : "";

            if (ThreadLocalRandom.current().nextBoolean() && ThreadLocalRandom.current().nextBoolean() && capitals) {
                builder.append(String.valueOf(randomChar).toUpperCase(Locale.ROOT)).append(randomString);
            } else {
                builder.append(randomChar).append(randomString.trim());
            }
        }

        return builder.toString();
    }

    private void actionPerformed(ActionEvent event) {
        this.createdPasswordHere.setText(this.createPassword(this.captial.isSelected(), this.numbers.isSelected(), this.jcomp3.getSelectedIndex()));
    }
}
