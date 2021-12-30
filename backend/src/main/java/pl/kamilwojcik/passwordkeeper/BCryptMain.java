package pl.kamilwojcik.passwordkeeper;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;

public class BCryptMain {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder(10, SecureRandom.getInstanceStrong());
        System.out.println("Strong_P@ssword123");
        System.out.println("Strong_storage_P@ssword123");
        System.out.println(bCrypt.encode("Strong_P@ssword123"));
        System.out.println(bCrypt.encode("Strong_storage_P@ssword123"));

        System.out.println("gre@T_passw321");
        System.out.println("gre@T_passw321_for_storage");
        System.out.println(bCrypt.encode("gre@T_passw321"));
        System.out.println(bCrypt.encode("gre@T_passw321_for_storage"));

        System.out.println("some_P@assw_222");
        System.out.println("some_storaGe1_p@ssw");
        System.out.println(bCrypt.encode("some_P@assw_222"));
        System.out.println(bCrypt.encode("some_storaGe1_p@ssw"));
    }

}
