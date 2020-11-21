import java.util.concurrent.Semaphore;

public class Synchronize {
    private int numReadersPosts = 0;
    private Semaphore mutexPosts = new Semaphore(1, true);
    private Semaphore wlockMessages = new Semaphore(1, true);
    private Semaphore wlockPosts = new Semaphore(1, true);
    private Semaphore wlockSubscribers = new Semaphore(1, true);
    private Semaphore binarySemaphore = new Semaphore(1, true);


    public void startWriteMessages() {
        try {
            wlockMessages.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void endWriteMessages() {
        wlockMessages.release();
    }

    public void startReadPosts() {
        try {
            mutexPosts.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        numReadersPosts++;
        if (numReadersPosts == 1) {
            try {
                wlockPosts.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        mutexPosts.release();
    }

    public void endReadPosts() {
        try {
            mutexPosts.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        numReadersPosts--;
        if (numReadersPosts == 0)
            wlockPosts.release();
        mutexPosts.release();
    }

    public void startWritePosts() {
        try {
            wlockPosts.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void endWritePosts() {
        wlockPosts.release();
    }


    public void startWriteSubscribers() {
        try {
            wlockSubscribers.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void endWriteSubscribers() {
        wlockSubscribers.release();
    }

    public void lock() {
        try {
            binarySemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void unlock() {
        binarySemaphore.release();
    }
}
