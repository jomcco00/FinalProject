import java.util.*;
import java.lang.*;

/**
 * Starting code for Kyle's 4140 Final Exam, Fall 2015.
 *
 * @author Kyle Burke <paithanq@gmail.com>
 */
public class BetterRiverDiscoverer
{
private Scanner betterInput = new Scanner(System.in);
private ArrayList<Integer> betterLengthsBetweenConvergences = new ArrayList<Integer>();
private ArrayList<String> betterConvergenceNames = new ArrayList<String>();
private ArrayList<Integer> betterRiverFlows = new ArrayList<Integer>(); //amount of flow on the main branch at each part of the river.
private String betterRiverName = "";
private String betterTributaryName = "";
private int betterSpillToSea = 1;
private int fromConfluence = 0;

public void mainRiverExplorer()
{
  //get the main river's name
  System.out.println("You have found the mouth of a new river where it spills into the sea.  What do the locals call this river?");
  betterRiverName = betterInput.nextLine();
  betterConvergenceNames.add(betterRiverName);
  addRiverFlow();
}

public void addRiverFlow()
{
  if(betterSpillToSea == 1)
  {
    while (true) {
      System.out.println("How much water is flowing into the sea?  (L/s)");
      try {
          int flow = Integer.parseInt(betterInput.nextLine());
          betterRiverFlows.add(flow);
          break;
      } catch (NumberFormatException e) {
          System.out.println("I didn't understand that!");
      }
    }
    betterSpillToSea = 0;
  }
  else if(betterSpillToSea == 0)
  {
    while (true) {
        System.out.println("How much water is flowing from that tributary into the main river?  (L/s)");
        try {
          int flow = Integer.parseInt(betterInput.nextLine());
          int previousFlow = betterRiverFlows.get(betterRiverFlows.size() - 1);
          betterRiverFlows.add(previousFlow - flow);
          break;
        } catch (NumberFormatException e) {
            System.out.println("I didn't understand that!");
        }
    }
  }
  if(fromConfluence == 0)
  {
    fromConfluence = 1;
    addRiverDistance();
  }
  else if(fromConfluence == 1)
  {
    exploreConfluence();
  }
}

public void addRiverDistance()
{
  while (true) {
    System.out.println("How far did you walk until you reached the next junction? (in km)");
    try {
      int length = Integer.parseInt(betterInput.nextLine());
      betterLengthsBetweenConvergences.add(length);
      addConfluence();
      break;
    } catch (NumberFormatException e) {
      System.out.println("I didn't understand that!");
      //break;
    }
  }
  //addConfluence();
}

public void exploreConfluence()
{
  System.out.println("Are you going to explore the tributary? (y/N)");
  if (betterInput.nextLine().equals("y")) {
      addRiverDistance();
  }
  else
  {
    addConfluence();
  }
}

public void addConfluence()
{
    System.out.println("Are you done traveling upstream on the " + betterRiverName + "? (y/N)");
    if (betterInput.nextLine().equals("y")) {
      System.out.println("Great!  We're done!");

      displayOutput();
    }
    else
    {
      System.out.println("That means you must be at another confluence.  What is the name the locals have given the river flowing into this one?");
      betterTributaryName = betterInput.nextLine();
      betterConvergenceNames.add(betterTributaryName);
      addRiverFlow();
    }
}

public void displayOutput()
{
  //now print the results
  System.out.println("You have now explored the " + betterRiverName + "!");

  //calculate the total length
  int totalLength = 0;
  for (Integer length : betterLengthsBetweenConvergences)
  {
      totalLength += length;
  }

  System.out.println("The river is at least " + totalLength + " km long.");
  System.out.println("From the furthest distance we explored:");

  System.out.println(betterRiverFlows.get(betterRiverFlows.size() - 1) + " L/s flows " + betterLengthsBetweenConvergences.get(betterRiverFlows.size() - 1) + " km downstream.");
  System.out.println("Then, the " + betterConvergenceNames.get(betterRiverFlows.size() - 1) + " flows into the river.");

  for (int i = betterRiverFlows.size() - 2; i > 0; i--)
  {
      System.out.println("Then " + betterRiverFlows.get(i) + " L/s flows " + betterLengthsBetweenConvergences.get(i) + " km downstream.");
      System.out.println("Then, the " + betterConvergenceNames.get(i) + " flows into the river.");
  }
  System.out.println(betterRiverFlows.get(0) + " L/s flows " + betterLengthsBetweenConvergences.get(0) + " km downstream.");
  System.out.println("Then the " + betterRiverName + " flows into the sea.");

}

    public static void main(String[] args)
    {
      BetterRiverDiscoverer thingMk2 = new BetterRiverDiscoverer();
      thingMk2.mainRiverExplorer();
    }
}
