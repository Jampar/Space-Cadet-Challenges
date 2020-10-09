package challenge2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BarebonesInterpreter {

    public static void main(String[] args) throws IOException {
        Lexer lexer = new Lexer();

        String input = getInput("");

        Token[] tokenizeString = lexer.lex(input);
        for (Token token : tokenizeString)  System.out.print(Arrays.toString(token.value));
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

}

//Lexer to create a tokenised version of the inputted string
class Lexer{

    //Characters to be split from the rest of the string
    String SpecialChars =
            ";=";


    String[] Operations = {
            "clear",
            "incr",
            "decr"
    };


    public Lexer(){

    }

    //Tokenize input string
    public Token[] lex(String inputString){

        System.out.println(inputString);

        //Split the input string to separate words & symbols
        String[] splitString = SplitString(inputString);
        Token[] tokens = new Token[splitString.length];

        int i = 0;
        for (String s : splitString){

            if(check(Operations,s)) tokens[i] = new Token(s,"");
            else if(i > 0 && check(Operations,tokens[i-1].type) || s.matches("^[a-zA-Z]")) tokens[i] = new Token("symbol",s);
            else if(SpecialChars.contains(s)) tokens[i] = new Token(s,"");
            else if(isStringInteger(s)) tokens[i] = new Token("number",s);
            else if (!isStringInteger(s)) tokens[i] = new Token("string",s);
            else tokens[i] = new Token("","");
            i++;

        }
        return tokens;
    }

    //Seperate string into words and symbols
    String[] SplitString(String inputString){
        return SeparateSpecialChars(inputString.split(" "));
    }

    String SpaceOutSpecialCharacters(String inputString){
        int i = 0;
        for (char c : inputString.toCharArray()){
            if(SpecialChars.indexOf(c) != -1){

            }
            i++;
        }
        return null;
    }

    //Separate special characters that arent recognised by the " " regex
    String[] SeparateSpecialChars(String[] inputArray){

        //Return result array
        String[] resultArray = new String[0];

        //Iterate through characters in SpecialChars array
        for (int i = 0; i < SpecialChars.length();i++) {

            //Iterate through the inputted string array
            for (String word : inputArray) {
                word = word; //CONTINUE
            }

            //concat the result array and split word array
            //resultArray = concat(resultArray,splitWord); //INITIATE splitWord

        }
        return resultArray;
    }

    //Array insert and concat methods
    private static String[] insert(String[] a, String key, int index) {
        String[] result = new String[a.length + 1];

        System.arraycopy(a, 0, result, 0, index);
        result[index] = key;
        System.arraycopy(a, index, result, index + 1, a.length - index);

        return result;
    }
    public static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }
    private static boolean check(String[] arr, String toCheckValue) {
        // check if the specified element
        // is present in the array or not
        // using Linear Search method
        for (String element : arr) {
            if (element.equals(toCheckValue)) {
                return true;
            }
        }
        return false;
    }
    public static boolean isStringInteger(String number ){
        try{
            Integer.parseInt(number);
        }catch(Exception e ){
            return false;
        }
        return true;
    }

}

class Token {

    String type;
    String content;

    String[] value = new String[2];

    public Token(String _type, String _content){
        type = _type;
        content = _content;

        value[0] = type;
        value[1] = content;
    }
}


