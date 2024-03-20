package hexlet.code.reporitory;

import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.*;

public class UrlCheckRepository extends BaseRepository {

    public static void saveCheck(Url url) throws Exception {

        String sqlInsert = "INSERT INTO url_checks(url_id, status_code, title, h1, description, created_at) VALUES "
                + "(?, ?, ?, ?, ?, ?)";

        var urlCheck = checkUrl(url);

        try (var conn = dataSource.getConnection();
             var statement = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, url.getId());
            statement.setInt(2, urlCheck.getStatusCode());
            statement.setString(3, urlCheck.getTitle());
            statement.setString(4, urlCheck.getH1());
            statement.setString(5, urlCheck.getDescription());
            statement.setTimestamp(6, urlCheck.getCreatedAt());

            statement.executeUpdate();
        }
    }

    public static UrlCheck checkUrl(Url url) throws IOException {

        Jsoup.connect(url.getName()).get();

        HttpResponse<String> response = Unirest.get(url.getName()).asString();
        Document bodyOfResponse = Jsoup.parse(response.getBody());

        int status = response.getStatus();

        String title = bodyOfResponse.title();

        String h = bodyOfResponse.select("h1").text();

        String description;

        if (bodyOfResponse.selectFirst("meta[name=description]") != null) {
            description = bodyOfResponse.selectFirst("meta[name=description]")
                    .attr("content");
        } else {
            description = "";
        }

        Timestamp timeNow = new Timestamp(new Date().getTime());

        var urlCheck = new UrlCheck();

        urlCheck.setUrlId(url.getId());
        urlCheck.setTitle(title);
        urlCheck.setDescription(description);
        urlCheck.setStatusCode(status);
        urlCheck.setCreatedAt(timeNow);
        urlCheck.setH1(h);

        return urlCheck;
    }

    public static List<UrlCheck> getChecksForUrl(Url url) {

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
