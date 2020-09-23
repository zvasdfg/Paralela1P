package Classes;
import java.awt.Color;
import java.util.Random;
import javax.swing.*;

public class Philosopher implements Runnable {

	public int id;
	Fork leftFork, rightFork;
	private JLabel  comments;
	private Random random = new Random();
	int maxSleep = 2000;
	int PorcentageMeals = 0;
	JProgressBar bar;

	public Philosopher(int id, Fork leftFork, Fork rightFork, JLabel comments, JProgressBar bar) {
		super();
		this.id = id;
		this.leftFork = leftFork;
		this.rightFork = rightFork;
		this.comments = comments;
		this.bar = bar;
	}

	public void run() {
		while ( PorcentageMeals != 100) {
			try {
				this.Think();
				this.Hungry();
				this.Eat();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		comments.setText("Filosofo: " + id + " Se retira de la mesa");
		comments.setForeground(Color.BLACK);
	}

	private void Think() throws InterruptedException {
		comments.setText("Filosofo: " + id + " Pensando");
		comments.setForeground(Color.BLUE);
		comments.setForeground(Color.BLACK);
		Thread.sleep(random.nextInt(maxSleep));
	}

	public void Hungry() throws InterruptedException {

		while (Frame.LeftPhilosofer(id) || Frame.RightPhilosofer(id)) {
			Thread.sleep(random.nextInt(10));
		}

		Frame.permissions.acquire();
		leftFork.pickUp();
		rightFork.pickUp();
		Frame.FilosofoComiendo[id] = true;
	}

	private void Eat() throws InterruptedException {
		
		comments.setText("Filosofo: " + id + " comiendo");
		PorcentageMeals++;
		bar.setValue( PorcentageMeals );
		comments.setForeground(Color.MAGENTA);
		comments.setForeground(Color.RED);

		Thread.sleep( random.nextInt(maxSleep) );
		leftFork.putDown();
		rightFork.putDown();
		Frame.permissions.release();
		Frame.FilosofoComiendo[id] = false;
	}
}
