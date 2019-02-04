public class Car extends Thread{
    public Car(){
        super(new Runnable() {
            @Override
            public void run() {
                Repository repo = Repository.getRepo();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repo.stopCar();
            }
        });
    }
}
