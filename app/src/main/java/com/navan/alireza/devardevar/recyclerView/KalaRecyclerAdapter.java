package com.navan.alireza.devardevar.recyclerView;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.navan.alireza.devardevar.ActivityKalaDetials;
import com.navan.alireza.devardevar.G;
import com.navan.alireza.devardevar.R;

import java.util.ArrayList;

public class KalaRecyclerAdapter extends RecyclerView.Adapter<KalaRecyclerAdapter.ViewHolder> {



  private ArrayList<KalaRecycleStruct> list;
private Activity activity;
private int lastPosition=-1;
  private final static int FADE_DURATION = 1000;
  public KalaRecyclerAdapter(ArrayList<KalaRecycleStruct> list, Activity activity) {
    this.list = list;
    this.activity=activity;
  }

  @Override
  public int getItemViewType(int position) {
    KalaRecycleStruct item = list.get(position);
    return item.type;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    int layoutId = KalaRecycleStruct.TYPE_KALA;
    switch(viewType) {
      case KalaRecycleStruct.TYPE_KALA:
        layoutId = R.layout.kala;
        break;

      case KalaRecycleStruct.TYPE_INDEX:
        layoutId = R.layout.index;
        break;
    }

    View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
    ViewHolder viewHolder = new ViewHolder(view);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    int type = holder.getItemViewType();
    KalaRecycleStruct item = list.get(position);

    switch (type) {
      case KalaRecycleStruct.TYPE_KALA:
        final Kala kalaObject = (Kala) item;

        holder.img.setImageResource(R.drawable.images2);
        holder.  txt_application.setText(kalaObject.applicationId);
        holder. txt_name.setText(kalaObject.kname);
        holder.txt_application.setText(kalaObject.applicationId);
        holder.txt_name.setText(kalaObject.kname);
        holder. cardView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent intent=new Intent(G.mainactivity, ActivityKalaDetials.class);
            intent.putExtra("kalaId",kalaObject.kalaId);
            G.mainactivity.startActivity(intent);

          }
        });
        if(position%3==1)
          holder.img.setImageResource(R.drawable.car1);
        else if(position%3==2)
          holder.img.setImageResource(R.drawable.antic_stone);
        else
          holder.img.setImageResource(R.drawable.kitchen);
        break;

      case KalaRecycleStruct.TYPE_INDEX:
        Log.i("Alireza Log","Type index notDo any work");
        break;
    }
    setAnimation(holder.itemView, position);
//    setFadeAnimation(holder.itemView);
//    setScaleAnimation(holder.itemView);

  }

  @Override
  public int getItemCount() {
    return list.size();
  }


  private void setAnimation(View viewToAnimate, int position)
  {
    // If the bound view wasn't previously displayed on screen, it's animated
    if (position > lastPosition)
    {

      Animation animation = AnimationUtils.loadAnimation(activity, android.R.anim.slide_in_left);
//      animation.setDuration(500);
      viewToAnimate.startAnimation(animation);
      lastPosition = position;
    }
  }

  private void setScaleAnimation(View viewToAnimate) {
    ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
    anim.setDuration(FADE_DURATION);
    viewToAnimate.startAnimation(anim);
  }

  private void setFadeAnimation(View viewToAnimate) {
    AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
    anim.setDuration(FADE_DURATION);
    viewToAnimate.startAnimation(anim);
  }
  class ViewHolder extends RecyclerView.ViewHolder {
    ViewGroup root;
    TextView txt_name;
    TextView txt_application;
    TextView txt_color;
    ImageView img;
    CardView cardView;



    public ViewHolder(View view) {
      super(view);

      root = (ViewGroup) view;
      txt_application= (TextView) view.findViewById(R.id.kala_txt_application);
      txt_color= (TextView) view.findViewById(R.id.kala_txt_color);
      txt_name= (TextView) view.findViewById(R.id.kala_txt_name);
      img= (ImageView) view.findViewById(R.id.kala_img);
      cardView= (CardView) view.findViewById(R.id.kala_cv_root);
    }
  }
}
