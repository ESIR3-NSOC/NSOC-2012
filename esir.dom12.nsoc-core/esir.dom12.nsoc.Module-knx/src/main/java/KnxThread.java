/**
 * Created with IntelliJ IDEA.
 * User: guillaumelefloch
 * Date: 1/21/13
 * Time: 11:38 AM
 * To change this template use File | Settings | File Templates.
 */
class KnxThread extends Thread{

    /**
     * Variable global
     */
    private boolean stopped = false;
    private long delay = 200000;


    public void run() {
        while(!stopped) {
                getData();

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isStopped(){
        return stopped;
    }


    public void getData(){

    }
}
