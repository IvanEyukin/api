package fmc.database.request;

public class UserRequest {
    public final static String INSERT = """
            INSERT INTO Users
                (name, login, password, creationDate, role)
            VALUES
                (?,?,?,?,?)
            """;
    public final static String UPDATE = """
            UPDATE 
                Users
            SET
                name = ?,
                password = ?,
                role = ?
            WHERE
                login = ?
            """;
    public final static String SELECT = """
            SELECT 
                name        AS name,
                login       AS login,
                password    AS password,
                role        AS role
            FROM
                Users
            WHERE
                login = '%s'
            """;
}
