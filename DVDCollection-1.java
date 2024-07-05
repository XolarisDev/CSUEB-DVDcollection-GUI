import java.io.*;
import java.util.Scanner;
public class DVDCollection {
    // Data fields
    /** The current number of DVDs in the array */
    private int numdvds;
    /** The array to contain the DVDs */
    private DVD[] dvdArray;
    /** The name of the data file that contains dvd data */
    private String sourceName;
    /** Boolean flag to indicate whether the DVD collection was
    modified since it was last saved. */
    private boolean modified;
    /**
     * Constructs an empty directory as an array
     * with an initial capacity of 7. When we try to
     * insert into a full array, we will double the size of
     * the array first.
     */
    public DVDCollection() {
        numdvds = 0;
        dvdArray = new DVD[7];
    }
    public String toString() {
        // Return a string containing all the DVDs in the
        // order they are stored in the array along with
        // the values for numdvds and the length of the array.
        // See homework instructions for proper format.
        String stringToReturn = " ";
        for (int i = 0; i < numdvds; i++) { //For each index, call its
            "toString"
            method
            stringToReturn += "\ndvdArray[" + i + "] = " +
                this.dvdArray[i].toString();
        }
        return "\nnumdvds = " + this.numdvds + "\ndvdArray.length = " +
            this.dvdArray.length + stringToReturn;
    }
    public void doubleSize() {
        int cur_size = this.dvdArray.length;
        DVD[] newDvdArray = new DVD[cur_size * 2];
        System.out.println("\nThe current size of the array is: " +
            this.dvdArray.length);
        for (int i = 0; i < this.dvdArray.length; i++) {
            newDvdArray[i] = this.dvdArray[i];
        }
        this.dvdArray = newDvdArray;
        System.out.println("The current size of the array is: " +
            this.dvdArray.length + "\n");
        this.toString();
    }
    public void addOrModifyDVD(String title, String rating, String runningTime) {
        // NOTE: Be careful. Running time is a string here
        // since the user might enter non-digits when prompted.
        // If the array is full and a new DVD needs to be added,
        // double the size of the array first.
        boolean dvdFound = false;
        int rTime = Integer.parseInt(runningTime); //parseInt() method parses a
        string argument and returns an integer
        for (int i = 0; i < numdvds; i++) {
            if (title.equals(this.dvdArray[i].getTitle())) { //if title
                equals a title already in array, calls setRating & setRunningTime
                dvdFound = true;
                this.dvdArray[i].setRating(rating);
                this.dvdArray[i].setRunningTime(rTime);
                this.modified = true;
                break;
            }
        }
        if (dvdFound == false) { // if title does NOT equal one already in array
            this.modified = true;
            if (this.dvdArray.length <= this.numdvds) {
                this.doubleSize();
                // int cur_size = this.dvdArray.length;
                // System.out.println("The current size of the array is: " +
                cur_size);
            // DVD[] newArray = new DVD[dvdArray.length * 2];
            // System.arraycopy(dvdArray, 0, newArray, 0, numdvds);
            // dvdArray = newArray;
            // System.out.println("The current size of the array is: " +
            cur_size);
        // this.toString();
    }
    this.numdvds += 1;
    dvdArray[this.numdvds - 1] = new DVD(title, rating, rTime);
    DVD temp;
    for (int j = 0; j < this.numdvds - 1; j++) { //basic bubble sort
        for (int i = j + 1; i < this.numdvds; i++) {
            if (this.dvdArray[j].getTitle().compareTo(this.dvdArray[i].getTitle()) > 0) {
                temp = this.dvdArray[j];
                this.dvdArray[j] = this.dvdArray[i];
                this.dvdArray[i] = temp;
            }
        }
    }
}
}
public void removeDVD(String title) {
    boolean dvdFound = false;
    int indexToRemove = 0;
    for (int i = 0; i < numdvds; i++) {
        if (this.dvdArray[i].getTitle().compareTo(title) == 0) {
            indexToRemove = i;
            dvdFound = true;
            this.modified = true;
            break;
        }
    }
    if (dvdFound == true) {
        this.modified = true;
        DVD[] newArray = new DVD[this.dvdArray.length];
        for (int i = 0; i < indexToRemove; i++) { //add elements before
            index
            newArray[i] = this.dvdArray[i];
        }
        for (int i = indexToRemove + 1; i < this.dvdArray.length; i++) { //add elements AFTER index
            newArray[i] = this.dvdArray[i];
        }
        // System.arraycopy(dvdArray, 0, newArray, 0, index);
        // System.arraycopy(dvdArray, index + 1, newArray, index,
        dvdArray.length - index - 1);
    this.dvdArray = newArray;
    numdvds--;
}
}
public String getDVDsByRating(String rating) {
    String movWithRating = "";
    for (int i = 0; i < numdvds; i++) {
        if (this.dvdArray[i].getRating().compareTo(rating) == 0) {
            movWithRating += this.dvdArray[i].toString() + "\n";
        }
    }
    return movWithRating;
}
public int getTotalRunningTime() {
    int moviesRT = 0;
    for (int i = 0; i < numdvds; i++) {
        moviesRT += this.dvdArray[i].getRunningTime();
    }
    return moviesRT;
}
public void loadData(String filename) {
    File file = new File(filename);
    try {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            String[] values = data.split(",");
            addOrModifyDVD(values[0], values[1], values[2]);
        }
        scanner.close();
    } catch (Exception e) {
        System.out.println(e);
    }
    System.out.println("\n" + toString());
}
public void save() {
        this.sourceName = "filename.txt";
        try {
            if (modified == false) {
                System.out.println("No changes have been made. File was not
                    modified.
                    ");
                    return;
                }
                else {
                    FileWriter fw = new FileWriter(this.sourceName);
                    for (int i = 0; i < numdvds; i++) {
                        fw.write(this.dvdArray[i].toString().replace("/",
                            ",") + "\n");
                    }
                    fw.close();
                    System.out.println("Changes saved successfully to " +
                        sourceName);
                    modified = false;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        //added new methods, to use in GUI
        public DVD[] getDVDArray() {
            return this.dvdArray;
        }
        public int getDvdCount() {
            return this.numdvds;
        }
