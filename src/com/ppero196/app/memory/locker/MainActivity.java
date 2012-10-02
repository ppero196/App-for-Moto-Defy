package com.ppero196.app.memory.locker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {
    private ListView mListAppInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set layout for the main screen
        setContentView(R.layout.activity_main);

        // load list application
        mListAppInfo = (ListView)findViewById(R.id.lvApps);
        // create new adapter
        AppInfoAdapter adapter = new AppInfoAdapter(this, Utilities.getInstalledApplication(this), getPackageManager());
        // set adapter to list view
        mListAppInfo.setAdapter(adapter);
        // implement event when an item on list view is selected
        mListAppInfo.setOnItemClickListener(new OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView parent, View view, int pos, long id) {
        		// get the list adapter
        		AppInfoAdapter appInfoAdapter = (AppInfoAdapter)parent.getAdapter();
        		// get selected item on the list
        		ApplicationInfo appInfo = (ApplicationInfo)appInfoAdapter.getItem(pos);
        		// launch the selected application
        		//Utilities.launchApp(parent.getContext(), getPackageManager(), appInfo.packageName);
        	}
		});
        final Button button = (Button) findViewById(R.id.butt1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	String filename = "test.txt";
            	File file = new File(Environment.getExternalStorageDirectory(), filename);
            	FileOutputStream fos;
            	byte[] data = new String("Package name: " + getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA) ).getBytes();
            	byte[] data1 = new String("        ").getBytes();
            	byte[] data2 = new String("        ").getBytes();
            	byte[] data3 = new String("        ").getBytes();
            	byte[] data4 = new String("        ").getBytes();
            	byte[] data5 = new String("        ").getBytes();
            	byte[] data6 = new String("Size").getBytes();
            	try {
            	    fos = new FileOutputStream(file);
            	    fos.write(data);
            	    fos.write(data1);
            	    fos.write(data2);
            	    fos.write(data3);
            	    fos.write(data4);
            	    fos.write(data5);
            	    fos.write(data6);
            	    fos.flush();
            	    fos.close();
            	} catch (FileNotFoundException e) {
            	    // handle exception
            	} catch (IOException e) {
            	    // handle exception
            	}

            	Toast.makeText(getApplicationContext(), 
                        "Apps locked in memory!", Toast.LENGTH_LONG).show();
               
            	}          
        });
}
}
