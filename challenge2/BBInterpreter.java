package challenge2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BBInterpreter {

  public enum type {
    op,
    symbol,
    value,
  }

  public static String[] ops = {
      "incr",
      "decr",
      "clear",
  };

  public static char[] specialChars = {
      '=',
      ';',
      ' '
  };

  public void interpret(String input){
    System.out.println("Input: " + input);

    Lexer lexer = new Lexer();
    Token[] tokens = lexer.lex(input);

    System.out.print("Tokenized Input: " + lexer.strTokens());
  }



  public static boolean isOp(String check){
    for (String op : ops){
      if(op.equals(check)) return true;
    }
    return false;
  }
  public static boolean isSpecialChar(char check){
    for (char sc : specialChars){
      if(sc == check) return true;
    }
    return false;
  }

}

class Lexer{

  private Token[] lastTokens;

  public Lexer(){

  }

  public Token[] lex(String instructionLine){

    List<Token> tokens = new ArrayList<Token>();

    StringBuilder builder = new StringBuilder();
    for(int i = 0; i < instructionLine.length(); i++){

      char currentChar = instructionLine.charAt(i);
      if(BBInterpreter.isSpecialChar(currentChar)){

        if(BBInterpreter.isOp(builder.toString())){
          tokens.add(new Token(builder.toString(),BBInterpreter.type.op));
        }else if(builder.length() > 0){
          if(isNumeric(builder.toString())){
            tokens.add(new Token(builder.toString(),BBInterpreter.type.value));
          }else{
            tokens.add(new Token(builder.toString(),BBInterpreter.type.symbol));
          }
        }

        if(currentChar != ' ') {
          tokens.add(new Token(String.valueOf(currentChar), BBInterpreter.type.op));
        }
        builder = new StringBuilder();
      }else{
        builder.append(currentChar);
      }
    }
    this.lastTokens = tokens.toArray(new Token[0]);
    return tokens.toArray(new Token[0]);
  }

  public void printTokens(){
    for(Token token : lastTokens) System.out.print(token.output());
    System.out.print("\n");
  }

  public String strTokens(){
    StringBuilder tokens = new StringBuilder();
    for(Token token : lastTokens) tokens.append(token.output());
    tokens.append("\n");
    return tokens.toString();
  }

  public boolean isNumeric(String strNum) {
    if (strNum == null) {
      return false;
    }
    try {
      int d = Integer.parseInt(strNum);
    } catch (NumberFormatException nfe) {
      return false;
    }
    return true;
  }
}

/**
 * Token object to store information on each token in an instruction.
 */
class Token{

  //Value of the token
  private final String value;
  //Token Type
  private final BBInterpreter.type type;

  /**
   * Token Constructor
   *
   * @param value Value of Token e.g. "0", "X", "incr"
   * @param type  Type of Token e.g. op, symbol, number
   */
  public Token(String value, BBInterpreter.type type){
    //Assign Token variables
    this.value = value;
    this.type = type;
  }

  /**
   * Type accessor method
   *
   * @return Token Type
   */
  public BBInterpreter.type getType() {
    return this.type;
  }


  /**
   * Value accessor method
   *
   * @return Token Value
   */
  //Value access method
  public String getValue() {
    return this.value;
  }

  public String output(){
    return new String('[' + getValue().toString() + ", " + getType().toString() + "] ");
  }

}
