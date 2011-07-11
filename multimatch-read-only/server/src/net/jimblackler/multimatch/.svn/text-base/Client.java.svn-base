package net.jimblackler.multimatch;

import java.util.Collection;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Client {

  @Persistent
  private String card;

  @Persistent
  private long clientId;

  @Persistent
  private boolean flipped;
  
  @Persistent
  private Game game;

  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  private Key id;

  @Persistent
  private boolean solved;

  public Client(long clientId) {
    this.clientId = clientId;
  }

  public String getCard() {
    return card;
  }

  public long getClientId() {
    return clientId;
  }

  public boolean getFlipped() {
    return flipped;
  }

  public boolean getSolved() {
    return solved;
  }

  public void setCard(String value) {
    card = value;
  }

  public void setFlipped(boolean value) {
    flipped = value;
  }

  public void setSolved(boolean value) {
    solved = value;
  }

}
