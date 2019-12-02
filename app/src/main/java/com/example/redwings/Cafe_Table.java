package com.example.redwings;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class Cafe_Table extends AppCompatActivity  {

    GridView gridViewCafeTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_cafe__table);

        Toolbar toolbar = findViewById(R.id.action_par);
        setSupportActionBar(toolbar);

        gridViewCafeTable=findViewById(R.id.gridViewCafeTaple);
        gridViewCafeTable.setAdapter(new CafeCaptainAdapter(this));
        gridViewCafeTable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(Cafe_Table.this,MakeOrder.class);
                intent.putExtra("PsOrCafe","Cafe");
                intent.putExtra("TableNumber",i);
                startActivity(intent);
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_captain_caafe,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.log_out){
            startActivity(new Intent(Cafe_Table.this,MainActivity.class));
        }
        else if(id==R.id.cafe_menu_pc_action_par){
            startActivity(new Intent(Cafe_Table.this,PC_Table.class));
        }
        return super.onOptionsItemSelected(item);
    }

}
class table{
    int tableId;
    String tableNumber;

    public table(int tableId, String tableNumber) {
        this.tableId = tableId;
        this.tableNumber = tableNumber;
    }
}

class CafeCaptainAdapter extends BaseAdapter{

    ArrayList<table>tableArrayList;

    Context context;
    public CafeCaptainAdapter(Context context) {
        this.context=context;
        tableArrayList=new ArrayList<>();
        Resources resources=context.getResources();
        String[] tempTablesNumbers=resources.getStringArray(R.array.tables_cafe);
        int cafeTableLogo=R.drawable.ic_cafe;
        for (int i=0;i<=29;i++){
            table table=new table(cafeTableLogo,tempTablesNumbers[i]);
            tableArrayList.add(table);
        }
    }

    @Override
    public int getCount() {
        return tableArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return tableArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    class ViewHolder{
        ImageView tableImage;
        TextView tableText;

        public ViewHolder(View view) {
            tableImage=view.findViewById(R.id.imageViewitem_design_cafe);
            tableText=view.findViewById(R.id.textView_item_design_tables);
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row=view;
        ViewHolder viewHolder=null;
        if (row==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row= inflater.inflate(R.layout.item_desing_cafe_table,viewGroup,false);
            viewHolder=new ViewHolder(row);
            row.setTag(viewHolder);

        }else{
            viewHolder= (ViewHolder) row.getTag();
        }
        table temp=tableArrayList.get(i);
        viewHolder.tableImage.setImageResource(temp.tableId);
        viewHolder.tableText.setText(temp.tableNumber);




        return row;
    }
}