package com.example.yoloswag.app.eventlist;

import android.content.Context;
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
import com.example.yoloswag.app.model.Event;
import com.example.yoloswag.app.model.EventsSingleton;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.view.CardListView;

/**
 * Created by dayouxia on 3/30/14.
 */
public class ListFragmentYo2 extends Fragment {

    public static final String EXTRA_TAB_NUM = "tabnumber";
    private int tabnumber;
    private ArrayList<Event> mEvent2List;

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

            CardExample2 cardx = new CardExample2(this.getActivity());
            CardHeader header = new CardHeader(getActivity());
            String headerTitle = mEvent2List.get(0).getEventName();
            //Set the header title
            header.setTitle(headerTitle);
            cardx.addCardHeader(header);


            cardx.title = "Come taste some wine!  " + i;
            cardx.secondaryTitle = "Address is : some street yo" + i;
            cardx.count = i + 2;
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

        protected TextView mTitle;
        protected TextView mSecondaryTitle;
        protected Button mImGoingButton;
        protected int count;

        protected String title;
        protected String secondaryTitle;


        public CardExample2(Context context) {
            super(context, R.layout.card_inner_content);
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
                    Toast.makeText(getContext(), "Click Listener card=" + getTitle(), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getActivity(), EventDetailActivity.class);
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

            if(mImGoingButton != null){
                mImGoingButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getActivity(), EventDetailActivity.class);
                        startActivity(i);
                    }
                });
            }


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
