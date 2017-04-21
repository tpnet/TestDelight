package com.tpnet.testdelight;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;
import com.squareup.sqldelight.SqlDelightStatement;

import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button mBtnAdd;
    private Button mBtnSelect;

 
    private BriteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnAdd = (Button) findViewById(R.id.btn_add);
        mBtnSelect = (Button) findViewById(R.id.btn_select);

        mBtnAdd.setOnClickListener(this);
        mBtnSelect.setOnClickListener(this);

        db = new SqlBrite.Builder().build().wrapDatabaseHelper(new DataBaseHelper(this,1), Schedulers.io());

    }

    @Override
    public void onClick(View v) {
        if(v == mBtnAdd){
            insert();
        }else if(v == mBtnSelect){
            select();
        }
    }
    
    
    
    private void insert(){
        ProgramModel.InsertProgram insertProgram = new ProgramModel.InsertProgram(db.getWritableDatabase());
        insertProgram.bind("link","name");
        long i = insertProgram.program.executeInsert();
        Log.e("@@","insert:"+i);
    }
    
    private void select(){
        SqlDelightStatement sqlDelightStatement = Program.FACTORY.selectDownName("link");
        
        db.createQuery(Program.TABLE_NAME,sqlDelightStatement.statement,sqlDelightStatement.args)
                .map(new Func1<SqlBrite.Query, String>() {
                    @Override
                    public String call(SqlBrite.Query query) {
                        return Program.ROW_NAMW_MAPPER.map(query.run());
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.e("@@","name: "+s);
                    }
                });
    }
    
    
}
