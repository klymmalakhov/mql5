package rest.models;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataCreateLink {

    @SerializedName("type")
    private String data;

    @SerializedName("url")
    private String url;

    @SerializedName("utm")
    private String utm;
}
