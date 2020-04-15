package com.example.rockpaperscissors

import android.content.Context

public class GameRepository (context: Context) {

    private var gameDao: GameDao

    init {
        val gameRoomDatabase = GameRoomDatabase.getDatabase(context)
        gameDao = gameRoomDatabase!!.gameDao()
    }

    suspend fun getAllStat(): List<Game> {
        return gameDao.getAllStats()
    }

    suspend fun insertStat(stat: Game) {
        gameDao.insertStat(stat)
    }

    suspend fun deleteStat(stat: Game) {
        gameDao.deleteStat(stat)
    }

    suspend fun updateStat(stat: Game) {
        gameDao.updateStat(stat)
    }

    suspend fun getResultCount (resultString: String?) :Int {
        // print("resultString is: "+resultString)
        return gameDao.getResultCount(resultString)
    }

    suspend fun deleteAllStats() = gameDao.deleteAllStats()

}