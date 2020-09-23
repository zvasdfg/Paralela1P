package Classes;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Semaphore;


public class Frame extends JFrame {

    private boolean flag = false;

    public JButton startButton = new JButton("Start");
    public JButton ADDLectorButton = new JButton("Agregar Lector");
    public JButton ADDEscritorButton = new JButton("Agregar Escritor");
    public JButton KillLectorButton = new JButton("Matar Lector");
    public JButton KillEscritorButton = new JButton("Matar Escritor");

    public static JProgressBar bar[] = new JProgressBar[5];

    static Semaphore permissions = new Semaphore(1);

    public static JLabel VariableGlobalLabel;
    public static int VariableGlobalValor = 5;

    private int numLectores = 0;
    private int numEscritores = 0;
    private int numLectoresMuertos = 0;
    private int numEscritoresMuertos = 0;

    private Random random = new Random();

    public JLabel[] LectoresLabelARR = new JLabel[5];
    public JLabel[] EscritoresLabelARR = new JLabel[5];
    private JLabel LectorLabel_0;
    private JLabel LectorLabel_1;
    private JLabel LectorLabel_2;
    private JLabel LectorLabel_3;
    private JLabel LectorLabel_4;

    private JLabel EscritorLabel_0;
    private JLabel EscritorLabel_1;
    private JLabel EscritorLabel_2;
    private JLabel EscritorLabel_3;
    private JLabel EscritorLabel_4;

    Lector[] LEC;
    Escritor[] ESC;


    public Frame(String title) {
        super(title);

        //countLabel1.setFont(new Font("serif", Font.BOLD, 28));

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.NONE;

        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 1;

        LectorLabel_0 = new JLabel("Lector: 0");
        LectorLabel_0.setBounds(191, 4, 169, 14);
        add(LectorLabel_0, gc);

        gc.gridy = 1;
        LectorLabel_1 = new JLabel("Lector: 1");
        LectorLabel_1.setBounds(191, 4, 169, 14);
        add(LectorLabel_1, gc);

        gc.gridy = 2;
        LectorLabel_2 = new JLabel("Lector: 2");
        LectorLabel_2.setBounds(191, 4, 169, 14);
        add(LectorLabel_2, gc);

        gc.gridy = 3;
        LectorLabel_3 = new JLabel("Lector: 3");
        LectorLabel_3.setBounds(191, 4, 169, 14);
        add(LectorLabel_3, gc);

        gc.gridy = 4;
        LectorLabel_4 = new JLabel("Lector: 4");
        LectorLabel_4.setBounds(191, 4, 169, 14);
        add(LectorLabel_4, gc);




        gc.gridx = 1;
        gc.gridy = 0;

        VariableGlobalLabel = new JLabel("Variable Global: " +  VariableGlobalValor);
        VariableGlobalLabel.setBounds(191, 4, 169, 14);
        add(VariableGlobalLabel, gc);

        gc.gridy = 1;
        add(startButton, gc);

        gc.gridy = 2;
        add(ADDLectorButton, gc);

        gc.gridy = 3;
        add(ADDEscritorButton, gc);

        gc.gridy = 4;
        add(KillEscritorButton, gc);

        gc.gridy = 5;
        add(KillLectorButton, gc);



        gc.gridx = 2;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 1;

        EscritorLabel_0 = new JLabel("Escritor: 0");
        EscritorLabel_0.setBounds(191, 4, 169, 14);
        add(EscritorLabel_0, gc);

        gc.gridy = 1;
        EscritorLabel_1 = new JLabel("Escritor: 1");
        EscritorLabel_1.setBounds(191, 4, 169, 14);
        add(EscritorLabel_1, gc);

        gc.gridy = 2;
        EscritorLabel_2 = new JLabel("Escritor: 2");
        EscritorLabel_2.setBounds(191, 4, 169, 14);
        add(EscritorLabel_2, gc);

        gc.gridy = 3;
        EscritorLabel_3 = new JLabel("Escritor: 3");
        EscritorLabel_3.setBounds(191, 4, 169, 14);
        add(EscritorLabel_3, gc);

        gc.gridy = 4;
        EscritorLabel_4 = new JLabel("Escritor: 4");
        EscritorLabel_4.setBounds(191, 4, 169, 14);
        add(EscritorLabel_4, gc);



        LectoresLabelARR[0] = LectorLabel_0;
        LectoresLabelARR[1] = LectorLabel_1;
        LectoresLabelARR[2] = LectorLabel_2;
        LectoresLabelARR[3] = LectorLabel_3;
        LectoresLabelARR[4] = LectorLabel_4;

        EscritoresLabelARR[0] = EscritorLabel_0;
        EscritoresLabelARR[1] = EscritorLabel_1;
        EscritoresLabelARR[2] = EscritorLabel_2;
        EscritoresLabelARR[3] = EscritorLabel_3;
        EscritoresLabelARR[4] = EscritorLabel_4;

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                start();
            }
        });

        ADDLectorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ADDLector();
            }
        });

        ADDEscritorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ADDEscritor();
            }
        });

        KillEscritorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                KillEscritor();
            }
        });
        KillLectorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                KillLector();
            }
        });

        setSize(1500, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }



    public void start() {
        for (int i = 0; i <= 4; i++){
            LectoresLabelARR[i].setForeground(Color.LIGHT_GRAY);
            EscritoresLabelARR[i].setForeground(Color.LIGHT_GRAY);
        }
        ESC = new Escritor[5];
        LEC = new Lector[5];
        flag = true;
    }

    public void ADDLector(){
        if (flag && this.numLectores <= 4){
            LEC[this.numLectores] = new Lector(this.numLectores, this.LectoresLabelARR[numLectores]);
            LEC[this.numLectores].start();
            this.numLectores++;
        }
    }

    public void ADDEscritor(){
        if (flag && this.numEscritores <= 4){
            ESC[this.numEscritores] = new Escritor(this.numEscritores, this.EscritoresLabelARR[numEscritores], this.VariableGlobalLabel, random.nextInt(9999));
            ESC[this.numEscritores].start();
            this.numEscritores++;
        }
    }

    public void KillEscritor(){
        if (flag && this.numEscritores > 0 && this.numEscritores <= 4) {
            System.out.println("Matar escritor: " + this.numEscritoresMuertos);
            ESC[this.numEscritoresMuertos].terminate();
            this.numEscritoresMuertos++;
        }
    }

    public void KillLector(){
        if (flag && this.numLectores > 0 &&  this.numLectoresMuertos <= 4) {
            System.out.println("Matar lector: " + this.numLectoresMuertos);
            LEC[this.numLectoresMuertos].terminate();
            this.numLectoresMuertos++;
        }
    }

}