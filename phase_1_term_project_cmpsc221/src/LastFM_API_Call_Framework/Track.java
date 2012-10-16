// Names:   John Duong and Scott Cheloha
// Section: 001
// Program: Simple Program for Last.fm API calls, Phase 1
// Date:    10/08/12

package LastFM_API_Call_Framework;

/**
 * This class contains methods for Track.
 *  
 * @author John Duong
 * @author Scott Cheloha
 * @version 1.0 10/8/2012
 * 
 */
public class Track {
    private String trackName;   // Name of the track
    private String artist;      // Name of the Artist of the track
    private String chartName;   // Name of the chart the Track appeared on
    private int chartRank;      // The Track's rank on the chart it appeared on
    
    /**
     * A default constructor that sets trackName, artist, and chart Name to a blank string
     * and chartRank equal to zero
     * 
     */
    public Track() {
        trackName = "";
        artist = "";
        chartName = "";
        chartRank = 0;
    }
    
    /**
    * A initializer constructor that initializes initTrackName, initArtist, 
    * initChartName, and initchartRank. Track object is created with trackName = initTrackName,
    * artist = initArtist, chartName = initChartName, and chartRank = initchartRank.
    * 
    * @param initTrackName the string to be assign a value
    * @param initArtist the string to be assign a value
    * @param initChartName the string to be assign a value
    * @param initchartRank the integer to be assign a value
    */    
    public Track(String initTrackName, String initArtist, String initChartName, int initchartRank){
        trackName = initTrackName;
        artist = initArtist;
        chartName = initChartName;
        chartRank = initchartRank;
    }
    
    /**
    * Setter method that initializes trackName and sets it equal to the class data member trackName
    * 
    * @param trackName the string to be assign a value
    */
    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }
    
    /**
     * Getter method that returns trackName
     * 
     * @return the name of the track
     */
    public String getTrackName() {
        return trackName;
    }
            
    /**
    * Setter method initializes artist and sets it equal to the class data member artist
    * 
    * @param artist the string to be assign a value
    */    
    public void setArtist(String artist) {
        this.artist = artist;
    }
    
    /**
     * Getter method that returns artist
     * 
     * @return the name of artist of the track
     */    
    public String getArtist() {
        return artist;
    }
    
    /**
     * Setter method that returns artist
     * 
     * @aaram chartName the name of the chart the the track appeared on
     */  
    public void setChartName(String chartName) {
        this.chartName = chartName;
    }
    
    /**
     * Getter method that returns chartName
     * 
     * @return the name of the chart the track appeared on
     */  
    public String getChartName() {
        return chartName;
    }
    
    /**
     * Setter method initializes chartRank and sets it equal to the class data member chartRank
     * 
     * @param chartRank the string to be assign a value
     */
    public void setChartRank(int chartRank) {
        this.chartRank = chartRank;
    }
    
    /**
     * Getter method that returns chartRank
     * 
     * @return the rank of the track
     */    
    public int getChartRank() {
        return chartRank;
    }

    /**
     * toString() method to represent the current object
     * 
     * @return chartRank, trackNAme, and artist
     */
    public String toString() {
        return "\nName: " + trackName 
             + "\nArtist: " + artist
             + "\nRank " + chartRank;
    }
}