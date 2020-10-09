package SpaceCadets.challenge1;

import java.io.*;
import java.net.*;

public class EmailWebService {

    public static final String URL_STUB = "https://www.ecs.soton.ac.uk/people/";
    public static final String TAG_LOOKUP = "<title>";

    public static void main(String[] args) throws IOException {

        //Receive ID input from user
        String id = getInput("Input your email ID: ");
        //Finds tagged line
        String nameLine = fetchTaggedLine(id);
        //Reduces line to just name
        String name = extractName(nameLine);

        System.out.println(name);
    }

    //Receive input from console given a prompt message that will precede it
    public static String getInput(String prompt) throws IOException {

        //Open a reader object that receives input from the user input
        BufferedReader consoleBufferedReader = new BufferedReader(new InputStreamReader(System.in));

        //Output a prompt message
        System.out.println(prompt);

        //Stores user input
        String input = consoleBufferedReader.readLine();

        //Close reader
        consoleBufferedReader.close();

        return input;

    }

    //Fetch HTML line that contains the subjects name
    public static String fetchTaggedLine(String id) throws IOException {

        //Create a URL by concatenating base URL with ID
        URL fullURL = new URL(URL_STUB + id);

        //Opens a new reader to receives input from the created url
        BufferedReader webBufferedReader = new BufferedReader(new InputStreamReader(fullURL.openStream()));

        String nameLine = null;

        //Read through to desired line
        while (nameLine == null) {
            //Get current read line and trim white spaces
            String line = webBufferedReader.readLine().trim();
            //If line starts with TITLE_LOOKUP assign as nameLine
            if (line.indexOf(TAG_LOOKUP) == 0) nameLine = line;
        }

        //Close web reader
        webBufferedReader.close();

        return nameLine;
    }



    //Trims tagged line to just name
    public static String extractName(String nameLine){
        return nameLine.substring(TAG_LOOKUP.length(), nameLine.indexOf("|"));
    }
}
