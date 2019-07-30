package rest.models;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateLinkModel {

    @SerializedName("data")
    private DataCreateLink data;
}
