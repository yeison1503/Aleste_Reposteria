package com.example.alestereposteria.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alestereposteria.R
import com.example.alestereposteria.databinding.FragmentHomeBinding
import com.example.alestereposteria.sever.Purchase

class HomeFragment : Fragment() {

    private lateinit var homeBinding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var purchaseAdapter: PurchaseAdapter
    private var purchaseListFromServer: ArrayList<Purchase> = ArrayList()    //firebase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeBinding.purchaseRecyclerView.apply {
            /* layoutManager = LinearLayoutManager(this@HomeFragment.requireContext())
             adapter = purchaseAdapter
             setHasFixedSize(false)*/
        }

        homeViewModel.loadPurchaseFromServerDone.observe(viewLifecycleOwner) { result ->
            onLoadPurchaseFromServerDoneSubscribe(result)
        }

        homeViewModel.loadPurchaseFromServer()


    }

    private fun onLoadPurchaseFromServerDoneSubscribe(purchaseListFromServerLoaded: ArrayList<Purchase>) {
        /*purchaseListFromServer = purchaseListFromServerLoaded
        purchaseAdapter.appendItems(purchaseListFromServer)*/
    }
}