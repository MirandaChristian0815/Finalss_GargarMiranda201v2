package com.example.finalss_gargarmiranda

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReportAdapter(private val reports: List<Report>) : RecyclerView.Adapter<ReportAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_report, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val report = reports[position]
        holder.bind(report)
    }

    override fun getItemCount(): Int = reports.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val reportedByTextView: TextView = itemView.findViewById(R.id.reportedByTextView)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)

        fun bind(report: Report) {
            reportedByTextView.text = "${report.reportedBy} submitted a report"
            descriptionTextView.text = report.description
        }
    }
}