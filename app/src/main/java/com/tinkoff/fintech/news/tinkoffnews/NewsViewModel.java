package com.tinkoff.fintech.news.tinkoffnews;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.widget.Toast;

import com.tinkoff.fintech.news.tinkoffnews.db.AppDatabase;
import com.tinkoff.fintech.news.tinkoffnews.network.NewsCallback;
import com.tinkoff.fintech.news.tinkoffnews.model.NewsObject;
import com.tinkoff.fintech.news.tinkoffnews.network.RetrofitClient;

import java.util.List;

public class NewsViewModel extends AndroidViewModel {

   private MutableLiveData<List<NewsObject>> nodes;

   private AppDatabase appDatabase;

    public NewsViewModel(Application application) {
        super(application);
        appDatabase = AppDatabase.getAppDatabase(this.getApplication());
        getFromDb();
    }

    public LiveData<List<NewsObject>> getNews() {
        if (nodes == null) {
            nodes = new MutableLiveData<List<NewsObject>>();
        }
        loadNews();
        return nodes;
    }

    private void loadNews() {
        RetrofitClient.sendRequest(new NewsCallback() {
            @Override
            public void onSuccess(List<NewsObject> newsObjects) {
                nodes.postValue(newsObjects);
                putToDb(newsObjects);
            }

            @Override
            public void onError() {
                Toast.makeText(getApplication(), "Check your internet connection.", Toast.LENGTH_SHORT).show();
                getFromDb();
            }
        });
    }

    private void putToDb(List<NewsObject> newsObjects) {
        NewsObject [] newsObjectsArray = new NewsObject[newsObjects.size()];
        newsObjects.toArray(newsObjectsArray);
        AsyncTask.execute(() -> appDatabase.newsDao().insertAll(newsObjectsArray));
    }

    private void getFromDb() {
        new AsyncTask<Void,Void,List<NewsObject>>() {
            @Override
            protected List<NewsObject> doInBackground(Void... voids) {
                return appDatabase.newsDao().getAll();
            }

            @Override
            protected void onPostExecute(List<NewsObject> data) {
                nodes.postValue(data);
            }
        }.execute();
    }

}
