package pl.kamilwojcik.passwordkeeper.exceptions.resource;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;
import pl.kamilwojcik.passwordkeeper.exceptions.ErrorBody;

@Getter
@Setter
public class ResourceErrorBody extends ErrorBody {

    private @Nullable
    String resourceName;
    private @Nullable
    String resourceType;

    public ResourceErrorBody(String errorCode, @Nullable String resourceType, @Nullable String resourceName) {
        super(errorCode);
        this.resourceName = resourceName;
        this.resourceType = resourceType;
    }
}
