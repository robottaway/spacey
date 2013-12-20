// For convenience, always static import your generated tables and jOOQ functions to decrease verbosity:
import static com.devero.jooq.Tables.LIBRARY;
import static com.devero.jooq.Tables.LIBRARY_COLUMN;
import static com.devero.jooq.Tables.LIBRARY_ENTRY;
import static com.devero.jooq.Tables.LIBRARY_VALUE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.jooq.DSLContext;
import org.jooq.Record2;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.Select;
import org.jooq.impl.DSL;

public class Main
{
    public static void main(String[] args)
    {
        Connection conn = null;

        String userName = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/library";

        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);

            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
            
            Select<Record2<String, Integer>> stmt = create
                    .select(LIBRARY.LIBRARY_NAME, LIBRARY_COLUMN.LIBRARY_COLUMN_ID)
                    .from(LIBRARY)
                    .join(LIBRARY_ENTRY)
                    .on(LIBRARY_ENTRY.LIBRARY_ID.eq(LIBRARY.LIBRARY_ID))
                    .join(LIBRARY_COLUMN)
                    .on(LIBRARY_COLUMN.LIBRARY_ID.equal(LIBRARY.LIBRARY_ID))
                    .join(LIBRARY_VALUE)
                    .on(
                            LIBRARY_VALUE.LIBRARY_ENTRY_ID.eq(LIBRARY_ENTRY.LIBRARY_ENTRY_ID)
                            .and(LIBRARY_VALUE.LIBRARY_COLUMN_ID.eq(LIBRARY_COLUMN.LIBRARY_COLUMN_ID)))
                    .where(LIBRARY.LIBRARY_NAME.eq("BLAH"));
            
            System.out.println(stmt.toString());
            
            Result<Record2<String, Integer>> result = stmt.fetch();
                        
            for (Record2<String, Integer> r : result) {
                Integer id = r.value2();
                String libraryName = r.value1();
                System.out.println("ID: " + id + " name: " + libraryName);
            }
        }
        catch (Exception e)
        {
            // For the sake of this tutorial, let's keep exception handling
            // simple
            e.printStackTrace();
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException ignore)
                {
                }
            }
        }
    }
}