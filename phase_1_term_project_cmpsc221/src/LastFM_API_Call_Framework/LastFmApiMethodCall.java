package LastFM_API_Call_Framework;

import java.net.URL;

public class LastFmApiMethodCall {
    private static final String URL_ROOT = "http://ws.audioscrobbler.com/2.0/";       // The root for all URLs within the Last.fm API
    private String lastFmApiMethod;     // Type of charts on last.fm, e.g. hyped tracks, top tracks, etc...
    private int trackLimit;       // The number of results to fetch per page
    private static final String API_KEY = "5a28347eaf3170a4f70a88ccffe5730b"; // the authentication key used by Last.fm to verify access to their database
    
    public LastFmApiMethodCall() {
        lastFmApiMethod = "";
        trackLimit = 50;
    }
    
    public LastFmApiMethodCall (String initLastFmApiMethod, int initTrackLimit) {
        lastFmApiMethod = initLastFmApiMethod;
        trackLimit = initTrackLimit;
    }
    
    public void setApiMethodName(String lastFmApiMethod) {
        this.lastFmApiMethod = lastFmApiMethod;
    }
    
    public String getMethodName() {
        return lastFmApiMethod;
    }
    
    public boolean apiMethodIsSupported() {
        if (lastFmApiMethod == "chart.getHypedTracks" 
         || lastFmApiMethod == "chart.getTopTracks"){
            return true;   
        }
        else {
            return false;
        }
    }
    
    public void setTrackLimit(int trackLimit) { 
        this.trackLimit = trackLimit;
    }
    
    public int getTrackLimit() {
        return trackLimit;
    }  
    
    public URL getUrl() throws Exception{
        return new URL(getUrlString());
        
    }
    
    public String getUrlString() {
        return (URL_ROOT + "?method=" + lastFmApiMethod 
             + "&limit=" + trackLimit 
             + "&api_key=" + API_KEY);
    }
    
    public String toString() {
        return ("Method Name: " + lastFmApiMethod
              + "Track Limit: " + trackLimit
              + "API Key: " + API_KEY
              + "URL:" + getUrlString()); 
    }
}
