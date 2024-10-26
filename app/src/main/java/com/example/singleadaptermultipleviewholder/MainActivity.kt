package com.example.singleadaptermultipleviewholder

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.singleadaptermultipleviewholder.databinding.ActivityMainBinding
import com.shuhart.stickyheader.StickyHeaderItemDecorator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val totalList = Database.getItems()
        val adapter = MyAdapter(totalList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){

            var isStickyHeaderAttack=false
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val firstVisibleItemPosition = (binding.recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                if (!isStickyHeaderAttack && firstVisibleItemPosition >0){
                    val  decorator =  StickyHeaderItemDecorator(adapter)
                    decorator.attachToRecyclerView(binding.recyclerView)
                    isStickyHeaderAttack = true
                }else if (isStickyHeaderAttack && firstVisibleItemPosition == 0){
                     binding.recyclerView.removeItemDecorationAt(0)
                    isStickyHeaderAttack = false
                }


            }
        })



    }
}