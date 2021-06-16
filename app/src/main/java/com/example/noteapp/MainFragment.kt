package com.example.noteapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.noteapp.adapters.NotesAdapter
import com.example.noteapp.dataBase.NotesDataBase
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            MainFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler.setHasFixedSize(true)
        recycler.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        GlobalScope.launch (Dispatchers.Main){
            context?.let {
               var notes=   NotesDataBase.getDatabase(it).noteDao().getallNotes()
                recycler.adapter=NotesAdapter(notes)
            }
        }
        creat_note.setOnClickListener {
            replaceFragment(CreateNoteFragment.newInstance(), false)
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
}