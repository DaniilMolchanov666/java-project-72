package hexlet.code.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Url {

    private long id;

    private String name;

    private Instant createdAt;

    private List<UrlCheck> listOfChecks = new ArrayList<>();

    private String flash = "";

    public Url(String name, Instant createdAt) {
        this.name = name;
        this.createdAt = createdAt;
    }

    public Url(long id, String name, Instant createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public List<UrlCheck> getListOfChecks() {
        return listOfChecks;
    }

    public void setListOfChecks(List<UrlCheck> listOfChecks) {
        this.listOfChecks = listOfChecks;
    }

    public String getFlash() {
        return flash;
    }

    public void setFlash(String flash) {
        this.flash = flash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Url url = (Url) o;
        return Objects.equals(name, url.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
