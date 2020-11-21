
Java 消息队列，并发编程

ArrayBlockingQueue：基于数组实现的一个阻塞队列，在创建ArrayBlockingQueue对象时必须制定容量大小。并且可以指定公平性与非公平性，默认情况下为非公平的，
                    即不保证等待时间最长的队列最优先能够访问队列。

LinkedBlockingQueue：基于链表实现的一个阻塞队列，在创建LinkedBlockingQueue对象时如果不指定容量大小，则默认大小为Integer.MAX_VALUE。

PriorityBlockingQueue：以上2种队列都是先进先出队列，而PriorityBlockingQueue却不是，它会按照元素的优先级对元素进行排序，按照优先级顺序出队，
                        每次出队的元素都是优先级最高的元素。注意，此阻塞队列为无界阻塞队列，即容量没有上限（通过源码就可以知道，它没有容器满的信号标志），前面2种都是有界队列。

DelayQueue：基于PriorityQueue，一种延时阻塞队列，DelayQueue中的元素只有当其指定的延迟时间到了，才能够从队列中获取到该元素。DelayQueue也是一个无界队列，因此往队列中插入数据的操作（生产者）永远不会被阻塞，而只有获取数据的操作（消费者）才会被阻塞。


threadPool: java 数据池实现
1、线程池是在初始化是就启动多个线程，然后在线程中的run（）方法中用一个while循环去不断的轮询任务队列，如果有任务就执行，没有任务就wait
2、若有任务添加进任务队列，就唤醒线程
3、在许多线程池的实现中使用的都是runnable接口，但是这个实现的接口可以自己定义，而且在线程池中的线程在run（）方法中执行的任务中的方法也只是一个普通的方法，没有什么特别，（众所周知，一个类实现了runnable接口，但是没有使用start（）方法来启动线程则在执行run（）方法时这只是一个普通的方法）完全可以替换成一个你自己定义的接口。
  为什么要定义接口，因为在线程池中的线程中需要知道怎么来执行任务类中的任务，所以需要定义一个接口来让线程通过定义接口变量来调用接口中的方法，这个方法对于线程来说是已知的


semaple:[Java并发编程] 用 信号量(Semaphore) 实现一个消息池

/获取一个许可
public void acquire() throws InterruptedException {  }
//获取permits个许可
public void acquire(int permits) throws InterruptedException { }
 //释放一个许可
public void release() { }
//释放permits个许可
public void release(int permits) { }
//尝试获取一个许可，若获取成功，则立即返回true，若获取失败，则立即返回false
public boolean tryAcquire() { };
//尝试获取一个许可，若在指定的时间内获取成功，则立即返回true，否则则立即返回false
public boolean tryAcquire(long timeout, TimeUnit unit) throws InterruptedException { };
   //尝试获取permits个许可，若获取成功，则立即返回true，若获取失败，则立即返回false
public boolean tryAcquire(int permits) { };
//尝试获取permits个许可，若在指定的时间内获取成功，则立即返回true，否则则立即返回false
public boolean tryAcquire(int permits, long timeout, TimeUnit unit) throws InterruptedException { };
