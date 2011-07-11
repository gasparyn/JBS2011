package net.jimblackler.multimatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import org.json.JSONException;
import org.json.JSONObject;

@PersistenceCapable
public class Game {

  private static final int VERSION = 8;

  private static final int NUMBER_IN_SET = 2;

  @Persistent(mappedBy = "game")
  private List<Client> clients;

  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  private Long id;

  public JSONObject doMove(JSONObject inObject) throws JSONException {
    JSONObject outObject = new JSONObject();
    if (inObject.getInt("version") != VERSION) {
      outObject.put("error", "version");
      return outObject;
    }
    String action = inObject.has("action") ? inObject.getString("action") : "";
    
    Client flippedClient = null;
    
    // Get client and also count unallocated cards
    long clientId = inObject.getLong("id");
    if (clients == null || "reset".equals(action)) {
      clients = new ArrayList<Client>();
    }
    
    Client thisClient = null;
    int noCard = 0;
    for (Client client : clients) {
      
      if (client.getClientId() == clientId) {
        thisClient = client;
        
        if ("flipped".equals(action)) {
          client.setFlipped(true);
        }
      }
        
      if (client.getFlipped()) {
        if (flippedClient == null) {
          flippedClient = client;
        } else {
          if (flippedClient.getCard().equals(client.getCard())) {
            flippedClient.setSolved(true);
            client.setSolved(true);
          }
          flippedClient.setFlipped(false);
          client.setFlipped(false);
        }
      }
   
      if (client.getCard() == null) {
        noCard++;
      }
    }
    if (thisClient == null) {
      thisClient = new Client(clientId);
      clients.add(thisClient);
      noCard++;
    }

    // Fill unallocated cards
    Random random = new Random();

    while (noCard >= NUMBER_IN_SET) {
      String newCard = newCard();
      for (int count = 0; count != NUMBER_IN_SET; count++) {
        int pick = random.nextInt(noCard);
        for (Client client : clients) {
          if (client.getCard() == null) {
            if (pick == 0) {
              client.setCard(newCard);
              break;
            }
            pick--;
          }
          
        }
        noCard--;
      }
    }
    
    String card = thisClient.getCard();
    outObject.put("showCard", card == null ? "null" : card);
    outObject.put("solved", thisClient.getSolved());
    outObject.put("flipped", thisClient.getFlipped());
    
    return outObject;
  }

  private String newCard() {
    return "" + (new Random().nextInt(10) + 1);
  }

}
