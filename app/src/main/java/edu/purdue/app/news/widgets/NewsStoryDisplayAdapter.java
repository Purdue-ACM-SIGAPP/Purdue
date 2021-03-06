package edu.purdue.app.news.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import edu.purdue.app.R;
import edu.purdue.app.news.models.RssItem;

/** Adapter for news story list activity (after selecting a news category.)
 * Created by fieldn on 2/3/2015.
 */
public class NewsStoryDisplayAdapter extends BaseAdapter{
    private Context context;
    private List<RssItem> rssItems;

    public NewsStoryDisplayAdapter(Context context, List<RssItem> rssItems) {
        this.context = context;
        this.rssItems = rssItems;
    }

    @Override
    public int getCount() {
        return rssItems.size();
    }

    @Override
    public Object getItem(int position) {
        return rssItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        // Prepare the requested item's Title and Date
        String storyTitleStr = rssItems.get(position).getTitle();
        String storyDate = rssItems.get(position).getDate();

        // Create and return the view
        // Prevents multiple creations of the same layout for faster scrolling
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.news_story_item, null, false);
        }

        // Grabs story title from view
        TextView storyTitle = (TextView) view.findViewById(R.id.news_story_title);
        // Grabs story time from view
        TextView timeStamp = (TextView) view.findViewById(R.id.news_story_date);

        char bullet = '\u2022';
        storyTitle.setText("    " + bullet + storyTitleStr);// Sets first text view to story title
        timeStamp.setText(storyDate);                      // Sets second text view to the date/time
        return view;
    }
}
