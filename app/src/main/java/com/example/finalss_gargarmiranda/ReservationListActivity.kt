package com.example.finalss_gargarmiranda

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class ReservationListActivity : AppCompatActivity(), ApprovedReservationsAdapter.OnReservationClickListener {

class ReservationListActivity : AppCompatActivity() {

    private lateinit var reservationRecyclerView: RecyclerView
    private lateinit var reservationAdapter: ReservationAdapter
    private lateinit var emptyStateTextView: TextView
    private val reservationList = mutableListOf<Reservation>()

    private lateinit var firestore: FirebaseFirestore
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ApprovedReservationsAdapter
    private val reservations = mutableListOf<Reservation>()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_list)

        firestore = FirebaseFirestore.getInstance()
        firebaseAuth = FirebaseAuth.getInstance()

        emptyStateTextView = findViewById(R.id.emptyStateTextView)
        reservationRecyclerView = findViewById(R.id.reservationRecyclerView)
        reservationRecyclerView.layoutManager = LinearLayoutManager(this)

        reservationAdapter = ReservationAdapter(reservationList) { reservation ->
            val intent = Intent(this, ReservationDetailsActivity::class.java)
            intent.putExtra("facility", reservation.facility)
            intent.putExtra("reservedBy", reservation.reservedBy)
            intent.putExtra("position", reservation.position)
            intent.putExtra("title", reservation.title)
            intent.putExtra("date", reservation.date)
            intent.putExtra("time", reservation.time)
            intent.putExtra("attendees", reservation.attendees)
            intent.putExtra("speaker", reservation.speaker)
            intent.putExtra("status", reservation.status)
            startActivity(intent)
        }
        reservationRecyclerView.adapter = reservationAdapter

        findViewById<ImageButton>(R.id.btn_back).setOnClickListener {
            finish()
        }

        fetchReservations()
    }

    private fun fetchReservations() {
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            firestore.collection("reservations")
                .whereEqualTo("submittedBy.email", currentUser.email)
                .addSnapshotListener { snapshots, e ->
                    if (e != null) {
                        Log.w("ReservationList", "Listen failed.", e)
                        return@addSnapshotListener
                    }

                    reservationList.clear()
                    for (doc in snapshots!!) {
                        val submittedBy = doc.get("submittedBy") as? Map<*, *>
                        val activityDetails = doc.get("activityDetails") as? Map<*, *>
                        val reservationSlot = doc.get("reservationSlot") as? Map<*, *>

                        val reservation = Reservation(
                            id = doc.id,
                            facility = doc.getString("facilityName") ?: "",
                            date = reservationSlot?.get("date")?.toString() ?: "",
                            time = reservationSlot?.get("time")?.toString() ?: "",
                            reservedBy = submittedBy?.get("name")?.toString() ?: "",
                            position = submittedBy?.get("position")?.toString() ?: "",
                            title = activityDetails?.get("title")?.toString() ?: "",
                            attendees = activityDetails?.get("attendees")?.toString() ?: "",
                            speaker = activityDetails?.get("speaker")?.toString() ?: "",
                            status = doc.getString("status") ?: ""
                        )
                        reservationList.add(reservation)
                    }

                    if (reservationList.isEmpty()) {
                        emptyStateTextView.visibility = View.VISIBLE
                        reservationRecyclerView.visibility = View.GONE
                    } else {
                        emptyStateTextView.visibility = View.GONE
                        reservationRecyclerView.visibility = View.VISIBLE
                    }

                    reservationAdapter.notifyDataSetChanged()
                }

        val btnBack = findViewById<ImageButton>(R.id.btn_back)
        btnBack.setOnClickListener {
            finish()
        }

        recyclerView = findViewById(R.id.approvedReservationsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ApprovedReservationsAdapter(reservations, this)
        recyclerView.adapter = adapter

        fetchApprovedReservations()
    }

    private fun fetchApprovedReservations() {
        db.collection("reservations")
            .whereEqualTo("status", "approved")
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Log.w("ReservationList", "Listen failed.", e)
                    return@addSnapshotListener
                }

                reservations.clear()
                for (doc in snapshots!!) {
                    val submittedBy = doc.get("submittedBy") as? Map<*, *>
                    val activityDetails = doc.get("activityDetails") as? Map<*, *>
                    val reservationSlot = doc.get("reservationSlot") as? Map<*, *>

                    val reservation = Reservation(
                        id = doc.id,
                        facility = doc.getString("facilityName") ?: "",
                        date = reservationSlot?.get("date")?.toString() ?: "",
                        time = reservationSlot?.get("time")?.toString() ?: "",
                        reservedBy = submittedBy?.get("name")?.toString() ?: "",
                        position = submittedBy?.get("position")?.toString() ?: "",
                        title = activityDetails?.get("title")?.toString() ?: "",
                        attendees = activityDetails?.get("attendees")?.toString() ?: "",
                        speaker = activityDetails?.get("speaker")?.toString() ?: "",
                        status = doc.getString("status") ?: ""
                    )

                    Log.d("ReservationList", "Parsed reservation: $reservation")
                    reservations.add(reservation)
                }

                adapter.notifyDataSetChanged()
            }
    }

    override fun onReservationClick(reservation: Reservation) {
        val intent = Intent(this, ReservationDetailsActivity::class.java)
        intent.putExtra("facility", reservation.facility)
        intent.putExtra("reservedBy", reservation.reservedBy)
        intent.putExtra("position", reservation.position)
        intent.putExtra("title", reservation.title)
        intent.putExtra("date", reservation.date)
        intent.putExtra("time", reservation.time)
        intent.putExtra("attendees", reservation.attendees)
        intent.putExtra("speaker", reservation.speaker)
        intent.putExtra("status", reservation.status)
        startActivity(intent)
    }
}