/*
 * GTreeBarrierTest.java
 * JUnit based test
 *
 * Created on December 29, 2005, 9:56 PM
 */

package tamp.ch17.barrier;

import junit.framework.*;

/**
 *
 * @author mph
 */
public class GTreeBarrierTest extends TestCase {
  static final int THREADS = 8;   // number threads to test
  static final int ROUNDS = 8;    // how many rounds to test
  
  public GTreeBarrierTest(String testName) {
    super(testName);
  }
  
  public static Test suite() {
    TestSuite suite = new TestSuite(GTreeBarrierTest.class);
    
    return suite;
  }
  
  /**
   * Test of await method, of class barrier.GTreeBarrier.
   */
  public void testBarrier() { 
    System.out.format("Testing %d threads, %d rounds\n", THREADS, ROUNDS); 
    GTreeBarrier instance = new GTreeBarrier(THREADS, 2);
    Thread[] thread = new Thread[THREADS];
    int[] log = new int[THREADS];
    for (int i = 0; i < THREADS; i++)
      log[i] = 2005;
    for (int j = 0; j < THREADS; j++) {
      thread[j] = new TestThread(j, THREADS, ROUNDS, instance, log);
    }
    for (int j = 0; j < THREADS; j++) {
      thread[j].start();
    }
    try {
      for (int j = 0; j < THREADS; j++) {
        thread[j].join();
      }
    } catch (InterruptedException e) {
      fail("interrupted exception");
    }
    
  }
  public class TestThread extends Thread {
    int threads;
    int rounds;
    Barrier bar;
    int[] log;
    int index;
    public TestThread(int index, int threads, int rounds, Barrier bar, int[] log) {
      this.threads = threads;
      this.rounds = rounds;
      this.bar = bar;
      this.log = log;
      this.index = index;
    }
    public void run() {
      ThreadID.set(index);
      int sense = 0;
      for (int round = 0; round < rounds; round++) {
        log[index] = round;
        bar.await();  // let writers finish
        for (int i = 0; i < threads; i++) {
          if (log[i] != round) {
            System.out.format("%d\tError expected %d found %d at %d\n", index, round, log[i], i);
          }
        }
        bar.await();  // let readers finish
        sense = 1 - sense;
      }
    }
  }
  
}
