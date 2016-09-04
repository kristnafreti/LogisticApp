
package alexandria.kris.logisticapp.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeocodedWaypoint {

    @SerializedName("geocoder_status")
    @Expose
    private String geocoderStatus;
    @SerializedName("place_id")
    @Expose
    private String placeId;
    @SerializedName("types")
    @Expose
    private List<String> types = new ArrayList<String>();
    @SerializedName("partial_match")
    @Expose
    private Boolean partialMatch;

    /**
     * 
     * @return
     *     The geocoderStatus
     */
    public String getGeocoderStatus() {
        return geocoderStatus;
    }

    /**
     * 
     * @param geocoderStatus
     *     The geocoder_status
     */
    public void setGeocoderStatus(String geocoderStatus) {
        this.geocoderStatus = geocoderStatus;
    }

    /**
     * 
     * @return
     *     The placeId
     */
    public String getPlaceId() {
        return placeId;
    }

    /**
     * 
     * @param placeId
     *     The place_id
     */
    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    /**
     * 
     * @return
     *     The types
     */
    public List<String> getTypes() {
        return types;
    }

    /**
     * 
     * @param types
     *     The types
     */
    public void setTypes(List<String> types) {
        this.types = types;
    }

    /**
     * 
     * @return
     *     The partialMatch
     */
    public Boolean getPartialMatch() {
        return partialMatch;
    }

    /**
     * 
     * @param partialMatch
     *     The partial_match
     */
    public void setPartialMatch(Boolean partialMatch) {
        this.partialMatch = partialMatch;
    }

}
