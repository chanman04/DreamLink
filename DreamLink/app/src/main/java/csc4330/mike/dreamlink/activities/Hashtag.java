package csc4330.mike.dreamlink.activities;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseRelation;

import csc4330.mike.dreamlink.components.Dream;

/**
 * This class was created for the creation of Hashtag objects. This specifically deals with when a
 * user adds a hashtag to their dream.
 */

@ParseClassName("Hashtag")
public class Hashtag extends ParseObject {

    /**
     * Required zero-argument constructor
     */
    public Hashtag() {}

    public void setTag(String tag) {
        put("tag",tag);
    }

    public String getTag() {
        return getString("tag");
    }

    public ParseRelation<Dream> getDreams() {
        return getRelation("dreams");
    }

    public Hashtag(String tag) {
        super();
        setTag(tag);
    }
}
