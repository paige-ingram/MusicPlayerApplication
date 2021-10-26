package persistence;

import org.json.JSONArray;
import org.json.JSONObject;

// Citation: JsonSerializationDemo - JsonWritable.java
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
