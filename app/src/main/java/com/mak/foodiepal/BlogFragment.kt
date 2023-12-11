package com.mak.foodiepal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment


class BlogFragment : Fragment() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.fragment_blog, container, false)

        val view = inflater.inflate(R.layout.fragment_blog, container, false)
        webView = view.findViewById(R.id.webView)

        val (headline, content) = getBlogPost()

        val htmlContent = "<html><body><h1>$headline</h1><p>$content.</p></body></html>"
        webView.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null)

        //val url = "https://www.softwaretestingmagazine.com/"
        //webView.loadUrl(url)

        return view
    }

    private fun getBlogPost(): Pair<String, String> {
        // Simulating data retrieval
        val headline = "Colors of Jupiter"
        val content = """
            The outer appearance of Jupiter is dominated by its atmosphere, which is divided into distinct cloud bands and zones. These cloud bands are created by the planet's rapid rotation and complex atmospheric dynamics. The appearance of Jupiter's atmosphere is characterized by alternating light-colored zones and darker belts, which are caused by differences in the altitude and composition of clouds.
            <br/>
            <br/>
            The cloud layers in Jupiter's atmosphere are composed mostly of ammonia crystals, ammonium hydrosulfide, and water vapor. The exact composition and properties of these clouds are still not fully understood, and observations from spacecraft like NASA's Juno mission continue to provide valuable data about Jupiter's atmospheric structure.
        """.trimIndent()
        return Pair(headline, content)
    }
}