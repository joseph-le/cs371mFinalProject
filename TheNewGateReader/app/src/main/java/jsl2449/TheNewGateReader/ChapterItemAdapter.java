package jsl2449.TheNewGateReader;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joe on 11/9/2016.
 */

public class ChapterItemAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<MangaChapter> list;
    private Context context;

    public ChapterItemAdapter(Context context, List<MangaChapter> list) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        if (list != null) {
            return list.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        if (list != null) {
            return list.get(position).hashCode();
        } else {
            return 0;
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MangaChapter data = (MangaChapter) getItem(i);
        if (data == null) {
            throw new IllegalStateException("this should be called when list is not null blaralshdgl");
        }

        if (view == null) {
            view = inflater.inflate(R.layout.chapter_item, viewGroup, false);
        }
        bindView(data, view, viewGroup);
        return view;
    }

    public void bindView(final MangaChapter data, View view, ViewGroup parent) {
        TextView chapterTextBox = (TextView) view.findViewById(R.id.chapter_number);
        chapterTextBox.setText(data.title);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewPageIntent = new Intent(context, PageViewerActivity.class);
                Bundle myExtras = new Bundle();
                Bookmark bm = new Bookmark();
                bm.pageURL = data.chapterURL;
                myExtras.putSerializable("resume", bm);
                viewPageIntent.putExtras(myExtras);
                final int result = 1;
                System.out.println("chapter to page intent");
                ((Activity) context).startActivityForResult(viewPageIntent, result);
            }
        });
    }
}
