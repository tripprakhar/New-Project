package com.example.drapplication.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drapplication.R;
import com.example.drapplication.Utill.DocInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context ctx;
    private List<DocInfo> docInfoList;

    public RecyclerViewAdapter(Context ctx, List<DocInfo> docInfoList) {
        this.ctx = ctx;
        this.docInfoList = docInfoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.doctors_card_view,parent,false);
        return new ViewHolder(view,ctx);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    DocInfo docInfo=docInfoList.get(position);
    String imageUri=null;
    holder.name.setText(docInfo.getFname()+docInfo.getMname()+docInfo.getLname());
    holder.degree.setText(docInfo.getDegree());
    holder.speciality.setText(docInfo.getSpeciality());
    int l=docInfo.getExperience();
    holder.exp.setText(String.valueOf(l));
    holder.pres.setText(docInfo.getPresent());
    holder.phone.setText(docInfo.getPhoneNo());
        imageUri=docInfo.getImage();
        Picasso.with(ctx).load(imageUri).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return docInfoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
      public TextView name;
      public TextView degree;
      public TextView speciality;
      public TextView exp;
      public TextView pres;
      public TextView phone;
      public ImageView image;
        public ViewHolder(@NonNull View view,Context context) {
            super(view);
            ctx=context;
            name=(TextView)view.findViewById(R.id.full_name);
            degree=(TextView)view.findViewById(R.id.degree);
            speciality=(TextView)view.findViewById(R.id.specialization);
            exp=(TextView)view.findViewById(R.id.experience);
            pres=(TextView)view.findViewById(R.id.present);
            phone=(TextView)view.findViewById(R.id.mobile);
            image=(ImageView)view.findViewById(R.id.doctor_pic);

        }
    }
}
