package testDataGenerator;
/*This Class helps in generating random data for the tests like
 * (random names, Phone numbers, Emails, reviews,  etc.) */
import java.security.SecureRandom;

/**
 *
 * @author COMPUTER SHOW
 */
public class TestDataGeneration {

    /*String contains all lower case letters*/
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    /*String contains all Higher case letters*/
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    /*String contains all Numbers*/
    private static final String NUMBER = "0123456789";
    /*String contains all Numbers, and lower and higher case letters*/
    private static final String DATA_FOR_RANDOM_STRING = CHAR_UPPER + CHAR_LOWER + NUMBER;
    /*String contains all lower and higher case letters*/
    private static final String DATA_FOR_RANDOM_LETTERS_STRING = CHAR_UPPER + CHAR_LOWER;
    /*SecureRandom class helps in generating random data*/
    private static SecureRandom random = new SecureRandom();
    
    /*Method to generate random names starting with Upper case letter 
     * and the rest are lower case letters 
     * will be used to generate first and last name in register form*/
    public String generateRandomName(int length) 
    {
        if (length < 1) throw new IllegalArgumentException();

        StringBuilder sb = new StringBuilder(length);
        int rndCharAt = random.nextInt(CHAR_UPPER.length());
        char rndChar = CHAR_UPPER.charAt(rndCharAt);
        /*add the first letter letter to the name and upper case letter*/
        sb.append(rndChar);
        for (int i = 1; i < length; i++) {
            
            rndCharAt = random.nextInt(CHAR_LOWER.length());
            rndChar = CHAR_LOWER.charAt(rndCharAt);
            /*add new letter to the name*/
            sb.append(rndChar);
        }
        return sb.toString();
    }
    /*Method to generate random password containing all upper and lower case letters and 
     * also numbers with limit of 8 characters*/
    public String generateRandomPassword() 
    {
        StringBuilder sb = new StringBuilder();
        
        int rndCharAt;
        char rndChar;
        /*generating random 5 characters*/
        for (int i = 0; i < 5; i++) {
            
            rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);
            /*add new character to the password*/
            sb.append(rndChar);
        }
        /*add new characters and making sure it contains 
         * all upper and lower case letters and also numbers*/
        sb.append("Ab1");
      
        return sb.toString();
    }
    /*Method to generate random emails containing with right email format*/
    public String generateRandomEmail(int length) 
    {
        if (length < 1) throw new IllegalArgumentException();

        StringBuilder sb = new StringBuilder(length);
        
        int rndCharAt;
        char rndChar;

        
        for (int i = 0; i < length; i++) {
            
            rndCharAt = random.nextInt(DATA_FOR_RANDOM_LETTERS_STRING.length());
            rndChar = DATA_FOR_RANDOM_LETTERS_STRING.charAt(rndCharAt);
            /*add new letter to the email*/
            sb.append(rndChar);
        }
        /*making sure of the right email format*/
        sb.append("@testDomain.ext");
        
        return sb.toString();
    }
    /*Method to generate random phone numbers compliant with the Egyptian system*/
    public String generateRandomPhoneNumber() 
    {
        StringBuilder sb = new StringBuilder(11);
        int rndCharAt = random.nextInt(NUMBER.length());
        char rndChar = NUMBER.charAt(rndCharAt);
        
        sb.append("010");
        for (int i = 0; i < 8; i++) {
           
            rndCharAt = random.nextInt(NUMBER.length());
            rndChar = NUMBER.charAt(rndCharAt);
           
            sb.append(rndChar);
        }
        return sb.toString();
    }
    /*Method to generate random job titles*/
    public String generateRandomJobTitle() 
    {
        String[] jobTitles = {"Elect Engineer" , "Software Tester" , "Quality Control Engineer" 
        		, "Teacher" , "Restaurant Owner" , "Civil Enginneer" , "Chemical Engineer" , "Software Developer"
        		, "Actor" , "Presenter" , "Player" , "CEO" , "Marketing Manageer" , "Sales Manager"};
        int randomNumber = random.nextInt(jobTitles.length);
        
        return jobTitles[randomNumber];
    }
    /*Method to generate random reviews or comments*/
    public String addReviewOrComment() 
    {
        String[] review = {"Hello " + "my name is " +  generateRandomName(6)+ " my email is " 
        			+ generateRandomEmail(5) +" and I want to complain about the service of  "
        			+ generateRandomName(8) +" Restaurant",
        			"Hello " + "my name is " +  generateRandomName(6)+ " my email is " 
                			+ generateRandomEmail(5) +" and I want to praise the service of  "
                			+ generateRandomName(8) +" Restaurant"};
        int randomNumber = random.nextInt(review.length); 
        
        return review[randomNumber];
    }
    
    
    
	
}
