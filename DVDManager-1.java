import java.util.*;
import javax.swing.*;
/**
 * Program to display and modify a simple DVD collection
 */
public class DVDManager {
    public static void main(String[] args) {
        DVDUserInterface dlInterface;
        DVDCollection dl = new DVDCollection();
        //GUI prompt for file to load
        String textFile = JOptionPane.showInputDialog("Please Enter the name of
            the file to load ");
            dl.loadData(textFile); dlInterface = new DVDGUI(dl); dlInterface.processCommands(); System.exit(0);
            /*
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter name of data file to load:");
            String filename = scan.nextLine();
            dl.loadData(filename);
            System.out.println("Input interface type: C=Console, G=GUI");
            String interfaceType = scan.nextLine();
            if (interfaceType.equals("C")) {
            dlInterface = new DVDConsoleUI(dl);
            dlInterface.processCommands();
            } else if (interfaceType.equals("G")) {
            dlInterface = new DVDGUI(dl);
            dlInterface.processCommands();
            } else {
            System.out.println("Unrecognized interface type. Program
            exiting.");
            System.exit(0);
            }
            */
        }
    }
