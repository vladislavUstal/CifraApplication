package ru.vladislav.cifraapplication.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import ru.vladislav.cifraapplication.R
import ru.vladislav.cifraapplication.data.model.Bank
import ru.vladislav.cifraapplication.databinding.FragmentMainBinding
import ru.vladislav.cifraapplication.ui.base.BaseFragment

class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val bankAdapter = BankAdapter(
        bankInterface = object : BankInterface {
            override fun onClickBank(bank: Bank) {
                Toast.makeText(requireContext(), "onBankClick ${bank.bank}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    )

    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getDataBinding().rvBankList.adapter = bankAdapter
        getDataBinding().viewModel = viewModel
        viewModel.bankList.observe(viewLifecycleOwner) { bankList ->
            bankAdapter.refresh(bankList)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.initBankList()
    }

    override fun getLayoutId() = R.layout.fragment_main

    companion object {
        fun newInstance() = MainFragment()
    }

}