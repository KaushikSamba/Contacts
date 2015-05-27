package com.kaushiksamba.contacts;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    String[] values;
    adapter my_adapter;
    ListView listView;
    int initstate = 1;

       @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        values = new String[]{"Wilbur Sargunaraj","Bryan Cranston","Kevin Spacey","Douglas Adams","Lewis Carroll","Phil Dunphy","Hugh Laurie","Christopher Nolan","Jonathan Nolan","Michael Emerson","John Reese","Sarah Shahi","Vennu Mallesh"};
        my_adapter = new adapter(this);
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(my_adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sort_string_array(String[] array)
    {
        int l = array.length;
        for(int i=0;i<l;i++)
        {
            for(int j=0;j<l-i-1;j++)
                if(array[j].compareTo(array[j+1])>0)
                {
                    String t = array[j];
                    array[j]=array[j+1];
                    array[j+1]=t;
                }

        }
    }

    public void rev_array(String[] array)
    {
        int l = array.length;
        for(int i=0;i<l/2;i++)
        {
            String t = array[i];
            array[i] = array[l-i-1];
            array[l-i-1] = t;
        }
    }

    public void sort_alpha(View view)
    {
        Button button = (Button) findViewById(R.id.order_button);
        if(initstate==1)
        {
            sort_string_array(values);
            button.setText(R.string.sortztoa);
            initstate=0;
        }
            else
            {
                rev_array(values);
                String text = button.getText().toString();
                if(text.equals("Sort A-Z")) button.setText(R.string.sortztoa);
                    else button.setText(R.string.sortatoz);
            }
        listView.setAdapter(my_adapter);
    }

    public void searchForName(View view)
    {
        EditText editText = (EditText) findViewById(R.id.search_name);
        String name = editText.getText().toString();
        int l = values.length, flag=0;
        if(l==0) Toast.makeText(this,"Please enter a name", Toast.LENGTH_SHORT).show();
        else
        {
            for (int i = 0; (i < l) && (flag == 0); i++) {
                if (values[i].equalsIgnoreCase(name)) {
                    Toast.makeText(this, "Contact found: " + values[i], Toast.LENGTH_SHORT).show();
                    flag = 1;
                }
            }
            if (flag == 0) Toast.makeText(this, "Missing. :(", Toast.LENGTH_SHORT).show();
        }
    }
    public class adapter extends ArrayAdapter<String>
    {
        private final Context context;
        public adapter(Context context)
        {
            super(context,R.layout.list_layout,values);
            this.context=context;
        }

        @Override
        public View getView(int position,View convertView, ViewGroup parent)
        {
            View row;
            row = convertView;
            one_person holder;
            //Code to optimize row inflation in  a ListView
            if(row==null)
            {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.list_layout,parent,false);
                holder = new one_person(row);
                row.setTag(holder);
            }
                else
                {
                    holder = (one_person) row.getTag();
                }
            String name = values[position];
            holder.textView.setText(name);
            holder.textView.setSingleLine(true);
            Resources resources = getResources();
            name = name.toLowerCase();
            name = name.replace(' ','_');
            int id = resources.getIdentifier(name,"drawable",getPackageName());
            holder.imageView.setImageResource(id);
            return row;
        }
    }
}
