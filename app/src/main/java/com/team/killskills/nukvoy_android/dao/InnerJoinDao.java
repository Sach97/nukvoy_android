package com.team.killskills.nukvoy_android.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.team.killskills.nukvoy_android.model.InnerJoin;

import java.util.List;

/**
 * Created by Sacha on 14/12/2017.
 */

@Dao
public interface InnerJoinDao {

    @Insert
    void insertAll(InnerJoin... innerjoins);

    @Update
    void updateAll(InnerJoin... innerjoins);

    @Query("SELECT * FROM innerjoin")
    List<InnerJoin> getAll();

    /*@Query("SELECT note.id, note.title, note.description, category.name as categoryName " +
            "FROM note " +
            "LEFT JOIN category ON note.category_id = category.id")
    List<CategoryNote> getCategoryNotes();

    @Query("SELECT note.id, note.title, note.description, note.category_id " +
            "FROM note " +
            "LEFT JOIN category ON note.category_id = category.id " +
            "WHERE note.id = :noteId")
    CategoryNote getCategoryNote(long noteId);*/

    @Delete
    void deleteAll(InnerJoin... innerJoins);
}
