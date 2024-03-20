package hexlet.code.dto;

import hexlet.code.model.Url;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;
import java.util.Optional;

@Getter
@AllArgsConstructor
public final class Urls {

    private final List<Url> urls;

    private final Optional<String> flash;

}
