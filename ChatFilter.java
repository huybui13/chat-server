import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ChatFilter {
private String badWordFileName;
    public ChatFilter(String badWordsFileName) {
        this.badWordFileName = badWordsFileName;

    }

    public String filter(String msg) {
        try{

            File file = new File(badWordFileName);

            FileReader fr1 = new FileReader(file);

            BufferedReader bfr1 = new BufferedReader(fr1);

            int lineCount =0; // count line in the text

            while(bfr1.readLine() != null){

                lineCount++;

            }

            bfr1.close();

            // copy bad words to a file

            String[] array = new String[lineCount];

            FileReader fr2 = new FileReader(file);

            BufferedReader bfr2 = new BufferedReader(fr2);

            for(int i =0; i < array.length; i++){

                array[i] = bfr2.readLine();

            }
            bfr2.close();
            
            
            //  replace bad words with "*" .The amount of * will be the number of letter in the word.

            for (int i = 0; i < array.length; i++) {
                int j = 0;
                for (int k = 0; k < array[i].length() ; k++) {
                    String badWord = msg.substring(j,k);
                    if(badWord.equals(array[i].toLowerCase())){

                        String wordBefore = msg.substring(0, j);

                        String wordAfter = msg.substring(i, msg.length());

                        String replacedWord = "*";

                        for(int m =0; m < badWord.length()-1; m++) {

                            replacedWord += "*";

                        }

                        msg = wordBefore + replacedWord +  wordAfter;

                    }

                    j++;

                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        return msg;

    }
}
