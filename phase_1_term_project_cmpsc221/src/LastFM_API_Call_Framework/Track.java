package LastFM_API_Call_Framework;


public class Track {
    private String trackName;   // Name of the track
    private String artist;      // Name of the Artist of the track
    private String chartName;   // Name of the chart the Track appeared on
    private int chartRank;      // The Track's rank on the chart it appeared on
    
    public Track() {
        // POST: Sets trackName, artist, and chart Name to a blank string
        //       and chartRank equal to zero
        trackName = "";
        artist = "";
        chartName = "";
        chartRank = 0;
    }
    
    public Track(String initTrackName, String initArtist, String initChartName, int initchartRank){
        trackName = initTrackName;
        artist = initArtist;
        chartName = initChartName;
        chartRank = initchartRank;
    }
    
    public void setTrackName(String trackName) {
        // PRE: songName is initialized
        // POST: Sets the method varible songName to the class varible songName
        this.trackName = trackName;
    }
    
    public String getTrackName() {
        // POST: Returns the string varible trackName
        return trackName;
    }
            
    public void setArtist(String artist) {
        // PRE: artist is initialized
        // POST: Sets the method varible artist to the class varible artist
        this.artist = artist;
    }
    
    public String getArtist() {
        // POST: Returns the string varible artist
        return artist;
    }
    
    public void setChartRank(int chartRank) {
        this.chartRank = chartRank;
    }
    
    public int getChartRank() {
        return chartRank;
    }
    
    public String toString() {
        // FCTVAL == String 
        return "\nTrack " + chartRank 
             + "\nName: " + trackName 
             + "\nArtist: " + artist;
    }
}