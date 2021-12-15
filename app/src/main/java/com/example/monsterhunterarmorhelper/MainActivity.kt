package com.example.monsterhunterarmorhelper

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.net.URL
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {

    var entities = arrayListOf<MonsterHunterArmorEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var placeholder: MonsterHunterArmorEntity = MonsterHunterArmorEntity(
            1,
            "head",
            "100",
            1,
            Defense(1,1,1),
            Resistances(1,1,1,1,1),
            "test attributes",
            "Test Naming",
            "",
            "",
            "",
            "",
            "")
        var placeholder2: MonsterHunterArmorEntity = MonsterHunterArmorEntity(
            1,
            "head",
            "100",
            1,
            Defense(1,1,1),
            Resistances(1,1,1,1,1),
            "test attributes",
            "Test Naming",
            "",
            "",
            "",
            "",
            "")
        var placeholder3: MonsterHunterArmorEntity = MonsterHunterArmorEntity(
            1,
            "head",
            "100",
            1,
            Defense(1,1,1),
            Resistances(1,1,1,1,1),
            "test attributes",
            "Test Naming",
            "",
            "",
            "",
            "",
            "")

        entities.add(placeholder)
        entities.add(placeholder2)
        entities.add(placeholder3)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(getApplicationContext())
        recyclerView.layoutManager = layoutManager


        val adapter = ArmorItemAdapter(this,R.layout.armor_list_item, entities)
        recyclerView.setAdapter(adapter)
        adapter.notifyDataSetChanged()

    }
}

class ArmorItemAdapter(private val context: Context, private val layoutResource: Int, private val arrayList: ArrayList<MonsterHunterArmorEntity>) : RecyclerView.Adapter<ArmorItemAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArmorItemAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(layoutResource, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var armorEntity: MonsterHunterArmorEntity = arrayList.get(position)
        //Type Icon
        //holder.typeIcon.setImageDrawable()
        //Name
        holder.name.text = armorEntity.name
        //Rarity
        holder.rarity.text = armorEntity.rarity.toString()
        //Rank
        holder.rank.text = armorEntity.rank
        /*
        //Rank 1
        val rank1layout: RelativeLayout = view.findViewById(R.id.rank1layout)
        val rank1: TextView = view.findViewById(R.id.rank1)
        //Rank 2
        val rank2layout: RelativeLayout = view.findViewById(R.id.rank2layout)
        val rank2: TextView = view.findViewById(R.id.rank2)
        //Rank 3
        val rank3layout: RelativeLayout = view.findViewById(R.id.rank3layout)
        val rank3: TextView = view.findViewById(R.id.rank3)
         */

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        //Type Icon
        val typeIcon: ImageView = view.findViewById(R.id.typeIcon)
        //Name
        val name: TextView = view.findViewById(R.id.name)
        //Rarity
        val rarity: TextView = view.findViewById(R.id.rarity)
        //Rank
        val rank: TextView = view.findViewById(R.id.rank)
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
}


