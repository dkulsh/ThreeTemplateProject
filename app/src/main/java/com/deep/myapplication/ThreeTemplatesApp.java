package com.deep.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ThreeTemplatesApp extends AppCompatActivity {

    public static final String TAG_LABEL = "label";
    public static final String TAG_IMAGE = "image";
    public static final String TAG_TEMPLATE = "template";
    public static final String TAG_ITEMS = "items";
    public static final String TAG_WEBURL = "web-url";

    public static final String LOGTAG = "THREETEMP";

//    JSONObject[] jsonObjects = new JSONObject[]{};
    List<JSONObject> jsonObjects = new ArrayList<JSONObject>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_templates_app);

        /*JSONObject[] jsonArray = */readJsonFiles();

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(new TemplateAdapter<String>(this, android.R.layout.simple_list_item_1, jsonObjects));
    }

    private /*JSONObject[]*/ void readJsonFiles(){

        String json = "";
        JSONArray jsonArray = null;

        try {
            InputStream inputStream = getAssets().open("f_one.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");

            jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {

                jsonObjects.add(jsonArray.getJSONObject(i));
//                jsonObjects[i] = jsonArray.getJSONObject(i);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

//        try {
//            JSONObject jsonObject = new JSONObject(json);
//            jsonArray = new JSONArray(json);

/*            for (int i = 0; i < jsonArray.length(); i++) {

                JSONArray itemsArray = jsonObject.getJSONArray(TAG_ITEMS);
                for (int j = 0; j < jsonArray.length(); j++) {

                    JSONObject itemsArrayJSONObject= itemsArray.getJSONObject(j);

                    String item_label = itemsArrayJSONObject.getString(TAG_LABEL);
                    String item_image = itemsArrayJSONObject.getString(TAG_IMAGE);
                    String item_url = itemsArrayJSONObject.getString(TAG_TEMPLATE);
                }
            }*/
/*
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

//        return jsonObjects;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_three_templates_app, menu);
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

    public class TemplateAdapter<String> extends ArrayAdapter{

        Context context;
        List<JSONObject> objects = null;

        public TemplateAdapter(Context context, int resource, /*Object[] objects*/ List<JSONObject> objects) {
            super(context, resource, objects);
            this.context = context;
            this.objects = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
//            return super.getView(position, convertView, parent);

            final LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View view = null;

//            java.lang.String label;
//            java.lang.String image = null;
            java.lang.String template = null;
            JSONArray itemsArray = null;
            List<JSONObject> itemObjects = new ArrayList<JSONObject>();

            try {
//                label = objects.get(position).getString(TAG_LABEL);
//                image = objects.get(position).getString(TAG_IMAGE);
                template = objects.get(position).getString(TAG_TEMPLATE);
                itemsArray = objects.get(position).getJSONArray(TAG_ITEMS);

                for (int i = 0; i < itemsArray.length(); i++) {
                    itemObjects.add(itemsArray.getJSONObject(i));
                }

                if("product-template-1".equals(template)){

                    Log.i(LOGTAG, "Position = " + position);
                    View layoutView = layoutInflater.inflate(R.layout.template_one_layout, parent, false);
//                    View imageView = layoutInflater.inflate(R.layout.template_one, parent, false);

                    LinearLayout linearLayout = (LinearLayout) layoutView.findViewById(R.id.vertical_layout);

                    for (int i = 0; i < itemsArray.length(); i++) {

//                        ImageView image = (ImageView) imageView.findViewById(R.id.image_1);
                        ImageView image = new ImageView(context);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                        image.setLayoutParams(params);
                        image.setScaleType(ImageView.ScaleType.FIT_XY);

                        View divider = new View(context);
                        LinearLayout.LayoutParams paramsDivider = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT, 20);
                        divider.setLayoutParams(paramsDivider);

                        try {
                            java.lang.String imageString = itemsArray.getJSONObject(i).getString(TAG_IMAGE);
                            Log.i(LOGTAG, "Template 1 : URL = " + imageString);
                            Picasso.with(getApplicationContext()).load(imageString).into(image);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        linearLayout.addView(image);
                        linearLayout.addView(divider);
                    }

                    return layoutView;

                }else if("product-template-2".equals(template)){

                    Log.i(LOGTAG, "Position = " + position);

                    /*View view = layoutInflater.inflate(R.layout.template_two, parent, false);
                    RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
    //                recyclerView.setHasFixedSize(true);

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                    recyclerView.setLayoutManager(linearLayoutManager);

                    RecyclerAdapter recyclerAdapter = new RecyclerAdapter(itemObjects);
                    recyclerView.setAdapter(recyclerAdapter);
                    return view;*/

                    View layoutView = layoutInflater.inflate(R.layout.template_two_layout, parent, false);
                    LinearLayout linearLayout = (LinearLayout) layoutView.findViewById(R.id.horizontal_layout);

                    for (int i = 0; i < itemsArray.length(); i++) {

                        View imageView = layoutInflater.inflate(R.layout.template_two_detail, null/*, false*/);
                        LinearLayout templateTwoDetailLayout = (LinearLayout) imageView.findViewById(R.id.template_two_layout);
                        ImageView image = (ImageView) imageView.findViewById(R.id.image_2);

                        View divider = new View(context);
                        LinearLayout.LayoutParams paramsDivider = new LinearLayout.LayoutParams(
                                20, LinearLayout.LayoutParams.MATCH_PARENT);
                        divider.setLayoutParams(paramsDivider);

                        try {
                            java.lang.String imageString = itemsArray.getJSONObject(i).getString(TAG_IMAGE);
                            Log.i(LOGTAG, "Template 2 : URL = " + imageString);
                            Picasso.with(getApplicationContext()).load(imageString).into(image);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        linearLayout.addView(templateTwoDetailLayout);
                        linearLayout.addView(divider);
                    }

                    return layoutView;

                }else if("product-template-3".equals(template)){

                    Log.i(LOGTAG, "Position = " + position);

                    View layoutView = layoutInflater.inflate(R.layout.template_three_layout, parent, false);
                    ViewPager viewPager = (ViewPager) layoutView.findViewById(R.id.pager);

                    Log.i(LOGTAG, "Size of itemsArray template 3 " + itemsArray.length());
                    ScreenSlidePagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), itemsArray);
                    viewPager.setAdapter(pagerAdapter);
                    viewPager.setOffscreenPageLimit(5);

                    CirclePageIndicator circlePageIndicator = (CirclePageIndicator) layoutView.findViewById(R.id.circlePageInd);
                    circlePageIndicator.setViewPager(viewPager);

                    return layoutView;

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new View(context);
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        JSONArray images;

        public ScreenSlidePagerAdapter(FragmentManager fm, JSONArray images) {
            super(fm);
             this.images = images;
        }

        @Override
        public Fragment getItem(int position) {

            Log.i(LOGTAG, "ScreenSlidePagerAdapter position : " + position);

            ScreenSlidePagerFragment screenSlidePagerFragment = new ScreenSlidePagerFragment();
            Bundle bundle = new Bundle();
            try {
                Log.i(LOGTAG, "Template 3 : URL =  " + images.getJSONObject(position).getString(TAG_IMAGE));
                bundle.putString("IMAGE", images.getJSONObject(position).getString(TAG_IMAGE));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            screenSlidePagerFragment.setArguments(bundle);

            return screenSlidePagerFragment;
        }

        @Override
        public int getCount() { return images.length(); }
    }

    public static class ScreenSlidePagerFragment extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//            return super.onCreateView(inflater, container, savedInstanceState);

            View view = inflater.inflate(R.layout.template_three_detail, container, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.pagerDetailImage);

            Bundle bundle = getArguments();
            String imageString = bundle.getString("IMAGE");

            Picasso.with(getActivity()).load(imageString).into(imageView);

            return imageView;

        }
    }
}
