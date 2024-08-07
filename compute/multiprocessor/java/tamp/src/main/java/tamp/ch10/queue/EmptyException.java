/*
 * EmptyException.java
 *
 * Created on December 28, 2005, 12:02 AM
 *
 * Copyright 2005 Sun Microsystems, Inc.  All Rights Reserved.
 *
 * Sun Microsystems, Inc. has intellectual property rights relating to technology embodied in the product that is described in this document.  In particular, and without limitation, these intellectual property rights may include one or more of the U.S. patents listed at http://www.sun.com/patents and one or more additional patents or pending patent applications in the U.S. and in other countries.
 * U.S. Government Rights - Commercial software.  Government users are subject to the Sun Microsystems, Inc. standard license agreement and applicable provisions of the FAR and its supplements.  Use is subject to license terms.
 * Sun, Sun Microsystems, the Sun logo and Java are trademarks or registered trademarks of Sun Microsystems, Inc. in the U.S. and other countries.
 */

package tamp.ch10.queue;

/**
 * @author Maurice Herlihy
 */
public class EmptyException extends java.lang.Exception {
  
  /** Creates a new instance of EmptyException */
  public EmptyException() {
    super();
  }
  
}
