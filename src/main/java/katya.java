/**
 * Created by Beta on 10/11/14.
 */
public class katya {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 2;
                int flag = 0;
                int A = 1;
                while(true){
                    try {
                        Thread.sleep(600);
                        count--;
                        if(count==0){
                            count=2;
                            if(flag==0){
                                if(A==1){
                                    flag = 1;
                                    A<<=1;
                                    continue;
                                }
                                else{
                                    A>>=1;
                                    continue;
                                }
                            }else{
                                if(A==128){
                                    flag = 0;
                                    A>>=1;
                                    continue;
                                }else{
                                    A<<=1;
                                    continue;
                                }
                            }

                        }
                        System.out.println(A);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        });
        t.start();
        Thread.sleep(1000000000);
        System.out.println();
    }
}
