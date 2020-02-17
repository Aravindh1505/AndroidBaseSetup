package com.aravindh.andriodbasesetup.ui.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.IntentSender.SendIntentException
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.aravindh.andriodbasesetup.BuildConfig
import com.aravindh.andriodbasesetup.R
import com.aravindh.andriodbasesetup.databinding.ActivityLocationBinding
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import java.text.DateFormat
import java.util.*

/**
 * Reference: https://github.com/googlesamples/android-play-location/tree/master/LocationUpdates
 */
class LocationActivity : AppCompatActivity() {
    private lateinit var activityLocationBinding: ActivityLocationBinding
    // location last updated time
    private var mLastUpdateTime: String? = null
    // bunch of location related apis
    private var mFusedLocationClient: FusedLocationProviderClient? = null
    private var mSettingsClient: SettingsClient? = null
    private var mLocationRequest: LocationRequest? = null
    private var mLocationSettingsRequest: LocationSettingsRequest? = null
    private var mLocationCallback: LocationCallback? = null
    private var mCurrentLocation: Location? = null
    // boolean flag to toggle the ui
    private var mRequestingLocationUpdates: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLocationBinding = DataBindingUtil.setContentView(this, R.layout.activity_location)


        activityLocationBinding.btnStartLocationUpdates.setOnClickListener {
            startLocationButtonClick()
        }

        activityLocationBinding.btnStopLocationUpdates.setOnClickListener {
            stopLocationButtonClick()
        }

        activityLocationBinding.btnGetLastLocation.setOnClickListener {
            showLastKnownLocation()
        }


