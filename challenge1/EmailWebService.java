package challenge1;

import java.io.*;
import java.net.*;

/**
 * Web service to  take in email ID and get a person's name.
 *
 * @author James Parkes jp4g20@soton.ac.uk
 */
public class EmailWebService {

    //URL to use to find users
    public static final String URL_STUB = "https://www.ecs.soton.ac.uk/people/";
    //The line returned if the id inputted is incorrect
    public static final String TITLE_ERROR_LOOKUP = "<title>People | Electronics and Computer Science | University of Southampton</title>";
    //Tag searched for on user profile
    public static final String TAG_LOOKUP = "<title>";

    /**
     * Program start method.
     *
     * @param args console input arguments
     * @throws IOException Define exception type to throw
     */
    public static void main(String[] args) throws IOException {

        //Receive ID input from user
        String id = getInput("Input your email ID: ");
        //Finds tagged line
        String nameLine = fetchTaggedLine(id);

        //Name variable declaration
        String name;
        if(nameLine != null){
            //Reduces line to just name
            System.out.println(extractName(nameLine));
        }
        else{
            //Print Error
            System.out.println("Error retrieving user, cannot print name.");
        }
    }

    /**
     * Retrieves written input from console after displaying a prompt.
     *
     * @param prompt    Message to display to console before receiving input
     * @return  The console input
     * @throws IOException  Define exception type to throw
     */
    //Receive input from console given a prompt message that will precede it
    public static String getInput(String prompt) throws IOException {

        //Output a prompt message
        System.out.println(prompt);

        //Open a reader object that receives input from the user input
        BufferedReader consoleBufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //Stores user input
        String input = consoleBufferedReader.readLine();
        //Close reader
        consoleBufferedReader.close();

        return input;
    }

    /**
     * Retrieves the HTML line that contains the users name.
     *
     * @param id Inputted id from console
     * @return  Line containing the users name
     * @throws IOException
     */
    //Fetch HTML line that contains the subjects name
    public static String fetchTaggedLine(String id) throws IOException {

        //Opens a new reader to receives input from the created url
        BufferedReader webBufferedReader = new BufferedReader(new InputStreamReader(new URL(URL_STUB + id).openStream()));

        String nameLine = null;
        //Read through to desired line
        while (nameLine == null) {
            //Get current read line and trim white spaces
            String line = webBufferedReader.readLine().trim();
            //If line starts with TITLE_LOOKUP assign as nameLine
            if (line.indexOf(TAG_LOOKUP) == 0) nameLine = line;
        }

        //If the username did not return a profile set the line to null
        if(nameLine.equals(TITLE_ERROR_LOOKUP)) nameLine = null;
        //Close web reader
        webBufferedReader.close();

        return nameLine;
    }

    /**
     * Extracts the users name from the line it is in.
     *
     * @param nameLine Line the name is within
     * @return Users name
     */
    public static String extractName(String nameLine){
        return nameLine.substring(TAG_LOOKUP.length(), nameLine.indexOf("|"));
    }
}