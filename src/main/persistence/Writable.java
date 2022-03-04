package persistence;

import org.json.JSONObject;

// interface to make an object writable
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
