package com.insive;


import java.util.Arrays;

public class App
{

    /**
     * Function that finds the key to decrypt a message encrypted using XOR
     * @param encryptedMessage - int[] Encrypted message, it is in ASCII.
     * @return int[] Key of length 4, which is in ASCII format
     */
    public static int[] findKey(int[] encryptedMessage){
        int [] answer = new int[4];
        int [] alphabetASCII = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        for (int i = 0; i < 4 ; i ++){
            int maxFrequency = 0;
            for (int element : alphabetASCII){
                int frequency = calculateFrequency(element, encryptedMessage, i);
                if (frequency > maxFrequency){
                    maxFrequency = frequency;
                    answer[i] = element;
                }
            }
        }
        return answer;
    }

    /**
     * Function that allows you to find the frequency with which a letter of the alphabet can be given for a possible key.
     * @param elementAlphabet - int Letter of the alphabet being analyzed
     * @param encryptedMessage - int[] Encrypted message to be analyzed
     * @param i - Key position you want to find.
     * @return int frequency, Frequency with which possible letters, numbers or symbols were found. Given the letter of the alphabet.
     */
    public static int calculateFrequency(int elementAlphabet, int[] encryptedMessage, int i){
        int frequency = 0;
        for (int j = i; j < encryptedMessage.length; j += 4) {
            char decryptedelement = calculateXOR(elementAlphabet, encryptedMessage[j]);
            if (Character.isLetterOrDigit(decryptedelement) || Character.isSpaceChar(decryptedelement) || decryptedelement == '.' || decryptedelement == ',' || decryptedelement == '@' || decryptedelement == '-' || decryptedelement == '_' || decryptedelement == '/'){
                frequency ++;
            }
        }
        return frequency;
    }

    /**
     * Function that applies XOR to decrypt or encrypt a letter of the message in ASCII.
     * @param elementOne - int Item one to apply XOR
     * @param elementTwo - int Item tow to apply XOR
     * @return char answer - Returns ASCII decoding
     */
    public static char calculateXOR(int elementOne, int elementTwo){
        char answer = (char) (elementOne ^ elementTwo);
        return answer;
    }

    /**
     * Function to decrypt a message in ASCII, given that you have its key.
     * @param key - int[] Key length 4, must be in ASCII format.
     * @param encryptedMessage - int[] Encrypted message.
     * @return int[] answerMessage - Decrypted message in ASCII format.
     */
    public static int[] decryptedMessage(int[] key, int[] encryptedMessage){
        int [] answerMessage = new int[encryptedMessage.length];
        int position = 0;
        for (int i = 0; i < encryptedMessage.length ; i ++){
            if (i % 4 == 0){
                position = 0;
            }else if (i % 4 == 1){
                position = 1;
            }else if (i % 4 == 2){
                position = 2;
            }else if (i % 4 == 3){
                position = 3;
            }
            answerMessage[i] = (calculateXOR(encryptedMessage[i], key[position]) );
        }
        return answerMessage;
    }

    /**
     * ASCII decoding function
     * @param decryptedMessageASCII - int[] Message in ASCII to decode.
     * @return cahr[] - Decoded message.
     */
    public static char[] ASCIIdecoding(int[] decryptedMessageASCII){
        char[] answer = new char[decryptedMessageASCII.length];
        for (int i = 0; i < decryptedMessageASCII.length; i ++){
            answer[i] = (char) decryptedMessageASCII[i];
        }
        return answer;
    }

    /**
     * Main function that executes the code, to solve the encryption problem.
     * @param args Argument that comes by default in the main.
     */
    public static void main( String[] args )
    {
        int[] encryptedMessage = {34, 22, 1, 27, 7, 26, 9, 19, 0, 22, 30, 82, 32, 22, 4, 4, 13, 23, 77, 33, 1, 17, 12, 1, 16, 26, 12, 28, 68, 62, 8, 22, 13, 29, 12, 82, 54, 18, 25, 27, 18, 18, 65, 82, 12, 18, 30, 82, 8, 28, 10, 0, 5, 23, 2, 82, 0, 22, 30, 17, 13, 21, 31, 19, 22, 83, 8, 30, 68, 30, 8, 28, 23, 18, 7, 23, 74, 83, 77, 51, 12, 28, 31, 19, 72, 83, 29, 19, 22, 18, 77, 3, 17, 22, 77, 59, 10, 0, 4, 4, 1, 83, 31, 23, 7, 28, 3, 29, 30, 16, 12, 82, 16, 6, 77, 30, 11, 20, 31, 29, 72, 83, 30, 7, 6, 22, 77, 23, 8, 83, 14, 29, 0, 22, 77, 17, 11, 29, 77, 23, 8, 83, 28, 7, 1, 83, 31, 23, 23, 28, 1, 4, 13, 0, 25, 23, 68, 22, 30, 6, 1, 83, 8, 24, 1, 1, 14, 27, 7, 26, 2, 82, 1, 29, 77, 53, 13, 7, 37, 7, 6, 92, 42, 27, 16, 63, 12, 16, 68, 10, 77, 17, 11, 30, 29, 19, 22, 7, 8, 82, 1, 31, 77, 23, 10, 31, 12, 17, 1, 83, 12, 82, 23, 28, 29, 29, 22, 7, 8, 50, 13, 29, 30, 27, 18, 22, 67, 17, 8, 93};
        int[] key = findKey(encryptedMessage);
        int[] decryptedMessageASCII = decryptedMessage(key, encryptedMessage);
        char[] messageText = ASCIIdecoding(decryptedMessageASCII);

        System.out.println("Llave: " + Arrays.toString(key));
        System.out.println("Mensaje Desencriptado en formato ASCII: " + Arrays.toString(decryptedMessageASCII));
        System.out.println("Mensaje decodificado:");
        System.out.println(messageText);
    }
}
