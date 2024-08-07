/*
 * ThreadID.java
 *
 * Created on January 11, 2006, 10:27 PM
 *
 * From "Multiprocessor Synchronization and Concurrent Data Structures",
 * by Maurice Herlihy and Nir Shavit.
 * Copyright 2006 Elsevier Inc. All rights reserved.
 */

package tamp.ch15.priority;

/**
 * Assigns unique contiguous ids to threads.
 * @author Maurice Herlihy
 */
public class ThreadID {
  /**
   * The next thread ID to be assigned
   **/
  private static volatile int nextID = 0;
  /**
   * My thread-local ID.
   **/
  private static ThreadLocalID threadID = new ThreadLocalID();
  /*
   * @return unique ID for this thread
   */
  public static int get() {
    return threadID.get();
  }
  /**
   * Reset this thread's ID
   * @param index new ID
   */
  public static void set(int index) {
    threadID.set(index);
  }
  /**
   * Assign new IDs from zero.
   */
  public static void reset() {
    nextID = 0;
  }
  private static class ThreadLocalID extends ThreadLocal<Integer> {
    protected synchronized Integer initialValue() {
      return nextID++;
    }
  }
}