        // initialize the necessary libraries
        init()
        // restore the values from saved instance state
        restoreValuesFromBundle(savedInstanceState)
    }

    private fun init() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mSettingsClient = LocationServices.getSettingsClient(this)
        mLocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                // location is received
                mCurrentLocation = locationResult.lastLocation
                mLastUpdateTime = DateFormat.getTimeInstance().format(Date())
                updateLocationUI()
            }
        }
        mRequestingLocationUpdates = false
        mLocationRequest = LocationRequest()
        mLocationRequest!!.interval = UPDATE_INTERVAL_IN_MILLISECONDS
        mLocationRequest!!.fastestInterval = FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS
        mLocationRequest!!.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(mLocationRequest!!)
        mLocationSettingsRequest = builder.build()
    }

    /**
     * Restoring values from saved instance state
     */
    private fun restoreValuesFromBundle(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("is_requesting_updates")) {
                mRequestingLocationUpdates = savedInstanceState.getBoolean("is_requesting_updates")
            }
            if (savedInstanceState.containsKey("last_known_location")) {
                mCurrentLocation = savedInstanceState.getParcelable("last_known_location")
            }
            if (savedInstanceState.containsKey("last_updated_on")) {
                mLastUpdateTime = savedInstanceState.getString("last_updated_on")
            }
        }
        updateLocationUI()
    }

    /**
     * Update the UI displaying the location data
     * and toggling the buttons
     */
    private fun updateLocationUI() {
        if (mCurrentLocation != null) {
            activityLocationBinding.locationResult.text =
                "Lat: " + mCurrentLocation!!.latitude + ", " + "Lng: " + mCurrentLocation!!.longitude
            // giving a blink animation on TextView
            activityLocationBinding.locationResult.animate().alpha(1F).duration = 300
            // location last updated time
            activityLocationBinding.updatedOn.text = "Last updated on: $mLastUpdateTime"
        }
        toggleButtons()
    }

    public override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("is_requesting_updates", mRequestingLocationUpdates!!)
        outState.putParcelable("last_known_location", mCurrentLocation)
        outState.putString("last_updated_on", mLastUpdateTime)
    }

    private fun toggleButtons() {
        if (mRequestingLocationUpdates!!) {
            activityLocationBinding.btnStartLocationUpdates.isEnabled = false
            activityLocationBinding.btnStopLocationUpdates.isEnabled = true
        } else {
            activityLocationBinding.btnStartLocationUpdates.isEnabled = true
            activityLocationBinding.btnStopLocationUpdates.isEnabled = false
        }
    }

    /**
     * Starting location updates
     * Check whether location settings are satisfied and then
     * location updates will be requested
     */
    private fun startLocationUpdates() {
        mSettingsClient?.checkLocationSettings(mLocationSettingsRequest)
            ?.addOnSuccessListener(this) {
                Log.i(
                    TAG,
                    "All location settings are satisfied."
                )
                Toast.makeText(
                    applicationContext,
                    "Started location updates!",
                    Toast.LENGTH_SHORT
                ).show()
                mFusedLocationClient!!.requestLocationUpdates(
                    mLocationRequest,
                    mLocationCallback, Looper.myLooper()
                )
                updateLocationUI()
            }
            ?.addOnFailureListener(this) { e ->
                val statusCode = (e as ApiException).statusCode
                when (statusCode) {
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                        Log.i(
                            TAG,
                            "Location settings are not satisfied. Attempting to upgrade " +
                                    "location settings "
                        )
                        try { // Show the dialog by calling startResolutionForResult(), and check the
// result in onActivityResult().
                            val rae = e as ResolvableApiException
                            rae.startResolutionForResult(
                                this@LocationActivity,
                                REQUEST_CHECK_SETTINGS
                            )
                        } catch (sie: SendIntentException) {
                            Log.i(
                                TAG,
                                "PendingIntent unable to execute request."
                            )
                        }
                    }
                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                        val errorMessage =
                            "Location settings are inadequate, and cannot be " +
                                    "fixed here. Fix in Settings."
                        Log.e(TAG, errorMessage)
                        Toast.makeText(this@LocationActivity, errorMessage, Toast.LENGTH_LONG)
                            .show()
                    }
                }
                updateLocationUI()
            }
    }

    fun startLocationButtonClick() { // Requesting ACCESS_FINE_LOCATION using Dexter library
        Dexter.withActivity(this)
            .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse) {
                    mRequestingLocationUpdates = true
                    startLocationUpdates()
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse) {
                    if (response.isPermanentlyDenied) { // open device settings when the permission is
// denied permanently
                        openSettings()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).check()
    }

    fun stopLocationButtonClick() {
        mRequestingLocationUpdates = false
        stopLocationUpdates()
    }

    fun stopLocationUpdates() { // Removing location updates
        mFusedLocationClient
            ?.removeLocationUpdates(mLocationCallback)
            ?.addOnCompleteListener(this) {
                Toast.makeText(
                    applicationContext,
                    "Location updates stopped!",
                    Toast.LENGTH_SHORT
                ).show()
                toggleButtons()
            }
    }

    fun showLastKnownLocation() {
        if (mCurrentLocation != null) {
            Toast.makeText(
                applicationContext, "Lat: " + mCurrentLocation!!.latitude
                        + ", Lng: " + mCurrentLocation!!.longitude, Toast.LENGTH_LONG
            ).show()
        } else {
            Toast.makeText(
                applicationContext,
                "Last known location is not available!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        // Check for the integer request code originally supplied to startResolutionForResult().
        if (requestCode == REQUEST_CHECK_SETTINGS) {
            when (resultCode) {
                Activity.RESULT_OK -> Log.e(
                    TAG,
                    "User agreed to make required location settings changes."
                )
                Activity.RESULT_CANCELED -> {
                    Log.e(
                        TAG,
                        "User chose not to make required location settings changes."
                    )
                    mRequestingLocationUpdates = false
                }
            }
        }
    }

    private fun openSettings() {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        val uri = Uri.fromParts(
            "package",
            BuildConfig.APPLICATION_ID, null
        )
        intent.data = uri
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    public override fun onResume() {
        super.onResume()
        // Resuming location updates depending on button state and
// allowed permissions
        if (mRequestingLocationUpdates!! && checkPermissions()) {
            startLocationUpdates()
        }
        updateLocationUI()
    }

    private fun checkPermissions(): Boolean {
        val permissionState = ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        return permissionState == PackageManager.PERMISSION_GRANTED
    }

    override fun onPause() {
        super.onPause()
        if (mRequestingLocationUpdates!!) { // pausing location updates
            stopLocationUpdates()
        }
    }

    companion object {
        private val TAG = LocationActivity::class.java.simpleName
        // location updates interval - 10sec
        private const val UPDATE_INTERVAL_IN_MILLISECONDS: Long = 10000
        // fastest updates interval - 5 sec
        // location updates will be received if another app is requesting the locations
        // than your app can handle
        private const val FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS: Long = 5000
        private const val REQUEST_CHECK_SETTINGS = 100
    }
}