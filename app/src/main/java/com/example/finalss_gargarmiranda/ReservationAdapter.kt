package com.example.finalss_gargarmiranda

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReservationAdapter(
    private val reservations: List<Reservation>,
    private val onItemClick: (Reservation) -> Unit
) : RecyclerView.Adapter<ReservationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_reservation, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reservation = reservations[position]
        holder.bind(reservation)
    }

    override fun getItemCount(): Int = reservations.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val facilityNameTextView: TextView = itemView.findViewById(R.id.facilityNameTextView)
        private val reservedByTextView: TextView = itemView.findViewById(R.id.reservedByTextView)
        private val positionTextView: TextView = itemView.findViewById(R.id.positionTextView)
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        private val timeTextView: TextView = itemView.findViewById(R.id.timeTextView)
        private val attendeesTextView: TextView = itemView.findViewById(R.id.attendeesTextView)
        private val speakerTextView: TextView = itemView.findViewById(R.id.speakerTextView)
        private val statusTextView: TextView = itemView.findViewById(R.id.statusTextView)

        fun bind(reservation: Reservation) {
            facilityNameTextView.text = reservation.facility
            reservedByTextView.text = reservation.reservedBy
            positionTextView.text = reservation.position
            titleTextView.text = reservation.title
            dateTextView.text = reservation.date
            timeTextView.text = reservation.time
            attendeesTextView.text = reservation.attendees
            speakerTextView.text = reservation.speaker
            statusTextView.text = reservation.status

            itemView.setOnClickListener {
                onItemClick(reservation)
            }
        }
    }
}