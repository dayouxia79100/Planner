package com.example.yoloswag.app;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by dayouxia on 3/30/14.
 */
public class CustomCard extends Card {

    public CustomCard(Context context) {
        this(context, R.layout.inner_layout);
    }

    /**
     *
     * @param context
     * @param innerLayout
     */
    public CustomCard(Context context, int innerLayout) {
        super(context, innerLayout);
    }


    private TextView mInnerTextContent;
    private ImageView mImageView;

    private String innerText;

    public void setInnerText(String innerText) {
        this.innerText = innerText;
    }

    public void setupInnerViewElements(ViewGroup parent, View view) {

        //Retrieve elements
        mInnerTextContent = (TextView) parent.findViewById(R.id.inner_text_content);
        mImageView = (ImageView) parent.findViewById(R.id.img_description);


        if(mInnerTextContent != null){
            mInnerTextContent.setText(innerText);
        }



    }
}
