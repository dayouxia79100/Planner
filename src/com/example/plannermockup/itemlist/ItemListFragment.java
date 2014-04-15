package com.example.plannermockup.itemlist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.plannermockup.DBConnectActivity;
import com.example.plannermockup.R;
import com.example.plannermockup.model.EventsSingleton;
import com.example.plannermockup.model.Item;
import com.example.plannermockup.model.MyUser;
import com.example.plannermockup.model.User;
import com.google.android.gms.internal.ha;
import com.google.android.gms.internal.m;
import com.google.android.gms.maps.GoogleMap.CancelableCallback;

/**
 * Created by dayouxia on 4/13/14.
 */
public class ItemListFragment extends ListFragment {
	
	private static final String processing_message = "Loading...";
    private static final String url_update_bring_item = DBConnectActivity.connect_url_header + "update_bring_item.php";
    private static final String TAG_UID = "uid";
    private static final String TAG_IID = "iid";
    private static final String TAG_ISBRINGING = "isbringing";

    private ArrayList<String> itemNameList;
    private ArrayList<Item> itemList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (itemNameList == null)
        	itemNameList = new ArrayList<String>();
        if (itemList == null)
        	itemList = new ArrayList<Item>();
        setListAdapter(new ItemListAdapter(itemNameList));
    }
    
    public void setItemList(ArrayList<Item> _itemList) {
    	itemList = _itemList;
    	itemNameList = new ArrayList<String>();
    	for (int i = 0; i < itemList.size(); i++) {
    		itemNameList.add(itemList.get(i).getItemName());
    	}
    }

    private class ItemListAdapter extends ArrayAdapter<String> {
    	
        public ItemListAdapter(ArrayList<String> itemList){
            super(getActivity(),0, itemList);
        }


        @Override
        public int getCount() {
            return itemNameList.size();

        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item, null);
            }
            final TextView itemNameTextView;
        	final TextView itemQuantityTextView;
        	final TextView itemUserNameTextView;
        	final Button bringItemButton;
        	
            itemNameTextView = (TextView)convertView.findViewById(R.id.item_name);
            itemQuantityTextView = (TextView)convertView.findViewById(R.id.item_quantity);
            itemUserNameTextView = (TextView)convertView.findViewById(R.id.item_user_name);
            bringItemButton = (Button)convertView.findViewById(R.id.bring_item_button);
            final Item mItem = itemList.get(position);
            
            itemNameTextView.setText(mItem.getItemName());
            int quantity = mItem.getQuantity();
            if (quantity > 0)
            	itemQuantityTextView.setText(Integer.toString(quantity));
            else
            	itemQuantityTextView.setText("");
            if (mItem.getUser().getName() != null) {
            	itemUserNameTextView.setText(mItem.getUser().getName());
            	if (mItem.getUser().getUid() == MyUser.getUser().getUid()) {
            		bringItemButton.setText(R.string.cancel_bring_button_text);
            	}
            	else {
            		//bringItemButton.setVisibility(View.GONE);
            	}
            }
            else
            	itemUserNameTextView.setText(R.string.no_one_bring_message);
            
            bringItemButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                	boolean cancel_bring = !(bringItemButton.getText().toString() == getResources().getString(R.string.bring_button_text));
                	int alertTitle;
                	int alertMessage;
                	if (cancel_bring) {
                		alertTitle = R.string.not_bring_title;
                		alertMessage = R.string.not_bring_message;
                	}
                	else {
                		alertTitle = R.string.bring_title;
                		alertMessage = R.string.bring_message;
                	}
                	
                	new AlertDialog.Builder(getActivity())
                    .setTitle(alertTitle)
                    .setMessage(alertMessage)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        	boolean cancel_bring = !(bringItemButton.getText().toString() == getResources().getString(R.string.bring_button_text));
                        	
                        	if (cancel_bring) {
                        		bringItemButton.setText(R.string.bring_button_text);
                                itemUserNameTextView.setText("");
                        	}
                        	else {
                        		bringItemButton.setText(R.string.cancel_bring_button_text);
                                itemUserNameTextView.setText(MyUser.getUser().getName());
							}
                            handleBringItem(mItem, !cancel_bring);
                        }
                     })
                     .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                         public void onClick(DialogInterface dialog, int which) {
                             // do nothing
                         }
                      })
                     .show();
                }
            });
            return convertView;
        }
        
        private void handleBringItem(Item item, boolean isBringing) {
    		List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair(TAG_IID, Integer.toString(item.getIid())));
            params.add(new BasicNameValuePair(TAG_UID, Integer.toString(MyUser.getUser().getUid())));
            if (isBringing)
            	params.add(new BasicNameValuePair(TAG_ISBRINGING, Integer.toString(1)));
            else
            	params.add(new BasicNameValuePair(TAG_ISBRINGING, Integer.toString(0)));
            DBConnectActivity dbConnect = new DBConnectActivity(getActivity(), params, url_update_bring_item, "POST", processing_message);
        	dbConnect.execute();
        }
    }
}
