package hexlet.code.reporitory;

import hexlet.code.model.Url;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UrlRepository extends BaseRepository{

    //TODO настройка ссылок на home страницу и urls страницу

    public static ArrayList<Url> listOfUrls = new ArrayList<>();
    public static void save(Url url) throws SQLException {
        if (!listOfUrls.contains(url)) {
            String sql = "INSERT INTO urls(name, created_at) VALUES (?, ?)";

            try (var conn = dataSource.getConnection();
                 var statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, url.getName());
                statement.setTimestamp(2, Timestamp.from(url.getCreatedAt()));
                statement.executeUpdate();

                var generatedKeys = statement.getGeneratedKeys();
                if(generatedKeys.next()) {
                    url.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("DB have not returned an id after saving the entity");
                }
                listOfUrls.add(url);
            }
        }
    }

    public static List<Url> getUrlsFromDB() {

        String sql = "SELECT * FROM urls";

        try(var conn = dataSource.getConnection()) {

            var statement = conn.prepareStatement(sql);
            var resultSet = statement.executeQuery();

            ArrayList<Url> listSQL = new ArrayList<>();

            while(resultSet.next()) {
                var id = resultSet.getLong("id");
                var name = resultSet.getString("name");
                var createdAt = resultSet.getTimestamp("created_at").toInstant();
                listSQL.add(new Url(id, name, createdAt));
            }

            return listSQL;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Url find(long id) throws SQLException {
        return listOfUrls.stream()
                .filter(u -> u.getId() == id)
                .findAny()
                .orElseThrow(SQLException::new);
    }
}
