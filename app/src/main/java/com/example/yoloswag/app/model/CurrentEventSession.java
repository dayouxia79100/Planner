package com.example.yoloswag.app.model;


public class CurrentEventSession {
    private static CurrentEventSession sCurrentEventSession;
    private static Event mEvent;

    public static CurrentEventSession get(){
        if(sCurrentEventSession == null){
            sCurrentEventSession = new CurrentEventSession();
        }

        return sCurrentEventSession;
    }

    private CurrentEventSession (){
        mEvent = new Event();


    }

    public void clear(){
        mEvent = null;
        mEvent = new Event();

    }

    public Event getEvent(){
        return mEvent;
    }
    public void setEvent(Event event){
        mEvent = event;
    }


}
