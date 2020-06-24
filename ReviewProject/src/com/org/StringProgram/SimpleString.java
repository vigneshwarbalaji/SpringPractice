package com.org.StringProgram;

public class SimpleString 
{

    public static void findingTheWords(String paragraph,char StartingCharacter,char endingCharacter)
    {
        String storageArray[] = paragraph.split(" ");
        
//        char start = StartingCharacter.charAt(0);
//        char end = StartingCharacter.charAt(0);
        
        int startStringCount = 0;
        int endStringCount = 0;
        int bthStringCount = 0;
        
        for(int i = 0;i < storageArray.length;i++)
        {
            if(storageArray[i].charAt(0) == StartingCharacter)
            {
            	startStringCount++;
            }
            
            if(storageArray[i].charAt(storageArray[i].length()-1) == endingCharacter)
            {
            	endStringCount++;
            }
        	
            if(storageArray[i].charAt(storageArray[i].length()-1) == endingCharacter && storageArray[i].charAt(0) == StartingCharacter)
            {
            	bthStringCount++;
            }
            
           
        
        }
        
        System.out.println(startStringCount+" "+endStringCount+" "+bthStringCount);
    }
    public static void main(String[] args) 
    {
        String paragraph = "my name is vicky vicky";
        char StartingCharacter = 'v';
        char endingCharacter = 'y';
        findingTheWords(paragraph,StartingCharacter,endingCharacter);    
    }
}