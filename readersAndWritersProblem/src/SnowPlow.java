public class SnowPlow extends Thread{
    public SnowPlow(){
        super(new Runnable() {
            @Override
            public void run() {

                Repository repo = Repository.getRepo();

                while (repo.getCars('s').size() != 0){}

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repo.stopSnowPlow();
            }
        });
    }
}
