package pl.kamilwojcik.passwordkeeper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class BCryptMain {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder(10, SecureRandom.getInstanceStrong());
        System.out.println("123");
        System.out.println("123");
        System.out.println(bCrypt.encode("123"));
        System.out.println(bCrypt.encode("123"));

        System.out.println("admin1");
        System.out.println("admin1");
        System.out.println(bCrypt.encode("admin1"));
        System.out.println(bCrypt.encode("admin1"));

        System.out.println("123456");
        System.out.println("123456");
        System.out.println(bCrypt.encode("123456"));
        System.out.println(bCrypt.encode("123456"));
    }

}
