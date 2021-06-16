package com.example.noteapp

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.content.pm.PackageManager.*
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils.indexOf
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.content.PermissionChecker
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.noteapp.dataBase.NotesDataBase
import com.example.noteapp.util.FragmentBottomSheet
import entities.Notes
import kotlinx.android.synthetic.main.fragment_create_note.*
import kotlinx.android.synthetic.main.fragment_create_note.tvDateTime
import kotlinx.android.synthetic.main.note_item.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import java.security.Permission
import java.text.SimpleDateFormat
import java.util.*
import java.util.jar.Manifest

class CreateNoteFragment : Fragment() {
    var selectedColor = "#272727"
    var currentDate: String? = null
    private val SELECTED_IMG = 1000
    private val PERMISSION_CODE = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_note, container, false)
    }

    companion object {


        @JvmStatic
        fun newInstance() =
            CreateNoteFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sdf = SimpleDateFormat("MM/dd/yyyy")
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(
            broadcastReceiver,
            IntentFilter("bottom_sheet_action")
        )
        colorView.setBackgroundColor(Color.parseColor(selectedColor))
        currentDate = sdf.format(Date())
        tvDateTime.text = currentDate
        img_done.setOnClickListener {
            saveNote()
        }
        img_back.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        imgmore.setOnClickListener {
            val noteBottomSheet = FragmentBottomSheet.newInstance()
            noteBottomSheet.show(
                requireActivity().supportFragmentManager,
                "note Bottum Sheet Fragment"
            )
        }
    }

    private fun saveNote() {
        if (note_title.text.isNullOrEmpty()) {
            Toast.makeText(context, "Note Title is required !", Toast.LENGTH_SHORT).show()
        }
        if (note_sub_title.text.isNullOrEmpty()) {
            Toast.makeText(context, "Note Sub Title is required !", Toast.LENGTH_SHORT).show()
        }
        if (img_note == null && note_desc.text.isNullOrEmpty()) {

            Toast.makeText(context, "Note Description is required !", Toast.LENGTH_SHORT).show()
        } else {
            GlobalScope.launch(Dispatchers.Main) {
                var notes = Notes()
                notes.title = note_title.text.toString()
                notes.subTitle = note_sub_title.text.toString()
                notes.noteText = note_desc.text.toString()
                notes.color = selectedColor
                notes.dateTime = currentDate
                context?.let {
                    NotesDataBase.getDatabase(it).noteDao().insertNote(notes)
                    note_title.setText("")
                    note_sub_title.setText("")
                    note_desc.setText("")
                }
            }
        }
    }

    fun replaceFragment(fragment: Fragment, transaction: Boolean) {
        val fragmentTransiction = activity!!.supportFragmentManager.beginTransaction()
        if (transaction) {
            fragmentTransiction.setCustomAnimations(
                android.R.anim.slide_out_right,
                android.R.anim.slide_in_left
            )
        }
        fragmentTransiction.replace(R.id.container, fragment)
            .addToBackStack(fragment.javaClass.simpleName).commit()
    }

    val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val receivedAction = intent!!.getStringExtra("action")
            when (receivedAction) {
                "Blue" -> {
                    selectedColor = intent.getStringExtra("SelectedColor")!!
                    colorView.setBackgroundColor(Color.parseColor(selectedColor))
                }
                "Yellow" -> {
                    selectedColor = intent.getStringExtra("SelectedColor")!!
                    colorView.setBackgroundColor(Color.parseColor(selectedColor))

                }
                "Purble" -> {
                    selectedColor = intent.getStringExtra("SelectedColor")!!
                    colorView.setBackgroundColor(Color.parseColor(selectedColor))
                }
                "Green" -> {
                    selectedColor = intent.getStringExtra("SelectedColor")!!
                    colorView.setBackgroundColor(Color.parseColor(selectedColor))
                }
                "Orange" -> {
                    selectedColor = intent.getStringExtra("SelectedColor")!!
                    colorView.setBackgroundColor(Color.parseColor(selectedColor))
                }
                "Black" -> {
                    selectedColor = intent.getStringExtra("SelectedColor")!!
                    colorView.setBackgroundColor(Color.parseColor(selectedColor))
                }
                "img" -> {
                    handlePermission()

                }
                else -> {
                    selectedColor = intent.getStringExtra("SelectedColor")!!
                    colorView.setBackgroundColor(Color.parseColor(selectedColor))
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(broadcastReceiver)
    }

    private fun handlePermission() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_DENIED
            ) {
                val permission = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                requestPermissions(
                    permission
                    , PERMISSION_CODE
                )
            } else {
                pickImgfromGallary()
            }
        }
    }

    private fun pickImgfromGallary() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, SELECTED_IMG)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == SELECTED_IMG) {
            img_note.visibility = View.VISIBLE
            note_desc.visibility = View.GONE
            img_note.setImageURI(data?.data)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImgfromGallary()
                } else {
                    Toast.makeText(requireContext(), "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

}