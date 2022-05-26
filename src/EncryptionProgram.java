import org.apache.commons.lang.*;
import java.util.*;

public class EncryptionProgram {
    private static int keyLength;
    private static String key;
    private Scanner scanner;
    private Random random;

    EncryptionProgram(){
        keyLength=0;
        key=new String();
        scanner=new Scanner(System.in);
        random=new Random();

        newKey();
        askQuestion();
    }
    private void askQuestion(){
        while(true) {
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("What do you want to do?");
            System.out.println("(E)ncrypt ,(D)ecrypt ,(N)ewkey ,(G)etkey ,(Q)uit");
            char response=Character.toUpperCase(scanner.nextLine().charAt(0));

            switch (response){
                case'E':
                    encrypt();
                    break;
                case 'D':
                    decrypt();
                    break;
                case 'N':
                    newKey();
                    break;
                case 'G':
                    getKey();
                    break;
                case 'Q':
                    quit();
                    break;
                default:
                    System.out.println("Invalid input...Try again");
            }
        }
    }
    private void encrypt(){
        System.out.println("Please enter message to Encrypt");
        StringBuilder messageToEncrypt= new StringBuilder(scanner.nextLine());

        int value=0;
        int ptr=0;
        if(key.length()%2!=0){
            while(ptr<key.length()){
                if(ptr%2!=0){
                    value = value + (int) key.charAt(ptr);
                } else{
                    value = value - (int) key.charAt(ptr);
                }
                ptr++;
            }
        } else{
            while(ptr<key.length()){
                if(ptr%2!=0){
                    value = value - (int) key.charAt(ptr);
                } else{
                    value = value + (int) key.charAt(ptr);
                }
                ptr++;
            }
        }
        if(value%2!=0){
            for(int i=0;i<messageToEncrypt.length();i++) {
                int temp;
                if (i%2==0){
                    temp = ((int) messageToEncrypt.charAt(i) + value);
                } else{
                    temp = ((int) messageToEncrypt.charAt(i) + ((-1) * value));
                }
                if(temp>126){
                    temp=(temp%127)+32;
                }
                if(temp<32){
                    temp=127-32+temp;
                }
                messageToEncrypt.setCharAt(i,(char)temp);
            }
        } else{
            for(int i=0;i<messageToEncrypt.length();i++) {
                int temp;
                if (i%2!=0){
                    temp=((int)messageToEncrypt.charAt(i)+value);
                } else{
                    temp=((int)messageToEncrypt.charAt(i)+((-1)*value));
                }
                if(temp>126){
                    temp=(temp%127)+32;
                }
                if(temp<32){
                    temp=127-32+temp;
                }
                messageToEncrypt.setCharAt(i,(char)temp);
            }
        }
        System.out.println("Encrypted message:"+messageToEncrypt);
        System.out.println("Key:"+key);
    }
    private void decrypt(){
        System.out.println("Please enter message to Decrypt");
        StringBuilder messageToDecrypt= new StringBuilder(scanner.nextLine());
        System.out.println(("Please enter key"));
        StringBuilder decryptionKey=new StringBuilder(scanner.nextLine());
        int value=0;
        int ptr=0;
        if(decryptionKey.length()%2!=0){
            while(ptr<decryptionKey.length()){
                if(ptr%2!=0){
                    value = value + (int) decryptionKey.charAt(ptr);
                } else{
                    value = value - (int) decryptionKey.charAt(ptr);
                }
                ptr++;
            }
        } else{
            while(ptr<decryptionKey.length()){
                if(ptr%2!=0){
                    value = value - (int) decryptionKey.charAt(ptr);
                } else{
                    value = value + (int) decryptionKey.charAt(ptr);
                }
                ptr++;
            }
        }
        if(value%2==0){
            for(int i=0;i<messageToDecrypt.length();i++) {
                int temp;
                if (i%2==0){
                    temp = ((int) messageToDecrypt.charAt(i) + value);
                } else{
                    temp = ((int) messageToDecrypt.charAt(i) + ((-1) * value));
                }
                if(temp>126){
                    temp=(temp%127)+32;
                }
                if(temp<32){
                    temp=127-32+temp;
                }
                messageToDecrypt.setCharAt(i,(char)temp);
            }
        } else{
            for(int i=0;i<messageToDecrypt.length();i++) {
                int temp;
                if (i%2!=0){
                    temp=((int)messageToDecrypt.charAt(i)+value);
                } else{
                    temp=((int)messageToDecrypt.charAt(i)+((-1)*value));
                }
                if(temp>126){
                    temp=(temp%127)+32;
                }
                if(temp<32){
                    temp=127-32+temp;
                }
                messageToDecrypt.setCharAt(i,(char)temp);
            }
        }
        System.out.println("Decrypted message:"+messageToDecrypt);

    }
    private void newKey(){
        keyLength= random.nextInt(10);
        key=RandomStringUtils.random(keyLength," !\"#$%&\\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\\\]^_`abcdefghijklmnopqrstuvwxyz{|}~");
        System.out.println("New key has been generated :)");
    }
    private void getKey(){
        System.out.println(key);
    }
    private void quit(){
        System.out.println("Good bye, See you again.");
        System.exit(0);
    }
}
