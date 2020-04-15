package com.example.rockpaperscissors

import androidx.room.*

@Dao
interface GameDao {

    @Query("SELECT * FROM gameTable")
    suspend fun getAllStats(): List<Game>

    @Insert
    suspend fun insertStat(stat: Game)

    @Delete
    suspend fun deleteStat(stat: Game)

    @Update
    suspend fun updateStat(stat: Game)


    @Query("DELETE FROM gameTable")
    suspend fun deleteAllStats()

    @Query("SELECT COUNT(*) FROM gameTable WHERE winnaar like :resultString")
    suspend fun getResultCount(resultString: String?): Int
}