package pl.kamilwojcik.passwordkeeper.passwords_storage.dto;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;

@Data
public class PasswordInfoDto {

    private @NotBlank String passwordName;
    private @Nullable String webUrl;

}
