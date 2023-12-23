package hexlet.code.dto;

import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor

public class UrlChecks {

    public final Url url;

    public final List<UrlCheck> urlCheckList;

    public final Optional<String> flash;

}
