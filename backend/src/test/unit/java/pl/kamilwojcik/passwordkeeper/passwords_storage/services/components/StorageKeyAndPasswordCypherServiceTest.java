package pl.kamilwojcik.passwordkeeper.passwords_storage.services.components;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.crypto.SecretKey;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.*;

class StorageKeyAndPasswordCypherServiceTest {
    StorageKeyAndPasswordCypherService cypherService =
            new StorageKeyAndPasswordCypherService();
    SecureRandom random = SecureRandom.getInstanceStrong();

    StorageKeyAndPasswordCypherServiceTest() throws NoSuchAlgorithmException {
    }

    @Test
    void should_encode_and_decode_text() {
        //given
        String storagePassword = "haslo123";
        String salt = "salt";
        String msgToEncrypt = "Hello world";

        //when
        SecretKey key = cypherService.getStorageKey(storagePassword, salt);
        String encodedMsg = cypherService.encryptPassword(msgToEncrypt, key);
        String decodedMsg = cypherService.decodePassword(encodedMsg, key);

        //then
        assert  decodedMsg.equals(msgToEncrypt);

    }

}