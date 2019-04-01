package com.example.yemece.activity;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.example.yemece.R;
import com.example.yemece.adapter.ImcsAdapter;
import com.example.yemece.data.Imc;
import com.example.yemece.data.ImcDAO;
import com.example.yemece.dialog.DeleteDialog;

import java.util.List;

public class ListImcsActivity extends ListActivity implements OnItemLongClickListener, DeleteDialog.OnDeleteListener {

    private static final int REQ_EDIT = 100;

    private ImcDAO imcDAO;
    private ImcsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listimcs);

        adapter = new ImcsAdapter(this);
        setListAdapter(adapter);

        getListView().setOnItemLongClickListener(this);


        imcDAO = ImcDAO.getInstance(this);

        updateList();
    }

    private void updateList() {
        List<Imc> imcs = imcDAO.list();
        adapter.setItems(imcs);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.action_listimcs, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            Intent intent = new Intent(getApplicationContext(), EditImcActivity.class);
            startActivityForResult(intent, REQ_EDIT);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_EDIT && resultCode == RESULT_OK) {
            updateList();
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(getApplicationContext(), EditImcActivity.class);
        intent.putExtra("imc", adapter.getItem(position));
        startActivityForResult(intent, REQ_EDIT);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
        Imc imc = adapter.getItem(position);

        DeleteDialog dialog = new DeleteDialog();
        dialog.setImc(imc);
        dialog.show(getFragmentManager(), "deleteDialog");
        return true;
    }

    @Override
    public void onDelete(Imc imc) {
        imcDAO.delete(imc);
        updateList();
    }
}
