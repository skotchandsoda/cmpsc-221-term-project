// Names:   John Duong and Scott Cheloha
// Section: 001
// Program: Simple Program for Last.fm API calls, Phase 1
// Date:    10/07/12

package LastFM_API_Call_Framework;

import java.io.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

// An extension of the DefaultHandler class tailored for the XML documents 
//   returned by Last.fm API calls.
// LastFmHandler reimplements methods in the DefaultHandler class
// to make them more appropriate for navigating the XML files returned by
// the Last.fm API.
// The custom handler takes into account the structure of the XML documents returned
// by the Last.fm API to best parse track names and artists from XML into
// instances of the Track class.
//
// @author Scott Cheloha
// @author John Duong
// @version 1.0
public class LastFmXmlHandler extends DefaultHandler
{    
    private int numberOfTracksInDocument;                 // The total number of tracks in the document.
    private Track[] arrayOfParsedTracks;                  // An array of Tracks. Space for the array is dynamically allocated
                                                          //   whenever the handler opens a new document or input stream.
    private int numberOfTracksParsed = 0;                 // A count of Tracks parsed from the XML document.
                                                          //   The count increments each time a Track's information is fully parsed
                                                          //   from the document.
    private boolean parserIsInArtistElementBlock = false; // Indicates whether the Document Handler is within an Artist
                                                          //   Element block in the XML Document.
    private boolean storeParsedData = false;              // Indicates whether the characters() method should store the parsed
                                                          //   character data in the active Track
    
    
    // An Initializer constructor for the handler
    // The constructor creates a new handler and sets
    // the number of tracks to parse for this instance
    // of the handler.
    //
    // @param numberOfTracksToParse the number of tracks to parse from the input source
    public LastFmXmlHandler (int numberOfTracksToParse)
    {
        numberOfTracksInDocument = numberOfTracksToParse;
    }
    
    // An event indicating the start of a new XML document.
    // The method allocates new space for arrayOfParsedTracks[]
    //
    // @throws SAXException any exceptions encountered by the XML parser
    public void startDocument() throws SAXException
    {
        System.out.print("\nParser reached start of XML document");
        System.out.print("\nAllocating space for new data ... ");
        arrayOfParsedTracks = new Track[numberOfTracksInDocument];
        System.out.print("DONE.");
    }
    
    public void endDocument() throws SAXException
    {
        System.out.print("\nParser reached end of document, " + getNumberOfTracksParsed() + " new items created.");
        
    }
    
    // An event indicating that the parser
    // This method is invoked each time the parser finds the opening of a new Element.
    //   When a starting Element is encountered in the XML document,
    //   the method evaluates whether or not it contains Track Name or Artist
    //   information and sets the Global boolean values accordingly so that
    //   the characters() method can store useful information in the current
    //   Track on arrayOfParsedTracks[].
    //
    // @param uri the Uniform Resource Indentifier for the document being parsed
    // @param localName the local name of the Element within the parser
    // @param tagName the name of the Element within the document being parsed
    // @param attributes any XML attributes associated with the Element
    // @throws SAXException any exceptions encountered by the XML parser
    public void startElement(String uri, String localName, String tagName, Attributes attributes) throws SAXException 
    {
        if (tagName.equalsIgnoreCase("TRACK"))
        {
            arrayOfParsedTracks[numberOfTracksParsed] = new Track();                          // Dynamically allocate space for the new Track on the array
            arrayOfParsedTracks[numberOfTracksParsed].setChartRank(numberOfTracksParsed + 1); // The start of a Track element indicates a new track
                                                                                              // in the document, whose chart rank must be 1 greater
                                                                                              // than the number of tracks already parsed by the handler
        }
        else if (tagName.equalsIgnoreCase("NAME"))
        {
            storeParsedData = true;              // Character data within Name Elements is the only useful information
                                                 // so we indicate that the data within the open Element should be stored
                                                 // on the active Track
        }
            
        if (tagName.equalsIgnoreCase("ARTIST"))
        {
            parserIsInArtistElementBlock = true; // The start of an Artist Element indicates that all information
                                                 // parsed (until the Element closes) is relevant to the artist of the track.
        }

    }
    
    // An event indicating that the parser has reached the end of an element in the document.
    // Depending on the name of the Element that has closed, the method will either update
    //   the parser's location in the document or increment the active Track on
    //   arrayOfParsedTracks[].
    //
    // @param uri the Universal Resource Indicator for the active document
    // @param localName the local name within the parser for the Element
    // @param tagName the name of the Element within the document itself
    // @throws SAXException any exceptions encountered by the XML parser
    public void endElement(String uri, String localName, String tagName) throws SAXException
    {
        if (tagName.equalsIgnoreCase("TRACK"))
        {
            incrementNumberOfTracksParsed(); // A Track Element has closed, so we increment the number of tracks parsed.
        }
        else if (tagName.equalsIgnoreCase("NAME"))
        {
            storeParsedData = false;             // The Name Element has closed, so the characters() method should discard
                                                 // any character data it collects until a new Name Element opens in the document
        }
        else if (tagName.equalsIgnoreCase("ARTIST"))
        {
            parserIsInArtistElementBlock = false; // The Artist Element has closed, so character data parsed
                                                  // is not relevant to the artist of the track.
        }
    }
    
    // An event indicating that the parser has found character data in the document.
    // Depending on where the parser is located in the document (either within an
    //   Artist Element block or outside of one) the method will populate an 
    //   appropriate field in the current Track on arrayOfParsedTracks[].
    //   
    // @param characters the character data the parser has retrieved from the document
    // @param startIndex the index where the relevant character data begins
    // @param lengthOfString the length of the string of characters parsed from this
    //                       section of the document
    // @throws SAXException any exceptions encountered by the XML parser
    public void characters(char[] characters, int startIndex, int lengthOfString) throws SAXException
    {
        if (storeParsedData && !parserIsInArtistElementBlock)
        {
            String trackName = new String(characters, startIndex, lengthOfString);
            arrayOfParsedTracks[numberOfTracksParsed].setTrackName(trackName);
        }
        else if (storeParsedData && parserIsInArtistElementBlock)
        {
            String artistName = new String(characters, startIndex, lengthOfString);
            arrayOfParsedTracks[numberOfTracksParsed].setArtist(artistName);
        }
    }
    
    // Adds 1 to the total number of tracks parsed.
    protected void incrementNumberOfTracksParsed()
    {
        // POST: numberOfTracksParsed == numberOfTracksParsed + 1
        numberOfTracksParsed++;
    }
    
    // Returns the current total number of tracks parsed by the reader
    public int getNumberOfTracksParsed()
    {
        return numberOfTracksParsed;
    }
    
    // Returns the tracks parsed from the XML document
    //
    // @return arrayOfParsedTracks the tracks parsed from the XML document
    public Track[] getParsedTracks()
    {
        System.out.print("\nReturning parsed data from handler ... ");
        return arrayOfParsedTracks;
    }
}
