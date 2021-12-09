package pl.kamilwojcik.passwordkeeper.passwords_storage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Getter
@Setter
@AllArgsConstructor
public class DecodedPasswordDto {
    private @NonNull String passwordName;
    private @NonNull String decodedPassword;
}
