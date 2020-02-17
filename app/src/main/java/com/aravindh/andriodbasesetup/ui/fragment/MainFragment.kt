package com.aravindh.andriodbasesetup.ui.fragment


import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.os.Environment
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKeys
import com.aravindh.andriodbasesetup.R
import com.aravindh.andriodbasesetup.base.BaseFragment
import com.aravindh.andriodbasesetup.databinding.FragmentMainBinding
import com.aravindh.andriodbasesetup.ui.adapter.MainAdapter
import com.aravindh.andriodbasesetup.ui.adapter.MainClickListener
import com.aravindh.andriodbasesetup.utils.*
import com.aravindh.andriodbasesetup.viewmodel.MainViewModel
import java.io.File


/**
 * A simple [Fragment] subclass.
 */

const val EDIT_TEXT_VALUE = "edit_text_value"

class MainFragment : BaseFragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var sharedPref: SharedPref

    //    private lateinit var viewModel: MainViewModel
    private val viewModel by lazy {
        getViewModel { MainViewModel() }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mainViewModel = viewModel


        context?.let {
            sharedPref = SharedPref(it)
        }


        val mainLifeCycleOwner = MainLifeCycleOwner(this.lifecycle)
        setHasOptionsMenu(true)

        val adapter = MainAdapter(
            MainClickListener {
                Logger.d(it.photoPath)


                //encryptFile(it.photoPath,it.photoName)

                /*// encryptFile("https://www.w3.org/TR/PNG/iso_8859-1.txt", "iso_8859-1.txt")
                    encryptFile(
                        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                        "BigBuckBunny.mp4"
                )*/
            })

        binding.recyclerViewPhotos.adapter = adapter

        viewModel.photos.observe(this, Observer {
            Logger.d(it.size.toString())
            adapter.submitList(it)
        })


        //val sharedPref = context?.let { SharedPref().getSharedPref(it) }


        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item, view!!.findNavController()
        ) || super.onOptionsItemSelected(item)
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Logger.w("onSaveInstanceState called...")
        //outState.putString(EDIT_TEXT_VALUE, binding.messageEditText.text.toString().trim())
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Logger.w("onViewStateRestored called...")
    }


    private fun makeDirectory() {
        val filename = "MyFolder"

        val directory: File
        activity?.let {
            if (filename.isEmpty()) {
                directory = it.filesDir
            } else {
                directory = it.getDir(filename, MODE_PRIVATE)
            }

            val files = directory.listFiles()

        }
    }

    fun encryptFile(filePath: String, fileName: String) {
        val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)

        val dirFolder = Environment.getRootDirectory().path + File.separator + "/MyFolder"
        val fp = "$dirFolder/$fileName"

        DownloadTask(context, File(fp), "Downloading...").execute()

        /*  val request = DownloadManager.Request(Uri.parse(filePath))
          request.setTitle(fileName)
          // in order for this if to run, you must use the android 3.2 to compile your app
          request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)


          request.setDestinationInExternalPublicDir(dirFolder, fileName)

          // get download service and enqueue file
          val manager = getSystemService<DownloadManager>(context!!, DownloadManager::class.java)
          manager!!.enqueue(request)*/


        /* val encryptedFile = EncryptedFile.Builder(
             File(fp),
             context!!,
             masterKeyAlias,
             EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
         ).build()

          encryptedFile.openFileInput().bufferedReader().useLines { lines ->
              Logger.d(lines.toString())
          }*/
    }

    fun decryptFile() {
        // Although you can define your own key generation parameter specification, it's
        // recommended that you use the value specified here.
        val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)

        // Creates a file with this name, or replaces an existing file
        // that has the same name. Note that the file name cannot contain
        // path separators.
        val fileToWrite = "my_sensitive_data.txt"
        val encryptedFile = EncryptedFile.Builder(
            File(Environment.getRootDirectory(), fileToWrite),
            context!!,
            masterKeyAlias,
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build()

        encryptedFile.openFileOutput().bufferedWriter().use {
            it.write("MY SUPER-SECRET INFORMATION")
        }
    }


}
