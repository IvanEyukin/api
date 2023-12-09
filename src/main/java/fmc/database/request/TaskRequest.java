package fmc.database.request;

public class TaskRequest {
    public final static String INSERT = """
            INSERT INTO Task
                (creator, title, content, status, creationDate)
            VALUES
                (?,?,?,?,?)
            """;
    public final static String UPDATE = """
            UPDATE 
                Task
            SET
                title = ?,
                content = ?,
                status = ?
            WHERE
                id = ?
            """;
    public final static String SELECT = """
            SELECT 
                id              AS id,
                creator         AS creator,
                title           AS title,
                content         AS content,
                status          AS status,
                creationDate    AS creationDate
            FROM
                Task
            """;
    public final static String DELETE = """
            DELETE FROM
                Task
            WHERE
                id = %s
            """;
}