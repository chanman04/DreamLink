package csc4330.mike.dreamlink.activities;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * The class that Dreams are made of.
 * Created by Matthew Wolff on 9/21/2015.
 *
 * NOTE: Properties of the class that are to be tracked by Parse should be synthesized by using
 * getX() and setX(param) methods. The body of such methods should follow an identical pattern.
 */
@ParseClassName("Dream")
public class Dream extends ParseObject {

    /**
     * Constructor used by Parse to generate preexisting objects. Do not modify.
     */
    public Dream() {};

    public void setTxt(String text) {
        put("text",text);
    }

    public String getTxt() {
        return getString("text");
    }

    public void setTitle(String title) {
        put("title",title);
    }

    public String getTitle() {
        return getString("title");
    }

    /**
     * Convenience constructor for setting up a brand new dream
     * @param title the title of the dream
     * @param text the tex of the dream
     */
    public Dream(String title, String text) {
        super();
        setTxt(text);
        setTitle(title);
    }
}
