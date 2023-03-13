package com.example.mvvmget;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mvvmget.Adapter.MyAdapter;
import com.example.mvvmget.DAO.GetDao;
import com.example.mvvmget.Database.GetDatabase;
import com.example.mvvmget.Model.GetModel;
import com.example.mvvmget.Model.GetViewModel;
import com.example.mvvmget.Repository.CreatePostRepository;
import com.example.mvvmget.Repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private ImageView btnCreatePost;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private Repository repository;
    private GetDatabase database;
    private CreatePostRepository createPostRepository;
    private EditText editTextTitle, editTextText;
    private Button button_save;
    private GetDao getDao;
    private GetViewModel viewModel;
    private GetModel getModel;
    private int listSize = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreatePost = findViewById(R.id.image);
        recyclerView = findViewById(R.id.recId);
        // postList = new ArrayList<>();
        // database = GetDatabase.getInstance(MainActivity.this);
        // postList = database.getDao().getAllData();
        repository = new Repository(getApplication(), database);
        myAdapter = new MyAdapter(this, new ArrayList<>());
        getModel = new GetModel();
        setAdapter();

        createPostRepository = new CreatePostRepository(this, database);
        btnCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.post_layout);

                int width = WindowManager.LayoutParams.MATCH_PARENT;
                int hight = WindowManager.LayoutParams.WRAP_CONTENT;

                dialog.getWindow().setLayout(width, hight);
                dialog.show();


                // Initialize and post Veriable
                EditText editText_Title = dialog.findViewById(R.id.edit_title_ID);
                EditText editText_Text = dialog.findViewById(R.id.edit_text_ID);
                Button button_save = dialog.findViewById(R.id.button_Save);

                button_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String title = editText_Title.getText().toString().trim();
                        String text = editText_Text.getText().toString().trim();

                        if (TextUtils.isEmpty(title)) {
                            Toast.makeText(MainActivity.this, "Enter Title", Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(text)) {
                            Toast.makeText(MainActivity.this, "Enter Body", Toast.LENGTH_SHORT).show();
                        } else {
                            getModel.setTitle(title);
                            getModel.setBody(text);
                            viewModel.requestPost(getModel);
                            dialog.dismiss();
                        }
                    }
                });
            }
        });

        viewModel = new ViewModelProvider(this).get(GetViewModel.class);
        viewModel.requestGetData();
        viewModel.getAllData().observe(this, new Observer<List<GetModel>>() {
            @Override
            public void onChanged(List<GetModel> getModelList) {
                Log.d(TAG, "getModel: " + getModelList.size());
                if (myAdapter != null) {
                    myAdapter.setPostList(getModelList);
                    listSize = getModelList.size();
                }
            }
        });

    }

    private void setAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdapter);
    }
}