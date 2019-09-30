package code.Security;


import org.jasypt.util.password.StrongPasswordEncryptor;

public class Encryption {
    private static StrongPasswordEncryptor passwordEncryptor=new StrongPasswordEncryptor();

    public static String encryptPassword(String password){
        return passwordEncryptor.encryptPassword(password);
    }

    public static boolean checkPassword(String plainTextPassword,String encryptedPassword){
        return passwordEncryptor.checkPassword(plainTextPassword, encryptedPassword);
    }
}
