package com.example.rockpaperscissors

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryActivity : AppCompatActivity() {

    private val gameList = arrayListOf<Game>()
    private val gameAdapter = GameAdapter(gameList)
    private lateinit var gameRepository: GameRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        gameAdapter.notifyDataSetChanged()
        gameRepository = GameRepository(this)
        initviews()
        val actionbar = supportActionBar
        actionbar!!.title = "Your game history"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed(){
        val intent = Intent(this, MainActivity::class.java)

        startActivity(intent)
    }

    fun initviews(){
        rvStats.layoutManager = LinearLayoutManager(this@HistoryActivity, RecyclerView.VERTICAL, false)
        rvStats.adapter = gameAdapter
        rvStats.addItemDecoration(DividerItemDecoration(this@HistoryActivity, DividerItemDecoration.VERTICAL))

        getStatsFromDatabase()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_history, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_show_history -> {
                deleteStats()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun deleteStats(){
        mainScope.launch {
            withContext(Dispatchers.IO) {
                gameRepository.deleteAllStats()
            }
            getStatsFromDatabase()
        }
    }

    private fun getStatsFromDatabase() {
        CoroutineScope(Dispatchers.Main).launch {
            val stats = withContext(Dispatchers.IO) {
                gameRepository.getAllStat()
            }
            this@HistoryActivity.gameList.clear()
            this@HistoryActivity.gameList.addAll(stats)
            this@HistoryActivity.gameAdapter.notifyDataSetChanged()
        }
    }

}
