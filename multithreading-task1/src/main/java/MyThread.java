class MyThread extends Thread {

    public MyThread(ThreadGroup threadGroup, String s) {
        super(threadGroup,s);
    }

    @Override
    public void run() {
        try {
            while(!isInterrupted()) {
                Thread.sleep(2500);
                System.out.printf("Я поток %s. Всем привет!\n",getName());
            }
        } catch (InterruptedException err) {
            System.out.printf("Thread %s is currently being interrupted\n",getName());
        } finally{
            System.out.printf("Поток %s завершен\n", getName());
        }
    }

}