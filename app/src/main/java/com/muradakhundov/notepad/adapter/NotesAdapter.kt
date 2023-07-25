package com.muradakhundov.notepad.adapter

import android.content.Context
import android.nfc.cardemulation.CardEmulation
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.muradakhundov.notepad.R
import com.muradakhundov.notepad.databinding.ListItemBinding
import com.muradakhundov.notepad.model.Note
import kotlin.random.Random

class NotesAdapter(val mContext : Context , val listener : NotesItemClickListener) :RecyclerView.Adapter<NotesAdapter.ItemDesignHolder>() {


    private val fullList = ArrayList<Note>()
    private val noteList = ArrayList<Note>()

    inner class ItemDesignHolder(val binding : ListItemBinding) :  RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemDesignHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(mContext))
        return ItemDesignHolder(binding)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: ItemDesignHolder, position: Int) {
        val b= holder.binding
        val listNote = noteList.get(position)
        b.tvNote.text = listNote.note
        b.tvTitle.text = listNote.title
        b.tvDate.text = listNote.date
        b.tvTitle.isSelected = true
        b.tvDate.isSelected = true

        b.notesLayout.setBackgroundColor(holder.itemView.resources.getColor(randomColor(),null))

        b.notesLayout.setOnClickListener {

            listener.onItemClicked(noteList[holder.adapterPosition])
        }

        b.notesLayout.setOnLongClickListener {
            listener.onLongItemClicked(noteList[holder.adapterPosition],b.notesLayout)
            true
        }
    }


    fun randomColor() :Int{
        val list = ArrayList<Int>()
        list.add(R.color.noteColor1)
        list.add(R.color.noteColor2)
        list.add(R.color.noteColor3)
        list.add(R.color.noteColor4)
        list.add(R.color.noteColor5)
        list.add(R.color.noteColor6)


        //time changes always so random will not repeat
        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(list.size)

        return list[randomIndex]
    }

    fun updateList(newList : List<Note>){
        fullList.clear()
        fullList.addAll(newList)

        noteList.clear()
        noteList.addAll(fullList)
        notifyDataSetChanged()
    }

    fun filterList(search : String){
        noteList.clear()

        for (item in fullList){
            if (item.title?.lowercase()?.contains(search.lowercase()) == true ||
                item.note?.lowercase()?.contains(search.lowercase()) == true){
                    noteList.add(item)
                }
            }
        notifyDataSetChanged()
        }

    interface NotesItemClickListener{
        fun onItemClicked(note : Note)
        fun onLongItemClicked(note:Note,cardview :CardView)

    }
    }


