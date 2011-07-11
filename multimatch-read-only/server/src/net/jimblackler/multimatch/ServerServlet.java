package net.jimblackler.multimatch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("serial")
public class ServerServlet extends HttpServlet {
  private static final int INPUT_STREAM_SIZE = 32 * 1024;

  private static final Logger log = Logger.getLogger(ServerServlet.class.getName());

  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.setContentType("text/plain");
    resp.getWriter().println("Hello, world");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {
    InputStream inputStream = req.getInputStream();
    BufferedReader reader =
        new BufferedReader(new InputStreamReader(inputStream), INPUT_STREAM_SIZE);
    StringBuffer builder = new StringBuffer();
    String line;
    while ((line = reader.readLine()) != null) {
      builder.append(line).append("\n");
    }

    PersistenceManager pm = PMF.getPersistenceManager();
    Game game = loadGame(pm);

    try {
      JSONObject object = new JSONObject(builder.toString());
      JSONObject reply = game.doMove(object);
      saveGame(pm, game);
      OutputStreamWriter writer = new OutputStreamWriter(resp.getOutputStream(), "UTF-8");
      writer.write(reply.toString());
      writer.close();
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unchecked")
  private Game loadGame(PersistenceManager pm) {
    while (true) {
      Query query = pm.newQuery("select from " + Game.class.getName());

      List<Game> results = (List<Game>) query.execute();
      if (results.size() >= 1) {
        return results.get(0);
      }
 
      Game entry = new Game();
      pm.makePersistent(entry);
    }
  }

  private void saveGame(PersistenceManager pm, Game game) {
    Transaction tx = pm.currentTransaction();
    tx.begin();
    pm.makePersistent(game);
    tx.commit();
  }

}
