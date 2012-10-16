// Names:   John Duong and Scott Cheloha
// Section: 001
// Program: Simple Program for Last.fm API calls, Phase 1
// Date:    10/08/12

package LastFM_API_Call_Framework;

import java.net.URL;

/**
 * This class contains methods for LastFmApiMethodCall.
 * 
 * @author John Duong
 * @author Scott Cheloha
 * @version 1.0 10/7/2012
 * 
 */
public class LastFmApiMethodCall {
    private static final String URL_ROOT = "http://ws.audioscrobbler.com/2.0/"; // The root for all URLs within the Last.fm API
    private static final String API_KEY = "5a28347eaf3170a4f70a88ccffe5730b";   // the authentication key used by Last.fm to verify access to their database
    private String lastFmApiMethod;                                             // Type of charts on last.fm, e.g. hyped tracks, top tracks, etc...
    private int trackLimit;                                                     // The number of results to fetch per page
    
    /**
     * A default constructor that sets lastFmApiMethod to a blank string and
     * sets trackLimit equal to fifty.
     * 
     */
    public LastFmApiMethodCall() {
        lastFmApiMethod = "";
        trackLimit = 50;
    }

    /**
     * A initializer constructor that initializes initLastFmApiMethod and initTrackLimit.
     * LastFmApiMethodCall object is created with lastFmApiMethod = initLastFmApiMethod
     * and trackLimit = initTrackLimit.
     * 
     * @param initLastFmApiMethod the string to be assign a value
     * @param initTrackLimit the integer to be assign a value
     */    
    public LastFmApiMethodCall (String initLastFmApiMethodName, int initTrackLimit) {
        this.setApiMethodName(initLastFmApiMethodName);
        this.setTrackLimit(initTrackLimit);
    }
    
    /**
     * Setter method that initializes lastFmApiMethod and
     * sets it equal to the class data member lastFmApiMethod
     * 
     * @param lastFmApiMethod the string to be assign a value
     */    
    public void setApiMethodName(String methodName) throws IllegalArgumentException {
        if (LastFmApiMethodIsSupported(methodName)) {
            this.lastFmApiMethod = methodName;
        }
        else {
            throw new IllegalArgumentException("Supplied Last.fm api method call is not supported, use a supported API method.");
        }
    }
    
    /**
     * Getter method that returns lastFmApiMethod
     * 
     * @return lastFmApiMethod returns the names of the chart from Last.fm
     */
    public String getMethodName() {
        return lastFmApiMethod;
    }
 
    /**
     * A boolean method to check if the chart name of Last.fm API exist.
     * 
     * @return true returns lastFmApiMethod, false returns blank
     */    
    public boolean LastFmApiMethodIsSupported(String methodName) {
        if (methodName == "chart.getHypedTracks" 
         || methodName == "chart.getTopTracks") {
            return true;   
        }
        
        return false;
    }

    /**
     * Setter method initializes trackLimit and sets it equal to the class data member trackLimit
     * 
     * @param trackLimit the string to be assign a value
     */    
    public void setTrackLimit(int trackLimit) throws IllegalArgumentException { 
        if (trackLimit >= 1 && trackLimit <= 1000) {            
            this.trackLimit = trackLimit;
        }
        
        if (trackLimit < 1)
            throw new IllegalArgumentException("Track Limit must be greater than 0, use a larger value.");
        else if (trackLimit > 1000)
            throw new IllegalArgumentException("Track Limit cannot exceed 1000, use a smaller value.");
            
    }
    
    /**
     * Getter method returns trackLimit
     * 
     * @return A number is returned from trackLimit
     */    
    public int getTrackLimit() {
        return trackLimit;
    }  
    
    /**
     * This methods creates a java object of URL
     * 
     * @return URL object is return
     * @throws Exception 
     */    
    public URL getUrl() throws Exception {
        return new URL(getUrlString());
        
    }
    /**
     * The method returns text representation of the URL
     * 
     * @return Text version of the URL
    ! */
    public String getUrlString() {
        return (URL_ROOT + "?method=" + lastFmApiMethod 
              + "&limit=" + trackLimit 
              + "&api_key=" + API_KEY);
    }
    
    /**
     * toString() method to represent the current object
     * 
     * @return lastFmApiMethod, trackLimit, API_KEY
     */
    public String toString() {
        return ("\nMethod Name: " + lastFmApiMethod
              + "\nTrack Limit: " + trackLimit
              + "\nAPI Key: " + API_KEY
              + "\nURL: " + getUrlString()); 
    }
}
