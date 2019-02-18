package me.gilo.wc.ui

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_shop.*
import kotlinx.android.synthetic.main.content_shop.*
import me.gilo.wc.R
import me.gilo.wc.adapter.ProductAdapter
import me.gilo.woodroid.Woocommerce
import me.gilo.woodroid.models.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ShopActivity : BaseActivity() {

    lateinit var adapter : ProductAdapter
    lateinit var products: ArrayList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)
        setSupportActionBar(toolbar)

        title = "Shop"

        val layoutManager = GridLayoutManager(baseContext, 2)
        rvShop.layoutManager = layoutManager
        rvShop.isNestedScrollingEnabled = false

        products = ArrayList<Product>()

        adapter = ProductAdapter(products)
        rvShop.adapter = adapter

        products()

    }

    //Not best practise, but works for purposes of demo
    private fun products() {
        val woocommerce = Woocommerce.Builder()
            .setSiteUrl("http://157.230.131.179")
            .setApiVersion(Woocommerce.API_V2)
            .setConsumerKey("ck_26c61abd7eeff238d87dc56585bf26cb2d1a1ec3")
            .setConsumerSecret("cs_062e8e3a7ae0ce08fdebc0c39f8f834d5e87598e")
            .build()

        woocommerce.ProductRepository().products().enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                val productsResponse = response.body()
                for (product in productsResponse!!) {
                    products.add(product)
                }

                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {

            }
        })

    }

}
