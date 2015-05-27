package com.kaushiksamba.contacts;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by kaushiksamba on 27-05-2015.
 */
public class one_person
{
    //Class that contains contents of each row of the ListView.
    ImageView imageView;
    TextView textView;

    public one_person(View view)
    {
        imageView = (ImageView)view.findViewById(R.id.image);
        textView = (TextView) view.findViewById(R.id.name);
    }
}
