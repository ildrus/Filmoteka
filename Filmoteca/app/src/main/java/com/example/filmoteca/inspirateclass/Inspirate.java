package com.example.filmoteca.inspirateclass;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.filmoteca.R;
import com.example.filmoteca.adapters.GhibliAdapter;
import com.example.filmoteca.models.FilmGhibli;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Inspirate extends AppCompatActivity {

    ProgressBar progressBar;
    Activity activity=this;
    ListView resultView;
    ArrayList<FilmGhibli> filmGhibliList;
    GhibliAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspirate);
        inicializate();
        conect();
    }

    private void inicializate() {
        resultView=findViewById(R.id.listFilmGhibli);
        progressBar=findViewById(R.id.progressBar);
        filmGhibliList=new ArrayList<FilmGhibli>();
        adapter=new GhibliAdapter(activity,R.layout.rowghibli,filmGhibliList);
        resultView.setAdapter(adapter);
    }

    public void conect(){
        if(isConnected()){
            progressBar.setVisibility(View.VISIBLE);
            getfromRetrofit();
        }
        else{
            Toast.makeText(activity, getString(R.string.e_conected), Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()){
            return true;
        }
        else {
            return false;
        }
    }

    public void getfromRetrofit(){
        MyService service = RetrofitFilmInstance.getRetrofitFilmInstance().create(MyService.class);
        Call<List<FilmGhibli>> call = service.getFilms();
        call.enqueue(new Callback<List<FilmGhibli>>() {
            @Override
            public void onResponse(Call<List<FilmGhibli>> call, Response<List<FilmGhibli>> response) {
                filmGhibliList.addAll(response.body());
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<FilmGhibli>> call, Throwable t) {
                Toast.makeText(activity, getString(R.string.e_ghibli_list), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
