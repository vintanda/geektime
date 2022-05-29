package week04;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class WaitDemo {

    public static void main(String[] args) {

        long start=System.currentTimeMillis();

        // 在这里创建一个线程或线程池
        System.out.println("这是主线程:begin!");
        MyThread thread = new MyThread();
        FutureTask<Integer> futureTask = new FutureTask<>(thread);

        // 异步执行 下面方法
        new Thread(futureTask).start();

        int result = 0; //这是得到的返回值
        try {
            result = futureTask.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程
    }

}
