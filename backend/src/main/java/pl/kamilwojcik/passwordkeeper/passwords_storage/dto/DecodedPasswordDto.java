package pl.kamilwojcik.passwordkeeper.passwords_storage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class DecodedPasswordDto {
    private @NotNull String passwordName;
    private @NotNull String decodedPassword;
}
