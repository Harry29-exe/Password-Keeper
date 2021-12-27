package pl.kamilwojcik.passwordkeeper.passwords_storage.services.components;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

class PasswordStorageDefaultCypherTest {
    PasswordStorageDefaultCypher cypherService =
            new PasswordStorageDefaultCypher();
    SecureRandom random = SecureRandom.getInstanceStrong();

    PasswordStorageDefaultCypherTest() throws NoSuchAlgorithmException {
    }

    @Test
    void should_encode_and_decode_text() {
        //given
//        String storagePassword = "haslo123";
//        String salt = "salt";
//        String msgToEncrypt = "Hello world";
//
//        //when
//        SecretKey key = cypherService.getStorageKey(storagePassword, salt);
//        String encodedMsg = cypherService.encryptPassword(msgToEncrypt, key);
//        String decodedMsg = cypherService.decodePassword(encodedMsg, key);
//
//        //then
//        assert  decodedMsg.equals(msgToEncrypt);

    }

}