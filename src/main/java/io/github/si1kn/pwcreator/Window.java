package io.github.si1kn.pwcreator;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
        final List<String> toBeConvertedLater = new ArrayList<>();

        for (int i = 1; i < 41; i++) {
            toBeConvertedLater.add(i + "");
        }

        //construct components
        final JButton createpassword = new JButton("Create password");
        final JLabel jcomp4 = new JLabel(" Pick number for password size:");

        this.jcomp3 = new JComboBox<>(toBeConvertedLater.toArray());
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

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    private String createPassword(boolean capitals, boolean numbers, int length) {
        final StringBuilder builder = new StringBuilder();
        final Random r = new Random();

        for (int i = 0; i < length; i++) {
            char c = (char) (r.nextInt(26) + 'a');

            if (ThreadLocalRandom.current().nextBoolean() && ThreadLocalRandom.current().nextBoolean() && numbers)
                builder.append(ThreadLocalRandom.current().nextInt(1, 8 + 1));
            else
                builder.append(ThreadLocalRandom.current().nextBoolean() && ThreadLocalRandom.current().nextBoolean() && capitals ? String.valueOf(c).toUpperCase(Locale.ROOT) : c);

        }

        return builder.toString();
    }

    private void actionPerformed(ActionEvent event) {
        this.createdPasswordHere.setText(this.createPassword(this.captial.isSelected(), this.numbers.isSelected(), this.jcomp3.getSelectedIndex() + 1));
    }
}