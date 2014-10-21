package example.listviewfilter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {

	private ArrayList<WeekDays> WeekDayList = new ArrayList<WeekDays>();
	private ArrayList<WeekDays> arraylist;
	Context mContext;
	LayoutInflater inflater;

	public ListViewAdapter(Context context, ArrayList<WeekDays> WeekDayList) {
		mContext = context;
		this.WeekDayList = WeekDayList;
		inflater = LayoutInflater.from(mContext);
		this.arraylist = new ArrayList<WeekDays>();
		this.arraylist.addAll(WeekDayList);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return WeekDayList.size();
	}

	@Override
	public WeekDays getItem(int position) {
		// TODO Auto-generated method stub
		return WeekDayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		// TODO Auto-generated method stub

		final ViewHolder holder;
		if (view == null) {
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.listview_item, null);
			// Locate the TextViews in listview_item.xml
			holder.Srno = (TextView) view.findViewById(R.id.txtSrno);
			holder.Days = (TextView) view.findViewById(R.id.txtDay);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		// Set the results into TextViews
		holder.Srno.setText(WeekDayList.get(position).getSrno());
		holder.Days.setText(WeekDayList.get(position).getDay());

		return view;
	}

	public class ViewHolder {
		TextView Srno;
		TextView Days;

	}

	// Filter Class
	public void filter(String charText) {

		WeekDayList.clear();
		if (charText.length() == 0) {
			WeekDayList.addAll(arraylist);
		} else {
			for (WeekDays Wd : arraylist) {
				if (Wd.getSrno().equals(charText)
						|| Wd.getDay().equals(charText)) {
					WeekDayList.add(Wd);
				}
			}
		}
		notifyDataSetChanged();

	}

}
