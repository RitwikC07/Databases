package data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Data
{
			public static final String DB_NAME = "music.db";
			public static final String CONNECTION_STRING ="jdbc:sqlie:C:\\\\Users\\\\Amit\\\\Desktop\\\\JAVA\\\\music\\"+ DB_NAME;
			public static final String TABLE_ARTIST = "artists";
			public static final String COLOUMN_ARTIST_ID = "_id";
			public static final String COLOUMN_ARTIST_NAME = "name";
			public static final int ORDER_BY_NONE = 1;
			public static final int ORDER_BY_ASC = 2;
			public static final int ORDER_BY_DESC = 3;
			private Connection conn;
			public boolean open()
			{
				try {
					conn = DriverManager.getConnection(CONNECTION_STRING);
					return true;
				}
				catch(SQLException e)
				{
					System.out.println(e.getMessage());
					return false;
				}	
			}
			public void close()
			{
				try
				{
					if(conn!= null)
					{
						conn.close();
					}
				}
				catch(SQLException e)
				{
					System.out.println(e.getMessage());
				}
			}
			public List<Artist> queryArtists( int sortOrder)
			{
				StringBuilder sb = new StringBuilder("SELECT * FROM");
				sb.append(TABLE_ARTIST);
				if(sortOrder != ORDER_BY_NONE)
				{
				  sb.append("ORDER BY");
				  sb.append(COLOUMN_ARTIST_NAME);
				  sb.append("COLLATE NOCASE");
				  	if(sortOrder== ORDER_BY_DESC)
				  	{
				  		sb.append("DECS");
				  	}
				  	else
				  	{
				  		sb.append("ASC");
				  	}
				}
				
				try(Statement statement = conn.createStatement();
					ResultSet results = statement.executeQuery(sb.toString()))
				{
					List<Artist> artists = new ArrayList<>();
					while(results.next())
					{
						Artist artist = new Artist();
						artist.setId(results.getInt(COLOUMN_ARTIST_ID));
						artist.setName(results.getString(COLOUMN_ARTIST_NAME));
						artists.add(artist);
					}
					return artists;
				}
				catch (SQLException e)
				{
					System.out.println(e.getMessage());
					return null;
				}
			}
}
