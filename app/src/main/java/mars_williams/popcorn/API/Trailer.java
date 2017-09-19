package mars_williams.popcorn.API;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mars_williams on 9/18/17.
 */

public class Trailer {

    @SerializedName("name")
    private String name;

    @SerializedName("source")
    private String source;

    public Trailer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
