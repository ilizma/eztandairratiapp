package com.ilizma.presentation.ui.content.menu

import android.os.Bundle
import android.view.View
import com.ilizma.presentation.R
import com.ilizma.presentation.extensions.callNumber
import com.ilizma.presentation.extensions.openUrl
import com.ilizma.presentation.extensions.setOnReactiveClickListener
import com.ilizma.presentation.ui.base.BaseFragment
import com.ilizma.presentation.ui.content.MainActivity
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment : BaseFragment() {

    override var fragmentLayout = R.layout.fragment_menu

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolBar()
        setUpUI()
    }

    private fun setUpToolBar() {
        (activity as MainActivity).supportActionBar?.title = getString(R.string.title_menu)
    }

    private fun setUpUI() {
        // TODO: 03/06/2020 get url and phones from service
        twitterTxv.setOnReactiveClickListener {
            context?.openUrl(
                try {
                    context?.packageManager?.getPackageInfo("com.twitter.android", 0)
                    "twitter://user?user_id=1256809951"
                } catch (e: Exception) {
                    "https://twitter.com/EztandaIrratia"
                }
            )
        }
        facebookTxv.setOnReactiveClickListener {
            context?.openUrl(
                try {
                    context?.packageManager?.getPackageInfo("com.facebook.katana", 0)
                    "fb://profile/100003479888864"
                } catch (e: Exception) {
                    "https://www.facebook.com/eztanda.irratia"
                }
            )
        }
        whatsappTxv.setOnReactiveClickListener {
            context?.callNumber("633489072")// TODO: 8/12/21 unify whatsapp and phone 
        }
        phoneTxv.setOnReactiveClickListener {
            context?.callNumber("948563766")
        }
        webTxv.setOnReactiveClickListener {
            context?.openUrl("http://eztanda.com/")
        }
    }

}