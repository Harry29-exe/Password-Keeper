package pl.kamilwojcik.passwordkeeper.authorized.devices.services;

public interface UserAgentService {

    /**
     * Eliminates browser version from given userAgentHeader and removes all characters witch are not
     * in typical header (allowed not letter or digit characters:  _ . / ; ( ) )
     *
     * @param userAgentHeader User-Agent header from request
     * @return converted header with contains only Digits Characters and following signs: _ . / ; ( )
     */
    String parseToStorageForm(String userAgentHeader);


    /**
     * @param uaHeader       User-Agent header from request
     * @param safeFormHeader safe User-Agent form which comes probably from parseToStorageForm() method
     * @return result of comparing two User-Agent headers
     */
    boolean areEqual(String uaHeader, String safeFormHeader);

}
