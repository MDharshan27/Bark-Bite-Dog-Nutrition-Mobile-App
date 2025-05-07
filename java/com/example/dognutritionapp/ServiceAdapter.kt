package com.example.dognutritionapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ServiceAdapter(private val services: List<ServiceItem>) :
    RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>() {

    // Step 1: Define the ViewHolder
    class ServiceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val serviceImage: ImageView = view.findViewById(R.id.service_image)
        val serviceName: TextView = view.findViewById(R.id.service_name)
        val serviceProvider: TextView = view.findViewById(R.id.service_provider)
        val serviceRating: TextView = view.findViewById(R.id.service_rating)
        val servicePrice: TextView = view.findViewById(R.id.service_price)
    }

    // Step 2: Inflate the item layout and return a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_service, parent, false)
        return ServiceViewHolder(view)
    }

    // Step 3: Bind data to the views in each ViewHolder
    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val service = services[position]
        holder.serviceImage.setImageResource(service.imageResId) // Assuming you have images as resources
        holder.serviceName.text = service.name
        holder.serviceProvider.text = "By ${service.provider}"
        holder.serviceRating.text = "★ ${service.rating}"
        holder.servicePrice.text = "₹${service.price}"
    }

    // Step 4: Return the total number of items
    override fun getItemCount() = services.size
}
