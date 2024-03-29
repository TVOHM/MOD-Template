package com.mod.template;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;

class StableArrayAdapter extends ArrayAdapter<String> {

    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

    public StableArrayAdapter(Context context, int textViewResourceId,
        String[] objects) {
      super(context, textViewResourceId, objects);
      for (int i = 0; i < objects.length; ++i) {
        mIdMap.put(objects[i], i);
      }
    }

    @Override
    public long getItemId(int position) {
      String item = getItem(position);
      return mIdMap.get(item);
    }

    @Override
    public boolean hasStableIds() {
      return true;
    }

  }
