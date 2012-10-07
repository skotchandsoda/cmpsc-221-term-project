/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LastFM_API_Call_Framework;


public class TestDriver1 
{
    public static void main (String[] args)
    {
        LastFmApiMethodCall apiCall = new LastFmApiMethodCall("chart.getTopTracks", 50);
        Track[] tracksFromCall = new Track[apiCall.getTrackLimit()];
        XmlDataParserForApiCall parser = new XmlDataParserForApiCall();
        
        try
        {
            tracksFromCall = parser.getTracksFromCall(apiCall);
            System.out.print("\nArray of tracks retrieved.");
            
            System.out.print("\nPrinting Data ... ");
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
