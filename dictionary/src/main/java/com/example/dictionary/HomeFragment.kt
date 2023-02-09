package com.example.dictionary

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private lateinit var adapter: WordAdapter

    private lateinit var recyclerView: RecyclerView
    private lateinit var words: ArrayList<Word>
    private lateinit var databaseAccess: DatabaseAccess

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.rvWord)


        context?.let {
            databaseAccess = DatabaseAccess(it)
            words = databaseAccess.getInstance(it).getAllWords()
        }

        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = WordAdapter(words)
        recyclerView.adapter = adapter
        adapter.onWordClick = {
            val intent = Intent(this@HomeFragment.requireContext(), DetailActivity::class.java)
            intent.putExtra("word", it)
            startActivity(intent)
        }
    }
}