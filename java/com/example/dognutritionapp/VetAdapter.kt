package com.example.dognutritionapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VetAdapter(private val vetList: List<Veterinary>) : RecyclerView.Adapter<VetAdapter.VetViewHolder>() {

    inner class VetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val doctorImageView: ImageView = itemView.findViewById(R.id.image_doctor)
        val nameTextView: TextView = itemView.findViewById(R.id.text_vet_name)
        val clinicTextView: TextView = itemView.findViewById(R.id.text_vet_clinic)
        val distanceTextView: TextView = itemView.findViewById(R.id.text_vet_distance)
        val feeTextView: TextView = itemView.findViewById(R.id.text_vet_fee)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_veterinary, parent, false)
        return VetViewHolder(view)
    }

    override fun onBindViewHolder(holder: VetViewHolder, position: Int) {
        val vet = vetList[position]
        holder.doctorImageView.setImageResource(vet.doctorImage)
        holder.nameTextView.text = vet.name
        holder.clinicTextView.text = vet.clinic
        holder.distanceTextView.text = vet.distance
        holder.feeTextView.text = vet.fee
    }

    override fun getItemCount() = vetList.size
}
