package com.aravindh.andriodbasesetup.ui.fragment


import android.Manifest
import android.content.Context.LOCATION_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.aravindh.andriodbasesetup.background.TrackerService
import com.aravindh.andriodbasesetup.databinding.FragmentLocationTrackingBinding
import com.aravindh.andriodbasesetup.utils.Logger
import com.google.android.gms.location.*


/**
 * A simple [Fragment] subclass.
 */
class LocationTrackingFragment : Fragment() {

    private val PERMISSIONS_REQUEST = 1
    private lateinit var binding: FragmentLocationTrackingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLocationTrackingBinding.inflate(inflater, container, false)

        initLocation()

        return binding.root
    }

    fun initLocation() {

        // Check GPS is enabled
        val locationManager = context!!.getSystemService(LOCATION_SERVICE) as LocationManager

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(context, "Please enable location services", Toast.LENGTH_SHORT).show()
            //activity?.finish()
        }

        // Check location permission is granted - if it is, start
        // the service, otherwise request the permission
        // Check location permission is granted - if it is, start
        // the service, otherwise request the permission
        val permission = ContextCompat.checkSelfPermission(
            context!!,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        if (permission == PackageManager.PERMISSION_GRANTED) {
//            startTrackerService()
            requestLocationUpdates()
        } else {
            requestPermissions(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION), PERMISSIONS_REQUEST
            )
        }
    }

    private fun startTrackerService() {
        activity?.startService(Intent(context, TrackerService::class.java))
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSIONS_REQUEST && grantResults.size == 1
            && grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            // Start the service when the permission is granted
            //startTrackerService();
            requestLocationUpdates()
        } else {
            // activity?.finish();
        }
    }

    private fun requestLocationUpdates() {
        // Functionality coming next step
        val request = LocationRequest()
        request.interval = 10000
        request.fastestInterval = 10000
        request.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        val client: FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(context!!)
        val permission: Int = ContextCompat.checkSelfPermission(
            context!!, Manifest.permission.ACCESS_FINE_LOCATION
        )
        if (permission == PackageManager.PERMISSION_GRANTED) {
            // Request location updates and when an update is
            // received, store the location in Firebase
            client.requestLocationUpdates(request, object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    val location: Location = locationResult.lastLocation

                    val loc = "${location.latitude} , ${location.longitude}"

                    Logger.d("location : $loc")
                    binding.textViewLocation.text = loc
                }
            }, null)
        }
    }


}
