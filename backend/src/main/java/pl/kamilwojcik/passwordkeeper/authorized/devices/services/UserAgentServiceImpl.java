package pl.kamilwojcik.passwordkeeper.authorized.devices.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAgentServiceImpl implements UserAgentService {

    private final List<Character> allowedChars = List.of('_', '.', '/', ';', '(', ')');

    @Override
    public String parseToStorageForm(String userAgentHeader) {
        var safeForm = removeNotAllowedCharacters(userAgentHeader);

        return removeNonSystemVersioning(safeForm);
    }

    @Override
    public boolean areEqual(String uaHeader, String safeFormHeader) {
        return parseToStorageForm(uaHeader).equals(safeFormHeader);
    }

    private String removeNonSystemVersioning(String userAgentHeader) {
        var startIndex = userAgentHeader.indexOf(')');

        StringBuilder sb = new StringBuilder();
        sb.append(userAgentHeader, 0, startIndex);

        String stringToParse = userAgentHeader.substring(startIndex);
        for(char c : stringToParse.toCharArray()) {
            if(Character.isDigit(c)) {
                sb.append('X');
            }
        }

        return sb.toString();
    }

    private String removeNotAllowedCharacters(String userAgentHeader) {
        StringBuilder sb = new StringBuilder();

        for(char c : userAgentHeader.toCharArray()) {
            if(Character.isLetterOrDigit(c) || allowedChars.contains(c)) {
                sb.append(c);
            } else {
                sb.append('_');
            }
        }

        return sb.toString();
    }


}
