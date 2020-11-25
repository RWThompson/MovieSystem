package name.rwthompson.movie_system.helper;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DatabaseHelper.class})
@PowerMockIgnore("javax.net.ssl.*")
public class DatabaseHelperTest {

    private String dbURL = "jdbc:mysql://localhost:3306/movies";
    private String dbUsername = "root";
    private String dbPassword = "root";

//    @Mock
//    private DriverManager mockedDriverManager;
//
//    @Mock
//    private Connection mockedConnection;
//
//    @Mock
//    private Statement mockedStatement;
//
//    @Mock
//    private ResultSet mockedResultSet;
//
//    @InjectMocks
//    private DatabaseHelper databaseHelper;

    private DriverManager mockedDriverManager;
    private Connection mockedConnection;
    private Statement mockedStatement;
    private ResultSet mockedResultSet;
    private DatabaseHelper databaseHelper;

    @Before
    public void setUp() {
        mockedDriverManager = mock(DriverManager.class);
        mockedConnection = mock(Connection.class);
        mockedStatement = mock(Statement.class);
        mockedResultSet = mock(ResultSet.class);
        databaseHelper = mock(DatabaseHelper.class);
//        databaseHelper = new DatabaseHelper();
    }

    @Ignore
    @Test
    public void testGetUniqueID() throws SQLException {
        String sql = "SELECT MAX(id) FROM Movies WHERE id <> 0;";
        Mockito.when(mockedDriverManager.getConnection(dbURL, dbUsername, dbPassword)).thenReturn(mockedConnection);
        Mockito.when(mockedConnection.createStatement()).thenReturn(mockedStatement);
        Mockito.when(mockedStatement.executeQuery(sql)).thenReturn(mockedResultSet);
        Mockito.when(mockedResultSet.getInt("MAX(id)")).thenReturn(1);
        int uniqueID = databaseHelper.getUniqueID("Movies");
        assertEquals(2, uniqueID);
        verify(databaseHelper).getUniqueID(Mockito.anyString());
    }
}
