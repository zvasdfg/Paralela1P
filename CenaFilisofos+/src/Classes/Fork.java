package Classes;

import java.util.concurrent.locks.ReentrantLock;

public class Fork extends ReentrantLock{

	int id;

	public Fork(int id) {
		super();
		this.id = id;
	}

	public synchronized void pickUp(){
		this.lock();
	}

	public synchronized void putDown(){
		this.unlock();
	}
}
