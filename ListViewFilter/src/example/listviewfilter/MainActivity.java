package example.listviewfilter;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

public class MainActivity extends Activity {

	String[] SrNo;
	String[] days;
	ListView list;
	ListViewAdapter adapter;
	AutoCompleteTextView editsearch;
	ArrayList<WeekDays> arraylist = new ArrayList<WeekDays>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);

		editsearch = (AutoCompleteTextView) findViewById(R.id.search);
		SrNo = new String[] { "1", "2", "3", "4", "5", "6", "7" };

		days = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday",
				"Thursday", "Friday", "Saturday" };

		for (int i = 0; i < SrNo.length; i++) {
			WeekDays wD = new WeekDays(SrNo[i], days[i]);
			// Binds all strings into an array
			arraylist.add(wD);
		}

		list = (ListView) findViewById(R.id.listview);
		adapter = new ListViewAdapter(this, arraylist);
        list.setAdapter(adapter);
		
        
        //preparing Suggestion List for AutoTextView
		ArrayList<String> searchSuggestionList = new ArrayList<String>();
		for (WeekDays obj : arraylist) {

			searchSuggestionList.add(obj.getSrno());
			searchSuggestionList.add(obj.getDay());

		}
         //setting adapter to autoTextView
		ArrayAdapter<String> AutoTextAdapter;
		String[] str = new String[searchSuggestionList.size()];
		AutoTextAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				searchSuggestionList.toArray(str));

		editsearch.setAdapter(AutoTextAdapter);

		editsearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				String text = editsearch.getText().toString();
				adapter.filter(text);

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

	}

}
