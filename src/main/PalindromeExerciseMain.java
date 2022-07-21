package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class PalindromeExerciseMain {

    public static void getOutputPalindromesFromFile(){

        //Read file and split the text inside it
        String textFile = getTextFromFile("src/palindromeExercise.txt").toLowerCase();
        String[] wordsFromFile = textFile.split("(\\s|[’-]|[~!@#$%^&*()_+{}:;,<>/?-])");

        //Check if word is palindrome and its number of ocurrences in the text
        HashMap<String, Long> data = new HashMap<String, Long>();
        Arrays.stream(wordsFromFile).filter(word -> isPalindrome(word)).forEach(word -> {
            long numberOcurrences = Arrays.stream(wordsFromFile).filter(wordRepeated -> wordRepeated.equals(word)).count();
            data.put(word, numberOcurrences);
        });

        //Reorder HashMap by value
        List<Map.Entry<String, Long>> reorderByValue = data.entrySet().stream().sorted((l1,l2) -> Long.compare(l2.getValue(), l1.getValue())).collect(Collectors.toList());

        //Reorder List by key
        reorderLexicographically(reorderByValue);

        //Print result
        reorderByValue.forEach(entry -> System.out.println(entry.getKey() + "-> " + entry.getValue()));

    }

    public static String getTextFromFile(String fileName){
        String textFile="";
        try{
            File palindromeFile = new File(fileName);
            Scanner reader = new Scanner(palindromeFile);
            while(reader.hasNextLine()){
                textFile += reader.nextLine();
            }


        }catch(FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
        }

        return textFile;
    }

    public static void main(String[] args) {
        getOutputPalindromesFromFile();
    }

    public static Boolean isPalindrome(String word){
        String wordReversed = "";
        if(word.length()  == 1){
            return true;
        }

        for(int i=word.length()-1; i>=0; i--){
            char actualLetter = word.charAt(i);
            wordReversed += actualLetter;
        }

        if(wordReversed.equals(word)){
            return true;
        }

        return false;


    }


    public static void reorderLexicographically(List<Map.Entry<String, Long>> list){

        Integer sizeList = list.size();
        for(int i=0; i<sizeList-1; i++){
            for(int j = i + 1; j<sizeList; j++){
                if(list.get(i).getValue() <= list.get(j).getValue()){
                    if(list.get(i).getKey().compareTo(list.get(j).getKey()) > 0){
                        Map.Entry<String, Long> temp = list.get(i);
                        list.set(i, list.get(j));
                        list.set(j, temp);
                    }
                }

            }
        }
    }


}
