package com.example.yoloswag.app.model;

import java.util.ArrayList;

/**
 * Created by dayouxia on 2/13/14.
 */
public class EventsSingleton {

    private static EventsSingleton sEvents;

    private ArrayList<Event> mEvents1;
    private ArrayList<Event> mEvents2;
    private ArrayList<Event> mEvents3;

    private EventsSingleton (){

        mEvents1 = new ArrayList<Event>();
        mEvents2 = new ArrayList<Event>();
        mEvents3 = new ArrayList<Event>();
        int i;
        for(i = 0; i < 12; i++){
            Event oneEvent = new Event();
            oneEvent.setAddress(i+"address");
            oneEvent.setEventname(i+"event name");
            oneEvent.setGoing(i%2 == 0);
            oneEvent.setTime(i+"time");
            mEvents1.add(oneEvent);
        }
        for(i = 24; i < 35; i++){
            Event oneEvent = new Event();
            oneEvent.setAddress(i+"address");
            oneEvent.setEventname(i+"event name");
            oneEvent.setGoing(i%2 == 0);
            oneEvent.setTime(i+"time");
            mEvents2.add(oneEvent);
        }
        for(i = 36; i < 38; i++){
            Event oneEvent = new Event();
            oneEvent.setAddress(i+"address");
            oneEvent.setEventname(i+"event name");
            oneEvent.setGoing(i%2 == 0);
            oneEvent.setTime(i+"time");
            mEvents3.add(oneEvent);
        }


    }

    public static EventsSingleton get(){
        if(sEvents == null){
            sEvents = new EventsSingleton();
        }
        return sEvents;
    }

    public ArrayList<Event> getEventList(int position){
        switch (position){
            case 0:
                return mEvents1;
            case 1:
                return mEvents2;
            case 2:
                return mEvents3;
            default:
                return null;
        }

    }
}
