package controller;

import java.util.concurrent.Semaphore;

public class CruzarPortaThread extends Thread {

	private final int corredorSize = 10; // em metros
	private final int velocidadeMin = 4; // em metros/segundos
	private final int velocidadeMax = 6; // em metros/segundos

	private Semaphore semaforo;

	public CruzarPortaThread(Semaphore semaforo) {
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		try {
			andar(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			this.semaforo.acquire();
			cruzarPorta();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			this.semaforo.release();
		}
	}

	private void andar(int andado) throws InterruptedException {
		int random = (int) (Math.random() * velocidadeMax - velocidadeMin) + velocidadeMin;

		andado += random;

		System.out.println("#" + (int) getId() + " está andando a " + random + " m/s");

		sleep(random * 1000);

		if (andado < this.corredorSize) {
			andar(andado);
		}
	}

	private void cruzarPorta() throws InterruptedException {
		int random = (int) (Math.random() * 2 - 1) + 1;
		System.out.println("#" + (int) getId() + " está atravessando a porta");
		sleep(random * 1000);
		System.out.println("#" + (int) getId() + " atravessou a porta em " + random + " segundo(s)");

	}
}
