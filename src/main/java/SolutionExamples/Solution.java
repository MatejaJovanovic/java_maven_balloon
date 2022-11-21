package SolutionExamples;

import org.jetbrains.annotations.VisibleForTesting;
import Exceptions.EmptyListException;
import Exceptions.InvalidStringException;

import java.util.*;
import java.io.*;

public class Solution {


    public int solution(String inputWord, BufferedReader reader) {
        int iterations = 0;
        int total_iterations = 0;
        HashMap<Character, Integer> inputWordChars;
        HashMap<Character, Integer> charCount;
        List<Integer> divList = new ArrayList<Integer>();
        List<Character> divListChar = new ArrayList<Character>();

        inputWordChars = traverseString(inputWord);

        try {
            if(reader==null){
                throw new  NullPointerException("Null pointer!");
            }
            boolean isValid;
            int row = 1;
            String line = reader.readLine();
            while (line != null) {

                System.out.println(line);
                isValid = isValidString(line);
                if (!isValid) {
                    throw new InvalidStringException("The string in row " + row + " is not valid! \nThe program will not continue processing the file.");
                }

                charCount = traverseString(line);
                System.out.println("Character count of the input word " + inputWordChars);
                System.out.println("Character count of the input text " + charCount);
                iterations=(rowComparison(inputWordChars, charCount, divList, divListChar));

                total_iterations = total_iterations + iterations;
                System.out.println("Number of iterations for line " + row + " is " + iterations);
                System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::");
                row++;
                clear(charCount, divList, divListChar);
                line = reader.readLine();


            }
            reader.close();
        } catch (IOException e) {
            System.out.println("IO Exception");
            e.printStackTrace();
        } catch (InvalidStringException | EmptyListException e) {
            System.out.println(e.getMessage());}
        catch (NullPointerException e) {
            System.out.println("Null pointer");
            //  e.getMessage();
            //e.printStackTrace();
        }


        return total_iterations;
    }
    @VisibleForTesting
    public int rowComparison(HashMap<Character, Integer> inputWordChars, HashMap<Character, Integer> charCount,  List<Integer> divList, List<Character> divListChar) throws EmptyListException{
        int div=0;

        for (char k : inputWordChars.keySet()) {

            if (charCount.get(k) != null) {
                System.out.println("Present character " + k);
                div = charCount.get(k) / inputWordChars.get(k);
                if (div < 1) {
                    System.out.println("Not enough character " + k);


                    divListChar.add(k);

                    divList.add(div);
                    break;
                } else {

                    divListChar.add(k);
                    divList.add(div);
                }

            }
            else {
                System.out.println("Missing character " + k + " in the line");
                divList.clear();
                divListChar.clear();
                div=0;
                divList.add(div);

            }

        }
        System.out.println("List of divisions" + divList);
        System.out.println("List of respective characters" + divListChar);

        if (divList.isEmpty()) {
            throw new EmptyListException("List of divisions is empty");
        }

        Collections.sort(divList);
        System.out.println("------------------------------------------------");
        System.out.println("List of divisions after sorting " + divList);
        return divList.get(0);
    }
    @VisibleForTesting
    public void clear(HashMap<Character, Integer> charCount, List<Integer> divList, List<Character> divListChar) {
        divList.clear();
        divListChar.clear();
        charCount.clear();
    }

    @VisibleForTesting
    public boolean isValidString(String line) {
        boolean isValid = false;

        if (line!=null) {

            isValid = !line.isEmpty();
            if (line.isBlank()) {
                isValid = false;
            }
        }
        return isValid;
    }
    @VisibleForTesting
    public HashMap<Character, Integer> traverseString( String str) {
        HashMap<Character, Integer> hashmap = new HashMap<Character, Integer>();

        if(str!=null){
            char[] strArray = str.toCharArray();
            char c;
            // Traverse the string
            for (int i = 0; i < strArray.length; i++) {

                c = str.charAt(i);
                if (hashmap.containsKey(c)) {
                    hashmap.put(c, hashmap.get(c) + 1);
                } else {
                    hashmap.put(c, 1);
                }
            }
        }

        return hashmap;
    }
}
