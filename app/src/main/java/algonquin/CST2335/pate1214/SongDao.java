package algonquin.CST2335.pate1214;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface SongDao {
    @Insert
    void insert(Song song);
}