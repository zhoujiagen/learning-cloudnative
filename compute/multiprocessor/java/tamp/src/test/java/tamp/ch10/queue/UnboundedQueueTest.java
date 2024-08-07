/*
 * UnboundedQueueTest.java
 * JUnit based test
 *
 * Created on March 8, 2006, 8:13 PM
 */

package tamp.ch10.queue;

import junit.framework.*;

/**
 * @author Maurice Herlihy
 */
public class UnboundedQueueTest extends TestCase {
  private final static int THREADS = 8;
  private final static int TEST_SIZE = 512;
  private final static int PER_THREAD = TEST_SIZE / THREADS;
  int index;
  UnboundedQueue<Integer> instance;
  boolean[] map = new boolean[TEST_SIZE];
  Thread[] thread = new Thread[THREADS];
  
  public UnboundedQueueTest(String testName) {
    super(testName);
    instance = new UnboundedQueue<Integer>();
  }
  
  public static Test suite() {
    TestSuite suite = new TestSuite(UnboundedQueueTest.class);
    return suite;
  }
  
  /**
   * Sequential calls.
   */
  public void testSequential() {
    System.out.println("sequential push and pop");
    
    for (int i = 0; i < TEST_SIZE; i++) {
      instance.enq(i);
    }
    for (int i = 0; i < TEST_SIZE; i++) {
      int j = -1;
      try {
        j = (Integer) instance.deq();
      } catch (EmptyException ex) {
        fail("bad deq: " + j + " expected " + i);
      }
      if (j != i) {
        fail("bad deq: " + j + " expected " + i);
      }
    }
  }
  /**
   * Parallel enqueues, sequential dequeues
   */
  public void testParallelEnq()  throws Exception {
    System.out.println("parallel enq");
    for (int i = 0; i < THREADS; i++) {
      thread[i] = new EnqThread(i * PER_THREAD);
    }
    for (int i = 0; i < THREADS; i ++) {
      thread[i].start();
    }
    for (int i = 0; i < THREADS; i ++) {
      thread[i].join();
    }
    for (int i = 0; i < TEST_SIZE; i++) {
      int j = (Integer)instance.deq();
      if (map[j]) {
        fail("duplicate pop: " + j);
      } else {
        map[j] = true;
      }
    }
  }
  /**
   * Sequential enqueues, parallel dequeues
   */
  public void testParallelDeq()  throws Exception {
    System.out.println("parallel deq");
    for (int i = 0; i < TEST_SIZE; i++) {
      map[i] = false;
    }
    for (int i = 0; i < TEST_SIZE; i++) {
      instance.enq(i);
    }
    for (int i = 0; i < THREADS; i++) {
      thread[i] = new DeqThread();
    }
    for (int i = 0; i < THREADS; i ++) {
      thread[i].start();
    }
    for (int i = 0; i < THREADS; i ++) {
      thread[i].join();
    }
  }
  /**
   * Sequential enqueues, parallel dequeues
   */
  public void testParallelBoth()  throws Exception {
    System.out.println("parallel both");
    Thread[] myThreads = new Thread[2 * THREADS];
    for (int i = 0; i < THREADS; i++) {
      myThreads[i] = new EnqThread(i * PER_THREAD);
      myThreads[i + THREADS] = new DeqThread();
    }
    for (int i = 0; i < 2 * THREADS; i ++) {
      myThreads[i].start();
    }
    for (int i = 0; i < 2 * THREADS; i ++) {
      myThreads[i].join();
    }
  }
  class EnqThread extends Thread {
    int value;
    EnqThread(int i) {
      value = i;
    }
    public void run() {
      for (int i = 0; i < PER_THREAD; i++) {
        instance.enq(value + i);
      }
    }
  }
  class DeqThread extends Thread {
    public void run() {
      for (int i = 0; i < PER_THREAD; i++) {
        int value;
        try {
          value = (Integer) instance.deq();
        } catch (EmptyException ex) {
          continue;
        }
        if (map[value]) {
          fail("DeqThread: duplicate pop");
        }
        map[value] = true;
      }
    }
  }
  
}