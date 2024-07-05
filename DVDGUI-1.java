import javax.swing.*;
/**
 * This class is an implementation of DVDUserInterface
 * that uses JOptionPane to display the menu of command choices.
 */
public class DVDGUI implements DVDUserInterface {
    private DVDCollection dvdlist;
    public DVDGUI(DVDCollection dl) {
        dvdlist = dl;
    }
    public void processCommands() {
        String[] commands = {
            "Add/Modify DVD",
            "Remove DVD",
            "Get DVDs By Rating",
            "Get Total Running Time",
            "Display DVD List",
            "Display DVD Information",
            "Edit DVD",
            "Exit and Save",
            "Exit"
        };
        int choice;
        do {
            choice = JOptionPane.showOptionDialog(null,
                "Select a command",
                "DVD Collection",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                commands,
                commands[commands.length - 1]);
            switch (choice) {
                case 0:
                    doAddOrModifyDVD();
                    break;
                case 1:
                    doRemoveDVD();
                    break;
                case 2:
                    doGetDVDsByRating();
                    break;
                case 3:
                    doGetTotalRunningTime();
                    break;
                case 4:
                    doDisplayList();
                    break;
                case 5:
                    doDisplayInfo();
                    break;
                case 6:
                    doEditDVD();
                    break;
                case 7:
                    doSave();
                    break;
                case 8:
                    doExit();
                    break;
                default: // do nothing
            }
        } while (choice != commands.length - 1);
        System.exit(0);
    }
    private void doAddOrModifyDVD() {
        // Request the title
        String title = JOptionPane.showInputDialog("Enter title");
        if (title == null) {
            return; // dialog was cancelled
        }
        title = title.toUpperCase();
        // Request the rating
        String rating = JOptionPane.showInputDialog("Enter rating for " +
            title);
        if (rating == null) {
            return; // dialog was cancelled
        }
        rating = rating.toUpperCase();
        // Request the running time
        String time = JOptionPane.showInputDialog("Enter running time for " +
            title);
        if (time == null) {}
        // Add or modify the DVD (assuming the rating and time are valid
        dvdlist.addOrModifyDVD(title, rating, time);
        //DELETE THIS
        // Display current collection to the console for debugging
        System.out.println("Adding/Modifying: " + title + "," + rating + "," +
            time);
        System.out.println(dvdlist);
    }
    private void doRemoveDVD() {
        // Request the title
        String title = JOptionPane.showInputDialog("Enter title");
        if (title == null) {
            return; // dialog was cancelled
        }
        title = title.toUpperCase();
        // Remove the matching DVD if found
        dvdlist.removeDVD(title);
        //DELETE THIS
        // Display current collection to the console for debugging
        System.out.println("Removing: " + title);
        System.out.println(dvdlist);
    }
    private void doGetDVDsByRating() {
        // Request the rating
        String rating = JOptionPane.showInputDialog("Enter rating");
        if (rating == null) {
            return; // dialog was cancelled
        }
        rating = rating.toUpperCase();
        String results = "DVDs Rated: " + rating + "\n" +
            dvdlist.getDVDsByRating(rating);
        JOptionPane.showMessageDialog(null, results,
            null, JOptionPane.INFORMATION_MESSAGE);
        /*
        String results = dvdlist.getDVDsByRating(rating);
        System.out.println("DVDs with rating " + rating);
        System.out.println(results);
        */
    }
    private void doGetTotalRunningTime() {
        int total = dvdlist.getTotalRunningTime();
        String result = "Total running time of all DVDs: " + total + " minutes.\
n";
        JOptionPane.showMessageDialog(null, result,
            null, JOptionPane.INFORMATION_MESSAGE);
        /*
        System.out.println("Total Running Time of DVDs: ");
        System.out.println(total);
        */
    }
    private void doDisplayList() {
        //I altered DVDCOLLECTION, to have array of dvds and num of dvds. sorry
        DVD[] arrayCopy = dvdlist.getDVDArray();
        int dvdCount = dvdlist.getDvdCount();
        //int length = arrayCopy.length;
        /*
        int i = 0;
        for (DVD dvd : arrayCopy) {
        if (dvd != null) {
        titles[i] = arrayCopy[i].getTitle();
        i++;
        }
        }
        */
        String[] sections = {
            "DVD number",
            "Title"
        };
        String[][] dvds = new String[dvdCount][2];
        for (int i = 0; i < dvdCount; i++) {
            dvds[i][0] = Integer.toString(i);
            dvds[i][1] = arrayCopy[i].getTitle();
        }
        // Create a new frame to display array contents
        JFrame arrayFrame = new JFrame("All DVDs");
        JTable arraytable = new JTable(dvds, sections);
        // Add the array table to the frame
        arrayFrame.add(new JScrollPane(arraytable));
        arrayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        arrayFrame.pack();
        arrayFrame.setLocationRelativeTo(null); // Center the frame on the screen
        arrayFrame.setVisible(true);
    }
    private void doDisplayInfo() {}
    private void doEditDVD() {}
    private void doSave() {
        dvdlist.save();
    }
    private void doExit() {
        JOptionPane.showMessageDialog(null, "Exit");
    }
}
