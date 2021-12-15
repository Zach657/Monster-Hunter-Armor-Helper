package com.example.monsterhunterarmorhelper

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.net.URL
import java.security.AlgorithmConstraints
import kotlin.concurrent.thread
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {

    var entities = arrayListOf<MonsterHunterArmorEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(getApplicationContext())
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
        val adapter = ArmorItemAdapter(this,R.layout.armor_list_item, entities)
        recyclerView.setAdapter(adapter)

        val searchView: SearchView = findViewById(R.id.search_view)
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(search: String): Boolean {
                adapter.filter.filter(search)
                return false
            }
            override fun onQueryTextChange(newSearch: String): Boolean {
                adapter.filter.filter(newSearch)
                return false
            }
        })

        thread {
            entities = DataHandler.fetchData()
            this@MainActivity.runOnUiThread {
                adapter.updateData(entities)
            }
        }
    }
}

// Custom Recycler View List Item Adapter
class ArmorItemAdapter(private val context: Context, private val layoutResource: Int, private var arrayList: ArrayList<MonsterHunterArmorEntity>) : RecyclerView.Adapter<ArmorItemAdapter.ViewHolder>(), View.OnClickListener, Filterable {
    var originalList: ArrayList<MonsterHunterArmorEntity> = arrayList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArmorItemAdapter.ViewHolder {
        var v: View= LayoutInflater.from(context).inflate(layoutResource, parent, false)
        v.setOnClickListener(this)
        return ViewHolder(v)
    }

    override fun onClick(p0: View?) {
        //TODO: Implement Details Page
    }

    //List Population Logic
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var armorEntity: MonsterHunterArmorEntity = arrayList.get(position)
        //Type Icon - set the appropriate image based on the type
        when(armorEntity.type) {
            "head" -> holder.typeIcon.setImageResource(R.drawable.ic_head)
            "chest" -> holder.typeIcon.setImageResource(R.drawable.ic_chest)
            "waist" -> holder.typeIcon.setImageResource(R.drawable.ic_waist)
            "gloves" -> holder.typeIcon.setImageResource(R.drawable.ic_gloves)
            "legs" -> holder.typeIcon.setImageResource(R.drawable.ic_legs)
        }
        //Name
        holder.name.text = armorEntity.name
        //Rarity
        holder.rarity.text = armorEntity.rarity.toString()
        //Rank
        holder.rank.text = armorEntity.rank
        //Defense
        holder.defense.text = armorEntity.defense.base.toString()
        //Slot 1
        if(armorEntity.slots.size >= 1){
            holder.rank1layout.visibility = View.VISIBLE
            holder.rank1.text = armorEntity.slots.get(0).value.toString()
        }
        else{
            holder.rank1layout.visibility = View.INVISIBLE
        }
        //Slot 2
        if(armorEntity.slots.size >= 2){
            holder.rank2layout.visibility = View.VISIBLE
            holder.rank2.text = armorEntity.slots.get(1).value.toString()
        }
        else{
            holder.rank2layout.visibility = View.INVISIBLE
        }
        //Slot 3
        if(armorEntity.slots.size >= 3){
            holder.rank3layout.visibility = View.VISIBLE
            holder.rank3.text = armorEntity.slots.get(2).value.toString()
        }
        else{
            holder.rank3layout.visibility = View.INVISIBLE
        }

    }

    //List Item Updater
    public fun updateData(newList: ArrayList<MonsterHunterArmorEntity>) {
        originalList = newList
        arrayList = originalList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    //View reference instantiator
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        //Type Icon
        val typeIcon: ImageView = view.findViewById(R.id.typeIcon)
        //Name
        val name: TextView = view.findViewById(R.id.name)
        //Rarity
        val rarity: TextView = view.findViewById(R.id.rarity)
        //Rank
        val rank: TextView = view.findViewById(R.id.rank)
        //Defense
        val defense: TextView = view.findViewById(R.id.defense)
        //Rank 1
        val rank1layout: RelativeLayout = view.findViewById(R.id.rank1layout)
        val rank1: TextView = view.findViewById(R.id.rank1)
        //Rank 2
        val rank2layout: RelativeLayout = view.findViewById(R.id.rank2layout)
        val rank2: TextView = view.findViewById(R.id.rank2)
        //Rank 3
        val rank3layout: RelativeLayout = view.findViewById(R.id.rank3layout)
        val rank3: TextView = view.findViewById(R.id.rank3)
    }

    //Custom Filter Logic
    override fun getFilter(): Filter {
        return object : Filter(){
            override fun publishResults(query: CharSequence?, results: FilterResults?) {
                if (results != null) {
                    arrayList = results.values as ArrayList<MonsterHunterArmorEntity>
                }
                notifyDataSetChanged()
            }
            override fun performFiltering(query: CharSequence?): FilterResults {
                var filterResults = Filter.FilterResults()
                if (query.isNullOrEmpty()) {
                    filterResults.values = originalList
                }
                else{
                    filterResults.values = originalList.filter { it.name.contains(query.toString(),true) || it.type.contains(query.toString(), true)}
                }
                return filterResults
            }

        }
    }
}


