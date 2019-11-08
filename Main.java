package data;

import java.util.List;

public class Main {
	public static void main(String[] args)
	{
		Data d = new Data();
		if(!d.open())
		{
			System.out.println("Can't open Data");
			return;
		}
		List<Artist> artists = d.queryArtists(Data.ORDER_BY_DESC);
		if( artists== null)
		{
			System.out.println("No artists");
			return;
		}
		for(Artist artist : artists)
		{
			System.out.println("ID = "+ artist.getId()+ "Name = "+ artist.getName());
		}
		d.close();
	}
}
