package csc4330.mike.dreamlink.activities;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseRelation;
import com.parse.ParseUser;

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

    public void setDream(String text) {put("text",text);
    }

    public String getDream() {return getString("text");
    }

    public void setTitle(String title) {put("title",title);
    }

    public String getTitle() {return getString("title");
    }

    public ParseUser getDreamer() {return (ParseUser)getParseObject("dreamer");
    }

    public void setDreamer(ParseUser dreamer) {put("dreamer",dreamer);
    }

    public ParseRelation<Hashtag> getHashtags() {
        return getRelation("hashtags");
    }

    /**
     * Convenience constructor for setting up a brand new dream
     * @param title the title of the dream
     * @param dream the tex of the dream
     */
    public Dream(String title, String dream) {
        super();
        setDream(dream);
        setTitle(title);
        //setDreamer(dreamer);
    }
}
