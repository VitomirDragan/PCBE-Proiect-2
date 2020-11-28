import java.util.concurrent.Semaphore;

public class Synchronize {
    private int numReadersEventSubscribers = 0;
    private final Semaphore mutexReadEventSubscriptions = new Semaphore(1, true);
    private final Semaphore mutexWriteEventSubscriptions = new Semaphore(1, true);
    private final Semaphore binaryEventQueueSemaphore = new Semaphore(1, true);

    public void startReadEventSubscription() {
        try {
            mutexReadEventSubscriptions.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        numReadersEventSubscribers++;
        if (numReadersEventSubscribers == 1) {
            try {
                mutexWriteEventSubscriptions.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        mutexReadEventSubscriptions.release();
    }

    public void endReadEventSubscriptions() {
        try {
            mutexReadEventSubscriptions.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        numReadersEventSubscribers--;
        if (numReadersEventSubscribers == 0)
            mutexWriteEventSubscriptions.release();
        mutexReadEventSubscriptions.release();
    }

    public void startWriteEventSubscriptions() {
        try {
            mutexWriteEventSubscriptions.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void endWriteEventSubscriptions() {
        mutexWriteEventSubscriptions.release();
    }

    public void lockEventQueue() {
        try {
            binaryEventQueueSemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void unlockEventQueue() {
        binaryEventQueueSemaphore.release();
    }
}
