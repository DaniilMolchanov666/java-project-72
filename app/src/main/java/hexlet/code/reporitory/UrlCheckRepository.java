package hexlet.code.reporitory;

import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import kong.unirest.GetRequest;
import kong.unirest.Unirest;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.*;

public class UrlCheckRepository extends BaseRepository {

    //TODO  настроить флеш-сообщения
    //TODO  вывод сообщения о некорректном URL адресе
    //TODO вывод h1 заголовка

    public static HashMap<Url, List<UrlCheck>> mapOfChecks = new HashMap<>();

    public static void saveCheck(Url url) throws IOException, SQLException {

        String sqlInsert = "INSERT INTO url_checks(url_id, status_code, title, h1, description, created_at) VALUES "
                + "(?, ?, ?, ?, ?, ?)";

        GetRequest con = Unirest.get(url.getName());
        Connection con2 = Jsoup.connect(url.getName());

        int status = Unirest.get(url.getName())
                .asEmpty()
                .getStatus();

        String title = Jsoup.connect(url.getName())
                .get()
                .title();

        String h = Jsoup.connect(url.getName())
                .get()
                .select("h1")
                .text();

        String description = Optional.of(Jsoup.connect(url.getName())
                .get()
                .selectFirst("meta")
                .attr("description"))
                .orElse("No such elements");

        Timestamp timeNow = new Timestamp(new Date().getTime());

        try (var conn = dataSource.getConnection();
             var statement = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, url.getId());
            statement.setInt(2, status);
            statement.setString(3, title);
            statement.setString(4, h);
            statement.setString(5, description);
            statement.setTimestamp(6, timeNow);

            statement.executeUpdate();

            UrlCheck urlChecks = new UrlCheck(url.getId());

            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                urlChecks.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("DB have not returned an id after saving the entity");
            }

            urlChecks.setStatusCode(status);
            urlChecks.setTitle(title);
            urlChecks.setH1(h);
            urlChecks.setDescription(description);
            urlChecks.setCreatedAt(timeNow);

            mapOfChecks.computeIfPresent(url, (key, value) -> {
                List<UrlCheck> list = new ArrayList<>(mapOfChecks.get(key));
                list.add(urlChecks);
                return list;
            });
        }
    }

    public static List<UrlCheck> getChecks(Url url) {

        String sql = "SELECT * FROM url_checks";

        try (var conn = dataSource.getConnection()) {

            var statement = conn.prepareStatement(sql);
            var resultSet = statement.executeQuery();

            ArrayList<UrlCheck> listSQL = new ArrayList<>();

            while (resultSet.next()) {
                var id = resultSet.getLong("id");
                var urlId = resultSet.getLong("url_id");
                var status = resultSet.getInt("status_code");
                var title = resultSet.getString("title");
                var h1 = resultSet.getString("h1");
                var description = resultSet.getString("description");
                var createdAt = resultSet.getTimestamp("created_at");

                UrlCheck urlChecks = new UrlCheck(urlId);
                urlChecks.setId(id);
                urlChecks.setStatusCode(status);
                urlChecks.setTitle(title);
                urlChecks.setH1(h1);
                urlChecks.setDescription(description);
                urlChecks.setCreatedAt(createdAt);

                listSQL.add(urlChecks);
            }

            return listSQL.stream()
                    .filter(c -> Objects.equals(c.getUrlId(), url.getId()))
                    .sorted(Comparator.comparingLong(UrlCheck::getId).reversed())
                    .toList();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
