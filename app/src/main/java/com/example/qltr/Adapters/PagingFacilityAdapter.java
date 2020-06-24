package com.example.qltr.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.Models.Facility.Facility;
import com.example.qltr.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PagingFacilityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "RoomButtonViewAdapter";
    private static final int TYPE_HEADER=0;
    private static final int TYPE_ITEM=1;
    private static final int TYPE_FOOTER=2;

    private Context mContext;
    private ArrayList<Facility> facilities;

    public PagingFacilityAdapter(Context context, ArrayList<Facility> facilities){
        this.mContext=context;
        this.facilities=facilities;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==TYPE_HEADER){
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_footer, parent, false);

            return new FooterViewHolder(view);
        }
        else if(viewType==TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_footer, parent, false);

            return new FooterViewHolder(view);
        }

        View view = LayoutInflater.from(mContext).inflate(R.layout.facility_viewholder_layout, parent, false);

        return new FacilityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof FacilityViewHolder){
            ((FacilityViewHolder) holder).facilityPriceDisplayer.setText(Integer.toString(facilities.get(position).getUnitPrice()));
            ((FacilityViewHolder) holder).facilityNameDisplayer.setText(facilities.get(position).getName());
            //set onLongClickListener
        }
    }

    @Override
    public int getItemCount() {
        return (facilities==null || facilities.isEmpty())?0:facilities.size();
    }

    @Override
    public int getItemViewType(int position){
        if(isFooter(position)){
            return TYPE_FOOTER;
        }

        return TYPE_ITEM;
    }

    private boolean isHeader(int pos){
        return pos==0;
    }

    private boolean isFooter(int pos){
        return facilities.get(pos)==null;
    }

    class FacilityViewHolder extends RecyclerView.ViewHolder {
        private TextView facilityNameDisplayer;
        private TextView facilityPriceDisplayer;
        private View view;

        public FacilityViewHolder(@NonNull View itemView) {
            super(itemView);

            this.view=itemView;
            facilityNameDisplayer=itemView.findViewById(R.id.facility_name_displayer);
            facilityPriceDisplayer=itemView.findViewById(R.id.facility_price_displayer);
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        private View view;

        public FooterViewHolder(@NonNull View itemView) {
            super(itemView);
//            this.view=itemView;
        }
    }
}
