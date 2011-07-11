package net.jimblackler.multimatch;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

public class PMF {
  private final static PersistenceManagerFactory pmf =
    JDOHelper.getPersistenceManagerFactory("transactions-optional");

  public static PersistenceManager getPersistenceManager() {
    return pmf.getPersistenceManager();
  }

}
