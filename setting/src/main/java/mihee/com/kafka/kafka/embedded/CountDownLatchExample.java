package mihee.com.kafka.kafka.embedded;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public static void main(String[] arsg) throws InterruptedException{
        CountDownLatch latch = new CountDownLatch(3);
        new Worker(latch, "Worker1").start();
        new Worker(latch, "Worker2").start();
        new Worker(latch, "Worker3").start();

        latch.await();
        System.out.println("All workers have finished their work!");

    }

    static class Worker extends Thread {
        private final CountDownLatch latch;

        public Worker (CountDownLatch latch, String name) {
            super(name);
            this.latch = latch;
        }

        @Override
        public void run () {
            System.out.println(getName() + " is working...");
            latch.countDown();
        }
    }
}


//public class CountDownLatchExample {
//    public static void main(String[] args) throws InterruptedException {
//        CountDownLatch latch = new CountDownLatch(3);
//
//        // 각 스레드가 일을 처리하고 마칠 때마다 countDown() 호출
//        new Worker(latch, "Worker1").start();
//        new Worker(latch, "Worker2").start();
//        new Worker(latch, "Worker3").start();
//
//        // 메인 스레드가 여기서 대기하다가 latch의 카운트가 0이 되면 진행
//        latch.await();
//
//        System.out.println("All workers have finished their work!");
//    }

//
//        @Override
//        public void run() {
//            System.out.println(getName() + " is working...");
//            // 작업을 처리한 후 countDown() 호출
//            latch.countDown();
//        }
//    }
//}