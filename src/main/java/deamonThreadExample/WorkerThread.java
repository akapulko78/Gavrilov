package deamonThreadExample;


class WorkerThread extends Thread {

    public WorkerThread() {
        // When false, (i.e. when it a user thread),
        // the Worker thread continues to run.
        // When true, (i.e. when it a daemon thread),
        // the Worker thread terminates when the main
        // thread terminates.
        setDaemon(false);
    }

    public void run() {
        int count = 0;

        while (true) {
            System.out.println("Hello from Worker "+count++);

            try {
                sleep(5000);
            } catch (InterruptedException e) {
                // handle exception here
            }

        }
    }
}
