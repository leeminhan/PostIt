package com.example.postit.eventlisting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.postit.R;

import java.util.ArrayList;

public class EventListFragment extends Fragment {

    // strings for logcat, for Log.i(TAG, msg)
    private static final String COMMON_TAG = "CombinedLifeCycle";
    private static final String FRAGMENT_NAME = EventListFragment.class.getSimpleName();
    private static final String TAG = COMMON_TAG;

    private Activity hostActivity;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<String> myDataset;

    // hardcode the myDataset(event Title)
    private ArrayList<String> setMyDatasetHardCodePasser(ArrayList<String> theData){
        theData.add("event1");
        theData.add("event2");
        theData.add("event3");
        theData.add("event4");
        theData.add("event5");
        theData.add("event6");
        theData.add("event7");
        theData.add("event8");
        theData.add("event9");
        theData.add("event10");
        return theData;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hostActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_event_list, container, false);


        recyclerView = (RecyclerView) view.findViewById(R.id.vRecycleViewViewEvents);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(hostActivity);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        myDataset = new ArrayList<String>();
        mAdapter = new MyAdapter(setMyDatasetHardCodePasser(myDataset));
        recyclerView.setAdapter(mAdapter);




        Log.i(TAG, FRAGMENT_NAME +" onCreateView");
        return view;
    }


    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private ArrayList<String> mDataset;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class MyViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView cvtvEventTitle;
            public TextView cvtvEventDescription;
            public MyViewHolder(View v) {
                super(v);
                cvtvEventTitle = v.findViewById(R.id.cvtvEventTitle);
                cvtvEventDescription = v.findViewById(R.id.cvtvEventDescription);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public MyAdapter(ArrayList<String> myDataset) {
            mDataset = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.event_card_view, parent, false);


            //TODO...

            MyViewHolder vh = new MyViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.cvtvEventTitle.setText(mDataset.get(position));
            holder.cvtvEventDescription.setText(mDataset.get(position)+" descriptions");

            // TODO #### ASSIGN ACTIVITY HERE
            holder.cvtvEventTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent openEventDetailsActivity = new Intent(view.getContext(), EventDetailsActivity.class);
                    view.getContext().startActivity(openEventDetailsActivity);
                }
            });
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }

}
