package com.example.yoloswag.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;


import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.view.CardListView;
import it.gmariotti.cardslib.library.view.CardView;

/**
 * Created by dayouxia on 3/30/14.
 */
public class CardListExpandable extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_card_list, container, false);

        CustomCard card = new CustomCard(getActivity());
        card.setInnerText("HELLU");



        //Create a CardHeader
        CardHeader header = new CardHeader(getActivity());
        //Set the header title
        header.setTitle("Someone's holy shit fuckin party");



        card.setInnerLayout(R.layout.inner_layout);


        card.setTitle("OMFG motherfucker!!!!!");





        //Set visible the expand/collapse button
        header.setButtonExpandVisible(true);

        //Add Header to card
        card.addCardHeader(header);

        //This provides a simple (and useless) expand area
        CustomExpandCard expand = new CustomExpandCard(getActivity());



        //Add Expand Area to Card
        card.addCardExpand(expand);

        //Set card in the cardView
        final CardView cardView = (CardView) v.findViewById(R.id.card_view);

        // It is not required.
        card.setOnExpandAnimatorEndListener(new Card.OnExpandAnimatorEndListener() {
            @Override
            public void onExpandEnd(Card card) {
                //TODO: check if hidden area is visible and it would be better an animator to do this
            }
        });

        cardView.setCard(card);

        return v;

    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
