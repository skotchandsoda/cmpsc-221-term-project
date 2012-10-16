// Names:   John Duong and Scott Cheloha
// Section: 001
// Program: Simple Program for Last.fm API calls, Phase 1
// Date:    10/08/12

package LastFM_API_Call_Framework;

public class TestDriver2
{
    public static void main (String[] args)
    {
        LastFmApiMethodCall apiCall = new LastFmApiMethodCall("chart.getHypedTracks", 107);// A test API call
        Track[] tracksFromCall = new Track[apiCall.getTrackLimit()];                       // An array for storing the data parsed from the API call
        XmlDataParserForApiCall parser = new XmlDataParserForApiCall();                    // An instance of a SAX parser for the XML retrieve from the API call
        
        System.out.print("\nThe program will execute the following Last.fm API call" + apiCall.toString() + "\n");
        
        try
        {
            System.out.print("\nStarting XML retrieval and parsing ...");
            tracksFromCall = parser.getTracksFromCall(apiCall);
            System.out.print("\nData retrieved.");
            
            System.out.print("\nPrinting Data to console ...\n");
            for (Track track : tracksFromCall)
            {
                System.out.println(track.toString());
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.toString());
            System.out.println(ex.getStackTrace());
        }
    }
}
