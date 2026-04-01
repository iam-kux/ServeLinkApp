package com.splinesoft.servelinkapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.splinesoft.servelinkapp.R;
import com.splinesoft.servelinkapp.models.Service;
import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {

    private List<Service> serviceList;
    private OnServiceClickListener listener;

    public interface OnServiceClickListener {
        void onServiceClick(Service service);
    }

    public ServiceAdapter(List<Service> serviceList, OnServiceClickListener listener) {
        this.serviceList = serviceList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service_card, parent, false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        Service service = serviceList.get(position);
        
        holder.tvServiceName.setText(service.getTitle());
        // In a real app, provider name might need to be fetched separately, 
        // but for now we just show providerId or title.
        holder.tvProviderName.setText("Provider ID: " + service.getProviderId());
        holder.tvRating.setText(String.valueOf(service.getRating()));
        holder.tvPrice.setText("$" + service.getPrice());

        if (service.getImage() != null && !service.getImage().isEmpty()) {
            Glide.with(holder.itemView.getContext())
                    .load(service.getImage())
                    .centerCrop()
                    .into(holder.ivServiceImage);
        } else {
            holder.ivServiceImage.setImageResource(R.mipmap.ic_launcher);
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onServiceClick(service);
        });
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    static class ServiceViewHolder extends RecyclerView.ViewHolder {
        ImageView ivServiceImage;
        TextView tvServiceName, tvProviderName, tvRating, tvPrice;

        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            ivServiceImage = itemView.findViewById(R.id.ivServiceImage);
            tvServiceName = itemView.findViewById(R.id.tvServiceName);
            tvProviderName = itemView.findViewById(R.id.tvProviderName);
            tvRating = itemView.findViewById(R.id.tvRating);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
}
