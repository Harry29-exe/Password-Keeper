package pl.kamilwojcik.passwordkeeper.passwords_storage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class PasswordInfoDto {

    private @NotBlank String passwordName;
    private @Nullable String passwordUrl;

}
