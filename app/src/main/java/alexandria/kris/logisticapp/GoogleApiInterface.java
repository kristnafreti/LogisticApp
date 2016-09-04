package alexandria.kris.logisticapp;

import java.util.Map;

import alexandria.kris.logisticapp.model.DirectionModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by ASUS on 9/4/2016.
 */
public interface GoogleApiInterface {
    @GET("directions/json")
    Call<DirectionModel> getDistance(@QueryMap Map<String, String> params);
}
