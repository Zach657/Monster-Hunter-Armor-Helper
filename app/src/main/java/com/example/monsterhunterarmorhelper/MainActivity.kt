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


