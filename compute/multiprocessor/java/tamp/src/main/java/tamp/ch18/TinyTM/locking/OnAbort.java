/*
 * OnAbort.java
 *
 * Created on January 14, 2007, 8:56 PM
 *
 * From "The Art of Multiprocessor Programming",
 * by Maurice Herlihy and Nir Shavit.
 *
 * This work is licensed under a Creative Commons Attribution-Share Alike 3.0 United States License.
 * http://i.creativecommons.org/l/by-sa/3.0/us/88x31.png
 */

package tamp.ch18.TinyTM.locking;

/**
 * Handler for transaction abort.
 * @author Maurice Herlihy
 */
public class OnAbort implements Runnable {
  public void run() {
    WriteSet writeSet = WriteSet.getLocal();
    ReadSet readSet  = ReadSet.getLocal();
    VersionClock.setReadStamp();
    writeSet.clear();
    readSet.clear();
  }
}
