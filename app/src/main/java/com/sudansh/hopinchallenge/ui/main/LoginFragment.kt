package com.sudansh.hopinchallenge.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sudansh.hopinchallenge.Constants
import com.sudansh.hopinchallenge.R
import com.sudansh.hopinchallenge.databinding.FragmentMainBinding
import com.sudansh.hopinchallenge.ui.player.PlayerFragmentArgs
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.webview.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                val cookies: String = CookieManager.getInstance().getCookie(url) ?: return
                viewModel.parseToken(cookies)
            }
        }
        val settings: WebSettings = binding.webview.settings
        settings.domStorageEnabled = true
        settings.javaScriptEnabled = true
        binding.webview.loadUrl(Constants.HOPIN_SIGNIN_URL)

        viewModel.streamUrl.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.nav_player, PlayerFragmentArgs(it).toBundle(), null)
        }
    }
}