package com.example.alestereposteria.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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

        purchaseAdapter =
            PurchaseAdapter(purchaseListFromServer, onItemClicked = { onPurchaseItemClicked(it) })

        homeViewModel.loadPurchaseFromServerDone.observe(viewLifecycleOwner) { result ->
            onLoadPurchaseFromServerDoneSubscribe(result)
        }

        homeBinding.purchaseRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@HomeFragment.requireContext())
            adapter = purchaseAdapter
            setHasFixedSize(false)
        }

        homeViewModel.loadPurchaseFromServer()


    }

    private fun onPurchaseItemClicked(it: Purchase) {

    }

    private fun onLoadPurchaseFromServerDoneSubscribe(purchaseListFromServerLoaded: ArrayList<Purchase>) {
        purchaseListFromServer = purchaseListFromServerLoaded
        purchaseAdapter.appendItems(purchaseListFromServer)
    }
}