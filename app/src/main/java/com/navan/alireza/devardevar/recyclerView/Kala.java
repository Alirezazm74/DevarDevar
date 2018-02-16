package com.navan.alireza.devardevar.recyclerView;

import android.app.Activity;
import android.database.Cursor;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Alireza on 09/02/2018.
 */

public class Kala extends KalaRecycleStruct {
    public String kalaId;
    public String kname;
    public String typeId;
    public String colorId;
    public String subTypeId;
    public String technologyId;
    public String applicationId;
    public String price;
    public String profileInfo;
    public String description;
    public String image;
    public String dimensionsId;
    private Activity activity;
    private static  ArrayList<View> views=new ArrayList<>();

    public Kala(String kalaId, String kname, String typeId, String colorId,
                String subTypeId, String technologyId, String applicationId,
                String price, String profileInfo, String description
                ,String image, String dimensionsId) {

            this.type=KalaRecycleStruct.TYPE_KALA;
            this.kalaId = kalaId;
            this.kname = kname;
            this.typeId = typeId;
            this.colorId = colorId;
            this.subTypeId = subTypeId;
            this.technologyId = technologyId;
            this.applicationId = applicationId;
            this.price = price;
            this.profileInfo = profileInfo;
            this.description = description;
            this.image = image;
            this.dimensionsId = dimensionsId;
    }
    public  Kala(int type){
        this.type=type;

    }




    //
private static int count=0;
    public void init(Cursor cursor ){
count++;
        this.activity=activity;
        int columnCount = cursor.getColumnCount();

        for (int i=0; i<columnCount; i++) {
            String name = cursor.getColumnName(i);
            String value = "";

            switch (name) {
                case "kalaId":
                    kalaId = "" + cursor.getInt(i);
                    break;
                case "name":
                    kname = "" + cursor.getString(i);
                    break;
                case "typeId":
                    typeId = "" + cursor.getInt(i);
                    break;
                case "colorId":
                    colorId =""+ cursor.getInt(i);
                    break;
                case "subTypeId":
                    subTypeId =""+ cursor.getInt(i);
                    break;
                case "technologyId":
                    technologyId =""+ cursor.getInt(i);
                    break;
                case "applicationId":
                    applicationId = cursor.getString(i);
                    break;
                case "price":
                    price = ""+cursor.getInt(i);
                    break;
                case "profileInfo":
                    profileInfo = cursor.getString(i);
                    break;
                case "description":
                    description = cursor.getString(i);
                    break;
                case "image":
                    image = cursor.getString(i);
                    break;
                case "dimensionsId":
                    dimensionsId = ""+cursor.getInt(i);
                    break;
            }


        }

    }
/*
    public  void show(LinearLayout root, LayoutInflater inflater){

        View view= inflater.inflate(R.layout.kala,null);

        TextView txt_application= (TextView) view.findViewById(R.id.kala_txt_application);
        TextView txt_color= (TextView) view.findViewById(R.id.kala_txt_color);
        TextView txt_name= (TextView) view.findViewById(R.id.kala_txt_name);
        ImageView img= (ImageView) view.findViewById(R.id.kala_img);
        CardView cardView= (CardView) view.findViewById(R.id.kala_cv_root);
//        cardView.setBackgroundColor(Color.parseColor("#000000"));
        if(count%3==0){
            img.setImageResource(R.drawable.images);
        }else if (count%3==1){

            img.setImageResource(R.drawable.images2);
        }else {

            img.setImageResource(R.drawable.images1);
        }

        txt_application.setText(applicationId);
        txt_name.setText(kname);
        String query="SELECT colorName FROM colors WHERE colorId = ";
        switch (colorId){
            case "1":
               query= query+"1";
                break;
            case "2":
                query= query+"2";
                break;
            case "3":
                query= query+"3";
                break;
            default:
                query=query+"0";
                break;

        }
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity, ActivityKalaDetials.class);
                intent.putExtra("kalaId",kalaId);
                activity.startActivity(intent);

            }
        });
//Cursor cursor=G.database.rawQuery(query,null);

//        Log.i("AlirezaLogcross",cursor.getString(1)+"");
//        Log.i("AlirezaLogcross",cursor.getCount()+"");
//        MainActivity.dbHelper.dumpCursor(cursor);
        views.add(view);
        displayAll(root);
    }
    public static void displayAll(final LinearLayout root){

       root.removeAllViews();

                for (View view:views){
                    root.addView(view);

                }

    }
    */
}
