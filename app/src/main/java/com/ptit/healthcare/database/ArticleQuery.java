package com.ptit.healthcare.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ptit.healthcare.entities.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleQuery {
    private Context context;
    private final DatabaseHelper databaseHelper;
    private static final String TABLE_NAME = "article";
    private static final String ID = "id";
    private static final String PATHIMAGE = "pathImage";
    private static final String NAME = "name_article";
    private static final String DESCRIPTION = "description";
    private static final String URL = "url";

    public ArticleQuery(Context context) {
        this.context = context;
        this.databaseHelper=new DatabaseHelper(context);
    }

    public List<Article> getAll(){
        SQLiteDatabase db=databaseHelper.getReadableDatabase();
        String sqlQuery = "SELECT * FROM " + TABLE_NAME;
        List<Article> articles=new ArrayList<>();
        Cursor cursor = db.rawQuery(sqlQuery, null);
        if(cursor.moveToFirst()){
            do{
                Article article=new Article();
                article.setId(cursor.getInt(0));
                article.setPathImage(cursor.getString(1));
                article.setName_article(cursor.getString(2));
                article.setDescription(cursor.getString(3));
                article.setUrl(cursor.getString(4));

                articles.add(article);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return articles;

    }

    public Article getSingle(int id) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{ID, PATHIMAGE,NAME,
                        DESCRIPTION, URL}, ID + "=?",
                new String[]{String.valueOf(id)},null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Article article = new Article(cursor.getInt(0), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getString(4));

        cursor.close();
        db.close();

        return article;
    }

    public void add(Article article) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(PATHIMAGE, article.getPathImage());
        values.put(NAME, article.getName_article());
        values.put(DESCRIPTION, article.getDescription());
        values.put(URL, article.getUrl());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public int update(Article article) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(PATHIMAGE, article.getPathImage());
        values.put(NAME, article.getName_article());
        values.put(DESCRIPTION, article.getDescription());
        values.put(URL, article.getUrl());

        return db.update(TABLE_NAME, values, ID + "=?",
                new String[] {String.valueOf(article.getId())});
    }

    public int delete(int id) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        return db.delete(TABLE_NAME, ID + "=?", new String[] {String.valueOf(id)});
    }
}
