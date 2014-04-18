package com.example.yoloswag.app.eventlist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yoloswag.app.R;
import com.example.yoloswag.app.eventdetail.EventDetailActivity;
import com.example.yoloswag.app.helper.DBConnectActivity;
import com.example.yoloswag.app.model.Event;
import com.example.yoloswag.app.model.EventsSingleton;
import com.example.yoloswag.app.model.MyUser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.view.CardListView;


public class ListFragmentYo2 extends Fragment {

    public static final String EXTRA_TAB_NUM = "tabnumber";
    private int tabnumber;
    private ArrayList<Event> mEvent2List;

    private static final String TAG_UID = "uid";
    private static final String TAG_EID = "eid";
    private static final String TAG_STATUS = "status";
    private static final String TAG_NAME = "name";
    private static final String url_get_user_name = DBConnectActivity.connect_url_header + "get_user_name.php";
    private static final String url_update_event_status = DBConnectActivity.connect_url_header + "update_event_status.php";
    private static final String processing_message = "Loading...";

    public static ListFragmentYo2 newInstance(int i){
        ListFragmentYo2 fragment = new ListFragmentYo2();

        Bundle args = new Bundle();
        args.putInt(EXTRA_TAB_NUM,i);
        fragment.setArguments(args);
        return fragment;
    }


    private ArrayList<String> mNameList = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mNameList.add("Ming");
        mNameList.add("Summer");
        mNameList.add("Charlie");



        tabnumber = getArguments().getInt(EXTRA_TAB_NUM);
        EventsSingleton.get().getEventList(tabnumber);

        // get models ready
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment_2, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initCards();
    }

    private void initCards() {


        mEvent2List = EventsSingleton.get().getEventList(tabnumber);

        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i = 0; i < mEvent2List.size(); i++) {

            CardExample2 cardx = new CardExample2(this.getActivity(), mEvent2List.get(i));
            CardHeader header = new CardHeader(getActivity());

            String headerTitle = mEvent2List.get(i).getEventName();
            Event currentEvent = mEvent2List.get(i);

            //Set the header title
            header.setTitle(headerTitle);
            cardx.addCardHeader(header);


            cardx.title = currentEvent.getDescription();
            cardx.secondaryTitle = currentEvent.getTime();
            cardx.count = i;
            cards.add(cardx);


        }



        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(getActivity(),cards);
        mCardArrayAdapter.setInnerViewTypeCount(1);

        // An alternative is to write a own CardArrayAdapter
        // MyCardArrayAdapter mCardArrayAdapter = new MyCardArrayAdapter(getActivity(),cards);


        CardListView listView = (CardListView) getActivity().findViewById(R.id.carddemo_list_base2);
        if (listView!=null){
            listView.setAdapter(mCardArrayAdapter);
        }


    }


    public class MyCardArrayAdapter extends CardArrayAdapter{


        /**
         * Constructor
         *
         * @param context The current context.
         * @param cards   The cards to represent in the ListView.
         */
        public MyCardArrayAdapter(Context context, List<Card> cards) {
            super(context, cards);
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }
    }

    public class CardExample2 extends Card{

        public static final String EXTRA_EVENT = "event";

        protected TextView mTitle;
        protected TextView mSecondaryTitle;
        protected Button mImGoingButton;
        protected int count;

        protected String title;
        protected String secondaryTitle;
        private Event currentEvent;


        public CardExample2(Context context, Event event) {
            super(context, R.layout.card_inner_content);
            currentEvent = event;
            init();
        }

        private void init(){

            //Create a CardHeader
            CardHeader header = new CardHeader(getActivity());
            addCardHeader(header);


            //Add ClickListener
            setOnClickListener(new OnCardClickListener() {
                @Override
                public void onClick(Card card, View view) {
                    Intent i = new Intent(getActivity(), EventDetailActivity.class);
                    i.putExtra(EXTRA_EVENT, currentEvent);
                    startActivity(i);

                }
            });

            setSwipeable(true);
        }


        @Override
        public void setupInnerViewElements(ViewGroup parent, View view) {

            //Retrieve elements
            mTitle = (TextView) parent.findViewById(R.id.carddemo_myapps_main_inner_title);
            mSecondaryTitle = (TextView) parent.findViewById(R.id.carddemo_myapps_main_inner_secondaryTitle);
            mImGoingButton = (Button) parent.findViewById(R.id.going_button);


            final Button addButton = (Button)parent.findViewById(R.id.going_button);
            String statusString = "";
            switch (currentEvent.getStatus()) {
                case 0:
                    statusString = "Pending";
                    break;
                case -1:
                    statusString = "Declined";
                    break;
                case 1:
                    statusString = "Going";
                    break;
            }
            //addButton.setText(statusString);
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    new AlertDialog.Builder(getActivity())
                            .setTitle(R.string.join_event_title)
                            .setMessage(R.string.join_event_message)
                            .setPositiveButton(R.string.accept_event, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //addButton.setText("Going");
                                    updateStatusOnDB(1);
                                    EventsSingleton.get().addToMySchedule(currentEvent);
                                    Crouton.makeText(getActivity(), "Event added to my schedule", Style.INFO).show();
                                    view.setBackground(getResources().getDrawable(R.drawable.ic_calendar_check));

                                }
                            })

                            .setNegativeButton(R.string.decline_event, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                   // addButton.setText("Declined");
                                    updateStatusOnDB(-1);
                                    EventsSingleton.get().removeFromMySchedule(currentEvent);
                                    Crouton.makeText(getActivity(), "Event removed from my schedule", Style.INFO).show();
                                    view.setBackground(getResources().getDrawable(R.drawable.ic_calendar_gray));
                                }
                            })
                            .show();
                }

                private void updateStatusOnDB(int status) {
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair(TAG_EID, Integer.toString(currentEvent.getEid())));
                    params.add(new BasicNameValuePair(TAG_UID, Integer.toString(MyUser.getUser().getUid())));
                    params.add(new BasicNameValuePair(TAG_STATUS, Integer.toString(status)));
                    DBConnectActivity dbConnect = new DBConnectActivity(getActivity(), params, url_update_event_status, "POST", processing_message);
                    dbConnect.execute();
                }
            });



            if (mTitle != null)
                mTitle.setText(title);

            if (mSecondaryTitle != null)
                mSecondaryTitle.setText(secondaryTitle);


        }

        @Override
        public int getType() {
            //Very important with different inner layouts
            return 1;
        }
    }

}
