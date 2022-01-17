package pl.kamilwojcik.passwordkeeper.exceptions.resource;

import org.springframework.lang.Nullable;

public class ResourceNotExistException extends RuntimeException {

    private @Nullable
    String resourceName;
    private @Nullable
    String resourceType;

    public ResourceNotExistException(@Nullable String resourceName, @Nullable String resourceType) {
        this.resourceName = resourceName;
        this.resourceType = resourceType;
    }

    public ResourceNotExistException(String message, @Nullable String resourceName, @Nullable String resourceType) {
        super(message);
        this.resourceName = resourceName;
        this.resourceType = resourceType;
    }

    public ResourceNotExistException(@Nullable String resourceType) {
        this.resourceType = resourceType;
    }

    public ResourceNotExistException() {
    }

    @Nullable
    public String getResourceName() {
        return resourceName;
    }

    @Nullable
    public String getResourceType() {
        return resourceType;
    }
}
