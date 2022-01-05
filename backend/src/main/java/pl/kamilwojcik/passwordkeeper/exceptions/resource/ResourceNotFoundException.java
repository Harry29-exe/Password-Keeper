package pl.kamilwojcik.passwordkeeper.exceptions.resource;

import org.springframework.lang.Nullable;

public class ResourceNotFoundException extends RuntimeException {

    private @Nullable
    String resourceName;
    private @Nullable
    String resourceType;

    public ResourceNotFoundException(@Nullable String resourceName, @Nullable String resourceType) {
        this.resourceName = resourceName;
        this.resourceType = resourceType;
    }

    public ResourceNotFoundException(String message, @Nullable String resourceName, @Nullable String resourceType) {
        super(message);
        this.resourceName = resourceName;
        this.resourceType = resourceType;
    }

    public ResourceNotFoundException(@Nullable String resourceType) {
        this.resourceType = resourceType;
    }

    public ResourceNotFoundException() {
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
