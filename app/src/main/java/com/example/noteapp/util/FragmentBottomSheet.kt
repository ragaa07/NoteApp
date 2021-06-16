package com.example.noteapp.util

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.noteapp.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.note_color_bottom_sheet.*

class FragmentBottomSheet:BottomSheetDialogFragment() {
    var selectedColor="#272727"
    companion object{
        fun newInstance():FragmentBottomSheet{
            val args=Bundle()
            val fragment=FragmentBottomSheet()
            fragment.arguments=args
            return fragment
        }
    }
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        val view=LayoutInflater.from(context).inflate(R.layout.note_color_bottom_sheet,null)
        dialog.setContentView(view)
        val param=(view.parent as View).layoutParams as CoordinatorLayout.LayoutParams
        val behavior=param.behavior
        if (behavior is BottomSheetBehavior<*>)
        {
            behavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    TODO("Not yet implemented")
                }

                override fun onStateChanged(bottomSheet: View, newState: Int) {
                   var state=" "
                    when (newState){
                        BottomSheetBehavior.STATE_DRAGGING->{
                            state="DRAGGING"
                        }
                        BottomSheetBehavior.STATE_COLLAPSED->{
                            state="COLLAPSED"
                        }
                        BottomSheetBehavior.STATE_EXPANDED->{
                            state="EXPANDED"
                        }
                        BottomSheetBehavior.STATE_SETTLING->{
                            state="SETTLING"
                        }
                        BottomSheetBehavior.STATE_HIDDEN->{
                            state="HIDDEN"
                            dismiss()
                           behavior.state= BottomSheetBehavior.STATE_COLLAPSED
                        }

                    }
                }
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListner()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.note_color_bottom_sheet,container,false)
    }
    public fun setClickListner(){
        fNote1.setOnClickListener(View.OnClickListener {
            imgNote1.setImageResource(R.drawable.ic_tick)
            imgNote2.setImageResource(0)
            imgNote4.setImageResource(0)
            imgNote5.setImageResource(0)
            imgNote6.setImageResource(0)
            imgNote7.setImageResource(0)
            selectedColor="#4e33ff"
            val intent= Intent("bottom_sheet_action")
            intent.putExtra("action","Blue")
            intent.putExtra("SelectedColor",selectedColor)
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)

        })
        fNote2.setOnClickListener(View.OnClickListener {
            imgNote1.setImageResource(0)
            imgNote2.setImageResource(R.drawable.ic_tick)
            imgNote4.setImageResource(0)
            imgNote5.setImageResource(0)
            imgNote6.setImageResource(0)
            imgNote7.setImageResource(0)
            selectedColor="#ffd633"
            val intent= Intent("bottom_sheet_action")
            intent.putExtra("action","Yellow")
            intent.putExtra("SelectedColor",selectedColor)
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
        })
        fNote4.setOnClickListener(View.OnClickListener {
            imgNote1.setImageResource(0)
            imgNote2.setImageResource(0)
            imgNote4.setImageResource(R.drawable.ic_tick)
            imgNote5.setImageResource(0)
            imgNote6.setImageResource(0)
            imgNote7.setImageResource(0)
            selectedColor="#ae3b76"
            val intent= Intent("bottom_sheet_action")
            intent.putExtra("action","Purble")
            intent.putExtra("SelectedColor",selectedColor)
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
        })
        fNote5.setOnClickListener(View.OnClickListener {
            imgNote1.setImageResource(0)
            imgNote2.setImageResource(0)
            imgNote4.setImageResource(0)
            imgNote5.setImageResource(R.drawable.ic_tick)
            imgNote6.setImageResource(0)
            imgNote7.setImageResource(0)
            selectedColor="#0aebaf"
            val intent= Intent("bottom_sheet_action")
            intent.putExtra("action","Green")
            intent.putExtra("SelectedColor",selectedColor)
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
        })
        fNote6.setOnClickListener(View.OnClickListener {
            imgNote1.setImageResource(0)
            imgNote2.setImageResource(0)
            imgNote4.setImageResource(0)
            imgNote5.setImageResource(0)
            imgNote6.setImageResource(R.drawable.ic_tick)
            imgNote7.setImageResource(0)
            selectedColor="#ff7746"
            val intent= Intent("bottom_sheet_action")
            intent.putExtra("action","Orange")
            intent.putExtra("SelectedColor",selectedColor)
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
        })
        fNote7.setOnClickListener(View.OnClickListener {
            imgNote1.setImageResource(0)
            imgNote2.setImageResource(0)
            imgNote4.setImageResource(0)
            imgNote5.setImageResource(0)
            imgNote6.setImageResource(0)
            imgNote7.setImageResource(R.drawable.ic_tick)
            selectedColor="#202734"
            val intent= Intent("bottom_sheet_action")
            intent.putExtra("action","Black")
            intent.putExtra("SelectedColor",selectedColor)
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
        })
         img_load.setOnClickListener(View.OnClickListener {
             val intent= Intent("bottom_sheet_action")
             intent.putExtra("action","img")
             LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
         })
    }


}