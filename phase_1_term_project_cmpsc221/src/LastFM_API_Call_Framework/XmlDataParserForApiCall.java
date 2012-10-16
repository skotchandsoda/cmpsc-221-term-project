// Names:   John Duong and Scott Cheloha
// Section: 001
// Program: Simple Program for Last.fm API calls, Phase 1
// Date:    10/07/12

package LastFM_API_Call_Framework;

import java.io.*;
import java.net.URL;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

// A class containing various methods for parsing XML data
//   from a given document.
// The current implementation supports Last.fm API calls returning
//   XML files. It implements a modified SAX handler to parse data 
//   from those files into an array of Tracks and returns that array
//   to the caller.
//
// @author Scott cheloha
// @author John Duong
// @version 1.0
public class XmlDataParserForApiCall 
{
    // Instantiates a new SAX parser and returns Tracks parsed from the XML
    //   resource of the given API call.
    // The method creates a new SAX XMLreader and parses the data at apiCall's
    //   URL. The handler for the XMLreader returns an array of Tracks generated
    //   from the parsed data, which the method then returns to the caller.
    //
    // @param apiCall the API call to the Last.fm service to use for retrieving
    //                XML data for the parser
    // @return an array of tracks parsed from the XML resource indicated by apiCall.
    // @throw Exception any Exceptions encountered by the data stream or the
    //                  XMLreader and its handler
    public Track[] getTracksFromCall(LastFmApiMethodCall apiCall) throws Exception
    {
        URL xmlDocumentLocation = apiCall.getUrl();                     // A URL pointing to the XML resource we want to parse
        XMLReader dataParser = XMLReaderFactory.createXMLReader();      // The SAX reader for the XML resource at xmlDocumentLocation
        LastFmXmlHandler documentHandler;                               // A Handler for events reported by the XMLReader during its run
        documentHandler = new LastFmXmlHandler(apiCall.getTrackLimit());
        InputSource xmlDataStream;                                      // A data stream from the resource at xmlDocumentLocation
        xmlDataStream = new InputSource(xmlDocumentLocation.openStream());

        System.out.print("\nXML content retrieved from source.");
        dataParser.setContentHandler(documentHandler);                  // Set the local event handler as the event handler for events
                                                                        //   reported by dataParser (our XML reader)
        System.out.print("\nEvent Handler associated with XML Reader.");
        System.out.print("\nStarting XML parser ...");
        dataParser.parse(xmlDataStream);                                // Execute a read of the data in the input stream
        
        return documentHandler.getParsedTracks();                       // Return the array of Tracks from the handler
    }
}
