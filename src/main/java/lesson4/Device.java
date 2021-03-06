package lesson4;

public abstract class Device {
    private Object resource = new Object();
    private String actionName;
    private static int taskNumber = 0;

    public Device(String actionName) {
        this.actionName = actionName;
    }

    void work(int countPages) {
        final int currentTask = taskNumber++;
        new Thread(() -> {
            synchronized (resource) {
                for (int i = 1; i <= countPages; i++) {
                    System.out.format("Задача %s: %s %s страниц\n",
                            currentTask, actionName, i);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
