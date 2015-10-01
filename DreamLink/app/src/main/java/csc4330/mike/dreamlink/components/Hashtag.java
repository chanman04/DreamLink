package csc4330.mike.dreamlink.components;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Mike on 9/23/15.
 */
public class Hashtag {

    private String tag;

    public Hashtag(){

    }

    public Hashtag(String hashtag){

        tag = hashtag;

    }

    public String getHashTags(String hashtag){

            return tag;

        }
    public void setHashTags(String hashtag){

        if (hashtag.contains("#")){

            hashtag.split(" ");
            this.tag = hashtag;
            }

        }


    }


