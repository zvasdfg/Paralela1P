package Classes;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Semaphore;


public class Frame extends JFrame {
    //public static JLabel countLabel1 = new JLabel("0");
    public JButton startButton = new JButton("Start");
    public static JProgressBar bar[] = new JProgressBar[5];
    private BufferedImage image;
    private JLabel picLabel;

    static final int NumFilosofos = 5;
    Philosopher[] Filosofos = new Philosopher[NumFilosofos];
    static Semaphore permissions = new Semaphore(2);
    static boolean [] FilosofoComiendo = {false, false, false, false, false};
    JLabel[] comments = new JLabel[5];
    private JLabel lblNewLabel_0;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_3;
    private JLabel lblNewLabel_4;


    public Frame(String title) {
        super(title);
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.NONE;

        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = .2;
        try {
            image = ImageIO.read(new File("img/TalesdeMileto.jpg"));
        }
        catch (IOException e) {
            System.out.println(e);
        }
        picLabel = new JLabel(new ImageIcon(image));
        picLabel.setIcon(new ImageIcon(new ImageIcon("img/TalesdeMileto.jpg").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
        add(picLabel);



        gc.gridx = 1;
        try {
            image = ImageIO.read(new File("img/TalesdeMileto.jpg"));
        }
        catch (IOException e) {
            System.out.println(e);
        }
        picLabel = new JLabel(new ImageIcon(image));
        picLabel.setIcon(new ImageIcon(new ImageIcon("img/TalesdeMileto.jpg").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
        add(picLabel);




        gc.gridx = 2;
        try {
            image = ImageIO.read(new File("img/TalesdeMileto.jpg"));
        }
        catch (IOException e) {
            System.out.println(e);
        }
        picLabel = new JLabel(new ImageIcon(image));
        picLabel.setIcon(new ImageIcon(new ImageIcon("img/TalesdeMileto.jpg").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
        add(picLabel);


        gc.gridx = 3;
        try {
            image = ImageIO.read(new File("img/TalesdeMileto.jpg"));
        }
        catch (IOException e) {
            System.out.println(e);
        }
        picLabel = new JLabel(new ImageIcon(image));
        picLabel.setIcon(new ImageIcon(new ImageIcon("img/TalesdeMileto.jpg").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
        add(picLabel);




        gc.gridx = 4;
        try {
            image = ImageIO.read(new File("img/TalesdeMileto.jpg"));
        }
        catch (IOException e) {
            System.out.println(e);
        }
        picLabel = new JLabel(new ImageIcon(image));
        picLabel.setIcon(new ImageIcon(new ImageIcon("img/TalesdeMileto.jpg").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
        add(picLabel);


        gc.gridx = 0;
        gc.gridy = 1;
        lblNewLabel_0 = new JLabel("Filosofo: 0");
        lblNewLabel_0.setBounds(191, 4, 169, 14);
        add(lblNewLabel_0, gc);

        gc.gridx = 1;
        lblNewLabel_1 = new JLabel("Filosofo: 1");
        lblNewLabel_1.setBounds(191, 4, 169, 14);
        add(lblNewLabel_1, gc);

        gc.gridx = 2;
        lblNewLabel_2 = new JLabel("Filosofo: 2");
        lblNewLabel_2.setBounds(191, 4, 169, 14);
        add(lblNewLabel_2, gc);

        gc.gridx = 3;
        lblNewLabel_3 = new JLabel("Filosofo: 3");
        lblNewLabel_3.setBounds(191, 4, 169, 14);
        add(lblNewLabel_3, gc);

        gc.gridx = 4;
        lblNewLabel_4 = new JLabel("Filosofo: 4");
        lblNewLabel_4.setBounds(191, 4, 169, 14);
        add(lblNewLabel_4, gc);



        gc.weighty = .5;
        gc.gridx = 0;
        gc.gridy = 2;
        bar[0] = new JProgressBar();
        bar[0].setValue(0);
        bar[0].setStringPainted(true);
        add(bar[0], gc);

        gc.gridx = 1;
        bar[1] = new JProgressBar();
        bar[1].setValue(0);
        bar[1].setStringPainted(true);
        add(bar[1], gc);

        gc.gridx = 2;
        bar[2] = new JProgressBar();
        bar[2].setValue(0);
        bar[2].setStringPainted(true);
        add(bar[2], gc);

        gc.gridx = 3;
        bar[3] = new JProgressBar();
        bar[3].setValue(0);
        bar[3].setStringPainted(true);
        add(bar[3], gc);

        gc.gridx = 4;
        bar[4] = new JProgressBar();
        bar[4].setValue(0);
        bar[4].setStringPainted(true);
        add(bar[4], gc);


//        gc.gridx = 0;
//        gc.gridy = 1;
//        gc.weightx = 1;
//        gc.weighty = 1;
//        add(ConsuLabel, gc);
//
//        gc.gridx = 1;
//        gc.gridy = 1;
//        gc.weightx = 1;
//        gc.weighty = 1;
//        spinnerConsu.setBounds(100,100,50,30);
//        add(spinnerConsu, gc);


        gc.gridx = 2;
        gc.gridy = 3;
        gc.weightx = 1;
        gc.weighty = 1;
        add(startButton, gc);

        comments[0] = lblNewLabel_0;
        comments[1] = lblNewLabel_1;
        comments[2] = lblNewLabel_2;
        comments[3] = lblNewLabel_3;
        comments[4] = lblNewLabel_4;

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                start();
            }
        });

        setSize(1500, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }



    public void start() {

        Fork[] forks = new Fork[NumFilosofos];
        for (int i = 0; i < NumFilosofos; i++) {
            forks[i] = new Fork(i);
        }
        for (int i = 0; i < NumFilosofos; i++) {
            Filosofos[i] = new Philosopher(i, forks[(i + 1) % NumFilosofos], forks[i], comments[i], bar[i]);
            new Thread(Filosofos[i]).start();
        }
    }


    public synchronized static boolean LeftPhilosofer(int id) {
        return Frame.FilosofoComiendo[(id + 1) % Frame.NumFilosofos];
    }

    public synchronized static boolean RightPhilosofer(int id) {

        if (id == 0)
            return Frame.FilosofoComiendo[4];
        else
            return Frame.FilosofoComiendo[(id - 1) % Frame.NumFilosofos];
    }


}